/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.CommentaireService;
import DB.MyDB;
import Entities.Commentaire;
import Entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

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
    @FXML
    private TextField filterField;
    @FXML
    private Label label;

   
   

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
        
         FilteredList<Commentaire> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(commentaire -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (commentaire.getDesc_comm().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(commentaire.getDate_comm()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(commentaire.getId()).indexOf(lowerCaseFilter)!=-1){
                                    
                                
				     return true;
                                }
                               
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commentaire> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listcomm.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listcomm.setItems(sortedData);
        
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
        
        FilteredList<Commentaire> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(commentaire -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (commentaire.getDesc_comm().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(commentaire.getDate_comm()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(commentaire.getId()).indexOf(lowerCaseFilter)!=-1){
                                    
                                
				     return true;
                                }
                               
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commentaire> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listcomm.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listcomm.setItems(sortedData);

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Commentaire ajoutee");
            alert.show();
            Notifications notifications=Notifications.create();
        notifications.text(" commentaire ajoutée");
        notifications.title("Success Message");
        notifications.darkStyle();
        notifications.show();
            
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
        
        FilteredList<Commentaire> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(commentaire -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (commentaire.getDesc_comm().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(commentaire.getDate_comm()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(commentaire.getId()).indexOf(lowerCaseFilter)!=-1){
                                    
                                
				     return true;
                                }
                               
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commentaire> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listcomm.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listcomm.setItems(sortedData);
                 Notifications notifications=Notifications.create();
        notifications.text(" Commentaire supprimée");
        notifications.title("Success Message");
        notifications.darkStyle();
        notifications.show();
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
         
         FilteredList<Commentaire> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(commentaire -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (commentaire.getDesc_comm().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(commentaire.getDate_comm()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(commentaire.getId()).indexOf(lowerCaseFilter)!=-1){
                                    
                                
				     return true;
                                }
                               
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commentaire> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listcomm.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listcomm.setItems(sortedData);
        
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Commentaire modifiée");
            alert.show();
             Notifications notifications=Notifications.create();
        notifications.text(" Commentaire  modifiée");
        notifications.title("Success Message");
        notifications.darkStyle();
        notifications.show();
            
             
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

    @FXML
    private void weather(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Weather.fxml"));
            Parent root = loader.load();
            label.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
