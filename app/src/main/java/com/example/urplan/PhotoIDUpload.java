package com.example.urplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.urplan.Authentication.EditProfileActivity;
import com.interfaceForAPIs.EditTextProfileInterface;
import com.interfaceForAPIs.PhotoUploadInterface;
import com.model.EditTextModel;
import com.model.PhotoIDUploadModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoIDUpload extends AppCompatActivity {

    String memberName1,iDNumber1,iDName1;
    private static final String api ="http://UrPlan_App.svc/";
    Bitmap selectedImage;
    EditText memberName,iDNumber,iDName;
    ImageView uploadPhotoId;
    LinearLayout uploadPhotoIdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_idupload);

        memberName = findViewById(R.id.memberName);
        iDNumber = findViewById(R.id.iDNumber);
        iDName = findViewById(R.id.iDName);
        uploadPhotoId = findViewById(R.id.uploadPhotoId);
        uploadPhotoIdBtn = findViewById(R.id.uploadPhotoIdBtn);



        uploadPhotoId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);
            }
        });



        uploadPhotoIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 memberName1 = memberName.getText().toString();
                 iDNumber1 = iDNumber.getText().toString();
                 iDName1 = iDName.getText().toString();


                if (memberName1.isEmpty()) {
                    Toast.makeText(PhotoIDUpload.this, "Please Enter  Member Name", Toast.LENGTH_SHORT).show();
                }else if (iDNumber1.isEmpty()) {
                    Toast.makeText(PhotoIDUpload.this, "Please Enter ID Number ", Toast.LENGTH_SHORT).show();
                } else if (iDName1.isEmpty()) {
                    Toast.makeText(PhotoIDUpload.this, "Please Enter Name on ID", Toast.LENGTH_SHORT).show();
                }

                insertData(selectedImage);

                Toast.makeText(PhotoIDUpload.this, "Data Has Uploaded", Toast.LENGTH_SHORT).show();


            }
        });



    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                uploadPhotoId.setImageBitmap(selectedImage);
                insertData(selectedImage);
            } catch (FileNotFoundException e) {

                Toast.makeText(PhotoIDUpload.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(PhotoIDUpload.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    public void Back(View view) {

        super.onBackPressed();

    }


    private void insertData(Bitmap selectedImage) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoUploadInterface photoUploadInterface = retrofit.create(com.interfaceForAPIs.PhotoUploadInterface.class);

        Call<PhotoIDUploadModel> call = photoUploadInterface.photoIdUpload(1,memberName1 ,iDNumber1,iDName1,image);
        call.enqueue(new Callback<PhotoIDUploadModel>() {
            @Override
            public void onResponse(Call<PhotoIDUploadModel> call, Response<PhotoIDUploadModel> response) {

                memberName.setText("");
                iDNumber.setText("");
                iDName.setText("");


                Toast.makeText(PhotoIDUpload.this, "Data Has Changed " + response, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PhotoIDUploadModel> call, Throwable t) {

                memberName.setText("");
                iDNumber.setText("");
                iDName.setText("");

                Toast.makeText(PhotoIDUpload.this, "Something went wrong"+ t, Toast.LENGTH_SHORT).show();

            }
        });



    }

}