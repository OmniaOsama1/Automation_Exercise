import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import resources.testData.ContactUsData;
import java.io.IOException;

public class ContactUsTest {
    private WebDriver driver;

    @Test
    public void contactUs() throws IOException, ParseException {
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage= homePage.clickContactUsButton();
        boolean isGetInTouchStatementVisible=contactUsPage.isGetInTouchStatementDisplayed();
        Assert.assertTrue(isGetInTouchStatementVisible,"Get in touch statement is not visible");
        ContactUsData contactUsData=new ContactUsData(driver);
        contactUsData.contactUsDataProvider();
        contactUsPage.fillContactUsFields(contactUsData.getName(),contactUsData.getEmailAddress(),contactUsData.getSubject(),contactUsData.getMessage()
        ,"C:\\Users\\omnia\\IdeaProjects\\untitled1\\src\\test\\java\\resources\\FilesToBeUploaded\\file.docx");
        contactUsPage.clickSubmitButton();
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
