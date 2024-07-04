package loginTests;

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
import resources.testData.ValidLoginData;

import java.io.IOException;
import java.time.Duration;

public class LoginWithValidCredentials {
    private WebDriver driver;
    @Test
    public void loginUserWithValidCredentials() throws IOException, ParseException {
        HomePage homePage = new HomePage(driver);
        Signup_LoginPage login= homePage.clickSignup_LoginButton();
        boolean loginStatement=login.isLoginStatementDisplayed();
        Assert.assertTrue(loginStatement,"Login statement is not visible!");
        ValidLoginData validLoginData =new ValidLoginData(driver);
        validLoginData.validLoginDataProvider();
        login.enterLoginData(validLoginData.getValidLoginEmailAddress(),validLoginData.getValidLoginPassword());
        HomePage homepage= login.clickLoginButton();
        boolean UserName=homepage.isUserNameDisplayed();
        Assert.assertTrue(UserName,"User name is not visible!");
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