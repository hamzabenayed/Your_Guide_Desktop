/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.CommentaireService;
import Utils.MyDB;
import entities.Commentaire;
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
public class AfficheCommentaireController implements Initializable {

    @FXML
    private TextField tfdesccomm;
    @FXML
    private TableView<Commentaire> listcomm;
    @FXML
    private TableColumn<Commentaire, Integer> id;
    @FXML
    private TableColumn<Commentaire, String> desccomm;
    @FXML
    private TableColumn<Commentaire, ?> datecomm;
    @FXML
    private Button supprimerc;
    @FXML
    private DatePicker tfdatecomm;

   
   

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          CommentaireService cs = new CommentaireService();
        List Commentaire = cs.listerCommentaire();
        ObservableList list = FXCollections.observableArrayList(Commentaire);
        listcomm.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        desccomm.setCellValueFactory(new PropertyValueFactory<>("desc_comm"));
        datecomm.setCellValueFactory(new PropertyValueFactory<>("date_comm"));
        
    }    


    @FXML
    private void ajouter(ActionEvent event) throws IOException{
          if (tfdesccomm.getText().isEmpty() || tfdatecomm.getValue()==null)
       {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Remplir les champs vide");
            alert.show();

       } 
       else{
        CommentaireService ps = new CommentaireService();

        Commentaire p = new Commentaire(tfdesccomm.getText(),Date.valueOf(tfdatecomm.getValue()));
        try {

            ps.ajoutCommentaire(p);      
            try{
            CommentaireService cs = new CommentaireService();
            List Commentaire = cs.listerCommentaire();
        ObservableList list = FXCollections.observableArrayList(Commentaire);
        listcomm.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        desccomm.setCellValueFactory(new PropertyValueFactory<>("desc_comm"));
        datecomm.setCellValueFactory(new PropertyValueFactory<>("date_comm"));

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Commentaire ajoutee");
            alert.show();
            
            tfdesccomm.setText("");
            tfdatecomm.setValue(null);
            
            
            
        } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
        
          }     
      
    }
    


    @FXML
    private void supprimer(ActionEvent event) throws IOException{
         try{
            CommentaireService cs = new CommentaireService();
        cs.supprimerCommentaire(listcomm.getSelectionModel().getSelectedItem().getId());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
        try{
            CommentaireService cs = new CommentaireService();
            List Commentaire = cs.listerCommentaire();
        ObservableList list = FXCollections.observableArrayList(Commentaire);
        listcomm.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        desccomm.setCellValueFactory(new PropertyValueFactory<>("desc_comm"));
        datecomm.setCellValueFactory(new PropertyValueFactory<>("date_comm"));
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }  tfdesccomm.setText("");
           tfdatecomm.setValue(null);
    }

    @FXML
    private void miseajour(ActionEvent event) throws IOException{
           if (tfdesccomm.getText().isEmpty() || tfdatecomm.getValue()==null)
       {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Remplir les champs vide");
            alert.show();

       } 
       else{
          CommentaireService ps = new CommentaireService();

        Commentaire p = new Commentaire(tfdesccomm.getText(), Date.valueOf(tfdatecomm.getValue()));
        try {
            ps.updateCommentaire(p, listcomm.getSelectionModel().getSelectedItem().getId());
            try{
            CommentaireService cs = new CommentaireService();
            List Commentaire = cs.listerCommentaire();
        ObservableList list = FXCollections.observableArrayList(Commentaire);
        listcomm.setItems(list);
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
         desccomm.setCellValueFactory(new PropertyValueFactory<>("desc_comm"));
         datecomm.setCellValueFactory(new PropertyValueFactory<>("date_comm"));
        
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Commentaire modifiée");
            alert.show();
            
             
            tfdesccomm.setText("");
            tfdatecomm.setValue(null);
            
            
        } catch (Exception e) {
JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
           }
    }

    @FXML
    private void table_view_commentaire_clicke(MouseEvent event) {
            System.out.println("Clicked : "+listcomm.getSelectionModel().getSelectedItem().getId());
            tfdatecomm.setValue(LocalDate.now());
            tfdesccomm.setText(listcomm.getSelectionModel().getSelectedItem().getDesc_comm()+"");
    }
    
}
