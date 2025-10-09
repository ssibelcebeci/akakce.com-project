import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BaseDriver;
import utilities.ReusableMethods;

import java.time.Duration;

public class US_105 extends BaseDriver {
    String email = "evrem99evrem@gmail.com";
    String password = "691296.Merve";
    String searchedProduct = "iPhone 16 pro";

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    Actions actions= new Actions(driver);

    @Test
    public void login() {
        driver.get("https://www.akakce.com/");

        WebElement loginBtn = driver.findElement(By.xpath("(//a[text()='Giriş Yap'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(loginBtn));
        loginBtn.click();

        WebElement verifyEmailPage = driver.findElement(By.xpath("(//a[text()='Akakce.com'])"));
        wait.until(ExpectedConditions.visibilityOf(verifyEmailPage));
        Assert.assertTrue("Email yazısı bulunamadı", verifyEmailPage.isDisplayed());

        WebElement emailHolder = driver.findElement(By.xpath("//input[@id='new-email']"));
        ReusableMethods.threadWait(2);
        emailHolder.sendKeys(email);

        WebElement continueBtn = driver.findElement(By.xpath("//button[@id='update']"));
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        continueBtn.click();

        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);

        WebElement loginBtn2 = driver.findElement(By.xpath("//button[@id='update']"));
        loginBtn2.click();

        WebElement verifyHomePage = driver.findElement(By.xpath("//a[@title='Anasayfaya gitmek için tıklayın']"));
        Assert.assertTrue("Ana sayfa bulunamadı", verifyHomePage.isDisplayed());

        WebElement searchProduct = driver.findElement(By.xpath("//input[@type='text']"));
        ReusableMethods.threadWait(3);
        searchProduct.click();
        searchProduct.sendKeys(searchedProduct);

        WebElement searchBtn = driver.findElement(By.xpath("(//i[text()='Ara'])"));
        searchBtn.click();
        ReusableMethods.threadWait(3);

        WebElement verifySearchedPage = driver.findElement(By.xpath("(//h1[text()='İphone 16 Pro'])"));
        Assert.assertTrue("Aranan ürün sayfası bulunamadı", verifySearchedPage.isDisplayed());
        ReusableMethods.threadWait(3);

        WebElement firstProduct = driver.findElement(By.xpath("(//span[text()='Alarm Kur'])[1]"));
        firstProduct.click();

        WebElement favoritesList = driver.findElement(By.xpath("//a[@title='Favori Listem']"));
        favoritesList.click();
        ReusableMethods.threadWait(3);

        WebElement favoritesPage = driver.findElement(By.xpath("(//span[text()='Favori Listem'])"));
        Assert.assertTrue("Favori Listem yazısı bulunamadı", favoritesPage.isDisplayed());

        WebElement myList = driver.findElement(By.xpath("(//a[text()='Listelerim'])[2]"));
        myList.click();
        ReusableMethods.threadWait(3);

        WebElement verifyMyListText = driver.findElement(By.xpath("(//h1[text()='Listelerim'])"));
        Assert.assertTrue("Listelerim yazısı bulunamadı", verifyMyListText.isDisplayed());

        WebElement clickFavorites = driver.findElement(By.xpath("(//span[text()='Favori Listem'])"));
        clickFavorites.click();
        ReusableMethods.threadWait(3);

        WebElement verifyTheProduct = driver.findElement(By.xpath("(//h3[text()='iPhone 16 Pro 128 GB'])"));
        Assert.assertTrue("Ürün favorilerde bulunamadı", verifyTheProduct.isDisplayed());
    }
}
