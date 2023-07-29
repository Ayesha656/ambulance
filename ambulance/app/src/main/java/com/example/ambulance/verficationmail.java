package com.example.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class verficationmail extends AppCompatActivity {
private FirebaseAuth auth;
private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verficationmail);
auth=FirebaseAuth.getInstance();
db= FirebaseFirestore.getInstance();
        Map<String, Object> adddata = new HashMap<>();
        Bundle bundle = getIntent().getExtras();
        Button v= findViewById(R.id.vr);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=getIntent().getStringExtra("email");
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   if (task.isSuccessful()){
                       adddata.put("companymail",email);
                       db.collection("registration").add(adddata).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                           @Override
                           public void onSuccess(DocumentReference documentReference) {
                               Intent i = new Intent(verficationmail.this,admindashboard.class);
                               startActivity(i);
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Log.e("aa","there is an error");
                           }
                       });

                   }
                   else{
                       Log.e("aa","there is an error");
                   }
                    }
                });
            }
        });

    }
}