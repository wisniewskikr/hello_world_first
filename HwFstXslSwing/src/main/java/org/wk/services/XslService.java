package org.wk.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.wk.jaxb.entities.XslData;
import org.wk.panels.HelloPanel;

public class XslService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String TEMPLATE_XLST = "XslTemplate.xslt";
	public final static String INPUT_XML = "XslData.xml";
	public final static String OUTPUT_FILE = "test.xml";


	public void transformXsl(File inputXml, File templateXslt, File outputFile) {
		
		 try {  
			 
		      TransformerFactory tFactory = TransformerFactory.newInstance();
		      Transformer transformer = tFactory.newTransformer(new StreamSource(templateXslt));
		      transformer.transform(new StreamSource(inputXml), new StreamResult(new FileOutputStream(outputFile)));
		      
       } catch (Throwable t) {
         t.printStackTrace();
       }
		 
   }
	
	public void writeStreamInputToOuptpu(InputStream is, OutputStream os, byte[] buf ) {

		try {

			int numRead;
		      while ( (numRead = is.read(buf) ) >= 0) {
		          os.write(buf, 0, numRead);
		      }

		} catch (Exception e) {
			e.printStackTrace();
		}


	} 	
	
	public void writeObjectToFile(Object obj, Class<? extends Object> clazz, File file){
		
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			jaxbMarshaller.marshal(obj, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Object readObjectFromFile(Class<? extends Object> clazz, File file){
		
		Object result = null;
		
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			result = jaxbUnmarshaller.unmarshal(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	private void writeNameToXmlFileInTmpFolder(String name){
		
		String fileLocation = FILE_PATH + INPUT_XML;
		File file = new File(fileLocation);
		writeObjectToFile(new XslData(name), XslData.class, file);
		
	}
	
	private void copyXslFileToTmpFolder() {

		try {

			InputStream is = HelloPanel.class.getResourceAsStream("/" + TEMPLATE_XLST);
			OutputStream os = new FileOutputStream(new File(FILE_PATH + TEMPLATE_XLST));

			writeStreamInputToOuptpu(is, os, new byte[1024]);

			is.close();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void transformXsl(String name) {
		
		writeNameToXmlFileInTmpFolder(name);
		copyXslFileToTmpFolder();
		
		String fileLocation = null; 
		fileLocation = FILE_PATH + INPUT_XML;
		File inputXml = new File(fileLocation);
		
		fileLocation = FILE_PATH + TEMPLATE_XLST;
		File templateXsl = new File(fileLocation);
		
		fileLocation = FILE_PATH + OUTPUT_FILE;
		File outputFile = new File(fileLocation);
		
		transformXsl(inputXml, templateXsl, outputFile);
		
	}		

}
