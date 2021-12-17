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
public class CredentialTest extends CloudStorageApplicationTests{

    @Test
    public void createCredential() throws InterruptedException {
        signupTest();
        FilePage filePage = login();
        filePage.goToCredentialTab();
        CredentialPage credentialPage = new CredentialPage(driver);
        Thread.sleep(2000);
        credentialPage.createCredential("test url", "test user", "password");
        ResultPage resultPage = new ResultPage(driver);
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.isSuccess());
        resultPage.clickOK();
        filePage.goToCredentialTab();
        Thread.sleep(1000);
        String url = credentialPage.getCredentialUrls().get(0).getText();
        String username = credentialPage.getCredentialUsername().get(0).getText();
        Assertions.assertEquals("test url", url);
        Assertions.assertEquals("test user", username);
        credentialPage.deleteCredential(0);
        resultPage.clickOK();
    }

    @Test
    public void editCredentialTest() throws InterruptedException {
        FilePage filePage = login();
        ResultPage resultPage = new ResultPage(driver);
        filePage.goToCredentialTab();
        CredentialPage credentialPage = new CredentialPage(driver);
        Thread.sleep(2000);
        credentialPage.createCredential("test url1", "test user1", "password1");
        resultPage.clickOK();
        filePage.goToCredentialTab();
        Thread.sleep(2000);
        credentialPage.editCredential(0, " new content", " new user", " new password");
        resultPage.clickOK();
        filePage.goToCredentialTab();
        Thread.sleep(2000);
        String url = credentialPage.getCredentialUrls().get(0).getText();
        String username = credentialPage.getCredentialUsername().get(0).getText();
        Assertions.assertEquals("test url1 new content", url);
        Assertions.assertEquals("test user1 new user", username);
    }

    @Test
    public void deleteCredentialTest() throws InterruptedException {

        FilePage filePage = login();
        ResultPage resultPage = new ResultPage(driver);
        filePage.goToCredentialTab();
        CredentialPage credentialPage = new CredentialPage(driver);
        Thread.sleep(2000);
        credentialPage.createCredential("test url", "test user", "password");
        resultPage.clickOK();
        filePage.goToCredentialTab();
        Thread.sleep(1000);
        credentialPage.deleteCredential(0);
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.isSuccess());
        resultPage.clickOK();
        filePage.goToCredentialTab();
        Thread.sleep(1000);
        List<WebElement> urls = credentialPage.getCredentialUrls();
        List<WebElement> users = credentialPage.getCredentialUsername();
        Assertions.assertEquals(0, urls.size());
        Assertions.assertEquals(0, users.size());

    }
}
