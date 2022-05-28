package br.edu.dudu.selenium;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import br.edu.dudu.driver.DriverFactory;

public class ATagTest 
{	WebDriver driver;
	WebElement loginID, passWord; //variáveis da classe
	
	//queremos testar a presença da tag <a></a> >> hyperlinks
	@Test public void loginElementsPresentTest()
	{	/*driver.findElement(By.id("email")).sendKeys("duduedu_bom@hotmail.com");
		driver.findElement(By.id("pass")).sendKeys("@(()$%*");	//preencha com a senha
		driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);
		JOptionPane.showMessageDialog(null, "Acesso realizado!");*/
		
		//uma lista de elementos web do tipo <a></a>
		List<WebElement> webElements = driver.findElements(By.tagName("a"));
		//imprime a quantidade de <a></a> existente na página
		JOptionPane.showMessageDialog(null, webElements.size());
		//looping por todos os elementos
		/*for(WebElement we:webElements)
		{System.out.println(we.getText());}*///imprime todo valor dentro dessa tag
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