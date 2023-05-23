package com.example.licenta6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Incidente_utilizator extends AppCompatActivity {
    private TextInputEditText titlu1, descriere1;

    FirebaseAuth mauth;
    private View post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidente_utilizator);
        titlu1 = findViewById(R.id.titlu_incident1);
        descriere1 = findViewById(R.id.descriere);
        mauth = FirebaseAuth.getInstance();


        DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("Reclamatii");

        post = findViewById(R.id.postare_incident);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String descriere2 = descriere1.getText().toString();
                String titlu2 = titlu1.getText().toString();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                           /* String nume1=snapshot.child("Nume").getValue().toString();
                            String prenume1=snapshot.child("Prenume").getValue().toString();
                            String cnp1=snapshot.child("CNP").getValue().toString();*/
                    Map newPost = new HashMap();
                    newPost.put("strada", titlu2);
                    newPost.put("reclamatie", descriere2);

                    current_user.push().setValue(newPost);


                }
            }
        });
    }
}
