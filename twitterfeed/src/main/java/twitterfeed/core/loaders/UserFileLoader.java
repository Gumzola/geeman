package twitterfeed.core.loaders;

import twitterfeed.core.entity.TwitterUser;
import twitterfeed.core.util.TwittUserHandler;

import java.io.*;
import java.util.*;

public class UserFileLoader {

    private TwittUserHandler twitterHandler = TwittUserHandler.Instance.getTwittHandlerInstance();
    private UserFileLoader(){

    }


    public Map<String,TwitterUser> loadFile(String userFile) throws Exception{

        Map<String,TwitterUser> twitterUsers = new HashMap<String,TwitterUser>();

        BufferedReader bReader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(userFile);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bReader = new BufferedReader(inputStreamReader);

            String line = "";
            String record [] ;
            while ((line = bReader.readLine()) != null) {
                record = line.split("follows");
                if(record ==null || record.length < 1 || record.length >2 ){
                    throw new Exception("Error in line format " + line);
                }

                String handle =record[0].trim();
                String followersList [] = null ;
                TwitterUser user = twitterHandler.getTwitterUser(handle);
                if(record.length>1 && user !=null) {
                    String follow = record[1].trim();
                    followersList = follow.split(",");
                    if(followersList!=null) {
                        for (String follower : followersList) {
                            TwitterUser followeUser = twitterHandler.getTwitterUser(follower.trim());
                            if(followeUser!=null){
                                followeUser.addFollower(user);
                                twitterUsers.put(followeUser.getTwitterHandle(),followeUser);
                            }
                        }
                    }
                }

                twitterUsers.put(user.getTwitterHandle(),user);

            }

            List<TwitterUser> list = new ArrayList<TwitterUser>(twitterUsers.values());
            twitterHandler.updateTweeterUsers(list);

        }catch(FileNotFoundException fnf){

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {

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
                File file = new File(userFile);
                boolean b = file.renameTo(new File(file.getAbsolutePath() + "_"+ new Date().getTime()));

            } catch(Exception exxc){
                exxc.printStackTrace();
                }


        }



        return twitterUsers;
    }


    public static class Instance{
        private  static UserFileLoader userFileLoader = new UserFileLoader();

        public static UserFileLoader getUserFileLoader() {
            return userFileLoader;
        }
    }
}
