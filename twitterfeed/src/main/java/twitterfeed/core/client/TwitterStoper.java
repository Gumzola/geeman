package twitterfeed.core.client;

/**
 * Created by gumani on 2018/05/14.
 */
public class TwitterStoper {

    public static void main(String[] args) {


        System.out.println("Going to Stop the application");
        TwitterApplicationConsole.runApplication=false;
        System.out.println("Reques to Stop the application sent");
    }
}
