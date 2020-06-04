package com.example.mysession;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mysession.bean.SmsCode;
import com.example.mysession.interceptor.ReceivedCookiesInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author LD
 * @Time 2019.7.5
 * @Describe 拦截器的使用
 * @Modify
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity_ceshi";

    private EditText etUsername, etPwd;
    private Button btnLogin;


    private Retrofit retrofitLogin;
    private Api loginApi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initView();

    }

    private void init() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ReceivedCookiesInterceptor())
                .build();

        retrofitLogin = new Retrofit.Builder()
                .baseUrl("https://panda.stone3a.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        loginApi = retrofitLogin.create(Api.class);
    }


    private void initView() {

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                loginApi.sendVerificationCode("15689123357").enqueue(new Callback<SmsCode>() {
                    @Override
                    public void onResponse(Call<SmsCode> call, Response<SmsCode> response) {
                        if (response.body() != null) {
                            Log.i(TAG, "发送成功");
                            startActivity(new Intent(MainActivity.this, EssayActivity.class));
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
