package com.example.urplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.urplan.Authentication.EditProfileActivity;
import com.example.urplan.Authentication.UserProfile;
import com.example.urplan.CurrencyConverter.CurrencyConverterActivity;
import com.example.urplan.Dayplan.DayPlan;
import com.example.urplan.DoDont.DOList;
import com.example.urplan.ExperienceShare.ShareImages;
import com.example.urplan.ExploreCity.ExploreActivity;
import com.example.urplan.Hotels.HotelsList;
import com.example.urplan.MyTrips.TripLists;
import com.example.urplan.ReviewRating.ReviewList;
import com.example.urplan.TourSpeaker.SpeakerList;
import com.example.urplan.TravelDocuments.DocumentsActivity;
import com.example.urplan.Weather.WeatherActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawer;
    ImageView imageButton;
    LinearLayout currencyConverter;
    RelativeLayout dayPlan,travelDoc_Layout,explore_layout,important_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        dayPlan = findViewById(R.id.dayPlan);
        currencyConverter = findViewById(R.id.currencyConverter);
        explore_layout = findViewById(R.id.explore_layout);
        explore_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExploreActivity.class);
                startActivity(intent);
            }
        });
        currencyConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrencyConverterActivity.class);
                startActivity(intent);
            }
        });
        travelDoc_Layout = findViewById(R.id.travelDoc_Layout);
        important_layout = findViewById(R.id.important_layout);
        travelDoc_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DocumentsActivity.class);
                startActivity(intent);
            }
        });

        important_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImportantContact.class);
                startActivity(intent);
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.vNavigation);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        imageButton = findViewById(R.id.menu);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(Gravity.LEFT);
            }
        });

        dayPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, DayPlan.class);
               startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.my_profile) {

            Intent contact = new Intent(MainActivity.this, UserProfile.class);
            startActivity(contact);

        }else   if (id == R.id.hotel_detail) {

            Intent contact = new Intent(MainActivity.this, HotelsList.class);
            startActivity(contact);

        }else   if (id == R.id.my_trip) {

            Intent contact = new Intent(MainActivity.this, TripLists.class);
            startActivity(contact);

        }else   if (id == R.id.activate_itinerary) {

            Intent contact = new Intent(MainActivity.this, ActivateItinerary.class);
            startActivity(contact);

        }else   if (id == R.id.tour_travel) {

            Intent contact = new Intent(MainActivity.this, SpeakerList.class);
            startActivity(contact);

        }else   if (id == R.id.feedback) {

            Intent contact = new Intent(MainActivity.this, ReviewList.class);
            startActivity(contact);

        }else   if (id == R.id.share_exp) {

            Intent contact = new Intent(MainActivity.this, ShareImages.class);
            startActivity(contact);

        }else   if (id == R.id.do_dont) {

            Intent contact = new Intent(MainActivity.this, DOList.class);
            startActivity(contact);

        }else   if (id == R.id.weather) {

            Intent contact = new Intent(MainActivity.this, WeatherActivity.class);
            startActivity(contact);

        }

//        else if (id == R.id.rate) {
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.inmortal.wallpaperstore")));
//        } else if (id == R.id.refresh) {
//
//
//        } else if (id == R.id.contactUs) {
////            // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://inmortaltechnologies.com/Contact.html")));
////            Intent contact = new Intent(MainActivity.this, contact.class);
////            startActivity(contact);
//        }
        mDrawer.closeDrawer(GravityCompat.START);
        return false;
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}