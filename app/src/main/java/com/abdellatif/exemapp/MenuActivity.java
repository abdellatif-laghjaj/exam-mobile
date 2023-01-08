package com.abdellatif.exemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button view_cooperative_list_btn;
    private Button view_google_maps_btn;
    private Button view_search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //change title of the activity
        setTitle("Menu");

        view_google_maps_btn = findViewById(R.id.view_google_maps_btn);
        view_cooperative_list_btn = findViewById(R.id.view_cooperative_list_btn);
        view_search_btn = findViewById(R.id.view_search_btn);


        view_google_maps_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
            startActivity(intent);
        });

        view_cooperative_list_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, CartsActivity.class);
            startActivity(intent);
        });

        view_search_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, SearchActivity.class);
            startActivity(intent);
        });
    }
}