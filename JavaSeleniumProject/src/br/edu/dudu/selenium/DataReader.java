package br.edu.dudu.selenium;

import java.util.List;

import javax.swing.JOptionPane;

import br.edu.dudu.driver.CSV;
import br.edu.dudu.driver.Excel;

public class DataReader 
{	public static void main(String[] args) 
	{	readXLS();
		//readCSV();
	
	}//chamando o m?todo de leitura de arquivo
	
	//ir? buscar a localiza??o do arquivo de massa e fazer a
	//leitura do arquivo
	public static void readCSV()//lista de dados
	{	List<String[]> datas = CSV.get("C:\\webdrivers\\UsersList.csv");
		//looping por todas as linhas de dados do arquivo
		String msg = "";
		for(String[] data : datas)
		{	for(String field:data)//looping por todas as colunas de dados
			{msg += field + " | ";}
			msg += "\n";
		}
		JOptionPane.showMessageDialog(null, msg);
	}
	//mesma fun??o acima mas de outro formato
	public static void readXLS()
	{	//neste formato, precisa ser uma matriz de String
		String[][] datas = Excel.get("C:\\webdrivers\\UserLogin.xls");
		String msg = "";
		//talvez seja melhor pegar uma vers?o mais recente ou atrasada
		for(String[] data:datas)
		{System.out.println(data[0]);}
	}
}