/*
 * DataProvider Class 
 * 
 * Reads data from *.txt file and returns array with values 
 */
	package utils;

	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;
	import org.apache.log4j.LogManager;
	import org.apache.log4j.Logger;
	import org.apache.log4j.PropertyConfigurator;

	public class DataProviderClass {

	private ArrayList<Object[]> arrayTestValues = new ArrayList<Object[]>();
	private String separator = "\t";
	private Logger log = LogManager.getLogger("DataProviderClass log: ");
	
	public DataProviderClass(){
		PropertyConfigurator.configure("Log4j.properties");
	}

/**
 * Parse line from a file and compare with value in text field. Show result
 */
	private void parseLine(String line){
		if(line.startsWith("//")){				//ignore lines with // in testdata file
			return;
		}else{
			Object[] parsedLine = line.split(separator);
			arrayTestValues.add(parsedLine);
		}
	}
	
/**
 * 	Reads data from file. 
 * 
 */
	public Object[][] readTestData(String fileName) {
		Object[][] tempArr = new Object[(arrayTestValues.size())][arrayTestValues.get(0).length];		//creates Object[][] with length as arrayTestValues
		
		try {
		fileProcess(fileName);
		} catch (IOException e) {
		log.error("I/O exception"+ e);
		}
		
		for(int i=arrayTestValues.size()-1; i>=0; i--){
			tempArr[i] = arrayTestValues.get(i);
		}
		
		return tempArr;
	}
	
/**
 * Reads line from file
 */
	private void fileProcess(String fileName) throws IOException{
		File file = new File(System.getProperty("user.dir")+"\\TestData\\"+fileName);
		BufferedReader bfrReader;
		FileReader fileRdr = new FileReader(file);
		bfrReader = new BufferedReader(fileRdr);
		String line =null;
		
		try{
			while((line = bfrReader.readLine())!=null){
				parseLine(line);
			}
		}catch (Exception e){
			e.printStackTrace();
			log.error("Error in read file method. Please check file location.");
		}finally{
			bfrReader.close();
			fileRdr.close();
		}
	}
		

	
}
