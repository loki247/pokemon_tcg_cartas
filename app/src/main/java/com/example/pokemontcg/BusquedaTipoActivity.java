package com.example.pokemontcg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pokemontcg.adapter.PokemonAdapter;
import com.example.pokemontcg.adapter.TipoAdapter;

public class BusquedaTipoActivity extends Activity {
    private ListView listaTipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_tipo);

        listaTipos = findViewById(R.id.listaTipos);

        TipoAdapter tipoAdapter = new TipoAdapter(BusquedaTipoActivity.this);
        listaTipos.setAdapter(tipoAdapter);
    }
}
