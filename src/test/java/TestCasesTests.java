import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.TestCasesPage;

import java.time.Duration;

public class TestCasesTests {
    private WebDriver driver;

    @Test
    public void testTestCases(){
        HomePage homePage = new HomePage(driver);
        TestCasesPage testCases= homePage.clickTestCasesButton();
        boolean isTestCasesStatementVisible=testCases.isTestCasesStatementDisplayed();
        Assert.assertTrue(isTestCasesStatementVisible,"Test cases statement is not visible");
    }
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}

