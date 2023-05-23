package com.example.licenta6;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private View login,register,parcare,calitatea_aerului,temperatura,buletin_digital,editare_profil,log_out,incident,buletin_vaccinare,programare;
    private TextView ora,temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login_rectangle);
        register=findViewById(R.id.register_rectangle);
        parcare=findViewById(R.id.parcare_rectangle);

        temperatura=findViewById(R.id.temperatura_elipse);
        buletin_digital=findViewById(R.id.buletin_elipse);
        editare_profil=findViewById(R.id.editare_profil_elipse);
        log_out=findViewById(R.id.log_out_elipse);
        temperature=findViewById(R.id.temperature_main);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("DHT11").child("Temperature");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String temp=snapshot.getValue().toString();
                temperature.setText(temp+" C");
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        incident=findViewById(R.id.incident_elipse);
        buletin_vaccinare=findViewById(R.id.buletin_vaccinare_elipse);
        programare=findViewById(R.id.programare_rectangle);
        programare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_programare();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_login();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_register();
            }
        });
        parcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_parcare();
            }
        });

        temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_temperatura();
            }
        });
        buletin_digital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_buletindigital();
            }
        });
        editare_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_editare_profil();
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        incident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_incident();
            }
        });
        buletin_vaccinare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_buletin_vaccinare();
            }
        });


    }

    private void open_programare() {
        Intent intent=new Intent(this, Problema_sanatate.class);
        startActivity(intent);
    }

    private void open_buletin_vaccinare() {
        Intent intent=new Intent(this,Buletin_vaccinare.class);
        startActivity(intent);
    }

    private void open_incident() {
        Intent intent=new Intent(this,Incidente_utilizator.class);
        startActivity(intent);
    }



    private void open_editare_profil() {
        Intent intent=new Intent(this,Editare_profil.class);
        startActivity(intent);
    }

    private void open_buletindigital() {
        Intent intent=new Intent(this,Buletin_digital.class);
        startActivity(intent);
    }

    private void open_temperatura() {
        Intent intent=new Intent(this,Temperatura.class);
        startActivity(intent);
    }



    private void open_parcare() {
        Intent intent=new Intent(this,Parcare_lista.class);
        startActivity(intent);
    }

    private void open_register() {
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }

    private void open_login() {
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }


}