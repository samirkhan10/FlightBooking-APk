package com.interfaceForAPIs;

import android.widget.EditText;
import android.widget.ImageView;

import com.model.EditTextModel;
import com.model.PassPortModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PassportUploadInterface {

    @FormUrlEncoded
    @POST("SavePassportDetails")
    Call<PassPortModel> passportUpload(@Field("UserID") int UserID, @Field("FirstName") String FirstName, @Field("LastName") String LastName, @Field("Passport_No") String Passport_No, @Field("Issue_Place") String Issue_Place, @Field("Issue_Date") String Issue_Date, @Field("ExpiryDate") String ExpiryDate, @Field("PassportImage_Front") String PassportImage_Front, @Field("PassportImage_Back") String PassportImage_Back);

}
