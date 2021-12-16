package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CredentialPage {

    @FindBy(id = "nav-files-tab")
    private WebElement fileTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "logout-btn")
    private WebElement logout;

    @FindBy(id = "new-credential-btn")
    private WebElement newCredentialBtn;

    @FindBy(className = "credential-delete-btn")
    private List<WebElement> deleteCredentialBtns;

    @FindBy(className = "credential-edit-btn")
    private List<WebElement> editCredentialBtns;

    @FindBy(className = "credential-url")
    private List<WebElement> credentialUrls;

    @FindBy(className = "credential-username")
    private List<WebElement> credentialUsername;

    @FindBy(className = "credential-password")
    private List<WebElement> credentialPassword;

    @FindBy(id = "credential-url")
    private WebElement inputCredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement inputCredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement inputCredentialPassword;

    @FindBy(id = "credentialSubmit-btn")
    private WebElement credentialSubmitBtn;

    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createCredential(String url, String username, String password) throws InterruptedException {
        this.newCredentialBtn.click();
        Thread.sleep(1000);
        this.inputCredentialUrl.sendKeys(url);
        this.inputCredentialUsername.sendKeys(username);
        this.inputCredentialPassword.sendKeys(password);
        Thread.sleep(2000);
        this.credentialSubmitBtn.click();
    }

    public void editCredential(int credentialIdx, String url, String username, String password) throws InterruptedException {
        this.editCredentialBtns.get(credentialIdx).click();
        Thread.sleep(1000);
        this.inputCredentialUrl.sendKeys(url);
        this.inputCredentialUsername.sendKeys(username);
        this.inputCredentialPassword.sendKeys(password);
        this.credentialSubmitBtn.click();
    }

    public void deleteCredential(int credentialIdx) {
        this.deleteCredentialBtns.get(credentialIdx).click();
    }

    public List<WebElement> getCredentialUrls() {
        return this.credentialUrls;
    }

    public List<WebElement> getCredentialUsername() {
        return this.credentialUsername;
    }

    public List<WebElement> getCredentialPassword() {
        return this.credentialPassword;
    }

    public void goToFileTab() {
        this.fileTab.click();
    }

    public void goToNoteTab() {
        this.noteTab.click();
    }

    public void logout() {
        this.logout.click();
    }
}
