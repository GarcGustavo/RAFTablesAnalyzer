package p2MainClasses;

import java.io.File;
import java.util.Scanner;

import fileManagement.DataFileManager;
import tableCollectionClasses.TableSchema;

/**
 * @author Gustavo
 *
 */
public class DataFilePopulator {

	/**
	 * @param args String of the name of desired file to modify or create
	 */
	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		TableSchema tableData = null;
		File source = DataFileManager.getFile(args[0]);
		boolean create = true;

		if(source == null){
			DataFileManager.createRAFile(sc, tableData, args);
			source = DataFileManager.getFile(args[0]);
		}

		//Option logic / User input
		while(create){
			System.out.println("Please input record data for the following table: \n");
			DataFileManager.populateFile(sc, tableData, source);
			System.out.println("\nDo you wish to add another record? \n");
			create = DataFileManager.optionGet(sc) > 0;
		}
		sc.close();
		System.out.println("\n	***PROGRAM TERMINATED***	\n");
	}
}
