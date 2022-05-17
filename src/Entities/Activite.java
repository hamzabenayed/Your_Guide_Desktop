package Entities;


import utils.RelationObject;

import java.time.LocalDate;

public class Activite implements Comparable<Activite>{
    
    private int id;
    private RelationObject typeactId;
    private String nom;
    private String lieu;
    private String description;
    private String image;
    private String longitude;
    private String lattitude;
    
    public Activite(int id, RelationObject typeactId, String nom, String lieu, String description, String image, String longitude, String lattitude) {
        this.id = id;
        this.typeactId = typeactId;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public Activite(RelationObject typeactId, String nom, String lieu, String description, String image, String longitude, String lattitude) {
        this.typeactId = typeactId;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public Activite(int aInt, LocalDate parse, float aFloat, RelationObject relationObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Activite(RelationObject relationObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Activite(int aInt, RelationObject relationObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Activite(int aInt, LocalDate parse, float aFloat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public RelationObject getTypeactId() {
        return typeactId;
    }

    public void setTypeactId(RelationObject typeactId) {
        this.typeactId = typeactId;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    @Override
    public int compareTo(Activite o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}