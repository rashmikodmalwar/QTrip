package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class testCase_01 {
    
    static RemoteWebDriver driver;
    public static String lastGeneratedName;

   @BeforeTest
    public static void createDriver(){
        driver=DriverSingleton.getInstance();
    }
   

   @Test(dataProvider="logindata", dataProviderClass = DP.class,description = "verify user registration - login -logout",priority=1,groups={"Login Flow"})
    public void TestCase01(String userName,String password ) throws InterruptedException{
       boolean status;
       HomePage home =new HomePage(driver);
       home.goToHomePage();
       home.clickRegister();
       Thread.sleep(2000);
       RegisterPage register = new RegisterPage(driver);
       register.navigateToRegisterPage();
       register.registerUser(userName,password,password,true);
       lastGeneratedName= register.lastGeneratedUserName_;
       Thread.sleep(2000);
       LoginPage login = new LoginPage(driver);
       login.performLogin(lastGeneratedName,password);
       status=home.isUserLoggedIn();
       Assert.assertTrue(status,"user is not login");
       home.logoutUser();

    }

   @AfterTest
    public static void quiteDriver(){
     driver.quit();
    }

}
