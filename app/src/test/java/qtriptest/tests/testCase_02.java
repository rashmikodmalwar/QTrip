package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_02 {

    static RemoteWebDriver driver;

    @BeforeTest
    public static void createDriver(){
        driver=DriverSingleton.getInstance();
        
    }
   
    @Test(description = "Verify that search and filters working fine")
    public void testcase02() throws InterruptedException{
    HomePage home =new HomePage(driver);
    home.goToHomePage();
    home.searchCity("Pune");
    home.noCityFound();
    home.searchCity("Goa");
    home.assertAutoCompleteText("Goa");
    Thread.sleep(2000);
    home.selectCity("Goa");
    AdventurePage advanture  =new AdventurePage(driver);
    advanture.setFilterValueDuration("0-2");
    Thread.sleep(4000);
    advanture.getResultCount();
    advanture.setFilterValueCategory("Cycling");
    Thread.sleep(4000);
    advanture.getResultCount();
    advanture.clearFilterDuration();
    advanture.clearFilterCategory();
    advanture.getResultCount();
    }

    @AfterTest
    public static void quiteDriver(){
     driver.quit();
    }
}
