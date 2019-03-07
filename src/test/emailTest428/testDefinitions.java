package test.emailTest428;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testDefinitions {

    private WebDriver driver;

    private final String PATH_TO_WEBDRIVER = "/Users/adeeb27/Documents/University_Stuff/WINTER2019/ECSE428/AssignmentB/chromedriver";
    private final String SIGNIN_PAGE_URL = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
    private final String GMAIL_MAIN_PAGE = "https://mail.google.com/mail/u/0/#inbox";
    private final String SIGNIN_PAGE_USERNAME_ID = "identifierId";
    private final String SIGNIN_PAGE_USERNAME_NEXT_ID = "identifierNext";
    private final String SIGNIN_PAGE_PASSWORD_NAME = "password";
    private final String SIGNIN_PAGE_PASSWORD_NEXT_ID = "passwordNext";
    private final String RECIPIENT_BOX = "//*[@aria-label=\"To\"]";
    private final String SUBJECT_BOX = "//*[@aria-label=\"Subject\"]";
    private final String USER_MAIL = "testassignmentb428@gmail.com";
    private final String USER_PASSWORD = "hello123@";

    @Given("I am logged in")
    public void iAmLoggedIn() throws Throwable {
        setupSeleniumWebDrivers();
        goTo(SIGNIN_PAGE_URL);

        WebElement user_field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id(SIGNIN_PAGE_USERNAME_ID)));
        user_field.sendKeys(USER_MAIL);

        WebElement next_button = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id(SIGNIN_PAGE_USERNAME_NEXT_ID)));
        next_button.click();

        WebElement password_field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.name(SIGNIN_PAGE_PASSWORD_NAME)));
        password_field.sendKeys(USER_PASSWORD);

        WebElement second_next_button = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id(SIGNIN_PAGE_PASSWORD_NEXT_ID)));
        second_next_button.click();


        String URL = driver.getCurrentUrl();
        System.out.println(URL);
    }

    @And("I am on the Gmail main page")
    public void iAmOnTheGmailMainPage() {

        if (driver.getCurrentUrl().equals(GMAIL_MAIN_PAGE)) {
            System.out.println("Logged In Successfully!");
        }

    }

    @When("I press {string}")
    public void iPress(String composeButtonText) {
        WebElement compose = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + composeButtonText + "']")));
        compose.click();
    }


    @And("I compose an email to {string}")
    public void iComposeAnEmailToTestAssignmentB428GmailCom(String email) {
        WebElement recipient = (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(By.xpath(RECIPIENT_BOX)));
        recipient.sendKeys(email);

        WebElement subject_box = (new WebDriverWait(driver, 2)).until(ExpectedConditions.elementToBeClickable(By.xpath(SUBJECT_BOX)));
        subject_box.sendKeys("Testing email at six");


        WebElement body_box = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Ar Au']//div")));
        body_box.click();
        body_box.sendKeys("Testing email");
    }

    @And("I attach a picture")
    public void iAttachAPicture() {
        driver.findElement(By.name("Filedata")).sendKeys("/Users/adeeb27/Downloads/Appa_eating_hay.gif");
    }



    @Then("the email should be sent")
    public void theEmailShouldBeSent() {
        WebElement sentMailBtn = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Sent']")));
        sentMailBtn.click();
        if (driver.findElement(By.xpath("//div[text()='Testing email at six']")) != null) {

            System.out.println("Email was sent successfully!");
        }
        else {
            System.out.println("Email failed to send!");
        }

    }

    // Helper functions
    private void setupSeleniumWebDrivers() {
        if (driver == null) {
            System.out.println("Setting up ChromeDriver... ");
            System.setProperty("webdriver.chrome.driver", PATH_TO_WEBDRIVER);
            driver = new ChromeDriver();
            System.out.print("Done!\n");
        }
    }

    private void goTo(String url) {
        if (driver != null) {
            System.out.println("Going to " + url);
            driver.get(url);
        }
    }

}

