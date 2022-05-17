/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class EditUserController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private TextField Nom;
    @FXML
    private Button A;
    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    @FXML
    private ComboBox<String> usertype;
    @FXML
    private Hyperlink prec;
    @FXML
    private Label User;
    private ImageView imgajoutincours;
    @FXML
    private Label imgpathttt;
    @FXML
    private Button Timage;
    @FXML
    private Label labelid;
    @FXML
    private ImageView imgajoutincours1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User.setText(LoginController.connectedUser.getNom());
         
   ObservableList<String> list_ne = FXCollections.observableArrayList( "Admin", "Client");
        usertype.setItems(list_ne);
        
        

        labelid.setText(Integer.toString(ListController.connectedUser.getId()));
         email.setText(ListController.connectedUser .getLogin());
     //   tel.setText(Integer.toString(ListController.connectedUser.getTelephone()));
        usertype.setValue(ListController.connectedUser.getRoles());
        mdp.setText(ListController.connectedUser.getMdp());
        Nom.setText(ListController.connectedUser.getNom());
    }    

    @FXML
    private void insert(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {
        
         UserService productService = new UserService();
         
         //   public User(String email, String roles, String password, String nom, String prenom, String cin, String image, String sexe) {

        
        User c = new User(Integer.parseInt(labelid.getText()),email.getText(),usertype.getValue(),mdp.getText(),Nom.getText()
                        ,Timage.getText());
            
                    
        
        productService.modifierUser(c);
       
      Parent page1 = FXMLLoader.load(getClass().getResource("List.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Utilisateurs");
        stage.setScene(scene);
        stage.show();
        
        
    }

    @FXML
    private void prec(ActionEvent event) throws IOException {
        
      Parent page1 = FXMLLoader.load(getClass().getResource("List.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Utilisateurs");
        stage.setScene(scene);
        stage.show();   
        
    }
 
@FXML
    private void addimgcours(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpathttt.setText(file.getAbsolutePath());
    }
    
    
}
