package twitterfeed.core.database;

/**
 * Created by gumani on 2018/05/13.
 */


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = createSessionFactory();

    private static SessionFactory createSessionFactory() {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory( new StandardServiceRegistryBuilder().applySettings( configuration.getProperties() ).build() );

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
