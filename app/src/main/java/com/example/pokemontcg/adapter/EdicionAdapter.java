package com.example.pokemontcg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemontcg.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EdicionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> listaEdiciones;

    public EdicionAdapter(Context context, ArrayList<String> listaEdiciones) {
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
            JSONObject edicion = new JSONObject(listaEdiciones.get(i));
            view = LayoutInflater.from(context).inflate(R.layout.edicion_item, null);

            TextView nombreEdicion = view.findViewById(R.id.nombre_edicion);
            nombreEdicion.setText(edicion.getString("name"));

            //JSONObject urlObject = new JSONObject(edicion.getString("logo"));

            String urlImg = edicion.getString("logo") + ".png";
            System.out.println(urlImg);
            ImageView imgEdicion = view.findViewById(R.id.img_edicion);
            Picasso.get().load(urlImg).into(imgEdicion);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}
