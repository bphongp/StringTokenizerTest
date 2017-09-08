package takeHome;

/*This program will read in input by the user using stringtokenizer
 * then it will convert each segment type back to string to manipulate strings
 * then back to proper type and increase my salary by 2% the print heading
 * with a new contents in a new file. Then print the file size and rename the file*/

import java.io.*;
import java.util.StringTokenizer;
public class Test
{
	private static String filename = "dbs1.txt";
	private static String filename2 = "dbs2.txt";
	
	private static InputStreamReader isr = new InputStreamReader(System.in);
	private static BufferedReader br = new BufferedReader(isr);
	private static StringBuffer[] sbRepRecords = new StringBuffer[10];//string buffer to manipulate strings
	private static String[] first6Records = new String[6];//string of the first 6 records
	private static Double[] salaries = new Double[10];//array for salary
	private static Integer[] id = new Integer[10];//array for id
	private static Integer[] age = new Integer[10];//array for age
	private static String[] fName = new String[10];//array for first name
	private static String[] lName = new String[10];//array for last name
	private static Boolean[] status = new Boolean[10];//array for status
	
	private static File file1= new File(filename);//first file dbs1
	private static File file2 = new File(filename2);//second file dbs2
	
	public static void main(String[] args) throws java.io.IOException
	{
		String[] records = new String[10];//array of the first 6 records
		int i = 0;//index for loop of arrays
		
		//prompt user to enter 6 records
		System.out.println("Enter 6 records, separated by spaces, that includes:");
		System.out.println("First Name, Last Name, ID Number, Age,");
		System.out.println("Salary, Full Time status(true/false)");
		
		//loop to read in each record entered using tokenizer
		while (i<6)
		{
			records[i] = br.readLine();//read in each string store into records variable
			
			StringTokenizer st = new StringTokenizer(records[i]);//create tokenizer to read in data
			fName[i] = st.nextToken();//convert to string
			lName[i] = st.nextToken();//convert to string
			id[i] = Integer.parseInt(st.nextToken());//convert to int
			age[i] = Integer.parseInt(st.nextToken());//convert to int
			salaries[i] = Double.parseDouble(st.nextToken());//convert to double
			status[i] = Boolean.parseBoolean(st.nextToken());///convert to boolean
		
			//put each type back into string to use string buffer
			first6Records[i]=(fName[i]+" "+ lName[i]+" "+ id[i]+
					" "+ age[i]+" "+salaries[i]+" "+status[i]);	
			//convert each to String to StringBuffer to manipulate strings
			sbRepRecords[i] = new StringBuffer(first6Records[i]);
			i++;
		}//while look to read in 6 records entered using tokenizer
		
		manipulateData();//call method that manipulates each record
		System.out.println("\nPlease enter 4 more records");
		appendMoreRecords();//call method that appends 4 more records
		raiseforMe();//call method that increase my salary by 2%
		
		System.out.println("\nByte size: " +file2.length());//print file size
		
		//renames the file to databaserecord
		boolean renameSuccess = false;
		try
		{
			File file2Rename = new File("databaserecord.txt");
			if(file2Rename.exists())
			{
				if(!(file2Rename.delete()))
				{
					throw new IOException(filename2+ " not successfully renamed");
				}
			}
			if(!file2.renameTo(file2Rename))
			{
				throw new IOException(filename2+" not successfully renamed");
			}
			else
			{
				renameSuccess = true;
			}
		}
		finally
		{
			if(renameSuccess)
			{
				file2.delete();
			}
		}
		System.out.println(renameSuccess);
	}//end main
	private static void manipulateData() throws java.io.IOException
	{
		int i =0;
		//while loop to change space to &
		while (i<6)
		{
			//for loop to look at each character of each record
			for(int k = 0; k < sbRepRecords[i].length(); k++)
			{
				char eachChar = sbRepRecords[i].charAt(k);
				
				//if char of records is a space will change to &
				if(Character.isWhitespace(eachChar))
				{
					sbRepRecords[i].replace(k,k+1, "&");
				}//end if statement to change to &
			}//end for loop for chars
			System.out.println(sbRepRecords[i]);
			i++;
		}//end while loop space to &
		
		FileWriter wf = new FileWriter(file1);//for first 6 records
		BufferedWriter bw = new BufferedWriter(wf);
		
		i = 0;
		//while loop to change from & to spaces
		while(i<6)
		{
			//for loop that reads through and change from & to spaces
			for(int j = 0; j < sbRepRecords[i].length(); j++)
			{
				char indChar = sbRepRecords[i].charAt(j);
				if(indChar =='&')
				{
					sbRepRecords[i].replace(j, j+1, " ");
				}//end if statement replacing back to spaces
			}//end for loop changing to back to spaces
			System.out.println(sbRepRecords[i]);
			
			if(!file1.exists())//check if file exists
				System.out.println(filename+ " Does not exist");
			if(!(file1.isFile() && file1.canWrite()))//check if file1 is a file and can write
				System.out.println(filename+ " cannot be write");
			bw.write(first6Records[i] +"\n");//write the records to file1(dbs1)
			i++;
		}//end while loop & to spaces
		bw.close();
	}//end manipulateData method
	private static void appendMoreRecords() throws java.io.IOException
	{
		int i = 0;
		Double[] salariesAppend = new Double[4];//array for salary
		Integer[] idAppend = new Integer[4];//array for id
		Integer[] ageAppend = new Integer[4];//array for age
		String[] fNameAppend = new String[4];//array for first name
		String[] lNameAppend = new String[4];//array for last name
		Boolean[] statusAppend = new Boolean[4];//array for status
		FileWriter wfAppend = new FileWriter(file1, true);//for append next records
		BufferedWriter bwAppend = new BufferedWriter(wfAppend);
		while (i<4)
		{
			String[] recordsAppend = new String[10];
			recordsAppend[i] = br.readLine();//read in records from user
			StringTokenizer st = new StringTokenizer(recordsAppend[i]);//new string tokenizer
			fNameAppend[i] = st.nextToken();//convert to string
			lNameAppend[i] = st.nextToken();//convert to string
			idAppend[i] = Integer.parseInt(st.nextToken());//convert to int
			ageAppend[i] = Integer.parseInt(st.nextToken());//convert to int		
			salariesAppend[i] = Double.parseDouble(st.nextToken());//convert to double
			statusAppend[i] = Boolean.parseBoolean(st.nextToken());//convert to boolean
			
			String[] next4Records = new String[4];
			
			//back to string so can store in file
			next4Records[i]=(fNameAppend[i]+" "+ lNameAppend[i]+" "+ idAppend[i]+
					" "+ ageAppend[i]+" "+salariesAppend[i]+" "+statusAppend[i]);
			
			if(!file1.exists())//check existence
				System.out.println(filename+ " Does not exist");
			if(!(file1.isFile() && file1.canWrite()))//check if is a file and can be written
				System.out.println(filename+ " cannot be write");
			
			bwAppend.write(next4Records[i] +"\n");//append onto file1
			i++;
		}//end while loop
		bwAppend.close();
	}//end method
	private static void raiseforMe() throws java.io.IOException
	{
		String s1;
		int i = 0;
		String[] recordsFile2 = new String[10];
		FileReader fr = new FileReader(filename);
		BufferedReader br1 = new BufferedReader(fr);
		
		FileWriter fw2 = new FileWriter(file2, true);
		BufferedWriter bw2append = new BufferedWriter(fw2);
		
		FileWriter wf2 = new FileWriter(file2);
		BufferedWriter wb2 = new BufferedWriter(wf2);
		
		//check status to read file1
		if(!file1.exists())
			System.out.println(filename+ " Does not exist");
		if(!(file1.isFile() && file1.canRead()))
			System.out.println(filename+ " cannot be read");
		int j = 0;
		while ((s1 = br1.readLine())!=null && j<10)//while loop reads file1
		{
			StringTokenizer st = new StringTokenizer(s1);
			fName[i] = st.nextToken();
			lName[i] = st.nextToken();
			id[i] = Integer.parseInt(st.nextToken());
			age[i] = Integer.parseInt(st.nextToken());	
			salaries[i] = Double.parseDouble(st.nextToken());
			status[i] = Boolean.parseBoolean(st.nextToken());

			//if-statement that test if tokens contains my name
			if(s1.toLowerCase().contains("bamphiane") && 
					s1.toLowerCase().contains("phongphouthai"))
			{
				salaries[i]= salaries[i]*1.02;
			}
				
			//creates string to write to new file(file2)
			recordsFile2[j]=(fName[i]+" "+ lName[i]+" "+ id[i]+
					" "+ age[i]+" "+salaries[i]+" "+status[i]);	
			System.out.println(s1);//reads file1
			j++;
		}//end while loop to read files
		
		FileReader fr2 = new FileReader(filename2);
		BufferedReader br2 = new BufferedReader(fr2);
		
		//check status of file2 to write new records
		if(!file2.exists())
			System.out.println(filename2+ "does not exist");
		if(!(file2.isFile() && file2.canWrite()))
			System.out.println(filename2+ " cannot write");
		//heading written to file
		wb2.write("\nFirst Name, Last Name, ID, Age, Salary, Status\n");
		
		int k=0;
		while(k<10)
		{
			wb2.write(recordsFile2[k]+"\n");//write to new file
			k++;
		}
		bw2append.close();
		wb2.close();
		if(!file2.exists())
			System.out.println(filename2+ " Does not exist");
		if(!(file2.isFile() && file2.canRead()))
			System.out.println(filename2+ " cannot be read");
		String s2;
		while ((s2 = br2.readLine())!=null)
		{
			System.out.println(s2);
		}
		br1.close();//close the buffered reader		
		br2.close();
	}//end method raiseforMe()
}
