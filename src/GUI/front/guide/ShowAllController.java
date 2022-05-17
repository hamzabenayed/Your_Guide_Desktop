package gui.front.guide;


import Entities.Guide;
import Services.GuideService;
import utils.AlertUtils;
import utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShowAllController implements Initializable {
    
    public static Guide currentGuide;

    @FXML
    public Text topText;
    public Button addButton;
    @FXML
    public VBox mainVBox;

    List<Guide> listGuide;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listGuide = GuideService.getInstance().getAll();
        
        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();
        
        Collections.reverse(listGuide);

        if (!listGuide.isEmpty()) {
            for (Guide guide : listGuide) {
                
                mainVBox.getChildren().add(makeGuideModel(guide));
                
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeGuideModel(
            Guide guide
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_GUIDE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#activiteIdText")).setText("Activite : " + guide.getActiviteId());
            ((Text) innerContainer.lookup("#nomText")).setText("Nom : " + guide.getNom());
            ((Text) innerContainer.lookup("#telText")).setText("Tel : " + guide.getTel());
            
            Path selectedImagePath = FileSystems.getDefault().getPath(guide.getImage());
            if (selectedImagePath.toFile().exists()) {
                ((ImageView) innerContainer.lookup("#imageIV")).setImage(new Image(selectedImagePath.toUri().toString()));
            }
            ((Button) innerContainer.lookup("#specialAction")).setOnAction((event) -> specialAction(guide));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    
    private void specialAction(Guide guide) {
        
    }

    @FXML
    private void bbbb(ActionEvent event) throws IOException {
             Stage XX = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Profil.fxml"));
        Scene scene = new Scene(root);
        XX.setScene(scene);
        XX.show();
    }
}
