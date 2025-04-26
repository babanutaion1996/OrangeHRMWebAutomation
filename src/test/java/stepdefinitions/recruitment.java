package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HRMLogin;
import pages.HRMRecruitment;
import utils.LoggerUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

import static hooks.Hooks.driver;

public class recruitment {
    private static HRMRecruitment recruitmentPage = new HRMRecruitment();
    //Scenario 1
    @And("I navigate to the Recruitment page")
    public void iNavigateToTheRecruitmentPage() {
        LoggerUtils.info("Redirecting to the Recruitment Page...");
        recruitmentPage.recruitmentLink.click();
    }

    @When("I click Add Candidate")
    public void i_click_add_candidate() {
        LoggerUtils.info("Adding a user..");
        recruitmentPage.addBtn.click();
    }

    @When("I enter candidate details {string} {string} {string} {string}.")
    public void i_enter_candidate_details(String lastName, String firstName, String userVacancy, String userEmail) {
    recruitmentPage.lastName.sendKeys(lastName);
    recruitmentPage.firstName.sendKeys(firstName);
    recruitmentPage.chooseVacancy(userVacancy);
    recruitmentPage.emailField.sendKeys(userEmail);

    }

    @Then("I Click save and verify the candidate is added. {string}")
    public void i_click_save_and_verify_the_candidate_is_added(String firstName) {
        LoggerUtils.info("Saving the user input..");
        recruitmentPage.saveBtn.click();

        // Wait for success message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(recruitmentPage.successMsg));
        Assert.assertTrue("Success message is NOT displayed!", recruitmentPage.successMsg.isDisplayed());

        if (!recruitmentPage.firstName.isDisplayed()) {
            Assert.fail("Candidate first name is NOT displayed after saving!");
        } else {
            String actualFirstName = recruitmentPage.firstName.getAttribute("value").trim();
            Assert.assertEquals("Expected first name is incorrect!", firstName, actualFirstName);
            LoggerUtils.info("User info successfully added: " + actualFirstName);
        }
    }
// SCENARIO 2

    @When("I search for candidate {string} {string} {string}.")
    public void i_search_for_candidate(String lastName, String firstName, String userVacancy) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        boolean isInvalidMessagePresent = isElementPresent(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][.='Invalid']"));

        if (!isInvalidMessagePresent) {
            recruitmentPage.candidateName.sendKeys(firstName);
            WebElement candidateOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(), '" + firstName + "')]")));
            candidateOption.click();

            LoggerUtils.info("Sending candidate name");
            recruitmentPage.chooseVacancy(userVacancy);
            recruitmentPage.searchBtn.click();
            LoggerUtils.info("Clicking on the search element");
        } else {
            LoggerUtils.info("No user info to search..");
            Assert.assertTrue("Invalid message should be displayed!", isInvalidMessagePresent);
        }

        // If the invalid message was present initially, verify that it still exists
        if (isInvalidMessagePresent) {
            Assert.assertTrue("Invalid message is not displayed!", isElementDisplayed(recruitmentPage.invalidMessage));
        }
    }

    /**
     * Helper method to check if an element is present in the DOM.
     */
    public boolean isElementPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Helper method to check if an element is displayed without causing NoSuchElementException.
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    @When("I click Edit, modify details, and save changes {string} {string} {string}.")
    public void i_click_edit_modify_details_and_save_changes(String lastName, String modifiedLastName, String modifiedUserVacancy) throws InterruptedException {
        List<WebElement> edit_delete =  recruitmentPage.returnRowActions(driver,lastName);
        edit_delete.getFirst().click(); // clicking on edit for the
        LoggerUtils.info("Clicked on edit user");
        recruitmentPage.editBtn.click();
        recruitmentPage.firstNameEdit.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        LoggerUtils.info("Input field cleared");
        recruitmentPage.firstNameEdit.sendKeys(modifiedLastName);
        LoggerUtils.info("Sent new user lastname..");
        recruitmentPage.chooseVacancyEdit(modifiedUserVacancy);
        recruitmentPage.saveBtn.click();
        recruitmentPage.confirmBtn.click();

    }
    @Then("I should see succes message is displayed.")
    public void i_should_see_succes_message_is_displayed() {
        String succesMessage = "Successfully Updated";
    Assert.assertEquals(succesMessage,recruitmentPage.successfullyUpdated.getText());
        LoggerUtils.info("Succes message is displayed.");
    }

    @Then("Details are updated correctly. {string}")
    public void details_are_updated_correctly(String modifiedLastName) {
        recruitmentPage.recruitmentLink.click();
        List<WebElement> rowForTheUserM =
                recruitmentPage.returnRowModifiedLastName(driver, modifiedLastName);
        Assert.assertFalse("No matching row found for modified last name: " + modifiedLastName, rowForTheUserM.isEmpty());
        String actualText = rowForTheUserM.getFirst().getText().trim();
        Assert.assertTrue("Expected last name: " + modifiedLastName + " but found: " + actualText,
                actualText.contains(modifiedLastName));

    }

