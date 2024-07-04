package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HandleGoogleAlerts {
    private WebDriver driver;
    private WebDriverWait wait;

    public HandleGoogleAlerts(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void closeGoogleAds() {
        // Check if any iframes might contain ads
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        for (WebElement iframe : iframes) {
            try {
                // Switch to the iframe
                driver.switchTo().frame(iframe);

                // Try to find the close button inside the iframe
                WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("[aria-label='Close ad']")));
                closeButton.click();
                //System.out.println("Ad closed successfully.");

                // Switch back to the main content
                driver.switchTo().defaultContent();
                return; // Exit after closing the ad
            } catch (Exception e) {
                // If no close button found or any error occurs, switch back to main content and continue
                driver.switchTo().defaultContent();
            }
        }
    }
}
