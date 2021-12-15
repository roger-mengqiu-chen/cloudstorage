package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "successBanner")
    private WebElement successBanner;

    @FindBy(id = "failBanner")
    private WebElement failBanner;

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "btn-submit")
    private WebElement submitBtn;

    @FindBy(id = "aBackToLogin")
    private WebElement aBackToLogin;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signup(String firstName, String lastName, String userName, String password){
        this.inputFirstName.sendKeys(firstName);
        this.inputLastName.sendKeys(lastName);
        this.inputUserName.sendKeys(userName);
        this.inputPassword.sendKeys(password);
        this.submitBtn.click();
    }

    public boolean isSuccess() {
        return successBanner != null;
    }

    public boolean isFail() {
        return failBanner != null;
    }

    public void backToLogin() {
        this.aBackToLogin.click();
    }
}
