package br.edu.test.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory 
{	//method that will return a Webdriver object
	public static WebDriver open(String browser)
	{	
		if(browser.equalsIgnoreCase("firefox"))
		{	System.setProperty("webdriver.gecko.driver", 
				"C:\\webdrivers\\geckodriver.exe");
			return new FirefoxDriver();
		}
		
		else if(browser.equalsIgnoreCase("IE"))
		{	System.setProperty("webdriver.ie.driver", 
				"C:\\webdrivers\\iedriver.exe");
			return new InternetExplorerDriver();
		}
	
		else
		{	System.setProperty("webdriver.chrome.driver", 
				"C:\\webdrivers\\chromedriver.exe");
			return new ChromeDriver();
		}
	}
}