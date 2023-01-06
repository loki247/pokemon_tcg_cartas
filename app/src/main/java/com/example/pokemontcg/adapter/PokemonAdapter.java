package com.example.pokemontcg.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.pokemontcg.model.Pokemon;
import com.example.pokemontcg.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import com.squareup.picasso.Picasso;

public class PokemonAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> listaPokemon;

    public PokemonAdapter(Context context, ArrayList<String> listaPokemon) {
        this.context = context;
        this.listaPokemon = listaPokemon;
    }

    @Override
    public int getCount() {
        return listaPokemon.size();
    }

    @Override
    public Object getItem(int i) {
        return listaPokemon.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            JSONObject pokemon = new JSONObject(listaPokemon.get(i));
            view = LayoutInflater.from(context).inflate(R.layout.pokemon_item, null);

            TextView nombrePokemon = view.findViewById(R.id.nombre_pokemon);
            nombrePokemon.setText(pokemon.getString("name"));

            JSONObject urlObject = new JSONObject(pokemon.getString("images"));

            String urlImg = urlObject.getString("small");

            ImageView imgCarta = view.findViewById(R.id.img_carta);
            Picasso.get().load(urlImg).into(imgCarta);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}
