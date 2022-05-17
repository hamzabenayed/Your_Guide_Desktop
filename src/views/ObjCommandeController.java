/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Commande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class ObjCommandeController implements Initializable {

    @FXML
    private ImageView icone;
    @FXML
    private Label lb_desc1;
    @FXML
    private Label lb_desc;
    @FXML
    private Label lb_duree;
    public Commande commande;
    public int idUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setData(Commande commande,int idUser) {
        this.idUser=idUser;
        this.commande=commande;       

        lb_desc.setWrapText(true);
        lb_desc.setText(commande.getVille());
        lb_duree.setText("" + commande.getPrix()+" DT");
//        lb_date.setText(produit.get);
//        lb_duree.setText("" + evenement.getPrix_event());
//        lb_duree1.setText("" + evenement.getNbr_place());
        lb_desc1.setText("" + commande.getEtat());
        Image image = new Image(getClass().getResourceAsStream("../images/default.png"));

      
        if (image != null) {
            icone.setImage(image);
        }
    
}}
