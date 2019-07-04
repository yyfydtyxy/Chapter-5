package com.bytedance.android.lesson.restapi.solution.newtork;


import retrofit2.http.GET;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Path;

/**
 * @author Xavier.S
 * @date 2019.01.15 16:42
 */
public interface ICatService {
    // TODO-C1 (2) Implement your Cat Request here, url: https://api.thecatapi.com/v1/images/search?limit=5
    @GET("blog/{id}")
    Call<ResponseBody> getBlog(@Path("id") int id);
}


