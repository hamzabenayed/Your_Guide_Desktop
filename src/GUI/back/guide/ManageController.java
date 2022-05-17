package gui.back.guide;

import intergrationfinal.IntergrationFinal;
import Entities.Guide;
import Services.GuideService;
import utils.AlertUtils;
import utils.Constants;
import utils.RelationObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    @FXML
    public ComboBox<RelationObject> activiteCB;
    @FXML
    public TextField nomTF;
    @FXML
    public TextField telTF;
    @FXML
    public ImageView imageIV;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Guide currentGuide;
    Path selectedImagePath;
    boolean imageEdited;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (RelationObject activite : GuideService.getInstance().getAllActivites()) {
            activiteCB.getItems().add(activite);
        }
        
        currentGuide = ShowAllController.currentGuide;

        if (currentGuide != null) {
            topText.setText("Modifier guide");
            btnAjout.setText("Modifier");
            
            try {
                activiteCB.setValue(currentGuide.getActiviteId());
                nomTF.setText(currentGuide.getNom());
                telTF.setText(String.valueOf(currentGuide.getTel()));
                selectedImagePath = FileSystems.getDefault().getPath(currentGuide.getImage());
                if (selectedImagePath.toFile().exists()) {
                    imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
                }
                
            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter guide");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {
            
            String imagePath;
            if (imageEdited) {
                imagePath = currentGuide.getImage();
            } else {
                createImageFile();
                imagePath = selectedImagePath.toString();
            }
            
            Guide guide = new Guide(
                activiteCB.getValue(),
                nomTF.getText(),
                Integer.parseInt(telTF.getText()),
                imagePath
            );

            if (currentGuide == null) {
                if (GuideService.getInstance().add(guide)) {
                    AlertUtils.makeInformation("Guide ajouté avec succés");
                } else {
                    AlertUtils.makeError("Could not add guide");
                }
            } else {
                guide.setId(currentGuide.getId());
                if (GuideService.getInstance().edit(guide)) {
                    AlertUtils.makeInformation("Guide modifié avec succés");
                    ShowAllController.currentGuide = null;
                } else {
                    AlertUtils.makeError("Could not edit guide");
                }
            }
            
            if (selectedImagePath != null) {
                createImageFile();
            }
            
        }
    }

    @FXML
    public void chooseImage(ActionEvent actionEvent) {

        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(IntergrationFinal.mainStage);
        if (file != null) {
            selectedImagePath = Paths.get(file.getPath());
            imageIV.setImage(new Image(file.toURI().toString()));
        }
    }

    public void createImageFile() {
        try {
            Path newPath = FileSystems.getDefault().getPath("src/images/uploads/" + selectedImagePath.getFileName());
            Files.copy(selectedImagePath, newPath, StandardCopyOption.REPLACE_EXISTING);
            selectedImagePath = newPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean controleDeSaisie() {
        
        
        if (activiteCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir activite");
            return false;
        }
        
        
        if (nomTF.getText().isEmpty()) {
            AlertUtils.makeInformation("nom ne doit pas etre vide");
            return false;
        }
        
        
        if (telTF.getText().isEmpty()) {
            AlertUtils.makeInformation("tel ne doit pas etre vide");
            return false;
        }
        
        try {
            Integer.parseInt(telTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("tel doit etre un nombre");
            return false;
        }
        
        if (selectedImagePath == null) {
            AlertUtils.makeInformation("Veuillez choisir une image");
            return false;
        }
        
        
        return true;
    }
}