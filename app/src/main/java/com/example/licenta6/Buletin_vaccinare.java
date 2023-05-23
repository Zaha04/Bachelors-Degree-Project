package com.example.licenta6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Buletin_vaccinare extends AppCompatActivity {
    FirebaseAuth mauth;
View vaccinat,nevaccinat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buletin_vaccinare);
        mauth=FirebaseAuth.getInstance();
        vaccinat=findViewById(R.id.vaccinat);
        nevaccinat=findViewById(R.id.nevaccinat);


        String user_id=mauth.getCurrentUser().getUid();
        DatabaseReference current_user= FirebaseDatabase.getInstance().getReference().child("Vaccinari").child(user_id);
        current_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    String nume1=snapshot.child("status").getValue().toString();
                    if (nume1=="vaccinat"){
                        vaccinat.setVisibility(View.VISIBLE);
                        nevaccinat.setVisibility(View.INVISIBLE);
                    }
                    else{
                        vaccinat.setVisibility(View.INVISIBLE);
                        nevaccinat.setVisibility(View.VISIBLE);
                    }



                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}