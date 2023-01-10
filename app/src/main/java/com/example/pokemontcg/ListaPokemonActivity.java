package com.example.pokemontcg;

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
import com.example.pokemontcg.adapter.PokemonAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

import java.util.ArrayList;

public class ListaPokemonActivity extends Activity {
    private ListView listaPokemon;
    private PokemonAdapter pokemonAdapter;
    private ProgressBar progressBar;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_pokemon);

        String valor = getIntent().getStringExtra("valor").toString();
        String tipoBusqueda = getIntent().getStringExtra("tipoBusqueda").toString();

        listaPokemon = findViewById(R.id.listaEdiciones);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getDataPokemon(valor, tipoBusqueda);
    }

    private void getDataPokemon(String valor, String tipoBusqueda) {
        String url = "https://api.pokemontcg.io/v2/cards?q=" + tipoBusqueda +":" + valor + "&orderBy=number";

        if(valor.equalsIgnoreCase("Mime Jr.")){
            url = "https://api.pokemontcg.io/v2/cards?q=" + tipoBusqueda +":mime&q=nationalPokedexNumbers:439&orderBy=number";
        }

        if(valor.equalsIgnoreCase("Mr. Mime")){
            url = "https://api.pokemontcg.io/v2/cards?q=" + tipoBusqueda +":mime&q=nationalPokedexNumbers:122&orderBy=number";
        }

        if(valor.equalsIgnoreCase("Mr. Rime")){
            url = "https://api.pokemontcg.io/v2/cards?q=" + tipoBusqueda +":rime&orderBy=number";
        }

        if(valor.equalsIgnoreCase("Nidoran♀")){
            url = "https://api.pokemontcg.io/v2/cards?q=" + tipoBusqueda +":nidoran&q=nationalPokedexNumbers:29&orderBy=number";
        }

        if(valor.equalsIgnoreCase("Nidoran♂")){
            url = "https://api.pokemontcg.io/v2/cards?q=" + tipoBusqueda +":nidoran&q=nationalPokedexNumbers:32&orderBy=number";
        }

        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));

                    ArrayList<String> arrayPokemon = new ArrayList<>();

                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++){
                            arrayPokemon.add(jsonArray.getString(i));
                        }
                    }

                    if(jsonArray.length() > 0){
                        pokemonAdapter = new PokemonAdapter(ListaPokemonActivity.this, arrayPokemon);
                        listaPokemon.setAdapter(pokemonAdapter);

                        listaPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                try {
                                    Intent intent = new Intent(ListaPokemonActivity.this, CartaActivity.class);
                                    intent.putExtra("id", jsonArray.getJSONObject(position).getString("id"));
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }else{
                        Intent intent = new Intent(ListaPokemonActivity.this, MainActivity.class);
                        intent.putExtra("sinResultados", "No se encontraron resultados para este Pokémon");
                        startActivity(intent);
                    }

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
