package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Signup_LoginPage {
    private final WebDriver driver;
    private final By signupStatement=By.cssSelector("[class='signup-form'] h2");
    private final By signup_NameField=By.cssSelector(".signup-form [data-qa='signup-name']");
    private final By signup_EmailField=By.cssSelector(".signup-form [data-qa='signup-email']");
    private final By signupButton=By.cssSelector(".signup-form [data-qa='signup-button']");
    private final By login_EmailField= By.cssSelector("[data-qa='login-email']");
    private final By login_PasswordField=By.cssSelector("[data-qa='login-password']");
    private final By loginButton=By.cssSelector("[data-qa='login-button']");
    private final By loginStatement=By.cssSelector("[class='login-form'] h2");
    private final By invalidLoginCredentialsError=By.xpath("//form/p [contains(., 'incorrect')]");
    private final By alreadyExistingEmailAdressError=By.xpath("//form/p [contains(., 'already exist')]");
    public Signup_LoginPage( WebDriver driver){
        this.driver=driver;
    }
    public boolean isSignupStatementDisplayed(){
        return driver.findElement(signupStatement).isDisplayed();
    }
    public void enterSignupData(String signupName, String signupEmail){
        driver.findElement(signup_NameField).sendKeys(signupName);
        driver.findElement(signup_EmailField).sendKeys(signupEmail);
    }
    public CreateAccountPage clickSignupButton(){
        driver.findElement(signupButton).click();
        return new CreateAccountPage(driver);
    }
    public boolean isLoginStatementDisplayed(){
        return driver.findElement(loginStatement).isDisplayed();
    }
    public void enterLoginData(String email,String password){
        driver.findElement(login_EmailField).sendKeys(email);
        driver.findElement(login_PasswordField).sendKeys(password);
    }
    public boolean isErrorMessageDisplayed(){
        return driver.findElement(invalidLoginCredentialsError).isDisplayed();
    }
    public boolean isAlreadyRegisteredEmailErrorMessageDisplayed(){
        return driver.findElement(alreadyExistingEmailAdressError).isDisplayed();
    }

    public HomePage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }
}
