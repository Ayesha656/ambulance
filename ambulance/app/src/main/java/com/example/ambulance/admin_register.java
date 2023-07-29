package com.example.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class admin_register extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4,ed5,ed6;
Button btn1;
FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=FirebaseFirestore.getInstance();
        Map<String, Object> users = new HashMap<>();
        setContentView(R.layout.activity_admin_register);
        ed1=(EditText) findViewById(R.id.com_name);
        ed2=(EditText) findViewById(R.id.com_mail);
        ed3=(EditText) findViewById(R.id.com_number);
        ed4=(EditText) findViewById(R.id.com_long);
        ed5=(EditText) findViewById(R.id.com_lat);
        ed6=(EditText) findViewById(R.id.com_vehicle);
        btn1=(Button) findViewById(R.id.register);


        
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c_name,c_mail,c_number,c_long,c_lat,c_vehicle;
                c_name=ed1.getText().toString();
                c_mail=ed2.getText().toString();
                c_number=ed3.getText().toString();
                c_long=ed4.getText().toString();
                c_lat=ed5.getText().toString();
                c_vehicle=ed6.getText().toString();
                users.put("comlat",c_lat.toLowerCase());
                users.put("comlong",c_long.toLowerCase());
                users.put("companymail",c_mail.toLowerCase());
                users.put("companyname",c_name.toLowerCase());
                users.put("companynumber",c_number.toLowerCase());
                users.put("companyvehicle",c_vehicle.toLowerCase());
                db.collection("registration")
                        .add(users)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d( "aa", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(getApplicationContext(), "data is added succefully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(admin_register.this,admindashboard.class);
                                startActivity(i);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("kssks","Error adding document", e);
                        Toast.makeText(getApplicationContext(), "Error while uploading data ", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }

}