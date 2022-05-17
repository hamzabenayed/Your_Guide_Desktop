package MA;

import Entities.User;
import utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    public static Stage mainStage;
    private static MainApp instance;
    private static User session;

    
     @Override
    public void start(Stage primaryStage) {
      
        mainStage = primaryStage;
        GuideManage();
    }
    
    public static void main(String[] args) {
     
        launch(args);
       
    }

    public static MainApp getInstance() {
        if (instance == null) {
            instance = new MainApp();
        }
        return instance;
    }

    public static User getSession() {
        return session;
    }

    public static void setSession(User session) {
        MainApp.session = session;
    }

   
    
    public void GuideManage() {
        loadScene(
                Constants.FXML_BACK_MANAGE_GUIDE,
                "Connexion",
                1100,
                600,
                true
        );
    }

  

    public void loadFront(){
        loadScene(
                Constants.FXML_FRONT_MAIN_WINDOW,
                "",
                1100,
                600,
                false
        );
    }
    
    public void loadBack() {
        loadScene(
                Constants.FXML_BACK_MAIN_WINDOW,
                "",
                1100,
                600,
                false
        );
    }

    public void logout() {
        session = null;
        System.out.println("Deconnexion ..");
        GuideManage();
    }

   private void loadScene(String fxmlLink, String title, int width, int height, boolean isAuthentification) {
        try {
            Stage primaryStage = mainStage;
            primaryStage.close();

            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlLink))));
            scene.setFill(Color.TRANSPARENT);

            //primaryStage.getIcons().add(new Image("app/images/app-icon.png"));
            primaryStage.setTitle(title);
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
            primaryStage.setMinWidth(width);
            primaryStage.setMinHeight(height);
            primaryStage.setScene(scene);
            primaryStage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - (width / 2.0));
            primaryStage.setY((Screen.getPrimary().getBounds().getHeight() / 2) - (height / 2.0));

            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
