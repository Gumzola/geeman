package twitterfeed.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import twitterfeed.core.database.HibernateUtil;
import twitterfeed.core.entity.Twitt;

/**
 * Created by gumani on 2018/05/14.
 */
public class TwittDao {

    public boolean UpdateOrSaveTweet(Twitt twitt){


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction =null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.saveOrUpdate(twitt);
            transaction.commit();

        }catch(Exception exp){
            exp.printStackTrace();

        }finally {
            session.close();
        }
        return true;

        }
}
