package com.qa.rpgwc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Selenium {

	private static ChromeDriver driver;
	
	@BeforeAll
	public static void setup() {
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void home() throws InterruptedException {
		driver.get("http://localhost:8080/home.html");
		Assertions.assertEquals(driver.getTitle(),"Home");
	}
	
	@Test
	public void create() throws InterruptedException {
		driver.get("http://localhost:8080/create.html");
		Assertions.assertEquals(driver.getTitle(),"Upload Weapon");
	}
	
	@Test
	public void read() throws InterruptedException{
		driver.get("http://localhost:8080/read.html");
		Assertions.assertEquals(driver.getTitle(), "View Weapons");
	}
	
	@Test
	public void update() throws InterruptedException{
		driver.get("http://localhost:8080/update.html");
		Assertions.assertEquals(driver.getTitle(), "Update your existing weapons");
	}
	
	@Test
	public void delete() throws InterruptedException {
		driver.get("http://localhost:8080/delete.html");
		Assertions.assertEquals(driver.getTitle(), "Delete a creation");
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
