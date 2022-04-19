/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.ReclamationService;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReclamationFXMLController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private TextField type;
    @FXML
    private TextField description;
    @FXML
    private TextField etat;
    @FXML
    private Label label;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valider(ActionEvent event) {
        
            
        
        
        ReclamationService ps = new ReclamationService();
        Reclamation p = new Reclamation(type.getText(), description.getText(), Date.valueOf(date.getValue()),etat.getText());
                ps.ajoutReclamation(p);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("success");
                alert.setContentText("Reclamation Ajouté");
                alert.show();
                type.setText("");
                description.setText("");
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheReclamation.fxml"));
            Parent root = loader.load();
            label.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficheReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }     
        }
    
    
}
