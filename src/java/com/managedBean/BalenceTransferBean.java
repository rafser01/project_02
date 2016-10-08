/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedBean;

import com.smartMess.dao.DAO;
import com.smartMess.pojos.Balancetransfer;
import com.smartMess.pojos.Member;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
public class BalenceTransferBean implements Serializable{

    private Balancetransfer bt = new Balancetransfer();
    private DAO dao = new DAO();
    private List<String> recieverList;
    private String receiver;
    private String password;
    private String sendingStats;

    public Balancetransfer getBt() {
        return bt;
    }

    public void setBt(Balancetransfer bt) {
        this.bt = bt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSendingStats() {
        return sendingStats;
    }

    public void setSendingStats(String sendingStats) {
        this.sendingStats = sendingStats;
    }

    public List<String> getRecieverList() {
        return recieverList;
    }

    public void setRecieverList(List<String> recieverList) {
        this.recieverList = recieverList;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Member user = (Member) session.getAttribute("user");
        getBt().setMember(user);

        recieverList = new ArrayList<String>();
        DAO d = new DAO();
        List<Member> list = d.getAll(Member.class);
        for (Member m : list) {
            if (!m.getUser().equals(user.getUser())) {
                recieverList.add(m.getUser());

            }

        }

    }

    /**
     * Creates a new instance of BalenceTransferBean
     */
    public BalenceTransferBean() {
    }

    public void sendBalnce() {
        try {
             
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            Member m = (Member) session.getAttribute("user");

            

            if (getPassword().equals(m.getPassword())) {
                 
                getBt().setDate(dao.dateConverter());
                getBt().setReceiver(getReceiver());
                
                dao.save(getBt());
            }

            setSendingStats("Balance Transfered successfully!");
        } catch (Exception e) {
            setSendingStats("Not send! Fill available fields.");
        }

    }

}
