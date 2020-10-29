package com.rohit.youtubesearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String API_KEY = "AIzaSyDdVfEHbbss9v05BErNviWla_g1kEp816I" ;
    private RecyclerView rView ;
    private EditText searchBar ;
    private Boolean gotData ;
    private static String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&key=" ;
    private String text ;
    private ArrayList<VideoObject> videoObjects = new ArrayList<>() ; ;
    private String title, description, channel, thumbnail ;
    private YtAdapter ytAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rView = findViewById(R.id.r_view) ;
        rView.setHasFixedSize(true) ;
        rView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        searchBar = findViewById(R.id.search_bar) ;
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                text = s.toString() ;
                if(text.length()>=3) {
                    gotData = false ;
                    waitForData();
                    getData() ;
                }
            }
        });

    }
    @SuppressLint("LongLogTag")
    public void getData() {
        String URL = url + API_KEY + "&type=video&q=" + text ;
//        Log.d(URL," yrk ") ;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    videoObjects.clear();
                    JSONArray items = response.getJSONArray("items") ;
                    for(int i = 0 ; i < 5 ; i++) {
                        JSONObject object = items.getJSONObject(i) ;
                        JSONObject snippet = object.getJSONObject("snippet") ;
                        title = snippet.getString("title") ;
                        description = snippet.getString("description") ;
                        channel = snippet.getString("channelTitle") ;
                        JSONObject thumb = snippet.getJSONObject("thumbnails") ;
                        JSONObject def = thumb.getJSONObject("default") ;
                        thumbnail = def.getString("url") ;
                        VideoObject videoObject = new VideoObject(title,channel,description,thumbnail) ;
                        videoObjects.add(videoObject) ;
                    }

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            ytAdapter = new YtAdapter(MainActivity.this, videoObjects) ;
                            rView.setAdapter(ytAdapter) ;
                        }
                    } ;
                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(runnable, 500);
                    gotData = true;

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
    public void waitForData() {
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                if (gotData != true) {
                    Toast.makeText(getApplicationContext(), "Internet slow/not available", Toast.LENGTH_SHORT).show();
                }
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);
    }


}