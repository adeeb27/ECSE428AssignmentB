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
        private final String COMPOSE_BUTTON_ID = "//*[@id=\":3s\"]/div/div";
        private final String SIGNIN_PAGE_USERNAME_ID = "identifierId";
        private final String SIGNIN_PAGE_USERNAME_NEXT_ID = "identifierNext";
        private final String SIGNIN_PAGE_PASSWORD_NAME = "password";
        private final String SIGNIN_PAGE_PASSWORD_NEXT_ID = "passwordNext";
        private final String RECIPIENT_BOX = ":9f";
        private final String SUBJECT_BOX = ":8x";
        private final String BODY_BOX = ":a2";
        private final String ATTACH_ICON_ID = "//*[@id=\":ej\"]";
        private final String SEND_EMAIL_ID = "//*[@id=\":ej\"]";
        private final String USER_MAIL = "testmailassignment428@gmail.com";
        private final String USER_PASSWORD = "adeebwiwi428";

        @Given("I am logged in")
        public void iAmLoggedIn() throws Throwable{
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
        public void iPress(String arg0) {
            WebElement compose = (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(By.xpath(COMPOSE_BUTTON_ID)));
            compose.click();
        }

        @And("I compose an email to dibbo.ritwik@mail.mcgill.ca")
        public void iComposeAnEmailToDibboRitwikMailMcgillCa() {
            WebElement recipient_box = (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(By.id(RECIPIENT_BOX)));
            recipient_box.sendKeys("testmailassignment428@gmail.com");

            WebElement subject_box = (new WebDriverWait(driver, 1)).until(ExpectedConditions.elementToBeClickable(By.id(SUBJECT_BOX)));
            subject_box.sendKeys("Testing email");

            WebElement body_box = (new WebDriverWait(driver, 1)).until(ExpectedConditions.elementToBeClickable(By.id(BODY_BOX)));
            body_box.sendKeys("Testing email");
        }

        @And("I attach a picture")
        public void iAttachAPicture() {
//            WebElement attach_btn = (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(By.id(":af")));
//            attach_btn.click();
        }

        @Then("the email should be sent")
        public void theEmailShouldBeSent() {
            WebElement send_email = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.id(":8n")));
            send_email.click();

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

