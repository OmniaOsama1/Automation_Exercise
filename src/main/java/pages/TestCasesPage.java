package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage {
    private final WebDriver driver;
    public TestCasesPage(WebDriver driver){
        this.driver=driver;
    }
    private final By testCasesStatement=By.cssSelector("h2 >b");

    public boolean isTestCasesStatementDisplayed(){
        return driver.findElement(testCasesStatement).isDisplayed();
    }
}
