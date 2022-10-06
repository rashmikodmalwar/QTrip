package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 {
    static RemoteWebDriver driver;
    public static String lastGeneratedName;
    List<WebElement> reservation;

    @BeforeTest
    public static void createDriver(){
        driver=DriverSingleton.getInstance();
        
    }
    
    @Test(dataProvider ="logindata",dataProviderClass=DP.class,description="Verify that adventure booking and cancellation works fine",priority=3,groups={"Booking and Cancellation Flow"})
    public void TestCase03(String newUser,String password,String searchCity,String adventureName,String guestName,String date,String noOfPerson) throws InterruptedException
    {
    boolean status;
    HomePage home =new HomePage(driver);
    home.goToHomePage();
    Thread.sleep(3000);
    home.clickRegister();
    Thread.sleep(2000);
    RegisterPage register = new RegisterPage(driver);
    register.navigateToRegisterPage();
    register.registerUser(newUser,password,password,true);
    lastGeneratedName= register.lastGeneratedUserName_;
    Thread.sleep(2000);
    LoginPage login = new LoginPage(driver);
    login.performLogin(lastGeneratedName, password);
    status=home.isUserLoggedIn();
    Assert.assertTrue(status,"user is not login");
    home.searchCity(searchCity);
    home.assertAutoCompleteText(searchCity);
    home.selectCity(searchCity);
    AdventurePage adventure =new AdventurePage(driver);
    Thread.sleep(4000);
    // adventure.navigateToAdventurePage();
    // adventure.setFilterValueDuration("2-6");
    // adventure.setFilterValueCategory("Cycling");
    adventure.selectAdventure(adventureName);
    AdventureDetailsPage adventureDetail = new AdventureDetailsPage(driver);
    Thread.sleep(3000);
    adventureDetail.bookAdventure(guestName,date,Integer.parseInt(noOfPerson));
    Thread.sleep(2000);
    adventureDetail.isBookingSuccessful();
    HistoryPage historyPage = new HistoryPage(driver);
    // historyPage.navegateToRservation();
    historyPage.navigateHistoryPage();
    Thread.sleep(3000);
    reservation=historyPage.getReservations();
    String transId = reservation.get(reservation.size()-1).getText();
    Thread.sleep(4000);
    historyPage.cancelReservation(transId);
    Thread.sleep(2000);
    home.logoutUser();
    //historyPage.isReserVationCancel(transId);
    }

    @AfterTest
    public static void quiteDriver(){
     driver.quit();
    }

}
