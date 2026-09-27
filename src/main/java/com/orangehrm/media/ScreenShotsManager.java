package com.orangehrm.media;

import com.orangehrm.utils.TimeManager;
import com.orangehrm.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShotsManager {
    public static final String SCREENSHOT_PATH = "test-output/screenshots/";

    //take Full page ScreenShot
    public static void takeFullPageScreenshot(WebDriver driver, String screenshotName) {
        try {
            File screenShotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


            //save screenshot to a file if needed
            File screenShotFile = new File(SCREENSHOT_PATH + screenshotName + TimeManager.grtTimeStamp() +".png");
            FileUtils.copyFile(screenShotSrc,screenShotFile);

            //TODO: Attach the Screenshot to Allure if needed
            LogsManager.info("Screenshot taken successfully");

        }catch (Exception e){
            LogsManager.error("Failed to Capture ScreenShot");
        }

    }
    //take screenshot to a specific element
    public static void takeElementScreenShot(WebDriver driver, By elementLocator) {
        try{
            String elementName = driver.findElement(elementLocator).getAccessibleName();
            File screenShotSrc = driver.findElement(elementLocator).getScreenshotAs(OutputType.FILE);
            File screenShotFile = new File(SCREENSHOT_PATH + elementName + TimeManager.grtTimeStamp() +".png");
            FileUtils.copyFile(screenShotSrc,screenShotFile);
            LogsManager.info("Screenshot Element taken successfully");


            //TODO: Attach the Screenshot to Allure if needed

        }catch (Exception e){
            LogsManager.error("Failed to Capture Element ScreenShot",e.getMessage());

        }
    }

}
