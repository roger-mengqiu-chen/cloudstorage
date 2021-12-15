package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    private final JavascriptExecutor js;

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

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void setFirstName (String firstName) {
        js.executeScript("arguments[0].value='" + firstName + "';", firstName);
    }

    public void setLastName (String lastName) {
        js.executeScript("arguments[0].value='" + lastName + "';", lastName );
    }

    public void setUserName (String userName) {
        js.executeScript("arguments[0].value='" + userName + "';", userName );
    }

    public void setPassword (String password) {
        js.executeScript("arguments[0].value='" + password + "';", password );
    }

    public void submit() {
        js.executeScript("arguments[0].click();", submitBtn);
    }

}
