/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Entities.Panier;
import Entities.Produit;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Utilisateur
 */
public class CommandeService {

    Connection cnx = DataSource.getInstance().getCnx();

    public long getCurrentDate() {
        return new Date().getTime();
    }

    public long ajouter(Commande commande, int idUser) {
        long timestamp = getCurrentDate();

        try {
            String req = "INSERT INTO commande (rue,code,ville,adresse,idUser,timestamp) VALUES (?,?,?,?,?,?)";
            System.out.println();
            PreparedStatement pst = cnx.prepareStatement(req);
            /*pst.setInt(1,t.getIdp());
            pst.setInt(2, t.getCategory_id());*/
            pst.setString(1, commande.getRue());
            pst.setString(2, commande.getCode());
            pst.setString(3, commande.getVille());
            pst.setString(4, commande.getAdresse());
            pst.setInt(5, idUser);
            pst.setLong(6, timestamp);

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return timestamp;

    }

    public void affecter(Produit produit, long timestamp) {
        Commande commande = new Commande();
        commande = this.getCommandeByTimestamp(timestamp);

        try {
            String req = "INSERT INTO commande_produit (idCommande,idProduit) VALUES (?,?)";
            System.out.println();
            PreparedStatement pst = cnx.prepareStatement(req);
            /*pst.setInt(1,t.getIdp());
            pst.setInt(2, t.getCategory_id());*/
            pst.setInt(1, commande.getId());
            pst.setInt(2, produit.getIdp());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Commande getCommandeByTimestamp(long timestamp) {
        Commande commande = new Commande();

        try {
            Statement st = cnx.createStatement();
            String query = "select * FROM commande WHERE timestamp='" + timestamp + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                commande.setId(rs.getInt(1));

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return commande;

    }

    public List<Commande> afficher(int idUser) {
        List<Commande> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM commande where idUser=" + idUser;
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commande commande = new Commande();

                commande.setId(rs.getInt(1));
                commande.setRue(rs.getString(2));
                commande.setCode(rs.getString(3));  
                commande.setVille(rs.getString(4));
                commande.setAdresse(rs.getString(5));
                commande.setEtat(rs.getString(6));
                commande.setPrix(rs.getInt(7));

                list.add(commande);
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
        System.out.println(produit.getIdp() + "user:" + idUser);

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM panier WHERE idProduit=" + produit.getIdp() + " and idUser=" + idUser;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                return true;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;

    }
    
     public void calculerPrix(int prix,long timestamp) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
 Commande commande = new Commande();
        commande = this.getCommandeByTimestamp(timestamp);      
        
        try {
            Statement st = cnx.createStatement();
           
            String query = "UPDATE  commande SET prix='"+prix+"' where id="+commande.getId();
            System.out.println(query);
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ");
            System.out.println(ex);
        }
    }

}
