package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {

    private static RemoteWebDriver driver = null;
    
    private DriverSingleton(){
       
    }

    public static RemoteWebDriver getInstance(){
        if(driver == null){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(BrowserType.CHROME);
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),capabilities);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
        return driver;
    }


}