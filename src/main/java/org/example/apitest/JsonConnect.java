package org.example.apitest;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class JsonConnect {
    private String loc;
    private Label err;
    private ActionEvent event;

    JsonConnect(String loc, Label err){
        this.loc = loc;
        this.err = err;
    }
    JsonConnect (String loc){
        this.loc = loc;
    }

    JSONObject Connect(){
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = null;

        // Replace 'YOUR_API_KEY' with your actual API key
        String apiKey = "cf9e3aa25ba505af3cc3a00577d16bcc";


        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" + loc + "&appid=" + apiKey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                jsonObject = new JSONObject(jsonData);
                return jsonObject;
            } else {
                // If the response is not successful, update error message and hide loading message
                err.setText("Not Found");
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}



