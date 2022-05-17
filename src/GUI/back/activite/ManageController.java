package gui.back.activite;

import intergrationfinal.IntergrationFinal;
import Entities.Activite;
import Services.ActiviteService;
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
import org.controlsfx.control.Notifications;

public class ManageController implements Initializable {

    @FXML
    public ComboBox<RelationObject> typeactiviteCB;
    @FXML
    public TextField nomTF;
    @FXML
    public TextField lieuTF;
    @FXML
    public TextField descriptionTF;
    @FXML
    public ImageView imageIV;
    @FXML
    public TextField longitudeTF;
    @FXML
    public TextField lattitudeTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Activite currentActivite;
    Path selectedImagePath;
    boolean imageEdited;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (RelationObject typeactivite : ActiviteService.getInstance().getAllTypeactivites()) {
            typeactiviteCB.getItems().add(typeactivite);
        }
        
        currentActivite = ShowAllController.currentActivite;

        if (currentActivite != null) {
            topText.setText("Modifier activite");
            btnAjout.setText("Modifier");
            
            try {
                typeactiviteCB.setValue(currentActivite.getTypeactId());
                nomTF.setText(currentActivite.getNom());
                lieuTF.setText(currentActivite.getLieu());
                descriptionTF.setText(currentActivite.getDescription());
                selectedImagePath = FileSystems.getDefault().getPath(currentActivite.getImage());
                if (selectedImagePath.toFile().exists()) {
                    imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
                }
                longitudeTF.setText(currentActivite.getLongitude());
                lattitudeTF.setText(currentActivite.getLattitude());
                
            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter activite");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {
            
            String imagePath;
            if (imageEdited) {
                imagePath = currentActivite.getImage();
            } else {
                createImageFile();
                imagePath = selectedImagePath.toString();
            }
            
            Activite activite = new Activite(
                typeactiviteCB.getValue(),
                nomTF.getText(),
                lieuTF.getText(),
                descriptionTF.getText(),
                imagePath,
                longitudeTF.getText(),
                lattitudeTF.getText()
            );

            if (currentActivite == null) {
                if (ActiviteService.getInstance().add(activite)) {
                       Notifications notifications=Notifications.create();
                notifications.text(" Ajoutée");
                        notifications.title("Success Message");
                        notifications.show();
                                    
                } else {
                      Notifications notifications=Notifications.create();
                notifications.text(" Non ajoute");
                        notifications.title("Success Message");
                        notifications.show();
                                    
                }
            } else {
                activite.setId(currentActivite.getId());
                if (ActiviteService.getInstance().edit(activite)) {
                    AlertUtils.makeInformation("Activite modifié avec succés");
                    ShowAllController.currentActivite = null;
                } else {
                    AlertUtils.makeError("Could not edit activite");
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
        File file = fileChooser.showOpenDialog(intergrationfinal.IntergrationFinal.mainStage);
        if (file != null) {
            selectedImagePath = Paths.get(file.getPath());
            imageIV.setImage(new Image(file.toURI().toString()));
        }
    }

    public void createImageFile() {
        try {
            Path newPath = FileSystems.getDefault().getPath("src/com/khedmetna/images/uploads/" + selectedImagePath.getFileName());
            Files.copy(selectedImagePath, newPath, StandardCopyOption.REPLACE_EXISTING);
            selectedImagePath = newPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     /*  public boolean controleDeSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

        if(checkIfStringContainsNumber(txtNom.getText())){
            alert.setContentText("Le nom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                if(checkIfStringContainsNumber(txtDescription.getText())){
            alert.setContentText("La description ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                 
        
                  if(checkIfStringContainsNumber2(txtCout.getText())){
            alert.setContentText("Le cout ne doit pas contenir des caracteres");
            alert.showAndWait();
            return false;
        }

        
        return true;
    }
       public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       return true;
    }
    
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfStringContainsNumber2(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
                return true;
            }
        }
        return false;
    }*/
    private boolean controleDeSaisie() {
        
        
        if (typeactiviteCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir typeactivite");
            return false;
        }
        
        
        if (nomTF.getText().isEmpty()) {
            AlertUtils.makeInformation("nom ne doit pas etre vide");
            return false;
        }
        
        
        if (lieuTF.getText().isEmpty()) {
            AlertUtils.makeInformation("lieu ne doit pas etre vide");
            return false;
        }
        
        
        if (descriptionTF.getText().isEmpty()) {
            AlertUtils.makeInformation("description ne doit pas etre vide");
            return false;
        }
        
        
        if (selectedImagePath == null) {
            AlertUtils.makeInformation("Veuillez choisir une image");
            return false;
        }
        
        
        if (longitudeTF.getText().isEmpty()) {
            AlertUtils.makeInformation("longitude ne doit pas etre vide");
            return false;
        }
        
        
        if (lattitudeTF.getText().isEmpty()) {
            AlertUtils.makeInformation("lattitude ne doit pas etre vide");
            return false;
        }
        
        
        return true;
    }
}