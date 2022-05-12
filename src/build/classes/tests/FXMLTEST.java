/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hedia
 */
public class FXMLTEST extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
         //FXMLLoader loader = new FXMLLoader(getClass().getResource("../esprit/com/views/EvenementView.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("../esprit/com/views/show.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("../esprit/com/views/ticketF.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("../esprit/com/views/modEvent.fxml"));
           //FXMLLoader loader = new FXMLLoader(getClass().getResource("../esprit/com/views/Reclamationback.fxml"));
             //FXMLLoader loader = new FXMLLoader(getClass().getResource("../esprit/com/views/Avis.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../com/esprit/views/ProduitFX.fxml"));
             
       Parent root=loader.load();
       Scene scene=new Scene(root);
       
       primaryStage.setTitle("CATEGRPORDUIT");//title
       primaryStage.setScene(scene);//put scene
       primaryStage.show();//thabat el curtains
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
