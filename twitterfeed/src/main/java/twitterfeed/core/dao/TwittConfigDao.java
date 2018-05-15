package twitterfeed.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import twitterfeed.core.database.HibernateUtil;
import twitterfeed.core.entity.TwittConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gumani on 2018/05/14.
 */
public class TwittConfigDao {

    public Map<String, TwittConfig> getAllConfigs()  {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Map<String, TwittConfig> configs = new HashMap<String, TwittConfig>();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            List <TwittConfig>list = session.createCriteria(TwittConfig.class).list();

            transaction.commit();
            for (TwittConfig config:list) {
                configs.put(config.getConfigName(),config);
            }

        }
        catch (Exception excp){
            excp.printStackTrace();
        } finally {
         session.close();
          }

          return configs;


}
}
