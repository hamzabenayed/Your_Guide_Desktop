/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Produit;
import Services.NotificationService;
import Services.PanierService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class PanierController implements Initializable {
     PanierService panierService =new PanierService();
    NotificationService notificationService =new NotificationService();
    public int idUser;
    @FXML
    private HBox hbox;
    @FXML
    private ScrollPane scroll_obj;
    @FXML
    private GridPane grid_obj;
    @FXML
    private Button retour;
    @FXML
    private Button commander;
    @FXML
    private Button mesCommandes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
      public void initializeFxml() {
        show();
    }
    
     public void show() {
        ObservableList<Produit> produits = FXCollections.observableArrayList(panierService.afficher(idUser));
        grid_obj.getChildren().clear();

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ObjPanier.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ObjPanierController itmc = fxmlloader.getController();
                itmc.idUser = idUser;
                itmc.setData(produits.get(i), idUser);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid_obj.add(anchorPane, column++, row);
                grid_obj.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid_obj.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid_obj.setMaxWidth(Region.USE_PREF_SIZE);

                grid_obj.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid_obj.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid_obj.setMaxHeight(Region.USE_PREF_SIZE);

                //grid_obj.setStyle("-fx-background-image: url(\"/Images/back.png\");");
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void retour(ActionEvent event) {
        
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitFX.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ProduitFXController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ProduitFXController HomeScene = loader.getController();


        Stage window = (Stage) retour.getScene().getWindow();

        window.setScene(new Scene(root, 1000, 718)); 
    }

    @FXML
    private void commander(ActionEvent event) {
        
        
            
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FormCommande.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ProduitFXController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FormCommandeController HomeScene = loader.getController();
        HomeScene.idUser=this.idUser;


        Stage window = (Stage) retour.getScene().getWindow();

        window.setScene(new Scene(root, 1000, 718));
        
        
    }

    @FXML
    private void mesCommandes(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Commande.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ProduitFXController.class.getName()).log(Level.SEVERE, null, ex);
        }

        CommandeController HomeScene = loader.getController();
        HomeScene.idUser=this.idUser;
        HomeScene.initializeFxml();
        


        Stage window = (Stage) mesCommandes.getScene().getWindow();

        window.setScene(new Scene(root, 1000, 718));
    }
    
}
