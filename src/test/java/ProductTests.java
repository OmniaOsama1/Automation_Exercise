import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductsPage;
import resources.testData.AddToCartTestData;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ProductTests {
    private WebDriver driver;
    private AddToCartTestData addToCartTestData;
    @Test(priority = 1)
    public void testProductPage() throws IOException, ParseException {
        HomePage homePage=new HomePage(driver);
        ProductsPage productsPage=homePage.clickProductsPageButton();
        Assert.assertEquals(productsPage.getAllProductsPageTitle(),"ALL PRODUCTS","All products page title is incorrect");
        addToCartTestData=new AddToCartTestData(driver);
        addToCartTestData.addToCartDataProvider();
        String firstProductName=addToCartTestData.getFirstProductName();
        productsPage.scrollToFirstProduct(firstProductName);
    }
    @Test(priority = 2)
    public void testProductDetails() throws IOException, ParseException {
        ProductsPage productsPage=new ProductsPage(driver);
        addToCartTestData=new AddToCartTestData(driver);
        addToCartTestData.addToCartDataProvider();
        String firstProductName=addToCartTestData.getFirstProductName();
        ProductDetailsPage productDetails=productsPage.clickViewProductButton(firstProductName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(productDetails.isProductDetailsDisplayed(),"Product details is not visible!");
        Assert.assertTrue(productDetails.isProductNameDisplayed(),"Product name is not visible!");
        Assert.assertTrue(productDetails.isProductCategoryDisplayed(),"Product category is not visible!");
        Assert.assertTrue(productDetails.isProductPriceDisplayed(),"Product price is not visible!");
        Assert.assertTrue(productDetails.isProductAvailabilityDisplayed(),"Product availability is not visible!");
        Assert.assertTrue(productDetails.isProductConditionDisplayed(),"Product condition is not visible!");
        Assert.assertTrue(productDetails.isProductBrandDisplayed(),"Product brand is not visible!");
    }
    @Test
    public void testSearchProductFunction(){

    }
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt=new ChromeOptions();
        opt.addExtensions(new File("src/test/extensions/AdBlock — block ads across the web 6.3.0.0.crx"));
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
//        Set<String> allWindowHandles=driver.getWindowHandles();
//        Iterator<String> iterate=allWindowHandles.iterator();
//        String mainWindow=iterate.next();
//        driver.switchTo().window(mainWindow);
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
