/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedBean;

import com.smartMess.dao.DAO;
import com.smartMess.pojos.Locations;
import com.smartMess.pojos.Tolet;
import com.smartMess.sessionFactory.SessionFactoryDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class PostAdsBean implements Serializable{

    private MapModel mapModel;
    private double lat;
    private double lng;
    private Tolet tolet;
    private String title;
    private Set<String> locationList;
    private Map<String,Locations> loadLocation;

     SessionFactoryDao sDao = new SessionFactoryDao();
     DAO dao = new DAO();
     
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void adMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), getTitle());
        mapModel.addOverlay(marker);

    }

    public PostAdsBean() {
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Tolet getTolet() {
        return tolet;
    }

    public void setTolet(Tolet tolet) {
        this.tolet = tolet;
    }

   public void onPointSelect(PointSelectEvent event) {

        LatLng latlng = event.getLatLng();
        
      mapModel = new DefaultMapModel();
      Marker marker = new Marker(latlng, title);
       mapModel.addOverlay(marker);
    }
    
    public  Set<String>  locaList() {
        
        List<Locations> list = new ArrayList<Locations>();
        list = dao.findLocations(getTolet().getArea());
        locationList=new HashSet<String>();
        loadLocation=new HashMap<String, Locations>();
        for (Locations l : list) {
            locationList.add(l.getLocation());
            loadLocation.put(l.getLocation(), l);
           }
        
         return  locationList;
         
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
    
    public void coords(){
        Locations locaInstance =new Locations();
        locaInstance=loadLocation.get(getTolet().getLocation());
        lat=Double.parseDouble(locaInstance.getRemark1());
        lng=Double.parseDouble(locaInstance.getRemark2());
         System.out.println("d"+lat);
    }

    @PostConstruct
    public void init() {

        tolet=new Tolet();
        setLat(23.8101612262493);
        setLng(90.41284561157227);
    }

}
