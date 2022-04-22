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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
     
    @FXML
    private Button supprimerr;
    @FXML
    private DatePicker tfdaterec;
    @FXML
    private TextField tftyperec;
    @FXML
    private TextField tfdescrec;
    @FXML
    private TextField tfetat;
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
    
  // Add
    @FXML
    private void ajouter(ActionEvent event)  throws IOException{
         if (tftyperec.getText().isEmpty() || tfdescrec.getText().isEmpty()|| tfetat.getText().isEmpty()|| tfdaterec.getValue()==null)
       {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Remplir les champs vide");
            alert.show();

       } 
       else{
          ReclamationService ps = new ReclamationService();

        Reclamation p = new Reclamation(tftyperec.getText(), tfdescrec.getText(), Date.valueOf(tfdaterec.getValue()),tfetat.getText());
        try {

            ps.ajoutReclamation(p);            try{
            ReclamationService cs = new ReclamationService();
            List Reclamation = cs.listerReclamation();
        ObservableList list = FXCollections.observableArrayList(Reclamation);
        listrec.setItems(list);
        TypeRec.setCellValueFactory(new PropertyValueFactory<>("type"));
        DateRec.setCellValueFactory(new PropertyValueFactory<>("DateRec"));
        DescRec.setCellValueFactory(new PropertyValueFactory<>("description"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Reclamation ajoutee");
            alert.show();
            
            tftyperec.setText("");
            tfdescrec.setText("");
           tfdaterec.setValue(null);
           tfetat.setText("");
            
            
            
        } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
         }   
    
}
   //Update
    @FXML
    private void miseajour(ActionEvent event) throws IOException {
          if (tftyperec.getText().isEmpty() || tfdescrec.getText().isEmpty()|| tfetat.getText().isEmpty()|| tfdaterec.getValue()==null)
       {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Remplir les champs vide");
            alert.show();

       } 
       else{
         ReclamationService ps = new ReclamationService();

        Reclamation p = new Reclamation(tftyperec.getText(), tfdescrec.getText(), Date.valueOf(tfdaterec.getValue()),tfetat.getText());
        try {
            ps.updateReclamation(p, listrec.getSelectionModel().getSelectedItem().getId());
            try{
            ReclamationService cs = new ReclamationService();
            List Reclamation = cs.listerReclamation();
        ObservableList list = FXCollections.observableArrayList(Reclamation);
        listrec.setItems(list);
        TypeRec.setCellValueFactory(new PropertyValueFactory<>("type"));
        DateRec.setCellValueFactory(new PropertyValueFactory<>("DateRec"));
        DescRec.setCellValueFactory(new PropertyValueFactory<>("description"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Reclamation modifiée");
            alert.show();
            
            tftyperec.setText("");
            tfdescrec.setText("");
            tfdaterec.setValue(null);
            tfetat.setText("");
            
            
            
        } catch (Exception e) {
JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
          }
        
    }
    
   // Delete
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
            List Reclamation = cs.listerReclamation();
        ObservableList list = FXCollections.observableArrayList(Reclamation);
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
            tftyperec.setText("");
            tfdescrec.setText("");
            tfdaterec.setValue(null);
            tfetat.setText("");
    }

    @FXML
    private void table_view_reclamation_clicke(MouseEvent event) {
            supprimerr.setDisable(false);
            System.out.println("Clicked : "+listrec.getSelectionModel().getSelectedItem().getId());
            tfdaterec.setValue(LocalDate.now());
            tftyperec.setText(listrec.getSelectionModel().getSelectedItem().getType());
            tfdescrec.setText(listrec.getSelectionModel().getSelectedItem().getDescription()+"");
            tfetat.setText(listrec.getSelectionModel().getSelectedItem().getEtat());
    }
    
    
}