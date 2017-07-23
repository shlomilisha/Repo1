package mavenProjectTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class browserTest {
	
	WebDriver driver;
	
	@Before
	public void beforeTest(){
		System.out.println("Prepering for test runing....");
		System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void browserTest(){
		System.out.println("Opening web site Yad2 ...");
		driver.get("http://www.yad2.co.il/");
		System.out.println("Open section business for sell...");
		driver.findElement(By.xpath(".//*[@id='menu']/ul/li[8]/a")).click();
	}
	
	//@After
	public void afterTest(){
		System.out.println("Close web browser");
		driver.quit();
	}
	

}
