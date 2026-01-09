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
import com.example.pokemontcg.adapter.CardAdapter;
import com.example.pokemontcg.model.tcg.Card;
import com.example.pokemontcg.model.tcg.SetContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

import java.util.ArrayList;

public class ListaPokemonActivity extends Activity {
    private ListView listaPokemon;
    private CardAdapter cardAdapter;
    private ProgressBar progressBar;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_pokemon);

        String valor = getIntent().getStringExtra("valor");
        String tipoBusqueda = getIntent().getStringExtra("tipoBusqueda");

        listaPokemon = findViewById(R.id.listaEdiciones);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getDataPokemon(valor, tipoBusqueda);
    }

    private void getDataPokemon(String valor, String tipoBusqueda) {
        String url = tipoBusqueda.equalsIgnoreCase("id") ?  "https://api.tcgdex.net/v2/en/sets/" + valor : "https://api.tcgdex.net/v2/en/cards?name=" + valor;

        /*if(valor.equalsIgnoreCase("Mime Jr.")){
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
        }*/

        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = tipoBusqueda.equalsIgnoreCase("id") ? new JSONObject(response) : null;
                    JSONArray jsonArray = tipoBusqueda.equalsIgnoreCase("name") ? new JSONArray(response) : null;
                    SetContent setContent = new SetContent();

                    if (jsonObject != null || jsonArray != null) {
                        JSONArray cardsJsonArray = tipoBusqueda.equalsIgnoreCase("id") ? jsonObject.getJSONArray("cards") : jsonArray;
                        ArrayList<Card> cards = new ArrayList<>();

                        for (int i = 0; i < cardsJsonArray.length(); i++){
                            Card card = new Card();
                            card.setId(cardsJsonArray.getJSONObject(i).getString("id"));
                            card.setImage(cardsJsonArray.getJSONObject(i).getString("image"));
                            card.setLocalId(cardsJsonArray.getJSONObject(i).getString("localId"));
                            card.setName(cardsJsonArray.getJSONObject(i).getString("name"));

                            cards.add(card);
                        }

                        setContent.setCards(cards);
                    }

                    if (setContent != null) {
                        cardAdapter = new CardAdapter(ListaPokemonActivity.this, setContent.getCards());
                        listaPokemon.setAdapter(cardAdapter);

                        listaPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                try {
                                    Intent intent = new Intent(ListaPokemonActivity.this, CartaActivity.class);
                                    intent.putExtra("id", setContent.getCards().get(position).getId());
                                    startActivity(intent);
                                } catch (Exception e) {
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
