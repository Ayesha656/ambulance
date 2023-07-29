package com.example.ambulance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admindashboard extends AppCompatActivity {
CardView c1,c2,c3,c4,c5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);
        c1=(CardView) findViewById(R.id.register);
        c2=(CardView) findViewById(R.id.edit);
        c3=(CardView) findViewById(R.id.delete);
        c4=(CardView) findViewById(R.id.view);
        c5=(CardView) findViewById(R.id.getmap);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(admindashboard.this, newaccountregister.class);
                startActivity(i);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(admindashboard.this, admin_edit.class);
                startActivity(i);
            }
        });        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(admindashboard.this, admin_delete.class);
                startActivity(i);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(admindashboard.this, admin_view.class);
                startActivity(i);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(admindashboard.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }
}