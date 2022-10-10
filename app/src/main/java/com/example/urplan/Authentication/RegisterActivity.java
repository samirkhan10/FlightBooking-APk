package com.example.urplan.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urplan.MainActivity;
import com.example.urplan.R;
import com.interfaceForAPIs.registerAPI;
import com.model.registerModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    CircleImageView imgUser;
    String strmobNum, struserName, stremailId;
    EditText mobNub, userName, emailId;
    TextView login;
    LinearLayout Regisster;
   private static final String api ="http://UrPlan_App.svc/";
    Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imgUser = findViewById(R.id.imgUser);
        mobNub = findViewById(R.id.mobNub);
        userName = findViewById(R.id.userName);
        emailId = findViewById(R.id.emailId);
        Regisster = findViewById(R.id.Regisster);

        Regisster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strmobNum = mobNub.getText().toString();
                struserName = userName.getText().toString();
                stremailId = emailId.getText().toString();


                if (struserName.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();

                }else if (strmobNum.isEmpty() || strmobNum.length() < 10) {
                    Toast.makeText(RegisterActivity.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (stremailId.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Enter Valid Email-id", Toast.LENGTH_SHORT).show();

                }

                    insertData(selectedImage);

                    Intent intent = new Intent(RegisterActivity.this, OTPActivity.class);
                    startActivity(intent);

            }
        });
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);
            }
        });
    }

    private void insertData(Bitmap selectedImage) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        registerAPI registerAPI = retrofit.create(com.interfaceForAPIs.registerAPI.class);

        Call<registerModel> call = registerAPI.regiterData(struserName,strmobNum,stremailId,image);
        call.enqueue(new Callback<registerModel>() {
            @Override
            public void onResponse(Call<registerModel> call, Response<registerModel> response) {

                userName.setText("");
                emailId.setText("");
                mobNub.setText("");
                Toast.makeText(RegisterActivity.this, "Register Successfully"+ response, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<registerModel> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, "Something went wrong"+ t, Toast.LENGTH_SHORT).show();

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
                 selectedImage = BitmapFactory.decodeStream(imageStream);
                imgUser.setImageBitmap(selectedImage);
                insertData(selectedImage);


            } catch (FileNotFoundException e) {

                Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(RegisterActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }


}