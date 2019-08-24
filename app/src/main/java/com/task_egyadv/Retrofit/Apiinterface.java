package com.task_egyadv.Retrofit;


import com.task_egyadv.Model.Login_Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Ahmed on 13/12/2018.
 */

public interface Apiinterface {


    @POST("LoginUser")
    Call<Login_Response> login(@Body Map<String, String> queryMab);


}
