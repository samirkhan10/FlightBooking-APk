package com.example.urplan.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urplan.MainActivity;
import com.example.urplan.R;

public class OTPActivity extends AppCompatActivity {
    TextView otp_timer,btn_resend_otp;
    long timerDuration = 60000;
    LinearLayout Submit;
    EditText otp;
    String strotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);

        otp_timer = findViewById(R.id.otp_timer);
        otp = findViewById(R.id.otp);
        Submit = findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strotp = otp.getText().toString();


                if (strotp.isEmpty()) {
                    Toast.makeText(OTPActivity.this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();

                }

               else {
                    Intent intent = new Intent(OTPActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
        btn_resend_otp = findViewById(R.id.btn_resend_otp);
        startCountDown();
    }

    void startCountDown() {


        new CountDownTimer(timerDuration, 1000) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                long sec = (l / 1000);
                if (sec >= 10) {
                    otp_timer.setText("00:" + sec);
                } else {
                    String format = "%1$02d";
                    otp_timer.setText("00:" + String.format(format,sec));
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                otp_timer.setText("00:00");
            }
        }.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_resend_otp.setEnabled(true);

            }
        }, timerDuration);
    }
}