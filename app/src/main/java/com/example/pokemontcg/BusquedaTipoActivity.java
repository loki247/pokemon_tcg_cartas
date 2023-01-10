package com.example.pokemontcg;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokemontcg.adapter.PokemonAdapter;
import com.example.pokemontcg.adapter.TipoAdapter;
import com.example.pokemontcg.model.Tipo;
import com.example.pokemontcg.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

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
