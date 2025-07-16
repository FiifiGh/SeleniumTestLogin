package com.example.test.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

    private WebDriver driver;

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By submitField = By.id("submit");
    private By errorDialog = By.id("error");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private void enterUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    private void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    private void click(){
        driver.findElement(submitField).click();
    }

    public String getErrorText(int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement errorElement = driver.findElement(errorDialog);

        String errorMessage = wait.until(ExpectedConditions.visibilityOf(errorElement)).getText();

        return errorMessage;
    }

    public void performLogin(String username, String password){
        enterUsername(username);
        enterPassword(password);
        click();
    }

}
