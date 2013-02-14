package org.wk.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wk.entities.UserEntity;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Service {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.xml";
	
	
	public void saveName(String name) {
		
		File file = new File(FILE_PATH + FILE_NAME);
		writeUserToFile(new UserEntity(name), file);
		
	}
	
	public String loadName() {
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = readUserFromFile(file);
		return user.getName();
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
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
