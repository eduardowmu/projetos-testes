package br.edu.test.driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CSV 
{	//this method will read and return data from a CSV FILE
	public static List<String[]> get(String filename)
	{	List<String[]> datas = new ArrayList<String[]>();
		String testRow;
		try	//Open the file and read it
		{	BufferedReader br = new BufferedReader(new 
				FileReader(filename));
			/*Read data as long it's not empty
			 Parse the data by comma using .split() method
			 Place into a temporary array, then add to list*/
			while((testRow = br.readLine()) != null)
			{	String[] line = testRow.split(",");
				datas.add(line);
			}
		}
		catch(FileNotFoundException e)
		{	JOptionPane.showMessageDialog(null, 
				e.getMessage() + " " + filename);
		}
		catch(IOException e2)
		{	JOptionPane.showMessageDialog(null, 
				e2.getMessage() + " " + filename);
		}
		return datas;
	}
}