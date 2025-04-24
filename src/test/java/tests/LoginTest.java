package tests;

import base.BaseTest;
import com.beust.jcommander.Parameter;
import org.openqa.selenium.internal.Debug;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
        ExcelUtils.loadExcel(filePath, "Sheet1");
        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount - 1][2];
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = ExcelUtils.getCellData(i, 0); //Username
            data[i - 1][1] = ExcelUtils.getCellData(i, 1); //Password
        }
        ExcelUtils.closeExcel();
        return data;
    }

    @DataProvider(name = "LoginData2")
    public Object[][] getData() {
        return new Object[][]{
                {"user1", "pass1"},
                {"user2", "pass2"},
                {"user3", "pass3"}
        };
    }

    @Test(dataProvider = "LoginData1")
    //   @Test
    //   @Parameter({"username", "password"})
    public void testValidLogin(String username, String password) {
        Log.info("Starting the login test..");
        test = ExtentReportManager.createTest("Login Test" + username);

        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding credentials");
        test.info("Adding Credentials");
        //    loginPage.enterUsername("admin@yourstore.com");
        //    loginPage.enterPassowrd("admin");
        loginPage.enterUsername(username);
        loginPage.enterPassowrd(password);
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
        loginPage.enterUsername("admin1234@yourstore.com");
        loginPage.enterPassowrd("admin123");
        loginPage.clickLogin();

        System.out.println("Title of the page is:" + driver.getTitle());
        Log.info("Verifying page title");
        test.info("Verifying page title");
        Assert.assertEquals(driver.getTitle(), "Just a moment...");
        test.pass("Login Successful");
    }
}
