package com.task_egyadv.View_Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;


import com.google.gson.Gson;
import com.task_egyadv.Model.Login_Response;
import com.task_egyadv.Model.UserLogin;
import com.task_egyadv.Retrofit.ApiCLint;
import com.task_egyadv.Retrofit.Apiinterface;
import com.task_egyadv.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_ViewModel extends ViewModel {

    Context context;
    private MutableLiveData<Login_Response> login;
    private String UserId;

    public LiveData<Login_Response> getLogin(UserLogin user, Context context) {
        login = new MutableLiveData<Login_Response>();
            this.context=context;
            login(user);

        return login;
    }

    public void login(UserLogin user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("password", user.getPassword());
        queryMap.put("login", user.getEmail());
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Login_Response> call = apiInterface.login(queryMap);
        call.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                if(response.isSuccessful()) {
                    if(response.body().getMessage().equals("تم تسجيل الدخول")){
                        try {
                            JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("user");
                            UserId=jsonObject2.getString("user_id");
                            SharedPrefManager.getInstance(context).saveUserToken(UserId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                     }

                    login.setValue(response.body());
                }else {
                    login.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                login.setValue(null);
            }
        });
    }



}
