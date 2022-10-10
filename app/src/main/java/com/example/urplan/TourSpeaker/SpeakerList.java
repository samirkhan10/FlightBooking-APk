package com.example.urplan.TourSpeaker;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.urplan.R;

public class SpeakerList extends AppCompatActivity {
RelativeLayout filter,textfilters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_list);
        filter = findViewById(R.id.filter);
        textfilters = findViewById(R.id.textFilters);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textfilters.getVisibility()== View.GONE){
                    textfilters.setVisibility(View.VISIBLE);
                }else{
                    textfilters.setVisibility(View.GONE);
                }
            }
        });
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}