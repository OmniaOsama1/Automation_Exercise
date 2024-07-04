import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import resources.testData.AddToCartTestData;
import resources.testData.PaymentData;
import resources.testData.RegistrationData;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DownloadInvoiceAfterOrderPurchase {

    private WebDriver driver;
    private HomePage homePage;
    private CartPage cartPage;
    private AddToCartTestData addToCartTestData;
    private RegistrationData registrationData;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    @Test(priority = 1)
    public void addProductToCart() throws IOException, ParseException {
        homePage=new HomePage(driver);
        ProductsPage productsPage= homePage.clickProductsPageButton();
        addToCartTestData = new AddToCartTestData(driver);
        addToCartTestData.addToCartDataProvider();
        productsPage.addProductsToCart(addToCartTestData.getFirstProductName());
        cartPage= productsPage.clickViewCartButton();
        Assert.assertTrue(cartPage.isCartPageIsDisplayed(),"Cart page is not displayed");
    }
    @Test (priority = 2)
    public void signupBeforeCheckout() throws IOException, ParseException {
        cartPage=new CartPage(driver);
        cartPage.clickCheckoutButton();
        Signup_LoginPage signupLoginPage = cartPage.clickRegister_LoginLink();
        registrationData=new RegistrationData(driver);
        registrationData.registerDataProvider();
        signupLoginPage.enterSignupData(registrationData.getName(),registrationData.getEmailAddress());
        CreateAccountPage createAccountPage =signupLoginPage.clickSignupButton();
        createAccountPage.fillCreateAccountData(registrationData.getGender(),registrationData.getPassword(),registrationData.getFirstName(),registrationData.getLastName(), registrationData.getCompany(),registrationData.getAddress(),registrationData.getAddress2(),registrationData.getCountry(),registrationData.getState(), registrationData.getCity(),registrationData.getZipCode(),registrationData.getMobileNumber());
        createAccountPage.clickCreateAccountButton();
        Assert.assertTrue(createAccountPage.isCreateAccountMessageVisible(),"Create account message is not visible!");
        createAccountPage.clickContinueButton();
        Assert.assertTrue(homePage.isUserNameDisplayed(),"User name is not visible");
    }
    @Test (priority = 3)
    public void verifyAddressDetails() throws IOException, ParseException {
        cartPage=homePage.clickCartLink();
        checkoutPage=cartPage.clickCheckoutButton();
        registrationData=new RegistrationData(driver);
        registrationData.registerDataProvider();
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressDetails_name(registrationData.getGender(),registrationData.getFirstName(),registrationData.getLastName()),"Delivery Address Name is incorrect!");
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressDetails_Address(registrationData.getAddress()),"Delivery Address  is incorrect!");
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressDetails_CityStateZipcode(registrationData.getCity(),registrationData.getState(),registrationData.getZipCode()),"Delivery Address Details are incorrect!");
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressDetails_Country(registrationData.getCountry()),"Delivery Country is incorrect!");
        Assert.assertTrue(checkoutPage.verifyDeliveryAddressDetails_PhoneNumber(registrationData.getMobileNumber()),"Phone Number is incorrect!");
    }
    @Test (priority = 4)
    public void reviewOrderWhileCheckOut() throws IOException, ParseException {
        cartPage=new CartPage(driver);
        addToCartTestData = new AddToCartTestData(driver);
        addToCartTestData.addToCartDataProvider();
        Assert.assertTrue(cartPage.validateOnProductName(addToCartTestData.getFirstProductName()));
        Assert.assertTrue(cartPage.validateOnProductPrice(addToCartTestData.getFirstProductName(),addToCartTestData.getFirstProductPrice()));
        Assert.assertTrue(cartPage.validateOnProductQuantity(addToCartTestData.getFirstProductName(),addToCartTestData.getFirstProductQuantity()));
        Assert.assertTrue(cartPage.validateOnProductTotalPrice(addToCartTestData.getFirstProductName(),addToCartTestData.getFirstProductTotalPrice()));
    }
    @Test (priority = 5)
    public void placeOrderAndPay() throws IOException, ParseException {
        checkoutPage=new CheckoutPage(driver);
        checkoutPage.enterAdditionalOrderComments();
        paymentPage=checkoutPage.clickPlaceOrderButton();
        PaymentData paymentData=new PaymentData(driver);
        paymentData.paymentDataProvider();
        paymentPage.enterPaymentDetails(paymentData.getNameOnCard(),paymentData.getCardNumber(),paymentData.getCVV(),paymentData.getExpirationMonth(),paymentData.getExpirationYear());
        paymentPage.clickPayAndConfirmOrderButton();
        //Assert.assertTrue(paymentPage.isOrderSuccessMessageVisible(),"Order success message is not visible");
    }
    @Test(priority = 6)
    public void downloadInvoiceAndContinueToHomePage(){
        paymentPage =new PaymentPage(driver);
        paymentPage.downloadInvoiceandAndContinueToHomePage();
    }
    @Test (priority = 7)
    public void deleteAccount(){
        homePage=new HomePage(driver);
        homePage.deleteAccount();
        boolean deleteAccountStatement=homePage.isDeleteAccountStatementVisible();
        Assert.assertTrue(deleteAccountStatement,"Delete account statement is not visible");
        homePage.clickContinueButton();
    }

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
