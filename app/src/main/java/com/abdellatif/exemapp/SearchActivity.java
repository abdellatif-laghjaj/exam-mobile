package com.abdellatif.exemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SearchView;

import com.abdellatif.exemapp.adapters.CooperativeAdapter;
import com.abdellatif.exemapp.models.Cooperative;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference dbRef;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private CooperativeAdapter adapter;
    private ArrayList<Cooperative> cooperativesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setTitle("Search Cooperative");

        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        readData();

        //search view listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Cooperative> filteredList = new ArrayList<>();

                for (Cooperative cooperative : cooperativesList) {
                    if (cooperative.getName().toLowerCase().contains(newText.toLowerCase())) {
                        filteredList.add(cooperative);
                    }
                }

                adapter.filterList(filteredList);
                return false;
            }
        });
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
                adapter = new CooperativeAdapter(SearchActivity.this, cooperativesList);
                recyclerView.setAdapter(adapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}