/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDB;
import entities.Likes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class LikesService {
      static Connection cn = MyDB.getInstance().getConnection();
     Statement st;
    
   //Ajout Reclamation
        public  void ajoutLike(Likes  l) 
       {           
           
           try {
               
               String requete =" INSERT INTO `like`(`commentaire_id`, `nom_like`, `rate`, `note`) VALUES ('"+l.getCom()+"','"+l.getNom_like()+"','"+l.getRate()+"','"+l.getNote()+"')";
               //String requete="INSERT INTO Like (nom_like,rate,note,commentaire_id) values ('"+l.getNom_like()+"','"+l.getRate()+"','"+l.getNote()+"','"+l.getCom()+"')";
              
               st = cn.createStatement();
               st.executeUpdate(requete);
            
        } catch (SQLException ex) {
            System.err.println("Error d'insertion"+ex);
        }
       
       }
        
        //supprimer Reclamation
         
            public  void supprimerLike(int id) 
              {
                     try {
               cn.createStatement().execute("Delete from `like` where id="+id+";");
               
        } catch (SQLException ex) {
            System.err.println("Error d'suppression"+ex);
        }
              
              }
            
            
           // modifier Reclamation            
    public void updateLike(Likes l, int id ) throws SQLException {
         try {
            Statement statement= cn.createStatement();
            String requete="update `like` set commentaire_id='"+l.getCom()+"',nom_like='"+l.getNom_like()+"',rate='"+l.getRate()+"',note='"+l.getNote()+"'where id= '"+id+"'";
            statement.executeUpdate(requete);
            System.out.print("Updated !!");
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        
    }
    
    
    // afficher tous les Reclamations
    
    public List<Likes> listerLike(){
        List<Likes> ListR = new ArrayList();
        
        try {
            String req = "Select * from `Like`";
            st = cn.createStatement();
            ResultSet rst = st.executeQuery(req);
             
            while(rst.next()){
                 
                 Likes p = new Likes(rst.getInt("id"),rst.getInt("commentaire_id"),rst.getString("nom_like"),rst.getInt("rate"),rst.getInt("note"));
                 ListR.add(p);
            }
            
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
          return ListR ;
    }
    
    public List<Likes> afficherByNote() {
        List<Likes> reclamations = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM `like` order by note";
            st = cn.createStatement();
            ResultSet rst = st.executeQuery(req);
           
            while (rst.next()) {
                 Likes t = new Likes(rst.getInt("id"),rst.getInt("commentaire_id"),rst.getString("nom_like"),rst.getInt("rate"),rst.getInt("note"));
                reclamations.add(t);   
            }
           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
        return reclamations;
    } 
    
}
