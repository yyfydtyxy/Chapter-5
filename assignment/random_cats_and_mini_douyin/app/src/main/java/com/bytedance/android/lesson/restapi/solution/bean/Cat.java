package com.bytedance.android.lesson.restapi.solution.bean;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

/**
 * @author Xavier.S
 * @date 2019.01.17 18:08
 */
public class Cat {

    // TODO-C1 (1) Implement your Cat Bean here according to the response json
    @SerializedName("id")
    String id;
    @SerializedName("url")
    String url;
    @SerializedName("width")
    int width;
    @SerializedName("height")
    int height;
    @SerializedName("breeds")
    JsonArray breeds;
    @SerializedName("categories")
    JsonArray categories;
    public Cat(String url){
       // this.id=id;
        this.url=url;
       // this.width=width;
       // this.height=height;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
