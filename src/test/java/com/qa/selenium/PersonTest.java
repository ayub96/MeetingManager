package com.qa.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PersonTest {

	private static WebDriver driver;
	private static WebElement targ;
	
	@BeforeAll
	public static void init() {
		System.setProperty(
				"webdriver.chrome.driver",
				"src/test/resources/drivers/chrome/chromedriver.exe"
				);
		driver = new ChromeDriver();
	}
	
	@Test
	public void testCreateRoom() throws InterruptedException {
		driver.get("http://localhost:8081/html/Room/Create.html");
		
		targ = driver.findElement(By.id("createRoomNo"));
		targ.sendKeys("100b");
		targ = driver.findElement(By.id("createRoomType"));
		targ.sendKeys("office");
		targ = driver.findElement(By.id("createRoomButton"));
		targ.click();
		targ = driver.findElement(By.id("navbarDropdownMenuLink"));
		targ.click();
		targ = driver.findElement(By.id("readPBtn"));
		targ.click();
		targ = driver.findElement(By.id("readAllRoomsBtn"));
		targ.click();
		
		
		assertEquals(" Room Number: 100b", driver.findElement(By.xpath("//*[@id=\"peeps\"]/p/text()[2]")).getText());
		
		// end wait
		Thread.sleep(5000);
	}
	
	@AfterAll
	public static void cleanUp() {
		driver.quit();
		System.out.println("Selenium driver has been cleaned up!");
	}
	
}
