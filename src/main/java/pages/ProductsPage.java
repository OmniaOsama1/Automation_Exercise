package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private final WebDriver driver;
    public ProductsPage(WebDriver driver){
        this.driver=driver;
    }
    //private final By closeAdButton=By.id("dismiss-button");
    private final By allProductsPage= By.cssSelector(".features_items >h2");
    private final By productSearchBar=By.id("search_product");
    private final By searchButton=By.id("submit_search");
    private final By productDescription=By.cssSelector("[class*=productinfo] p");
    private final By searchedProductPageTitle=By.xpath("//h2[contains(.,'Searched Products')]");
    private final By productQuantity=By.id("quantity");
    private final By addToCartButton=By.cssSelector("button[class*='cart']");
    private final By brandsSideBar=By.cssSelector(".brands_products h2");
    private final By productDetailsButton=By.cssSelector(".choose");
   private By ViewProductButton(String productName){
        return By.xpath("//p[text()='"+productName+"'] //ancestor :: div[@class='single-products']//following-sibling :: div[@class='choose']/ul/li/a");
   }
   private By productCard(String productName){
       return By.xpath("//p[text()='"+productName+"']//ancestor ::div [contains(@class,'productinfo')]//following-sibling ::div [@class='product-overlay']");
   }
    private By addProductToCart(String productName){
        return By.xpath("//p [text()='"+ productName +"']//ancestor:: div[contains(@class,'productinfo')] //following-sibling :: a[contains(@class,'add-to-cart')]");
    }
    private By getBrandName(String brandName){
       return By.xpath("//li/a[text()='"+brandName+"']");
    }
    private By getTheNavigatedBrand(String brandName){
       return By.xpath("//li[@class='active'] [text()='"+brandName+"']");
    }
    private By getBrandFromProductDetails(String brandName){
       return By.xpath("//p[text()=' "+brandName+"']");
    }
    private final By continueShoppingButton =By.cssSelector("div [class*='btn-block']");
    private final By viewCartLink=By.cssSelector("p [href='/view_cart']");

    public String getAllProductsPageTitle(){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(allProductsPage));
        return driver.findElement(allProductsPage).getText();
    }
    public void scrollToFirstProduct(String productName){
        Actions actions=new Actions(driver);
        actions.scrollToElement(driver.findElement(ViewProductButton(productName)));
    }

    public ProductDetailsPage clickViewProductButton(String productName){
        driver.findElement(ViewProductButton(productName)).click();
        return new ProductDetailsPage(driver);
    }
    public void searchForProduct(String searchKeyword){
        driver.findElement(productSearchBar).sendKeys(searchKeyword);
        driver.findElement(searchButton).click();
    }
    public String getProductDescription(){
        return driver.findElement(productDescription).getText();
    }
    public String getSearchedProductTitle(){
        return driver.findElement(searchedProductPageTitle).getText();
    }
    public void addProductsToCart(String productName){
        Actions actions=new Actions(driver);
        actions.scrollToElement(driver.findElement(productCard(productName))).moveToElement(driver.findElement(productCard(productName))).perform();
        driver.findElement(addProductToCart(productName)).click();
    }
    public void clickContinueShoppingButton(){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        driver.findElement(continueShoppingButton).click();
    }

    public CartPage clickViewCartButton(){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink));
        driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }
    public void changeProductQuantity(String quantity){
        driver.findElement(productQuantity).clear();
        driver.findElement(productQuantity).sendKeys(quantity);
    }
    public void addProductsToCart(){
        driver.findElement(addToCartButton).click();
    }
    public Boolean isBrandsSideBarVisible(){
        return driver.findElement(brandsSideBar).isDisplayed();
    }
    public void selectBrand(String brandName){
        Actions actions=new Actions(driver);
        actions.scrollToElement(driver.findElement(getBrandName(brandName)));
        driver.findElement(getBrandName(brandName)).click();
    }
    public Boolean verifyNavigatedBrand(String brandName){
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(getTheNavigatedBrand(brandName)));
        return driver.findElement(getTheNavigatedBrand(brandName)).getText().equals(brandName);
    }
    public Boolean verifyBrandRelatedProducts(String brandName) {
        List<WebElement> viewProductsDetailsButton = driver.findElements(productDetailsButton);
        boolean isProductBrandRelated = false;
        for (WebElement productDetails : viewProductsDetailsButton) {
            productDetails.click();
            isProductBrandRelated = driver.findElement(getBrandFromProductDetails(brandName)).getText().contains(brandName);
            driver.navigate().back();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        return isProductBrandRelated;
    }


}
