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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ObjPanierController implements Initializable {

 
    public int idUser;
    @FXML
    private Label lb_desc;
    @FXML
    private ImageView icone;
    private Label lb_rep;
    @FXML
    private Label lb_duree;
    @FXML
    private Label lb_desc1;
    @FXML
    private Button supprimer;
    private Produit produit;
    
    PanierService panierService =new PanierService();
    NotificationService notificationService =new NotificationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initializeFxml(int idUser) {

    }

  

    public void setData(Produit produit,int idUser) {
        this.idUser=idUser;
        this.produit=produit;       

        lb_desc.setWrapText(true);
        lb_desc.setText(produit.getNom());
        lb_duree.setText("" + produit.getPrix()+" DT");
//        lb_date.setText(produit.get);
//        lb_duree.setText("" + evenement.getPrix_event());
//        lb_duree1.setText("" + evenement.getNbr_place());
        lb_desc1.setText("" + produit.getDescrip());
        Image image = new Image(getClass().getResourceAsStream("../images/default.png"));

      
        if (image != null) {
            icone.setImage(image);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        panierService.supprimer(produit, idUser);
        Boolean suppression =notificationService.suppression("Panier","Vous voulez supprimer ce produit de votre panier ");
        if(suppression)
        {
            notificationService.alert("Panier", "Produit supprimé");
            refreshAll();
            
        }
    }
    
    public void refreshAll()
    {
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

        Stage window = (Stage) supprimer.getScene().getWindow();

        window.setScene(new Scene(root, 1000, 718)); 
    }
    
      
   

 

}
