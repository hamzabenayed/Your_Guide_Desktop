/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Commande;
import Entities.Produit;
import Services.CommandeService;
import Services.NotificationService;
import Services.PanierService;
import Services.SendMail;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class FormCommandeController implements Initializable {

    CommandeService commandeService = new CommandeService();
    PanierService panierService = new PanierService();

    NotificationService notificationService = new NotificationService();

    int idUser;
    @FXML
    private TextField labelRue;
    @FXML
    private TextField labelCode;
    @FXML
    private TextField labelVille;
    @FXML
    private TextArea labelAdresse;
    @FXML
    private Button showCommande;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void SaveCom(ActionEvent event) {

        String rue = this.labelRue.getText();
        String ville = this.labelVille.getText();
        String code = this.labelCode.getText();
        String adresse = this.labelAdresse.getText();

        if (rue.length() == 0 || ville.length() == 0 || code.length() == 0 || adresse.length() == 0) {
            notificationService.alert("Commande", "Veuillez remplir vos informations");

        } else {
            Commande commande = new Commande(rue, code, ville, adresse);
            ObservableList<Produit> produits = FXCollections.observableArrayList(panierService.afficher(idUser));
            long timestamp = commandeService.ajouter(commande, idUser);
int somme=0;
            for(Produit produit:produits)
            {
                
                commandeService.affecter(produit, timestamp);
                somme+=produit.getPrix();}
            commandeService.calculerPrix(somme, timestamp);
                
            

            notificationService.alert("Commande", "Commande effectuée");
            String email=panierService.getEmailFromUser(idUser);
                           SendMail sendMail = new SendMail();

            if(email!=null)
                

    sendMail.envoyerMail(email, "Nouvelle commande", "Votre nouvelle commande est en cours de verification Merci ");
                
        }

    }

    @FXML
    private void showCommande(ActionEvent event) {
        
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
        


        Stage window = (Stage) showCommande.getScene().getWindow();

        window.setScene(new Scene(root, 1000, 718));
    }

    @FXML
    private void retour(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Panier.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ProduitFXController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PanierController HomeScene = loader.getController();
        HomeScene.idUser = this.idUser;

        HomeScene.initializeFxml();

        Stage window = (Stage) retour.getScene().getWindow();

        window.setScene(new Scene(root, 1000, 718));
    }

}
