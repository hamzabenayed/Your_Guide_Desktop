/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import static intergrationfinal.IntergrationFinal.mainStage;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.Constants;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ProfilController implements Initializable {

    @FXML
    private Label nom;

    @FXML
    private Label roles;
    private Label sexe;
    @FXML
    private Button supp1;
    @FXML
    private TextField Nom2;
    @FXML
    private TextField email2;
    @FXML
    private TextField mdp2;
    @FXML
    private Label email;
    @FXML
    private Button Confirmer;
    @FXML
    private Label labelid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setText(LoginController.connectedUser.getNom());
        roles.setText(LoginController.connectedUser.getRoles());
        email.setText(LoginController.connectedUser.getLogin());
        labelid.setText(Integer.toString((int) LoginController.connectedUser.getId()));

    }

    @FXML
    private void Modif(ActionEvent event) {
        Nom2.setVisible(true);

        email2.setVisible(true);

        mdp2.setVisible(true);

        Nom2.setText(nom.getText());

        email2.setText(email.getText());

        mdp2.setText(LoginController.connectedUser.getMdp());

    }

    private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    @FXML
    private void Confirmer(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {

        UserService productService = new UserService();

        //   public User(String email, String roles, String password, String nom, String prenom, String cin, String image, String sexe) {
        User c = new User(Integer.parseInt(labelid.getText()), email2.getText(), LoginController.connectedUser.getRoles(),
                LoginController.connectedUser.getMdp(), Nom2.getText(), LoginController.connectedUser.getImage()
        );

        productService.modifierUser(c);

        nom.setText(Nom2.getText());

        email.setText(email2.getText());

        Nom2.setVisible(false);

        email2.setVisible(false);

        mdp2.setVisible(false);

        Confirmer.setVisible(false);

    }

    @FXML
    private void aa(ActionEvent event) {
        Acctivite();
        
    }
  public void Acctivite() {
        loadScene(
                Constants.FXML_FRONT_DISPLAY_ALL_ACTIVITE,
                "Connexion",
                1100,
                600,
                true
        );}
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
      public void Guide() {
        loadScene(
                Constants.FXML_FRONT_DISPLAY_ALL_GUIDE,
                "Connexion",
                1100,
                600,
                true
        );}
    @FXML
    private void ggg(ActionEvent event) {
        Guide();
    }

    @FXML
    private void recc(ActionEvent event) throws IOException {
               Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/AfficheReclamation.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }

    @FXML
    private void comm(ActionEvent event) throws IOException {
           Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/AfficheCommentaire.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }

    @FXML
    private void pp(ActionEvent event) throws IOException {
               Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/views/ProduitFront.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
             Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/login.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }
}
  
