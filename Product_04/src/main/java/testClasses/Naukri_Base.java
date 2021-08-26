package testClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Naukri_Base {

	public static WebDriver driver;
	public static Properties prop;
	By Google_Search = By.xpath("//input[@title='Search']");
	By file = By.xpath("//*[@id=\"wdgt-file-upload\"]");

	By file_upload = By.xpath("//label[normalize-space()='Upload CV']");
	By Textbox_1 = By.xpath("//input[@id='qsb-keyword-sugg']");
	By Textbox_2 = By.xpath("//input[@id='qsb-location-sugg']");
	By Login = By.xpath("//div[normalize-space()='Login']");
	By Login_Email_txtbx = By.xpath("//input[@placeholder='Enter your active Email ID / Username']");
	By Login_Password_txtbx =  By.xpath("//input[@placeholder='Enter your password']");
	By Login_log_btn = By.xpath("//button[normalize-space()='Login']");
	By My_Naukri = By.xpath("//div[normalize-space()='My Naukri']");
	By Logout = By.xpath("//a[normalize-space()='Logout']");

	@BeforeTest
	public static void BrowserStart() throws InterruptedException {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/testClasses/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String browserName = prop.getProperty("browser");

		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "./browsers/chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "./browsers/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}

		else if(browserName.equals("Edge")){
			System.setProperty("webdriver.edge.driver", "./browsers/msedgedriver.exe"); 		
			 driver = new EdgeDriver(); 
		}


	//	System.setProperty("webdriver.chrome.driver", "./browsers/chromedriver.exe"); 
	//	WebDriver driver = new ChromeDriver(); 
		
	//	driver.get("https://www.naukri.com/"); 
//		String PageTitle =  driver.getTitle();
	//	System.out.println("Page Title verified, the user is on "+PageTitle);
	//	Thread.sleep(3000);

	}

	@AfterTest
	public static void TearDown() {

		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
