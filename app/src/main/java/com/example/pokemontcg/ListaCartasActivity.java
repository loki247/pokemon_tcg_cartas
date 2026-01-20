package com.example.pokemontcg;

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
import com.example.pokemontcg.adapter.CardAdapter;
import com.example.pokemontcg.adapter.EdicionAdapter;
import com.example.pokemontcg.helper.CardHelper;
import com.example.pokemontcg.helper.SetHelper;
import com.example.pokemontcg.model.tcg.Card;
import com.example.pokemontcg.model.tcg.Set;
import com.example.pokemontcg.model.tcg.SetContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

import java.util.ArrayList;
import java.util.List;

public class ListaCartasActivity extends Activity {
    private ListView listaCartas;
    private CardAdapter cardAdapter;
    private ProgressBar progressBar;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private StringRequest mStringRequest2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_cartas);

        Integer valor = getIntent().getIntExtra("valor", 0);
        String tipoBusqueda = getIntent().getStringExtra("tipoBusqueda");

        listaCartas = findViewById(R.id.listaCartas);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getCards(valor, tipoBusqueda);
    }

    private void getCards(Integer valor, String tipoBusqueda) {
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

        CardHelper cardHelper = new CardHelper(this);

        List<Card> cards = cardHelper.getCards(valor);
        ArrayList<Card> listaFinal = new ArrayList<>();

        for (Card card : cards) {
            listaFinal.add(card);
        }

        CardAdapter adapter = new CardAdapter(this, listaFinal);
        listaCartas.setAdapter(adapter);

        listaCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaCartasActivity.this, listaFinal.get(position).getCategory().equals("Pokemon") ? PokemonActivity.class : ItemActivity.class);
                intent.putExtra("id", listaFinal.get(position).getId());
                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.GONE);
    }
}
