package com.interfaceForAPIs;

import com.model.PhotoIDUploadModel;
import com.model.registerModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PhotoUploadInterface {

    @FormUrlEncoded
    @POST("SavePhotoIDDetails")
    Call<PhotoIDUploadModel> photoIdUpload (@Field("UserID") int UserID, @Field("MemberName")String MemberName, @Field("ID_No")String ID_No, @Field("NameOn_Id")String NameOn_Id, @Field("UploadPhotoId")String UploadPhotoId);

}
