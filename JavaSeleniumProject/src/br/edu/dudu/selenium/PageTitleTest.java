package br.edu.dudu.selenium;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.edu.dudu.driver.DriverFactory;

public class PageTitleTest 
{	WebDriver driver;				//vari�vel de classe
	@Test public void pageTitleTest()//instancia um driver de conex�o
	{	//pega a pagina que desejamos abrir
		driver.get("https://pt-br.facebook.com/");
		
		//pegamos o titulo da p�gina <title></title>
		String title = driver.getTitle();
		
		//declaramos uma vari�vel com titulo esper�vel
		String expectedTitle = "Facebook � entre ou cadastre-se";
		
		//classe Assert, que possui m�todos de verifica��o de igualdade
		//e muito mais. Portanto o retorno � sempre booleano.
		/*Assert.assertEquals(expectedTitle, title);
		Assert.fail("We intentionally failed this test");*/
		if(title.equals(expectedTitle))
		{JOptionPane.showMessageDialog(null, "Test Ok!");}
		
		else JOptionPane.showMessageDialog(null, "Test failed");
	}
	
	@Before public void setUp()
	{	JOptionPane.showMessageDialog(null, "Begining test");
	 	driver = DriverFactory.open("firefox");
	}
	
	@After public void finishTest()
	{	JOptionPane.showMessageDialog(null, "End of test");
		driver.close();
	}
}