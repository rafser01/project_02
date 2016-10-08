/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedBean;

import com.smartMess.dao.DAO;
import com.smartMess.pojos.Member;
import com.smartMess.pojos.Notice;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class NoticeBean implements Serializable{
   private Notice notice=new Notice();
   private String message;
   private List<String> tag=new ArrayList<>();
   StringBuffer buf=new StringBuffer();
    DAO dao=new DAO();
    private HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
   

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        Map<String, String> tags=new HashMap<>();
        this.tag=tag;
        for(String s:this.tag){
            tags.put(s, s);
             
        }
        Iterator it=tags.values().iterator();
        while(it.hasNext()){
            buf.append(it.next()+",");
        }
        notice.setTag(buf.toString());
         
    }
     

    /**
     * Creates a new instance of NoticeBean
     */
    public NoticeBean() {
    }
    public void submit(){
        try{
        Member mem=(Member) session.getAttribute("user");
             
        getNotice().setRemark1(dao.dateConverter());
             
        getNotice().setMember(mem);
          
        dao.save(getNotice());
         
        setMessage("Notice submitted!"); //confirmation of saving message
        }
        catch(Exception e){
            setMessage("Not submitted! Fill forms "); 
        }
         
    }
    
    
    
}
