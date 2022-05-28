package br.edu.dudu.parameters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.edu.dudu.driver.CSV;
import br.edu.dudu.driver.DriverFactory;
//executar com os valores parametrizados
@RunWith(value = Parameterized.class)
public class NewUserAccount 
{	String name, email, phone, password, gender, country;
	boolean weeklyEmail, monthlyEmail, occasionalEmail;
	WebDriver driver;

	//método de teste unitário
	@Test public void newAccountTest()
	{	JOptionPane.showMessageDialog(null, "New User: " + 
			this.name + " | " + this.email + " | " + this.phone + " | " +
			this.gender + " | " + this.password + " | " + this.country +
			" | " + this.weeklyEmail + " | " + this.monthlyEmail + " | " +
			this.occasionalEmail);
	
		WebElement nameElement = driver.findElement(By.name("ctl00$MainContent$txtFirstName"));
		WebElement emailElement = driver.findElement(By.id("MainContent_txtEmail"));
		WebElement phoneElement = driver.findElement(By.id("MainContent_txtHomePhone"));
		WebElement passElement = driver.findElement(By.id("MainContent_txtPassword"));
		WebElement vPassElement = driver.findElement(By.id("MainContent_txtVerifyPassword"));
		WebElement countryElement = driver.findElement(By.id("MainContent_menuCountry"));
		WebElement maleRadio = driver.findElement(By.id("MainContent_Male"));
		WebElement femaleRadio = driver.findElement(By.id("MainContent_Female"));
		WebElement weeklyCheckbox = driver.findElement(By.id("MainContent_checkWeeklyEmail"));
		WebElement submitButton = driver.findElement(By.id("MainContent_btnSubmit"));
		
		//Find out the form
		nameElement.sendKeys(this.name);
		emailElement.sendKeys(this.email);
		phoneElement.sendKeys(this.phone);
		passElement.sendKeys(this.password);
		vPassElement.sendKeys(this.password);
		new Select(countryElement).selectByVisibleText(this.country);
		
		//gender Radio Button algorithm
		if(this.gender.equalsIgnoreCase("Male"))	{maleRadio.click();}
		else {femaleRadio.click();}
		
		//checkbox algorithm
		if(this.weeklyEmail)
		{	if(!weeklyCheckbox.isSelected()) 
			{weeklyCheckbox.click();}
		}
		else
		{	if(weeklyCheckbox.isSelected())
			{weeklyCheckbox.click();}
		}
		
		submitButton.click();
	}
	
	//pré-teste
	@Before public void setUp()
	{	driver = DriverFactory.open("firefox");
		driver.get("http://sdettraining.com/trguitransactions/NewAccount.aspx");
		//driver.findElement(By.xpath("html/body/form/div[3]/div[2]/div/div[2]/a"));
	}
	
	//pós teste, fechar a janela
	@After public void finish()
	{driver.close();}
		
	//retorna os parâmetros que desejo mostrar
	@Parameters public static List<String[]> getData() 
	{	List<String[]> datas = CSV.get("C:\\webdrivers\\UsersList.csv");
		return datas;
	}
	
	//construtor da classe
	public NewUserAccount(String name, String email, 
		String phone, String gender, String password,
		String country, String weeklyEmail, 
		String monthlyEmail, String occasionalEmail)
	{	this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.password = password;
		this.country = country;
		
		//se no arquivo CSV estiver com valor true neste
		//parâmetro
		if(weeklyEmail.equals("TRUE"))	this.weeklyEmail = true;
		else this.weeklyEmail = false;
		
		if(monthlyEmail.equals("TRUE"))	this.monthlyEmail = true;
		else this.monthlyEmail = false;
		
		if(occasionalEmail.equals("TRUE"))	this.occasionalEmail = true;
		else this.occasionalEmail = false;
	}
}
