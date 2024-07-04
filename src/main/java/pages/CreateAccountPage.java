package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage {
    private final WebDriver driver;
    private final By password=By.id("password");
    private final By dateOfBirth_Day=By.id("days");
    private final By dateOfBirth_Month=By.id("months");
    private final By dateOfBirth_Year=By.id("years");
    private final By newsLetter=By.id("newsletter");
    private final By specialOffers=By.id("optin");
    private final By firstName=By.id("first_name");
    private final By lastName=By.id("last_name");
    private final By companyName=By.id("company");
    private final By address1=By.id("address1");
    private final By address2=By.id("address2");
    private final By country=By.id("country");
    private final By state=By.id("state");
    private final By city=By.id("city");
    private final By zipcode=By.id("zipcode");
    private final By mobileNumber=By.id("mobile_number");
    private final By createAccountButton=By.cssSelector("[data-qa='create-account']");
    private final By createdAccountMessage=By.cssSelector("[class*= 'title text-center']");
    private final By continueButton=By.cssSelector("[class*= 'btn btn-primary']");
    private By gender(String gender){
        return By.xpath("//input [@value='"+gender+"']");
    }

    public CreateAccountPage(WebDriver driver){
        this.driver=driver;
    }
    public Select select(WebElement webElement){
        return new Select(webElement);
    }
    public void fillCreateAccountData(String gender,String Password,String FirstName,String LastName,String CompanyName,
                              String Address1,String Address2,String Country,String State,String City,String Zipcode,
                              String MobileNumber){
        driver.findElement(gender(gender)).click();
        driver.findElement(password).sendKeys(Password);
        select(driver.findElement(dateOfBirth_Day)).selectByVisibleText("11");
        select(driver.findElement(dateOfBirth_Month)).selectByVisibleText("September");
        select(driver.findElement(dateOfBirth_Year)).selectByVisibleText("1995");
        driver.findElement(newsLetter).click();
        driver.findElement(specialOffers).click();
        driver.findElement(firstName).sendKeys(FirstName);
        driver.findElement(lastName).sendKeys(LastName);
        driver.findElement(companyName).sendKeys(CompanyName);
        driver.findElement(address1).sendKeys(Address1);
        driver.findElement(address2).sendKeys(Address2);
        select(driver.findElement(country)).selectByVisibleText(Country);
        driver.findElement(state).sendKeys(State);
        driver.findElement(city).sendKeys(City);
        driver.findElement(zipcode).sendKeys(Zipcode);
        driver.findElement(mobileNumber).sendKeys(MobileNumber);
    }
    public void clickCreateAccountButton(){
        driver.findElement(createAccountButton).click();
    }
    public boolean isCreateAccountMessageVisible(){
        return driver.findElement(createdAccountMessage).isDisplayed();
    }
    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }

}
