package com.arifdauhi.pdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import com.arifdauhi.utils.Program;
import com.arifdauhi.utils.ProgramAdapter;
import com.arifdauhi.utils.RealisasiProgram;
import com.arifdauhi.utils.RealisasiProgramAdapter;
import com.arifdauhi.utils.RealisasiSubprogram;
import com.arifdauhi.utils.RealisasiSubprogramAdapter;
import com.arifdauhi.utils.RealisasiSubprogramTahun;
import com.arifdauhi.utils.RealisasiSubprogramTahunAdapter;
import com.arifdauhi.utils.Tools;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;

public class RealisasiActivity extends AppCompatActivity {

    private TextView tvJumlahSub, tvPersentaseSub, jumlahProgram, tvJProg;
    public RecyclerView listRealisasiProgram, lsRealisasiSubprogram, lsRealisasiSubprogramPertahun;
    public RealisasiProgramAdapter realisasiProgramAdapter;
    public RealisasiSubprogramAdapter realisasiSubprogramAdapter;
    public RealisasiSubprogramTahunAdapter realisasiSubprogramTahunAdapter;
    private Toolbar toolbar;
    public Double jumlahAnggaranSub;
    public DecimalFormat kursIndo;
    public DecimalFormatSymbols formatRp;

    private static final String URL_PROGRAM = "https://arifdauhi.tk/api/rincian";
    private static final String URL_SUBPROGRAM = "https://arifdauhi.tk/api/rt";
//    private static final String URL_PROGRAM = "http://192.168.10.1/pedede/api/rincian";
//    private static final String URL_SUBPROGRAM = "http://192.168.10.1/pedede/api/rincians";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realisasi);
        tvJumlahSub = findViewById(R.id.jumlahSub);
        tvPersentaseSub = findViewById(R.id.persentaseSub);
        jumlahProgram = findViewById(R.id.jumlahProgram);
        tvJProg = findViewById(R.id.jProg);
        listRealisasiProgram = findViewById(R.id.lsRealisasiProgram);
        lsRealisasiSubprogramPertahun = findViewById(R.id.lsRealisasiSubProgramPertahun);
        listRealisasiProgram.setLayoutManager(new LinearLayoutManager(this));
        lsRealisasiSubprogramPertahun.setLayoutManager(new LinearLayoutManager(this));
        initToolbar();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String tahun = "2019";
        getRealisasiProgram(id, tahun);
        getRealisasiSubprogramTahun(id, tahun);
    }

    private void getRealisasiSubprogramTahun(final String id, final String tahun) {
        StringRequest request = new StringRequest(Request.Method.POST, URL_SUBPROGRAM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RealisasiSub",response);
                        try {
                            JSONObject object = new JSONObject(response);
                            String status, semula, menjadi, subprogram, msg;
                            status = object.getString("status");
                            msg = object.getString("message");
                            if (status.equals("true")){
                                subprogram = object.getString("subprogram");
                                Log.d("Realiasasi", subprogram);
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                RealisasiSubprogramTahun[] realisasiSubprogramTahuns = gson.fromJson(subprogram, RealisasiSubprogramTahun[].class);
                                realisasiSubprogramTahunAdapter = new RealisasiSubprogramTahunAdapter(RealisasiActivity.this,realisasiSubprogramTahuns);
                                lsRealisasiSubprogramPertahun.setAdapter(realisasiSubprogramTahunAdapter);
                            }else{
                                Toast.makeText(RealisasiActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            Toast.makeText(RealisasiActivity.this, "Data untuk program yang anda pilih belum tersedia", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RealisasiActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("tahun", tahun);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void getRealisasiProgram(final String id, final String tahun) {
        StringRequest request = new StringRequest(Request.Method.POST, URL_PROGRAM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String status, msg, program;
                            status = object.getString("status");
                            msg = object.getString("message");
                            program = object.getString("program");
                            if (status.equals("true")){
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                RealisasiProgram[] realisasiPrograms = gson.fromJson(program, RealisasiProgram[].class);
                                realisasiProgramAdapter = new RealisasiProgramAdapter(RealisasiActivity.this, realisasiPrograms);
                                listRealisasiProgram.setAdapter(realisasiProgramAdapter);
                                realisasiProgramAdapter.notifyDataSetChanged();
                                for (int i = 0; i < realisasiPrograms.length; i++) {
                                    String m = realisasiPrograms[i].getMenjadi();
                                    if (m.equals("0")){
                                        jumlahProgram.setText(String.valueOf(realisasiPrograms[i].getSemula()));
                                        String js = String.valueOf(realisasiPrograms[i].getSemula());
                                        SharedPreferences preferences;
                                        SharedPreferences.Editor editor;
                                        preferences = getSharedPreferences("program", MODE_PRIVATE);
                                        editor = preferences.edit();
                                        editor.putString("jumlahProgram", js);
                                        editor.apply();
                                    } else {
                                        jumlahProgram.setText(String.valueOf(realisasiPrograms[i].getMenjadi()));
                                        String jm = String.valueOf(realisasiPrograms[i].getMenjadi());
                                        SharedPreferences preferences;
                                        SharedPreferences.Editor editor;
                                        preferences = getSharedPreferences("program", MODE_PRIVATE);
                                        editor = preferences.edit();
                                        editor.putString("jumlahProgram", jm);
                                        editor.apply();
                                    }
                                }
                            } else {
                                Toast.makeText(RealisasiActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RealisasiActivity.this, "Data yang anda pilih belum tersedia", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RealisasiActivity.this, "Terjadi kesalahan!", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("tahun", tahun);
                return params;
            }
        };

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
        toolbar.setTitle("Realisasi");
        Tools.setSystemBarColor(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(RealisasiActivity.this);
    }

}