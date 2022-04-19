/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.ReclamationService;
import Utils.MyDB;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficheReclamationController implements Initializable {

    @FXML
    private TableColumn<Reclamation, Integer> id;
   
    
    @FXML
    private TableView<Reclamation> listrec;
    @FXML
    private TableColumn<Reclamation, String> Etat;
    @FXML
    private TableColumn<Reclamation, ?> DateRec;
    @FXML
    private TableColumn<Reclamation, String> TypeRec;
    @FXML
    private TableColumn<Reclamation, String> DescRec;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
     ObservableList<Reclamation> data = FXCollections.observableArrayList();
     ReclamationService sp = new ReclamationService();
    @FXML
    private Button supprimerr;
    @Override
    //affichage
    public void initialize(URL url, ResourceBundle rb) {
              ReclamationService cs = new ReclamationService();
        List Reclamation = cs.listerReclamation();
        ObservableList list = FXCollections.observableArrayList(Reclamation);
        listrec.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TypeRec.setCellValueFactory(new PropertyValueFactory<>("type"));
        DateRec.setCellValueFactory(new PropertyValueFactory<>("DateRec"));
        DescRec.setCellValueFactory(new PropertyValueFactory<>("description"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationFXML.fxml"));
            Parent root = loader.load();
            label.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReclamationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    
public void refreshlist() {
    data.clear(); 
            List Reclamation = sp.listerReclamation();

            ObservableList data = FXCollections.observableArrayList(Reclamation);

    data= FXCollections.observableArrayList(sp.listerReclamation());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TypeRec.setCellValueFactory(new PropertyValueFactory<>("type"));
        DateRec.setCellValueFactory(new PropertyValueFactory<>("DateRec"));
        DescRec.setCellValueFactory(new PropertyValueFactory<>("description"));
         Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    
    listrec.setItems(data);
    }
   
    @FXML
    private void miseajour(ActionEvent event) {
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException{
          try{
            ReclamationService cs = new ReclamationService();
        cs.supprimerReclamation(listrec.getSelectionModel().getSelectedItem().getId());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
        try{
            ReclamationService cs = new ReclamationService();
            List Equipement = cs.listerReclamation();
        ObservableList list = FXCollections.observableArrayList(Equipement);
        listrec.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TypeRec.setCellValueFactory(new PropertyValueFactory<>("type"));
        DateRec.setCellValueFactory(new PropertyValueFactory<>("DateRec"));
        DescRec.setCellValueFactory(new PropertyValueFactory<>("description"));
         Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
    }

    @FXML
    private void table_view_reclamation_clicke(MouseEvent event) {
        supprimerr.setDisable(false);
        System.out.println("Clicked : "+listrec.getSelectionModel().getSelectedItem().getId());
    }
    
    
}