//SCENARIO 3

    @When("I select the employee checkbox delete to confirm deletion {string}.")
    public void i_select_the_employee_checkbox(String lastName) {
        List<WebElement> edit_delete =  recruitmentPage.returnRowActions(driver,lastName);
        edit_delete.get(1).click(); // clicking on delete
        LoggerUtils.info("Clicked on edit user");
        recruitmentPage.confirmDeletion.click();
        LoggerUtils.info("Deleting selected user");
    }
    @Then("I should see that employee is removed from the list {string}.")
    public void i_should_see_that_employee_is_removed_from_the_list(String lastName) {
        List<WebElement> rowForTheUserM =
                recruitmentPage.returnRowModifiedLastName(driver, lastName);
        Assert.assertTrue(rowForTheUserM.isEmpty());
        LoggerUtils.info("Users were deleted...");
    }

    @Then("I should see succes message is displayed for deletion.")
    public void i_should_see_succes_message_is_displayed_for_deletion() {
        String succesMessage = "Successfully Deleted";
        Assert.assertEquals(succesMessage,recruitmentPage.successfullyDeleted.getText());
        LoggerUtils.info("Succes message is displayed.");
    }


    //SCENARIO 4

    @When("I search for candidate {string}.")
    public void i_search_for_candidate(String firstName) {
        LoggerUtils.info("Searching for candidate: " + firstName);
        recruitmentPage.candidateName.sendKeys(firstName, Keys.ENTER);
        if (driver.findElements(By.xpath("//div[@class='oxd-autocomplete-option'][.='No Records Found']")).size() > 0) {
            LoggerUtils.info("No records found for " + firstName);
            Assert.assertTrue("Expected 'No Records Found' message, but it was not displayed!",
                    recruitmentPage.noRecordsFound.isDisplayed());
            return;
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement candidateOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(), '" + firstName + "')]")));
            candidateOption.click();
            LoggerUtils.info("Candidate selected: " + firstName);
        } catch (Exception e) {
            LoggerUtils.info("No candidate dropdown found, proceeding with search...");
        }

        recruitmentPage.searchBtn.click();
        LoggerUtils.info("Clicked search button for candidate: " + firstName);
    }



    @Then("I should see matching employee details appear {string} {string}.")
    public void i_should_see_matching_employee_details_appear(String firstName,String lastName) {
        recruitmentPage.recruitmentLink.click();
        LoggerUtils.info("Redirecting to the PIM...");
        List<WebElement> rowForTheUserM  = recruitmentPage.returnfirstName(driver, firstName+" "+ lastName);
        Assert.assertTrue(rowForTheUserM.get(1).getText().equals(firstName+" " + lastName));
        LoggerUtils.info("Validates and saved the table...");

    }




}
