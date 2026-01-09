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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokemontcg.adapter.EdicionAdapter;
import com.example.pokemontcg.model.tcg.CardCount;
import com.example.pokemontcg.model.tcg.Set;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

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
                    ArrayList<Set> sets = new ArrayList<>();

                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                Set set = new Set();
                                set.setId(jsonArray.getJSONObject(i).getString("id"));
                                set.setName(jsonArray.getJSONObject(i).getString("name"));
                                set.setLogo(jsonArray.getJSONObject(i).getString("logo"));

                                CardCount cardCount = new CardCount();
                                cardCount.setTotal(jsonArray.getJSONObject(i).getJSONObject("cardCount").getInt("total"));
                                cardCount.setOfficial(jsonArray.getJSONObject(i).getJSONObject("cardCount").getInt("official"));

                                set.setCardCount(cardCount);
                                sets.add(set);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    EdicionAdapter edicionAdapter = new EdicionAdapter(BusquedaEdicionActivity.this, sets);
                    listaEdiciones.setAdapter(edicionAdapter);

                    listaEdiciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                Intent intent = new Intent(BusquedaEdicionActivity.this, ListaPokemonActivity.class);
                                intent.putExtra("valor", sets.get(position).getId());
                                intent.putExtra("tipoBusqueda", "id");
                                startActivity(intent);
                            } catch (Exception e) {
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

        mRequestQueue.add(mStringRequest);
    }
}
