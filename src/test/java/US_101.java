
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseDriver;
import utilities.ReusableMethods;

import java.time.Duration;
public class US_101 extends BaseDriver {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

    @Test
    public void positiveLogin() {
        String email = "testafn0@gmail.com";

        driver.get("https://www.akakce.com/");

        WebElement loginBtn = driver.findElement(By.xpath("//a[@href='/hesabim/giris/']"));
        loginBtn.click();
        ReusableMethods.threadWait(3);

        WebElement inputEmail = driver.findElement(By.xpath("//input[@type='email']"));
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.sendKeys(email);
        ReusableMethods.threadWait(2);

        WebElement clickBtn = driver.findElement(By.cssSelector("button>b"));
        wait.until(ExpectedConditions.elementToBeClickable(clickBtn));
        clickBtn.click();
        ReusableMethods.threadWait(3);

        WebElement inputName = driver.findElement(By.xpath("//input[@id='name']"));
        wait.until(ExpectedConditions.visibilityOf(inputName));
        inputName.sendKeys("testercan");

        WebElement inputSecondName = driver.findElement(By.xpath("//input[@id='surname']"));
        wait.until(ExpectedConditions.visibilityOf(inputSecondName));
        inputSecondName.sendKeys("testerogullari");

        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys("Tester123");

        WebElement checkBox = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(checkBox));
        checkBox.click();

        WebElement createAccount = driver.findElement(By.xpath("//button/b"));
        wait.until(ExpectedConditions.elementToBeClickable(createAccount));
        createAccount.click();

        WebElement emailLogin = driver.findElement(By.xpath("//p[@class='email']"));
        String stremailLogin = emailLogin.getText();

        Assert.assertEquals(email, stremailLogin);
    }

    @Test
    public void negativeLogin() {

        String email = "testafn0@gmail.com";

        driver.get("https://www.akakce.com/");

        ReusableMethods.threadWait(2);

        WebElement loginBtn = driver.findElement(By.xpath("//a[@href='/hesabim/giris/']"));
        loginBtn.click();
        ReusableMethods.threadWait(3);

        WebElement inputEmail = driver.findElement(By.xpath("//input[@type='email']"));
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.sendKeys(email);
        ReusableMethods.threadWait(2);

        WebElement clickBtn = driver.findElement(By.cssSelector("button > b"));
        wait.until(ExpectedConditions.elementToBeClickable(clickBtn));
        clickBtn.click();
        ReusableMethods.threadWait(3);

        WebElement inputName = driver.findElement(By.xpath("//input[@id='name']"));
        wait.until(ExpectedConditions.visibilityOf(inputName));
        inputName.sendKeys(".");

        WebElement inputSecondName = driver.findElement(By.xpath("//input[@id='surname']"));
        wait.until(ExpectedConditions.visibilityOf(inputSecondName));
        inputSecondName.sendKeys(".");

        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys("<");

        WebElement checkBox = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(checkBox));
        checkBox.click();

        WebElement errorName = driver.findElement(By.xpath("//i[@class='c']"));
        wait.until(ExpectedConditions.visibilityOf(errorName));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String content = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');",
                errorName);
        if (content != null) {
            content = content.replaceAll("^\"|\"$", "");
        }
        System.out.println("Hata mesajı: " + content);
        Assert.assertEquals("Lütfen adınızı yazın.", content);

        WebElement createAccount = driver.findElement(By.xpath("button > b"));
        wait.until(ExpectedConditions.elementToBeClickable(createAccount));
        createAccount.click();
    }
}
