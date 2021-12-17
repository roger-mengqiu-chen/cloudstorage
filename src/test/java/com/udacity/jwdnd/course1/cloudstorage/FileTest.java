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
public class FileTest extends CloudStorageApplicationTests{

    @Test
    public void uploadFileTest() {
        signupTest();
        FilePage filePage = login();
        // upload a file
        filePage.uploadFile("/Users/mengqiuchen/Downloads/text");
        Assertions.assertEquals("Result", driver.getTitle());
        ResultPage resultPage = new ResultPage(driver);
        Assertions.assertTrue(resultPage.isSuccess());
        resultPage.clickOK();
        String fileName = filePage.getFileNames().get(0).getText();
        Assertions.assertEquals("text", fileName);
    }

    @Test
    public void uploadLargeFileTest() {

        FilePage filePage = login();
        // upload a file
        filePage.uploadFile("/Users/mengqiuchen/Documents/eclipse-jee-2021-09-R-macosx-cocoa-x86_64.dmg");
        Assertions.assertEquals("Error", driver.getTitle());
    }

    @Test
    public void deleteFileTest() {

        FilePage filePage = login();
        ResultPage resultPage = new ResultPage(driver);
        filePage.deleteFile(0);
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.isSuccess());
        resultPage.clickOK();
        List<WebElement> fileNames = filePage.getFileNames();
        Assertions.assertEquals(0, fileNames.size());
    }
}
