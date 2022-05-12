/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Produit;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Utilisateur
 */
public class ImProduit implements IService<Produit>  {
 Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Produit t) {
          
  
           try {
            String req = "INSERT INTO produit (nom,description,prix,image) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            /*pst.setInt(1,t.getIdp());
            pst.setInt(2, t.getCategory_id());*/
            pst.setString(1, t.getNom());
            pst.setString(2,t.getDescrip());
            pst.setInt(3,t.getPrix());
            pst.setString(4, t.getImage());
          
            pst.executeUpdate();
            System.out.println("categorie ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
    }

    @Override
    public void supprimer(Produit t) {
       try {
            String req = "DELETE FROM produit WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getIdp());
            pst.executeUpdate();
            System.out.println("produit supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Produit t) {
           try{
            String req = "UPDATE produit SET nom=?, description=?, prix=?, image=? WHERE id=? and category_id=?";
            PreparedStatement pst = cnx.prepareStatement(req);       
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescrip());
            pst.setInt(3, t.getPrix());
            pst.setString(4,t.getImage());
            pst.setInt(5,t.getIdp());
            pst.setInt(6,t.getCategory_id());
            pst.executeUpdate();
            System.out.println("produit modifiée !");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Produit> afficher() {
         List<Produit> list = new ArrayList<>();
          try {
            String req = "SELECT * FROM produit";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Produit(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getString(4),rs.getInt(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    
    }

    

   
    

