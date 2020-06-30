package web.services.symetra.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	static String fileseparator = System.getProperty("file.separator");
	static String userdir = System.getProperty("user.dir");
	static String propertiydir = "Properties";
	static Properties config_properties;
	
	public WebDriver driver;
	private static String browser;
	private static String URL;
	private static String version;
	
	public TestBase() {
		setUp();
	}
	public void setUp() {
		try {
		FileInputStream config_file = new FileInputStream(userdir+fileseparator+propertiydir+fileseparator+"config.properties");
		config_properties = new Properties();
		config_properties.load(config_file);
		browser = config_properties.getProperty("browser").trim();
		URL = config_properties.getProperty("URL").trim();
		version = config_properties.getProperty("version").trim();
		}catch (FileNotFoundException e) {
			System.out.println("File not found at : "+userdir+fileseparator+propertiydir+fileseparator);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver getDriver() {	
		
		return driver;
		
	}
	
	public void lanch() {
		setDriver();
		driver.get(URL);
	}
	
	
	private void setDriver() {
		
		if(browser.equalsIgnoreCase("CHROME")) {
			//WebDriverManager.chromedriver().version(version).setup();
			WebDriverManager.chromedriver().setup();
			this.driver = new ChromeDriver();
			driver.manage().window().maximize();
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		    String browserName = cap.getBrowserName().toLowerCase();
		    System.out.println("Lanuching : "+browserName);
		    String os = cap.getPlatform().toString();
		    System.out.println("OS : "+os);
		    String v = cap.getVersion().toString();
		    System.out.println("Browser version : "+v);
			
		}else if(browser.equalsIgnoreCase("FIREFOX")) {
			WebDriverManager.firefoxdriver().version(version).setup();
			this.driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("SAFARI")) {
			
		}else if (browser.equalsIgnoreCase("IE")){
			WebDriverManager.iedriver().version(version).setup();
			this.driver = new InternetExplorerDriver();
		}else if(browser.equalsIgnoreCase("EDGE")) {
			WebDriverManager.edgedriver().version(version).setup();
		}
		else {
			System.out.println("Invalid browser name :"+browser);
		}
	}
	
	
	@AfterClass
	public void tearDown() {
	//driver.quit();
	}

}
