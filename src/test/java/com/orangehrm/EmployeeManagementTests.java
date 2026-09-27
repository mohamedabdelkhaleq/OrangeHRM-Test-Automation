package com.orangehrm;

import com.jayway.jsonpath.JsonPath;
import com.orangehrm.pages.pagescomponents.EmployeeManagementPage;
import com.orangehrm.utils.dataReader.JsonReader;
import com.orangehrm.utils.dataReader.PropertyReader;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeManagementTests extends BaseTest {
    JsonReader testEmployeedata = new JsonReader("employee.json");
    @Test(description = "TC-01:Add new Employee with valid name and Id")
    public void addNewEmployee() {
                EmployeeManagementPage employeeManagementPage =navigationBarComponents.clickPimButton()
                .clickAddBTN()
                .addFirstName(testEmployeedata.getJsonData("validEmployee.first_name"))
                .addLastName(testEmployeedata.getJsonData("validEmployee.last_name"))
                .addEmployeeId(testEmployeedata.getJsonData("validEmployee.employee_id"))
                .clickSaveButton();


        Assert.assertEquals(testEmployeedata.getJsonData("validEmployee.first_name")+" "
                + testEmployeedata.getJsonData("validEmployee.last_name"),employeeManagementPage.getProfileLabel());

    }
    @Test(description = "TC-02:Add new system user with all required fields")
    public void addNewSystemUser() {
        EmployeeManagementPage employeeManagementPage =navigationBarComponents.clickPimButton()
                .clickAddBTN()
                .addFirstName(testEmployeedata.getJsonData("executiveEmployee.first_name"))
                .addLastName(testEmployeedata.getJsonData("executiveEmployee.last_name"))
                .addEmployeeId(testEmployeedata.getJsonData("executiveEmployee.employee_id"))
                .clickLoginDetails()
                 .addUserName("executiveEmployee.username")
                .addPassword("executiveEmployee.password")
                .addConfirmPassword("executiveEmployee.password")
                .clickSaveButton();

        Assert.assertFalse(employeeManagementPage.getCurrentUrl().contains("viewPersonalDetails"),
                "Should land on dashboard after login");


    }
    @Test(description = "TC-03:trying to add an already registered user"
            ,dependsOnMethods = "addNewEmployee")
    public void addreqisteredUser() {
        EmployeeManagementPage employeeManagementPage =navigationBarComponents.clickPimButton()
                .clickAddBTN()
                .addFirstName(testEmployeedata.getJsonData("validEmployee.first_name"))
                .addLastName(testEmployeedata.getJsonData("validEmployee.last_name"))
                .addEmployeeId(testEmployeedata.getJsonData("validEmployee.employee_id"))
                .clickSaveButton();
        Assert.assertEquals("Employee Id already exists",employeeManagementPage.getErrormessage());
    }




//    @Test
//    public void addNewSystemUserWithInvalidName() {
//          String BASE_API = "https://opensource-demo.orangehrmlive.com/web/index.php/api/v2";
//         String apiToken;
//         String newUsername = "e2euser_" + System.currentTimeMillis(); // unique every run
//         String newPassword = "Test@1234";
//        RestAssured.baseURI = BASE_API;
//        // OrangeHRM demo uses HTTP Basic auth on every API request
//        apiToken = "Basic " +
//                java.util.Base64.getEncoder()
//                        .encodeToString(
//                                (PropertyReader.getProperty("admin.username") + ":" +PropertyReader.getProperty("admin.password"))
//                                        .getBytes()
//                        );
//
//        System.out.println(apiToken);
//    }



}
