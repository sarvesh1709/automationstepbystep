package tests;

import base.BaseTest;
import org.openqa.selenium.internal.Debug;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    @Test
   public void testValidLogin(){
       LoginPage loginPage=new LoginPage(driver);
       loginPage.enterPassowrd("admin@yourstore.com");
       loginPage.enterPassowrd("admin");
       loginPage.clickLogin();
       System.out.println("Title of the page is:"+driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Just a moment...");
   }
}
