package com.example.pokemontcg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.pokemontcg.adapter.EdicionAdapter;
import com.example.pokemontcg.helper.SetHelper;
import com.example.pokemontcg.model.tcg.Set;
import com.example.pokemontcg.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusquedaEdicionActivity extends Activity {

    private ListView listaEdiciones;
    private ProgressBar progressBar;
    private EditText inputFiltro;

    private EdicionAdapter adapter;
    private ArrayList<Set> listaOriginal = new ArrayList<>();
    private ArrayList<Set> listaFiltrada = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_edicion);

        listaEdiciones = findViewById(R.id.listaEdiciones);
        progressBar = findViewById(R.id.progressBar3);
        inputFiltro = findViewById(R.id.inputFiltro);

        progressBar.setVisibility(View.VISIBLE);
        getSets();

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

    private void getSets() {
        SetHelper setHelper = new SetHelper(this);
        Utils utils = new Utils();

        List<Set> sets = setHelper.getSets();
        List<String> excludedSets = Arrays.asList(utils.getExludedSets());

        listaOriginal.clear();
        listaFiltrada.clear();

        for (Set set : sets) {
            if (!excludedSets.contains(set.getIdSet())) {
                listaOriginal.add(set);
            }
        }

        listaFiltrada.addAll(listaOriginal);

        adapter = new EdicionAdapter(this, listaFiltrada);
        listaEdiciones.setAdapter(adapter);

        listaEdiciones.setOnItemClickListener((parent, view, position, id) -> {
            Set set = listaFiltrada.get(position);
            Intent intent = new Intent(BusquedaEdicionActivity.this, ListaCartasActivity.class);
            intent.putExtra("valor", set.getId().toString());
            intent.putExtra("tipoBusqueda", "id");
            startActivity(intent);
        });

        progressBar.setVisibility(View.GONE);
    }

    private void filtrar(String texto) {
        texto = texto.toLowerCase();
        ArrayList<Set> resultado = new ArrayList<>();

        for (Set set : listaOriginal) {
            if (set.getName().toLowerCase().contains(texto)) {
                resultado.add(set);
            }
        }

        adapter.updateData(resultado);
    }
}
