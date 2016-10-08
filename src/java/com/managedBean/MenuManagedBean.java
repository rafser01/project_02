/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedBean;

import com.smartMess.pojos.Menus;
import com.smartMess.sessionFactory.SessionFactoryDao;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
@ManagedBean
@SessionScoped
public class MenuManagedBean implements Serializable{
    
    public  List<Menus> getmenu(){
      Session ses=SessionFactoryDao.getSeesion();
        Transaction t=ses.beginTransaction();
        Query q=ses.createQuery("from Menus");
        List<Menus> menuList=q.list();
        
//        for(Menus m:menuList){
//            System.out.println("Menu "+m.getName());
//            if(m.getLink().equals("null")){
//                q=ses.createQuery("  from Menus where remark1=:r");
//                q.setParameter("r", m.getName());
//                List<Menus> subList=q.list();
//                for(Menus m2:subList){
//                    System.out.println("Sub menu "+m2.getName());
//                    System.out.println("Sub Link "+m2.getLink());
//                }
//                
//            }
//
//        }
        
        t.commit();
        
        ses.close();  
        
        return menuList;
    }
    
    public static  List<Menus> getSubMenu(String menu){
        Session ses=SessionFactoryDao.getSeesion();
        Transaction t=ses.beginTransaction();
        Query q=ses.createQuery("  from Menus where remark1=:r");
        q.setParameter("r", menu);
        List<Menus> subMenuList=q.list();
         t.commit();
        
        ses.close();  
        return subMenuList;
    }
    
    
     
    /**
     * Creates a new instance of MenuManagedBean
     */
    public MenuManagedBean() {
    }
    
    public List<Menus> getAllMenu(){
        Session ses=SessionFactoryDao.getSeesion();
        Transaction t=ses.beginTransaction();
        Query q=ses.createQuery(" from Menus where link <> 'null'");
         
        List<Menus> getAllMenu=q.list();
         t.commit();
        
        ses.close();  
        return getAllMenu;
        
    }
   
    
}
