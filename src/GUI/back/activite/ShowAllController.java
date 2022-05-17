package gui.back.activite;

import Entities.Activite;
import Gui.DashboardAdminController;
import Services.ActiviteService;
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

    public static Activite currentActivite;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Activite> listActivite = ActiviteService.getInstance().getAll();
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
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_ACTIVITE)));

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
            
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierActivite(activite));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerActivite(activite));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterActivite(ActionEvent event) {
        currentActivite = null;
    }

    private void modifierActivite(Activite activite) {
        currentActivite = activite;
        DashboardAdminController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_ACTIVITE);
    }

    private void supprimerActivite(Activite activite) {
        currentActivite = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer activite ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (ActiviteService.getInstance().delete(activite.getId())) {
                
            } else {
                AlertUtils.makeError("Could not delete activite");
            }
        }
    }
}
