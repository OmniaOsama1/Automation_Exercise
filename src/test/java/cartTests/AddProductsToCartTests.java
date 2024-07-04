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

public class AddProductsToCartTests {
    private WebDriver driver;
    private AddToCartTestData addToCartTestData;
    @Test (priority = 1)
    public void addProductsToCart() throws IOException, ParseException {
        HomePage homePage=new HomePage(driver);
        ProductsPage productsPage= homePage.clickProductsPageButton();
        addToCartTestData = new AddToCartTestData(driver);
        addToCartTestData.addToCartDataProvider();
        productsPage.addProductsToCart(addToCartTestData.getFirstProductName());
        productsPage.clickContinueShoppingButton();
        productsPage.addProductsToCart(addToCartTestData.getSecondProductName());
        productsPage.clickViewCartButton();
    }
    @Test (priority = 2)
    public void validateOnCartAddedFirstProduct() throws IOException, ParseException {
        CartPage cartPage=new CartPage(driver);
        addToCartTestData = new AddToCartTestData(driver);
        addToCartTestData.addToCartDataProvider();
        Assert.assertTrue(cartPage.validateOnProductName(addToCartTestData.getFirstProductName()));
        Assert.assertTrue(cartPage.validateOnProductName(addToCartTestData.getSecondProductName()));
        Assert.assertTrue(cartPage.validateOnProductQuantity(addToCartTestData.getFirstProductName(),addToCartTestData.getFirstProductQuantity()));
        Assert.assertTrue(cartPage.validateOnProductQuantity(addToCartTestData.getSecondProductName(),addToCartTestData.getSecondProductQuantity()));
        Assert.assertTrue(cartPage.validateOnProductPrice(addToCartTestData.getFirstProductName(),addToCartTestData.getFirstProductPrice()));
        Assert.assertTrue(cartPage.validateOnProductPrice(addToCartTestData.getSecondProductName(),addToCartTestData.getSecondProductPrice()));
        Assert.assertTrue(cartPage.validateOnProductTotalPrice(addToCartTestData.getFirstProductName(),addToCartTestData.getFirstProductTotalPrice()));
        Assert.assertTrue(cartPage.validateOnProductTotalPrice(addToCartTestData.getSecondProductName(),addToCartTestData.geSecondProductTotalPrice()));
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
