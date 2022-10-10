package com.example.urplan.ReviewRating;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.urplan.R;

public class ReviewList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}