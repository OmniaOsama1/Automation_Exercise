package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    private final WebDriver driver;
    public PaymentPage(WebDriver driver){
        this.driver=driver;
    }
    private final By cardNameField=By.cssSelector("[name='name_on_card']");
    private final By cardNumberField=By.cssSelector("[name='card_number']");
    private final By cardCVVField=By.cssSelector("[name='cvc']");
    private final By cardExpirationMonthField=By.cssSelector("[name='expiry_month']");
    private final By cardExpirationYearField= By.cssSelector("[name='expiry_year']");
    private final By payAndConfirmOrderButton=By.cssSelector("[class='form-control btn btn-primary submit-button']");
    private final By orderSuccessMessage=By.id("success_message");
    private final By downloadInvoiceButton=By.xpath("//a[text()='Download Invoice']");
    private final By continueButton=By.xpath("//a[@data-qa='continue-button']");
    public void enterPaymentDetails(String nameOnCard,String cardNumber,String CVV,String cardExpirationMonth,String cardExpirationYear){
        driver.findElement(cardNameField).sendKeys(nameOnCard);
        driver.findElement(cardNumberField).sendKeys(cardNumber);
        driver.findElement(cardCVVField).sendKeys(CVV);
        driver.findElement(cardExpirationMonthField).sendKeys(cardExpirationMonth);
        driver.findElement(cardExpirationYearField).sendKeys(cardExpirationYear);
    }
    public void clickPayAndConfirmOrderButton(){
        driver.findElement(payAndConfirmOrderButton).click();
    }
    public Boolean isOrderSuccessMessageVisible(){
        return driver.findElement(orderSuccessMessage).isDisplayed();
    }
    public HomePage downloadInvoiceandAndContinueToHomePage(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(downloadInvoiceButton));
        driver.findElement(downloadInvoiceButton).click();
        driver.findElement(continueButton).click();
        return new HomePage(driver);
    }
}
