package gui.back.type_activite;


import Entities.Type_activite;
import Services.Type_activiteService;
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

    public static Type_activite currentType_activite;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Type_activite> listType_activite = Type_activiteService.getInstance().getAll();
        Collections.reverse(listType_activite);

        if (!listType_activite.isEmpty()) {
            for (Type_activite type_activite : listType_activite) {
                mainVBox.getChildren().add(makeType_activiteModel(type_activite));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeType_activiteModel(
            Type_activite type_activite
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_TYPE_ACTIVITE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#nomText")).setText("Nom : " + type_activite.getNom());
            
            
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierType_activite(type_activite));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerType_activite(type_activite));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterType_activite(ActionEvent event) {
        currentType_activite = null;

    }

    private void modifierType_activite(Type_activite type_activite) {
        currentType_activite = type_activite;

    }

    private void supprimerType_activite(Type_activite type_activite) {
        currentType_activite = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer type_activite ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (Type_activiteService.getInstance().delete(type_activite.getId())) {

            } else {
                AlertUtils.makeError("Could not delete type_activite");
            }
        }
    }
}
