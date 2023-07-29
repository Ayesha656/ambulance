package com.example.ambulance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

    Context context;
    ArrayList<comany_view> userArraylist;
    public Myadapter(Context context, ArrayList<comany_view> userArraylist) {
        this.context = context;
        this.userArraylist = userArraylist;
    }



    @NonNull
    @Override
    public Myadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.MyViewHolder holder, int position) {
        comany_view c=userArraylist.get(position);
        holder.comp_name.setText(c.companyname);
        holder.comp_number.setText(c.companynumber);
        holder.comp_map1.setText(c.comlong);
        holder.comp_map2.setText(c.comlat);
        holder.comp_vehicle.setText(c.companyvehicle);
        holder.comp_mail.setText(c.companymail);
        holder.comp_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(),makecall.class);
                i.putExtra("contactinfo",c.companynumber);
                view.getContext().startActivity(i);
            }
        });
        holder.comp_map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),MapsActivity.class);
                i.putExtra("comlong",c.comlong);
                i.putExtra("complat",c.comlat);
                view.getContext().startActivity(i);
            }
        });
        holder.comp_map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),MapsActivity.class);
                i.putExtra("comlong",c.comlong);
                i.putExtra("complat",c.comlat);
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userArraylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView comp_name,comp_number,comp_vehicle,comp_map1,comp_map2,comp_mail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            comp_name=itemView.findViewById(R.id.c_name);
            comp_number=itemView.findViewById(R.id.c_number);
            comp_vehicle=itemView.findViewById(R.id.c_vehicle);
            comp_map1=itemView.findViewById(R.id.c_map1);
            comp_map2=itemView.findViewById(R.id.c_map2);
            comp_mail=itemView.findViewById(R.id.c_mail);

        }
    }
}
