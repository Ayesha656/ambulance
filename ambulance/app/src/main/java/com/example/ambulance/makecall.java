package com.example.ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class makecall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makecall);
        Bundle bundle = getIntent().getExtras();
        String phone=getIntent().getStringExtra("contactinfo");
        String x="tel:"+phone;
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse(x));
        startActivity(i);
    }
}