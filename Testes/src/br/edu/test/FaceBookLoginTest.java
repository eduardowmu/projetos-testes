package br.edu.test;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
public class FaceBookLoginTest 
{	private WebDriver driver;
  	private Map<String, Object> vars;
  	JavascriptExecutor js;
  	
  	//pre - test
  	@Before
  	public void setUp()
  	{	//instancia do driver de acesso ao Firefox
  		driver = new ChromeDriver();
  		//execução pelo driver do JS
  		js = (JavascriptExecutor) driver;
  		//mapa de variáveis de entrada
  		vars = new HashMap<String, Object>();
  	}
  	//what'll be done after test (FINAL)
	@After
	public void tearDown() 
	{driver.navigate();}//mantenha a navegação.
	  
	@Test//test's scenary
	public void faceBookLoginTest() 
	{	//Acesso a pagina do facebook, local onde será feito o teste
		driver.get("https://pt-br.facebook.com/");
		
		//define as dimensões da janela
	    driver.manage().window().fullscreen();
	    
	    //define o usuário da tag <input type="text" id="email"/>
	    //se o campo não estiver vazio
	    if(driver.findElement(By.name("email")).getText().equals(""))
	    {driver.findElement(By.id("email")).sendKeys("duduedu_bom@hotmail.com");}
	    //define o usuário da tag <input type="password" id="pass"/>
	    driver.findElement(By.id("pass")).sendKeys("@(()$%*");
	    driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);
	}
}