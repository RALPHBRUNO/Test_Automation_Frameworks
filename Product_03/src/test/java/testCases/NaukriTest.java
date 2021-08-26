package testCases;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testClasses.Naukri_Base;

public class NaukriTest {

	public static WebDriver driver;
	public static Properties prop;
	By Google_Search = By.xpath("//input[@title='Search']");
	By file = By.xpath("//*[@id=\"wdgt-file-upload\"]");

	By file_upload = By.xpath("//label[normalize-space()='Upload CV']");
	By Textbox_1 = By.xpath("//input[@id='qsb-keyword-sugg']");
	By Textbox_2 = By.xpath("//input[@id='qsb-location-sugg']");
	By Login = By.xpath("//div[normalize-space()='Login']");
	By Login_Email_txtbx = By.xpath("//input[@placeholder='Enter your active Email ID / Username']");
	By Login_Password_txtbx = By.xpath("//input[@placeholder='Enter your password']");
	By Login_log_btn = By.xpath("//button[normalize-space()='Login']");
	// By My_Naukri = By.xpath("//div[normalize-space()='My Naukri']");
	By Logout = By.xpath("//a[contains(text(),'Logout')]");

	By element = By.xpath("//div[normalize-space()='Jobs']");

	static String File_location = "C:/Users/User/eclipse-workspace/POM_TestNG/FileUploads/File_1";

	@Test(priority = 1, groups = { "Smoke" })
	@Parameters({ "username", "password", "jobtitle", "location" })

	public void NaukriTest_01(String username, String password, String jobtitle, String location)
			throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./browsers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.naukri.com/");
		driver.manage().window().maximize();
		String PageTitle = driver.getTitle();

		System.out.println("Page Title verified, the user is on " + PageTitle);
		Thread.sleep(3000);

