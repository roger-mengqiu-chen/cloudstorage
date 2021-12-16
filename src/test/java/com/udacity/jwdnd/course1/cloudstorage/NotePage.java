package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NotePage {

    @FindBy(id = "nav-files-tab")
    private WebElement fileTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "logout-btn")
    private WebElement logout;

    @FindBy(id = "btn-newNote")
    private WebElement newNoteBtn;

    @FindBy(className = "note-title-class")
    private List<WebElement> noteTitles;

    @FindBy(className = "note-desc-class")
    private List<WebElement> noteDescs;

    @FindBy(className = "note-edit-btn")
    private List<WebElement> noteEditBtns;

    @FindBy(className = "note-delete-btn")
    private List<WebElement> noteDeleteBtns;

    @FindBy(id = "note-title")
    private WebElement inputNoteTitle;

    @FindBy(id = "note-description")
    private WebElement inputNoteDesc;

    @FindBy(id = "note-save-btn")
    private WebElement save;

    public NotePage (WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void newNote(String title, String desc) throws InterruptedException {
        this.newNoteBtn.click();
        Thread.sleep(1000);
        this.inputNoteTitle.sendKeys(title);
        this.inputNoteDesc.sendKeys(desc);
        this.save.click();
    }

    public void modifyNote(int noteIdx, String title, String desc) throws InterruptedException {
        this.noteEditBtns.get(noteIdx).click();
        Thread.sleep(1000);
        this.inputNoteTitle.sendKeys(title);
        this.inputNoteDesc.sendKeys(desc);
        this.save.click();
    }

    public void deleteNote(int noteIdx) {
        this.noteDeleteBtns.get(noteIdx).click();
    }

    public List<WebElement> getNoteTitles() {
        return this.noteTitles;
    }

    public List<WebElement> getNoteDescs() {
        return this.noteDescs;
    }

    public void goToFileTab() {
        this.fileTab.click();
    }

    public void goToCredentialTab() {
        this.credentialTab.click();
    }

    public void logout() {
        this.logout.click();
    }
}
