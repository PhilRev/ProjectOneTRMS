package dev.crane.test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	
	public static void main(String[] args) {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		WebDriver driver = new ChromeDriver();
		
		
		String url = "http://localhost:8080/projectOne/loginScreen.html";
		driver.get(url);
		
		WebElement user = driver.findElement(By.id("username"));
		WebElement pass = driver.findElement(By.id("userpass"));
		WebElement sub = driver.findElement(By.id("submitInput"));
		
		user.sendKeys("BigBoss");
		pass.sendKeys("ssssss");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		sub.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement enroll = driver.findElement(By.id("reqEnroll"));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		enroll.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		//WebElement signUp = driver.findElement(By.id("signUp"));
		

		
		
		
//		WebElement newHire = driver.findElement(By.id("newHire"));
//		
//		newHire.click();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		WebElement newJob = driver.findElement(By.id("nJob"));
//		newJob.click();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
		
		
		
		
		driver.quit();
	}
}
