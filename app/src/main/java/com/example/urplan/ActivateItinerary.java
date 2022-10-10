package com.example.urplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urplan.Authentication.OTPActivity;

import java.util.Objects;

public class ActivateItinerary extends AppCompatActivity {
    String strbookingId,strpickup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_itinerary);

        final Dialog dialog = new Dialog(Objects.requireNonNull(this));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setContentView(R.layout.layout_activate);

        ImageView cancle = dialog.findViewById(R.id.cancle);
        LinearLayout Submit = dialog.findViewById(R.id.Submit);
        EditText bookinId = dialog.findViewById(R.id.bookinId);
        EditText pickup = dialog.findViewById(R.id.pickup);


        cancle.setOnClickListener(v1 -> {
            dialog.dismiss();
            Intent intent = new Intent(ActivateItinerary.this,MainActivity.class);
            startActivity(intent);
            finish();

        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strbookingId = bookinId.getText().toString();
                strpickup = pickup.getText().toString();


                if (strbookingId.isEmpty()) {
                    Toast.makeText(ActivateItinerary.this, "Please Enter Valid Booking ID", Toast.LENGTH_SHORT).show();

                } else if (strpickup.isEmpty()) {

                    Toast.makeText(ActivateItinerary.this, "Please Enter Valid Pickup Location", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivateItinerary.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();


                }

            }
        });


        dialog.show();
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}