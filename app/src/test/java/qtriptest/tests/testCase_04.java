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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_04 {
    static RemoteWebDriver driver;
    public String lastGeneratedName;
    List<WebElement> reservation;
    @BeforeTest
    public static void createDriver(){
        driver=DriverSingleton.getInstance();
    }

    @Test(dataProvider ="logindata",dataProviderClass = DP.class,description="Verify that Booking history can be viewed",priority = 4,groups={"Reliability Flow"})
    public void TestCase04(String newuser,String password,String dataset1,String dataset2,String dataset3) throws InterruptedException
    {
    boolean status;
    HomePage home =new HomePage(driver);
    String[] dataset = dataset1.split(";");
    home.goToHomePage();
    home.clickRegister();
    Thread.sleep(2000);
    RegisterPage register = new RegisterPage(driver);
    register.navigateToRegisterPage();
    register.registerUser(newuser,password,password,true);
    System.out.println("hello-----");
    lastGeneratedName= register.lastGeneratedUserName_;
    Thread.sleep(2000);
    LoginPage login = new LoginPage(driver);
    login.performLogin(lastGeneratedName, password);
    status=home.isUserLoggedIn();
    Assert.assertTrue(status,"user is not login");
    home.searchCity(dataset[0]);
    home.assertAutoCompleteText(dataset[0]);
    home.selectCity(dataset[0]);

    AdventurePage adventure =new AdventurePage(driver);
    // adventure.navigateToAdventurePage();
    // adventure.setFilterValueDuration("2-6 Hours");
    // adventure.setFilterValueCategory("Cycling Routes");
    adventure.selectAdventure(dataset[1]);
    AdventureDetailsPage adventureDetail = new AdventureDetailsPage(driver);
    Thread.sleep(3000);
    adventureDetail.bookAdventure(dataset[2],dataset[3],Integer.parseInt(dataset[4]));
    Thread.sleep(2000);
    status =adventureDetail.isBookingSuccessful(); 
    Assert.assertTrue(status,"Booking is not successfull");

    
    String[] datasetOne = dataset2.split(";");
    home.goToHomePage();
    Thread.sleep(2000);
    home.searchCity(datasetOne[0]);
    home.assertAutoCompleteText(datasetOne[0]);
    System.out.println("reached there....");
    home.selectCity(datasetOne[0]);
    // adventure.setFilterValueDuration("2-6 Hours");
    // adventure.setFilterValueCategory("Cycling Routes");
    adventure.selectAdventure(datasetOne[1]);
    Thread.sleep(3000);
    adventureDetail.bookAdventure(datasetOne[2], datasetOne[3],Integer.parseInt(datasetOne[4]));
    Thread.sleep(2000);
    adventureDetail.isBookingSuccessful(); 
    Assert.assertTrue(status,"Booking is not successfull");

     
    String[] datasetTwo = dataset3.split(";");
    home.goToHomePage();
    Thread.sleep(2000);
    home.searchCity(datasetTwo[0]);
    home.assertAutoCompleteText(datasetTwo[0]);
    home.selectCity(datasetTwo[0]);
    // adventure.setFilterValueDuration("6-12 Hours");
    // adventure.setFilterValueCategory("Serene Beaches");
    adventure.selectAdventure(datasetTwo[1]);
    Thread.sleep(3000);
    adventureDetail.bookAdventure(datasetTwo[2], datasetTwo[3],Integer.parseInt(datasetTwo[4]));
    Thread.sleep(2000);
    adventureDetail.isBookingSuccessful(); 
    Assert.assertTrue(status,"Booking is not successfull");
    HistoryPage historyPage = new HistoryPage(driver);
    historyPage.navegateToRservation();
    reservation= historyPage.getReservations();
    Assert.assertEquals(reservation.size(), 3);
    Thread.sleep(2000);
    home.logoutUser();

    }


}
