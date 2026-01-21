package com.example.pokemontcg;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemontcg.helper.CardHelper;
import com.example.pokemontcg.model.tcg.Card;
import com.squareup.picasso.Picasso;


public class PokemonActivity extends Activity {
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
        getDataPokemon(getIntent().getStringExtra("id"));

        imgCarta.setOnClickListener(v -> mostrarZoomCarta(imgCarta));

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void getDataPokemon(String idCarta) {
        CardHelper cardHelper = new CardHelper(this);
        Card card = cardHelper.getById(idCarta);

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

    private void mostrarZoomCarta(ImageView imgCarta) {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.image_zoom);

        ImageView imgZoom = dialog.findViewById(R.id.imgZoom);
        imgZoom.setImageDrawable(imgCarta.getDrawable());

        // Animación entrada
        imgZoom.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_in_fade));

        imgZoom.setOnClickListener(v -> {
            Animation animOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out_fade);
            animOut.setAnimationListener(new Animation.AnimationListener() {
                @Override public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    dialog.dismiss();
                }

                @Override public void onAnimationRepeat(Animation animation) {}
            });

            imgZoom.startAnimation(animOut);
        });

        dialog.show();
    }
}
