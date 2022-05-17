/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergrationfinal;

import MA.MainApp;
import static MA.MainApp.mainStage;
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
 * @author admin
 */
public class IntergrationFinal extends Application {
    
    public static Stage mainStage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
         mainStage = primaryStage;
        System.out.println("aaaa");
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
//////        Parent root = FXMLLoader.load(getClass().getResource("/gui.front.activite/ShowAll.fxml"));
//////        Scene scene = new Scene(root);
//////        primaryStage.setScene(scene);
//////        primaryStage.show();
//////MainApp.getInstance().start(primaryStage);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        launch(args);
    }
    
}
