/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.APIConnector;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class WeatherController implements Initializable {

    @FXML
    private TextField cityInput;
    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";
    @FXML
    private TextArea weatherText;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getWeatherData(ActionEvent event) throws MalformedURLException{
         JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());

        System.out.println(todaysWeather);

        weatherText.setText(
            "Min temperature: " + todaysWeather.get("min_temp") +
            "\nCurrent temperature: " + todaysWeather.get("the_temp") +
            "\nMax temperature: " + todaysWeather.get("max_temp")
        );
    }
    public String getWoeid() throws MalformedURLException {
        APIConnector apiConnectorCity = new APIConnector(cityAPI);

        JSONObject jsonData = (JSONObject) (apiConnectorCity.getJSONArray(cityInput.getText())).get(0);

        return jsonData.get("woeid").toString();
    }

    public JSONObject GetTodaysWeatherInformation(String woeid) throws MalformedURLException {
        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");

        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");

        return  (JSONObject) weatherArray.get(0);
    }

    @FXML
    private void submit(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheReclamation.fxml"));
            Parent root = loader.load();
            msg.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficheReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void submitcomm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheCommentaire.fxml"));
            Parent root = loader.load();
            msg.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficheCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
