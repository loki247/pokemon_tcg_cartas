package com.example.pokemontcg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemontcg.R;
import com.example.pokemontcg.model.tcg.Set;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EdicionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Set> listaEdiciones;

    public EdicionAdapter(Context context, ArrayList<Set> listaEdiciones) {
        this.context = context;
        this.listaEdiciones = listaEdiciones;
    }

    @Override
    public int getCount() {
        return listaEdiciones.size();
    }

    @Override
    public Object getItem(int i) {
        return listaEdiciones.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            Set set = listaEdiciones.get(i);
            view = LayoutInflater.from(context).inflate(R.layout.edicion_item, null);

            TextView nombreEdicion = view.findViewById(R.id.nombre_edicion);
            nombreEdicion.setText(set.getName());

            String urlImg = set.getLogo() + ".png";
            ImageView imgEdicion = view.findViewById(R.id.img_edicion);
            Picasso.get().load(urlImg).into(imgEdicion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
