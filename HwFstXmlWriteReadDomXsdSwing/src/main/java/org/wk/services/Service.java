package org.wk.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.wk.entities.UserEntity;

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
			Source sourceXml = convertDomToSource(fileXml);
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
	
	private Source convertDomToSource(File xmlFile) throws Exception{
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(xmlFile);
        
        return new DOMSource(doc);
		
	}
	
	private void writeUserToFile(UserEntity user, File file){
		
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element userEntityElement = doc.createElement("userEntity");
            doc.appendChild(userEntityElement);
            
            Element nameElement = doc.createElement("name");
            userEntityElement.appendChild(nameElement);
            
            Text nameElementText = doc.createTextNode(user.getName());
            nameElement.appendChild(nameElementText);
            
			DOMSource source = new DOMSource(doc);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            transformer.transform(source, new StreamResult(file));
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private UserEntity readUserFromFile(File file){
		
		UserEntity result = null;
		
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            
            NodeList nameNodeList = doc.getElementsByTagName("name");
            Node nameNode = nameNodeList.item(0);
            NodeList nameNodeTextList = nameNode.getChildNodes();
            Node nameNodeText = nameNodeTextList.item(0);
            String name = nameNodeText.getNodeValue();
            
            result = new UserEntity(name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	

}
