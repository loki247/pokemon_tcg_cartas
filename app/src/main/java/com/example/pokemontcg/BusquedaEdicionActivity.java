package com.example.pokemontcg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_edicion);

        listaEdiciones = findViewById(R.id.listaEdiciones);
        progressBar = findViewById(R.id.progressBar3);

        progressBar.setVisibility(View.VISIBLE);
        getSets();
    }

    private void getSets() {
        SetHelper setHelper = new SetHelper(this);
        Utils utils = new Utils();

        List<Set> sets = setHelper.getSets();
        ArrayList<Set> listaFinal = new ArrayList<>();

        List<String> excludedSets = Arrays.asList(utils.getExludedSets());

        for (Set set : sets) {
            if (!excludedSets.contains(set.getIdSet())) {
                listaFinal.add(set);
            }
        }

        EdicionAdapter adapter = new EdicionAdapter(this, listaFinal);
        listaEdiciones.setAdapter(adapter);

        listaEdiciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BusquedaEdicionActivity.this, ListaCartasActivity.class);
                intent.putExtra("valor", listaFinal.get(position).getId().toString());
                intent.putExtra("tipoBusqueda", "id");
                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.GONE);
    }
}
