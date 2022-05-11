package qa.test;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestUrl {
	
	public WebDriver driver;
	public Properties prop;
	
	@Test
	public void get()
	{
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"G:\\New-workspace\\Krinati\\src\\main\\java\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (Exception e) {
		}
	}
	
	@Test(priority=1)
	public void lauch()
	{
	System.setProperty(prop.getProperty("key"), prop.getProperty("value"));
	driver = new ChromeDriver();
	driver.get(prop.getProperty("url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.manage().window().maximize();
	}
	
	@Test(priority=2)
	public void login()
	{
		WebElement user = driver.findElement(By.xpath("//input[@id='user-name']"));
		user.sendKeys(prop.getProperty("username"));
		
		WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
		pass.sendKeys(prop.getProperty("password"));
		
		WebElement login = driver.findElement(By.xpath("//input[@id='login-button']"));
		login.click();
	}
}
