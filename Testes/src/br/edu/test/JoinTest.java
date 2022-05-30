package br.edu.test;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;

import br.edu.test.driver.DriverFactory;

public class JoinTest
{	public static void main(String[] args) 
	{	//atributos de classe
		String name = "Anonimoua";
		String surname = "Anonimous";
		String celphone = "99999999999";
		String password = "!Qw23456";
		String browser = "chrome";
		int day = 12;
		int mon = 12;
		int year = 15;
		int sex = 2;
		WebDriver driver = null;
		
		//1 - Open Browser to Account
		driver = DriverFactory.open(browser);
		 
		//2 - Navigate to the web browser app https://pt-br.facebook.com/
		//se o driver não estiver nulo
		if(!driver.equals(null))//entra na página desejada
		{	driver.get("https://pt-br.facebook.com/");
			
			//localiza o <input/> de submissão
			//driver.findElement(By.linkText("Inscreva-se")).click();
		
			//uma outra maneira de inserir valor em um <input type="text"/>
			driver.findElement(By.name("firstname")).sendKeys(name);
			
			//inserindo valor no campo Nova Senha
			driver.findElement(By.name("lastname")).sendKeys(surname);
			
			//inserindo numero de telefone
			driver.findElement(By.name("reg_email__")).sendKeys(celphone);
			
			//inserindo senha
			driver.findElement(By.name("reg_passwd__")).sendKeys(password);
			
			driver.findElement(By.cssSelector("input[name='sex'][value='2']")).click();
			
			new Select(driver.findElement(By.name("birthday_day"))).selectByValue(String.valueOf(day));

			new Select(driver.findElement(By.name("birthday_month"))).selectByValue(String.valueOf(mon));

			new Select(driver.findElement(By.name("birthday_year"))).selectByValue(String.valueOf(year));
		}
	}
}