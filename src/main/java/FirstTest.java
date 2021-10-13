import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
public class FirstTest {
    @Test
    public void checkMobiPayMinSum() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://next.privat24.ua/mobile");

        //Ввод номера телефона
        driver.findElement(By.xpath("//input[@data-qa-node='phone-number']")).sendKeys("637086360");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Ввод суммы 1 UAH
        driver.findElement(By.xpath("//input[@data-qa-node='amount']")).sendKeys("1");

        //Ввод номера карты
        driver.findElement(By.xpath("//input[@data-qa-node='numberdebitSource']")).sendKeys("5309 2330 3476 5085");

        //Ввод срока действия карты
        driver.findElement(By.xpath("//input[@data-qa-node='expiredebitSource']")).sendKeys("0124");

        //Ввод CVV-кода
        driver.findElement(By.xpath("//input[@data-qa-node='cvvdebitSource']")).sendKeys("891");

        //Ввод Имя
        driver.findElement(By.xpath("//input[@data-qa-node='firstNamedebitSource']")).sendKeys("Juliana");

        //Ввод Фамилия
        driver.findElement(By.xpath("//input[@data-qa-node='lastNamedebitSource']")).sendKeys("Janssen");

        //Нажимаем кнопку "В кошик"
        driver.findElement(By.xpath("//button[@data-qa-node='submit']")).click();

        //Проверка коректности предзаполненных данных ввода мобильного номера телефона
        By dataQaNodeDetails = By.xpath("//span[@data-qa-node='details']");
        Assert.assertEquals("Поповнення телефону. На номер +380637086360",driver.findElement(dataQaNodeDetails).getText());

        //Проверка коректности предзаполненных данных ввода номера карты
        By dataQaNodeCard = By.xpath("//td[@data-qa-node='card']");
        Assert.assertEquals("5309 **** **** 5085",driver.findElement(dataQaNodeCard).getText());

        //Проверка оператора мобильной связи
        By LifecelUkraine = By.xpath("//td[@class='sc-bmyXtO iKQEMo receiver_j8X5PN7XJD']");
        Assert.assertEquals("Lifecell Ukraine",driver.findElement(LifecelUkraine).getText());

        //Проверка суммы пополнения
        By dataQaNodeAmount = By.xpath("//span[@data-qa-node='amount']");
        Assert.assertEquals("1",driver.findElement(dataQaNodeAmount).getText());

        //Проверка суммы комисии
        By dataQaNodeCommission = By.xpath("//span[@data-qa-node='commission']");
        Assert.assertEquals("2",driver.findElement(dataQaNodeCommission).getText());

        //Нажимаем кнопку "Підтвердити"
        driver.findElement(By.xpath("(//button[@class='sc-VigVT cQOKFU'])[2]")).click();

    }
}