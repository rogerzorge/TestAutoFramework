package by.epam.emailtest.test;

import by.epam.taframework.businessobject.Account;
import by.epam.taframework.businessobject.Message;
import by.epam.taframework.pageobject.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;
import static java.lang.Math.random;
import static org.testng.Assert.fail;

/**
 * Created by Yahor_Faliazhynski on 1/12/2016.
 */
public class BaseTestPO {

    protected WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    protected int randomNum;

    SignInPage signInPage;
    InboxPage inboxPage;
    ComposePage composePage;
    DraftPage draftPage;
    DraftItemPage draftItemPage;
    SentPage sentPage;
    Account user01;
    Message email01;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.out.println("Class start!");
        randomNum = (int)abs(random() * 1000000);
        System.out.println("Current random number is: " + randomNum);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        signInPage = new SignInPage(driver);
        driver.get(SignInPage.GMAIL_URL);

        user01 = new Account("ivan.mailfortest", "Zaq1!Xsw2@");
        email01 = new Message("ivan.mailtest@mail.ru", "Test subject " + randomNum + " gmail", "Test body " + randomNum + " test");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("Class finish!");
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}