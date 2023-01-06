package com.example.pokemontcg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokemontcg.utils.Utils;

public class MainActivity extends AppCompatActivity {
    //private EditText nombrePokemon;
    private Button btnBuscar;
    private AutoCompleteTextView nombrePokemon;

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

        nombrePokemon.setThreshold(1);//will start working from first character
        nombrePokemon.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
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