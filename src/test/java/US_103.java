import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseDriver;

import java.time.Duration;

public class US_103 extends BaseDriver {

    US_102 login = new US_102();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void logoutSuccess() {
        login.loginSuccessfully();

        WebElement usernameDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/a/i")));
        usernameDisplay.click();

        WebElement logOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Çık")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logOutButton);

        WebElement loginAgain = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/button")));
        loginAgain.click();

        WebElement akakceText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//b[text()='Akakçe Ayrıcalıkları Sizi Bekliyor!'])")));
        String actualText = akakceText.getText();
        Assert.assertTrue(actualText.contains("Akakçe Ayrıcalıkları Sizi Bekliyor!"));

        tearDown();
    }
}
