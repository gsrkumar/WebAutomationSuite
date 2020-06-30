package web.services.symetra.utils.Listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import web.services.symetra.testbase.TestBase;
import web.services.symetra.utils.ExtentReports.ExtentManager;
import web.services.symetra.utils.ExtentReports.ExtentTestManager;
import web.services.symetra.utils.UtilFunctions.UtilClass;

public class TestListener extends TestBase implements ITestListener{
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
		//context.setAttribute("WebDriver", this.driver);
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "*****"));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		 String image = null;
		 Object testClass = result.getInstance();
		 WebDriver webDriver = ((TestBase) testClass).getDriver();
		ExtentTestManager.getTest().log(Status.PASS, result.getMethod().getMethodName()+" : Test passed");
		try {
			image = UtilClass.getBase64Screenshot(webDriver, result.getInstanceName().trim(),result.getMethod().getMethodName().trim(), "pass");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentTestManager.getTest().addScreenCaptureFromBase64String(image);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		String image = null;
		 Object testClass = result.getInstance();
		 WebDriver webDriver = ((TestBase) testClass).getDriver();
		//ITestContext context = result.getTestContext();
		//WebDriver driver = (WebDriver) context.getAttribute("driver");
		
		ExtentTestManager.getTest().log(Status.FAIL, result.getMethod().getMethodName()+" : Test Failed");
		try {
			image = UtilClass.getBase64Screenshot(webDriver, result.getInstanceName().trim(),result.getMethod().getMethodName().trim(), "fail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentTestManager.getTest().addScreenCaptureFromBase64String(image);
			
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}




}
