package com.orangehrm.pages.pagescomponents;

import com.orangehrm.drivers.GUIDriver;
import com.orangehrm.utils.dataReader.PropertyReader;
import com.orangehrm.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class NavigationBarComponents {
    private final GUIDriver driver;
    public NavigationBarComponents(GUIDriver driver) {
        this.driver = driver;
    }
    //Locators
    private final By adminButton = By.xpath("//span[normalize-space()='Admin']");
    private final By dashboardButton = By.xpath("//span[normalize-space()='Dashboard']");
    private final By pimButton = By.xpath("//span[normalize-space()='PIM']");
    private final By leaveButton = By.xpath("//span[normalize-space()='Leave']");
    private final By myInfoButton = By.xpath("//span[normalize-space()='My Info']");
    private final By recruitmentButton = By.xpath("//span[normalize-space()='Recruitment']");
    private final By userAccountMenu = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private final By logoutButton =  By.xpath("//a[normalize-space()='Logout']");
    private final By userNameLabel = By.xpath("//p[@class='oxd-userdropdown-name']");
    //Actions
    @Step("Navigate To Home Page")
    public NavigationBarComponents navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("homeUrl"));
        return this;
    }
    @Step("Navigate to Admin page")
    public AdminPage clickAdminButton(){
        driver.element().click(adminButton);
        return new AdminPage(driver);
    }
    @Step("Navigate to DashBoard")
    public DashboardPage clickDashboardButton(){
        driver.element().click(dashboardButton);
        return new DashboardPage(driver);
    }
    @Step("Navigate to EmployeeManagement page")
    public  EmployeeManagementPage  clickPimButton(){
        driver.element().click(pimButton);
        return new EmployeeManagementPage(driver);
    }
    @Step("Navigate to Leave page")
    public LeavePage clickLeaveButton(){
        driver.element().click(leaveButton);
        return new LeavePage(driver);
    }
    @Step("Navigate to MyInfo page")
    public MyInfoPage clickMyInfoButton(){
        driver.element().click(myInfoButton);
        return new MyInfoPage(driver);
    }
    @Step("Navigate to Recruitment page")
    public RecruitmentPage clickRecruitmentButton(){
        driver.element().click(recruitmentButton);
        return new RecruitmentPage(driver);
    }
    public NavigationBarComponents clickMenuButton(){
        driver.element().click(userAccountMenu);
        return this;
    }
    public  LoginPage clickLogoutButton(){
        driver.element().click(logoutButton);
        return new LoginPage(driver);
    }
    public NavigationBarComponents verifyUserLabel(String expectedName){
        String actualName = driver.element().getText(userNameLabel);
        LogsManager.info("verifying user label:  " + actualName);
    driver.verification().assertEquals(actualName,expectedName,"User name does not match. Expected: " + expectedName
            + ", Actual: " + actualName);
    return this;
    }






}


