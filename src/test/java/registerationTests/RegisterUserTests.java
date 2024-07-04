package registerationTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.Signup_LoginPage;
import resources.testData.RegistrationData;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RegisterUserTests {
    private WebDriver driver;
    private HomePage homePage;
    private RegistrationData registrationData;

    @Test (priority = 1)
    public void verifyHomePageVisibility(){
        homePage=new HomePage(driver);
        boolean homePageIcon =homePage.isHomePageIconDisplayed();
        Assert.assertTrue(homePageIcon);
    }
    @Test(priority = 2)
    public void signupNewUser() throws IOException, ParseException {
        homePage=new HomePage(driver);
        registrationData=new RegistrationData(driver);
        registrationData.registerDataProvider();
        Signup_LoginPage signup=homePage.clickSignup_LoginButton();
        boolean signupStatement =signup.isSignupStatementDisplayed();
        Assert.assertTrue(signupStatement,"Signup statement is not visible");
        signup.enterSignupData(registrationData.getName(),registrationData.getEmailAddress());
        signup.clickSignupButton();
    }
    @Test (priority = 3)
    public void registerUser() throws IOException, ParseException {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        registrationData=new RegistrationData(driver);
        registrationData.registerDataProvider();
        Set<String> allWindowHandles=driver.getWindowHandles();
        Iterator<String> iterate=allWindowHandles.iterator();
        String mainWindow=iterate.next();
        driver.switchTo().window(mainWindow);
        createAccountPage.fillCreateAccountData(registrationData.getGender(),registrationData.getPassword(),registrationData.getFirstName(), registrationData.getLastName(),registrationData.getCompany(),registrationData.getAddress(), registrationData.getAddress2(),registrationData.getCountry(),registrationData.getCity(),registrationData.getState(), registrationData.getZipCode(),registrationData.getMobileNumber());
        createAccountPage.clickCreateAccountButton();
        boolean createAccountMessage= createAccountPage.isCreateAccountMessageVisible();
        Assert.assertTrue(createAccountMessage,"Create account message is not visible");
        createAccountPage.clickContinueButton();
    }
    @Test (priority = 4)
    public void deleteAccount(){
        homePage=new HomePage(driver);
        homePage.deleteAccount();
        boolean deleteAccountStatement=homePage.isDeleteAccountStatementVisible();
        Assert.assertTrue(deleteAccountStatement,"Delete account statement is not visible");
        homePage.clickContinueButton();
    }


    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
