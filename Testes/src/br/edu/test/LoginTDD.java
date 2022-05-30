package br.edu.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTDD 
{	public static void main(String[] arg)
	{	//1 - Open the web browser;
		//1.1 - Nome do driver, path (endereço do diretório do driver + 
		
		System.setProperty("webdriver.chrome.driver",
				//o nome do arquivo do driver)
				"C:\\webdrivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
	
		/*System.setProperty("webdriver.gecko.driver",
				//o nome do arquivo do driver)
				"C:\\webdrivers\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();*/
		
		//2 - Navigate to the web browser app
		//https://pt-br.facebook.com/
		//se o driver não estiver nulo
		if(!driver.equals(null))//entra na página desejada
		{	driver.get("https://pt-br.facebook.com/");
			
			//3 - Insert user's login data
			//3.1 - e-mail da conta.
			//O segundo método envia o valor q ser inserido no campo
			driver.findElement(By.id("email")).sendKeys("duduedu_bom@hotmail.com");
			
			//3.2 - password
			driver.findElement(By.id("pass")).sendKeys("@(()$%*");
						
			//4 - Submit data to access user's page, clicking on the access' button
			//define o usuário da tag <input type="password" id="pass"/>
		    driver.findElement(By.name("pass")).sendKeys(Keys.ENTER);
			
			//5 - Getting the confirmation
		    
			//6 - Close the app (Optional)
		    //driver.close();
		}
	}
}