package org.wk.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

public class MailService {
	
	
	// Sending mail
	private static String MAIL_SENDING_HOST;
	private static String MAIL_SENDING_PORT;
	private static String MAIL_SENDING_USER;
	private static String MAIL_SENDING_PASSWORD;
	
	// Receiving mail
	private static String MAIL_RECEIVING_HOST;
	private static String MAIL_RECEIVING_PROTOCOL;
	private static String MAIL_RECEIVING_USER;
	private static String MAIL_RECEIVING_PASSWORD;
	
	// Mail content
	public static String MAIL_FROM;
	public static String MAIL_TO;
	public static String MAIL_SUBJECT;
			
	private TextService textService;
	
	
	public MailService(){
		
		textService = new TextService();
		
		try {
			
			Properties prop = new Properties();
			prop.load(MailService.class.getResourceAsStream("/project.properties"));
			
			MAIL_SENDING_HOST = prop.getProperty("mail.sending.host");
			MAIL_SENDING_PORT = prop.getProperty("mail.sending.port");
			MAIL_SENDING_USER = prop.getProperty("mail.sending.user");
			MAIL_SENDING_PASSWORD = prop.getProperty("mail.sending.password");
			
			MAIL_RECEIVING_HOST = prop.getProperty("mail.receiving.host");
			MAIL_RECEIVING_PROTOCOL = prop.getProperty("mail.receiving.protocol");
			MAIL_RECEIVING_USER = prop.getProperty("mail.receiving.user");
			MAIL_RECEIVING_PASSWORD = prop.getProperty("mail.receiving.password");
			
			MAIL_FROM = prop.getProperty("mail.from");
			MAIL_TO = prop.getProperty("mail.to");
			MAIL_SUBJECT = prop.getProperty("mail.subject");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
		
    public void sendMailwithAttachement(String from, String to, String subject, String body, List<File> attachements) {  	
    	
		try {
				 
				Session session = getSendingSession();			
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
				message.setSubject(subject);
				
				Multipart multipart = new MimeMultipart();
				BodyPart messageBodyPart;
				
				// Message
				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText(body);
				multipart.addBodyPart(messageBodyPart);
				
				// Attachements
				for (File file : attachements) {
					messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(file);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(file.getName());
					multipart.addBodyPart(messageBodyPart);
				}
				
				message.setContent(multipart);
	 
				Transport.send(message);
	 
				System.out.println("Mail was sent!");
				
		} catch (Exception e) {
		    e.printStackTrace();
		}
				
    }
    
    public Message receiveEmailBySubject(String subject) {
    	
    	try {
    		
    	  Session session = getReceivingSession();
    	  
    	  Store store = session.getStore();
    	  store.connect(MAIL_RECEIVING_HOST, MAIL_RECEIVING_USER, MAIL_RECEIVING_PASSWORD);
    	  Folder folder=store.getFolder("INBOX");
    	  folder.open(Folder.READ_ONLY);
    	  
    	  SearchTerm term = new SubjectTerm(subject);
    	  Message[] messages = folder.search(term);
    	  return messages[0];
    	  
    	} catch (Exception e) {
    	  e.printStackTrace();
    	}
    	
    	return null;

	}
    
    public String getMessageContent(Message msg){
    	
    	String content = null;
        	
    	try {
    		
    		if (msg.isMimeType("text/plain")) {
                content = msg.getContent().toString();
            } else {
                content = getContentFromMultipartMessage(msg);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return content;
    	
    }
    
    public List<File> getMessageAttachements(Message msg){
    	
    	List<File> attachments = new ArrayList<File>();
    	
    	try {
			
    		if (msg.isMimeType("text/plain")) {
    			return attachments;
        	}
    		
    		Multipart multipart = (Multipart) msg.getContent();
    		for (int x = 0; x < multipart.getCount(); x++) {
    			
    			BodyPart bodyPart = multipart.getBodyPart(x);
    			String disposition = bodyPart.getDisposition();

    		    if (disposition == null || !Part.ATTACHMENT.equalsIgnoreCase(disposition)) {
    		    	continue;
    		    }
    		    
    		    File file = getAttachmentFile(bodyPart);
    			attachments.add(file);
    		    
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return attachments;
    	
    }
    
   
    // ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
    
    
    private Session getSendingSession(){
    	
    	Properties props = new Properties();
    	props.put("mail.smtp.host", MAIL_SENDING_HOST);
    	props.put("mail.smtp.port", MAIL_SENDING_PORT);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_SENDING_USER, MAIL_SENDING_PASSWORD);
			}
		  });
		
		return session;
    	
    }
    
    private Session getReceivingSession(){
    	
    	Properties props = System.getProperties();
    	props.setProperty("mail.store.protocol", MAIL_RECEIVING_PROTOCOL);
    	return Session.getDefaultInstance(props, null);
    	
    }
        
    private String getContentFromMultipartMessage(Message msg) {

        String content = null;
        try {
            String disposition;
            BodyPart part;
            Multipart mp = (Multipart) msg.getContent();

            int mpCount = mp.getCount();
            for (int m = 0; m < mpCount; m++) {
                part = mp.getBodyPart(m);
              
                disposition = part.getDisposition();
                if (disposition != null && disposition.equals(Part.INLINE)) {
                    content = part.getContent().toString();
                } else {
                    content = part.getContent().toString();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        return content;
    }
    
    private File getAttachmentFile(BodyPart bodyPart) throws Exception{
    	
    	File file = new File(TextService.FILE_PATH + bodyPart.getFileName());
    	
    	InputStream is = bodyPart.getInputStream();
		FileOutputStream fos = new FileOutputStream(file);
		byte[] buf = new byte[4096];
		int bytesRead;
		while((bytesRead = is.read(buf))!=-1) {
		    fos.write(buf, 0, bytesRead);
		}
		fos.close();
		
		return file;
    	
    }

}
