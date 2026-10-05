package com.example.pokemontcg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pokemontcg.helper.CardSQLHelper;
import com.example.pokemontcg.helper.CollectionSQLHelper;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView nombrePokemon;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CardSQLHelper cardSQLHelper = new CardSQLHelper(this);
        cardSQLHelper.getReadableDatabase();

        CollectionSQLHelper collectionSQLHelper = new CollectionSQLHelper(this);
        collectionSQLHelper.getReadableDatabase();

        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            String sinResultados = getIntent().getStringExtra("sinResultados");

            if (!sinResultados.isEmpty()){
                Toast toast = Toast.makeText(this, "No se encontraron resultados para este Pokémon", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        nombrePokemon = (AutoCompleteTextView) findViewById(R.id.nombrePokemon);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        ImageButton btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(v ->
                drawerLayout.openDrawer(GravityCompat.START)
        );

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_sets) {
                startActivity(new Intent(this, BusquedaEdicionActivity.class));
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
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