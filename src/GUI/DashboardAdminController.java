/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import static intergrationfinal.IntergrationFinal.mainStage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.Constants;

import Entities.User;
import utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class DashboardAdminController implements Initializable {
  static AnchorPane staticContent;
  public static DashboardAdminController instance;
    @FXML
    private Label label;
    @FXML
    private Button Panier;
    @FXML
    private Button guide;
    
    Stage  primaryStage;
   
    public static DashboardAdminController getInstance() {
    if (instance == null) {
      instance = new DashboardAdminController();
    }
    return instance;
  }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            label.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Poduit(ActionEvent event) throws IOException {
        Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/views/ProduitFX.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();        
    }

    @FXML
    private void Commentaire(ActionEvent event) throws IOException {
        Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/AfficheCommentaire.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }

    @FXML
    private void user(ActionEvent event) throws IOException {
        Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/List.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }

    @FXML
    private void Panier(ActionEvent event) throws IOException {
                  Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/views/Panier.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }
      
    @FXML
    private void guide(ActionEvent event) throws IOException {   
        GuideManage();
    }
    

      public void GuideManage() {
        loadScene(
                Constants.FXML_BACK_MANAGE_GUIDE,
                "Connexion",
                1100,
                600,
                true
        );
    }
    
    public void loadInterface(String location) {
        
        if (getClass().getResource(location) == null) {
            System.out.println("Could not load FXML check the path");
        } else {
            System.out.println("Loading fxml : " + location);
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(location)));
                AnchorPane.setTopAnchor(parent, 0.0);
                AnchorPane.setBottomAnchor(parent, 0.0);
                AnchorPane.setRightAnchor(parent, 0.0);
                AnchorPane.setLeftAnchor(parent, 0.0);
               
            } catch (IOException e) {
                System.out.println("Could not load FXML : " + e.getMessage() + " check your controller");
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void cattt(ActionEvent event) throws IOException {
        Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/views/CategorieFX.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }

    @FXML
    private void Like(ActionEvent event) throws IOException {
               Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/AfficheLike.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
                Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/AfficheReclamation.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }
    
   
    
    private void loadScene(String fxmlLink, String title, int width, int height, boolean isAuthentification) {
        try {
            Stage primaryStage = mainStage;
            primaryStage.close();

            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlLink))));
            scene.setFill(Color.TRANSPARENT);

            //primaryStage.getIcons().add(new Image("app/images/app-icon.png"));
            primaryStage.setTitle(title);
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
            primaryStage.setMinWidth(width);
            primaryStage.setMinHeight(height);
            primaryStage.setScene(scene);
            primaryStage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - (width / 2.0));
            primaryStage.setY((Screen.getPrimary().getBounds().getHeight() / 2) - (height / 2.0));

            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void aaaa(ActionEvent event) {
        Acctivite();
        
    }
  public void Acctivite() {
        loadScene(
                Constants.FXML_BACK_MANAGE_ACTIVITE,
                "Connexion",
                1100,
                600,
                true
        );
    }   
  public void AG() {
        loadScene(
                Constants.FXML_BACK_DISPLAY_ALL_GUIDE,
                "Connexion",
                1100,
                600,
                true
        );
    } 
    public void AA() {
        loadScene(
                Constants.FXML_BACK_DISPLAY_ALL_ACTIVITE,
                "Connexion",
                1100,
                600,
                true
        );
    } 
    @FXML
    private void AffG(ActionEvent event) {
        AG();
    }

    @FXML
    private void AffA(ActionEvent event) {
        AA();
    }
}
