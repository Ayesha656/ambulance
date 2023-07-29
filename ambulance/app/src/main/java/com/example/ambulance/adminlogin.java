package com.example.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class adminlogin extends AppCompatActivity {
EditText admin_email, admin_password;
Button admin_selected;
private FirebaseAuth auth;
private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        admin_email=(EditText) findViewById(R.id.admin_mail);
        admin_password=(EditText) findViewById(R.id.admin_pass);
        admin_selected=(Button) findViewById(R.id.admin_signin);
        auth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        admin_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String admin1=admin_email.getText().toString();
                String admin2=admin_password.getText().toString();
                login(admin1,admin2);
            }
        });

    }

    private void login(String admin1, String admin2) {
        auth.signInWithEmailAndPassword(admin1,admin2).addOnCompleteListener(adminlogin.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               String uid;
               uid=auth.getUid();
               Toast.makeText(adminlogin.this,"loginsuccess 1",Toast.LENGTH_SHORT).show();
               db.collection("admin").whereEqualTo("uid",uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if(task.isSuccessful()){
                          QuerySnapshot document=task.getResult();
                          if(document.isEmpty()) {
                              Toast.makeText(getApplicationContext(), "Data not Found", Toast.LENGTH_SHORT).show();
                          }
                          else{
                              Toast.makeText(getApplicationContext(), "Data Found", Toast.LENGTH_SHORT).show();
                              Intent i = new Intent(adminlogin.this,admindashboard.class);
                              startActivity(i);

                          }
                       }


                   }
               });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(adminlogin.this,"login access failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}