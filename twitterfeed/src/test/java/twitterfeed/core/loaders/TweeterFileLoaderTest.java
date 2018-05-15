package twitterfeed.core.loaders;

import twitterfeed.core.util.TwittUserHandler;

/**
 * Created by gumani on 2018/05/13.
 */
public class TweeterFileLoaderTest {




   @org.junit.Test
    public void loadTwittsFile() throws Exception {

        String file = "/home/gumani/work/code/personal/mytwitterfeed/tweet.txt";

        TweeterFileLoader userFileLoader = TweeterFileLoader.Instance.getUserFileLoader();
        userFileLoader.loadTwittsFile(file);

        TwittUserHandler twittHandlerInstance = TwittUserHandler.Instance.getTwittHandlerInstance();



    }

    public static void main(String[] args) {

        TweeterFileLoaderTest test = new TweeterFileLoaderTest();

        try {
            test.loadTwittsFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}