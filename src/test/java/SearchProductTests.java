import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

import java.time.Duration;


public class SearchProductTests {
    private WebDriver driver;
    @Test
    public void testSearchProduct(){
        HomePage homePage=new HomePage(driver);
        ProductsPage productPage= homePage.clickProductsPageButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        productPage.searchForProduct("Jeans");
        String productDescription= productPage.getProductDescription();
        Assert.assertTrue(productDescription.contains("Jeans"),"Incorrect search results");
        String searchProductsPageTitle= productPage.getSearchedProductTitle();
        Assert.assertEquals(searchProductsPageTitle,"SEARCHED PRODUCTS");
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
