package web.services.symetra.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;




public class HomePage extends BasePage {

	
	private HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	public static HomePage initHomePage(WebDriver driver) {
		return new HomePage(driver);
	}

}
