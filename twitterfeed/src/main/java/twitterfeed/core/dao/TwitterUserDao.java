package twitterfeed.core.dao;

import org.hibernate.*;
import twitterfeed.core.database.HibernateUtil;
import twitterfeed.core.entity.TwitterUser;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TwitterUserDao {




    public TwitterUser getTwitterUser(String handle){

        TwitterUser user = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction =null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String sql = "Select * from twitter_user WHERE twitter_handle = '"+handle +"'";
            final SQLQuery sqlQuery = session.createSQLQuery(sql);
            user = (TwitterUser)sqlQuery.uniqueResult();
            transaction.commit();
        }catch(Exception exp){
            exp.printStackTrace();
            throw exp;

        }finally {
            session.close();
        }


        return user;
    }

    public  Map<String,TwitterUser> getAllTwitterUser() throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Map<String,TwitterUser> users = new TreeMap<String,TwitterUser>();
        Session session = null;
        Transaction transaction =null;


        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

           users = new TreeMap<String,TwitterUser>();
        List<TwitterUser> list = session.createCriteria(TwitterUser.class).setFetchMode("twitts", FetchMode.JOIN).list();

        transaction.commit();
        for (TwitterUser user :list) {
            users.put(user.getTwitterHandle(),user);
        }


        }catch(Exception exp){
            throw exp;


        }finally {
            session.close();
        }

        return users;
    }

    public boolean updateTwitterUser(List<TwitterUser> users) throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction =null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            for (TwitterUser user:
                    users) {
                session.saveOrUpdate(user);
            }

            transaction.commit();

        }catch(Exception exp){
            throw exp;


        }finally {
             session.close();
        }
            return true;
    }
}
