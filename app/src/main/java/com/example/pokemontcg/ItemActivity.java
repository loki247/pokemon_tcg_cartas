package com.example.pokemontcg;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.TextViewCompat;

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

public class ItemActivity extends Activity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String urlApi = "https://api.tcgdex.net/v2/en/cards/";

    private TextView tituloNombre;
    private ImageView imgCarta;
    private TextView ilustrador;
    private TextView categoriaCarta;
    private ImageView edicionLogo;
    private TextView edicion;
    private TextView numeroCarta;
    private TextView rarezaCarta;
    private TextView efectoLabel;
    private TextView efectoCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
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
                    card.setCategory(jsonObject.getString("category"));
                    card.setName(jsonObject.getString("name"));
                    card.setImage(jsonObject.getString("image") + "/high.webp");
                    card.setIllustrator(jsonObject.has("illustrator") ? jsonObject.getString("illustrator") : null);
                    card.setHp(jsonObject.has("hp") ? Integer.parseInt(jsonObject.getString("hp")) : null);
                    card.setStage(jsonObject.has("stage") ? jsonObject.getString("stage") : null);
                    card.setEffect(jsonObject.has("effect") ? jsonObject.getString("effect") : null);

                    if(jsonObject.has("types")){
                        List<String> tipos = new ArrayList<>();
                        JSONArray jsonArrayTypes = new JSONArray(jsonObject.getString("types"));

                        for(int i = 0; i < jsonArrayTypes.length(); i++){
                            tipos.add(jsonArrayTypes.get(i).toString());
                        }

                        //card.setTypes(tipos);
                    }

                    card.setEvolveFrom(jsonObject.has("evolveFrom") ? jsonObject.getString("evolveFrom") : null);
                    card.setRarity(jsonObject.getString("rarity"));
                    card.setLocalId(jsonObject.getString("localId"));

                    CardCount cardCount = new CardCount();
                    cardCount.setOfficial(Integer.parseInt(jsonObject.getJSONObject("set").getJSONObject("cardCount").getString("official")));
                    cardCount.setTotal(Integer.parseInt(jsonObject.getJSONObject("set").getJSONObject("cardCount").getString("total")));

                    Set set = new Set();
                    set.setName(jsonObject.getJSONObject("set").has("name") ? jsonObject.getJSONObject("set").getString("name") :  null);
                    set.setLogo(jsonObject.getJSONObject("set").has("logo") ? jsonObject.getJSONObject("set").getString("logo") + ".png" : null);
                    set.setCardCount(cardCount);
                    card.setSet(set);

                    tituloNombre = findViewById(R.id.tituloNombre);
                    tituloNombre.setText(card.getName());

                    imgCarta = findViewById(R.id.imgCarta);
                    Picasso.get().load(card.getImage()).into(imgCarta);

                    edicionLogo = findViewById(R.id.edicionLogo);
                    Picasso.get().load(set.getLogo()).into(edicionLogo);

                    edicion = findViewById(R.id.edicion);
                    edicion.setText(card.getSet().getName());

                    ilustrador = findViewById(R.id.ilustrador);
                    ilustrador.setText(card.getIllustrator());

                    categoriaCarta = findViewById(R.id.categoriaCarta);
                    categoriaCarta.setText(card.getCategory());

                    numeroCarta = findViewById(R.id.numeroCarta);
                    numeroCarta.setText(card.getLocalId() + "/" + card.getSet().getCardCount().getOfficial().toString());

                    rarezaCarta = findViewById(R.id.rarezaCarta);
                    rarezaCarta.setText(card.getRarity());

                    efectoLabel = findViewById(R.id.efectoLabel);
                    efectoCarta = findViewById(R.id.efectoCarta);
                    if(card.getEffect() != null){
                        efectoCarta.setText(card.getEffect());
                        TextViewCompat.setAutoSizeTextTypeWithDefaults(efectoCarta, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                    }else{
                        efectoLabel.setVisibility(TextView.INVISIBLE);
                        efectoCarta.setVisibility(TextView.INVISIBLE);
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

        mRequestQueue.add(mStringRequest);
    }
}
