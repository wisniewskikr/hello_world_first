package org.wk.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class ITextService {

	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.pdf";
	
	private static Font boldFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
	private static Font normalFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font italicFont = new Font(Font.TIMES_ROMAN, 10, Font.ITALIC);
	
	public void createHelloWorldPdf(String name, File pdf) {
		
		try {
			
			Document document = initDocument(pdf);
			addMetaData(document);
			openDocument(document);
			addTitlePage(document);
			addContentPage(document, name);
			closeDocument(document);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Document initDocument(File pdf) throws FileNotFoundException, DocumentException {

		Document document = null;

		document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(pdf));
		
		return document;

	}
	
	public void openDocument(Document document) {
		document.open();
	}

	public void closeDocument(Document document) {
		document.close();
	}

	public void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public void addMetaData(Document document) {
		document.addTitle("Hello World PDF");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("wk");
		document.addCreator("wk");
	}
	
	  public void addTitlePage(Document document)
		      throws DocumentException {
		  
		    Paragraph paragraph = new Paragraph();
		    
		    addEmptyLine(paragraph, 1);
		    paragraph.add(new Paragraph("Hello World", boldFont));

		    addEmptyLine(paragraph, 1);
		    paragraph.add(new Paragraph("Using iText", italicFont));

		    document.add(paragraph);
		    
		    document.newPage();
	
	  }
	  
	  public void addContentPage(Document document, String name) throws DocumentException {
		  
		  Paragraph paragraph = new Paragraph();
		  
		  paragraph.add(new Paragraph("Hello World: " + name, normalFont));
		  
		  document.add(paragraph);
		  
	  }

}
