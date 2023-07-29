package com.example.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class fingings extends AppCompatActivity {
    RecyclerView recyclerView2;
    Myadapter2 myadapter2;
    FirebaseFirestore db;
    ArrayList<comany_view> userArraylist2;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingings);

        progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data");
        progressDialog.show();
        recyclerView2=findViewById(R.id.recyclerview);
      recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseFirestore.getInstance();
        userArraylist2=new ArrayList<comany_view>();
        myadapter2= new Myadapter2(fingings.this,userArraylist2);
        recyclerView2.setAdapter(myadapter2);
        EditText edrr=findViewById(R.id.ed2);
        edrr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("v","word is changed to "+editable.toString());
                db.collection("registration")
                        .whereEqualTo("companyname",editable.toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("vv", document.getId() + " => " + document.getData());
                                for (DocumentChange dc : task.getResult().getDocumentChanges()) {
                                    if (dc.getType() == DocumentChange.Type.ADDED) {
                                        userArraylist2.add(dc.getDocument().toObject(comany_view.class));

                                    }
                                    myadapter2.notifyDataSetChanged();
                                    //

                                }
                            }

                        }
                        else {
                            Log.d("ee", "Error getting documents: ", task.getException());
                        }
                    }
                });

            }
        });
    }
}