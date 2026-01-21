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
    private ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_cartas);

        String valor = getIntent().getStringExtra("valor");
        String tipoBusqueda = getIntent().getStringExtra("tipoBusqueda");

        listaCartas = findViewById(R.id.listaCartas);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getCards(valor, tipoBusqueda);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void getCards(String valor, String tipoBusqueda) {
        CardHelper cardHelper = new CardHelper(this);
        System.out.println(tipoBusqueda);
        List<Card> cards = tipoBusqueda.equalsIgnoreCase("id") ?  cardHelper.getCards(valor) : cardHelper.getCardsSearch(valor);
        ArrayList<Card> listaFinal = new ArrayList<>();

        for (Card card : cards) {
            listaFinal.add(card);
        }

        CardAdapter adapter = new CardAdapter(this, listaFinal);
        listaCartas.setAdapter(adapter);

        listaCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(listaFinal.get(position).getId());
                Intent intent = new Intent(ListaCartasActivity.this, listaFinal.get(position).getCategory().equals("Pokemon") ? PokemonActivity.class : ItemActivity.class);
                intent.putExtra("id", listaFinal.get(position).getId().toString());
                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.GONE);
    }
}
