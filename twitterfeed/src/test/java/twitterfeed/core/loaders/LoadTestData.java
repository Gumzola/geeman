package twitterfeed.core.loaders;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import twitterfeed.core.database.HibernateUtil;
import twitterfeed.core.entity.*;

import java.util.HashSet;

/**
 * Created by gumani on 2018/05/14.
 */
public class LoadTestData {

    public static void loadTestData(){

        try {
            loadTestUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void loadTestUsers() throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = null;
        Transaction transaction =null;


        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            PersonalDetails pdA = new PersonalDetails("Alan1", "Smith", "Alan.Smith@themail.com", "0713456967");
            Address addresA = new Address("17 first street", "Vorna Valley", "Johannesurg", "South Africa", "0923");
            TwitterUser userAlan = new TwitterUser(1L, pdA, addresA, "alan", new HashSet<TwitterUser>(), new HashSet<Twitt>());

            PersonalDetails pdWard = new PersonalDetails("Ward1", "Freez", "Ward.Freeez@themail.com", "0614567893");
            Address addresWard = new Address("345 Lower Main street", "Obervatory", "Cape Town", "South Africa", "2019");
            TwitterUser userward = new TwitterUser(2L, pdWard, addresWard, "ward", new HashSet<TwitterUser>(), new HashSet<Twitt>());


            PersonalDetails pdMartin = new PersonalDetails("Martin1", "Manzini", "martin.manzini@themail.com", "0614567893");
            Address addresMartin = new Address("20 RS Street", "Soweto", "Johannesburg", "South Africa", "2019");
            TwitterUser userMartin = new TwitterUser(3L, pdMartin, addresMartin, "martin", new HashSet<TwitterUser>(), new HashSet<Twitt>());

            session.save(userAlan);
            session.save(userward);
            session.save(userMartin);

            transaction.commit();
        }catch(Exception exp){
            throw exp;


        }finally {
            session.close();
        }


    }

    private static void loadTestUsersTwitts(){

    }
}
