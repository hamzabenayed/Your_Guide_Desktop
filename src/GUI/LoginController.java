/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DB.MyDB;
import Entities.User;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class LoginController implements Initializable {

    @FXML
    private Button inscrivezvous;
 
    
       public LoginController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = MyDB.getInstance().getConnection();
    }
    Connection connexion;
    public static User connectedUser;
    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    @FXML
    private Button login;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
      private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
    
    @FXML
    private void login(ActionEvent event) throws SQLException, NoSuchAlgorithmException, IOException {
      String req = "SELECT * from user WHERE login LIKE '" + email.getText() + "' and mdp LIKE '" + hashmdp(mdp.getText()) + "' ";

            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
//String req = "INSERT INTO `user` (`email`,`roles`,`password`,`nom`,`prenom`,`cin`,`image`,`sexe`) "
                User p = new User(rst.getInt("id")
                    , rst.getString("login")
                    , rst.getString("roles")
                    , rst.getString("mdp")
                    , rst.getString("nom")
               
                        , rst.getString("image")
             
                      
                
                );
                      
                LoginController.connectedUser = p;  
        
                
                if(LoginController.connectedUser.getRoles().equals("Admin")){
                       Parent page1 = FXMLLoader.load(getClass().getResource("List.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profil");
        stage.setScene(scene);
        stage.show();    
           
                }
                
                else   if(LoginController.connectedUser.getRoles().equals("Client")){
                    
                          Parent page1 = FXMLLoader.load(getClass().getResource("Profil.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profil");
        stage.setScene(scene);
        stage.show();       
                }  
       
        
    }
            
    
}

    @FXML
    private void InscrivezVous(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("Ajout.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profil");
        stage.setScene(scene);
        stage.show();    
        
        
    }
    
  }  
