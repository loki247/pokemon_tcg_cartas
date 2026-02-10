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
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.pokemon_item, parent, false);

            holder = new ViewHolder();
            holder.nombrePokemon = convertView.findViewById(R.id.nombre_pokemon);
            holder.imgCarta = convertView.findViewById(R.id.img_carta);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Card card = cards.get(i);

        holder.nombrePokemon.setText(card.getName());

        Picasso.get().load(card.getImage()).into(holder.imgCarta);

        return convertView;
    }


    public void updateData(ArrayList<Card> nuevasCartas) {
        this.cards.clear();
        this.cards.addAll(nuevasCartas);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView nombrePokemon;
        ImageView imgCarta;
    }
}