
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Actions {
WebDriver driver;
private String url;

    public Actions(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void initialize() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }


    public boolean isLoggedIn () {
            List<WebElement> list = driver.findElements(By.xpath("//a[text()='АВТОРИЗАЦИЯ']"));
            if (list != null && !list.isEmpty()) {
                return false;
            }
            else
                {
            return true;
                }

        }

    public boolean isOnMainPage() {
        if (driver.getCurrentUrl().matches(url)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void goToMainPage () {
        driver.get(url);
    }

    public boolean isCorrectAccount (Account account) throws InterruptedException {


        //if (!isLoggedIn()) {
        //    System.out.println("Method:isCorrectAccount::Not logged in. Logging in...");
         //   login(account);
       // }
        driver.switchTo().frame("auth");
        String nick = driver.findElement(By.xpath("//span[@class='display-name mp-hide']")).getText();
        driver.switchTo().defaultContent();

        if (nick.matches(account.getNickname())) {
         return true;
        }
        else {
         return false;
        }

}

    public boolean _isCorrectAccount (Account account) throws NotLoggedInException {

                driver.get("https://ru.101xp.com/profile/settings/personal-data");
                try {
                    WebElement table = driver.findElement(By.tagName("table"));
                    String mail = table.findElement(By.xpath("//tr[3]//td[2]/p")).getText();
                    System.out.println("current account email: " + mail);
                    System.out.println("expected account email: " + account.getEmail());
                    driver.get("https://icarus.101xp.com/");
                    if (account.getEmail().matches(mail)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                catch (NoSuchElementException e)
                { throw  new NotLoggedInException ("Error: not logged in"); }

    }

    public void register (Account account) throws InterruptedException {
        driver.get("https://portal-id-beta-ru.101xp.com/sessions/signup?theme=icarus");
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys(account.getEmail());
        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys(account.getPassword());
        driver.findElement(By.xpath("//input[@formcontrolname='username']")).sendKeys(account.getNickname());
        driver.findElement(By.xpath("//*[contains(text(), 'Регистрация')]")).click();
        Thread.sleep(5000);
        driver.get(url);
        if (isCorrectAccount(account)) {
            System.out.println("Method:register:: New account "+account.getEmail()+" registered!");
        }
        else {
            System.out.println( account.getEmail()+": Something went wrong!");
            logout();
        }

    }

    public void login (Account account) throws InterruptedException {
        //if (!driver.getCurrentUrl().matches("https://icarus.101xp.com/")) { driver.get(url); }
        if (!isLoggedIn()) {
            driver.get("https://portal-id-beta-ru.101xp.com/sessions/signin?theme=icarus");
            WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
            emailField.sendKeys(account.getEmail());
            WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
            passwordField.sendKeys(account.getPassword());
            WebElement loginButton = driver.findElement(By.xpath("//*[contains(text(), 'Войти')]"));
            loginButton.click();
            Thread.sleep(5000);
            driver.get("https://icarus.101xp.com/");
            if (isLoggedIn() ) {
                System.out.println("Method:login::Successfully logged in ");
            }
            else {
                System.out.println("Method:login::Not Logged in.Something went wrong");
            }

            //if (isCorrectAccount(account)) {
            //    System.out.println("Method:login::Account is correct");
            //}
            //else {
            //    System.out.println("Method:login:: WARN: wrong account! logging out!");
            //    logout();
            //}
        }
        else {
            System.out.println("Method:login:: warning :already logged in");
        }

    }

    public void logout () {
        if (!isOnMainPage()) {
            goToMainPage();
        }

        if (isLoggedIn()) {
            driver.switchTo().frame("auth");
            driver.findElement(By.xpath("//span[@class='btn-desc']")).click();
            driver.switchTo().defaultContent();
            if (!isLoggedIn()) {
                System.out.println("Method:logout::Successfully logged out");
            }
            else {
                System.out.println("Method:logout::Still logged in.Something went wrong");
            }

        }
        else  {
            System.out.println("Method:logout::Can't log out: not logged in");
        }
    }

    public void promo (Account account) throws InterruptedException {
        //if (!isLoggedIn()) {
        //    login(account);
        //}
        //if (!isCorrectAccount(account)) {
        //    System.out.println("Method:promo::Account is incorrect! re-logging...");
        //    logout();
        //    login(account);
        //}

        String arrow = "//div[contains(@class,'adaptive menu-1')]//li[@class='menu-item menu-item-18 menu-item-level-0 menu-item-has-children']//div[@class='menu-item-arrow']//div";

        if (!isOnMainPage()) {
            goToMainPage();
        }
        driver.findElement(By.xpath(arrow)).click();
        driver.findElement(By.xpath("//a[text() ='Промокод']")).click();
        driver.findElement(By.xpath("//input[contains(@placeholder,'промокод')]")).sendKeys("СНОВЫМ2019ГОДОМ!");
        driver.findElement(By.xpath("//button[@value = 'Активировать']")).click();

        Thread.sleep(3000);
        goToMainPage();

    }

    public void changeNickname () {

    }


    public void test () {
        driver.switchTo().frame("auth");
        System.out.println("still alive");
        System.out.println(driver.findElement(By.xpath("//span[@class='display-name mp-hide']")).getText());
    }






}
