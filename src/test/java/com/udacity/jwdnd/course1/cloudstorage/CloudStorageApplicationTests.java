package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.Duration;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void pageAccessTest() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void signupTest() {
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("firstName", "lastName", "test", "password");
		Assertions.assertTrue(signupPage.isSuccess());
	}

	@Test
	public void loginTest() {
		signupTest();
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);

		loginPage.login("t", "t");
		Assertions.assertTrue(loginPage.hasError());

		loginPage.login("test", "password");
		Assertions.assertEquals("Home", driver.getTitle());

	}

	@Test
	public void logoutTest() {
		loginTest();
		FilePage filePage = new FilePage(driver);
		filePage.logout();
		Assertions.assertEquals("Login", driver.getTitle());
		LoginPage loginPage = new LoginPage(driver);
		Assertions.assertTrue(loginPage.isLogout());
	}

	@Test
	public void fileTest() {
		loginTest();
		FilePage filePage = new FilePage(driver);
		// upload a file
		filePage.uploadFile("/Users/mengqiuchen/Downloads/text");
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertTrue(resultPage.isSuccess());
		resultPage.clickOK();
		String fileName = filePage.getFileNames().get(0).getText();
		Assertions.assertEquals("text", fileName);
		// delete a file
		filePage.deleteFile(0);
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertTrue(resultPage.isSuccess());
		resultPage.clickOK();
		List<WebElement> fileNames = filePage.getFileNames();
		Assertions.assertEquals(0, fileNames.size());
	}

	@Test
	public void noteTest() throws InterruptedException {
		loginTest();
		FilePage filePage = new FilePage(driver);
		filePage.goToNoteTab();
		NotePage notePage = new NotePage(driver);
		// add a new note
		Thread.sleep(2000);
		notePage.newNote("test", "test description");
		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertTrue(resultPage.isSuccess());
		resultPage.clickOK();
		filePage.goToNoteTab();
		Thread.sleep(1000);
		String title = notePage.getNoteTitles().get(0).getText();
		String desc = notePage.getNoteDescs().get(0).getText();
		Assertions.assertEquals("test", title);
		Assertions.assertEquals("test description", desc);

		// edit a note
		notePage.modifyNote(0, " new title", " new content");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertTrue(resultPage.isSuccess());
		resultPage.clickOK();
		filePage.goToNoteTab();
		Thread.sleep(1000);
		title = notePage.getNoteTitles().get(0).getText();
		desc = notePage.getNoteDescs().get(0).getText();
		Assertions.assertEquals("test new title", title);
		Assertions.assertEquals("test description new content", desc);

		// delete a note
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

	@Test
	public void credentialTest() throws InterruptedException {
		loginTest();
		FilePage filePage = new FilePage(driver);
		filePage.goToCredentialTab();
		CredentialPage credentialPage = new CredentialPage(driver);
		Thread.sleep(2000);

		// add new credential
		credentialPage.createCredential("test url", "test user", "password");
		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertTrue(resultPage.isSuccess());
		resultPage.clickOK();
		filePage.goToCredentialTab();
		Thread.sleep(1000);
		String url = credentialPage.getCredentialUrls().get(0).getText();
		String username = credentialPage.getCredentialUsername().get(0).getText();
		String password = credentialPage.getCredentialPassword().get(0).getText();
		Assertions.assertEquals("test url", url);
		Assertions.assertEquals("test user", username);
		Assertions.assertEquals("password", password);

		// edit credential
		credentialPage.editCredential(0," new content", " new user", " new password");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertTrue(resultPage.isSuccess());
		resultPage.clickOK();
		filePage.goToCredentialTab();
		Thread.sleep(1000);
		url = credentialPage.getCredentialUrls().get(0).getText();
		username = credentialPage.getCredentialUsername().get(0).getText();
		password = credentialPage.getCredentialPassword().get(0).getText();
		Assertions.assertEquals("test url new content", url);
		Assertions.assertEquals("test user new user", username);
		Assertions.assertEquals("password new password", password);

		// delete credential
		credentialPage.deleteCredential(0);
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertTrue(resultPage.isSuccess());
		resultPage.clickOK();
		filePage.goToCredentialTab();
		Thread.sleep(1000);
		List<WebElement> urls = credentialPage.getCredentialUrls();
		List<WebElement> users = credentialPage.getCredentialUsername();
		List<WebElement> passwords = credentialPage.getCredentialPassword();
		Assertions.assertEquals(0, urls.size());
		Assertions.assertEquals(0, users.size());
		Assertions.assertEquals(0, passwords.size());
	}
}
