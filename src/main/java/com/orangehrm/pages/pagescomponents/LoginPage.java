package com.orangehrm.pages.pagescomponents;

import com.orangehrm.drivers.GUIDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {
    private final GUIDriver driver;

    private final By userNameFiled =  By.cssSelector("input[placeholder='Username']");
    private final By passwordField = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By invaliderrorMessage = By.xpath("//p[contains(.,'credentials')]");
    private final By requiredErrorMessage =By.cssSelector("span.oxd-input-field-error-message");


    public LoginPage (GUIDriver driver) {
        this.driver = driver;

    }
    public NavigationBarComponents login(String username, String password) {
        driver.element().type(userNameFiled, username);
        driver.element().type(passwordField, password);
        driver.element().click(loginButton);
        return new NavigationBarComponents(driver);
    }
    public LoginPage addUserName(String username){
        driver.element().type(userNameFiled, username);
        return this;
    }
    public LoginPage addPassword(String password){
        driver.element().type(passwordField, password);
        return this;
    }
    public NavigationBarComponents clickLoginButton(){
        driver.element().click(loginButton);
        return new NavigationBarComponents(driver);
    }
    public boolean isInvalidErrorMessageVisible(){
       return driver.element().IsVisible(invaliderrorMessage);

    }
    public boolean isRequiredFiledErrorVisible(){
        return driver.element().IsVisible(requiredErrorMessage);
    }
//    public boolean isRequiredFieldsErrorVisible(){
//        List<WebElement> fields = driver.findElements(requiredErrorMessage);
//        for(WebElement field : fields){
//            if(!field.isDisplayed()){
//                return false;
//            }
//        }
//        return true;
//    }

}
