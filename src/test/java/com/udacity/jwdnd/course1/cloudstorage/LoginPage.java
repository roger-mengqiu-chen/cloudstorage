package com.udacity.jwdnd.course1.cloudstorage;

import org.h2.mvstore.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "btn-login")
    private WebElement login;

    @FindBy(id = "signup")
    private WebElement signup;

    @FindBy(id = "error")
    private WebElement error;

    @FindBy(id = "logoutMsg")
    private WebElement logoutMsg;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login (String username, String password) {
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.login.click();
    }

    public void goToSignup () {
        this.signup.click();
    }

    public boolean hasError() {
        return this.error != null;
    }

    public boolean isLogout() {
        return this.logoutMsg != null;
    }
}
