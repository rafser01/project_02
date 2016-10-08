package com.smartMess.business;

import com.hib.util.HibernateUtil;
import com.smartMess.pojos.Member;
import com.smartMess.pojos.Messname;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Domain {
    
    public  void insert(Member mem){
        SessionFactory sFactory = HibernateUtil.getSessionFactory();
        Session session = sFactory.openSession();
        session.beginTransaction();
        
//        Messname mess=new Messname(mem.getMessName(), mem.getLocation());
//        mem.getMessnames().add(mess);
        
        
        session.save(mem);
        session.getTransaction().commit();
        session.close();
         
    }

    public Domain() {
    }
    
    

}
