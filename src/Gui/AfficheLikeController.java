/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.CommentaireService;
import Services.LikesService;
import Utils.MyDB;
import entities.Commentaire;
import entities.Likes;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class AfficheLikeController implements Initializable {

    @FXML
    private TextField tfnomlike;
    @FXML
    private ComboBox<String> tfcom;
    @FXML
    private TextField tfrate;
    @FXML
    private TextField tfnote;
    @FXML
    private TableView<Likes> listl;
    @FXML
    private TableColumn<Likes, Integer> id;
    @FXML
    private TableColumn<Likes, String> nomlike;
    @FXML
    private TableColumn<Likes, String> comm;
    @FXML
    private TableColumn<Likes, Integer> rate;
    @FXML
    private TableColumn<Likes, Integer> note;
    @FXML
    private Button supprimerl;
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         LikesService cs = new LikesService();
        List Likes = cs.listerLike();
        ObservableList list = FXCollections.observableArrayList(Likes);
        listl.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        nomlike.setCellValueFactory(new PropertyValueFactory<>("nom_like"));
        comm.setCellValueFactory(new PropertyValueFactory<>("com"));

        afficherCommentaireDesc();
        
        FilteredList<Likes> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(likes -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (likes.getNom_like().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(likes.getRate()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(likes.getNote()).indexOf(lowerCaseFilter)!=-1){
                                    
                                
				     return true;
                                }else if (String.valueOf(likes.getCom()).indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }else if (String.valueOf(likes.getId()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Likes> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listl.setItems(sortedData);


    }    

    @FXML
    private void table_view_like_clicke(MouseEvent event) {
         System.out.println("Clicked : "+listl.getSelectionModel().getSelectedItem().getId());
            tfrate.setText(listl.getSelectionModel().getSelectedItem().getRate()+"");
            tfnomlike.setText(listl.getSelectionModel().getSelectedItem().getNom_like());
            tfnote.setText(listl.getSelectionModel().getSelectedItem().getNote()+"");
            tfcom.setValue(listl.getSelectionModel().getSelectedItem().getCom()+"");


    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        if (nomlike.getText().isEmpty() || tfrate.getText().isEmpty()|| tfnote.getText().isEmpty()|| tfcom.getValue().isEmpty())
       {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Remplir les champs vide");
            alert.show();

       } 
       else{

          LikesService ps = new LikesService();
          CommentaireService ks = new CommentaireService();
          

        Likes p = new Likes(tfnomlike.getText(), Integer.parseInt(tfnote.getText()),Integer.parseInt(tfrate.getText()),ks.getIdCommentaire(tfcom.getValue()));
         
        try {

            ps.ajoutLike(p);   
            try{
            List Likes = ps.listerLike();
        ObservableList list = FXCollections.observableArrayList(Likes);
        listl.setItems(list);
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomlike.setCellValueFactory(new PropertyValueFactory<>("nom_like"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        comm.setCellValueFactory(new PropertyValueFactory<>("com"));
        
         FilteredList<Likes> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(likes -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (likes.getNom_like().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(likes.getRate()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(likes.getNote()).indexOf(lowerCaseFilter)!=-1){
                                    
                                
				     return true;
                                }else if (String.valueOf(likes.getCom()).indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }else if (String.valueOf(likes.getId()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Likes> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listl.setItems(sortedData);

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Like ajoutee");
            alert.show();
            
            tfnote.setText("");
            tfrate.setText("");
            tfcom.setValue("");
           tfnomlike.setText("");
            
            
            
        } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
        } 
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException{
         try{
            LikesService cs = new LikesService();
        cs.supprimerLike(listl.getSelectionModel().getSelectedItem().getId());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
        try{
            LikesService cs = new LikesService();
            List Likes = cs.listerLike();
        ObservableList list = FXCollections.observableArrayList(Likes);
        listl.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        nomlike.setCellValueFactory(new PropertyValueFactory<>("nom_like"));
        comm.setCellValueFactory(new PropertyValueFactory<>("com"));
        
         FilteredList<Likes> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(likes -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (likes.getNom_like().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(likes.getRate()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(likes.getNote()).indexOf(lowerCaseFilter)!=-1){
                                    
                                
				     return true;
                                }else if (String.valueOf(likes.getCom()).indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }else if (String.valueOf(likes.getId()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Likes> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listl.setItems(sortedData);


        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            tfnomlike.setText("");
            tfcom.setValue("");
            tfnote.setText("");
            tfrate.setText("");

    }

    @FXML
    private void miseajour(ActionEvent event) {
         if (nomlike.getText().isEmpty() || tfrate.getText().isEmpty()|| tfnote.getText().isEmpty()|| tfcom.getValue().isEmpty())
       {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Remplir les champs vide");
            alert.show();

       } 
       else{
         LikesService ps = new LikesService();
         CommentaireService ks = new CommentaireService();

        Likes p = new Likes(tfnomlike.getText(), Integer.parseInt(tfnote.getText()),Integer.parseInt(tfrate.getText()),ks.getIdCommentaire(tfcom.getValue()));
        try {
            ps.updateLike(p, listl.getSelectionModel().getSelectedItem().getId());
            try{
            LikesService cs = new LikesService();
            List Likes = cs.listerLike();
        ObservableList list = FXCollections.observableArrayList(Likes);
        listl.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        nomlike.setCellValueFactory(new PropertyValueFactory<>("nom_like"));
        comm.setCellValueFactory(new PropertyValueFactory<>("com"));
        
         FilteredList<Likes> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(likes -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (likes.getNom_like().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(likes.getRate()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(likes.getNote()).indexOf(lowerCaseFilter)!=-1){
                                    
                                
				     return true;
                                }else if (String.valueOf(likes.getCom()).indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }else if (String.valueOf(likes.getId()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Likes> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listl.setItems(sortedData);


        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Like modifiée");
            alert.show();
            
             tfnomlike.setText("");
            tfcom.setValue("");
            tfnote.setText("");
                        tfrate.setText("");

            
            
            
        } catch (Exception e) {
JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
         }
    }
 
    private void afficherCommentaireDesc() {
        CommentaireService cs = new CommentaireService();
        List<Commentaire> fcts = cs.listerCommentaire();
        ObservableList<String> descComm = FXCollections.observableArrayList();
        for(int i = 0; i<fcts.size();i++){
            descComm.add(fcts.get(i).getDesc_comm());
        }
       tfcom.setItems(descComm); //pour remplir le combo box   
    }

    @FXML
    private void tribynote(ActionEvent event) {
         LikesService ts = new LikesService();
                    
        List <Likes> likes = ts.afficherByNote();
        ObservableList list = FXCollections.observableArrayList(likes);
       listl.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        nomlike.setCellValueFactory(new PropertyValueFactory<>("nom_like"));
        comm.setCellValueFactory(new PropertyValueFactory<>("com"));
        
         FilteredList<Likes> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(likess -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (likess.getNom_like().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(likess.getRate()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(likess.getNote()).indexOf(lowerCaseFilter)!=-1){
                                    
                                
				     return true;
                                }else if (String.valueOf(likess.getCom()).indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }else if (String.valueOf(likess.getId()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Likes> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listl.setItems(sortedData);

    }
    
}
