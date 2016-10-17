Author: Gustavo A. Reyes

Data Structures Project 2 Compilation Instructions:

|=========================================================================================================|
 - Eclipse - 
	
	[DataFilePopulator]
	
	Right click the DataFilePopulator class and choose Run_as-->Run_configuration and choose the arguments
	tab, in the arguments tab specify the name of a file you wish to populate or create and click 'Run'.
	(Note that bytes are shown as hexa-decimals on created tables)
	
	[TableAnalyzer]
	
	Right click the TableAnalyzer class and choose Run_as-->Run_configuration and choose the arguments 
	tab, in the arguments tab specify the name of a file you wish to populate or create and click 'Run'.
	
	Make sure you're running the appropriate main class when running the programs!

|=========================================================================================================|

 - Command Line -
	
	Navigate to where the project directory is located (/P2_802136460) and enter 

	[DataFilePopulator]
	
	java -classpath bin p2MainClasses.DataFilePopulator 'filename'
	
	[TableAnalyzer]
	
	java -classpath bin p2MainClasses.TableAnalyzer 'filename'

	Where 'fileName' is the name of the file to be created or populated.
	
|=========================================================================================================|