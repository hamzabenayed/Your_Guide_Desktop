package gui.back.type_activite;


import Entities.Type_activite;
import Services.Type_activiteService;
import utils.AlertUtils;
import utils.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.text.Text;


import java.net.URL;

import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;

public class ManageController implements Initializable {

    @FXML
    public TextField nomTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Type_activite currentType_activite;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        currentType_activite = ShowAllController.currentType_activite;

        if (currentType_activite != null) {
            topText.setText("Modifier type_activite");
            btnAjout.setText("Modifier");
            
            try {
                nomTF.setText(currentType_activite.getNom());
                
            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter type_activite");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {
            
            Type_activite type_activite = new Type_activite(
                nomTF.getText()
            );

            if (currentType_activite == null) {
                if (Type_activiteService.getInstance().add(type_activite)) {
                    //AlertUtils.makeInformation("Type_activite ajouté avec succés");
                                            Notifications notifications=Notifications.create();

                        notifications.text("Reclamation Ajoutée");
                        notifications.title("Success Message");
                        notifications.show();
                } else {
                    AlertUtils.makeError("Could not add type_activite");
                }
            } else {
                type_activite.setId(currentType_activite.getId());
                if (Type_activiteService.getInstance().edit(type_activite)) {
                    AlertUtils.makeInformation("Type_activite modifié avec succés");
                    ShowAllController.currentType_activite = null;
                } else {
                    AlertUtils.makeError("Could not edit type_activite");
                }
            }
            
        }
    }

    

    private boolean controleDeSaisie() {
        
        
        if (nomTF.getText().isEmpty()) {
            AlertUtils.makeInformation("nom ne doit pas etre vide");
            return false;
        }
        
        
        return true;
    }
}