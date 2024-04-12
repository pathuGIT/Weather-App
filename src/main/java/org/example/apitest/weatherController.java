package org.example.apitest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class weatherController{
    private Stage stage;
    public String cityName = "";
    @FXML
    private Button exitButton;
    @FXML
    private TextField loc;
    @FXML
    private Pane frontPane;
    @FXML
    private Pane backPane;
    @FXML
    public Label err;
    @FXML
    public Label loadingLabel;

    @FXML
    private Label date;

    @FXML
    private Label description;

    @FXML
    private Label direct;

    @FXML
    private Label hum;

    @FXML
    private Label lat;

    @FXML
    private Label name;

    @FXML
    private Label lon;

    @FXML
    private Label pressure;

    @FXML
    private Label temp;

    @FXML
    private Label wind;



    //Check if it is valid location
    @FXML
    void Search(ActionEvent event) {
        cityName = loc.getText();//
        JsonConnect conn = new JsonConnect(cityName,err);
        try {
            JSONObject jsonObject = conn.Connect();
            if(jsonObject != null) {
                // Double cod = jsonObject.getJSONObject("coord").getDouble("lon");
                // Integer cod = jsonObject.getInt("cod");
                // System.out.println(cod);
                frontPane.setVisible(false);
                backPane.setVisible(true);
                show();
            } else {
                // Handle the case where the JSON response is null (e.g., show an error message)
                err.setText("Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void back(ActionEvent event){
        frontPane.setVisible(true);
        backPane.setVisible(false);
        err.setText("");
    }

    void show(){
        DataImplement di = new DataImplement(cityName);
        name.setText(di.country());
        lon.setText(Double.toString(di.Longitude()));
        lat.setText(Double.toString(di.Latitude()));
        description.setText(di.Weatherdescription());
        temp.setText(Integer.toString(di.Temperature())+"°");
        hum.setText(Double.toString(di.Humidity())+"%");
        wind.setText(Double.toString(di.WindSpeed())+"m/s");
        direct.setText(Double.toString(di.WindDirection())+"°");
        date.setText(di.DateTime());
        pressure.setText(Double.toString(di.Presure())+"hpa");
    }

}
