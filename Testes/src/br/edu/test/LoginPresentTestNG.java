package br.edu.test;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import br.edu.test.driver.DriverFactory;

//classe que abre a p�gina e que preenche os
//campos de entrada
public class LoginPresentTestNG 
{	WebDriver driver;
	WebElement loginID, passWord; //vari�veis da classe
	@Test public void loginElementsPresentTest()
	{	loginID.sendKeys("duduedu_bom@hotmail.com");
		passWord.sendKeys("@(()$%*");	//preencha com a senha
		driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);
		JOptionPane.showMessageDialog(null, "Acesso realizado!");
	}

	@Before public void setUp()
	{	driver = DriverFactory.open("chrome");			//instancia um driver
		//define as dimens�es da janela
	    driver.manage().window().fullscreen();
		driver.get("https://pt-br.facebook.com/");		//p�gina de acesso
		loginID = driver.findElement(By.id("email"));	//elemento de entrada do e-mail
		passWord = driver.findElement(By.id("pass"));	//elemento de entrada de senha
	}
	
	//mant�m a navega��o
	@After public void finish()
	{driver.navigate();}
}