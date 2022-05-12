/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

/**
 *
 * @author Utilisateur
 */
public class Categorie {
int ID;
String nom;

    public Categorie(int ID, String nom) {
        this.ID = ID;
        this.nom = nom;
    }

    public Categorie(String nom) {
        this.nom = nom;
    }
    

    public Categorie(int ID) {
       this.ID=ID;
    }

    public Categorie() {
       
    }

    public int getID() {
        return ID;
    }

    public String getNom() {
        return nom;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Categorie{" + "ID=" + ID + ", nom=" + nom + '}';
    }
    


    
}
