package com.example.pokemontcg;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokemontcg.adapter.PokemonAdapter;
import com.example.pokemontcg.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //private EditText nombrePokemon;
    private Button btnBuscar;
    private AutoCompleteTextView nombrePokemon;

    private RequestQueue mRequestQueue;
    private JsonRequest mJsonRequest;

    private TextView cantidades;

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

        nombrePokemon.setThreshold(1);//will start working from first character
        nombrePokemon.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

        ArrayList<String> listaSubtipos = new ArrayList<>();
        listaSubtipos.add("vmax");
        listaSubtipos.add("mega");

        getDataCartasTipo(listaSubtipos);
    }

    public void ListaPokemonActivity(View view) {
        String nombre = nombrePokemon.getText().toString();
        if(!nombre.isEmpty()){
            Intent intent = new Intent(this, ListaPokemonActivity.class);
            intent.putExtra("valor", nombrePokemon.getText().toString());
            intent.putExtra("tipoBusqueda", "name");
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this, "Debes ingresar el nombre de un Pokémon", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void getDataCartasTipo(ArrayList<String> subTipos){
        for(String tipo : subTipos){
            String url = "https://api.pokemontcg.io/v2/cards?q=subtypes:" + tipo;

            Log.i(TAG, url);

            mRequestQueue = Volley.newRequestQueue(this);
            mJsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                public void onResponse(JSONObject response) {
                    try {
                        if(tipo.equalsIgnoreCase("vmax")){
                            cantidades = findViewById(R.id.cantVmax);
                            cantidades.setText(response.getString("totalCount"));
                        }else if(tipo.equalsIgnoreCase("mega")){
                            cantidades = findViewById(R.id.cantMega);
                            cantidades.setText(response.getString("totalCount"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, "Error :" + error.toString());
                }
            });

            int socketTimeout = 30000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            mJsonRequest.setRetryPolicy(policy);

            mRequestQueue.add(mJsonRequest);
        }
    }

    public void BusquedaTipoActivity(View view) {
        Intent intent = new Intent(this, BusquedaTipoActivity.class);
        startActivity(intent);
    }
}