package org.wk.services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CsvService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.csv";
	
	
	public void writeLinesToFile(List<String[]> lines, File file) {
		
		try {
			
			CSVWriter writer = new CSVWriter(new FileWriter(file));
			writer.writeAll(lines);
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<String[]> readLinesFromFile(File file) {
		
		List<String[]> result = new ArrayList<String[]>();
		
		try {
			
			CSVReader csvReader = new CSVReader(new FileReader(file));
			result = csvReader.readAll();
			csvReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	

}