		// CLOSING CHILD WINDOWS
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow);

		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> it = allWindows.iterator();

		String childwindow1 = it.next();

		String childwindow2 = it.next();
		driver.switchTo().window(childwindow2);
		driver.close();

		String childwindow3 = it.next();
		driver.switchTo().window(childwindow3);
		driver.close();

		String childwindow4 = it.next();
		driver.switchTo().window(childwindow4);
		driver.close();

		Thread.sleep(2000);
		driver.switchTo().window(parentWindow);

		driver.manage().window().maximize();
		Thread.sleep(4000);

		WebElement ele = driver.findElement(By.xpath("//div[normalize-space()='Jobs']"));
		WebElement ele1 = driver.findElement(By.xpath("//a[@title='Search Recruiters']"));
		WebElement ele2 = driver.findElement(By.xpath("//div[normalize-space()='Companies']"));
		WebElement ele3 = driver.findElement(By.xpath("//div[normalize-space()='Tools']"));
		WebElement ele4 = driver.findElement(By.xpath("//div[@class='mTxt'][normalize-space()='Services']"));
		WebElement ele5 = driver.findElement(By.xpath("//div[normalize-space()='More']"));
		WebElement file = driver.findElement(By.xpath("//*[@id=\"wdgt-file-upload\"]"));

		boolean txtbx1 = driver.findElement(Textbox_1).isEnabled();
		System.out.println("Textbox 1 verified " + txtbx1);

		boolean txtbx2 = driver.findElement(Textbox_2).isEnabled();
		System.out.println("Textbox 2 verified " + txtbx2);

		Actions act1 = new Actions(driver);
		act1.moveToElement(ele).perform();
		Thread.sleep(2000);

		Actions act2 = new Actions(driver);
		act1.moveToElement(ele1).perform();
		Thread.sleep(2000);

		Actions act3 = new Actions(driver);
		act1.moveToElement(ele2).perform();
		Thread.sleep(2000);

		Actions act4 = new Actions(driver);
		act1.moveToElement(ele3).perform();
		Thread.sleep(2000);

		Actions act5 = new Actions(driver);
		act1.moveToElement(ele4).perform();
		Thread.sleep(2000);

		Actions act6 = new Actions(driver);
		act1.moveToElement(ele5).perform();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='qsb-keyword-sugg']")).click();
		driver.findElement(By.xpath("//input[@id='qsb-keyword-sugg']")).sendKeys(jobtitle);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='qsb-location-sugg']")).sendKeys(location);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		Thread.sleep(2000);

		driver.findElement(Login).click();
		Thread.sleep(2000);

		driver.findElement(Login_Email_txtbx).clear();
		driver.findElement(Login_Email_txtbx).sendKeys(username);
		Thread.sleep(1000);

		driver.findElement(Login_Password_txtbx).clear();
		driver.findElement(Login_Password_txtbx).sendKeys(password);
		driver.findElement(Login_Password_txtbx).sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions mn = new Actions(driver);
		mn.moveToElement(driver.findElement(By.xpath("//a[@href='https://my.naukri.com/HomePage/view']"))).perform();

		Thread.sleep(2000);

		driver.findElement(Logout).click();
		Thread.sleep(2000);

		driver.quit();
	}

	@Test(priority = 2, groups = { "Sanity" })
	@Parameters({ "username", "password", "jobtitle1", "location1" })
	public void NaukriTest_02(String username, String password, String jobtitle1, String location1)
			throws InterruptedException {

		System.out.println("Edge running in thread " + Thread.currentThread());

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/testClasses/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browserName = prop.getProperty("browser2");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./browsers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "./browsers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browserName.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", "./browsers/msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.get("https://www.naukri.com/");
		String PageTitle = driver.getTitle();
		System.out.println("Page Title verified, the user is on " + PageTitle);
		Thread.sleep(3000);

		// CLOSING CHILD WINDOWS
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow);

		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> it = allWindows.iterator();

		String childwindow1 = it.next();

		String childwindow2 = it.next();
		driver.switchTo().window(childwindow2);
		driver.close();

		String childwindow3 = it.next();
		driver.switchTo().window(childwindow3);
		driver.close();

		String childwindow4 = it.next();
		driver.switchTo().window(childwindow4);
		driver.close();

		Thread.sleep(2000);
		driver.switchTo().window(parentWindow);

		driver.manage().window().maximize();
		Thread.sleep(4000);

		WebElement ele = driver.findElement(By.xpath("//div[normalize-space()='Jobs']"));
		WebElement ele1 = driver.findElement(By.xpath("//a[@title='Search Recruiters']"));
		WebElement ele2 = driver.findElement(By.xpath("//div[normalize-space()='Companies']"));
		WebElement ele3 = driver.findElement(By.xpath("//div[normalize-space()='Tools']"));
		WebElement ele4 = driver.findElement(By.xpath("//div[@class='mTxt'][normalize-space()='Services']"));
		WebElement ele5 = driver.findElement(By.xpath("//div[normalize-space()='More']"));
		WebElement file = driver.findElement(By.xpath("//*[@id=\"wdgt-file-upload\"]"));

		boolean txtbx1 = driver.findElement(Textbox_1).isEnabled();
		System.out.println("Textbox 1 verified " + txtbx1);

		boolean txtbx2 = driver.findElement(Textbox_2).isEnabled();
		System.out.println("Textbox 2 verified " + txtbx2);

		Actions act1 = new Actions(driver);
		act1.moveToElement(ele).perform();
		Thread.sleep(2000);

		Actions act2 = new Actions(driver);
		act1.moveToElement(ele1).perform();
		Thread.sleep(2000);

		Actions act3 = new Actions(driver);
		act1.moveToElement(ele2).perform();
		Thread.sleep(2000);

		Actions act4 = new Actions(driver);
		act1.moveToElement(ele3).perform();
		Thread.sleep(2000);

		Actions act5 = new Actions(driver);
		act1.moveToElement(ele4).perform();
		Thread.sleep(2000);

		Actions act6 = new Actions(driver);
		act1.moveToElement(ele5).perform();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='qsb-keyword-sugg']")).click();
		driver.findElement(By.xpath("//input[@id='qsb-keyword-sugg']")).sendKeys(jobtitle1);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='qsb-location-sugg']")).sendKeys(location1);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		driver.findElement(Login).click();
		Thread.sleep(2000);

		driver.findElement(Login_Email_txtbx).clear();
		driver.findElement(Login_Email_txtbx).sendKeys(username);
		Thread.sleep(1000);

		driver.findElement(Login_Password_txtbx).clear();
		driver.findElement(Login_Password_txtbx).sendKeys(password);
		driver.findElement(Login_Password_txtbx).sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions mn = new Actions(driver);
		mn.moveToElement(driver.findElement(By.xpath("//a[@href='https://my.naukri.com/HomePage/view']"))).perform();

		Thread.sleep(2000);

		driver.findElement(Logout).click();
		Thread.sleep(2000);

		driver.quit();
	}
}