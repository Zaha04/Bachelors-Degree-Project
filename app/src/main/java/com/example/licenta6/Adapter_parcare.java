package com.example.licenta6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class Adapter_parcare extends FirebaseRecyclerAdapter<
        Model_parcare, Adapter_parcare.personsViewholder> {

    public Adapter_parcare(
            @NonNull FirebaseRecyclerOptions<Model_parcare> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull Model_parcare model) {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.status.setText(model.getStatus());
        holder.title.setText(model.getTitle());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_parcare_template, parent, false);
        return new Adapter_parcare.personsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView status;
        TextView title;
        View parking;

        public personsViewholder(@NonNull View itemView) {
            super(itemView);
            parking=itemView.findViewById(R.id.parking_view);
            status
                    = itemView.findViewById(R.id.liber);
            title=itemView.findViewById(R.id.loc1);
        }
    }
}


