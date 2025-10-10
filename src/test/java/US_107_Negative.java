import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.BaseDriver;
import utilities.ReusableMethods;

import java.util.List;
/*1. Kullanıcı Akakce.com sitesini tarayıcıda açar.
 2. Kullanıcı ana sayfada "Giriş Yap" veya benzer bir seçeneği bulur
ve tıklar.
 3. Kullanıcı,test datadaki geçerli bilgileri girer:
 4. "Giriş Yap" butonuna tıklar.
 5. Kullanıcı hesabına başarılı bir şekilde giriş yapar.
 6. Kullanıcı, hesap ayarları veya profil bölümünden "Hesabımı Sil"
veya benzer bir seçeneğe tıklar.
 7. Hesap silme işlemi sırasında, geçersiz bir şifre girer ve işlemi
tamamlamaya çalışır. */

public class US_107_Negative extends BaseDriver {
    final String email = "gurhanatas45@gmail.com";
    final String password = "12345++aA";
    final String name = "Gurhan";
    final String surname = "Atas";
    final String newEmail = "gurhanatas3545@gmail.com";

        @Test
        public void accountDeletionNTest(){
        // 1st step
        driver.get("https://www.akakce.com/");

        // 2nd step
        WebElement loginBtn = driver.findElement(By.xpath("(//a[text()='Giriş Yap'])[1]"));
        Assert.assertTrue("Login button is not visible", loginBtn.isDisplayed());
        loginBtn.click();
        ReusableMethods.threadWait(3);

        // 3rd step
        WebElement emailPlaceHolder = driver.findElement(By.xpath("//input[@type='email']"));
        emailPlaceHolder.sendKeys(email);

        WebElement continueBtn = driver.findElement(By.xpath("//button[@id='update']/b"));
        continueBtn.click();
        ReusableMethods.threadWait(3);

        WebElement namePlaceHolder = driver.findElement(By.xpath("//input[@id='name']"));
        namePlaceHolder.sendKeys(name);

        WebElement surnamePlaceHolder = driver.findElement(By.xpath("//input[@id='surname']"));
        surnamePlaceHolder.sendKeys(surname);

        WebElement passwordPlaceHolder = driver.findElement(By.xpath("//input[@type='password']"));
        passwordPlaceHolder.sendKeys(password);

        WebElement checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        checkbox.click();
        ReusableMethods.threadWait(3);

        // 4th step
        WebElement createAcc = driver.findElement(By.xpath("//button[@id='update']/b"));
        createAcc.click();
        ReusableMethods.threadWait(3);

        // 5th step
        WebElement nameTag = driver.findElement(By.xpath("//a[@title='Hesabım']/i"));
        Assert.assertTrue("Gurhan account name is not visible", nameTag.isDisplayed());

        // 6th step
        nameTag.click();
        ReusableMethods.threadWait(3);
        WebElement personalInfoBtn = driver.findElement(By.xpath("//div[@class='mnu']/a[1]"));
        personalInfoBtn.click();
        ReusableMethods.threadWait(3);

        WebElement deleteMyAccBtn = driver.findElement(By.cssSelector("  button[id='delete']>b"));
        deleteMyAccBtn.click();
        ReusableMethods.threadWait(3);

        List<WebElement> links = driver.findElements(By.cssSelector("button[type='submit']>b"));
        for (WebElement link : links) {
            if (link.getText().equals("Hesabımı Silmek İçin Devam Et"))
                link.click();
        }

        ReusableMethods.threadWait(3);
        List<WebElement> typesRadio = driver.findElements(By.xpath("//input[@type='radio']"));
        for (WebElement webElement : typesRadio) {
            if (webElement.getText().equals("Uygulamaya artık ihtiyaç duymuyorum."))
                webElement.click();
        }

        ReusableMethods.threadWait(3);
        List<WebElement> buttons = driver.findElements(By.xpath("//button[@type='button']/b"));
        for (WebElement button : buttons) {
            if (button.getText().equals("Hesabımı Sil"))
                button.click();
        }

        ReusableMethods.threadWait(3);

        // 7th step
        WebElement changeEmail = driver.findElement(By.xpath("//div[@class='change-but']/a"));
        changeEmail.click();

        WebElement currentPassword = driver.findElement(By.xpath("//input[@type='password']"));
        currentPassword.sendKeys("incorrect");

        WebElement newEmailInput = driver.findElement(By.xpath("//input[@id='new-email']"));
        newEmailInput.sendKeys(newEmail);
        WebElement updateBtn = driver.findElement(By.xpath("//button[@type='button']/b"));
        ReusableMethods.threadWait(3);
        updateBtn.click();

        WebElement changedEmailinfo = driver.findElement(By.xpath("//div[@class='alertX t1']"));
        Assert.assertTrue("Your email has not been changed", changedEmailinfo.isDisplayed());
        ReusableMethods.threadWait(3);
        changedEmailinfo.click();
    }
}