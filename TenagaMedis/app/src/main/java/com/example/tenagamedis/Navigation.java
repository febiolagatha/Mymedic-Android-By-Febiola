package com.example.tenagamedis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navigation extends AppCompatActivity {
    Button btn_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set home
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()) {
                   case R.id.info:
                       startActivity(new Intent(getApplicationContext()
                       ,Dashboard.class));
                       overridePendingTransition(0, 0 );
                       return true;
                   case R.id.home:
                       return true;
                   case R.id.map:
                       startActivity(new Intent(getApplicationContext()
                               ,MapsActivity.class));
                       overridePendingTransition(0, 0 );
                       return true;
               }
                return false;
            }
        });

        btn_daftar = findViewById(R.id.btn_daftar);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Navigation.this, database.class);
                startActivity(i);

        }
        });


    }
}
