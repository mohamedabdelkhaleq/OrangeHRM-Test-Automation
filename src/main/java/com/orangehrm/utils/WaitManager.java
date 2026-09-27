package com.orangehrm.utils;

import com.orangehrm.utils.dataReader.PropertyReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.ArrayList;

public class WaitManager {
    private WebDriver driver;


    public WaitManager(WebDriver driver) {
        this.driver = driver;
    }
    public FluentWait<WebDriver> getWait() {
        return new FluentWait<>(driver)
                .withTimeout(java.time.Duration.ofSeconds(Long.parseLong(PropertyReader.getProperty("DEFAULT_WAIT"))))
                .pollingEvery(java.time.Duration.ofMillis(100))
                .ignoreAll(getExceptions());
    }

public ArrayList<Class<? extends Exception>> getExceptions() {
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>();
        exceptions.add(StaleElementReferenceException.class);
        exceptions.add(ElementNotInteractableException.class);
        exceptions.add(NoSuchElementException.class);
        exceptions.add(ElementNotInteractableException.class);
        return exceptions;
};



}

