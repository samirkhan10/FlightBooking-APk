package com.example.urplan.ServiceVoucher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.urplan.R;

public class VoucherList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_list);
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}