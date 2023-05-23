package com.example.licenta6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Parcare_lista extends AppCompatActivity {



    private RecyclerView recyclerView;
    Adapter_parcare adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    private android.content.Context Context;
    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcare_lista);

        // Create a instance of the database and get
        // its reference
        mbase= FirebaseDatabase.getInstance().getReference("PARCARE1");

        recyclerView = findViewById(R.id.recview);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Model_parcare> options
                = new FirebaseRecyclerOptions.Builder<Model_parcare>()
                .setQuery(mbase, Model_parcare.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new Adapter_parcare(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}