package com.orangehrm.drivers;

import com.orangehrm.utils.dataReader.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxFactory extends AbstractDriver {
    public FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless")||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            options.addArguments("--headless");
        }
        return options;
    }



    @Override
    public WebDriver createDriver() {
        return new FirefoxDriver(getFireFoxOptions());
    }

}
