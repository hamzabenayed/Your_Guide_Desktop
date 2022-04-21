/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class Commentaire {
   private int id;
   private String desc_comm;
   private Date date_comm;

    public Commentaire() {
    }

    public Commentaire(String desc_comm, Date date_comm) {
        this.desc_comm = desc_comm;
        this.date_comm = date_comm;
    }

    public Commentaire(int id, String desc_comm, Date date_comm) {
        this.id = id;
        this.desc_comm = desc_comm;
        this.date_comm = date_comm;
    }

    public Commentaire(int id, Date date_comm) {
        this.id = id;
        this.date_comm = date_comm;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc_comm() {
        return desc_comm;
    }

    public void setDesc_comm(String desc_comm) {
        this.desc_comm = desc_comm;
    }

    public Date getDate_comm() {
        return date_comm;
    }

    public void setDate_comm(Date date_comm) {
        this.date_comm = date_comm;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", desc_comm=" + desc_comm + ", date_comm=" + date_comm + '}';
    }
   
   
    
}
