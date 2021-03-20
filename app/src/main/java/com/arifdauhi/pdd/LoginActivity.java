package com.arifdauhi.pdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arifdauhi.utils.Tools;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText nik, password;
    private Button btnLogin;
    private ProgressBar spinLogin;
    private static final String URL_LOGIN = "https://arifdauhi.tk/api/user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(this, "Silahkan login untuk melihat data lainnya", Toast.LENGTH_LONG).show();
        initToolbar();
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n, p;
                n = nik.getText().toString().trim();
                p = password.getText().toString().trim();
                if (n.isEmpty()){
                    nik.setError("Form NIK tidak boleh kosong");
                } else if (p.isEmpty()){
                    password.setError("Form Password tidak boleh kosong");
                } else {
                    getPengguna(n,p);
                }
            }
        });
    }

    private void getPengguna(final String nik, final String password) {
       spinLogin.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String status, msg;
                    status = object.getString("status");
                    msg = object.getString("message");
                    if (status.equals("true")){
                        JSONArray array = object.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++){
                            JSONObject dataObject = array.getJSONObject(i);
                            Log.d("Login", String.valueOf(dataObject));
                            String nama, nik, role;
                            nama = dataObject.getString("nama");
                            nik = dataObject.getString("nik");
                            role = dataObject.getString("role");
                            if (role == "Warga"){
                                Toast.makeText(LoginActivity.this, "Silahkan lihat data lainnya di website kami", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(LoginActivity.this, ProgramActivity.class));
                            }
                        }
                        spinLogin.setVisibility(View.GONE);
                    }
                    if (status.equals("false")){
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                        spinLogin.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    spinLogin.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                spinLogin.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nik", nik);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void init() {
        nik = findViewById(R.id.nikLogin);
        password = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnMasuk);
        spinLogin = findViewById(R.id.spinLogin);
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
        toolbar.setTitle("Login");
        Tools.setSystemBarColor(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(LoginActivity.this);
    }


}