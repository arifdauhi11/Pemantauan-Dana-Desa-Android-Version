package com.arifdauhi.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arifdauhi.utils.Bidang;
import com.arifdauhi.utils.BidangAdapter;
import com.arifdauhi.utils.Tools;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BidangActivity extends AppCompatActivity {
    public ActionBar actionBar;
    public RecyclerView bidangList;
    public BidangAdapter bidangAdapter;
    private Toolbar toolbar;

    private static final String URL = "https://arifdauhi.tk/api/bidang";
//      private static final String URL = "http://192.168.10.1/pedede/api/bidang";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidang);
        bidangList = findViewById(R.id.lsBidang);
        bidangList.setLayoutManager(new LinearLayoutManager(this));
        initToolbar();
        getBidang();
        final Handler updateData = new Handler();
        updateData.postDelayed(new Runnable() {
            @Override
            public void run() {
                getBidang();
                updateData.postDelayed(this,5000);
            }
        },5000);
    }


    private void getBidang() {
        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Bidang",response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Bidang[] bidangs = gson.fromJson(response, Bidang[].class);
                bidangAdapter = new BidangAdapter(BidangActivity.this, bidangs);
                bidangList.setAdapter(bidangAdapter);
                bidangAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Eror", String.valueOf(error));
                Toast.makeText(BidangActivity.this, "Terjadi kesalahan!", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setTitle("Bidang");
        Tools.setSystemBarColor(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(BidangActivity.this);
    }
}