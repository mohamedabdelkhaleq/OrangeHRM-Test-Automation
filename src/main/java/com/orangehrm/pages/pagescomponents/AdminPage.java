package com.orangehrm.pages.pagescomponents;

import com.orangehrm.drivers.GUIDriver;
import org.openqa.selenium.By;

public class AdminPage {
   private final GUIDriver driver;
    public AdminPage(GUIDriver driver) {
        this.driver = driver;
    }
    private String role;
    private String status;
    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By recoardNums = By.cssSelector("div > div.orangehrm-horizontal-padding > span.oxd-text");
    private final By userRoleDropDown = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]");
    private final By userRoleChoice  = By.xpath("//div[contains(text(),"+"'"+role+"')]");
    private final By empoyeeName = By.xpath("//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']");
    private final By statusDropDown = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]");
    private final By statusChoice = By.xpath("//div[contains(text(),"+"'"+status+"')]");
    private final By userNAME = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By passWord = By.xpath("(//input[@type='password'])[1]");
    private final By confirmPassword = By.xpath("(//input[@type='password'])[2]");
    private final By saveButton = By.xpath("//button[@type='submit']");
    public NavigationBarComponents addNewUserSys(String userRole ,String employeeName,String status,String userName,String password,String confirmPassword){
        return clickAddButton()
                .clickUserRoleDropDown()
                .clickUserRoleChoice()
                .clickStatusDropDown()
                .clickStatusChoice()
                .addEmpoyeeName(employeeName)
                .addUserName(userName)
                .addPassword(password)
                .addConfirmPassword(confirmPassword)
                .clickSaveButton();

    }
    public AdminPage clickAddButton() {
        driver.element().click(addButton);
        return this;
    }
    public String getRecoardNums() {
        return driver.element().getText(recoardNums);
    }
    public AdminPage clickUserRoleDropDown() {
         driver.element().click(userRoleDropDown);
         return this;
    }
    public AdminPage clickUserRoleChoice() {
        driver.element().click(userRoleChoice);
        return this;
    }
    public AdminPage clickStatusDropDown() {
        driver.element().click(statusDropDown);
        return this;
    }
    public AdminPage clickStatusChoice() {
        driver.element().click(statusChoice);
        return this;
    }
    public AdminPage addEmpoyeeName(String empoyeename) {
        driver.element().type(empoyeeName,empoyeename);
        return this;
    }
    public AdminPage addUserName(String userName) {
        driver.element().type(userNAME,userName);
        return this;
    }
    public AdminPage addPassword(String password) {
        driver.element().type(passWord,password);
        return this;
    }
    public AdminPage addConfirmPassword(String password) {
        driver.element().type(confirmPassword,password);
        return this;
    }
    public NavigationBarComponents clickSaveButton() {
        driver.element().click(saveButton);
        return new NavigationBarComponents(driver);
    }

}
