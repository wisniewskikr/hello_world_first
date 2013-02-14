package org.wk.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.wk.entities.UserEntity;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Service {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.xml";
	public final static String FILE_XSD_NAME = "test.xsd";
	
	
	public void saveName(String name) {
		
		saveXsd();
		
		File file = new File(FILE_PATH + FILE_NAME);
		writeUserToFile(new UserEntity(name), file);
		
	}
	
	public String loadName() {
		
		validateByXsd();
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = readUserFromFile(file);
		return user.getName();
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private void saveXsd() {
		
		try {
			
			InputStream is = Service.class.getResourceAsStream("/" + FILE_XSD_NAME);
			OutputStream os = new FileOutputStream(new File(FILE_PATH + FILE_XSD_NAME));

			byte[] buf = new byte[1024];
			int numRead;
		      while ( (numRead = is.read(buf) ) >= 0) {
		          os.write(buf, 0, numRead);
		      }

			is.close();
			os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void validateByXsd() {
		
		try {
			
			File fileXml = new File(FILE_PATH + FILE_NAME);
			Source sourceXml = convertSaxToSource(fileXml);
			File fileXsd = new File(FILE_PATH + FILE_XSD_NAME);
			
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		    SchemaFactory factory = SchemaFactory.newInstance(language);
		    Schema schema = factory.newSchema(fileXsd);
		    
		    Validator validator = schema.newValidator();
		    validator.validate(sourceXml);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Source convertSaxToSource(File xmlFile) throws Exception{
		
		return new SAXSource(new InputSource(new FileInputStream(xmlFile)));
		
	}
	
	private void writeUserToFile(UserEntity user, File file){
		
		try {
			
			OutputStream outputStream = new FileOutputStream(file);

			XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
			                new OutputStreamWriter(outputStream, "utf-8"));

			out.writeStartDocument();
			out.writeStartElement("userEntity");

			out.writeStartElement("name");
			out.writeCharacters(user.getName());
			out.writeEndElement();

			out.writeEndElement();
			out.writeEndDocument();

			out.close();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private UserEntity readUserFromFile(File file){
		
		UserEntity result = null;
		
		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			UserEntityHandler handler = new UserEntityHandler();
			
			saxParser.parse(file, handler);
			String name = handler.getName();
			
			result = new UserEntity(name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	class UserEntityHandler extends DefaultHandler{
		
		boolean isNameElement;
		String name;
		
		@Override
		public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {
 
			if (qName.equalsIgnoreCase("name")) {
				isNameElement = true;
			}
 
		}
		
		@Override
		public void characters(char ch[], int start, int length) throws SAXException {
			
			if (isNameElement) {
				name = new String(ch, start, length);
				isNameElement = false;
			}
			
		}

		public String getName() {
			return name;
		}
		
	}
	

}
