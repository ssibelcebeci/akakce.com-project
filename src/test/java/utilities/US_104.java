package utilities;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class US_104 extends BaseDriver{
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    String email = "ytmsc1604@gmail.com";
    String password = "Sifretest1$";

    @Test
    public void successfulLogin() {
        driver.get("https://www.akakce.com/");

        WebElement signIn = driver.findElement(By.xpath("(//a[text()='Giriş Yap'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(signIn));
        signIn.click();

        WebElement loginPanelText = driver.findElement(By.cssSelector("div > b"));
        wait.until(ExpectedConditions.visibilityOf(loginPanelText));

        WebElement emailText = driver.findElement(By.xpath("//label[text()='E-posta']"));
        wait.until(ExpectedConditions.visibilityOf(emailText));

        ReusableMethods.threadWait(2);
        WebElement inputEmail = driver.findElement(By.xpath("//input[@type='email']"));
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.sendKeys(email);

        WebElement updateBtn = driver.findElement(By.cssSelector("button > b"));
        wait.until(ExpectedConditions.elementToBeClickable(updateBtn));
        updateBtn.click();

        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//b[text()='Giriş Yap']"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();

        WebElement myAccountText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div > h1")));
        wait.until(ExpectedConditions.visibilityOf(myAccountText));
        Assert.assertTrue("'Akakçe ile En Ucuzu Keşfet!' text not visible", myAccountText.isDisplayed());

        tearDown();
    }
}
