package com.example.urplan.TravelDocuments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.urplan.FlightDoc.FlightList;
import com.example.urplan.PassportUpload;
import com.example.urplan.PhotoIDUpload;
import com.example.urplan.R;
import com.example.urplan.ServiceVoucher.VoucherList;

public class DocumentsActivity extends AppCompatActivity {
RelativeLayout travelDoc_Layout,voucher_layout,photo_layout,passport_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        travelDoc_Layout = findViewById(R.id.flight_Layout);
        voucher_layout = findViewById(R.id.voucher_layout);
        passport_layout = findViewById(R.id.passport_layout);
        photo_layout = findViewById(R.id.photo_layout);
        travelDoc_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentsActivity.this, FlightList.class);
                startActivity(intent);
            }
        });

        voucher_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentsActivity.this, VoucherList.class);
                startActivity(intent);
            }
        });

        passport_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentsActivity.this, PassportUpload.class);
                startActivity(intent);
            }
        });

        photo_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentsActivity.this, PhotoIDUpload.class);
                startActivity(intent);
            }
        });
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}