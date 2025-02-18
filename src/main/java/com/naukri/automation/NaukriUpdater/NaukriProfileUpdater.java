package com.naukri.automation.NaukriUpdater;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.Desktop.Action;
import java.time.Duration;

public class NaukriProfileUpdater {
    public static void main(String[] args) {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // Run in incognito mode (no cookies stored)
        options.addArguments("--disable-blink-features=AutomationControlled"); // Prevent detection
        options.addArguments("--headless"); // Uncomment if you want to run in headless mode
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open Naukri Login Page
            driver.get("https://www.naukri.com/nlogin/login");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Click on Login
            driver.findElement(By.linkText("Login")).click();
            Thread.sleep(3000);

            // Enter Login Credentials
            driver.findElement(By.id("usernameField")).sendKeys("vpattar6@gmail.com");
            driver.findElement(By.id("passwordField")).sendKeys("Anvit@2503");
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

            System.out.println("Profile updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}