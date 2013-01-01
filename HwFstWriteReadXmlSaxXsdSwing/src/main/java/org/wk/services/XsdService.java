package org.wk.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.wk.panels.HelloPanel;
import org.xml.sax.InputSource;

public class XsdService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.xsd";
	
	
	public void copyXsdFileToTmpFolder() {

		try {

			InputStream is = HelloPanel.class.getResourceAsStream("/" + FILE_NAME);
			OutputStream os = new FileOutputStream(new File(FILE_PATH + FILE_NAME));

			writeStreamInputToOuptpu(is, os, new byte[1024]);

			is.close();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void writeStreamInputToOuptpu(InputStream is, OutputStream os, byte[] buf ) {

		try {

			int numRead;
		      while ( (numRead = is.read(buf) ) >= 0) {
		          os.write(buf, 0, numRead);
		      }

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	public void validateXmlByXsd(File xmlFile, File xsdFile) {
		
		try {
			
			Source source = new SAXSource(new InputSource(new FileInputStream(xmlFile)));
            
            validateXmlByXsd(source, xsdFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void validateXmlByXsd(Source xmlSource, File xsdFile) {
		
		try {
			
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		    SchemaFactory factory = SchemaFactory.newInstance(language);
		    Schema schema = factory.newSchema(xsdFile);
		    
		    Validator validator = schema.newValidator();
		    validator.validate(xmlSource);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
