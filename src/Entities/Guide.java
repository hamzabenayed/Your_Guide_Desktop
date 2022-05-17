package Entities;


import utils.RelationObject;

import java.time.LocalDate;

public class Guide {
    
    private int id;
    private RelationObject activiteId;
    private String nom;
    private int tel;
    private String image;
    
    public Guide(int id, RelationObject activiteId, String nom, int tel, String image) {
        this.id = id;
        this.activiteId = activiteId;
        this.nom = nom;
        this.tel = tel;
        this.image = image;
    }

    public Guide(RelationObject activiteId, String nom, int tel, String image) {
        this.activiteId = activiteId;
        this.nom = nom;
        this.tel = tel;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public RelationObject getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(RelationObject activiteId) {
        this.activiteId = activiteId;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    
}