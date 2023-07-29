package com.example.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class signin extends AppCompatActivity {
    TextView tv;
    EditText ed1,ed2;
    Button signinbtn;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        tv=(TextView) findViewById(R.id.u_signup);
        ed1=(EditText) findViewById(R.id.s_mail);
        ed2=(EditText) findViewById(R.id.s_pass);
        auth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
      signinbtn=(Button) findViewById(R.id.s_signup); 
        tv.setOnClickListener(new View.OnClickListener() {
          
            @Override
            public void onClick(View view) {
               
                Intent i = new Intent(signin.this,usersignup.class);
                startActivity(i);
            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String checkuser=ed1.getText().toString();
                String checkpass=ed2.getText().toString();
                if(TextUtils.isEmpty(checkuser)|| TextUtils.isEmpty(checkpass)|| TextUtils.isEmpty(checkuser) && TextUtils.isEmpty(checkpass)){
                    Toast.makeText(signin.this, "Please enter the above fields", Toast.LENGTH_SHORT).show();
                }
                else{
                  login(checkuser,checkpass);
                }
            }
        });
    }

    private void login(String checkuser, String checkpass) {
        auth.signInWithEmailAndPassword(checkuser,checkpass).addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                    String uid;
                    uid=auth.getUid();

                        db.collection("user").whereEqualTo("uid",uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    QuerySnapshot document=task.getResult();
                                    if(document.isEmpty()) {
                                        Toast.makeText(getApplicationContext(), "Data not Found", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Data Found", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(signin.this,admin_view.class);
                                        startActivity(i);

                                    }
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });


                }
                else{
                    Toast.makeText(signin.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(signin.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signin.this, "Email id or password is incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}