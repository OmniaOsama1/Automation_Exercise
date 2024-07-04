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
import pages.ProductsPage;
import resources.testData.AddToCartTestData;

import java.io.IOException;

public class ProductQuantityTests {
    private WebDriver driver;
    @Test
    public void testProductQuantity() throws IOException, ParseException {
        AddToCartTestData addToCartTestData=new AddToCartTestData(driver);
        addToCartTestData.addToCartDataProvider();
        String productName= addToCartTestData.getFirstProductName();
        HomePage homePage=new HomePage(driver);
        ProductsPage productsPage=homePage.clickProductsPageButton();
        productsPage.clickViewProductButton(productName);
        productsPage.changeProductQuantity("4");
        productsPage.addProductsToCart();
        CartPage cartPage= productsPage.clickViewCartButton();
        Assert.assertTrue(cartPage.validateOnProductQuantity(productName,"4"),"Product quantity is incorrect");
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

