package com.arifdauhi.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arifdauhi.utils.Bidang;
import com.arifdauhi.utils.BidangAdapter;
import com.arifdauhi.utils.Program;
import com.arifdauhi.utils.ProgramAdapter;
import com.arifdauhi.utils.Tools;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class ProgramActivity extends AppCompatActivity {

    public RecyclerView programList;
    public ProgramAdapter programAdapter;
    private Toolbar toolbar;
    private static final String URL = "https://arifdauhi.tk/api/program";
//    private static final String URL = "http://192.168.10.1/pedede/api/program";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        initToolbar();
        programList = findViewById(R.id.lsProgram);
        programList.setLayoutManager(new LinearLayoutManager(this));
        getProgram();
    }

    private void getProgram() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Program",response);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        Program[] programs = gson.fromJson(response, Program[].class);
                        programAdapter = new ProgramAdapter(ProgramActivity.this,programs);
                        programList.setAdapter(programAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Eror", String.valueOf(error));
                        Toast.makeText(ProgramActivity.this, "Terjadi kesalahan!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(ProgramActivity.this);
        requestQueue.add(stringRequest);
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
        toolbar.setTitle("Program");
        Tools.setSystemBarColor(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProgramActivity.this, DashboardActivity.class));
        Animatoo.animateSlideRight(ProgramActivity.this);
    }
}
