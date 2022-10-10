package com.example.urplan.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urplan.MainActivity;
import com.example.urplan.R;
import com.model.LoginModel;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private static final String url="http://192.168.0.100/api/";
    private static Retrofit retrofit;
    CircleImageView imgUser;
    LinearLayout getOTP;
    String strmobNum;
    EditText mobNub;
    TextView sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgUser = findViewById(R.id.imgUser);
        getOTP = findViewById(R.id.getOTP);
        sign_up = findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        mobNub = findViewById(R.id.mobNub);
        checkuserexistence();
        getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strmobNum = mobNub.getText().toString();


                if (strmobNum.isEmpty() || strmobNum.length() < 10) {
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, OTPActivity.class);
                    startActivity(intent);
                }

                processlogin();

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

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgUser.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {

                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(LoginActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    void processlogin() {

        Call<LoginModel> call= LoginNumberController
                .getInstance()
                .getapi()
                .login(strmobNum);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                LoginModel obj=response.body();
                String output=obj.getMobileNo();
                if(output.equals("failed")){
                    Toast.makeText(LoginActivity.this, "Invalid username or password ", Toast.LENGTH_SHORT).show();

                }
                if(output.equals("exist")) {
                    SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("MobileNo",mobNub.getText().toString());
                    editor.commit();
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something went wrong "+t, Toast.LENGTH_SHORT).show();

            }
        });

    }

    void checkuserexistence()
    {
        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("MobileNo"))
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        else {
            Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
        }
    }
}