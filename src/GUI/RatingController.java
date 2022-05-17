/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.controlsfx.control.*;
import Gui.AfficheReclamationController;
import Entities.Reclamation;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class RatingController implements Initializable {

    @FXML
    private Label msg;
     @FXML
    private Rating rate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         rate.ratingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            msg.setText("Rating :- "+newValue);
        });
    }    

    @FXML
    private void Submit(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheReclamation.fxml"));
            Parent root = loader.load();
            msg.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficheReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    
}
