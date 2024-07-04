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
import resources.testData.ProductsCategoryAndSubcategoryTestData;
import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class ViewCategoryProductsTests {
    private WebDriver driver;
    private HomePage homePage;
    private ProductsCategoryAndSubcategoryTestData productCategoryTestData;
    @Test
    public void viewCategoryProductTest() throws IOException, ParseException {
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isCategoryVisible(), "Category side bar are not Visible!");
        productCategoryTestData = new ProductsCategoryAndSubcategoryTestData(driver);
        productCategoryTestData.getProductCategoryAndSubcategoryDataProvider();
        homePage.selectingCategoryAndSubcategory(productCategoryTestData.getWomenCategory(), productCategoryTestData.getWomenSubCategory());
        Assert.assertEquals(homePage.getCategoryPageTitle(productCategoryTestData.getWomenCategory(), productCategoryTestData.getWomenSubCategory()), productCategoryTestData.getWomanCategoryPageTitle());
    }
    @Test
    public void verifyNavigatedCategory() throws IOException, ParseException {
        homePage=new HomePage(driver);
        productCategoryTestData=new ProductsCategoryAndSubcategoryTestData(driver);
        productCategoryTestData.getProductCategoryAndSubcategoryDataProvider();
        homePage.selectingCategoryAndSubcategory(productCategoryTestData.getMenCategory(),productCategoryTestData.getMenSubCategory());
        Assert.assertEquals(homePage.getNavigatedCategoryPage(productCategoryTestData.getMenCategory(),productCategoryTestData.getMenSubCategory()),productCategoryTestData.getNavigatedManCategoryPage());

    }

    @BeforeClass
    public void setup(){
        ChromeOptions opt=new ChromeOptions();
        opt.addExtensions(new File("src/test/extensions/AdBlock — block ads across the web 6.3.0.0.crx"));
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
//        Set<String> allWindowHandles=driver.getWindowHandles();
//        Iterator<String> iterate=allWindowHandles.iterator();
//        String mainWindow=iterate.next();
//        driver.switchTo().window(mainWindow);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
