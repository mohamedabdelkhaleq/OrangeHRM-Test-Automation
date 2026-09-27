package com.orangehrm.drivers;

import com.orangehrm.utils.dataReader.PropertyReader;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.*;

public class EdgeFactory extends AbstractDriver {

    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
//        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless")||
//                PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
//            options.addArguments("--headless");
//        }
        return options;

    }





    @Override
    public WebDriver createDriver() {
        return new EdgeDriver(getEdgeOptions());
    }
}
