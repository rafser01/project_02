package com.smartMess.dao;

 
import com.smartMess.pojos.Locations;
import com.smartMess.pojos.Meal;
 
import com.smartMess.pojos.Member;
import com.smartMess.pojos.Money;
import com.smartMess.pojos.Tolet;
import com.smartMess.sessionFactory.SessionFactoryDao;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DAO implements Serializable{

    SessionFactoryDao sDao = new SessionFactoryDao();

    public SessionFactoryDao getsDao() {
        return sDao;
    }

    public void setsDao(SessionFactoryDao sDao) {
        this.sDao = sDao;
    }

    
    public DAO() {
    }

    public List getAll(Class c) {
        Session session = sDao.getSeesion();
        session.beginTransaction();
        List list = session.createCriteria(c).list();
        session.close();
        return list;
    }

    public List<Tolet> find( String s) {
        Session session = sDao.getSeesion();
        session.beginTransaction();
        Query q = session.createQuery("from Tolet where  remark1=:s  or location=:s or remark2=:s");
       
        q.setParameter("s", s);
        List<Tolet> list=q.list();
        session.close();
        return list;

    }
    public void save(Object value) {
        Session session = sDao.getSeesion();
        session.beginTransaction();
        session.save(value);
        session.getTransaction().commit();
        session.close();

    }
    
    public List<Locations> findLocations( String s) {
        Session session = sDao.getSeesion();
        session.beginTransaction();
        Query q = session.createQuery("from Locations where  area=:s");
         
        q.setParameter("s", s);
        List<Locations> list=q.list();
         return list;

    }
    public String dateConverter(){
         
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        
        String date=format.format(new Date());
        return date;
        
    }
    
    public Object findObject(Object o, String s){
        Session session = sDao.getSeesion();
         
        
        return session.get(o.getClass(), s);
        
    }
    
    public List<Member> getMessMebers(String messName){
        List<Member> lists=new ArrayList<Member>();
        
        Session session=sDao.getSeesion();
        Query query=session.createQuery("from Member where messName=:messName");
        query.setParameter("messName", messName);
        lists=query.list();
        session.close();
        return  lists;
    }
    public List<Meal> getMemberMeals(Member member) throws ParseException{
        Date date=null;
        Session session=sDao.getSeesion();
        List<Meal> list=new ArrayList<Meal>();
        Query q=session.createQuery("from Meal  where user=:m order by date desc");
        q.setParameter("m", member.getUser());
        list=q.list();
        String month=null;
        if(list.size()>0){
             Meal meal=list.get(0);
             date=meal.getDate();
             DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
             month=df.format(date);
             
        }
        if(month!=null){
        month=month.substring(0,month.length()-2);
        
        month=month+"01";
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        date=format.parse(month);
        }
        list=new ArrayList<Meal>();
        q=session.createQuery("from Meal where user=:m and date >=:d");
        q.setParameter("m", member.getUser());
        q.setParameter("d", date);
        list=q.list();
        session.close();
         return list;
    }
    public List<Member> getMemberList(String messName){
        List<Member> list=new ArrayList<Member>();
        Session session=sDao.getSeesion();
        Criteria cr=session.createCriteria(Member.class);
        cr.add(Restrictions.eq("messName", messName));
        list=cr.list();
        session.close();
        return list;
    }
    
    public void update(Object value){
        Session session=sDao.getSeesion();
        session.beginTransaction();
        session.update(value);
        session.getTransaction().commit();
        session.close();
    }
    
    public Map<String, List<Money>> getMoneyListofAllMembers(String messName) {
        Map<String, List<Money>> userList = new HashMap<String, List<Money>>();
        System.out.println("1");
        Date date = null;
        String month = null;
        Map<String, ArrayList<Money>> membersMeals = new HashMap<String, ArrayList<Money>>();
        Criteria criteria = sDao.getSeesion().createCriteria(Member.class);
        criteria.add(Restrictions.eq("messName", messName));
 
        List<Member> list = criteria.list();
        int i=0;
        for (Member member : list) {
 
            List<Money> moneyList = new ArrayList<Money>();
            Query query = sDao.getSeesion().createQuery("from Money    where user='" + member.getUser() + "' order by date desc");

            moneyList = query.list();
            if(i<=0){
            if (moneyList.size() > 0) {
                Money money = moneyList.get(0);
                date = money.getDate();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                month = df.format(date);
            }

            if (month != null) {

                month = month.substring(0, month.length() - 2);
 
                month = month + "01";
                    
            }
            }
            moneyList = new ArrayList<Money>();
            query = sDao.getSeesion().createQuery("from Money where user='" + member.getUser() + "' and date >='" + month + "'");
            
            ArrayList<Money> finalMealList = new ArrayList<Money>();
            moneyList = query.list();
             System.out.println(month);
            for (Money m : moneyList) {
                finalMealList.add(m);
                i++;
            }
             
            userList.put(member.getUser(), finalMealList);
            
        }

        
        return userList;
    }

}
