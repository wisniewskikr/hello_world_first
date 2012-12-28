package pl.kwi.intg;

import java.io.File;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Arquillian.class)
public class HelloWorldTest {
	
	
	private final static String PATH_HOST = "http://localhost:8181/";
	private final static String PATH_CONTEXT = "HwFstArquillianSeleniumServlets";
	private final static String WAR_FILE = PATH_CONTEXT + ".war";

	private WebDriver driver;
	private Wait wait;
	private String text;
	String title;
	
	
	@Deployment
    public static Archive<?> createDeployment() {
		
		MavenDependencyResolver resolver = DependencyResolvers.use(
		MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
		
        WebArchive war = ShrinkWrap.create(WebArchive.class, WAR_FILE)
        .addPackages(true, "pl.kwi")
        .addAsLibraries(
            resolver
            .artifact("jstl:jstl")
            .artifact("taglibs:standard")
        .resolveAsFiles());
        
        addFilesToWar(war, new File("src/main/webapp"));
        
        return war;
        
    }
	
	
	@Before
	public void setUp(){
		
		driver = new HtmlUnitDriver();
		wait = new WebDriverWait(driver, 10);
		
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
	
	
	// ****************************************************** //
	
	
	private static void addFilesToWar(WebArchive war, File dir) {
		
		try {
			
			if (!dir.isDirectory()) {
	            throw new Exception("not a directory");
	        }
	        for (File f : dir.listFiles()) {
	            if (f.isFile()) {
	                war.addAsWebResource(f, f.getPath().replace("\\", "/").substring("src/main/webapp/".length()));
	            } else {
	                addFilesToWar(war, f);
	            }
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
        
    }
	

}
