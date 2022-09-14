package qtriptest.tests;

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

  
   @Test(description = "verify user registration - login -logout")
    public void testcase01() throws InterruptedException{
        boolean status;
        HomePage home =new HomePage(driver);
       home.goToHomePage();
       home.clickRegister();
       Thread.sleep(2000);
       System.out.println("hello-----");
       RegisterPage register = new RegisterPage(driver);
       register.navigateToRegisterPage();
       register.registerUser("abc@gmail.com", "abc@123","abc@123",true);
       System.out.println("hello-----");
       lastGeneratedName= register.lastGeneratedUserName_;
       Thread.sleep(2000);
       LoginPage login = new LoginPage(driver);
       login.performLogin(lastGeneratedName, "abc@123");
       status=home.isUserLoggedIn();
       Assert.assertTrue(status,"user is not login");
       home.logoutUser();

    }

   @AfterTest
    public static void quiteDriver(){
     driver.quit();
    }

}
