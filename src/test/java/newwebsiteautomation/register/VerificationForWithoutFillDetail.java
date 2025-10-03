package newwebsiteautomation.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerificationForWithoutFillDetail {
@Test
	public void main() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		//driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
		
		String expectedRow1="First Name must be between 1 and 32 characters!";
		String expectedRow2="Last Name must be between 1 and 32 characters!";
		String expectedRow3="E-Mail Address does not appear to be valid!";
		String expectedRow4="Telephone must be between 3 and 32 characters!";
		String expectedRow5="Password must be between 4 and 20 characters!";

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), expectedRow1);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), expectedRow2);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), expectedRow3);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedRow4);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), expectedRow5);
		driver.quit();
	}

}
