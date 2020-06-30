package web.services.symetra.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;




public class HomePage extends BasePage {

	
	private HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	public static HomePage initHomePage(WebDriver driver) {
		return new HomePage(driver);
	}

}
