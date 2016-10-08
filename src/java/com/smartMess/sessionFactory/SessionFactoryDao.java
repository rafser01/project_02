package com.smartMess.sessionFactory;

import com.hib.util.HibernateUtil;
import org.hibernate.Session;


public class SessionFactoryDao {
    public static Session getSeesion(){
        return  HibernateUtil.getSessionFactory().openSession();
    }

}
