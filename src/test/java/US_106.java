import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseDriver;
import utilities.ReusableMethods;

import java.time.Duration;

public class US_106 extends BaseDriver {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    String email = "ahmetefe5531@gmail.com";
    String sifre = "dante1453A1";

    @Test
    public void positiveMessage() {
        driver.get("https://www.akakce.com/");

        WebElement loginBtn = driver.findElement(By.xpath("(//a[text()='Giriş Yap'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();

        ReusableMethods.threadWait(3);

        WebElement emailInput = driver.findElement(By.xpath("//input[@type='email']"));
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailInput.click();
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(email);

        WebElement continueBtn = driver.findElement(By.xpath("//div[@class='bc']/button"));
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        continueBtn.click();

        ReusableMethods.threadWait(3);

        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.click();
        password.sendKeys(sifre);

        WebElement login = driver.findElement(By.xpath("//div[@class='bc']/button"));
        wait.until(ExpectedConditions.elementToBeClickable(login));
        login.click();

        WebElement account = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Hesabım']")));
        wait.until(ExpectedConditions.elementToBeClickable(account));
        account.click();

        WebElement contactUs = driver.findElement(By.xpath("//div[@id='AP']/div[3]/div/a[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(contactUs));
        contactUs.click();

        WebElement contactUsText = driver.findElement(By.xpath("//h1"));

        String expected = "Bize Ulaşın";
        String actual = contactUsText.getText();

        Assert.assertEquals("Bize Ulaşın sayfası açılmadı!", expected, actual);

        tearDown();

    }
}
