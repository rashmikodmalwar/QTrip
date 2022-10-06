package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class testCase_02 {

    static RemoteWebDriver driver;
    List<WebElement> imageSize;

    @BeforeTest
    public static void createDriver(){
        driver=DriverSingleton.getInstance();
        
    }
   
    @Test(dataProvider="logindata",dataProviderClass=DP.class, description = "Verify that search and filters working fine",priority=2,groups={"Search and Filter flow"})
    public void TestCase02(String cityName,String category_Filter,String durationFilter,String filtered_Count,String unfiltered_Count) throws InterruptedException{
    
     HomePage home =new HomePage(driver);
    home.goToHomePage();
    Thread.sleep(2000);
    home.searchCity("Mumbai");
    Thread.sleep(2000);
    home.noCityFound();
    Thread.sleep(2000);
    home.searchCity(cityName);
    Thread.sleep(2000);
    home.assertAutoCompleteText(cityName);
    Thread.sleep(2000);
    home.selectCity(cityName);
    Thread.sleep(3000);
    AdventurePage advanture=new AdventurePage(driver);
    Thread.sleep(3000);
    advanture.setFilterValueDuration(durationFilter);
    Thread.sleep(2000);
    advanture.setFilterValueCategory(category_Filter);
    Thread.sleep(2000);
    int imgSize = advanture.getResultCount();
    Assert.assertEquals(imgSize,Integer.parseInt(filtered_Count));
    // advanture.getResultCount();
    advanture.clearFilterDuration();
    Thread.sleep(2000);
    advanture.clearFilterCategory();
    Thread.sleep(2000);
    int imgSize1 = advanture.getResultCount();
    // advanture.getResultCount();
    Assert.assertEquals(imgSize1,Integer.parseInt(unfiltered_Count));
    }

    @AfterTest
    public static void quiteDriver(){
     driver.quit();
    }
}
