import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {



    private static WebDriver driver;

    private static boolean isLoggedIn = false;

    private static String nickname;
    private static String emailBody = "trickster";
    private final static String password = "Timetofade1";
    private final static String  postfix = "@mail.com";
    private static String email;

    private static String url = "https://icarus.101xp.com/";


    private static int firstIteration = 1;
    private static int lastIteration = 100;
    private static int currIteration = 5;
    private static final int failIterations = 3;


    final static String path = "/Users/hleb/1.txt";





    public static void main(String[] args) throws InterruptedException, NotLoggedInException {


       // for (int currIteration = firstIteration; currIteration <= lastIteration; currIteration++) {
                //You code here e.g.:

            email =currIteration + emailBody + currIteration + postfix;
            nickname =currIteration + emailBody + currIteration;
            Account account = new Account(nickname, password, email);
            System.out.println("account email: "+account.getEmail());
            System.out.println("account nickname: "+account.getNickname());

            Actions actions = new Actions(driver, url);

            actions.initialize();
            actions.login(account);
            System.out.println("Start counting!");
            actions.isCorrectAccount(account);

            //actions.promo(account);
            //actions.logout();
            //System.out.println(actions.isCorrectAccount(account));




       // }
    }






}