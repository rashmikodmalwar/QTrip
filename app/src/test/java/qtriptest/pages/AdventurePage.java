package qtriptest.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city="+String.format(%s,city);

    @FindBy(id = "duration-select")
    WebElement select_duration_dropdown;

    @FindBy(id = "category-select")
    WebElement select_category_dropdown;

    @FindBy(id = "search-adventures")
    WebElement search_adventure_text_box;

    @FindBy(xpath = "//div[@id='data']//div[@class='activity-card']")
    List<WebElement> getImageCount;

    @FindBy(xpath ="//div[@onclick='clearDuration(event)']")
    WebElement clear_duration;
    //(//div[@class='ms-3']//parent::div)[2]
    //div[@onclick='clearDuration(event)']
   

    @FindBy(xpath ="//div[@onclick='clearCategory(event)']")
    WebElement clear_Category;
    //(//div[@class='ms-3']//parent::div)[4]
   
    public AdventurePage(RemoteWebDriver driver1){
        this.driver = driver1;
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
    }
    public void navigateToAdventurePage(String city){
        "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city="+String.format(%s,city)
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(url);
        }
    }
    public void setFilterValueDuration(String filter){
     
        Select dropdown = new Select(select_duration_dropdown);
        dropdown.selectByValue(filter);

    }

    public void setFilterValueCategory(String filter){
     
        Select dropdown = new Select(select_category_dropdown);
        dropdown.selectByValue(filter);

    }
   
    public void clearFilterDuration(){
        clear_duration.clear();
        System.out.println("he-------");
    }

    public void clearFilterCategory(){
        clear_Category.clear();
        System.out.println("he-------");
    }

    public int getResultCount(){
    
    if(getImageCount.size()==0){
      return 0;
    }else
   return getImageCount.size();
}

    public void selectCity(String place){
        search_adventure_text_box.sendKeys(place);
    }

}