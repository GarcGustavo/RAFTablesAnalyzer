package p2MainClasses;

import generalUtilities.DataUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import tableCollectionClasses.AttributeInSchema;
import tableCollectionClasses.Record;
import tableCollectionClasses.Table;
import tableCollectionClasses.TableSchema;

public class DataFilePopulator {

	public static void main(String args[]){
		//IOUtils.listFilesAndFolders( "InputData");
		
		Scanner sc = new Scanner(System.in);
		TableSchema tableData = null;
		File source = getFile(args[0]);;
		
		if(source != null)
		{
			
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		else
		{
			System.out.println("File does not exist, Beggining File Creation Process...\n");
			System.out.println("Please Imput Number of Atributes for Intended Table: \n");
			
			int attNumber = sc.nextInt();
			
			tableData =  new TableSchema(attNumber);
			
			int type, offset = 0 , runningAtt = 0; String name; 
			
			while( runningAtt  <attNumber ){
				System.out.println("Please Enter Type of Atribute " + (runningAtt+1) + ":\n"
						+ "Byte - Input: 0\n "
						+ "Char - Input: 1\n "
						+ "Short - Input: 2\n "
						+ "Int - Input: 3\n "
						+ "Long - Input: 4\n "
						+ "Float - Input: 5\n "
						+ "Double - Input: 6\n "
						+ "Boolean - Input: 7\n"
						+ "Date - Input: 8\n");
				
				type = sc.nextInt();
				
				if(type > 8 || type < 0 ){
					System.out.println("Error; Please input correct Values");
				}
				else{
					System.out.println("\nPlease Enter Name of Atribute " + (runningAtt+1) + ":\n");
					
					name = sc.next();
					
					tableData.addAttribute(new AttributeInSchema(name,type,offset));
					
					offset =+ offset + DataUtils.getTypeSize(type);
					runningAtt++;
				}
			}
			
			
			
		try {
			tableData.saveSchema(new RandomAccessFile("InputData/" + args[0], "rw"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		
		}
		
		
		
	}
	
	private static File getFile(String fileName) {
		 
		 File directory = new File("InputData");
		 File[] fList = directory.listFiles();
		 
		 for (File file : fList){
			 
			 if(fileName.equals(file.getName()))
				 return file;
			 }
		 
		 return null;
	 


}

	
}
