package com.example.pokemontcg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pokemontcg.adapter.TipoAdapter;
import com.example.pokemontcg.utils.Utils;

public class BusquedaTipoActivity extends Activity {
    private ListView listaTipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_tipo);

        listaTipos = findViewById(R.id.listaTipos);

        TipoAdapter tipoAdapter = new TipoAdapter(BusquedaTipoActivity.this);
        listaTipos.setAdapter(tipoAdapter);

        listaTipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BusquedaTipoActivity.this, ListaPokemonActivity.class);
                intent.putExtra("valor", new Utils().getListadoTiposIng()[position].toLowerCase());
                intent.putExtra("tipoBusqueda", "types");
                startActivity(intent);
            }
        });
    }
}
