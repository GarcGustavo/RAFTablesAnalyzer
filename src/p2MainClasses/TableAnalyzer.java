package p2MainClasses;

import java.io.File;
import java.util.Scanner;

import tableCollectionClasses.TableSchema;
import fileManagement.DataFileManager;

/**
 * RAF Table Analyzer main class, requires a valid file name as argument
 * @author Gustavo A. Reyes
 *
 */
public class TableAnalyzer {

	/**
	 * Main method
	 * @param args - File name
	 */
	public static void main(String args[]){

		TableSchema tableData = null;
		File source = DataFileManager.getFile(args[0]);
		Scanner in = new Scanner(System.in);
		
		//If there's no file to analyze
		if(source == null){
			System.out.println("Not a valid random access file!\n");
		}
		else
			DataFileManager.analyzeFile(source, tableData, in);
		
		System.out.println("\n	*** PROGRAM TERMINATED ***	\n");
	}
}
