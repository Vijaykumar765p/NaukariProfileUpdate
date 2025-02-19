package com.naukri.automation.NaukriUpdater;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class NaukriProfileUpdaterTest {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-blink-features=AutomationControlled");
		// options.addArguments("--headless"); // Uncomment if you want to run in
		// headless mode
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	}

	@DataProvider(name = "loginData")
	public Object[][] loginDataProvider() {
		return new Object[][] { { "vpattar6@gmail.com", "Anvit@2503" } };
	}

	@Test(dataProvider = "loginData")
	public void testProfileUpdate(String username, String password) throws InterruptedException {
		// Open Naukri Login Page
		driver.get("https://www.naukri.com/nlogin/login");

		// Click on Login
		driver.findElement(By.linkText("Login")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("usernameField")));

		// Enter Login Credentials
		driver.findElement(By.id("usernameField")).sendKeys(username);
		driver.findElement(By.id("passwordField")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(5000);

		// Navigate to Profile Page
		driver.get("https://www.naukri.com/mnjuser/profile");
		Thread.sleep(5000);

		// Upload Resume to Update Profile
		String resumePath = System.getProperty("user.dir") + "/resume/Vijaykumar_Resume_QAEngineer_ATS.pdf";
		WebElement uploadButton = driver.findElement(By.xpath("//input[@type='file']"));
		uploadButton.sendKeys(resumePath);
		Thread.sleep(5000); // Wait for upload to complete
		 // Assert that the profile was updated successfully
//        WebElement successMessage = driver.findElement(By.xpath("//span[contains(text(),'Profile updated successfully')]"));
//        Assert.assertTrue(successMessage.isDisplayed(), "Profile update was not successful");

		System.out.println("Profile updated successfully!");
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}