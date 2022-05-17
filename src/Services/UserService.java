/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDB;
import Entities.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
 * @author Administrateur
 */
public class UserService {
    Connection connexion;   
  public UserService() {
        connexion = MyDB.getInstance().getConnection();
    }


 
 
     
 
  public void ajouterUser(User u) throws SQLException {
        String req = "INSERT INTO `user` (`login`,`roles`,`mdp`,`nom`,`image`) "
                + "VALUES (?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, u.getLogin());
        ps.setString(2, u.getRoles());
        try {
            ps.setString(3, hashmdp (u.getMdp()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
           ps.setString(4, u.getNom());
         
                 ps.setString(5, u.getImage());
                 
                       
                       
    

        ps.executeUpdate();
    }
  ////////
     
    public String hashmdp (String mdp) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     
    
       return hexString.toString();
    }
  
  //////////
  

     public List<User> AfficherAllUsers() throws SQLException {

        List<User> Users = new ArrayList<>();
        String req = "select * from user ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User u = new User(rst.getInt("id")
                    , rst.getString("login")
                    , rst.getString("roles")
                    , rst.getString("mdp")
                    , rst.getString("nom")
                     , rst.getString("image")
                   );
            Users.add(u);
        }
        return Users;
    }
     

     public void SupprimerUser(User u) throws SQLException {

        String req = "DELETE FROM user WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
       

      public void modifierUser(User u) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE user SET "
                + " login='"+u.getLogin()+"'"
                + ", roles='"+u.getRoles()+"'"
                 + ", mdp='"+hashmdp(u.getMdp())+"'"
                + ", nom='"+u.getNom()+"'"
           
 
                 
                
                
                + ", image='"+u.getImage()+"' where id  = "+u.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
      
   
     
     
            
             

   

   
 
 
   
 
}
