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

public class US_104 extends BaseDriver {
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    String email = "ytmsc1604@gmail.com";
    String password = "Sifretest1$";
    String invalidPassword = "InvalidPassword";
    String invalidEmail = "invalid@invalid.com";

    @Test
    public void positiveLogin() {
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

        WebElement myAccountBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Hesabım']")));
        wait.until(ExpectedConditions.elementToBeClickable(myAccountBtn));
        myAccountBtn.click();

        WebElement myAccountText = driver.findElement(By.cssSelector("body > h1"));
        wait.until(ExpectedConditions.visibilityOf(myAccountText));
        String actual = myAccountText.getText();
        String expeted = "Hesabım";
        Assert.assertEquals("No 'Hesabim' text", expeted, actual);

        WebElement usernameDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/a/i")));
        usernameDisplay.click();

        WebElement logOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#Çık']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logOutButton);
    }

    @Test
    public void invalidLoginWithInPassword() {
        driver.get("https://www.akakce.com/");

        WebElement signIn = driver.findElement(By.xpath("(//a[text()='Giriş Yap'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(signIn));
        signIn.click();

        WebElement loginPanelText = driver.findElement(By.cssSelector("div > b"));
        wait.until(ExpectedConditions.visibilityOf(loginPanelText));

        ReusableMethods.threadWait(3);
        WebElement inputEmail = driver.findElement(By.xpath("//input[@type='email']"));
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.sendKeys(email);

        WebElement updateBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button > b")));
        wait.until(ExpectedConditions.elementToBeClickable(updateBtn));
        updateBtn.click();

        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(invalidPassword);

        WebElement loginBtn = driver.findElement(By.xpath("//b[text()='Giriş Yap']"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();

        WebElement errorText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='form-group'][2]/div")));
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[@class='form-group'][2]/div"), "data-e", "Lütfen şifrenizi kontrol edin."));
        String actual = errorText.getAttribute("data-e");
        String expeted = "Lütfen şifrenizi kontrol edin.";
        Assert.assertEquals("No error text", expeted, actual);
    }

    @Test
    public void invalidLoginWithInEmail() {
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
        inputEmail.sendKeys(invalidEmail);

        WebElement updateBtn = driver.findElement(By.cssSelector("button > b"));
        wait.until(ExpectedConditions.elementToBeClickable(updateBtn));
        updateBtn.click();

        WebElement nameText = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
        wait.until(ExpectedConditions.visibilityOf(nameText));
        String actual = nameText.getAttribute("placeholder");
        String expected = "Adınızı yazın";
        Assert.assertEquals("Text do not match 'Adınızı yazın'", expected, actual);
    }

    @Test
    public void noInputLogin() {
        driver.get("https://www.akakce.com/");

        WebElement signIn = driver.findElement(By.xpath("(//a[text()='Giriş Yap'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(signIn));
        signIn.click();

        WebElement updateBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div > button > b")));
        wait.until(ExpectedConditions.elementToBeClickable(updateBtn));
        ReusableMethods.threadWait(2);
        updateBtn.click();

        WebElement errorText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='input-wrapper er']")));
        wait.until(ExpectedConditions.visibilityOf(errorText));
        String expected = "Lütfen e-posta adresinizi yazın.";
        String actual = errorText.getAttribute("data-e");
        Assert.assertEquals("Error text don't match", expected, actual);
        System.out.println(actual);

        tearDown();
    }
}
