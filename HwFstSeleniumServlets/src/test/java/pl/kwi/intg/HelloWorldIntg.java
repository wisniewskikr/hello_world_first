package pl.kwi.intg;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HelloWorldIntg {
	
	
	private final static String PATH_HOST = "http://localhost:8080/";
	private final static String PATH_CONTEXT = "HwFstSeleniumServlets";

	private WebDriver driver;
	private Wait wait;
	private String text;
	String title;
	
	
	@Before
	public void setUp(){
		
		driver = new HtmlUnitDriver();
		wait = new WebDriverWait(driver, 5);
		
	}
	
	@Test
	public void testHelloWorld() {
		
		// INIT
		driver.get(PATH_HOST + PATH_CONTEXT);
		
		
		// ********** STEP 1 ********** //
		
		
		// Wait
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("title"), "Hello World"));
        
        // Conditions
        title = driver.getTitle();
        Assert.assertEquals("Hello World", title);
        text = driver.findElement(By.id("title")).getText();
        Assert.assertEquals("Hello World", text); 
        
        // Actions
        driver.findElement(By.id("userName")).sendKeys("Chris");
        driver.findElement(By.id("submit")).click();
        
        
        // ********** STEP 2 ********** //
        
        
        // Wait
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("title"), "Hello World"));
        
        // Conditions
        title = driver.getTitle();
        Assert.assertEquals("Hello World", title);
        text = driver.findElement(By.id("title")).getText();
        Assert.assertEquals("Hello World", text);
        text = driver.findElement(By.id("userName")).getText();
        Assert.assertEquals("Chris", text); 
        
		
	}
	

}
