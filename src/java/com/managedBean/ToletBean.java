/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedBean;

import com.smartMess.dao.DAO;
import com.smartMess.pojos.Locations;
import com.smartMess.pojos.Member;
import com.smartMess.pojos.Tolet;
import com.smartMess.sessionFactory.SessionFactoryDao;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import org.hibernate.Query;
import org.hibernate.Session;
 
import org.primefaces.component.fileupload.FileUpload;

/**
 *
 * @author HP
 */
@ManagedBean
@ApplicationScoped
public class ToletBean implements Serializable {

    private Tolet tolet = new Tolet();
    private Map<String, String> area;
    private Map<String, String> location;
    SessionFactoryDao sDao = new SessionFactoryDao();
    private String category;
    private boolean wanted;
    private boolean offered;
    private Set<String> locationList;

    private FileUpload file;

    DAO dao = new DAO();
    Member member = new Member();

    public List<Tolet> ads;

    public List<Tolet> getAds() {
        return ads;
    }

    public void setAds(List<Tolet> ads) {
        this.ads = ads;
    }

    public boolean isWanted() {
        return wanted;
    }

    public void setWanted(boolean wanted) {
        this.wanted = wanted;
    }

    public boolean isOffered() {
        return offered;
    }

    public void setOffered(boolean offered) {
        this.offered = offered;
    }

    public Map<String, String> getArea() {
        return area;
    }

    public void setArea(Map<String, String> area) {
        this.area = area;
    }

    public Map<String, String> getLocation() {
        return location;
    }

    public void setLocation(Map<String, String> location) {
        this.location = location;
    }

    public Tolet getTolet() {
        return tolet;
    }

    public void setTolet(Tolet tolet) {
        this.tolet = tolet;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public FileUpload getFile() {
        return file;
    }

    public void setFile(FileUpload file) {
        this.file = file;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @PostConstruct
    public void init() {
        area = new HashMap<String, String>();

        //get Tolet 
        Session sess = sDao.getSeesion();
        sess.beginTransaction();
        Query q = sess.createQuery("from Tolet");
        List<Tolet> list = q.list();
        for (Tolet t : list) {
            area.put(t.getArea().toUpperCase(), t.getArea().toUpperCase());
        }

        List<Tolet> ads = new ArrayList<Tolet>();

        ads = dao.getAll(Tolet.class);
        setAds(ads);

    }

    public void locations() {
        this.location = new HashMap<String, String>();
        Session sess = sDao.getSeesion();
        sess.beginTransaction();
        Query q = sess.createQuery("from Tolet where area =:a");
        q.setParameter("a", tolet.getArea().toLowerCase());
        List<Tolet> list = q.list();
        for (Tolet t : list) {
            this.location.put(t.getLocation().toUpperCase(), t.getLocation().toUpperCase());
        }

    }

    /**
     * Creates a new instance of Tolet
     */
    public ToletBean() {
    }

    public void customSearch(String q) {
        List<Tolet> list = dao.find(q);
        setAds(list);
    }

    public void customTypes() {
        if (wanted == true) {
            String want = "wanted";
            customSearch(want);
        }
        if (offered == true) {
            String offer = "offered";
            customSearch(offer);
        }

    }

    public void reset() {
        tolet.setArea("");
        tolet.setLocation("");
        setCategory("");
        setWanted(false);
        setOffered(false);
        init();

    }

    public List<String> members() {
        List<Member> list = new ArrayList<Member>();
        list = dao.getAll(Member.class);
        List<String> names = new ArrayList<String>();
        for (Member s : list) {
            names.add(s.getFirstName());
        }
        return names;
    }

    public Set<String> areas() {
        List<Locations> list = new ArrayList<Locations>();
        list = dao.getAll(Locations.class);

        Set<String> areas = new HashSet<String>();
        for (Locations l : list) {

            areas.add(l.getArea());
        }
        return areas;
    }

    public  Set<String>  locaList() {
        
        List<Locations> list = new ArrayList<Locations>();
        list = dao.findLocations(getTolet().getArea());
        locationList=new HashSet<String>();
        for (Locations l : list) {
            locationList.add(l.getLocation());
           }
        
       
        return  locationList;
         
    }

    public void saveNotice() {

    }

  

 
    
    

}
