package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTest extends CloudStorageApplicationTests{

    @Test
    public void createNoteTest() throws InterruptedException {
        signupTest();
        FilePage filePage = login();
        filePage.goToNoteTab();
        NotePage notePage = new NotePage(driver);
        // add a new note
        Thread.sleep(2000);
        notePage.newNote("test", "test description");
        ResultPage resultPage = new ResultPage(driver);
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.isSuccess());
        Thread.sleep(1000);
        resultPage.clickOK();
        filePage.goToNoteTab();
        Thread.sleep(1000);
        String title = notePage.getNoteTitles().get(0).getText();
        String desc = notePage.getNoteDescs().get(0).getText();
        Assertions.assertEquals("test", title);
        Assertions.assertEquals("test description", desc);
        notePage.deleteNote(0);
        resultPage.clickOK();
    }

    @Test
    public void editNoteTest() throws InterruptedException {
        FilePage filePage = login();
        ResultPage resultPage = new ResultPage(driver);
        filePage.goToNoteTab();
        NotePage notePage = new NotePage(driver);
        Thread.sleep(1000);
        notePage.newNote("test2", "test description2");
        Thread.sleep(1000);
        resultPage.clickOK();
        filePage.goToNoteTab();
        Thread.sleep(1000);
        notePage.modifyNote(0, " new title", " new content");
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.isSuccess());
        resultPage.clickOK();
        filePage.goToNoteTab();
        Thread.sleep(1000);
        String title = notePage.getNoteTitles().get(0).getText();
        String desc = notePage.getNoteDescs().get(0).getText();
        Assertions.assertEquals("test2 new title", title);
        Assertions.assertEquals("test description2 new content", desc);
    }

    @Test
    public void deleteNoteTest() throws InterruptedException {

        FilePage filePage = login();
        ResultPage resultPage = new ResultPage(driver);
        filePage.goToNoteTab();
        NotePage notePage = new NotePage(driver);
        Thread.sleep(1000);
        notePage.newNote("test", "desc");
        resultPage.clickOK();
        filePage.goToNoteTab();
        Thread.sleep(1000);
        notePage.deleteNote(0);
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.isSuccess());
        resultPage.clickOK();
        filePage.goToNoteTab();
        Thread.sleep(1000);
        List<WebElement> titles = notePage.getNoteTitles();
        List<WebElement> descs = notePage.getNoteDescs();
        Assertions.assertEquals(0, titles.size());
        Assertions.assertEquals(0, descs.size());
    }
}
