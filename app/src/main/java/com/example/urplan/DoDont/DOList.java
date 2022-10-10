package com.example.urplan.DoDont;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.urplan.R;

public class DOList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolist);
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}