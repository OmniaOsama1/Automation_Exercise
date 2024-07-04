import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

public class ViewBrandProductsTest {
    private WebDriver driver;

    @Test
    public void verifyProductsBrand(){
        HomePage homePage=new HomePage(driver);
        ProductsPage productsPage=homePage.clickProductsPageButton();
        Assert.assertTrue(productsPage.isBrandsSideBarVisible(),"Brands side bar is invisible!");
        productsPage.selectBrand("Polo");
        Assert.assertTrue(productsPage.verifyNavigatedBrand("Polo"),"Incorrect Navigated Brand!");
        Assert.assertTrue(productsPage.verifyBrandRelatedProducts("Polo"),"Products are not related to the selected brand!");
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
