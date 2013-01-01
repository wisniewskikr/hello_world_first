package org.wk.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import org.wk.jaxb.entities.UserEntity;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.xml";
	public XsdService xsdService;
	
	
	public XmlService() {
		xsdService = new XsdService();
	}
	
	
	public void writeObjectToFile(UserEntity user, File file){
		
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
	
	public UserEntity readObjectFromFile(File file){
		
		UserEntity result = null;
		
		try {
			
			File xsdFile = new File(XsdService.FILE_PATH + XsdService.FILE_NAME);
			xsdService.validateXmlByXsd(file, xsdFile);
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			HelloWorldHandler helloWorldHandler = new HelloWorldHandler();
			
			saxParser.parse(file, helloWorldHandler);
			String name = helloWorldHandler.getName();
			
			result = new UserEntity(name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	class HelloWorldHandler extends DefaultHandler{
		
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
