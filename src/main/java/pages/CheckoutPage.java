package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;
    public CheckoutPage (WebDriver driver){
        this.driver=driver;
    }
    private final By additionalOrderCommentsField =By.className("form-control");
    private final By placeOrderButton=By.cssSelector("div [href='/payment']");
    private final By addressVerification_Name=By.xpath("//ul[@class='address item box'] /li[@class='address_firstname address_lastname']");
    private final By addressVerification_Address=By.xpath("//ul[@class='address item box'] /li[contains(@class,'address_address1')][2]");
    private final By addressVerification_City=By.xpath("//ul[@class='address item box'] /li[contains(@class,'address_city')]");
    private final By addressVerification_Country=By.xpath("//ul[@class='address item box'] /li[@class='address_country_name']");
    private final By addressVerification_PhoneNumber=By.xpath("//ul[@class='address item box'] /li[@class='address_phone']");
    public boolean verifyDeliveryAddressDetails_name(String title,String firstName,String lastName){
        return driver.findElement(addressVerification_Name).getText().equals(title+". "+firstName+" "+lastName);
    }
    public boolean verifyDeliveryAddressDetails_Address(String Address){
        return driver.findElement(addressVerification_Address).getText().equals(Address);
    }
    public boolean verifyDeliveryAddressDetails_CityStateZipcode(String city,String state,String zipcode){
        return driver.findElement(addressVerification_City).getText().equals(city+" "+state+" "+zipcode);
    }
    public boolean verifyDeliveryAddressDetails_Country(String country){
        return driver.findElement(addressVerification_Country).getText().equals(country);
    }
    public boolean verifyDeliveryAddressDetails_PhoneNumber(String phoneNumber){
        return driver.findElement(addressVerification_PhoneNumber).getText().equals(phoneNumber);
    }
    public void enterAdditionalOrderComments(){
        driver.findElement(additionalOrderCommentsField).sendKeys("No Additional Comments");
    }
    public PaymentPage clickPlaceOrderButton(){
        driver.findElement(placeOrderButton).click();
        return new PaymentPage(driver);
    }
}
