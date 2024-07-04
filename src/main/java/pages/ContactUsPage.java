package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {
    private final WebDriver driver;
    public ContactUsPage(WebDriver driver){
        this.driver=driver;
    }
    private final By getInTouchStatement=By.xpath("//div /h2 [contains(text(),'Get In Touch')]");
    private final By nameField=By.name("name");
    private final By emailAddressField=By.name("email");
    private final By subjectField=By.name("subject");
    private final By MessageField=By.id("message");
    private final By uploadFileButton=By.cssSelector("input[name='upload_file']");
    private final By submitButton=By.name("submit");

    public boolean isGetInTouchStatementDisplayed(){
        return driver.findElement(getInTouchStatement).isDisplayed();
    }
    public void fillContactUsFields(String name,String emailAddress,String subject,String message,String filePath){
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailAddressField).sendKeys(emailAddress);
        driver.findElement(subjectField).sendKeys(subject);
        driver.findElement(MessageField).sendKeys(message);
        driver.findElement(uploadFileButton).sendKeys(filePath);
    }
    public void clickSubmitButton(){
        driver.findElement(submitButton).click();
    }
}
