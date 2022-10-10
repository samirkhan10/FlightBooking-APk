package com.example.urplan.MyTrips;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.urplan.R;

public class TripLists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_lists);
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}