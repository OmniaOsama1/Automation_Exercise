package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final By shoppingCartPage=By.xpath("//li[text()='Shopping Cart']");
    private final By checkoutButton=By.cssSelector("[class *=check_out]");
    private final By checkoutLogin_RegisterLink=By.xpath("//u [text()='Register / Login']");
    private By removeProductFromCart(String productName){
        return By.xpath("//h4/a[text()='"+productName+"'] //ancestor:: td[@class='cart_description']//following-sibling:: td[@class='cart_delete']//a[@class='cart_quantity_delete']");
    }
    public CartPage(WebDriver driver){
        this.driver=driver;
    }
    private By cart_productName(String productName){
        return By.xpath("//h4/a[text()='"+productName+"']");
    }
    private By cart_productPrice(String productName){
        return By.xpath("//h4/a[text()='"+ productName +"'] //ancestor:: td[@class='cart_description'] //following-sibling ::td[@class='cart_price'] ");
    }
    private By cart_productQuantity(String productName){
        return By.xpath("//h4/a[text()='"+productName+"'] //ancestor:: td[@class='cart_description'] //following-sibling ::td[@class='cart_quantity']/button[@class='disabled']");
    }
    private By cart_totalPrice(String productName){
        return By.xpath("//h4/a[text()='"+productName+"'] //ancestor:: td[@class='cart_description'] //following-sibling ::td[@class='cart_total']/p[@class='cart_total_price']");
    }
    public boolean validateOnProductName(String productName){
        return driver.findElement(cart_productName(productName)).getText().equals(productName);
    }
    public boolean validateOnProductPrice(String productName,String productPrice){
        return driver.findElement(cart_productPrice(productName)).getText().equals(productPrice);
    }
    public boolean validateOnProductQuantity(String productName,String productQuantity){
        return driver.findElement(cart_productQuantity(productName)).getText().equals(productQuantity);
    }
    public boolean validateOnProductTotalPrice(String productName,String productTotalPrice){
        return driver.findElement(cart_totalPrice(productName)).getText().equals(productTotalPrice);
    }
    public Boolean isCartPageIsDisplayed(){
        return driver.findElement(shoppingCartPage).isDisplayed();
    }
    public CheckoutPage clickCheckoutButton(){
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }
    public void removingProductFromCart(String productName){
        driver.findElement(removeProductFromCart(productName)).click();
    }

    public Boolean isProductRemovedFromCart(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(cart_productName(productName))));
        try {
            return !(driver.findElement(cart_productName(productName)).isDisplayed());
        } catch (NoSuchElementException e) {
            return true;
        }
    }
    public Signup_LoginPage clickRegister_LoginLink(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(checkoutLogin_RegisterLink)));
        driver.findElement(checkoutLogin_RegisterLink).click();
        return new Signup_LoginPage(driver);
    }
}
