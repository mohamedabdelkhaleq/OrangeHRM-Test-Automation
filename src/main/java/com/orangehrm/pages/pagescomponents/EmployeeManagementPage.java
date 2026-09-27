package com.orangehrm.pages.pagescomponents;

import com.orangehrm.drivers.GUIDriver;
import org.openqa.selenium.By;

public class EmployeeManagementPage {
    private final GUIDriver driver;

    public EmployeeManagementPage(GUIDriver driver) {
        this.driver = driver;
    }

    private final By employeeName = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    private final By employeeId = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By firstName = By.xpath("(//input[@placeholder='First Name'])[1]");
    private final By lastName = By.xpath("//input[@placeholder='Last Name']");
    private final By loginDetailsCheckBox = By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']");
    private final By userName = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private final By passwordField = By.xpath("(//input[@type='password'])[1]");
    private final By confirmPassword = By.xpath("(//input[@type='password'])[2]");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By deleteButton = By.xpath("//i[@class='oxd-icon bi-trash']");
    private final By profileLabel = By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']");
    private final By idAlreadyExists = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");


    public NavigationBarComponents AddEmployee(String firstName, String lastName,String employeeId){
        clickAddBTN()
        .addFirstName(firstName)
                .addLastName(lastName)
                .addEmployeeId(employeeId)
                .clickSaveButton();
        return new NavigationBarComponents(driver);
    }
    public EmployeeManagementPage clickAddBTN() {
        driver.element().click(addButton);
        return this;
    }
    public EmployeeManagementPage addFirstName(String name) {
        driver.element().type(firstName,name);
        return this;
    }
    public EmployeeManagementPage addLastName(String name) {
        driver.element().type(lastName,name);
        return this;
    }
    public EmployeeManagementPage employeeId(String id) {
        driver.element().type(employeeId,id);
        return this;
    }
    public EmployeeManagementPage clickLoginDetails() {
        driver.element().click(loginDetailsCheckBox);
        return this;
    }
    public EmployeeManagementPage addUserName(String name) {
        driver.element().type(userName,name);
        return this;
    }
    public EmployeeManagementPage addPassword(String password) {
        driver.element().type(passwordField,password);
        return this;
    }
    public EmployeeManagementPage addConfirmPassword(String password) {
        driver.element().type(confirmPassword,password);
        return this;
    }
    public EmployeeManagementPage clickSaveButton() {
        driver.element().click(saveButton);
        return new EmployeeManagementPage(driver);
    }
    public NavigationBarComponents toNavigationBarComponents(){
        return new NavigationBarComponents(driver);
    }
    public EmployeeManagementPage addEmployeeName(String name) {
        driver.element().type(firstName,name);
        return this;
    }
    public EmployeeManagementPage addEmployeeId(String id) {
        driver.element().type(employeeId,id);
        return this;
    }
    public String getEmployeeId(){
        return driver.element().getText(employeeId);
    }
    public EmployeeManagementPage clickDeleteButton() {
        driver.element().click(deleteButton);
        return this;
    }
    public String getCurrentUrl(){
        return driver.browser().getCurrentUrl();
    }
    public String getProfileLabel() {
       return driver.element().getText(profileLabel);
    }
    public String getErrormessage() {
        return driver.element().getText(idAlreadyExists);
    }



}
