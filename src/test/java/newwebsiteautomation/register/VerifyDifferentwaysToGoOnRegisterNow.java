package newwebsiteautomation.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyDifferentwaysToGoOnRegisterNow {
@Test
	public void main() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));//maximum till 15 sec preferable for large content
		
		driver.get("https://tutorialsninja.com/demo");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Register Account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//legend[normalize-space()='Your Personal Details']")).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")).click();
		
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Register Account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//legend[normalize-space()='Your Personal Details']")).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Register Account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//legend[normalize-space()='Your Personal Details']")).isDisplayed());
		
		driver.quit();

	}

}
