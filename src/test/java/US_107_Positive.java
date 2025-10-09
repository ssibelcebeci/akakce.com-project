import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utilities.BaseDriver;
import utilities.ReusableMethods;
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
            E-posta:
    testuser@example.com
            Şi  */

    public static void main(String[] args) {

        final String email="gurhanatas45@gmail.com";
        final String password="12345++aA";
        final String name="Gurhan";
        final String surname="Atas";
        // 1st step
        driver.get("https://www.akakce.com/");
        // 2nd step
        WebElement loginBtn= driver.findElement(By.xpath("(//a[text()='Giriş Yap'])[1]"));
        Assert.assertTrue("Login button is not visible", loginBtn.isDisplayed());
        loginBtn.click();
        ReusableMethods.threadWait(3);

        // 3rd step

        WebElement emailPlaceHolder= driver.findElement(By.xpath("//input[@type='email']"));
        emailPlaceHolder.sendKeys(email);

        WebElement continueBtn=driver.findElement(By.xpath("//button[@id='update']/b"));
        continueBtn.click();
        ReusableMethods.threadWait(3);

        WebElement namePlaceHolder=driver.findElement(By.xpath("//input[@id='name']"));
        namePlaceHolder.sendKeys(name);

        WebElement surnamePlaceHolder=driver.findElement(By.xpath("//input[@id='surname']"));
        surnamePlaceHolder.sendKeys(surname);
        ReusableMethods.threadWait(3);
        WebElement passwordPlaceHolder=driver.findElement(By.xpath("//input[@type='password']"));
        passwordPlaceHolder.sendKeys(password);

        WebElement checkbox=driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        checkbox.click();
        ReusableMethods.threadWait(3);

        // 4th step

        WebElement createAcc=driver.findElement(By.xpath("//button[@id='update']/b"));
        createAcc.click();
        ReusableMethods.threadWait(30);
        // 5th step

        WebElement nameTag=driver.findElement(By.xpath("//a[@title='Hesabım']"));
        Assert.assertTrue("Gurhan account name is not visible",nameTag.isDisplayed());

        // 6th step

        nameTag.click();
        ReusableMethods.threadWait(3);
        WebElement personalInfoBtn=driver.findElement(By.xpath("//div[@class='mnu']/a[1]"));
        personalInfoBtn.click();
        ReusableMethods.threadWait(3);

        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,750)");

        WebElement deleteMyAccBtn=driver.findElement(By.cssSelector("  button[id='delete']>b"));
        deleteMyAccBtn.click();
        ReusableMethods.threadWait(3);

        js.executeScript("window.scrollTo(0,document.body.scrollHeight");
        List<WebElement> links=driver.findElements(By.cssSelector("button[type='submit']>b"));
        for (int i = 0; i < links.size() ; i++) {
            if (links.get(i).equals("Hesabımı Silmek İçin Devam Et"))
                links.get(i).click();

        }
        ReusableMethods.threadWait(3);
        List<WebElement>typesRadio=driver.findElements(By.xpath("//input[@type='radio']"));
        for (int i = 0; i < typesRadio.size() ; i++) {
            if (typesRadio.get(i).equals("Uygulamaya artık ihtiyaç duymuyorum."))
                typesRadio.get(i).click();
        }
        ReusableMethods.threadWait(3);
        List<WebElement>buttons=driver.findElements(By.xpath("//button[@type='button']/b"));
        for (int i = 0; i < buttons.size() ; i++) {
            if (buttons.get(i).equals("Hesabımı Sil"))
                buttons.get(i).click();
        }

        ReusableMethods.threadWait(3);
        // 7th step
        WebElement loginBtn_AfterDeletion= driver.findElement(By.xpath("(//a[text()='Giriş Yap'])[1]"));
        Assert.assertTrue("Account deletion is not successful", loginBtn_AfterDeletion.isDisplayed());

        BaseDriver.tearDown();
    }
}