package dev.crane.steps;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.crane.pages.ProjectOneMain;
import dev.crane.runners.ProjectOneRunner;
import junit.framework.Assert;

public class ProjectOneStepImpl {
	
	public static ProjectOneMain promain = ProjectOneRunner.promain;
	public static WebDriver driver = ProjectOneRunner.driver;
	public static String url = "http://localhost:8080/projectOne/loginScreen.html";
	
	@Given("^the guest is on the login page$")
	public void the_guest_is_on_the_login_page()  {
			driver.get(url);
	}

	@When("^the guest puts in credentials \"([^\"]*)\" and a \"([^\"]*)\" then hits enter$")
	public void the_guest_puts_in_credentials_and_a_then_hits_enter(String username, String userpass)  {
		promain.usernameIn.sendKeys(username);
		promain.userpassIn.sendKeys(userpass);
		promain.enter.click();

	}

	@Then("^the guest should be on \"([^\"]*)\"$")
	public void the_guest_should_be_on(String title) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(title, driver.getTitle());

	}

}
