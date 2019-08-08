package com.ideas2it.onlineshopping.database;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * <p>
 * It is a singleton class in which single instance is created for a class
 * and also used to open and close the database connection
 * </p>
 */
public class Connector {

    private static SessionFactory factory;
    private static Session session;
    private static Connector connector;
    private static Logger logger = Logger.getLogger(Connector.class);

    /**
     * <p>
     * It is default constructor in which each time connection is invoked
     * </p>
     */
    private Connector() {
        try {
            factory = new Configuration().configure("com/ideas2it/onlineshopping/resources/hibernate.cfg.xml").buildSessionFactory();
        } catch(HibernateException exception) {
            logger.error("Error occurred while connecting to database", exception);
        }
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns the connection for database
     */
    public static Session getSession() {
        try {
            if (factory == null) {
                connector = new Connector();
                return factory.getCurrentSession();
            }
        } catch(HibernateException exception) {
            logger.error("Problem while getting a session connection to database", exception);
        }
        return factory.getCurrentSession();
    }
}
