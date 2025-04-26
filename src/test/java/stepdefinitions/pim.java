package stepdefinitions;

import io.cucumber.java.an.E;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HRMPim;
import utils.LoggerUtils;
import static hooks.Hooks.driver;

import java.time.Duration;
import java.util.List;


public class pim {
    private static HRMPim pimPage = new HRMPim();
    //Scenario 1

    @And("I navigate to the PIM page")
    public void iNavigateToThePIMPage() {
        LoggerUtils.info("Navigating to the PIM page...");
        pimPage.navigateToPim.click();
    }

    @When("I click on the Addpim button")
    public void i_click_on_the_button() {
        LoggerUtils.info("Clicking on the Add employee button");
        pimPage.addBtnPim.click();
    }

    @When("I enter employee details {string} {string} {string}")
    public void i_enter_employee_details(String firstName, String lastName, String employeeID) {
        pimPage.firstNamePim.sendKeys(firstName);
        pimPage.lastNamePim.sendKeys(lastName);
        pimPage.employeeIdPim.sendKeys(Keys.CONTROL + "a"); // Select all text
        pimPage.employeeIdPim.sendKeys(Keys.DELETE);
        pimPage.employeeIdPim.sendKeys(employeeID);

        try {
            if (pimPage.alreadyExistingId.isDisplayed()) {
                LoggerUtils.info("User entered an already existing ID.");
            }
        } catch (Exception e) {  // Catch all exceptions
            LoggerUtils.info("No duplicate ID warning displayed, proceeding...");
        }

    }

    @When("I click the Save button and get successful message")
    public void i_click_the_button() {
        LoggerUtils.info("Saving the User Data...");
        pimPage.saveBtnPim.click();
        LoggerUtils.info("Displaying the successful message...");
        String successMsg = "Successfully Saved";
        try {
            if (pimPage.alreadyExistingId.isDisplayed()) {
                LoggerUtils.info("Continuing the test.. there was a duplicate employee id");
        Assert.assertEquals(pimPage.messageSuccess.getText(),successMsg.trim());
            }
        } catch (Exception e) {  // Catch all exceptions
            LoggerUtils.info("No duplicate ID warning displayed, proceeding...");
        }
    }

    @Then("the new employee should be listed in the system {string}")
    public void the_new_employee_should_be_listed_in_the_system(String employeeID) {
        pimPage.navigateToPim.click();
        LoggerUtils.info("Redirecting to the PIM...");
        List<WebElement> rowForId = pimPage.returnRowIdElements(driver,employeeID);
        Assert.assertEquals(rowForId.get(1).getText().trim(), employeeID);
        LoggerUtils.info("Validates and saved the table...");


    }
    //Scenario 2

    @Then("I search for existing employee {string} {string} {string}")
    public void i_search_for_existing_employee(String firstName, String lastName, String employeeID) throws InterruptedException {
    LoggerUtils.info("Getting the User Data Input");
        System.out.println("Searching for: " + firstName);
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text()")));
    pimPage.employeeLastNameEdit.sendKeys(firstName);
        driver.findElement(By.xpath("//span[contains(text(), '" + firstName + "')]")).click();
        pimPage.employeeIdEdit.sendKeys(employeeID);
    pimPage.searchBtnEdit.click();
        LoggerUtils.info("Submiting the info..");
    }

    @Then("I click edit modify details and save changes  {string}")
    public void i_click_edit(String employeeID) {
        List<WebElement> rowForIdEdit = pimPage.returnRowActions(driver, employeeID);
        LoggerUtils.info("Selecting the user and editing info..");
        rowForIdEdit.getFirst().click();
        pimPage.dlNumber.sendKeys("12345");
        pimPage.saveBtnPim.click();
        String successMsg = "Successfully Updated";
        Assert.assertEquals(pimPage.messageSuccess.getText(),successMsg.trim());
    }


    @Then("I should see a message appear if any of the field are incomplete.")
    public void i_should_see_a_message_appear_if_any_of_the_field_are_incomplete() {
        try {

        if (pimPage.requiredMessage.isDisplayed()){
            LoggerUtils.warn("User didn't fill the required fields");
        }
        } catch (Exception e) {  // Catch all exceptions
            LoggerUtils.info("User enter the required field");
        }
    }

    //Scenario 3
    @And("I select the employee checkbox {string}.")
    public void iSelectTheEmployeeCheckbox(String employeeID) {
        List<WebElement> rowCheckMark = pimPage.returnRowIdElements(driver, employeeID);
        LoggerUtils.info("Selecting the User checkbox");
        rowCheckMark.get(0).click();// delete user possition is 0
    }

    @And("I click delete button and confirm deletion {string}.")
    public void iClickDeleteButtonAndConfirmDeletion(String employeeID) {
        String successMsg = "Successfully Deleted";
        List<WebElement> rowForIdEdit = pimPage.returnRowActions(driver, employeeID);
        rowForIdEdit.get(1).click();
        pimPage.deleteBtn.click();
        Assert.assertEquals(pimPage.messageSuccess.getText(),successMsg.trim());
    }

    @When("I enter employee fist,last,employeeid {string} {string}")
    public void iSearchForEmployee(String firstName, String employeeID) {
        pimPage.employeeLastNameEdit.sendKeys(firstName);
        pimPage.employeeIdPim.sendKeys(employeeID);
    }

    @And("I click search button.")
    public void iClickSearchButton() {
        pimPage.searchBtnEdit.click();
        LoggerUtils.info("Clicking on search");
    }


    @Then("I should see that employee is removed from the list.")
    public void iShouldSeeThatEmployeeIsRemovedFromTheList(String employeeID) {
        List<WebElement> rowForTheUser =
                pimPage.returnRowIdElements(driver, employeeID);
        Assert.assertTrue(
                rowForTheUser.get(2).getText().trim().equals(employeeID));
            LoggerUtils.info("Successfully removed");
    }



//




}
