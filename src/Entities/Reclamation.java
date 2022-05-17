/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class Reclamation {
    private int id;
    private String type,description,etat;
    private Date date_rec;


    
    public Reclamation() {
    }

    public Reclamation(int id, String type, String description, String etat, Date date_rec) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.etat = etat;
        this.date_rec = date_rec;
    }
    

    public Reclamation(String type, String description, Date date_rec,String etat) {
        this.type = type;
        this.description = description;
        this.date_rec = date_rec;
        this.etat = etat;
    }

    public Reclamation(String type, String description, Date date_rec) {
        this.type = type;
        this.description = description;
        this.date_rec = date_rec;
    }

    
    
    public Reclamation(int id, Date date_rec) {
        this.id = id;
        this.date_rec = date_rec;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
     public String getEtat() {
        return etat;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateRec() {
        return date_rec;
    }

    public void setDateRec(Date date_rec) {
        this.date_rec = date_rec;
    }
     public void setEtat(String etat) {
        this.etat = etat;
    }
     @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + ", description=" + description + ", date_rec=" + date_rec + ", etat=" + etat + '}';
    }
    
    
    
}
