package twitterfeed.core.util;

import twitterfeed.core.dao.TwittConfigDao;
import twitterfeed.core.entity.TwittConfig;

import java.util.Map;

/**
 * Created by gumani on 2018/05/14.
 */
public class ConfigurationManager {


    private TwittConfigDao configDao = new TwittConfigDao();
    private Map<String, TwittConfig> configurations ;
    private ConfigurationManager() {
        init();
    }

    private void init(){
        configurations = configDao.getAllConfigs();
    }

    public Map<String, TwittConfig> getConfigurations() {
        return configurations;
    }

    public static  class Instance{
        private static  ConfigurationManager configuration  = null;
        private static Object mutex = new Object();

       public static ConfigurationManager getConfigurationManager(){

           if(configuration==null){
               synchronized (mutex){
                   if(configuration==null){
                       configuration = new ConfigurationManager();
                   }
               }
           }
           return configuration;
       }

    }
}
