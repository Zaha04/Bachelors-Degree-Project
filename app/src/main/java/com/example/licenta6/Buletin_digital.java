package com.example.licenta6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Buletin_digital extends AppCompatActivity {
private TextView nume2,prenum2,cnp2,serie2,numar2;
FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buletin_digital);
        nume2=findViewById(R.id.nume_read);
        prenum2=findViewById(R.id.prenume_read);
        cnp2=findViewById(R.id.cnp_read);
        serie2=findViewById(R.id.serie_read);
        numar2=findViewById(R.id.numar_read);
        mauth=FirebaseAuth.getInstance();


        String user_id=mauth.getCurrentUser().getUid();
        DatabaseReference current_user= FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    String nume1=snapshot.child("Nume").getValue().toString();
                    String prenume1=snapshot.child("Prenume").getValue().toString();
                    String cnp1=snapshot.child("CNP").getValue().toString();
                    String serie1=snapshot.child("Serie").getValue().toString();
                    String numar1=snapshot.child("Numar").getValue().toString();
                    nume2.setText(nume1);
                    prenum2.setText(prenume1);
                    cnp2.setText(cnp1);
                    serie2.setText(serie1);
                    numar2.setText(numar1);



                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}