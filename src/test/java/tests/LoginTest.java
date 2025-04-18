package tests;

import base.BaseTest;
import org.openqa.selenium.internal.Debug;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {
    @Test
    public void testValidLogin() {
        Log.info("Starting the login test..");
        test = ExtentReportManager.createTest("Login Test");

        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding credentials");
        test.info("Adding Credentials");
        loginPage.enterPassowrd("admin@yourstore.com");
        loginPage.enterPassowrd("admin");
        loginPage.clickLogin();

        System.out.println("Title of the page is:" + driver.getTitle());
        Log.info("Verifying page title");
        test.info("Verifying page title");
        Assert.assertEquals(driver.getTitle(), "Just a moment...");
        test.pass("Login Successful");
    }
    @Test
    public void testLoginWithInvalidCredentials() {
        Log.info("Starting the login test..");
        test = ExtentReportManager.createTest("Login Test with Invalid Credentials");

        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding credentials");
        test.info("Adding Credentials");
        loginPage.enterPassowrd("admin1234@yourstore.com");
        loginPage.enterPassowrd("admin123");
        loginPage.clickLogin();

        System.out.println("Title of the page is:" + driver.getTitle());
        Log.info("Verifying page title");
        test.info("Verifying page title");
        Assert.assertEquals(driver.getTitle(), "Just a moment...");
        test.pass("Login Successful");
    }
}
