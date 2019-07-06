package com.bytedance.android.lesson.restapi.solution;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bytedance.android.lesson.restapi.solution.bean.Cat;
import com.bytedance.android.lesson.restapi.solution.newtork.ICatService;
import com.bytedance.android.lesson.restapi.solution.utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.ViewHolder;

public class Solution2C1Activity extends AppCompatActivity {

    private static final String TAG = Solution2C1Activity.class.getSimpleName();
    public Button mBtn;
    public RecyclerView mRv;
    private List<Cat> mCats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution2_c1);
        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
                mBtn.setText(R.string.requesting);
                mBtn.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestData(v);
                        mHandler.sendMessage(new Message());
                    }
                }).start();
            }
        });

        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(new Adapter() {
            @NonNull @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                ImageView imageView = new ImageView(viewGroup.getContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setAdjustViewBounds(true);
                return new MyViewHolder(imageView);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
                ImageView iv = (ImageView) viewHolder.itemView;

                // TODO-C1 (4) Uncomment these 2 lines, assign image url of Cat to this url variable
                String url = mCats.get(i).getUrl();
                //System.out.println(url);
                Glide.with(iv.getContext()).load(url).into(iv);
            }

            @Override public int getItemCount() {
                return mCats.size();
            }
        });
    }

    public static class MyViewHolder extends ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void requestData(View view) {
        //mBtn.setText(R.string.requesting);
        //mBtn.setEnabled(false);
        System.out.println("11111111111111");
        // TODO-C1 (3) Send request for 5 random cats here, don't forget to use {@link retrofit2.Call#enqueue}

        String a = NetworkUtils.getResponseWithHttpURLConnection("https://api.thecatapi.com/v1/images/search?limit=5");


        //System.out.println(a);
        mCats.clear();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(a).getAsJsonArray();
        for (JsonElement cat : jsonElements) {
            Cat cat1 = gson.fromJson(cat, Cat.class);//解析
            mCats.add(cat1);
        }

        // Call restoreBtn() and loadPics(response.body()) if success
        // Call restoreBtn() if failure
    }

    private void loadPics(List<Cat> cats) {
        mCats = cats;
        mRv.getAdapter().notifyDataSetChanged();
    }

    private void restoreBtn() {
        mBtn.setText(R.string.request_data);
        mBtn.setEnabled(true);
    }


    private final MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<Solution2C1Activity> mActivity;

        public MyHandler(Solution2C1Activity activity) {
            mActivity = new WeakReference<Solution2C1Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Solution2C1Activity activity = mActivity.get();
            if (activity != null) {
                //activity.mClockView.setShowAnalog(activity.mClockView.isShowAnalog());
                super.handleMessage(msg);
                if(activity.mCats.size()==0){
                    activity.restoreBtn();
                    //loadPics(mCats);
                }
                else{
                    if(activity.mCats.size()<5){
                        Toast.makeText(activity, "网络错误 部分图片丢失",Toast.LENGTH_LONG).show();
                    }
                    activity.restoreBtn();
                    activity.loadPics(activity.mCats);
                }
            }
        }
    }

}


