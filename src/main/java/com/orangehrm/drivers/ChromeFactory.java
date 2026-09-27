package com.orangehrm.drivers;

import com.orangehrm.utils.dataReader.PropertyReader;
import com.orangehrm.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class ChromeFactory extends AbstractDriver {
    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
//        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless") ||
//                PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
//            options.addArguments("--headless");
//        }
        return options;
    }


    @Override
    public WebDriver createDriver() {
        return new ChromeDriver(getChromeOptions());
    }
}
