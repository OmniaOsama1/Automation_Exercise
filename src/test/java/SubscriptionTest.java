import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class SubscriptionTest {
    private WebDriver driver;
    @Test
    public void verifyHomePageSubscription(){
        HomePage homePage=new HomePage(driver);
        homePage.scrollToSubscriptionField();
        homePage.enterSubscriptionEmailAddress("test@test.com");
        homePage.clickSubscriptionArrowButton();
        Assert.assertTrue(homePage.isSubscriptionSuccessMessageDisplayed(),"Subscription success message is not visible");
    }
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
