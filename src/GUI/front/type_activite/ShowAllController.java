package gui.front.type_activite;

import Entities.Type_activite;
import Services.Type_activiteService;
import utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

public class ShowAllController implements Initializable {

    public static Type_activite currentType_activite;

    @FXML
    public Text topText;
    public Button addButton;
    @FXML
    public VBox mainVBox;

    List<Type_activite> listType_activite;
    @FXML
    private ComboBox<String> sortCB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listType_activite = Type_activiteService.getInstance().getAll();
        sortCB.getItems().addAll("nom", "id");
        displayData();
    }
    
    

    void displayData() {
        mainVBox.getChildren().clear();

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
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_TYPE_ACTIVITE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#nomText")).setText("Nom : " + type_activite.getNom());


            ((Button) innerContainer.lookup("#specialAction")).setOnAction((event) -> specialAction(type_activite));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    private void specialAction(Type_activite type_activite) {

    }

    @FXML
    private void sort(ActionEvent actionEvent) {
        Constants.compareVar = (String) sortCB.getValue();
        Collections.sort(listType_activite);
        displayData();
    }

   
   
    
}
