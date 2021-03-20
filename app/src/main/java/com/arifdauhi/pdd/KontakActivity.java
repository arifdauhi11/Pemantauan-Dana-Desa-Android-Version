package com.arifdauhi.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arifdauhi.utils.Saran;
import com.arifdauhi.utils.Tools;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KontakActivity extends AppCompatActivity {

    private EditText nik, password, saran;
    private Button btnKirim;
    public ActionBar actionBar;
    private Toolbar toolbar;
    private static final String URL_KIRIM = "https://arifdauhi.tk/rest/api/saran";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);
        initToolbar();
        init();
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n, p, s;
                n = nik.getText().toString().trim();
                p = password.getText().toString().trim();
                s = saran.getText().toString().trim();
                if (n.equals("")){
                    nik.setError("NIK tidak boleh kosong.");
                } else if (p.equals("")){
                    password.setError("Password tidak boleh kosong.");
                } else if (s.equals("")){
                    saran.setError("Saran tidak boleh kosong");
                } else {
                    postSaran(n, p, s);
                }
            }
        });
    }

    private void postSaran(final String n, final String p, final String s) {
        StringRequest request = new StringRequest(Request.Method.POST, URL_KIRIM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String status, msg;
                            status = object.getString("status");
                            msg = object.getString("message");
                            if (status.equals("true")){
                                Toast.makeText(KontakActivity.this, msg, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(KontakActivity.this, DashboardActivity.class));
                                Animatoo.animateSlideRight(KontakActivity.this);
                            } else {
                                Toast.makeText(KontakActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KontakActivity.this, "Error getting data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KontakActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nik", n);
                params.put("password", p);
                params.put("saran", s);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void init() {
        nik = findViewById(R.id.nikSaran);
        password = findViewById(R.id.passwordSaran);
        saran = findViewById(R.id.etSaran);
        btnKirim = findViewById(R.id.btnKirimSaran);
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
        toolbar.setTitle("Kontak");
        Tools.setSystemBarColor(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(KontakActivity.this);
    }
}
