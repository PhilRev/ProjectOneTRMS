package dev.crane.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectOneMain {
	
	public WebDriver driver;
	
	@FindBy(id = "username")
	public WebElement usernameIn;
	
	@FindBy(id = "userpass")
	public WebElement userpassIn;
	
	@FindBy(id = "submitInput")
	public WebElement submitInput;
	
	@FindBy(id = "submitInput")
	public WebElement enter;
	
	public ProjectOneMain(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
