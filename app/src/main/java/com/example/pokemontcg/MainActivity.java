package com.example.pokemontcg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.pokemontcg.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView nombrePokemon;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            String sinResultados = getIntent().getStringExtra("sinResultados");

            if (!sinResultados.isEmpty()){
                Toast toast = Toast.makeText(this, "No se encontraron resultados para este Pokémon", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        nombrePokemon = (AutoCompleteTextView) findViewById(R.id.nombrePokemon);
        String[] lista = new Utils().getListadoPokemon();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, lista);
        nombrePokemon.setThreshold(1);
        nombrePokemon.setAdapter(adapter);
    }

    public void ListaPokemonActivity(View view) {
        String nombre = nombrePokemon.getText().toString();
        if(!nombre.isEmpty()){
            Intent intent = new Intent(this, ListaCartasActivity.class);
            intent.putExtra("valor", nombrePokemon.getText().toString());
            intent.putExtra("tipoBusqueda", "name");
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this, "Debes ingresar el nombre de un Pokémon", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void BusquedaTipoActivity(View view) {
        Intent intent = new Intent(this, BusquedaTipoActivity.class);
        startActivity(intent);
    }

    public void BusquedaEdicionActivity(View view) {
        Intent intent = new Intent(this, BusquedaEdicionActivity.class);
        startActivity(intent);
    }
}