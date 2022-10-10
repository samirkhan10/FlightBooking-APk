package com.interfaceForAPIs;

import com.model.EditTextModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EditTextProfileInterface {

    @FormUrlEncoded
    @POST("EditProfile")
    Call<EditTextModel> editTextProfile(@Field("UserID") int UserID, @Field("Gender")String Gender, @Field("MobileNo")String MobileNo, @Field("FullName")String FullName, @Field("UsersImage")String UsersImage, @Field("EmailID")String EmailID, @Field("DOB")String DOB);


}
