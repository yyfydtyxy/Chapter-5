package com.bytedance.android.lesson.restapi.solution.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Xavier.S
 * @date 2019.01.20 14:18
 */
public class Feed {

    // TODO-C2 (1) Implement your Feed Bean here according to the response json
    @SerializedName("student_id")
    String student_id;
    @SerializedName("user_name")
    String user_name;
    @SerializedName("image_url")
    String image_url;
    @SerializedName("_id")
    String _id;
    @SerializedName("video_url")
    String video_url;
    @SerializedName("createdAt")
    String createdAt;
    @SerializedName("updatedAt")
    String updatedAt;
    @SerializedName("__v")
    int __v;

    @Override
    public String toString() {
        return "id "+student_id+", name"+user_name+", video_url"+video_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public String getImage_url() {
        return image_url;
    }
}
