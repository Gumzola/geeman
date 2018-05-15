package twitterfeed.core.client;

/**
 * Created by gumani on 2018/05/14.
 */
public class TwitterRunner {

    public static void main(String[] args) {
        System.out.println("Starting twitter Runner Application");

        final TwitterApplicationConsole twitterApplicationConsole = new TwitterApplicationConsole();

        twitterApplicationConsole.runApplication();

    }
}
