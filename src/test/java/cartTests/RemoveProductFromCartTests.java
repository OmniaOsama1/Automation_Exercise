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

public class RemoveProductFromCartTests {
    private WebDriver driver;
    @Test
    public void testRemovingProductFromCart() throws IOException, ParseException {
        HomePage homePage=new HomePage(driver);
        ProductsPage productsPage=homePage.clickProductsPageButton();
        AddToCartTestData addToCartTestData=new AddToCartTestData(driver);
        addToCartTestData.addToCartDataProvider();
        productsPage.addProductsToCart(addToCartTestData.getFirstProductName());
        productsPage.clickContinueShoppingButton();
        productsPage.addProductsToCart(addToCartTestData.getSecondProductName());
        CartPage cartPage=productsPage.clickViewCartButton();
        cartPage.removingProductFromCart(addToCartTestData.getFirstProductName());
        Assert.assertTrue(cartPage.isProductRemovedFromCart(addToCartTestData.getFirstProductName()));
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
