package Entities;


import java.time.LocalDate;

public class Type_activite implements Comparable<Type_activite>{
    
    private int id;
    private String nom;
    
    public Type_activite(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Type_activite(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int compareTo(Type_activite o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}