package org.example.apitest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DataImplement implements Data {
    private String loc;

    DataImplement(String loc) {
        this.loc = loc;
    }

    @Override
    public double Longitude() {
        JsonConnect conn = new JsonConnect(loc);
        double out = 0;
        try {
            double lon = conn.Connect().getJSONObject("coord").getDouble("lon");
            out = lon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public double Latitude() {
        JsonConnect conn = new JsonConnect(loc);
        double out = 0;
        try {
            double lat = conn.Connect().getJSONObject("coord").getDouble("lat");
            out = lat;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public String Weatherdescription() {
        JsonConnect conn = new JsonConnect(loc);
        String out = "";
        try {
            JSONArray weatherArray = conn.Connect().getJSONArray("weather");
            JSONObject weatherObject = weatherArray.getJSONObject(0);
            String description = weatherObject.getString("description");
            out = description;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public int Temperature() {
        JsonConnect conn = new JsonConnect(loc);
        int out = 0;
        try {
            Double temp = conn.Connect().getJSONObject("main").getDouble("temp");
            out = (int) Math.round(temp - 273.15);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }


    @Override
    public Double Humidity() {
        JsonConnect conn = new JsonConnect(loc);
        Double out = 0.0;
        try {
            Double humidity = conn.Connect().getJSONObject("main").getDouble("humidity");
            out = humidity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public Double WindSpeed() {
        JsonConnect conn = new JsonConnect(loc);
        Double out = 0.0;
        try {
            Double wind = conn.Connect().getJSONObject("wind").getDouble("speed");
            out = wind;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public Double WindDirection() {
        JsonConnect conn = new JsonConnect(loc);
        Double out = 0.0;
        try {
            Double deg = conn.Connect().getJSONObject("wind").getDouble("deg");
            out = deg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public String DateTime() {
        JsonConnect conn = new JsonConnect(loc);
        long unixTimestamp = conn.Connect().getLong("dt");
        int timezoneOffset = conn.Connect().getInt("timezone");

        // Convert UNIX timestamp to milliseconds
        long timestampInMillis = unixTimestamp * 1000;

        // Create a Date object using the timestamp in milliseconds
        Date date = new Date(timestampInMillis);

        // Calculate timezone offset in milliseconds
        long timezoneOffsetInMillis = timezoneOffset * 1000;

        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Adjust the timezone based on the offset
        if (timezoneOffset > 0) {
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+" + timezoneOffset / 3600));
        } else {
            sdf.setTimeZone(TimeZone.getTimeZone("GMT" + timezoneOffset / 3600));
        }
        return sdf.format(new Date(timestampInMillis));
    }


    @Override
    public Double Presure() {
        JsonConnect conn = new JsonConnect(loc);
        Double out = 0.0;
        try {
            Double pressure = conn.Connect().getJSONObject("main").getDouble("pressure");
            out = pressure;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public String country() {
        JsonConnect conn = new JsonConnect(loc);
        String out = "";
        try {
            String name = conn.Connect().getString("name");
            out = name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}
