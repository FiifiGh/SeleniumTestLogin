package com.example.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.test.pages.LoginPage;
import com.example.test.utils.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();  //create webmanager
        driver = new ChromeDriver(); //launch browser
        driver.manage().window().maximize(); //make window big
        driver.get("https://practicetestautomation.com/practice-test-login/");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


    @Test
    public void shouldLoginSuccessfullyWithValidCredentials(){
        loginPage.performLogin(Constants.VALID_USERNAME, Constants.VALID_PASSWORD);
        String urlString = driver.getCurrentUrl();
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        Assert.assertEquals(urlString, expectedUrl);
    }

    @Test
    public void shouldFailWithInvalidUsernameErrorMessage(){
        loginPage.performLogin(Constants.INVALID_USERNAME, Constants.VALID_USERNAME);
        String expectedString = Constants.USERNAME_ERROR_STRING;
        String actualString = loginPage.getErrorText(5);
        Assert.assertEquals(actualString, expectedString);
    }

    @Test
    public void shouldFailWithInvalidPasswordErrorMessage(){
        loginPage.performLogin(Constants.VALID_USERNAME, Constants.INVALID_PASSWORD);
        String expectedString = Constants.PASSWORD_ERROR_STRING;
        String actualString = loginPage.getErrorText(5);
        Assert.assertEquals(actualString, expectedString);
    }
}
