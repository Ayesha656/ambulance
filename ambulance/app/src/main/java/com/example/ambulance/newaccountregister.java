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

public class newaccountregister extends AppCompatActivity {
EditText new_email,new_pass;
Button reg;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccountregister);
        new_email=(EditText) findViewById(R.id.n_mail);
        new_pass=(EditText) findViewById(R.id.n_pass);
        reg=(Button) findViewById(R.id.newbtn);
        auth=FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=new_email.getText().toString();
                String c=new_pass.getText().toString();
                checkuser(s,c);
            }
        });
    }

    private void checkuser(String s, String c) {
        auth.createUserWithEmailAndPassword(s,c).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent i = new Intent(newaccountregister.this,verficationmail.class);
                    i.putExtra("email",s);
                    startActivity(i);
                }
                else{
                    Toast.makeText(newaccountregister.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(newaccountregister.this, "Email not verified", Toast.LENGTH_SHORT).show();
            }
        });
    }


}