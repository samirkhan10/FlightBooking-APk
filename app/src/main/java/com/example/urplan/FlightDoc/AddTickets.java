package com.example.urplan.FlightDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.urplan.Authentication.OTPActivity;
import com.example.urplan.Authentication.RegisterActivity;
import com.example.urplan.R;
import com.interfaceForAPIs.AddTicketsInterface;
import com.interfaceForAPIs.registerAPI;
import com.model.AddticketsModel;
import com.model.registerModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddTickets extends AppCompatActivity {

    EditText flightNum,departureAirport,departureDateTime,arrivalAirport,arrivalDateTime;
    LinearLayout Regisster;
    ImageView uploadFlightTicket;
    String flightNum1,departureAirport1,departureDateTime1,arrivalAirport1,arrivalDateTime1;
    Bitmap selectedImage;
    private static final String api ="http://UrPlan_App.svc/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tickets);

        uploadFlightTicket = findViewById(R.id.uploadFlightTicket);
        flightNum = findViewById(R.id.flightNum);
        departureAirport = findViewById(R.id.departureAirport);
        departureDateTime = findViewById(R.id.departureDateTime);
        arrivalAirport = findViewById(R.id.arrivalAirport);
        arrivalDateTime = findViewById(R.id.arrivalDateTime);


        Regisster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 flightNum1 = flightNum.getText().toString();
                 departureAirport1 = departureAirport.getText().toString();
                 departureDateTime1 = departureDateTime.getText().toString();
                 arrivalAirport1 = arrivalAirport.getText().toString();
                 arrivalDateTime1 = arrivalDateTime.getText().toString();


                if (flightNum1.isEmpty()) {
                    Toast.makeText(AddTickets.this, "Please Enter Flight Number", Toast.LENGTH_SHORT).show();
                }else if (departureAirport1.isEmpty()) {
                    Toast.makeText(AddTickets.this, "Please Enter Departure Airport ", Toast.LENGTH_SHORT).show();
                } else if (departureDateTime1.isEmpty()) {
                    Toast.makeText(AddTickets.this, "Please Enter Departure DateTime", Toast.LENGTH_SHORT).show();
                }else if (arrivalAirport1.isEmpty()) {
                    Toast.makeText(AddTickets.this, "Please Enter Arrival Airport", Toast.LENGTH_SHORT).show();
                }else if (arrivalDateTime1.isEmpty()) {
                    Toast.makeText(AddTickets.this, "Please Enter Arrival DateTime", Toast.LENGTH_SHORT).show();
                }

                insertData(selectedImage);

                Toast.makeText(AddTickets.this, "Data Has Inserted", Toast.LENGTH_SHORT).show();

            }
        });






        uploadFlightTicket.setOnClickListener(new View.OnClickListener() {
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
                selectedImage = BitmapFactory.decodeStream(imageStream);
                uploadFlightTicket.setImageBitmap(selectedImage);
                insertData(selectedImage);


            } catch (FileNotFoundException e) {

                Toast.makeText(AddTickets.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(AddTickets.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }


    private void insertData(Bitmap selectedImage) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AddTicketsInterface addTicketsInterface = retrofit.create(com.interfaceForAPIs.AddTicketsInterface.class);

        Call<AddticketsModel> call = addTicketsInterface.addTickets(1,flightNum1,departureAirport1,departureDateTime1,arrivalAirport1,arrivalDateTime1,image);
        call.enqueue(new Callback<AddticketsModel>() {
            @Override
            public void onResponse(Call<AddticketsModel> call, Response<AddticketsModel> response) {

                flightNum.setText("");
                departureAirport.setText("");
                departureDateTime.setText("");
                arrivalAirport.setText("");
                arrivalDateTime.setText("");

                Toast.makeText(AddTickets.this, "Data Has Inserted " + response, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<AddticketsModel> call, Throwable t) {

                flightNum.setText("");
                departureAirport.setText("");
                departureDateTime.setText("");
                arrivalAirport.setText("");
                arrivalDateTime.setText("");

                Toast.makeText(AddTickets.this, "Something went wrong"+ t, Toast.LENGTH_SHORT).show();

            }
        });



    }



}