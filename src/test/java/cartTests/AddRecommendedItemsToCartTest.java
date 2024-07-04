package cartTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import resources.testData.AddToCartTestData;

import java.io.IOException;
import java.time.Duration;

public class AddRecommendedItemsToCartTest {
    private WebDriver driver;
    @Test
    public void addRecommendedProductsToCart() throws IOException, ParseException {
        HomePage homePage=new HomePage(driver);
        Assert.assertTrue(homePage.isRecommendedItemsVisible(),"Recommended items are not visible!");
        AddToCartTestData addToCartTestData=new AddToCartTestData(driver);
        addToCartTestData.addToCartDataProvider();
        CartPage cartPage = homePage.addRecommendedItemsToCart(addToCartTestData.getFirstProductName());
        Assert.assertTrue(cartPage.validateOnProductName(addToCartTestData.getFirstProductName()),"Product is not available in the cart!");

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
