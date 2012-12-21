package pl.kwi.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpService {
	
	
	private final static String FTP_SERVER = "localhost";
	private final static String FTP_USER = "admin";
	private final static String FTP_PASSWORD = "admin";
	
	
	public void storeFile(InputStream is, String fileName, String pathInFtp){
				
		FTPClient ftpClient = new FTPClient();
		
		try {
			
			connectToFtp (ftpClient);
			createFoldersInFtp(ftpClient, pathInFtp);
			String locationInFtp = createLocationInFtp(pathInFtp, fileName);
			ftpClient.changeWorkingDirectory("/");
            ftpClient.storeFile(locationInFtp, is);
            
        } catch (Exception e) {                    
            e.printStackTrace();
        } finally {
        	logoutAndDisconnect(ftpClient); 
        }
		
	}
	
	public InputStream retrieveFile(String fileName, String pathInFtp){
		
		InputStream result = null;
		FTPClient ftpClient = new FTPClient();
		
		try {
			
			connectToFtp (ftpClient);
			createFoldersInFtp(ftpClient, pathInFtp);
			String locationInFtp = createLocationInFtp(pathInFtp, fileName);
			
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			if (!ftpClient.retrieveFile(locationInFtp, output)){
				throw new Exception("Can not retrieve file");
			}
			result = new ByteArrayInputStream(output.toByteArray());
    		output.close();
            
        } catch (Exception e) {                    
            e.printStackTrace();
        } finally {
        	logoutAndDisconnect(ftpClient); 
        }
		
		return result;
		
	}
	
	// =================== COMMON METHODS ====================== //
    
	 
    private void connectToFtp( FTPClient ftpClient) throws Exception{
    	    		
		int reply;
        ftpClient.connect(FTP_SERVER);

        // After connection attempt, you should check the reply code to verify
        // success.
        reply = ftpClient.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply))
        {
            ftpClient.disconnect();
        }        

        if (!ftpClient.login(FTP_USER, FTP_PASSWORD))
        {
            ftpClient.logout();
        }


        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        // Use passive mode as default because most of us are behind firewalls these days.
        ftpClient.enterLocalPassiveMode();
	        

    }
    
    private void logoutAndDisconnect(FTPClient ftpClient) {
    	            
        try {
        	
        	if (!ftpClient.isConnected()) {
            	return;
            }
            
            ftpClient.logout();
            ftpClient.disconnect();
            
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
    }
    
    private void createFoldersInFtp(FTPClient ftpClient, String pathInFtp) throws Exception{
    	
        // Check if path exist go through it and create directories. 
        String[] pathArray = pathInFtp.split("/");
        for (String dirString : pathArray) {
            
            List<String> files;

            files = Arrays.asList(ftpClient.listNames());

            if (files.contains(dirString) ) {
                if (!ftpClient.changeWorkingDirectory(dirString)) {
                    logoutAndDisconnect(ftpClient);
                    throw new Exception ( "Problems while changing directory on FTP server. Directory : " + dirString );
                }
            } else {
                if (!ftpClient.makeDirectory(dirString) ) {
                    logoutAndDisconnect(ftpClient);
                    throw new Exception ( "Problems while creating directory tree on FTP server. Directory : " + dirString );
                }
                if (!ftpClient.changeWorkingDirectory(dirString)) {
                    logoutAndDisconnect(ftpClient);
                    throw new Exception ( "Problems while changing directory on FTP server after creation. Directory : " + dirString );
                }
            }
            
        }
            	
    }
    
    private String createLocationInFtp(String remotePath, String remoteFileName) {    	

        String remoteLocation = "";
        
        if ( remotePath.endsWith("/")) {
            remoteLocation = remotePath + remoteFileName ;
        } else {
            remoteLocation = remotePath + "/" + remoteFileName ;
        }
        return remoteLocation;
    }

}
