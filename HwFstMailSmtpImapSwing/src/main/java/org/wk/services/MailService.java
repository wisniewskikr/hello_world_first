package org.wk.services;

import java.util.Properties;

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
import javax.mail.internet.MimeMessage;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

public class MailService {
	
	
	// Sending mail
	private static String MAIL_SMTP_HOST;
	private static String MAIL_SMTP_PORT;
	private static String MAIL_SMTP_USER;
	private static String MAIL_SMTP_PASSWORD;
	
	// Receiving mail
	private static String MAIL_IMAP_HOST;
	private static String MAIL_IMAP_USER;
	private static String MAIL_IMAP_PASSWORD;
	
	// Mail content
	public static String MAIL_FROM;
	public static String MAIL_TO;
	public static String MAIL_SUBJECT;
	
	
	public MailService(){
		
		try {
			
			Properties prop = new Properties();
			prop.load(MailService.class.getResourceAsStream("/project.properties"));
			
			MAIL_SMTP_HOST = prop.getProperty("mail.smtp.host");
			MAIL_SMTP_PORT = prop.getProperty("mail.smtp.port");
			MAIL_SMTP_USER = prop.getProperty("mail.smtp.user");
			MAIL_SMTP_PASSWORD = prop.getProperty("mail.smtp.password");
			
			MAIL_IMAP_HOST = prop.getProperty("mail.imap.host");
			MAIL_IMAP_USER = prop.getProperty("mail.imap.user");
			MAIL_IMAP_PASSWORD = prop.getProperty("mail.imap.password");
			
			MAIL_FROM = prop.getProperty("mail.from");
			MAIL_TO = prop.getProperty("mail.to");
			MAIL_SUBJECT = prop.getProperty("mail.subject");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
		
    public void sendMail(String from, String to, String subject, String body) {  	
    	
		try {
				 
				Session session = getSessionSMTP();			
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
				message.setSubject(subject);
				message.setText(body);
	 
				Transport.send(message);
	 
				System.out.println("Mail was sent!");
				
		} catch (Exception e) {
		    e.printStackTrace();
		}
				
    }
    
    public Message receiveEmailBySubject(String subject) {
    	
    	try {
    		
    	  Session session = getSessionIMAPS();
    	  
    	  Store store = session.getStore("imaps");
    	  store.connect(MAIL_IMAP_HOST, MAIL_IMAP_USER, MAIL_IMAP_PASSWORD);
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
    
    
    // ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
    
    
    private Session getSessionSMTP(){
    	
    	Properties props = new Properties();
    	props.put("mail.smtp.host", MAIL_SMTP_HOST);
    	props.put("mail.smtp.port", MAIL_SMTP_PORT);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_SMTP_USER, MAIL_SMTP_PASSWORD);
			}
		  });
		
		return session;
    	
    }
    
    private Session getSessionIMAPS(){
    	
    	Properties props = System.getProperties();
    	props.setProperty("mail.store.protocol", "imaps");
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

}
