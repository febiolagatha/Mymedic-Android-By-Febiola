package com.example.tenagamedis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    //initialize variable
    RecyclerView recyclerView;
    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

         //Assign Variable
        recyclerView = findViewById(R.id.recycler_view);

        //create integer array
        Integer[] numberImages = {R.drawable.n1,R.drawable.n2,R.drawable.n3
                ,R.drawable.n4,R.drawable.n5,R.drawable.n6,R.drawable.n7
                ,R.drawable.n8,R.drawable.n9,R.drawable.n10,R.drawable.n11
                ,R.drawable.n12,R.drawable.n13,R.drawable.n14};

        //Create string array
        String[] numberWords = {"Penularan","Melalui Tangan","Bersin","Melalui Udara","Melalui Hidung"
                ,"Melalui Mulut","Menyentuh Wajah","Mata","Pencegahan","Stay Home","Memakai Masker"
                ,"Cuci Tangan","Siku menutup mulut ketika batuk","Jaga Jarak"};

        //Initialize ArrayList
        mainModels = new ArrayList<>();
        for (int i=0; i<numberImages.length;i++) {
            MainModel mainModel = new MainModel(numberImages[i],numberWords[i]);
            this.mainModels.add(mainModel);
        }

        //Design RecyclerView
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(
                2,StaggeredGridLayoutManager.VERTICAL
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialize ArrayAdpter
        mainAdapter = new MainAdapter(Dashboard.this,mainModels);

        //Set Adapter to Recyclerview
        recyclerView.setAdapter(mainAdapter);

        //buat bottom
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home
        bottomNavigationView.setSelectedItemId(R.id.info);
        //Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.info:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,Navigation.class));
                        overridePendingTransition(0, 0 );
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
    }
}
