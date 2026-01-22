package com.example.pokemontcg;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.TextViewCompat;

import com.example.pokemontcg.helper.CardHelper;
import com.example.pokemontcg.model.tcg.Card;
import com.squareup.picasso.Picasso;

public class ItemActivity extends Activity {
    private TextView tituloNombre;
    private TextView categoriaCarta;
    private ImageView imgCarta;
    private TextView ilustrador;
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

        if(card.getSet().getLogo() != null){
            edicionLogo = findViewById(R.id.edicionLogo);
            Picasso.get().load(card.getSet().getLogo()).into(edicionLogo);
        }

        edicion = findViewById(R.id.edicion);
        edicion.setText(card.getSet().getName());

        ilustrador = findViewById(R.id.ilustrador);
        ilustrador.setText(card.getIllustrator());

        categoriaCarta = findViewById(R.id.categoriaCarta);
        categoriaCarta.setText(card.getCategory());

        rarezaCarta = findViewById(R.id.rarezaCarta);
        rarezaCarta.setText(card.getRarity());

        numeroCarta = findViewById(R.id.numeroCarta);
        numeroCarta.setText(card.getLocalId() + "/" + card.getSet().getCardCount().getOfficial().toString());

        efectoLabel = findViewById(R.id.efectoLabel);
        efectoCarta = findViewById(R.id.efectoCarta);
        if(card.getEffect() != null){
            efectoCarta.setText(card.getEffect());
            TextViewCompat.setAutoSizeTextTypeWithDefaults(efectoCarta, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        }else{
            efectoLabel.setVisibility(TextView.INVISIBLE);
            efectoCarta.setVisibility(TextView.INVISIBLE);
        }
    }

    private void mostrarZoomCarta(ImageView imgCarta) {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.image_zoom);

        ImageView imgZoom = dialog.findViewById(R.id.imgZoom);
        imgZoom.setImageDrawable(imgCarta.getDrawable());

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
