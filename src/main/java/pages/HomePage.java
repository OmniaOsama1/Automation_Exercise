package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private WebDriverWait wait;
    private final By homePageIcon=By.cssSelector("[alt='Website for automation practice']");
    private final By signupLink=By.xpath("//a[text()=' Signup / Login']");
    private final By deleteAccountButton=By.cssSelector("a[href='/delete_account']");
    private final By AccountDeletedStatement=By.xpath("//h2/b");
    private final By continueButton =By.cssSelector("[class*= 'btn btn-primary']");
    private final By homePageUserName=By.cssSelector("[class*=fa-user]");
    private final By logoutButton=By.cssSelector("[class*='fa-lock']");
    private final By contactUsLink=By.xpath("//li/a[@href='/contact_us']");
    private final By testCasesLink=By.xpath("//li /a[@href='/test_cases']");
    private final By productsLink=By.cssSelector("li [href='/products']");
    private final By subscriptionText=By.xpath("//h2[text()='Subscription']");
    private final By subscriptionField=By.id("susbscribe_email");
    private final By subscriptionButton=By.cssSelector("[class*=fa-arrow-circle-o-right]");
    private final By subscriptionSuccessMessage=By.cssSelector("[class*=alert-success]");
    private final By cartLink=By.xpath("//li/a[@href='/view_cart']");
    private final By categoriesSideBar =By.xpath("//div [@class='left-sidebar'] /h2[text()='Category']");
    private final By recommendedItems=By.cssSelector(".recommended_items h2");
    private final By addToCartButton=By.xpath("//p/a[@href='/view_cart']");
    private final By moveUpwardButton=By.xpath("//i[@class='fa fa-angle-up']");
    private final By homePageText=By.xpath("//div[@class='item active']/div/h2[text()='Full-Fledged practice website for Automation Engineers']");
    private By getCategoryLocator(String categoryName){
        return By.xpath("//a[@href='#"+categoryName+"']");
    }
    private By getSubCategoryLocator(String categoryName,String subCategoryName){
        return By.xpath("//div[@id='"+categoryName+"']/div/ul/li/a[text()='"+subCategoryName+" ']");
    }
    private By getCategoryPageTitleLocator(String categoryName,String subCategoryName){
        return By.xpath("//h2[@class='title text-center'][text()='"+categoryName+" - "+subCategoryName+" Products']");
    }
    private By getNavigatedCategoryPageLocator(String categoryName,String subCategoryName){
        return By.xpath("//li[@class='active'][text()='"+categoryName+" > "+subCategoryName+"']");
    }
    private By recommendedItems_addToCartButton(String productName){
        return By.xpath("//div [@class='item active']/div/div/div/div/p[text()='"+productName+"'] //following-sibling :: a[@class='btn btn-default add-to-cart']");
    }
    public HomePage(WebDriver driver){
        this.driver=driver;
    }
    public boolean isHomePageIconDisplayed(){
        return driver.findElement(homePageIcon).isDisplayed();
    }
    public Signup_LoginPage clickSignup_LoginButton(){
        driver.findElement(signupLink).click();
        return new Signup_LoginPage(driver);
    }
    public void deleteAccount(){
        driver.findElement(deleteAccountButton).click();
    }
    public boolean isDeleteAccountStatementVisible(){
        return driver.findElement(AccountDeletedStatement).isDisplayed();
    }
    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
    public boolean isUserNameDisplayed(){
        return driver.findElement(homePageUserName).isDisplayed();
    }
    public void logout(){
        driver.findElement(logoutButton).click();
    }
    public ContactUsPage clickContactUsButton(){
        driver.findElement(contactUsLink).click();
        return new ContactUsPage(driver);
    }
    public TestCasesPage clickTestCasesButton(){
        driver.findElement(testCasesLink).click();
        return new TestCasesPage(driver);
    }
    public ProductsPage clickProductsPageButton(){
        driver.findElement(productsLink).click();
        return new ProductsPage(driver);
    }
    public void scrollToSubscriptionField(){
        Actions actions=new Actions(driver);
        actions.scrollToElement(driver.findElement(subscriptionField));
    }
    public void enterSubscriptionEmailAddress(String emailAddress){
        driver.findElement(subscriptionField).click();
        driver.findElement(subscriptionField).sendKeys(emailAddress);
    }
    public void clickSubscriptionArrowButton(){
        driver.findElement(subscriptionButton).click();
    }
    public boolean isSubscriptionSuccessMessageDisplayed(){
        return driver.findElement(subscriptionSuccessMessage).isDisplayed();
    }
    public CartPage clickCartLink(){
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }
    public Boolean isCategoryVisible(){
        return driver.findElement(categoriesSideBar).isDisplayed();
    }
    public void selectingCategoryAndSubcategory(String category,String subcategory){
        wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(getCategoryLocator(category)));
        driver.findElement(getCategoryLocator(category)).click();
        driver.findElement(getSubCategoryLocator(category,subcategory)).click();
    }
    public String getCategoryPageTitle(String category,String subcategory){
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(getCategoryPageTitleLocator(category,subcategory)));
        return driver.findElement(getCategoryPageTitleLocator(category,subcategory)).getText();
    }
    public String getNavigatedCategoryPage(String category,String subcategory){
        return driver.findElement(getNavigatedCategoryPageLocator(category,subcategory)).getText();
    }
    public Boolean isRecommendedItemsVisible(){
        Actions actions=new Actions(driver);
        actions.scrollToElement(driver.findElement(recommendedItems));
        return driver.findElement(recommendedItems).isDisplayed();
    }
    public CartPage addRecommendedItemsToCart(String productName){
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(recommendedItems_addToCartButton(productName)));
        driver.findElement(recommendedItems_addToCartButton(productName)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
        driver.findElement(addToCartButton).click();
        return new CartPage(driver);
    }

    public void scrollDownToPageBottom(){
        Actions actions=new Actions(driver);
        actions.scrollToElement(driver.findElement(subscriptionField)).perform();
    }
    public boolean isSubscriptionTextVisible(){
        return driver.findElement(subscriptionText).isDisplayed();
    }
    public void scrollUpUsingArrowButton(){
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(moveUpwardButton));
        driver.findElement(moveUpwardButton).click();
    }

    public Boolean isHomePageTextVisible(){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(homePageText)));
        return driver.findElement(homePageText).isDisplayed();
    }
    public void scrollUpToPageLogoWithoutButton(){
        Actions actions=new Actions(driver);
        actions.scrollToElement(driver.findElement(homePageIcon));
    }
}
