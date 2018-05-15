package twitterfeed.core.util;

import twitterfeed.core.dao.TwittDao;
import twitterfeed.core.dao.TwitterUserDao;
import twitterfeed.core.entity.Twitt;
import twitterfeed.core.entity.TwitterUser;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TwittUserHandler {

    private static Map<String,TwitterUser> twitterUsers = new TreeMap<String,TwitterUser>();
    private static long lastTwitterUser = 0L;
    private TwitterUserDao userDao = new TwitterUserDao();
    private TwittDao twittDao = new TwittDao();

//	private Map twit\

    private TwittUserHandler(){
        initialise();
    }

    private void initialise() {
        try {
            twitterUsers = userDao.getAllTwitterUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadtwitterUsers(){

    }

    public void  addTwitterUser(TwitterUser user){

    }

    public TwitterUser getTwitterUser(String twitterHandle){
        if(twitterHandle!=null)
             twitterHandle = twitterHandle.toLowerCase();
        return twitterUsers.get(twitterHandle);
    }
    public Map<String,TwitterUser> getTwitterUsers(){
        return twitterUsers;
    }


    public boolean updateTweeterUsers(List<TwitterUser> users){

        try {
             if(userDao.updateTwitterUser(users))
                 return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateTweet(Twitt twitt){

        boolean successFul = false;

        return twittDao.UpdateOrSaveTweet(twitt);

    }
    public static class Instance{
        private static TwittUserHandler twitterHandler = null;
       private static Object mutex = new Object();


        public static TwittUserHandler getTwittHandlerInstance(){
            if (twitterHandler == null) {
                synchronized (mutex){
                    if (twitterHandler == null) {
                        twitterHandler =  new TwittUserHandler();
                    }
                }
            }

            return twitterHandler;
        }
    }
}
