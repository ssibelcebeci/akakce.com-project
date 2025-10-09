import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseDriver;
import utilities.ReusableMethods;

import java.time.Duration;

public class TC_0201 extends BaseDriver {

    String email = "sibel.testedici@gmail.com";
    String password = "Kayseri123";
    String username = "Sibel";

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void loginSuccessfully() {
        driver.get("https://www.akakce.com/");

        WebElement loginBtn = driver.findElement(By.xpath("(//a[text()='Giriş Yap'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();

        ReusableMethods.threadWait(3);
        WebElement emailInput = driver.findElement(By.xpath("//div/input[2]"));
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(email);

        WebElement continueButton = driver.findElement(By.xpath("//b[text()='Devam Et']"));
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();

        ReusableMethods.threadWait(2);
        WebElement passwordInput = driver.findElement(By.xpath("(//div/input)[3]"));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//b[text()='Giriş Yap']"));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();

        WebElement usernameDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/a/i")));
        String actualUsername = usernameDisplay.getText();
        Assert.assertTrue(actualUsername.contains(username));
    }
}