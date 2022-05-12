/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.models.Categorie;
import com.esprit.services.ImCategorie;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

//import java.util.regex.Matches;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import static javafx.scene.control.cell.CheckBoxTableCell.forTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hedia
 */
public class CategorieFXController implements Initializable {

    @FXML
    private TableView<Categorie> TableC;
    @FXML
    private TextField NomC;
    @FXML
    private Button DeleteC;
    @FXML
    private TableColumn<Categorie,Integer > col_id;
    @FXML
    private TableColumn< Categorie,String> colName;
    private  ImCategorie ic = new ImCategorie();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Categorie> ll = ic.afficher();
        // TODO
        TableC.setEditable(true);
        col_id.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("ID"));
  
        //col_date.setCellFactory(TextFieldTableCell.forTableColumn());
        
        colName.setCellValueFactory(new PropertyValueFactory<Categorie,String>("Nom"));
        colName.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        TableC.getItems().setAll(ll);
        
            colName.setOnEditCommit(new EventHandler<CellEditEvent<Categorie, String>>() {
          
            public void handle(CellEditEvent<Categorie, String> event) {
                Categorie tt = event.getRowValue();
                tt.setNom(event.getNewValue());
              
               ic.modifier(tt);

            }});
                
        
    }    

    @FXML
    private void ajouterC(ActionEvent event) {
      String s ="^[0-9]";
//        Pattern empat = Pattern.compile(s,Pattern.CASE_INSENSITIVE);
//        Matches m = emapat.matches(NomC.getText().toString());
//        boolean bb;
//        bb=m.
        if(NomC.getText().equals("") || NomC.getText().contains(s)){
                    Notifications notifications=Notifications.create();
                    notifications.text("Hello please fill the required fields");
                    notifications.title("Failed Message");
                    notifications.show();

    }
        else{
            
            ic.ajouter(new Categorie(NomC.getText()));
             Notifications notifications=Notifications.create();
            notifications.text("Ajoute");
            notifications.title("Suceess");
            notifications.show();
            initialize(null,null);
        }
    }

    @FXML
    private void supprimerC(ActionEvent event) {
        Categorie C = new Categorie();
            System.out.println("****");
        C=TableC.getSelectionModel().getSelectedItems().get(0);
        ic.supprimer(C);
         initialize(null,null);
    }
    
}
