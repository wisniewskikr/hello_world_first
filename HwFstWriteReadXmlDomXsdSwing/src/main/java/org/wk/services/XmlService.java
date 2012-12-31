package org.wk.services;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.wk.jaxb.entities.UserEntity;

public class XmlService {
	
	
	private XsdService xsdService;
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.xml";
	
	
	public XmlService() {
		xsdService = new XsdService();
	}
	
	
	public void writeObjectToFile(UserEntity user, File file){
		
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
			
			// Validation
			File xsdFile = new File(XsdService.FILE_PATH + XsdService.FILE_NAME);
			xsdService.validateXmlByXsd(source, xsdFile);			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            transformer.transform(source, new StreamResult(file));
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public UserEntity readObjectFromFile(File file){
		
		UserEntity result = null;
		
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            
            DOMSource source = new DOMSource(doc);
			
            // Validation
            File xsdFile = new File(XsdService.FILE_PATH + XsdService.FILE_NAME);
			xsdService.validateXmlByXsd(source, xsdFile);
            
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
