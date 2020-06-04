package com.example.mysession;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mysession.bean.SmsCode;
import com.example.mysession.interceptor.AddCookiesInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author LD
 * @Time
 * @Describe 获取文章列表的活动
 * @Modify
 */
public class EssayActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EssayActivity_ceshi";

    private Retrofit retrofitCollect;
    private Api getCollectApi;

    private Button btnGetCollectEssay;
    private Button btn_ectedEssay;
    private EditText ed_sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay);

        init();
        initView();
    }

    private void initView() {

        btnGetCollectEssay = findViewById(R.id.btn_getCollectedEssay);
        btn_ectedEssay = findViewById(R.id.btn_ectedEssay);
        ed_sms = findViewById(R.id.ed_sms);

        btn_ectedEssay.setOnClickListener(this);
        btnGetCollectEssay.setOnClickListener(this);
    }


    //待会写
    private void init() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .build();

        retrofitCollect = new Retrofit.Builder()
                .baseUrl("https://panda.stone3a.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        getCollectApi = retrofitCollect.create(Api.class);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_getCollectedEssay:

                break;

            case R.id.btn_ectedEssay:
                getCollectApi.validate(ed_sms.getText().toString()).enqueue(new Callback<SmsCode>() {
                    @Override
                    public void onResponse(Call<SmsCode> call, Response<SmsCode> response) {
                        if (response.body() != null) {
                            boolean data = response.body().isData();
                            Log.i(TAG, "data===" + data);
                        }
                    }

                    @Override
                    public void onFailure(Call<SmsCode> call, Throwable t) {

                    }
                });
                break;


            default:
                break;
        }
    }
}
