package newwebsiteautomation.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterAccount {
@Test
	public void RegisterPage() {
		
		 WebDriver driver=new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		 driver.manage().window().maximize();
		 driver.get("https://tutorialsninja.com/demo/");
		 
		 driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 driver.findElement(By.xpath("//a[text()='Register']")).click();
		 driver.findElement(By.id("input-firstname")).sendKeys("Ramanan");
		 driver.findElement(By.id("input-lastname")).sendKeys("Ahuja");
		 driver.findElement(By.id("input-email")).sendKeys(getEmailByTimeStamp());
		 driver.findElement(By.id("input-telephone")).sendKeys("2546687089");
		 driver.findElement(By.id("input-password")).sendKeys("aman123");
		 driver.findElement(By.id("input-confirm")).sendKeys("aman123");
		 driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		 driver.findElement(By.xpath("//input[@type='submit']")).click();
		 String success="Your Account Has Been Created!";
		 String firstVerificationLine="Congratulations! Your new account has been successfully created!";
		 String SecondVerificationLine="You can now take advantage of member privileges to enhance your online shopping experience with us.";
		 String ThirdVerificationLine="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";

		 //To Verify all Expected Result
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(), success);
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//p")).getText(), firstVerificationLine);
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//p[2]")).getText(), SecondVerificationLine);
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//p[3]")).getText(), ThirdVerificationLine);

		 driver.findElement(By.linkText("Continue")).click();
		 String firstAnchorMyAccount="Edit Account";
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='account-account']//a[2]")).getText(), firstAnchorMyAccount);

		 driver.quit();
	}
	
	public String getEmailByTimeStamp() {
		/*
		 * Date date=new Date();
		 * String datewithoutspace=new Date().toString().replaceAll("\\s","");
		 * String datewithoutspaceAndColon=new Date().toString().replaceAll("\\s","").replaceAll(":","")+"@gmail.com";
		*/
		//This is called method chaining..
		return new Date().toString().replaceAll("\\s","").replaceAll(":","")+"@gmail.com";
	}

}
