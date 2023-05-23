package com.example.licenta6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class After_register extends AppCompatActivity {
private TextInputEditText nume1,prenume1,cnp1,serie1,numar1;
private View save_informatii;
FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_register);
        nume1=findViewById(R.id.nume_after);
        prenume1=findViewById(R.id.prenume_after);
        cnp1=findViewById(R.id.cnp_after) ;
        mauth=FirebaseAuth.getInstance();
        serie1=findViewById(R.id.serie_buletin);
        numar1=findViewById(R.id.numar_buletin);
        save_informatii=findViewById(R.id.save_info);
        save_informatii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salveazainformatii();
            }
        });

    }

    private void salveazainformatii() {
        String user_id=mauth.getCurrentUser().getUid();
        String nume2=nume1.getText().toString();
        String prenume2=prenume1.getText().toString();
        String cnp2=cnp1.getText().toString();
        String serie2=serie1.getText().toString();
        String numar2=numar1.getText().toString();
        DatabaseReference current_user= FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        Map newPost=new HashMap();
        newPost.put("Nume",nume2);
        newPost.put("Prenume",prenume2);
        newPost.put("CNP",cnp2);
        newPost.put("Serie",serie2);
        newPost.put("Numar",numar2);
        current_user.setValue(newPost);
        Toast.makeText(this,"Ai salvat cu succes!",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}