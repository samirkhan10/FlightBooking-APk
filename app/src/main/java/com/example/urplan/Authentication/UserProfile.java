package com.example.urplan.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.urplan.R;

public class UserProfile extends AppCompatActivity {
TextView editProfile , myInterest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editProfile = findViewById(R.id.editProfile);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    myInterest.setBackgroundDrawable(ContextCompat.getDrawable(UserProfile.this, R.drawable.left_radius_unselected) );
                    editProfile.setBackgroundDrawable(ContextCompat.getDrawable(UserProfile.this, R.drawable.right_radius_selected) );
                    editProfile.setTextColor(getResources().getColor(R.color.white));
                    myInterest.setTextColor(getResources().getColor(R.color.main));
                    Intent intent = new Intent(UserProfile.this,EditProfileActivity.class);
                    startActivity(intent);

                } else {
                    myInterest.setBackground(ContextCompat.getDrawable(UserProfile.this, R.drawable.left_radius_unselected));
                    editProfile.setBackground(ContextCompat.getDrawable(UserProfile.this, R.drawable.right_radius_selected));
                    editProfile.setTextColor(getResources().getColor(R.color.white));
                    myInterest.setTextColor(getResources().getColor(R.color.main));
                    Intent intent = new Intent(UserProfile.this,EditProfileActivity.class);
                    startActivity(intent);

                }
            }
        });
        myInterest = findViewById(R.id.myInterest);
        myInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    myInterest.setBackgroundDrawable(ContextCompat.getDrawable(UserProfile.this, R.drawable.left_radius_selected) );
                    editProfile.setBackgroundDrawable(ContextCompat.getDrawable(UserProfile.this, R.drawable.right_radius_unselected) );
                    myInterest.setTextColor(getResources().getColor(R.color.white));
                    editProfile.setTextColor(getResources().getColor(R.color.main));

                } else {
                    myInterest.setBackground(ContextCompat.getDrawable(UserProfile.this, R.drawable.left_radius_selected));
                    editProfile.setBackground(ContextCompat.getDrawable(UserProfile.this, R.drawable.right_radius_unselected));
                    myInterest.setTextColor(getResources().getColor(R.color.white));
                    editProfile.setTextColor(getResources().getColor(R.color.main));
                }
            }
        });
    }
}