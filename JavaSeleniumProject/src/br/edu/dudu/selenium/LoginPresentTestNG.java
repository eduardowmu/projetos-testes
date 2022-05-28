package br.edu.dudu.selenium;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import br.edu.dudu.driver.DriverFactory;

//classe que abre a página e que preenche os
//campos de entrada
public class LoginPresentTestNG 
{	WebDriver driver;
	WebElement loginID, passWord; //variáveis da classe
	@Test public void loginElementsPresentTest()
	{	driver.findElement(By.id("email")).sendKeys("duduedu_bom@hotmail.com");
		driver.findElement(By.id("pass")).sendKeys("@(()$%*");	//preencha com a senha
		driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);
		JOptionPane.showMessageDialog(null, "Acesso realizado!");
	}

	@BeforeTest public void setUp()
	{	driver = DriverFactory.open("firefox");			//instancia um driver
		driver.get("https://pt-br.facebook.com/");					//página de acesso
		loginID = driver.findElement(By.id("email"));	//elemento de entrada do e-mail
		passWord = driver.findElement(By.id("pass"));	//elemento de entrada de senha
	}
	
	@AfterTest public void finish()
	{driver.close();}
}