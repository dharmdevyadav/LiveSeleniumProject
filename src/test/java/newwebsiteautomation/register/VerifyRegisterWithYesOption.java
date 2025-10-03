package newwebsiteautomation.register;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class VerifyRegisterWithYesOption {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://tutorialsninja.com/demo");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Rammana");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Raskal");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(GetNewMailEverytime());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(GetNewNumber());
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("asfdsf@");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("asfdsf@");
		driver.findElement(By.xpath("//label[normalize-space()='Yes']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Subscribe / unsubscribe to newsletter']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Newsletter']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).isSelected());
		
		Thread.sleep(2000);
		driver.quit();
		}
	
	public static String GetNewMailEverytime() {
		return new Date().toString().replaceAll(" ","").replaceAll("\\:","")+"@gamil.com";
	}
	
	public static String GetNewNumber() {
		String prefix="23";
		Random ran=new Random();
		StringBuilder str=new StringBuilder();
		for(int i=0;i<8;i++) {
			int digit=ran.nextInt(10);
			str.append(digit);
			
		}
		return prefix+str.toString();
	}

}
