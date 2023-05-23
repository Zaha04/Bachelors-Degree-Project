package com.example.licenta6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Problema_sanatate extends AppCompatActivity {
private TextView nume,prenume,cnp,serie,numar,ora,data,simptome;
FirebaseAuth mauth;
private View finalizare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema_sanatate);


finalizare=findViewById(R.id.finalizeaza_buton);
        nume=findViewById(R.id.nume_get);
        prenume=findViewById(R.id.prenume_get);
        cnp=findViewById(R.id.cnp_get);
        serie=findViewById(R.id.serie_get);
        numar=findViewById(R.id.numer_get);
        ora=findViewById(R.id.ora_get);
        data=findViewById(R.id.data_get);
        finalizare=findViewById(R.id.finalizeaza_buton);
simptome=findViewById(R.id.simptome_get);
        mauth=FirebaseAuth.getInstance();
        String user_id=mauth.getCurrentUser().getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Programari");
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
                    nume.setText(nume1);
                    prenume.setText(prenume1);
                    cnp.setText(cnp1);
                    serie.setText(serie1);
                    numar.setText(numar1);



                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteazaora();
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alegedata();
            }
        });
finalizare.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String nume2=nume.getText().toString();
        String prenume2=prenume.getText().toString();
        String cnp2=cnp.getText().toString();
        String serie2=serie.getText().toString();
        String numar2=numar.getText().toString();
        String ora2=ora.getText().toString();
        String data2=data.getText().toString();
        String simptome2=simptome.getText().toString();
        Map newPost=new HashMap();
        newPost.put("nume",nume2+prenume2);
        newPost.put("prenume",prenume2);
        newPost.put("cnp",cnp2);
        newPost.put("serie",serie2);
        newPost.put("numar",numar2);
        newPost.put("ora",ora2);
        newPost.put("data",data2);
        newPost.put("simptome",simptome2);
        reference.push().setValue(newPost);

    }
});



    }

    private void alegedata() {
        Calendar calendar=Calendar.getInstance();

        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int date=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
String dataset=date+"/"+month+"/"+year;
data.setText(dataset);
            }
        },year,month,date);
        datePickerDialog.show();
    }

    private void selecteazaora() {
        int hour1,minute1;
        Calendar c1=Calendar.getInstance();
        hour1=c1.get(Calendar.HOUR);
        minute1=c1.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
String timestring="Ora:"+hourOfDay+":"+minute;
ora.setText(timestring);

            }
        },hour1,minute1,false);
        timePickerDialog.show();

    }
}