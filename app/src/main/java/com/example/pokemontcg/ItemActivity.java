package com.example.pokemontcg;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemontcg.helper.CardHelper;
import com.example.pokemontcg.model.tcg.Card;
import com.google.android.material.textview.MaterialTextView;
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
    private MaterialTextView descripcion;

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

        descripcion = findViewById(R.id.descripcion);
        String descripcionTxt = "<b>Ilustrador: </b>" + card.getIllustrator();
        descripcionTxt += "<br>";
        descripcionTxt += "<b>Categoria: </b>" + card.getCategory();
        descripcionTxt += "<br>";
        descripcionTxt += "<b>Número: </b>" + card.getLocalId() + "/" + card.getSet().getCardCount().getOfficial().toString();
        descripcionTxt += "<br>";
        descripcionTxt += "<b>Rareza: </b>" + card.getRarity();

        if(card.getAbilities() != null && !card.getAbilities().isEmpty()){
            descripcionTxt += "<br>";
            descripcionTxt += "<b>Efecto: </b>";
            descripcionTxt += "<br>";
            descripcionTxt += card.getAbilities().get(0).getEffect();
        }

        if(card.getIdTcgPlayer() != null){
            descripcionTxt += "<br>";
            descripcionTxt += "<a href='https://www.tcgplayer.com/product/" + card.getIdTcgPlayer() + "'>Ver en TcgPlayer</a>";
        }

        descripcion.setText(Html.fromHtml(descripcionTxt, Html.FROM_HTML_MODE_LEGACY));
        Spannable spannable = new SpannableString(descripcion.getText());

        URLSpan[] urls = spannable.getSpans(0, spannable.length(), URLSpan.class);

        for (URLSpan span : urls) {
            int start = spannable.getSpanStart(span);
            int end = spannable.getSpanEnd(span);
            String url = span.getURL();

            spannable.removeSpan(span);

            spannable.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Intent intent = new Intent(widget.getContext(), WebViewActivity.class);
                    intent.putExtra(WebViewActivity.EXTRA_URL, url);
                    widget.getContext().startActivity(intent);
                }
            }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        descripcion.setText(spannable);
        descripcion.setMovementMethod(LinkMovementMethod.getInstance());
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
