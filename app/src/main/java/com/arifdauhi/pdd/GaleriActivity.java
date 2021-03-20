package com.arifdauhi.pdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arifdauhi.utils.Bidang;
import com.arifdauhi.utils.BidangAdapter;
import com.arifdauhi.utils.Galeri;
import com.arifdauhi.utils.GaleriAdapter;
import com.arifdauhi.utils.Tools;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GaleriActivity extends AppCompatActivity {
    public ActionBar actionBar;
    private Toolbar toolbar;
    public RecyclerView galeriList;
    public GaleriAdapter galeriAdapter;

    private static final String URL_IMAGE = "https://arifdauhi.tk/api/galeri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri);
        galeriList = findViewById(R.id.listGaleri);
        galeriList.setLayoutManager(new LinearLayoutManager(this));
        getGaleri();
        initToolbar();
    }

    private void getGaleri(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        String URL_IMAGE = "http://192.168.1.9/pedede/api/galeri";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_IMAGE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.d("Galeri", String.valueOf(response));
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    String res = jsonArray.toString();
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    Galeri[] galeris = gson.fromJson(res, Galeri[].class);
                    galeriAdapter = new GaleriAdapter(GaleriActivity.this, galeris);
                    galeriList.setAdapter(galeriAdapter);
                    galeriAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showCustomDialog();
//                error.printStackTrace();
            }
        });

        requestQueue.add(request);

    }

    private void showCustomDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_warning);
        dialog.setCancelable(true);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = -2;
        layoutParams.height = -2;

        ((Button) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Context applicationContext = IdentificatoinActivity.this.getApplicationContext();
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append(((Button) v).getText().toString());
//                stringBuilder.append(" Clicked");
//                Toast.makeText(applicationContext, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);
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
        toolbar.setTitle("Galeri");
        Tools.setSystemBarColor(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(GaleriActivity.this);
    }
}
