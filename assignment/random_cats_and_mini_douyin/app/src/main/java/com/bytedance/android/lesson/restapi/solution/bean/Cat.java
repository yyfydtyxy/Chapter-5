package com.bytedance.android.lesson.restapi.solution.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;


/**
 * @author Xavier.S
 * @date 2019.01.17 18:08
 */
public class Cat {
    private String id;
    private String url;
    private int width;
    private int height;


    // TODO-C1 (1) Implement your Cat Bean here according to the response json
    public Cat(String id,String url,int width,int height){
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    private void parseJJSONObject(String jsonData) {
        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            id = jsonObject.getString("id");
            url = jsonObject.getString("url");
            width= jsonObject.getInt("width");
            height= jsonObject.getInt("height");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }
    public String geturl() {
        return url;
    }
    public int getwidth() {
        return width;
    }
    public int getheight() {
        return height;
    }

}
