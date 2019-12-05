import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {



    private static WebDriver driver;


    private static String nickname;
    private static String emailBody = "stupidloli";
    private final static String password = "T1metolol";
    private final static String  postfix = "@mail.com";
    private static String email;

    private static String url = "https://icarus.101xp.com/";


    private static int firstIteration = 138;
    private static int lastIteration = 1100;
    private static final int failIterations = 3;


    final static String path = "/Users/hleb/1.txt";





    public static void main(String[] args) throws InterruptedException {

        Actions actions = new Actions(driver, url);
        actions.initialize();
       for (int currIteration = firstIteration; currIteration <= lastIteration; currIteration++) {
                //You code here e.g.:

            email = currIteration + emailBody+ postfix;
            nickname = currIteration + emailBody;
            Account account = new Account(nickname, password, email);
            //System.out.println("account email: "+account.getEmail());
            //System.out.println("account nickname: "+account.getNickname());

            actions.register(account);
            //System.out.println("Start counting!");
            //actions.promo(account);
            //actions.logout();
            //System.out.println(actions.isCorrectAccount(account));




       }
    }






}