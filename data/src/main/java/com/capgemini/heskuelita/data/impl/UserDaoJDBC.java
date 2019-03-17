package com.capgemini.heskuelita.data.impl;

import com.capgemini.heskuelita.core.Beans.UserAnnotation;
import com.capgemini.heskuelita.data.DataException;
import com.capgemini.heskuelita.data.IUserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.slf4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import java.util.List;

public class UserDaoJDBC implements IUserDao {

     private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (UserDaoHibernate.class);


    public UserDaoHibernate(SessionFactory sessionFactory) {

        super ();

        this.sessionFactory = sessionFactory;
    }


    public static void setup () {

        sessionFactory = HibernateUtil.getSessionFactory ();
    }


    public static void destroy () {

        sessionFactory.close ();
    }

    @Override
    public UserAnnotation login (String userName, String password) {

        // Get a session.
        Session session = null;
        final String    filter1 = userName;
        final String filter2 = password;
        UserAnnotation us = null;

        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();
            logger.info (String.format ("Finding users by name and password [%s, %s] using criteria object.", filter1, filter2));
            Criterion criterion1 = Restrictions.like ("name", filter1);
            Criterion criterion2 = Restrictions.like ("password", filter2);
            LogicalExpression andExp = Restrictions.and (criterion1, criterion2);
            List<UserAnnotation> list = (List<UserAnnotation>) session.createCriteria (UserAnnotation.class).
                    add (andExp).list ();
            if(!list.isEmpty()){
                for (UserAnnotation e : list){
                    us = new UserAnnotation();

                    us.setName(e.getName());

                }

            }

            logger.info (String.format ("Users by username and password [%s, %s] using criteria object executed!", filter1, filter2));

        } catch (Exception ex) {

            String m = String.format ("Problems executing login %s", ex.getMessage ());
            logger.error (m);

        } finally {

            logger.info ("Closing session...");
            session.close ();
        }

        if (us == null) {
            throw new DataException ("Usuario " + userName + " desconocido");
        }
        return  us;
    }


    @Override
    public  void create (String user_name, String password, String email){

        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info(String.format("Creating value to insert... %s , %s , %s",  user_name ,  password , email));
            UserAnnotation pt = new UserAnnotation (user_name,password,email);

            // Save the data.
            logger.info (String.format ("Saving value %s", pt.getName ()));
            session.save (pt);
            logger.info (String.format ("Value %s saved!", pt.getName ()));

            tx.commit ();

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();

        } finally { session.close (); }

    }

    @Override
    public List<UserAnnotation> findAll () {

        Session session = null;
        List<UserAnnotation> users = null;

        try {

            logger.info ("Executing select all users.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            users = (List)session.createQuery ("from user").list ();

            logger.info ("Print all users info.");
            users.forEach ( e -> logger.info (String.format ("user %s name ==> %s",
                    e.getName (), e.getEmail()))
            );

        } catch (Exception e) {

            logger.error (e.getMessage ());


        } finally { session.close(); }

        return users;
    }





    @Override
    public UserAnnotation findById (int id) {

        // Get a session.
        Session session = null;
        final int    filter1 = id;
        UserAnnotation user =  new UserAnnotation();
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding users by id  [%d] using criteria object.", filter1));
            Criterion criterion1 = Restrictions.gt ("id", filter1);
            Conjunction andExp = Restrictions.and (criterion1);
            List<UserAnnotation> list = (List<UserAnnotation>) session.createCriteria (UserAnnotation.class).
                    add (andExp).list ();
            logger.info (String.format ("Users by id [%d] using criteria object executed!", filter1));


            logger.info ("Print all user info.");
            list.forEach ( e -> logger.info (e.getEmail()));

            if(!list.isEmpty()){
                for (UserAnnotation us : list){

                    user.setName(us.getName());
                    user.setPassword(us.getPassword());
                    user.setEmail(us.getEmail());

                }
            }

        } catch (Exception ex) {

            String m = String.format ("Problems with find user %s", ex.getMessage ());
            logger.error (m);
        } finally {

            logger.info ("Closing session...");
            session.close ();
        }

        return user;
    }

    @Override
    public void update (UserAnnotation user) {
        final Session session;
        Transaction tx = null;
        final int    filter1 = user.getId();

        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            logger.info (String.format ("Finding users by name  [%s] using criteria object.", filter1));
            Criterion criterion1 = Restrictions.gt ("users_id", filter1);
            Conjunction andExp = Restrictions.and (criterion1);

            List<UserAnnotation> list = (List<UserAnnotation>) session.createCriteria (UserAnnotation.class).
                    add (andExp).list ();

            logger.info ("Print all employees info.");
            list.forEach (e -> {

                logger.info (String.format ("Updating %s ", e.getName ()));
                e.setName (user.getName());
                e.setPassword(user.getPassword());
                e.setEmail(user.getEmail());
                session.save (e);
            });
            tx.commit ();



        } catch (Exception e) {

            logger.error (e.getMessage ());

        }
    }

    @Override
    public boolean remove (UserAnnotation us) {
        /*
        final Session session;
        Transaction tx;
        final int    filter1 = us.getId();
        try {
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
           list.forEach (e -> session.delete (e));
            tx.commit ();
        } catch (Exception e) {
            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }*/
        return false;

    }
}
