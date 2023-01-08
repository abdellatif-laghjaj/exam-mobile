package com.abdellatif.exemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.abdellatif.exemapp.adapters.CooperativeAdapter;
import com.abdellatif.exemapp.models.Cooperative;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartsActivity extends AppCompatActivity {
    private TextView textView;
    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;
    private ArrayList<Cooperative> cooperativesList;
    private CooperativeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);

        setTitle("Cooperative List");

        initViews();
//        insertData();
        readData();
    }

    //init views
    private void initViews() {
        textView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.recyclerView);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();
    }

    //insert dummy data
    private void insertData() {
        int[] logos = {R.drawable.logo_1, R.drawable.logo_2, R.drawable.logo_3, R.drawable.logo_4, R.drawable.logo_5};
        for (int i = 0; i < 20; i++) {
            Cooperative cooperative = new Cooperative("Cooperative #" + i, "Type " + i, logos[i % 5]);
            dbRef.child("cooperatives").child(String.valueOf(i)).setValue(cooperative);
        }
    }

    //read data from firebase
    private void readData() {
        dbRef.child("cooperatives").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cooperativesList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Cooperative cooperative = dataSnapshot.getValue(Cooperative.class);
                    cooperativesList.add(cooperative);
                }
                adapter = new CooperativeAdapter(CartsActivity.this, cooperativesList);
                recyclerView.setAdapter(adapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartsActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}