package com.rohit.youtubesearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private final String API_KEY = "AIzaSyDdVfEHbbss9v05BErNviWla_g1kEp816I" ;
    private RecyclerView rView = findViewById(R.id.r_view) ;
    private EditText searchBar = findViewById(R.id.search_bar) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}