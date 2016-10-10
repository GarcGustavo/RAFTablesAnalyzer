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
		Scanner option = new Scanner(System.in);
		TableSchema tableData = null;
		File source = DataFileManager.getFile(args[0]);
		boolean create = true;
		String selectedOption;

		if(source == null){
			DataFileManager.createRAFile(sc, tableData, args);
			source = DataFileManager.getFile(args[0]);
		}
		while(create){
			System.out.println("Please input record data: \n");
			DataFileManager.populateFile(sc, tableData, source);
			System.out.println("\nDo you wish to add another record?: Y/N \n");
			boolean choice = true;
			while(choice){
				selectedOption = option.next();
				if(selectedOption.equalsIgnoreCase("n") || selectedOption.equalsIgnoreCase("no"))
					create=choice=false;
				else if(selectedOption.equalsIgnoreCase("y") || selectedOption.equalsIgnoreCase("yes"))
					choice = false;
				else
					System.out.println("Please enter a valid input! \n");
			}
		}
		option.close();
		sc.close();
		System.out.println("PROGRAM TERMINATED \n");
	}
}
