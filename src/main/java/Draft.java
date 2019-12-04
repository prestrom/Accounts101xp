import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Draft {


    public WebDriver initialize() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return driver;
    }


    public void signUp (WebDriver driver, Account account) throws InterruptedException {
        driver.get("https://portal-id-beta-ru.101xp.com/sessions/signin?theme=icarus");
        WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
        //email.sendKeys(account.getEmail);
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        //password.sendKeys(account.getPassword);
        WebElement loginButton = driver.findElement(By.xpath("//*[contains(text(), 'Войти')]"));
        loginButton.click();
        Thread.sleep(5000);
    }


    public void register(WebDriver driver, Account account) throws InterruptedException{

        driver.get("https://portal-id-beta-ru.101xp.com/sessions/signup?theme=icarus");
        WebElement email = driver.findElement(By.xpath("//input[@email='email']"));
        //email.sendKeys(account.getEmail);
        WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
        //password.sendKeys(account.getPassword);
        WebElement nickElem = driver.findElement(By.xpath("//input[@formcontrolname='username']"));
        //nickElem.sendKeys(account.getNickname);
        WebElement registerButton = driver.findElement(By.xpath("//*[contains(text(), 'Регистрация')]"));
        registerButton.click();
        Thread.sleep(3000);
    }


    public void promo (WebDriver driver, Account account) throws InterruptedException {

        String arrow = "//div[contains(@class,'adaptive menu-1')]//li[@class='menu-item menu-item-18 menu-item-level-0 menu-item-has-children']//div[@class='menu-item-arrow']//div";

        driver.get("https://icarus.101xp.com/");
        Thread.sleep(5000);
        driver.manage().window().maximize();
        WebElement itemArrow = driver.findElement(By.xpath(arrow));
        itemArrow.click();
        WebElement whBtn = driver.findElement(By.xpath("//a[text() ='Промокод']"));
        whBtn.click();
        WebElement promostr = driver.findElement(By.xpath("//input[contains(@placeholder,'промокод')]"));
        promostr.sendKeys("СНОВЫМ2019ГОДОМ!");
        WebElement activate = driver.findElement(By.xpath("//button[@value = 'Активировать']"));
        activate.click();
        Thread.sleep(3000);
    }

    public void print(String path, Account account) throws IOException {
        File file = new File(path);
        String header ="Login:              Password:   Nick:\n";
        String str = "";
        if (file.length() == 0)
        {
            str = header;
        }
        //String mail =account.getEmail;
        //String nick =account.getNickname;
        //String pass = account.getPassword;
        //str += mail+"   "+pass+"   "+nick+"   \n";
        FileUtils.writeStringToFile(file, str, "UTF-8", true);
    }




}
