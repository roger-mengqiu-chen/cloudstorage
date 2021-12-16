package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FilePage {

    @FindBy(id = "nav-files-tab")
    private WebElement fileTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "logout-btn")
    private WebElement logout;

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "btn-file-submit")
    private WebElement fileSubmitBtn;

    @FindBy(className = "fileName")
    private List<WebElement> fileNames;

    @FindBy(className = "file-view-btn")
    private List<WebElement> viewBtns;

    @FindBy(className = "file-delete-btn")
    private List<WebElement> deleteBtns;


    public FilePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void uploadFile (String filePath) {
        this.fileTab.click();
        this.fileUpload.sendKeys(filePath);
        this.fileSubmitBtn.click();
    }

    public void viewFile(int fileIdx) {
        this.viewBtns.get(fileIdx).click();
    }

    public void deleteFile(int fileIdx) {
        this.deleteBtns.get(fileIdx).click();
    }

    public void goToNoteTab() {
        this.noteTab.click();
    }

    public void goToCredentialTab() {
        this.credentialTab.click();
    }

    public List<WebElement> getFileNames() {
        return this.fileNames;
    }

    public void logout() {
        this.logout.click();
    }
}
