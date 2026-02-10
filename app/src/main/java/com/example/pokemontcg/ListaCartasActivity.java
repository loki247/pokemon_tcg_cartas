package com.example.pokemontcg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.pokemontcg.adapter.CardAdapter;
import com.example.pokemontcg.helper.CardHelper;
import com.example.pokemontcg.model.tcg.Card;

import java.util.ArrayList;
import java.util.List;

public class ListaCartasActivity extends Activity {

    private ListView listaCartas;
    private ProgressBar progressBar;
    private EditText inputFiltro;

    private CardAdapter adapter;
    private ArrayList<Card> listaOriginal = new ArrayList<>();
    private ArrayList<Card> listaFiltrada = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_cartas);

        String valor = getIntent().getStringExtra("valor");
        String tipoBusqueda = getIntent().getStringExtra("tipoBusqueda");

        listaCartas = findViewById(R.id.listaCartas);
        progressBar = findViewById(R.id.progressBar);
        inputFiltro = findViewById(R.id.inputFiltro);

        progressBar.setVisibility(View.VISIBLE);
        getCards(valor, tipoBusqueda);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // 🔍 Filtro en tiempo real
        inputFiltro.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrar(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void getCards(String valor, String tipoBusqueda) {
        CardHelper cardHelper = new CardHelper(this);
        List<Card> cards = tipoBusqueda.equalsIgnoreCase("id")
                ? cardHelper.getCards(valor)
                : cardHelper.getCardsSearch(valor);

        listaOriginal.clear();
        listaOriginal.addAll(cards);

        listaFiltrada.clear();
        listaFiltrada.addAll(cards);

        adapter = new CardAdapter(this, listaFiltrada);
        listaCartas.setAdapter(adapter);

        listaCartas.setOnItemClickListener((parent, view, position, id) -> {
            Card card = listaFiltrada.get(position);
            Intent intent = new Intent(
                    ListaCartasActivity.this,
                    card.getCategory().equals("Pokemon") ? PokemonActivity.class : ItemActivity.class
            );

            intent.putExtra("id", card.getId().toString());
            startActivity(intent);
        });

        progressBar.setVisibility(View.GONE);
    }

    private void filtrar(String texto) {
        texto = texto.toLowerCase();
        ArrayList<Card> resultado = new ArrayList<>();

        for (Card card : listaOriginal) {
            if (card.getName().toLowerCase().contains(texto)) {
                resultado.add(card);
            }
        }

        adapter.updateData(resultado);
    }
}
