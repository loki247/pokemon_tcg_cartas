package com.example.pokemontcg;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokemontcg.model.tcg.Card;
import com.example.pokemontcg.model.tcg.CardCount;
import com.example.pokemontcg.model.tcg.Set;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartaActivity extends Activity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String urlApi = "https://api.tcgdex.net/v2/en/cards/";

    private TextView tituloNombre;
    private ImageView imgCarta;
    private TextView ilustrador;
    private TextView hpCarta;
    private TextView faseCarta;
    private TextView tipoCarta;
    private TextView preEvolucionCarta;
    private TextView evolucionCarta;
    private ImageView edicionCarta;
    private TextView numeroCarta;
    private TextView rarezaCarta;
    private TextView precioCarta;
    private TextView fuentePrecioCarta;
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
                    Card card = new Card();
                    card.setName(jsonObject.getString("name"));
                    card.setImage(jsonObject.getString("image") + "/high.webp");
                    card.setIllustrator(jsonObject.getString("illustrator"));
                    card.setHp(Integer.parseInt(jsonObject.getString("hp")));
                    card.setStage(jsonObject.has("stage") ? jsonObject.getString("stage") : null);

                    List<String> tipos = new ArrayList<>();
                    JSONArray jsonArrayTypes = new JSONArray(jsonObject.getString("types"));

                    for(int i = 0; i < jsonArrayTypes.length(); i++){
                        tipos.add(jsonArrayTypes.get(i).toString());
                    }

                    card.setTypes(tipos);
                    card.setEvolveFrom(jsonObject.has("evolveFrom") ? jsonObject.getString("evolveFrom") : null);
                    card.setRarity(jsonObject.getString("rarity"));
                    card.setLocalId(jsonObject.getString("localId"));

                    Set set = new Set();
                    CardCount cardCount = new CardCount();
                    cardCount.setOfficial(Integer.parseInt(jsonObject.getJSONObject("set").getJSONObject("cardCount").getString("official")));
                    cardCount.setTotal(Integer.parseInt(jsonObject.getJSONObject("set").getJSONObject("cardCount").getString("total")));

                    set.setCardCount(cardCount);

                    card.setSet(set);

                    tituloNombre = findViewById(R.id.tituloNombre);
                    tituloNombre.setText(card.getName());

                    imgCarta = findViewById(R.id.imgCarta);
                    Picasso.get().load(card.getImage()).into(imgCarta);

                    ilustrador = findViewById(R.id.ilustrador);
                    ilustrador.setText(card.getIllustrator());

                    hpCarta = findViewById(R.id.hpCarta);
                    hpCarta.setText(card.getHp().toString());

                    faseCarta = findViewById(R.id.faseCarta);
                    faseCarta.setText(card.getStage());

                    tipoCarta = findViewById(R.id.tipoCarta);
                    tipoCarta.setText(card.getTypes().get(0));

                    preEvolucionCarta = findViewById(R.id.preevolucionCarta);
                    preEvolucionCarta.setText(card.getEvolveFrom());

                    rarezaCarta = findViewById(R.id.rarezaCarta);
                    rarezaCarta.setText(card.getRarity());

                    numeroCarta = findViewById(R.id.numeroCarta);
                    numeroCarta.setText(card.getLocalId() + "/" + card.getSet().getCardCount().getOfficial().toString());

                    precioCarta = findViewById(R.id.precioCarta);
                    fuentePrecioCarta = findViewById(R.id.fuentePrecioCarta);


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
