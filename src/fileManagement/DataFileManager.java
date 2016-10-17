package fileManagement;

import generalUtilities.DataUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

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
	 * Populates file according to user input
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
			comTable.displayTable();

			//Populating file with user input
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

		//Creating records/attributes from user input
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

	/**
	 * Returns an array list containing the records of chosen attributes
	 * @param chosenAtts
	 * @param dataSet
	 * @return recordList -  containing only chosen attributes 
	 */
	public static ArrayList<String> prepareRecord(ArrayList<Integer> chosenAtts, ArrayList<Record> dataSet){
		ArrayList<String> recordList = new ArrayList<>();
		String str = "";
		//Creates an array list string
		for(int i=0; i<dataSet.size(); i++){
			for(int p=0; p<chosenAtts.size(); p++){
				str = str.concat(String.format(DataUtils.STRINGFORMAT, dataSet.get(i).readData(chosenAtts.get(p))));
			}
			recordList.add(str);
			str = "";
		}
		return recordList;
	}

	/**
	 * Small method to get option selection from user
	 * @param sc
	 * @return answer
	 */
	public static int optionGet(Scanner sc){
		boolean legit = false;
		int answer = 0;
		String input = null;

		System.out.println("Please Input Answer (Y/N) [input: 'exit' to stop adding attributes]: \n");
		//Option selection logic
		while(!legit){
			input = sc.next();

			if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")){
				answer = -1;
				legit = true;

			}
			else if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
				answer = 1;
				legit = true;

			}
			else if(input.equalsIgnoreCase("e") || input.equalsIgnoreCase("exit")){
				answer = 0;
				legit = true;
			}
			else{

				System.out.println("Please enter a valid input!\n");
			}
		}
		return answer;
	}

	/**
	 * Gets a selection of attributes from user as an array list of integers
	 * @param sc
	 * @param table
	 * @return selectedAtt
	 */
	private static ArrayList<Integer> getAtts(Scanner sc, Table table){

		ArrayList<Integer> selectedAtt = new ArrayList<>();
		int answer = 0;

		System.out.println("\n Displaying all Attributes: \n");
		table.displaySchema();

		System.out.println("\n ANALYZE ALL ATTRIBUTES? \n");
		answer = optionGet(sc);

		if(answer == 1){
			for(int i = 0; i< table.getNumberOfAttrs();i++){
				selectedAtt.add(i);
			}
		}	
		if(answer == -1){
			for(int i = 0; i< table.getNumberOfAttrs();i++){
				System.out.println("Add Attribute: " + table.getAttribute(i) + " ?" );
				answer = optionGet(sc);
				if(answer == 1){
					selectedAtt.add(i);
				}
				if(answer == 0){
					i = table.getNumberOfAttrs();
				}
			}	
		}			
		return selectedAtt;		
	}

	/**
	 * Analyzes frequency and percentage of a file and prints it as a table
	 * @param source
	 * @param tableData
	 * @param in
	 */
	public static void analyzeFile(File source, TableSchema tableData, Scanner in){
		try {

			RandomAccessFile file = new RandomAccessFile(source, "r");
			tableData = TableSchema.getInstance(file);
			Table comTable =  new Table(tableData);
			comTable.readTableDataFromFile(file);

			ArrayList<Record> records = comTable.getRecordList();
			ArrayList<Integer> listAtts = getAtts(in , comTable);
			//Check that user selected attributes to analyze
			if(listAtts.size()==0)
				System.out.println("No attributes to analyze! \n");
			else{
				ArrayList<String> recordStrings = prepareRecord(listAtts , records);
				ArrayList<Entry<String, Float>> map = analyzeRecords(recordStrings);

				float percentage;
				float frequency;

				//Print attribute names, records, frequencies, percentages
				for(int i=0; i<listAtts.size(); i++){
					System.out.print(String.format(DataUtils.STRINGFORMAT, comTable.getAttribute(listAtts.get(i)).getName()));
				}
				System.out.println(String.format(DataUtils.STRINGFORMAT, "Frequency") + String.format(DataUtils.STRINGFORMAT, "Percentage") + "	\n");
				for(Entry<String, Float> e : map){
					frequency = (float)e.getValue();
					percentage = frequency/records.size()*100;
					System.out.println(e.getKey() + String.format(DataUtils.STRINGFORMAT, frequency) +String.format(DataUtils.STRINGFORMAT, percentage + "%	"));
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Analyzes a set of record using hash map, returns an array list with record and its frequency
	 * @param dataSet
	 * @return results
	 */
	public static ArrayList<Entry<String, Float>> analyzeRecords(ArrayList<String> dataSet){
		HashMap< String, Float > mapList = new HashMap<String, Float>();
		ArrayList<Map.Entry<String, Float>> results = new ArrayList<Map.Entry<String, Float>>();
		//Travel through map comparing records
		for(int i= 0; i<dataSet.size();i++ )
		{
			if(mapList.containsKey(dataSet.get(i)))
			{
				mapList.put(dataSet.get(i), mapList.get(dataSet.get(i)) + 1);
			}
			else
			{
				mapList.put(dataSet.get(i), (float)1);
			}
		}
		for (Entry<String,Float> entry : mapList.entrySet()){ 
			results.add(entry);
		}
		return results;
	}

}
