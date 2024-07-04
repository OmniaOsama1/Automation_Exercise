package registerationTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Signup_LoginPage;
import resources.testData.RegisterWithExistingUserData;
import java.io.IOException;
import java.time.Duration;

public class RegisterUserWithExistingEmail {
    private WebDriver driver;

    @Test
    public void signupNewUser() throws IOException, ParseException {
        HomePage homePage = new HomePage(driver);
        RegisterWithExistingUserData register = new RegisterWithExistingUserData(driver);
        register.registerWithExistingUserDataProvider();
        Signup_LoginPage signup= homePage.clickSignup_LoginButton();
        boolean signupStatement =signup.isSignupStatementDisplayed();
        Assert.assertTrue(signupStatement,"Signup statement is not visible");
        signup.enterSignupData(register.getName(),register.getEmailAddress());
        signup.clickSignupButton();
        boolean alreadyRegisteredUserEmailErrorMessage=signup.isAlreadyRegisteredEmailErrorMessageDisplayed();
        Assert.assertTrue(alreadyRegisteredUserEmailErrorMessage,"Error message is not displayed!");
    }
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
