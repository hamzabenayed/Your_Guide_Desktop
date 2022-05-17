/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Panier;
import Entities.Produit;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Utilisateur
 */
public class PanierService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Panier panier) {

        try {
            String req = "INSERT INTO panier (idProduit,idUser) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            /*pst.setInt(1,t.getIdp());
            pst.setInt(2, t.getCategory_id());*/
            pst.setInt(1, panier.getIdProduit().getIdp());
            pst.setInt(2, panier.getIdUser());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Produit getProduitById(int id) {
        Produit p = new Produit();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM produit WHERE id='" + id + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                p.setIdp(rs.getInt(1));
                p.setNom(rs.getString(3));
                p.setDescrip(rs.getString(4));
                p.setPrix(rs.getInt(5));
                p.setImage(rs.getString(6));

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return p;

    }
    
    public String getEmailFromUser(int idUser) {
        Produit p = new Produit();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM user WHERE id='" + idUser + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                
                return (rs.getString(2));

               

            }

        } catch (SQLException ex) {
            System.out.println("erreur get IdOBJ pour suivi");
            System.out.println(ex);
        }

        return null;

    }

    public List<Produit> afficher(int idUser) {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM panier where idUser=" + idUser;
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produit p = this.getProduitById(rs.getInt(2));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void supprimer(Produit produit, int idUser) {
        try {
            String req = "DELETE FROM panier WHERE idProduit=? and idUser=? ";
            PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1, produit.getIdp());
            pst.setInt(2, idUser);

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    
    public Boolean productExist(Produit produit, int idUser) {
        System.out.println(produit.getIdp()+"user:"+idUser);

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM panier WHERE idProduit="+produit.getIdp()+" and idUser="+idUser ;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

              return true;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;

    }

}
