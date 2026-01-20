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
import com.example.pokemontcg.helper.CardHelper;
import com.example.pokemontcg.model.tcg.Ability;
import com.example.pokemontcg.model.tcg.Card;
import com.example.pokemontcg.model.tcg.CardCount;
import com.example.pokemontcg.model.tcg.Set;
import com.example.pokemontcg.model.tcg.Tipo;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonActivity extends Activity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String urlApi = "https://api.tcgdex.net/v2/en/cards/";

    private TextView tituloNombre;
    private ImageView imgCarta;
    private ImageView edicionLogo;
    private TextView ilustrador;
    private TextView edicion;
    private TextView hpCarta;
    private TextView faseCarta;
    private TextView tipoCarta;
    private TextView preEvolucionCarta;
    private TextView numeroCarta;
    private TextView rarezaCarta;
    private TextView habilidadLabel;
    private TextView habilidadCarta;
    private TextView textoHabilidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon);
        getDataPokemon(getIntent().getIntExtra("id", 0));
    }

    private void getDataPokemon(Integer idCarta) {
        CardHelper cardHelper = new CardHelper(this);
        Card card = cardHelper.getById(idCarta);

        /*if(jsonObject.has("types")){
            List<String> tipos = new ArrayList<>();
            JSONArray jsonArrayTypes = new JSONArray(jsonObject.getString("types"));

            for(int i = 0; i < jsonArrayTypes.length(); i++){
                tipos.add(jsonArrayTypes.get(i).toString());
            }

            card.setTypes(tipos);
        }

        card.setEvolveFrom(jsonObject.has("evolveFrom") ? jsonObject.getString("evolveFrom") : null);
        card.setRarity(jsonObject.getString("rarity"));

        card.setLocalId(jsonObject.getString("localId"));

        if(jsonObject.has("abilities")){
            List<Ability> abilities = new ArrayList<>();
            JSONArray jsonArrayAbilities = new JSONArray(jsonObject.getString("abilities"));
            for(int i = 0; i < jsonArrayAbilities.length(); i++){
                Ability ability = new Ability();
                ability.setType(jsonArrayAbilities.getJSONObject(i).getString("type"));
                ability.setName(jsonArrayAbilities.getJSONObject(i).getString("name"));
                ability.setEffect(jsonArrayAbilities.getJSONObject(i).getString("effect"));
                abilities.add(ability);
            }

            card.setAbilities(abilities);
        }

        CardCount cardCount = new CardCount();
        cardCount.setOfficial(Integer.parseInt(jsonObject.getJSONObject("set").getJSONObject("cardCount").getString("official")));
        cardCount.setTotal(Integer.parseInt(jsonObject.getJSONObject("set").getJSONObject("cardCount").getString("total")));

        Set set = new Set();
        set.setName(jsonObject.getJSONObject("set").has("name") ? jsonObject.getJSONObject("set").getString("name") :  null);
        set.setLogo(jsonObject.getJSONObject("set").has("logo") ? jsonObject.getJSONObject("set").getString("logo") + ".png" : null);
        set.setCardCount(cardCount);
        card.setSet(set);*/

        tituloNombre = findViewById(R.id.tituloNombre);
        tituloNombre.setText(card.getName());

        imgCarta = findViewById(R.id.imgCarta);
        Picasso.get().load(card.getImage()).into(imgCarta);

        edicionLogo = findViewById(R.id.edicionLogo);
        Picasso.get().load(card.getSet().getLogo() + ".png").into(edicionLogo);

        edicion = findViewById(R.id.edicion);
        edicion.setText(card.getSet().getName());

        ilustrador = findViewById(R.id.ilustrador);
        ilustrador.setText(card.getIllustrator());

        hpCarta = findViewById(R.id.hpCarta);
        hpCarta.setText(card.getHp().toString());

        faseCarta = findViewById(R.id.faseCarta);
        faseCarta.setText(card.getStage());

        habilidadCarta = findViewById(R.id.habilidadCarta);

        preEvolucionCarta = findViewById(R.id.preevolucionCarta);
        preEvolucionCarta.setText(card.getEvolveFrom());

        tipoCarta = findViewById(R.id.tipoCarta);

        if(card.getTypes() != null && !card.getTypes().isEmpty()){
            tipoCarta.setText(String.join(", ", card.getTypes()));
        }else{
            tipoCarta.setVisibility(TextView.INVISIBLE);
        }

        rarezaCarta = findViewById(R.id.rarezaCarta);
        rarezaCarta.setText(card.getRarity());

        numeroCarta = findViewById(R.id.numeroCarta);
        numeroCarta.setText(card.getLocalId() + "/" + card.getSet().getCardCount().getOfficial().toString());

        habilidadLabel = findViewById(R.id.habilidadLabel);
        habilidadCarta = findViewById(R.id.habilidadCarta);
        textoHabilidad = findViewById(R.id.textoHabilidad);

        if(card.getAbilities() != null && !card.getAbilities().isEmpty()){
            habilidadCarta.setText(card.getAbilities().get(0).getName());
            textoHabilidad.setText(card.getAbilities().get(0).getEffect());
        }else{
            habilidadLabel.setVisibility(TextView.INVISIBLE);
            habilidadCarta.setVisibility(TextView.INVISIBLE);
            textoHabilidad.setVisibility(TextView.INVISIBLE);
        }
    }
}
