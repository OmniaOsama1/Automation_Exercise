import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductsPage;
import resources.testData.AddReviewTestData;

import java.io.IOException;

public class AddReviewOnProductTest {
    private WebDriver driver;
    @Test
    public void addReviewOnProduct() throws IOException, ParseException {
        HomePage homePage=new HomePage(driver);
        ProductsPage productsPage= homePage.clickProductsPageButton();
        AddReviewTestData addReviewTestData=new AddReviewTestData(driver);
        addReviewTestData.getAddReviewDataProvider();
        ProductDetailsPage productDetailsPage=productsPage.clickViewProductButton(addReviewTestData.getProductName());
        productDetailsPage.submitReview(addReviewTestData.getName(),addReviewTestData.getEmailAddress(),addReviewTestData.getReviewComment());
        Assert.assertTrue(productDetailsPage.verifyAddingReviewSuccessMessage(addReviewTestData.getReviewSuccessMessage()));
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
