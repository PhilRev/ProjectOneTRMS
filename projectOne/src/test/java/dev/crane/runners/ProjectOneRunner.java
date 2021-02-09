package dev.crane.runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dev.crane.pages.ProjectOneMain;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "dev.crane.steps")
public class ProjectOneRunner {
	
	public static WebDriver driver;
	public static ProjectOneMain promain;
	
	@BeforeClass
	public static void setUp() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		promain = new ProjectOneMain(driver);
		
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
