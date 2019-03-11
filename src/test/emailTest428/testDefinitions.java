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

import java.io.File;

public class testDefinitions {

    private WebDriver driver;

    private final String PATH_TO_WEBDRIVER = "/Users/adeeb27/Documents/University_Stuff/WINTER2019/ECSE428/AssignmentB/chromedriver";
    private final String SIGNIN_PAGE_URL = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
    private final String GMAIL_MAIN_PAGE = "https://mail.google.com/mail/#inbox";
    private final String SIGNIN_PAGE_USERNAME_ID = "identifierId";
    private final String SIGNIN_PAGE_USERNAME_NEXT_ID = "identifierNext";
    private final String SIGNIN_PAGE_PASSWORD_NAME = "password";
    private final String SIGNIN_PAGE_PASSWORD_NEXT_ID = "passwordNext";
    private final String RECIPIENT_BOX = "//*[@aria-label=\"To\"]";
    private final String SUBJECT_BOX = "//*[@aria-label=\"Subject\"]";

    private final String ACCOUNT_ICON = "//*[@aria-label=\"Google Account: ecse testassignmentb  \n" +
            "(testassignmentb428@gmail.com)\"]";

    private final String USER_MAIL = "testassignmentb428@gmail.com";
    private final String USER_PASSWORD = "hello123@";

    private String emailRecipient = "";
    private String ERROR_BOX_TEXT = "The address \"hello\" in the \"To\" field was not recognized. Please make sure that all addresses are properly formed.";

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

        while (true) {
            if (driver.getCurrentUrl().equals(GMAIL_MAIN_PAGE)) {
                System.out.println("Logged In Successfully!");
                break;
            }

        }
    }

    @When("I compose an email to ([^\"]*)")
    public void iAnEmailToEmail(String email) {
        compose();
        emailRecipient = email;
        WebElement recipient = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(RECIPIENT_BOX)));
        recipient.sendKeys(email);
    }

    @And("I enter a ([^\"]*) and ([^\"]*)")
    public void iEnterASubjectAndBody(String body, String subject) {
        WebElement body_box = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Ar Au']//div")));
        body_box.click();
        body_box.sendKeys(body);
        WebElement subject_box = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(SUBJECT_BOX)));
        subject_box.sendKeys(subject);
    }

    @And("I attach a file ([^\"]*)")
    public void iAttachAFile1(String filename) {
        driver.findElement(By.name("Filedata")).sendKeys(filename);
    }

    @And("I {string} the email with subject and body")
    public void iTheEmailWithSubjectAndBody(String sendBtnText) {
        WebElement sendBtn = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + sendBtnText + "']")));
        sendBtn.click();

    }

    @Then("the email should be sent with ([^\"]*) and ([^\"]*)")
    public void theEmailShouldBeSentWithSubjectAndAttachment(String subjectOfEmail, String attachmentText) {
        WebElement sentMailBtn = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Sent']")));
        sentMailBtn.click();
        File f = new File (attachmentText);
        String temp = f.getName();

        if ((driver.findElement(By.xpath("//div[text()='" + subjectOfEmail + "']")) != null) && (driver.findElement(By.xpath("//div[text()='" + temp + "']")) != null)) {
            WebElement specificSentEmail = (new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@email=\"" + emailRecipient + "\"]/../.."))));
            specificSentEmail.click();
            System.out.println("Email was sent successfully!");
        }
        else {
            System.out.println("Email failed to send!");
        }

        signOut();

    }

    @When("I compose an email to {string}")
    public void iComposeAnEmailTo(String errorEmail) {
        WebElement compose = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Compose']")));
        compose.click();
        WebElement recipient = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(RECIPIENT_BOX)));
        recipient.sendKeys(errorEmail);
    }

    @And("I enter in the subject box {string}")
    public void iEnterInTheSubjectBox(String subjectText) {
        WebElement subject_box = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(SUBJECT_BOX)));
        subject_box.sendKeys(subjectText);
    }

    @Then("I should get an error")
    public void iShouldGetAnError() {
        if (driver.findElement(By.xpath("//div[text()='" + ERROR_BOX_TEXT + "']")) != null) {

            System.out.println("Invalid Recipient test passed");
            WebElement okaybtn = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.name("ok")));
            okaybtn.click();
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.className("Kj-JD-Jh")));
           // driver.switchTo().alert().accept();
            signOut();
        }
    }

    @And("I {string} the email")
    public void iTheEmail(String button) {
        WebElement sendBtn = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + button + "']")));
        sendBtn.click();
    }



    @And("I attach a file {string}")
    public void iAttachAFile(String attachment) {
        driver.findElement(By.name("Filedata")).sendKeys(attachment);
    }

    @Then("the email should be sent with {string}")
    public void theEmailShouldBeSent(String attachmentText) {
        WebElement sentMailBtn = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Sent']")));
        sentMailBtn.click();
        File f = new File (attachmentText);
        String temp = f.getName();

        if (driver.findElement(By.xpath("//div[text()='" + temp + "']")) != null) {
            WebElement specificSentEmail = (new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@email=\"" + "adrianguindani@gmail.com" + "\"]/../.."))));
            specificSentEmail.click();
            System.out.println("Email was sent successfully!");
        }
        else {
            System.out.println("Email failed to send!");
        }

        signOut();
    }

    @And("I {string} the email with no subject and body")
    public void iTheEmailWithNoSubjectAndBody(String sendBtnText) {
        WebElement sendBtn = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + sendBtnText + "']")));
        sendBtn.click();
        driver.switchTo().alert().accept();
    }


    // Helper functions
    private void setupSeleniumWebDrivers() {
        if (driver == null) {
            System.out.println("Setting up ChromeDriver... ");
            System.setProperty("webdriver.chrome.driver", PATH_TO_WEBDRIVER);
            driver = new ChromeDriver();
            System.out.println("Done!");
        }
    }

    private void goTo(String url) {
        if (driver != null) {
            System.out.println("Going to " + url);
            driver.get(url);
        }
    }

    public void signOut()  {
        WebElement accountIcon = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_ICON)));
        accountIcon.click();
        WebElement signOutbtn = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("gb_71")));
        signOutbtn.click();
        System.out.println("Logged out successfully!");
        System.out.println("Closing browser...");
        driver.quit();
    }

    private void compose() {
        WebElement compose = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Compose']")));
        compose.click();
    }
}

