package com.example.urplan.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urplan.FlightDoc.AddTickets;
import com.example.urplan.R;
import com.interfaceForAPIs.AddTicketsInterface;
import com.interfaceForAPIs.EditTextProfileInterface;
import com.model.AddticketsModel;
import com.model.EditTextModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity {

    private static final String api ="http://UrPlan_App.svc/";
    Bitmap selectedImage;
    EditText mobileNum,userName,EmailEDT,dobEDT;
    String mobileNum1,userName1,EmailEDT1,dobEDT1;
    LinearLayout saveProfile;
    CircleImageView imgUser;
    TextView male , female;
    TextView Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        female = findViewById(R.id.female);
        mobileNum = findViewById(R.id.mobileNum);
        userName = findViewById(R.id.userName);
        EmailEDT = findViewById(R.id.EmailEDT);
        dobEDT = findViewById(R.id.dobEDT);
        saveProfile = findViewById(R.id.saveProfile);






        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN)

                {
                    male.setBackgroundDrawable(ContextCompat.getDrawable(EditProfileActivity.this, R.drawable.left_radius_unselected) );
                    female.setBackgroundDrawable(ContextCompat.getDrawable(EditProfileActivity.this, R.drawable.right_radius_selected) );
                    female.setTextColor(getResources().getColor(R.color.white));
                    male.setTextColor(getResources().getColor(R.color.main));


                }
                else

                {
                    male.setBackground(ContextCompat.getDrawable(EditProfileActivity.this, R.drawable.left_radius_unselected));
                    female.setBackground(ContextCompat.getDrawable(EditProfileActivity.this, R.drawable.right_radius_selected));
                    female.setTextColor(getResources().getColor(R.color.white));
                    male.setTextColor(getResources().getColor(R.color.main));


                }
            }
        });
        male = findViewById(R.id.male);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN)

                {
                    male.setBackgroundDrawable(ContextCompat.getDrawable(EditProfileActivity.this, R.drawable.left_radius_selected) );
                    female.setBackgroundDrawable(ContextCompat.getDrawable(EditProfileActivity.this, R.drawable.right_radius_unselected) );
                    male.setTextColor(getResources().getColor(R.color.white));
                    female.setTextColor(getResources().getColor(R.color.main));

                }
                else {
                    male.setBackground(ContextCompat.getDrawable(EditProfileActivity.this, R.drawable.left_radius_selected));
                    female.setBackground(ContextCompat.getDrawable(EditProfileActivity.this, R.drawable.right_radius_unselected));
                    male.setTextColor(getResources().getColor(R.color.white));
                    female.setTextColor(getResources().getColor(R.color.main));
                }
            }
        });

        imgUser = findViewById(R.id.imgUser);
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);
            }
        });


        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mobileNum1 = mobileNum.getText().toString();
                userName1 = userName.getText().toString();
                EmailEDT1 = EmailEDT.getText().toString();
                dobEDT1 = dobEDT.getText().toString();


                if (mobileNum1.isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }else if (userName1.isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "Please Enter User Name ", Toast.LENGTH_SHORT).show();
                } else if (EmailEDT1.isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }else if (dobEDT1.isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "Please Enter Date of Birth", Toast.LENGTH_SHORT).show();
                }

                insertData(selectedImage);

                Toast.makeText(EditProfileActivity.this, "Data Has Updated", Toast.LENGTH_SHORT).show();

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
                insertData(selectedImage);
            } catch (FileNotFoundException e) {

                Toast.makeText(EditProfileActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(EditProfileActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
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

        EditTextProfileInterface editTextProfileInterface = retrofit.create(com.interfaceForAPIs.EditTextProfileInterface.class);

        Call<EditTextModel> call = editTextProfileInterface.editTextProfile(1, String.valueOf(Gender),mobileNum1,userName1,EmailEDT1,dobEDT1,image);
        call.enqueue(new Callback<EditTextModel>() {
            @Override
            public void onResponse(Call<EditTextModel> call, Response<EditTextModel> response) {

                mobileNum.setText("");
                EmailEDT.setText("");
                userName.setText("");
                dobEDT.setText("");


                Toast.makeText(EditProfileActivity.this, "Data Has Changed " + response, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<EditTextModel> call, Throwable t) {

                mobileNum.setText("");
                EmailEDT.setText("");
                userName.setText("");
                dobEDT.setText("");

                Toast.makeText(EditProfileActivity.this, "Something went wrong"+ t, Toast.LENGTH_SHORT).show();

            }
        });



    }


}