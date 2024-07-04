package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {
    private final WebDriver driver;
    public ProductDetailsPage(WebDriver driver){
        this.driver=driver;
    }
    private final By productDetails=By.className("product-information");
    private final By productName=By.xpath("//div[@class='product-information']/h2");
    private final By productCategory=By.xpath("//p[contains(text(),'Category:')]");
    private final By productPrice=By.xpath("//span[contains(text(),'Rs')]");
    private final By productAvailability=By.xpath("//p/b[contains(text(),'Availability')]");
    private final By productCondition=By.xpath("//p/b[contains(text(),'Condition')]");
    private final By productBrand=By.xpath("//p/b[contains(text(),'Brand')]");
    private final By review_nameField=By.id("name");
    private final By review_emailField=By.id("email");
    private final By review_commentField=By.id("review");
    private final By review_submitButton=By.id("button-review");
    private final By review_successMessage=By.xpath("//span[text()='Thank you for your review.']");

    public boolean isProductDetailsDisplayed(){
        return driver.findElement(productDetails).isDisplayed();
    }
    public boolean isProductNameDisplayed(){
        return driver.findElement(productName).isDisplayed();
    }
    public boolean isProductCategoryDisplayed(){
        return driver.findElement(productCategory).isDisplayed();
    }
    public boolean isProductPriceDisplayed(){
        return driver.findElement(productPrice).isDisplayed();
    }
    public boolean isProductAvailabilityDisplayed(){
        return driver.findElement(productAvailability).isDisplayed();
    }
    public boolean isProductConditionDisplayed(){
        return driver.findElement(productCondition).isDisplayed();
    }
    public boolean isProductBrandDisplayed(){
        return driver.findElement(productBrand).isDisplayed();
    }
    public void submitReview(String name,String emailAddress,String reviewComment){
        driver.findElement(review_nameField).sendKeys(name);
        driver.findElement(review_emailField).sendKeys(emailAddress);
        driver.findElement(review_commentField).sendKeys(reviewComment);
        driver.findElement(review_submitButton).click();
        driver.findElement(review_successMessage).getText();
    }
    public Boolean verifyAddingReviewSuccessMessage(String reviewSuccessMessage){
        return driver.findElement(review_successMessage).getText().equals(reviewSuccessMessage);
    }
}
