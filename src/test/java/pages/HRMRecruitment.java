package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;
import java.util.logging.XMLFormatter;

public class HRMRecruitment{

    public HRMRecruitment(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(xpath = "//a[@href=\"/web/index.php/recruitment/viewRecruitmentModule\"]")
    public WebElement recruitmentLink;

    @FindBy(xpath = "//input[@class=\"oxd-input oxd-input--active orangehrm-firstname\"]")
    public WebElement firstName;

    @FindBy(xpath = "//input[@class=\"oxd-input oxd-input--active orangehrm-lastname\"]")
    public WebElement lastName;

    @FindBy(xpath = "//label[@class=\"oxd-label oxd-input-field-required\"]/../following-sibling::div/input[@class=\"oxd-input oxd-input--active\"]")
    public WebElement emailField;

    @FindBy(xpath = "//label[.=\"Vacancy\"]/../following-sibling::div")
    public WebElement vacancyDropDown;

    @FindBy(xpath = "//label[.=\"Job Vacancy\"]/../following-sibling::div")
    public WebElement jobVacancy;

    @FindBy(xpath = "//div[@role=\"listbox\"]//div/span")
    public List<WebElement> userVacancyOptions;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]")
    public WebElement saveBtn;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-button-margin\"]")
    public WebElement confirmBtn;

    @FindBy(xpath = "//label[.=\"Name\"]/../following-sibling::div")
    public WebElement nameConfirmation;

    @FindBy(xpath = "//label[.=\"Vacancy\"]/../following-sibling::div")
    public WebElement vacancyConfirmation;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary\"]")
    public WebElement addBtn;

    @FindBy(xpath = "//p[@class=\"oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text\"]")
    public WebElement successMsg;

    @FindBy(xpath = "//p[@class=\"oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text\"][.=\"Successfully Deleted\"]")
    public WebElement successfullyDeleted;

    @FindBy(xpath = "//p[@class=\"oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text\"][.=\"Successfully Updated\"]")
    public WebElement successfullyUpdated;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")
    public WebElement requiredMsg;

    @FindBy(xpath = "//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"][.=\"Invalid\"]")
    public WebElement invalidMessage;

    @FindBy(xpath = "//label[contains(text(),'Candidate Name')]/following::input[1]")
    public WebElement candidateName;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]")
    public WebElement searchBtn;

    @FindBy(xpath = "//span[@class=\"oxd-switch-input oxd-switch-input--active --label-left\"]")
    public WebElement editBtn;

    @FindBy(xpath = "//input[@name=\"firstName\"]")
    public WebElement firstNameEdit;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin\"]")
    public WebElement confirmDeletion;

    @FindBy(xpath = "//div[@class=\"oxd-autocomplete-option\"][.=\"No Records Found\"]")
    public WebElement noRecordsFound;


    public void chooseVacancy(String userVacancy) {
        vacancyDropDown.click();
        for (WebElement userVacancyOptionsElement : userVacancyOptions) {
            if (userVacancyOptionsElement.getText().trim().equalsIgnoreCase(userVacancy)) {
                userVacancyOptionsElement.click();
                return;
            }
        }
    }


    public void chooseVacancyEdit(String userVacancyEdit) {
        jobVacancy.click();
        for (WebElement userVacancyOptionsElement : userVacancyOptions) {
            if (userVacancyOptionsElement.getText().trim().equalsIgnoreCase(userVacancyEdit)) {
                userVacancyOptionsElement.click();
                return;
            }
        }
    }


    public List<WebElement> returnRowId2Elements(WebDriver driver, String employeeIdNumber){
        String xpathForRowIdElements=
                "//div[@class=\"oxd-table-card\"]//div[2]/div[.="+employeeIdNumber+"]";
        List<WebElement> rowIdElements
                = driver.findElements(By.xpath(xpathForRowIdElements));

        return rowIdElements;

    }


    public List<WebElement> returnRowActions(WebDriver driver, String lastName){
        // This method is going to get the row actions for the username given.
        // first element is delete button, second element is edit button.
        String xpathForRowActions=
                "//div[@class=\"oxd-table-card\"]/div/div[3]/div[contains(normalize-space(), '"+lastName+"')]/../..//div[@class=\"oxd-table-cell-actions\"]/button";
        List<WebElement> rowActions
                = driver.findElements(By.xpath(xpathForRowActions));

        return rowActions;

    }

    public List<WebElement> returnRowModifiedLastName(WebDriver driver, String modifiedLastName){
        String xpathForRowModifiedLastName =
                "//div[@class='oxd-table-card']/div/div[3]/div[contains(normalize-space(), '" + modifiedLastName + "')]";
        return driver.findElements(By.xpath(xpathForRowModifiedLastName));
    }


    public List<WebElement> returnfirstName(WebDriver driver, String firstName){
        String xpathfirstName =
                "//div[@class='oxd-table-card']/div/div[3]/div[contains(normalize-space(), '" + firstName + "')]";
        return driver.findElements(By.xpath(xpathfirstName));
    }


}

