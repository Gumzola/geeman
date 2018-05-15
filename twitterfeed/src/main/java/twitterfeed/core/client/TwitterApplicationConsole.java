package twitterfeed.core.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import twitterfeed.core.database.HibernateUtil;
import twitterfeed.core.entity.Twitt;
import twitterfeed.core.entity.TwittConfig;
import twitterfeed.core.entity.TwitterUser;
import twitterfeed.core.loaders.TweeterFileLoader;
import twitterfeed.core.loaders.UserFileLoader;
import twitterfeed.core.util.ConfigurationManager;
import twitterfeed.core.util.TwittUserHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static twitterfeed.core.util.StaticFields.FILE_LOCATION;
import static twitterfeed.core.util.StaticFields.TWEETS_FILE_NAME;
import static twitterfeed.core.util.StaticFields.USER_FILE_NAME;

/**
 * Created by gumani on 2018/05/14.
 */
public class TwitterApplicationConsole{

    private ConfigurationManager configurationManager = ConfigurationManager.Instance.getConfigurationManager();
    private TweeterFileLoader tweeterFileLoader = TweeterFileLoader.Instance.getUserFileLoader();
    private  UserFileLoader userFileLoader = UserFileLoader.Instance.getUserFileLoader();
    private TwittUserHandler twitterHandler = TwittUserHandler.Instance.getTwittHandlerInstance();
   private boolean fileUpdated = false;

    public static boolean runApplication =true;

    private Map<String, TwittConfig> configurations;


    public TwitterApplicationConsole() {

        initialise();


    }
    public void runApplication() {

        System.out.println("Starting with application");
        while(runApplication){
            loadUserFile();
            loadTweetsFile();

            if(fileUpdated) {
                Map<String, TwitterUser> twitterUsers = twitterHandler.getTwitterUsers();

                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                Map<String, TwittConfig> configs = new HashMap<String, TwittConfig>();
                Session session = null;
                Transaction transaction = null;

                try {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();


                twitterUsers.forEach((key, value) -> {
                    System.out.println(key);
                    Set<Twitt> twitts = value.getTwitts();
                    twitts.forEach((twitt) -> {
                                System.out.println("@" + key + ": " + twitt.getTwitterText());
                            }

                    );

                    final Set<TwitterUser> followers = value.getFollowers();
                    followers.forEach((follower) -> {
                        final Set<Twitt> followerTwitts = follower.getTwitts();
                        followerTwitts.forEach((twittF) -> {
                                    System.out.println("@" + follower.getTwitterHandle() + ": " + twittF.getTwitterText());
                                }
                        );
                    });
                });

                    transaction.commit();
                }catch(Exception exp){
                    exp.printStackTrace();
                    throw exp;

                }finally {
                    session.close();
                }

                fileUpdated=false;

            }
            try {
                Thread.sleep(10000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        System.out.println("Exiting application");


    }

    private void loadUserFile(){

        File fileDirectory = new File(getFileLocation());
        final boolean exists = fileDirectory.exists();
        final String fileName = fileDirectory.getPath() + File.separatorChar +  configurations.get(USER_FILE_NAME).getConfiguration();

        if(isFileThere(fileName)){
            fileUpdated=true;
            try {
                final Map<String, TwitterUser> stringTwitterUserMap = userFileLoader.loadFile(fileName);

                System.out.println("stringTwitterUserMap = " + stringTwitterUserMap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void loadTweetsFile() {
        File fileDirectory = new File(getFileLocation());
        final String fileName = fileDirectory.getPath() + File.separatorChar +  configurations.get(TWEETS_FILE_NAME).getConfiguration();
        if (isFileThere(fileName)) {
            fileUpdated = true;
            try {
                boolean b = tweeterFileLoader.loadTwittsFile(fileName);

                System.out.println("Load tweet file = ");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }



    private String getFileLocation(){

        TwittConfig twittConfig = configurations.get(FILE_LOCATION);
        String fileLocation ="";
        if(twittConfig!=null) {
            fileLocation =  twittConfig.getConfiguration();
        }
        return  fileLocation;
    }

    private void initialise(){
        configurations = configurationManager.getConfigurations();
    }


    private boolean isFileThere( String fileName){
        File file = new File(fileName);
        return file.exists();

    }
}
