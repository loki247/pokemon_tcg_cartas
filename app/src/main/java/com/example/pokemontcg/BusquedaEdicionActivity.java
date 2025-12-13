package com.example.pokemontcg;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokemontcg.adapter.EdicionAdapter;
import com.example.pokemontcg.adapter.PokemonAdapter;
import com.example.pokemontcg.adapter.TipoAdapter;
import com.example.pokemontcg.model.Edicion;
import com.example.pokemontcg.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BusquedaEdicionActivity extends Activity {
    private ListView listaEdiciones;
    private ProgressBar progressBar;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_edicion);

        listaEdiciones = findViewById(R.id.listaEdiciones);
        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);
        getDataEdicion();
    }

    private void getDataEdicion() {
        String url = "https://api.tcgdex.net/v2/en/sets";

        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> arrayEdiciones = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                arrayEdiciones.add(jsonArray.getString(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    EdicionAdapter edicionAdapter = new EdicionAdapter(BusquedaEdicionActivity.this, arrayEdiciones);
                    listaEdiciones.setAdapter(edicionAdapter);

                    listaEdiciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                Intent intent = new Intent(BusquedaEdicionActivity.this, ListaPokemonActivity.class);
                                intent.putExtra("valor", jsonArray.getJSONObject(position).getString("id"));
                                intent.putExtra("tipoBusqueda", "set.id");
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        //int socketTimeout = 30000;//30 seconds - change to what you want
        //RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        //mJsonRequest.setRetryPolicy(policy);

        mRequestQueue.add(mStringRequest);
    }
}
