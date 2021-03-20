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


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.arifdauhi.utils.Pendapatan;
import com.arifdauhi.utils.PendapatanAdapter;
import com.arifdauhi.utils.Saran;
import com.arifdauhi.utils.SaranAdapter;
import com.arifdauhi.utils.Tools;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    public ActionBar actionBar;
    private Toolbar toolbar;
    public RecyclerView bidangList, pendapatanList, saranList;
    public BidangAdapter bidangAdapter;
    public PendapatanAdapter pendapatanAdapter;
    public SaranAdapter saranAdapter;
    public Double semula;
    public DecimalFormat kursIndo;
    public DecimalFormatSymbols formatRp;


    private static final String URL_BIDANG = "https://arifdauhi.tk/api/bidang";
    private static final String URL_PENDAPATAN = "https://arifdauhi.tk/api/pendapatan";
    private static final String URL_SARAN = "https://arifdauhi.tk/rest/api/saran";

    public TextView txtJumlahPendapatan;
    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bidangList = findViewById(R.id.listBidang);
        pendapatanList = findViewById(R.id.listPendapatan);
        saranList = findViewById(R.id.listSaran);
        txtJumlahPendapatan = findViewById(R.id.txtJumlahPendapatan);
        imageSlider = findViewById(R.id.image_slider);
        bidangList.setLayoutManager(new LinearLayoutManager(this));
        pendapatanList.setLayoutManager(new LinearLayoutManager(this));
        saranList.setLayoutManager(new LinearLayoutManager(this));
        initToolbar();
        initNavigationMenu();
        getGaleri();
        getBidang();
        getPendapatan();
        getSaran();
        final Handler updateData = new Handler();
        updateData.postDelayed(new Runnable() {
            @Override
            public void run() {
                getBidang();
                getPendapatan();
                updateData.postDelayed(this,5000);
            }
        },5000);
    }

    private void getSaran() {
        StringRequest request = new StringRequest(Request.Method.GET, URL_SARAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        Saran[] sarans = gson.fromJson(response, Saran[].class);
                        saranAdapter = new SaranAdapter(DashboardActivity.this, sarans);
                        saranList.setAdapter(saranAdapter);
                        saranAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DashboardActivity.this, "Terjadi kesalahan!", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void getBidang() {
        StringRequest request = new StringRequest(URL_BIDANG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Bidang",response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Bidang[] bidangs = gson.fromJson(response, Bidang[].class);
                bidangAdapter = new BidangAdapter(DashboardActivity.this, bidangs);
                bidangList.setAdapter(bidangAdapter);
                bidangAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Eror", String.valueOf(error));
                Toast.makeText(DashboardActivity.this, "Terjadi kesalahan!", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void getPendapatan(){
        StringRequest request = new StringRequest(URL_PENDAPATAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Pendapatan",response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Pendapatan[] pendapatans = gson.fromJson(response, Pendapatan[].class);
                int jumlah = 0;
                for (int i = 0; i < pendapatans.length; i++) {
                    Double nilai = Double.parseDouble(pendapatans[i].getAnggaran());
                    jumlah += nilai;
                }
                semula = Double.parseDouble(String.valueOf(jumlah));
                kursIndo = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                formatRp = new DecimalFormatSymbols();

                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setGroupingSeparator('.');
                formatRp.setMonetaryDecimalSeparator('.');
                kursIndo.setDecimalFormatSymbols(formatRp);
                txtJumlahPendapatan.setText(String.valueOf(kursIndo.format(semula)));

                pendapatanAdapter = new PendapatanAdapter(DashboardActivity.this, pendapatans);
                pendapatanList.setAdapter(pendapatanAdapter);
                pendapatanAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Eror", String.valueOf(error));
                Toast.makeText(DashboardActivity.this, "Terjadi kesalahan!", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void getGaleri(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL_IMAGE = "https://arifdauhi.tk/api/galeri";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_IMAGE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.d("Galeri", String.valueOf(response));
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    List<SlideModel> slideModels = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        slideModels.add(new SlideModel("https://arifdauhi.tk/assets/images/multiple/" + data.getString("nama_gambar"), data.getString("nama_bidang")));
                    }
                    imageSlider.setImageList(slideModels, false);
                    imageSlider.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onItemSelected(int i) {
                            startActivity(new Intent(DashboardActivity.this, GaleriActivity.class));
                            Animatoo.animateSlideLeft(DashboardActivity.this);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Beranda");
        Tools.setSystemBarColor(this);
    }

    private void initNavigationMenu(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout,this.toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }
                };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Context context = DashboardActivity.this.getApplicationContext();
                switch (menuItem.getItemId()){
//                    case R.id.nav_beranda:
//                        Intent beranda = new Intent(context,DashboardActivity.class);
//                        startActivity(beranda);
//                        return true;
                    case R.id.nav_bidang:
                        Intent bidang = new Intent(context,BidangActivity.class);
                        startActivity(bidang);
                        Animatoo.animateSlideLeft(DashboardActivity.this);
                        return true;

                    case R.id.nav_kontak:
                        Intent kontak = new Intent(context,KontakActivity.class);
                        startActivity(kontak);
                        Animatoo.animateSlideLeft(DashboardActivity.this);
                        return true;

                    case R.id.nav_tentang:
                        Intent tentang = new Intent(context,TentangActivity.class);
                        startActivity(tentang);
                        Animatoo.animateSlideLeft(DashboardActivity.this);
                        return true;
                    default:return DashboardActivity.super.onOptionsItemSelected(menuItem);
                }
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append(menuItem.getTitle());
//                stringBuilder.append(" Selected");
//                Toast.makeText(context, stringBuilder.toString(), Toast.LENGTH_LONG).show();
//                DashboardActivity.this.actionBar.setTitle(menuItem.getTitle());
//                drawerLayout.closeDrawers();
//                return false;
            }
        });
        drawerLayout.openDrawer((int) GravityCompat.START);
    }


}