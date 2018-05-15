package twitterfeed.core.loaders;

import twitterfeed.core.entity.Twitt;
import twitterfeed.core.entity.TwitterUser;
import twitterfeed.core.util.TwittUserHandler;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TweeterFileLoader {

    private TwittUserHandler twitterHandler = TwittUserHandler.Instance.getTwittHandlerInstance();
    private TweeterFileLoader(){

    }

    public boolean loadTwittsFile(String tweetFile) throws Exception {

        Map<String, Twitt> twitterUsers = new HashMap<String, Twitt>();

        BufferedReader bReader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(tweetFile);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bReader = new BufferedReader(inputStreamReader);

            String line = "";
            String record[];
            while ((line = bReader.readLine()) != null) {

                record = line.split("> ");
                if (record == null || record.length < 1) {
                    throw new Exception("Error in line format " + line);
                }

                String handle = record[0].trim();
                TwitterUser user = twitterHandler.getTwitterUser(handle);
                String twittText = record[1].trim();
                if (user != null) {

                    Date now = new Date();
                    Twitt twitt = new Twitt(handle + now.getTime(), user, twittText, now);
                    try {
                        user.addTwitt(twitt);

                    }catch(Exception pp){
                        pp.printStackTrace();
                    }



                    twitterHandler.updateTweet(twitt);
                }


            }

            System.out.println("Done reading");


        } catch (FileNotFoundException fnf) {

            fnf.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            try {
                if (bReader != null) {
                    bReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (bReader != null) {
                    fileInputStream.close();
                }

                File file = new File(tweetFile);
//                boolean b = file.renameTo(new File(file.getAbsolutePath() + "_"+new Date().getTime()));

            } catch (Exception exxc) {
                exxc.printStackTrace();
            }

            return true;

        }
    }




        public static class Instance{
        private  static TweeterFileLoader twitterFileLoader = new TweeterFileLoader();

        public static TweeterFileLoader getUserFileLoader() {
            return twitterFileLoader;
        }
    }
}
