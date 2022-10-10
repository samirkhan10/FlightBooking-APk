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
import com.interfaceForAPIs.PassportUploadInterface;
import com.model.EditTextModel;
import com.model.PassPortModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PassportUpload extends AppCompatActivity {

    Bitmap selectedImage;
    String firstName1,lastName1,passportNumber1,issuePlace1,issueDate1,expiryDate1,passPortFrontImg1,passPortBackImg1;
    private static final String api ="http://UrPlan_App.svc/";
    EditText firstName,lastName,passportNumber,issuePlace,issueDate,expiryDate;
    ImageView passPortFrontImg,passPortBackImg;
    LinearLayout uploadPassPortDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport_upload);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        passportNumber = findViewById(R.id.passportNumber);
        issuePlace = findViewById(R.id.issuePlace);
        issueDate = findViewById(R.id.issueDate);
        expiryDate = findViewById(R.id.expiryDate);
        passPortFrontImg = findViewById(R.id.passPortFrontImg);
        passPortBackImg = findViewById(R.id.passPortBackImg);
        uploadPassPortDetails = findViewById(R.id.uploadPassPortDetails);



        uploadPassPortDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName1 = firstName.getText().toString();
                lastName1 = lastName.getText().toString();
                passportNumber1 = passportNumber.getText().toString();
                issuePlace1 = issuePlace.getText().toString();
                issueDate1 = issueDate.getText().toString();
                expiryDate1 = expiryDate.getText().toString();


                if (firstName1.isEmpty()) {
                    Toast.makeText(PassportUpload.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                }else if (lastName1.isEmpty()) {
                    Toast.makeText(PassportUpload.this, "Please Enter Last Name ", Toast.LENGTH_SHORT).show();
                } else if (passportNumber1.isEmpty()) {
                    Toast.makeText(PassportUpload.this, "Please Enter Passport Number", Toast.LENGTH_SHORT).show();
                }else if (issuePlace1.isEmpty()) {
                    Toast.makeText(PassportUpload.this, "Please Enter issue Place", Toast.LENGTH_SHORT).show();
                }else if (issueDate1.isEmpty()) {
                    Toast.makeText(PassportUpload.this, "Please Enter issue Date", Toast.LENGTH_SHORT).show();
                }
                else if (expiryDate1.isEmpty()) {
                    Toast.makeText(PassportUpload.this, "Please Enter Expiry Date ", Toast.LENGTH_SHORT).show();
                }

                insertData(selectedImage);

                Toast.makeText(PassportUpload.this, "Data Has Updated", Toast.LENGTH_SHORT).show();

            }
        });



        passPortFrontImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);
            }
        });

        passPortBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);
            }
        });



    }

    public void Back(View view) {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                passPortFrontImg.setImageBitmap(selectedImage);
                passPortBackImg.setImageBitmap(selectedImage);
                insertData(selectedImage);
            } catch (FileNotFoundException e) {

                Toast.makeText(PassportUpload.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(PassportUpload.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
//    @Override
//    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
//        super.onActivityResult(reqCode, resultCode, data);
//
//
//        if (resultCode == RESULT_OK) {
//            try {
//                final Uri imageUri = data.getData();
//                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//                passPortBackImg.setImageBitmap(selectedImage);
//                insertData(selectedImage);
//            } catch (FileNotFoundException e) {
//
//                Toast.makeText(PassportUpload.this, "Something went wrong", Toast.LENGTH_LONG).show();
//            }
//
//        } else {
//            Toast.makeText(PassportUpload.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
//        }
//    }


    private void insertData(Bitmap selectedImage) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PassportUploadInterface passportUploadInterface = retrofit.create(com.interfaceForAPIs.PassportUploadInterface.class);

        Call<PassPortModel> call = passportUploadInterface.passportUpload(1, passPortFrontImg1,passPortBackImg1,firstName1,lastName1,passportNumber1,issuePlace1,issueDate1,expiryDate1);
        call.enqueue(new Callback<PassPortModel>() {
            @Override
            public void onResponse(Call<PassPortModel> call, Response<PassPortModel> response) {

                firstName.setText("");
                lastName.setText("");
                passportNumber.setText("");
                issuePlace.setText("");
                issueDate.setText("");
                expiryDate.setText("");


                Toast.makeText(PassportUpload.this, "Data Has Changed " + response, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PassPortModel> call, Throwable t) {

                firstName.setText("");
                lastName.setText("");
                passportNumber.setText("");
                issuePlace.setText("");
                issueDate.setText("");
                expiryDate.setText("");

                Toast.makeText(PassportUpload.this, "Something went wrong"+ t, Toast.LENGTH_SHORT).show();

            }
        });



    }
}