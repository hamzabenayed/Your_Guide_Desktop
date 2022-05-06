/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import entities.Reclamation;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
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
 * @author asus
 */
public class ReclamationService {
     static Connection cn = MyDB.getInstance().getConnection();
     Statement st;
    
   //Ajout Reclamation
        public  void ajoutReclamation(Reclamation  l) 
       {           
           
           try {
               String requete="INSERT INTO Reclamation (type_reclamation,desc_reclamation,date_reclamation,etat) values ('"+l.getType()+"','"+l.getDescription()+"','"+l.getDateRec()+"','"+l.getEtat()+"')";
              
              
               st = cn.createStatement();
               st.executeUpdate(requete);
            
        } catch (SQLException ex) {
            System.err.println("Error d'insertion"+ex);
        }
       
       }
        
        //supprimer Reclamation
         
            public  void supprimerReclamation(int id) 
              {
                     try {
               cn.createStatement().execute("Delete from Reclamation where id="+id+";");
               
        } catch (SQLException ex) {
            System.err.println("Error d'suppression"+ex);
        }
              
              }
            
            
           // modifier Reclamation            
    public void updateReclamation(Reclamation l, int id ) throws SQLException {
         try {
            Statement statement= cn.createStatement();
            String requete="update Reclamation set type_reclamation='"+l.getType()+"' ,  desc_reclamation='"+l.getDescription()+"' ,  date_reclamation='"+l.getDateRec()+"',etat='"+l.getEtat()+"'where id= '"+id+"'";
            statement.executeUpdate(requete);
            System.out.print("Updated !!");
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        
    }
    
    
    // afficher tous les Reclamations
    
    public List<Reclamation> listerReclamation(){
        List<Reclamation> ListR = new ArrayList();
        
        try {
            String req = "Select * from `Reclamation`";
            st = cn.createStatement();
            ResultSet rst = st.executeQuery(req);
             
            while(rst.next()){
                 
                 Reclamation p = new Reclamation(rst.getInt("id"),rst.getString("type_reclamation"),rst.getString("desc_reclamation"),rst.getString("etat"),rst.getDate("date_reclamation"));
                 ListR.add(p);
            }
            
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
          return ListR ;
    }
             
    public List<Reclamation> afficherByDate(String type) {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from reclamation where date_reclamation =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            preparedStatement.setString(1,type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 Reclamation t = new Reclamation(resultSet.getInt("id"),resultSet.getString("type_reclamation"),resultSet.getString("desc_reclamation"),resultSet.getString("etat"),resultSet.getDate("date_reclamation"));
                reclamations.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                
        return reclamations;
    } 
    
     public List<Reclamation> afficherByDate() {
        List<Reclamation> reclamations = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM `reclamation` order by date_reclamation";
            st = cn.createStatement();
            ResultSet rst = st.executeQuery(req);
           
            while (rst.next()) {
                 Reclamation t = new Reclamation(rst.getInt("id"),rst.getString("type_reclamation"),rst.getString("desc_reclamation"),rst.getString("etat"),rst.getDate("date_reclamation"));
                reclamations.add(t);   
            }
           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
        return reclamations;
    } 
    /* public List<Reclamation> QueryByDate() {
        List<Reclamation> reclamations = new ArrayList<>();
        
        try {
      String sql_query = "select *" + "from reclamation" + "order by date_reclamation'"; 
            st = cn.createStatement();
            ResultSet rst = st.executeQuery(sql_query);
           
            while (rst.next()) {
                 Reclamation t = new Reclamation(rst.getInt("id"),rst.getString("type_reclamation"),rst.getString("desc_reclamation"),rst.getString("etat"),rst.getDate("date_reclamation"));
                reclamations.add(t);   
            }
           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
        return reclamations;
    } */
}
