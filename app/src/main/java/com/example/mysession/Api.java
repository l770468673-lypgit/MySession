package com.example.mysession;


import com.example.mysession.bean.SmsCode;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * @Author
 * @Time 2019/7/5 14:33
 * @Describe 接口
 * @Modify
 */
public interface Api {




    // 短信验证码获取 /ui/register/sendVerificationCode.jspx
    @FormUrlEncoded
    @POST("ui/register/sendVerificationCode.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<SmsCode> sendVerificationCode(@Field("mobile") String mobile


    );


    // 短信校验码校验  ：/ui/register/validate.jspx
    @FormUrlEncoded
    @POST("ui/register/validate.jspx")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<SmsCode> validate(@Field("smsActiveCode") String smsActiveCode
                      );

}
