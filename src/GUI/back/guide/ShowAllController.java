package gui.back.guide;

import Entities.Guide;
import Services.GuideService;
import utils.AlertUtils;
import utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import java.util.*;

public class ShowAllController implements Initializable {

    public static Guide currentGuide;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Guide> listGuide = GuideService.getInstance().getAll();
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
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_GUIDE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#activiteIdText")).setText("Activite : " + guide.getActiviteId());
            ((Text) innerContainer.lookup("#nomText")).setText("Nom : " + guide.getNom());
            ((Text) innerContainer.lookup("#telText")).setText("Tel : " + guide.getTel());
            
            Path selectedImagePath = FileSystems.getDefault().getPath(guide.getImage());
            if (selectedImagePath.toFile().exists()) {
                ((ImageView) innerContainer.lookup("#imageIV")).setImage(new Image(selectedImagePath.toUri().toString()));
            }
            
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierGuide(guide));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerGuide(guide));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterGuide(ActionEvent event) {
        currentGuide = null;

    }

    private void modifierGuide(Guide guide) {
        currentGuide = guide;

    }

    private void supprimerGuide(Guide guide) {
        currentGuide = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer guide ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (GuideService.getInstance().delete(guide.getId())) {
            } else {
                AlertUtils.makeError("Could not delete guide");
            }
        }
    }
}
