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
public class Produit {
    
        int idp;
        int category_id;
        String nom;
        String descrip;
        int prix;
        String image;

    public Produit(int idp, int category_id, String nom, String descrip, int prix, String image) {
        this.idp = idp;
        this.category_id = category_id;
        this.nom = nom;
        this.descrip = descrip;
        this.prix = prix;
        this.image = image;
    }

    public Produit(String nom, String descrip, int prix, String image) {
        this.nom = nom;
        this.descrip = descrip;
        this.prix = prix;
        this.image = image;
    }

    public Produit(int idp) {
        this.idp = idp;
    }

    public Produit() {
    }

    public Produit(String text, String text0, int parseInt, String pic, int idCat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public int getIdp() {
        return idp;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescrip() {
        return descrip;
    }

    public int getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "idp=" + idp + ", category_id=" + category_id + ", nom=" + nom + ", descrip=" + descrip + ", prix=" + prix + ", image=" + image + '}';
    }
        
    

    
}
