package com.example.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class usersignup extends AppCompatActivity {
    EditText ed1,ed2;
    Button sign_btn;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersignup);
        ed1=(EditText) findViewById(R.id.umail);
        ed2=(EditText) findViewById(R.id.u_pass);
        sign_btn=(Button) findViewById(R.id.u_signup);
        auth= FirebaseAuth.getInstance();
        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usermail=ed1.getText().toString();
                String userpass=ed2.getText().toString();

                if(TextUtils.isEmpty(usermail)|| TextUtils.isEmpty(userpass)|| TextUtils.isEmpty(usermail) && TextUtils.isEmpty(userpass)){
                    Toast.makeText(usersignup.this, "Please enter the above feilds", Toast.LENGTH_SHORT).show();
                }
                else{
                    registeruser(usermail,userpass);
                }
            }
        });
    }

    private void registeruser(String usermail, String userpass) {
        auth.createUserWithEmailAndPassword(usermail,userpass).addOnCompleteListener(usersignup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser u = auth.getCurrentUser();

                    u.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Intent i = new Intent(usersignup.this,welcome.class);
                            startActivity(i);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(usersignup.this, "Email not verified", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(usersignup.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}