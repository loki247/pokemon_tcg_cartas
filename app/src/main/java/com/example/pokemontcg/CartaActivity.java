package com.example.pokemontcg;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokemontcg.adapter.PokemonAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartaActivity extends Activity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String urlApi = "https://api.pokemontcg.io/v2/cards/";


    private TextView tituloNombre;
    private ImageView imgCarta;
    private TextView claseCarta;
    private TextView hpCarta;
    private TextView faseCarta;
    private TextView tipoCarta;
    private TextView preEvolucionCarta;
    private ListView evolucionCarta;
    private TextView numeroCarta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carta);
        getDataPokemon(getIntent().getStringExtra("id"));
    }

    private void getDataPokemon(String idCarta) {
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, urlApi + idCarta, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject dataCarta = new JSONObject(jsonObject.getString("data"));

                    tituloNombre = findViewById(R.id.tituloNombre);
                    tituloNombre.setText(dataCarta.getString("name"));

                    JSONObject urlObject = new JSONObject(dataCarta.getString("images"));
                    String urlImg = urlObject.getString("large");

                    imgCarta = findViewById(R.id.imgCarta);
                    Picasso.get().load(urlImg).into(imgCarta);

                    claseCarta = findViewById(R.id.claseCarta);
                    claseCarta.setText(dataCarta.getString("supertype"));

                    hpCarta = findViewById(R.id.hpCarta);
                    hpCarta.setText(dataCarta.getString("hp"));

                    JSONArray jsonArraySubtypes = new JSONArray(dataCarta.getString("subtypes"));

                    faseCarta = findViewById(R.id.faseCarta);
                    faseCarta.setText(jsonArraySubtypes.get(0).toString());

                    JSONArray jsonArrayTypes = new JSONArray(dataCarta.getString("types"));

                    tipoCarta = findViewById(R.id.tipoCarta);
                    tipoCarta.setText(jsonArrayTypes.get(0).toString());

                    preEvolucionCarta = findViewById(R.id.preevolucionCarta);
                    preEvolucionCarta.setText(!dataCarta.isNull("evolvesFrom") ? dataCarta.getString("evolvesFrom") : "-");

                    if(!dataCarta.isNull("evolvesTo")){
                        JSONArray jsonArrayEvolucion = new JSONArray(dataCarta.getString("evolvesTo"));
                        evolucionCarta = findViewById(R.id.evolucionCarta);
                        ArrayList<String> evoluciones = new ArrayList<>();

                        if(jsonArrayEvolucion != null){
                            for (int i = 0; i < jsonArrayEvolucion.length(); i++){
                                evoluciones.add(jsonArrayEvolucion.getString(i).toString());
                            }

                            ArrayAdapter<String> evolucionesAdapter = new ArrayAdapter<String>(CartaActivity.this, android.R.layout.simple_list_item_1, evoluciones);
                            evolucionCarta.setAdapter(evolucionesAdapter);
                        }
                    }

                    JSONObject jsonObjectSet = new JSONObject(dataCarta.getString("set"));

                    numeroCarta = findViewById(R.id.numeroCarta);
                    numeroCarta.setText(dataCarta.getString("number") + "/" + jsonObjectSet.getString("printedTotal"));

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

        mRequestQueue.add(mStringRequest);
    }
}
