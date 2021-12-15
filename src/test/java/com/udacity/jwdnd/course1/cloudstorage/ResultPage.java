package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id = "aResultSuccess")
    private WebElement aResultSuccess;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isSuccess(WebDriver driver) {
        return driver.findElement(By.id("aResultSuccess")) != null;
    }

    public void clickOK() {
        aResultSuccess.click();
    }
}
