package web.services.symetra.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import web.services.symetra.testbase.TestBase;
import web.services.symetra.utils.ExtentReports.ExtentTestManager;

public class HomePageTest extends TestBase{
     WebDriver driver;

	 public HomePageTest() {
		super();
	}
	
	@BeforeClass
	
	
	@BeforeMethod
	public void openBrowser() {
	  lanch();
	  this.driver = getDriver();
	}
	
	@Test(priority = 1)
	public void testOne() {
		ExtentTest test =  ExtentTestManager.getTest();
		test.info("Test one start");
		System.out.println("Hellow"); 
		Assert.assertEquals("https://www.google.com/", driver.getCurrentUrl());
		System.out.println("Passed");
	}
	
	@Test(priority = 2)
	public void testTwo() {
		System.out.println("Bye...."); 
		Assert.assertEquals("Bye", "Bye");
	}
	
	@AfterMethod
	public void close() {
		driver.close();
	}
}
