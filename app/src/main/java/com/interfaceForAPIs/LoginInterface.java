package com.interfaceForAPIs;

import com.model.EditTextModel;
import com.model.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {

    @FormUrlEncoded
    @POST("AppLogin")
    Call<LoginModel> login(
              @Field("MobileNo")String MobileNo

    );

}
