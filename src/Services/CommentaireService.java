/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDB;
import Entities.Commentaire;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class CommentaireService {
     static Connection cn = MyDB.getInstance().getConnection();
     Statement st;
    
   //Ajout Commentaire
        public  void ajoutCommentaire(Commentaire  l) 
       {           
           
           try {
               String requete="INSERT INTO Commentaire (description_commentaire,date_commentaire) values ('"+l.getDesc_comm()+"','"+l.getDate_comm()+"')";
              
              
               st = cn.createStatement();
               st.executeUpdate(requete);
            
        } catch (SQLException ex) {
            System.err.println("Error d'insertion"+ex);
        }
       
       }
        
        //supprimer Commentaire
         
            public  void supprimerCommentaire(int id) 
              {
                     try {
               cn.createStatement().execute("Delete from Commentaire where id="+id+";");
               
        } catch (SQLException ex) {
            System.err.println("Error d'suppression"+ex);
        }
              
              }
            
            
           // modifier Commentaire            
    public void updateCommentaire(Commentaire l, int id ) throws SQLException {
         try {
            Statement statement= cn.createStatement();
            String requete="update Commentaire set description_commentaire='"+l.getDesc_comm()+"' ,  date_commentaire='"+l.getDate_comm()+"'where id= '"+id+"'";
            statement.executeUpdate(requete);
            System.out.print("Updated !!");
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        
    }
    
    
    // afficher tous les Commentaires
    
    public List<Commentaire> listerCommentaire(){
        List<Commentaire> ListR = new ArrayList();
        
        try {
            String req = "Select * from `Commentaire`";
            st = cn.createStatement();
            ResultSet rst = st.executeQuery(req);
             
            while(rst.next()){
                 
                 Commentaire p = new Commentaire(rst.getInt("id"),rst.getString("description_commentaire"),rst.getDate("date_commentaire"));
                 ListR.add(p);
            }
            
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
          return ListR ;
    }
    
        public int getIdCommentaire(String desc) {
        try {
            String req ="SELECT id from `Commentaire` WHERE description_commentaire ='"+desc+"'";
            st = cn.createStatement();
            ResultSet rst = st.executeQuery(req);
            if (rst.next()){
                int i = rst.getInt("id");
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;        
    }
             
    
}
