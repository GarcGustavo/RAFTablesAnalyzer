package fileManagement;

import generalUtilities.DataUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import tableCollectionClasses.AttributeInSchema;
import tableCollectionClasses.Record;
import tableCollectionClasses.Table;
import tableCollectionClasses.TableSchema;

/**
 * Methods to manipulate random access files
 * @author Gustavo
 *
 */
public class DataFileManager {

	/**
	 * Finds specified file
	 * @param fileName
	 * @return file
	 */
	public static File getFile(String fileName) {

		File directory = new File("InputData");
		File[] fList = directory.listFiles();
		for (File file : fList){
			if(fileName.equals(file.getName()))
				return file;
		}
		return null;
	}
	
	/**
	 * populates file according to user input
	 * @param sc scanner used for input
	 * @param tableData table schema object
	 * @param File
	 */
	public static void populateFile(Scanner sc, TableSchema tableData, File source){
		try {
			RandomAccessFile file = new RandomAccessFile(source, "rw");
			tableData = TableSchema.getInstance(file);
			Table comTable =  new Table(tableData);
			comTable.readTableDataFromFile(file);
			Record input = comTable.getNewRecordInstance();
			input.readDataRecordFromUser(sc);
			comTable.addRecord(input);
			input.writeToFile(file);
			comTable.displayTable();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * creates a random access file with the appropriate attributes
	 * @param sc scanner
	 * @param tableData table schema
	 * @param fileName string
	 */
	public static void createRAFile(Scanner sc, TableSchema tableData, String fileName[]){

		System.out.println("File does not exist, creating new file "+ "'"+fileName[0]+"'" +"\n");
		System.out.println("Specify Number of Attributes for Table: \n");

		int attNumber = sc.nextInt();

		tableData =  new TableSchema(attNumber);

		int type = 0;
		int offset = 0; 
		int runningAtt = 0; 
		String name; 

		while( runningAtt < attNumber ){
			System.out.println("Please Enter Data Type of Attribute " + (runningAtt+1) + ":\n"
					+ " Byte	-	Input: 0\n "
					+ "Char	-	Input: 1\n "
					+ "Short	-	Input: 2\n "
					+ "Int	-	Input: 3\n "
					+ "Long	-	Input: 4\n "
					+ "Float	-	Input: 5\n "
					+ "Double	-	Input: 6\n "
					+ "Boolean-	Input: 7\n"
					+ " Date	-	Input: 8\n");

			type = sc.nextInt();

			if(type > 8 || type < 0 )
				System.out.println("Error: Please input values between 0 and 8");
			else{
				System.out.println("Please Enter Name of Attribute " + (runningAtt+1) + ":\n");
				name = sc.next();
				tableData.addAttribute(new AttributeInSchema(name,type,offset));
				offset =+ offset + DataUtils.getTypeSize(type);
				runningAtt++;
			}
		}
		try {
			tableData.saveSchema(new RandomAccessFile("InputData/" + fileName[0], "rw"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
