package gui.front;

import MA.MainApp;
import utils.Animations;
import utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TopBarController implements Initializable {

    private final Color COLOR_GRAY = new Color(0.9, 0.9, 0.9, 1);
    private final Color COLOR_PRIMARY = Color.web("#053F6E");
    private final Color COLOR_DARK = new Color(0.20, 0.23, 0.25, 1);
    private Button[] liens;
    
    @FXML
    private Button btnActivites;
    @FXML
    private Button btnType_activites;
    @FXML
    private Button btnGuides;
    @FXML
    private AnchorPane mainComponent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        liens = new Button[]{
            btnActivites,
            btnType_activites,
            btnGuides,
        };

        mainComponent.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));

        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            lien.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));
            Animations.animateButton(lien, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
        }
        btnActivites.setTextFill(Color.WHITE);
        btnType_activites.setTextFill(Color.WHITE);
        btnGuides.setTextFill(Color.WHITE);
        
    }
    
    @FXML
    private void afficherActivites(ActionEvent event) {
        goToLink(Constants.FXML_FRONT_DISPLAY_ALL_ACTIVITE);

        btnActivites.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnActivites, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }
    
    @FXML
    private void afficherType_activites(ActionEvent event) {
        goToLink(Constants.FXML_FRONT_DISPLAY_ALL_TYPE_ACTIVITE);

        btnType_activites.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnType_activites, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }
    
    @FXML
    private void afficherGuides(ActionEvent event) {
        goToLink(Constants.FXML_FRONT_DISPLAY_ALL_GUIDE);

        btnGuides.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnGuides, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }
    
    private void goToLink(String link) {
        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            Animations.animateButton(lien, COLOR_GRAY, COLOR_DARK, COLOR_PRIMARY, 0, false);
        }
        MainWindowController.getInstance().loadInterface(link);
    }

    @FXML
    public void logout(ActionEvent actionEvent) {
        MainApp.getInstance().logout();
    }
}