/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Categorie;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilisateur
 */
public class ImCategorie implements IService<Categorie>{
     Connection cnx = DataSource.getInstance().getCnx();
     Statement st;
    @Override
    public void ajouter(Categorie t) {
           try {
            String req = "INSERT INTO category (id,nom) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getID());
            pst.setString(2, t.getNom());
          
            pst.executeUpdate();
            System.out.println("Produit ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
    }

    @Override
    public void supprimer(Categorie t) {
         try {
            String req = "DELETE FROM category  WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getID());
            pst.executeUpdate();
            System.out.println("categoryt suprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifier(Categorie t) {
         try{
            String req = "UPDATE category SET nom=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);       
            pst.setString(1, t.getNom());
            pst.setInt(2,t.getID());
            pst.executeUpdate();
            System.out.println("category modifiée !");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> afficher() {
        List<Categorie> list = new ArrayList<>();
          try {
            String req = "SELECT * FROM category";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Categorie(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public int getIdCat(String nom) {
        try {
            String req ="SELECT id from category WHERE nom ='"+nom+"'";
            st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            if (rst.next()){
                int i = rst.getInt("id");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;        
    }
}
