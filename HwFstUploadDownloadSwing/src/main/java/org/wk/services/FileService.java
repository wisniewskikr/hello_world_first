package org.wk.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileService {
	
	public void writeTextFromFileToFile(File inputFile, File outputFile){

		BufferedReader br = null;
		
		BufferedWriter bw = null;

		try {

			br = new BufferedReader(new FileReader(inputFile));
			
			bw = new BufferedWriter(new FileWriter(outputFile));
			
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				bw.write(strLine);
				bw.newLine();
			}
			bw.flush();

			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null){br.close();}
				if(bw != null){bw.close();}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}

}
