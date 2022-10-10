package com.interfaceForAPIs;

import com.model.registerModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface registerAPI {

    @FormUrlEncoded
    @POST("SaveRegistrationDetail")
    Call<registerModel> regiterData(@Field("FullName")String FullName,@Field("MobileNo")String MobileNo,@Field("EmailID")String EmailID,@Field("UsersImage")String UsersImage);

}
