package twitterfeed.core.loaders;

import org.junit.Assert;
import org.junit.Test;
import twitterfeed.core.entity.TwitterUser;

import java.util.Map;

public class UserFileLoaderTest {


   /* @Before
    private void loadTestData()
    {
        LoadTestData.loadTestData();
    }*/
    @Test
    public void testLoadFile() {

//        LoadTestData.loadTestData();

        UserFileLoader fileLoader = UserFileLoader.Instance.getUserFileLoader();
        Map<String,TwitterUser> twitterUsers =null;
        try {
          twitterUsers = fileLoader.loadFile("/home/gumani/work/code/personal/mytwitterfeed/user.txt");
        } catch (Exception e) {

            e.printStackTrace();


        }


        Assert.assertNotNull(twitterUsers);
    }
}