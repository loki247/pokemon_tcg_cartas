package com.example.pokemontcg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemontcg.BusquedaTipoActivity;
import com.example.pokemontcg.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class TipoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> listaTipos = new ArrayList<>();

    public TipoAdapter(Context context) {
        listaTipos.add("Incolora");
        listaTipos.add("Oscura");
        listaTipos.add("Dragón");
        listaTipos.add("Hada");
        listaTipos.add("Lucha");
        listaTipos.add("Fuego");
        listaTipos.add("Planta");
        listaTipos.add("Rayo");
        listaTipos.add("Acero");
        listaTipos.add("Psiquica");
        listaTipos.add("Agua");

        this.context = context;
    }

    @Override
    public int getCount() {
        return listaTipos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaTipos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String tipo = listaTipos.get(i);
        view = LayoutInflater.from(context).inflate(R.layout.tipo_item, null);

        TextView nombrePokemon = view.findViewById(R.id.nombre_tipo);
        nombrePokemon.setText(tipo);

        ImageView imgTipo = view.findViewById(R.id.img_tipo);
        if(listaTipos.get(i).equalsIgnoreCase("dragón")){
            imgTipo.setImageResource(view.getResources().getIdentifier("com.example.pokemontcg:drawable/" + "energia_dragon", null, null));
        }else{
            imgTipo.setImageResource(view.getResources().getIdentifier("com.example.pokemontcg:drawable/" + "energia_" + listaTipos.get(i).toLowerCase(), null, null));
        }


        return view;
    }
}
