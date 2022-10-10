package com.example.urplan.FlightDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.urplan.R;

public class FlightList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);
    }

    public void Back(View view) {

        super.onBackPressed();

    }

    public void addTicket(View view) {
        Intent intent = new Intent(FlightList.this,AddTickets.class);
        startActivity(intent);
    }
}