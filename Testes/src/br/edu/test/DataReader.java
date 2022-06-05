package br.edu.test;

import java.util.List;

import javax.swing.JOptionPane;

import br.edu.test.driver.CSV;
import br.edu.test.driver.Excel;

public class DataReader 
{	public static void main(String[] args) 
	{	//readXLS();
		readCSV();
	
	}//chamando o método de leitura de arquivo
	
	//irá buscar a localização do arquivo de massa e fazer a
	//leitura do arquivo
	public static void readCSV()//lista de dados
	{	List<String[]> datas = CSV.get("H:\\Dados\\MEU PC\\GROOVETECH\\Testes\\files\\UserAccounts.csv");
		//looping por todas as linhas de dados do arquivo
		String msg = "";
		for(String[] data : datas)
		{	for(String field:data)//looping por todas as colunas de dados
			{msg += field + " | ";}
			msg += "\n";
		}
		//JOptionPane.showMessageDialog(null, msg);
		System.out.println(msg);
	}
	//mesma função acima mas de outro formato
	public static void readXLS()
	{	//neste formato, precisa ser uma matriz de String
		String[][] datas = Excel.get("H:\\Dados\\MEU PC\\GROOVETECH\\Testes\\files\\UserLogin.xls");
		String msg = "";
		//talvez seja melhor pegar uma versão mais recente ou atrasada
		for(String[] data:datas)
		{System.out.println(data[0]);}
	}
}