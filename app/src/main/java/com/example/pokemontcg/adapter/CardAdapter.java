package com.example.pokemontcg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemontcg.R;

import java.util.ArrayList;

import com.example.pokemontcg.model.tcg.Card;
import com.squareup.picasso.Picasso;

public class CardAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Card> cards;

    public CardAdapter(Context context, ArrayList<Card> cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int i) {
        return cards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            Card card = cards.get(i);
            view = LayoutInflater.from(context).inflate(R.layout.pokemon_item, null);

            TextView nombrePokemon = view.findViewById(R.id.nombre_pokemon);
            nombrePokemon.setText(card.getName());

            String urlImg = card.getImage();
            ImageView imgCarta = view.findViewById(R.id.img_carta);
            Picasso.get().load(urlImg).into(imgCarta);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
