/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedBean;

import com.smartMess.dao.DAO;
import com.smartMess.pojos.Meal;
import com.smartMess.pojos.Member;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author HP
 */
@ManagedBean
@ViewScoped
public class MealBean implements Serializable {

    ArrayList<String> calenders = null;
    private Map<String, Integer> monthList;
    private List<String> months;
    DAO dao = new DAO();
    private int monthNo;
    private String monthName;
    private Member member=new Member();
    private Map<String, Member> memMap = null;
    private String memFirstName;
    private double breakFast;
    private double lunch;
    private double dinner;
    private List<Meal> mealList;

    /**
     * Creates a new instance of MealBean
     */
    public MealBean() {
    }

    public List<String> datePub(int month, int year) {
        calenders = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.YEAR, year);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i + 1);

            calenders.add(df.format(cal.getTime()));

        }

        return calenders;
    }

    @PostConstruct
    public void init() {
        try {
            monthList = new HashMap<String, Integer>();
            monthList.put("January", 0);
            monthList.put("February", 1);
            monthList.put("March", 2);
            monthList.put("April", 3);
            monthList.put("May", 4);
            monthList.put("June", 5);
            monthList.put("July", 6);
            monthList.put("August", 7);
            monthList.put("September", 8);
            monthList.put("October", 9);
            monthList.put("November", 10);
            monthList.put("December", 11);
            
            months = new ArrayList<String>();
            months.add("January");
            months.add("February");
            months.add("March");
            months.add("April");
            months.add("May");
            months.add("June");
            months.add("July");
            months.add("August");
            months.add("September");
            months.add("October");
            months.add("November");
            months.add("December");
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            Member mem = (Member) session.getAttribute("user");
            setMember(mem);
            mealList=memberMeals();
        } catch (ParseException ex) {
            Logger.getLogger(MealBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void createMonth() throws ParseException {
setMonthNo(monthList.get(getMonthName()));

        List<String> allDaysMonth = datePub(getMonthNo(), 2016);

        List<Member> lists = dao.getMessMebers(getMember().getMessName());

        for (Member m : lists) {
            Meal meal = new Meal();
            meal.setMember(m);
            meal.setBreakFast(0.0);
            meal.setLunch(0.0);
            meal.setDinner(0.0);
            for (String date : allDaysMonth) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date day = format.parse(date);
                meal.setDate(day);

                dao.save(meal);
            }

        }
        

    }

    public List<String> memberList() {
        List<Member> list = new ArrayList<Member>();
        List<String> memList = new ArrayList<String>();
        memMap = new HashMap<String, Member>();
        list = dao.getMemberList(getMember().getMessName());
        for (Member m : list) {
            memMap.put(m.getUser(), m);
            memList.add(m.getUser());

        }

        return memList;
    }

    public List<Meal> memberMeals() throws ParseException {
        List<Meal> list = new ArrayList<Meal>();
        list = dao.getMemberMeals(getMember());
        return list;
    }

    public void chnageMeal() throws ParseException {
        setMember(getMemMap().get(getMember().getUser()));
        mealList=memberMeals();

    }

    public void onRowEdit(RowEditEvent event) {
        Meal meal = (Meal) event.getObject();
         
        
        dao.update(meal);
}

    public void onCellEdit(CellEditEvent event) {

    }

    public Map<String, Integer> getMonthList() {
        return monthList;
    }

    public void setMonthList(Map<String, Integer> monthList) {
        this.monthList = monthList;
    }

    public int getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(int monthNo) {
        this.monthNo = monthNo;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {

        this.member = member;
    }

    public String getMemFirstName() {
        return memFirstName;
    }

    public void setMemFirstName(String memFirstName) {
        this.memFirstName = memFirstName;
        setMember(memMap.get(getMemFirstName()));
    }

    public Map<String, Member> getMemMap() {
        return memMap;
    }

    public double getBreakFast() {
        return breakFast;
    }

    public void setBreakFast(double breakFast) {
        this.breakFast = breakFast;
    }

    public double getLunch() {
        return lunch;
    }

    public void setLunch(double lunch) {
        this.lunch = lunch;
    }

    public double getDinner() {
        return dinner;
    }

    public void setDinner(double dinner) {
        this.dinner = dinner;
    }

    public List<Meal> getMealList() throws ParseException {

        
        if (mealList == null) {
            mealList = memberMeals();
        }
        return mealList;

    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
    }

}
