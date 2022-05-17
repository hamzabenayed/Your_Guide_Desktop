package gui.front.activite;

import intergrationfinal.IntergrationFinal;
import Entities.Activite;
import Services.ActiviteService;
import utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ShowAllController implements Initializable {
    
    public static Activite currentActivite;

    @FXML
    public Text topText;
    public Button addButton;
    @FXML
    public VBox mainVBox;

    List<Activite> listActivite;
    @FXML
    private TextField searchTF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listActivite = ActiviteService.getInstance().getAll();
       displayData("");
    }

    
  void displayData(String searchText) {
        mainVBox.getChildren().clear();

        Collections.reverse(listActivite);

        if (!listActivite.isEmpty()) {
            for (Activite activite : listActivite) {
                
                mainVBox.getChildren().add(makeActiviteModel(activite));
                
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeActiviteModel(
            Activite activite
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_ACTIVITE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#typeactIdText")).setText("Typeactivite : " + activite.getTypeactId());
            ((Text) innerContainer.lookup("#nomText")).setText("Nom : " + activite.getNom());
            ((Text) innerContainer.lookup("#lieuText")).setText("Lieu : " + activite.getLieu());
            ((Text) innerContainer.lookup("#descriptionText")).setText("Description : " + activite.getDescription());
            
            ((Text) innerContainer.lookup("#longitudeText")).setText("Longitude : " + activite.getLongitude());
            ((Text) innerContainer.lookup("#lattitudeText")).setText("Lattitude : " + activite.getLattitude());
            Path selectedImagePath = FileSystems.getDefault().getPath(activite.getImage());
            if (selectedImagePath.toFile().exists()) {
                ((ImageView) innerContainer.lookup("#imageIV")).setImage(new Image(selectedImagePath.toUri().toString()));
            }

            ((Button) innerContainer.lookup("#specialAction")).setOnAction((event) -> specialAction(activite));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    private void specialAction(Activite activite) {
        
    }

    @FXML
    private void search(KeyEvent event) {
          displayData(searchTF.getText());
    }

    @FXML
    private void bb(ActionEvent event) throws IOException {
          Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Profil.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }

    

    
}
