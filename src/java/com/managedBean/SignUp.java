/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedBean;

import com.hib.util.HibernateUtil;
import com.smartMess.business.Domain;
import com.smartMess.dao.DAO;
import com.smartMess.model.SendVerificationMail;
import com.smartMess.pojos.Member;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
 
import javax.servlet.http.HttpSession;
 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author HP
 */
@ManagedBean
@SessionScoped
public class SignUp implements Serializable{

    private String phoneNumber;
    private String lastName;
    private String user;
    private List<String> userList;
    private String pass;
    private String firstName;
    private String email;
    private Date date;
    private String gender;
    private String location;
    private String active;
    private String varifyCode;
    private String messName;

    int alert=0;
    DAO dao=new DAO();
    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    Domain domain = new Domain();

    public String getVarifyCode() {
        return varifyCode;
    }

    public void setVarifyCode(String varifyCode) {
        this.varifyCode = varifyCode;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<String> UserList(String user) {
        if (user.length() <= 1) {
            userList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                userList.add(lastName.toLowerCase().trim() + i);
            }
            return userList;
        }
        return userList;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }
    
    

    /**
     * Creates a new instance of signUp
     */
    public SignUp() {
    }

//    private void insert(Member mem){
//        SessionFactory sFactory = HibernateUtil.getSessionFactory();
//        Session session = sFactory.openSession();
//        session.beginTransaction();
//        
//        session.save(mem);
//        session.getTransaction().commit();
//        session.close();
//        sFactory.close();
//    }
    public void save() {
        try{
        Member mem = new Member();
        mem.setDob(date);
        mem.setEmail(email);
        mem.setFirstName(firstName);
        mem.setGender(gender);
        mem.setLastName(lastName);
        mem.setLocation(location);
        mem.setPassword(pass);
        mem.setRemarkMem1(phoneNumber);
        mem.setUser(user);
        mem.setMessName(messName);
        mem.setType("Admin");
        domain.insert(mem);
        alert=0;
        }catch(Exception e){
            try {
                List<Member> list=dao.getsDao().getSeesion().createQuery("from Member where user='"+user+"'").list();
                if(list.size()>0){
                    alert=1;
                }
                list=dao.getsDao().getSeesion().createQuery("from Member where email='"+email+"'").list();
                 
                if(list.size()>0){
                   alert=2;
                }
                FacesContext.getCurrentInstance().getExternalContext().redirect("signUp.xhtml");
            }
//        return "success";
            catch (IOException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String login() {

        SessionFactory sFactory=HibernateUtil.getSessionFactory();
        
        Session session = sFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Member where user=:a and password=:b");
        query.setParameter("a", user);
        query.setParameter("b", pass);

        
        List<Member> list = query.list();

        session.getTransaction().commit();
//        session.close(); // if we do close session here, filter wont work and send exception
//        sFactory.close();
        if (list.size() > 0) {
            Member mem = new Member();
            mem = list.get(0);
            HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            sess.setAttribute("user", mem);
            
                    if (mem.getType().equalsIgnoreCase("admin")) {
                        return "admin";

                    } else {
                        return "member";
                    }

        } 
        else {

            return "signUp";
        }

    }

    public String logOut() {
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sess.invalidate();
        
        return "signUp";
    }
//public void checkEmail(){
//    SessionFactory sFactory=HibernateUtil.getSessionFactory();
//        Session session=sFactory.openSession();
//        session.beginTransaction();
//        Query query=session.createQuery("from Member where email=:email");
//        query.setParameter("email",email);
//        
//        List<Member> list =query.list();
//        
//        session.getTransaction().commit();
//        session.close() ;
//        sFactory.close();
//        if(list.size()>0){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email already exist!"));
//        }
//}

    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);

    }

    public String sendMail() {
        SendVerificationMail sendMail = new SendVerificationMail();

        sendMail.sendMail2(email, pass + lastName, firstName);
        return "signUpNext";
    }

    public String verify() {
        String check = pass + lastName;
        if (check.equals(varifyCode)) {
            save();
            return "signUpSuccess";
        }

        return "signUp";
    }

}
