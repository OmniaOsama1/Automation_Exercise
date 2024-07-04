import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class ScrollUpAndScrollDownTests {
    private WebDriver driver;
    private HomePage homePage;
    @Test (priority =1)
    public void verifyScrollUpUsingArrowButtonAndScrollDownFunctionality(){
        homePage=new HomePage(driver);
        homePage.scrollDownToPageBottom();
        Assert.assertTrue(homePage.isSubscriptionTextVisible(),"Subscription Text is not visible!");
        homePage.scrollUpUsingArrowButton();
        Assert.assertTrue(homePage.isHomePageTextVisible(),"HomePage Text is not visible!");
    }
    @Test (priority =2)
    public void VerifyScrollUpWithoutArrowButtonAndScrollDownFunctionality(){
        homePage=new HomePage(driver);
        homePage.scrollDownToPageBottom();
        Assert.assertTrue(homePage.isSubscriptionTextVisible(),"Subscription Text is not visible!");
        homePage.scrollUpToPageLogoWithoutButton();
        Assert.assertTrue(homePage.isHomePageTextVisible(),"HomePage Text is not visible!");
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
