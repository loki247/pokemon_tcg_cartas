package com.example.pokemontcg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokemontcg.helper.SQLHelper;

public class MainActivity extends AppCompatActivity {
    private TextView nombrePokemon;
    private Button btnBuscar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLHelper dbHelper = new SQLHelper(this);
        dbHelper.getReadableDatabase();

        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            String sinResultados = getIntent().getStringExtra("sinResultados");

            if (!sinResultados.isEmpty()){
                Toast toast = Toast.makeText(this, "No se encontraron resultados para este Pokémon", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        nombrePokemon = findViewById(R.id.nombrePokemon);
        btnBuscar = findViewById(R.id.btnBuscar);
    }

    public void ListaPokemonActivity(View view) {
        String nombre = nombrePokemon.getText().toString();
        if(nombre.isEmpty()){
            Toast toast = Toast.makeText(this, "Debes ingresar el nombre de un Pokémon", Toast.LENGTH_SHORT);
            toast.show();
        }else if(nombre.length() < 3){
            Toast toast = Toast.makeText(this, "Debes ingresar al menos tres caracteres", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Intent intent = new Intent(this, ListaCartasActivity.class);
            intent.putExtra("valor", nombrePokemon.getText().toString());
            intent.putExtra("tipoBusqueda", "name");
            startActivity(intent);
        }
    }
    public void BusquedaEdicionActivity(View view) {
        Intent intent = new Intent(this, BusquedaEdicionActivity.class);
        startActivity(intent);
    }
}