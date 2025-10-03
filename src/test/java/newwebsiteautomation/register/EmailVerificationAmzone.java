package newwebsiteautomation.register;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EmailVerificationAmzone {

	public String finalOTP;
	WebDriver driver;
	@Test
	public void mainEmail() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();

		driver.get("https://www.amazon.in/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
		
		
		  driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		  driver.findElement(By.xpath("//input[@type='submit']")).click();
		  driver.findElement(By.xpath("//input[@id='ap_email_login']")).sendKeys("dharmdevyadav76@gmail.com");
		  driver.findElement(By.xpath("//input[@type='submit']")).click();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		  driver.findElement(By.xpath("//a[@id='auth-fpp-link-bottom']")).click();
		  driver.findElement(By.xpath("//input[@type='email']")).clear();
		  driver.findElement(By.xpath("//input[@type='email']")).sendKeys("dharmdevyadav76@gmail.com");
		  driver.findElement(By.xpath("//input[@id='continue']")).click();
		  
		  String email="dharmdevyadav76@gmail.com"; 
		  String AppPasscode="fqqh rtwd tyjs tscb";
		  
		// Gmail IMAP settings
	        Properties props = new Properties();
	        props.put("mail.store.protocol", "imaps");
	        props.put("mail.imaps.host", "imap.gmail.com");
	        props.put("mail.imaps.port", "993");
	        props.put("mail.imaps.ssl.enable", "true");

	        try {
	            Session session = Session.getDefaultInstance(props);
	            Store store = session.getStore("imaps");

	            System.out.println("Connecting to Gmail...");
	            store.connect("imap.gmail.com", email, AppPasscode);
	            System.out.println("Connected!");
    			Thread.sleep(3000);
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);

	            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN),false));
	            boolean found=false;
	            for(int i=messages.length-1;i>=0;i--) {
	            	found=true;
	            	Message message= messages[i];
	            	
	            	System.out.println("Subject: " + message.getSubject());
	            	System.out.println("Gmail From: " + message.getFrom()[0].toString());
		            String body = getTextFromMessage(message);
		            System.out.println("\nEmail Body:\n" + body);
		            
		            if(message.getSubject().contains("amazon.in: Password recovery")) {
		            	extractAndPrintLinks(body);
		            	Thread.sleep(3000);
		            	driver.findElement(By.xpath("//input[@id='input-box-otp']")).sendKeys(finalOTP);
		            	Thread.sleep(1000);
			            driver.findElement(By.xpath("//input[@aria-labelledby='cvf-submit-otp-button-announce']")).click();
			            Thread.sleep(2000);
			            driver.findElement(By.xpath("//input[@id='ap_fpp_password']")).sendKeys("Dharma123@");
			            driver.findElement(By.xpath("//input[@id='ap_fpp_password_check']")).sendKeys("Dharma123@");
			            driver.findElement(By.xpath("//input[@id='continue']")).click();
		            }
		            break;

	            }
				if(!found) {
					System.out.println("no Confirmation Email found!!");
				}
				
	            inbox.close(false);
	            store.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

		/*
		 * @AfterTest public void QuitApp() { driver.close(); driver.quit(); }
		 */
	    // Helper to extract text from email (handles plain + HTML)
	    private String getTextFromMessage(Message message) throws Exception {
	        Object content = message.getContent();
	        if (content instanceof String) {
	            return (String) content;
	        } else if (content instanceof MimeMultipart) {
	            MimeMultipart multipart = (MimeMultipart) content;
	            return getTextFromMimeMultipart(multipart);
	        }
	        return "";
	    }

	    private String getTextFromMimeMultipart(MimeMultipart multipart) throws Exception {
	        for (int i = 0; i < multipart.getCount(); i++) {
	            BodyPart part = multipart.getBodyPart(i);
	            if (part.getContentType().toLowerCase().contains("text/plain")) {
	                return (String) part.getContent();
	            }
	        }
	        // fallback: return first part
	        return (String) multipart.getBodyPart(0).getContent();
	    }
	    
	 // Extracts and prints all hyperlinks from the email body
	    private void extractAndPrintLinks(String cont) {
	        if (cont == null || cont.isEmpty()) {
	            System.out.println("No content found in email.");
	            return;
	        }

	        // Regex to find URLs
	        Pattern pattern = Pattern.compile(
	            "\\b\\d{6}\\b",
	            Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(cont);

	        while (matcher.find()) {
	            //System.out.println(matcher.group());
	        	finalOTP =matcher.group();
		        System.out.println(finalOTP);
	        }
	        
	    }
	
	/*
	 * @AfterMethod public void Anotherbrowser() {
	 * WebDriverManager.firefoxdriver().setup(); WebDriver driver1=new
	 * FirefoxDriver();
	 * 
	 * driver1.get("https://www.amazon.in/");
	 * 
	 * driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	 * driver1.manage().window().maximize(); }
	 */

}
