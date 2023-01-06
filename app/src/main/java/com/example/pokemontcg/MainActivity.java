package com.example.pokemontcg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nombrePokemon;
    private Button btnBuscar;

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

        nombrePokemon = (EditText) findViewById(R.id.nombrePokemon);
    }

    public void ListaPokemonActivity(View view) {
        String nombre = nombrePokemon.getText().toString();
        if(!nombre.isEmpty()){
            Intent intent = new Intent(this, ListaPokemonActivity.class);
            intent.putExtra("nombrePokemon", nombrePokemon.getText().toString());
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this, "Debes ingresar el nombre de un Pokémon", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}