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
import java.util.List;


public class US_107_Positive extends BaseDriver {
    /* 1. Kullanıcı Akakce.com sitesini tarayıcıda açar.
 2. Kullanıcı ana sayfada "Giriş Yap" veya benzer bir seçeneği bulur
    ve tıklar.
 3. Kullanıcı, test data daki geçerli bilgileri girer:
 4. "Giriş Yap" butonuna tıklar.
 5. Kullanıcı hesabına başarılı bir şekilde giriş yapar.
 6. Kullanıcı, hesap ayarları veya profil bölümünden "Hesabımı Sil"
    veya benzer bir seçeneğe tıklar.
 7. Hesap silme işlemi başarılı bir şekilde tamamlandığında, uygun
    bir mesajın görüntülendiğini kontrol eder.
    */

    final String email = "gurhanatas45@gmail.com";
    final String password = "12345++aA";
    final String name = "Gurhan";
    final String surname = "Atas";
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void accountDeletionPTest() {
        // 1st
        driver.get("https://www.akakce.com/");

        // 2nd
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("(//a[text()='Giriş Yap'])[1]")));
        loginButton.click();
        ReusableMethods.threadWait(2);

        // 3rd
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//input[@type='email']")));
        emailField.sendKeys(email);

        WebElement continueButton = driver.findElement(By.xpath("//button[@id='update']/b"));
        continueButton.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//input[@id='name']")));
        nameField.sendKeys(name);

        WebElement surnameField = driver.findElement(By.xpath("//input[@id='surname']"));
        surnameField.sendKeys(surname);

        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys(password);

        WebElement agreementCheckbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        agreementCheckbox.click();

        // 4th
        WebElement createAccountButton = driver.findElement(By.xpath("//button[@id='update']/b"));
        createAccountButton.click();
        ReusableMethods.threadWait(5);

        // 5th
        WebElement accountName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@title='Hesabım']")));
        Assert.assertTrue("Account name is not visible", accountName.isDisplayed());

        //6th
        accountName.click();
        ReusableMethods.threadWait(2);

        WebElement personalInfoButton = driver.findElement(By.xpath("//div[@class='mnu']/a[1]"));
        personalInfoButton.click();
        ReusableMethods.threadWait(2);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,750)");

        WebElement deleteAccountButton = driver.findElement(By.cssSelector("button[id='delete']>b"));
        deleteAccountButton.click();
        ReusableMethods.threadWait(2);

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        List<WebElement> continueButtons = driver.findElements(By.cssSelector("button[type='submit']>b"));
        for (WebElement btn : continueButtons) {
            if (btn.getText().equals("Hesabımı Silmek İçin Devam Et")) {
                btn.click();
                break;
            }
        }

        List<WebElement> reasonRadios = driver.findElements(By.xpath("//input[@type='radio']"));
        for (WebElement radio : reasonRadios) {
            if (radio.getAttribute("value").equals("Uygulamaya artık ihtiyaç duymuyorum.")) {
                radio.click();
                break;
            }
        }

        List<WebElement> confirmButtons = driver.findElements(By.xpath("//button[@type='button']/b"));
        for (WebElement btn : confirmButtons) {
            if (btn.getText().equals("Hesabımı Sil")) {
                btn.click();
                break;
            }
        }

        // 7th
        WebElement loginButtonAfterDeletion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//a[text()='Giriş Yap'])[1]")));
        Assert.assertTrue("Account deletion was not successful", loginButtonAfterDeletion.isDisplayed());

        BaseDriver.tearDown();
    }
}
