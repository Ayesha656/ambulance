package com.example.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class admin_edit extends AppCompatActivity {
EditText ad1,ad2,ad3,ad4,ad5,ad6;
Button bt;
FirebaseFirestore db;
Map<String,Object> editaccount = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);
    ad1=(EditText) findViewById(R.id.ed_mail);
    ad2=(EditText) findViewById(R.id.ed_number);
    ad3=(EditText) findViewById(R.id.ed_vehicle);
    ad4=(EditText) findViewById(R.id.ed_long);
    ad5=(EditText) findViewById(R.id.ed_lat);
    ad6=(EditText) findViewById(R.id.ed_name);
    bt=(Button) findViewById(R.id.edit);

    bt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String editmail=ad1.getText().toString();
            String editnumber=ad2.getText().toString();
            String editvehicle=ad3.getText().toString();
            String edit_long=ad4.getText().toString();
            String edit_lat=ad5.getText().toString();
            String edit_name=ad6.getText().toString();
            db=FirebaseFirestore.getInstance();
            editaccount.put("companyname",edit_name.toLowerCase());
            editaccount.put("companyvehicle",editvehicle.toLowerCase());
            editaccount.put("companynumber",editnumber.toLowerCase());
            editaccount.put("comlat",edit_lat.toLowerCase());
            editaccount.put("comlong",edit_long.toLowerCase());
            db.collection("registration").whereEqualTo("companymail",editmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        String documentID = documentSnapshot.getId();
                        db.collection("registration").document(documentID).update(editaccount).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Updated Failed", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    else{

                        Toast.makeText(getApplicationContext(), "Data Upldate Fail", Toast.LENGTH_SHORT).show();
                    }
                }


            });

        }
    });

    }
}