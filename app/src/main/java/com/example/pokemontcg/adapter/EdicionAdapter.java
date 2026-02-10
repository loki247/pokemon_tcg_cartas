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
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.edicion_item, parent, false);

            holder = new ViewHolder();
            holder.nombreEdicion = convertView.findViewById(R.id.nombre_edicion);
            holder.imgEdicion = convertView.findViewById(R.id.img_edicion);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Set set = listaEdiciones.get(i);
        holder.nombreEdicion.setText(set.getName());
        Picasso.get().load(set.getLogo()).into(holder.imgEdicion);

        return convertView;
    }

    public void updateData(ArrayList<Set> nuevasEdiciones) {
        listaEdiciones.clear();
        listaEdiciones.addAll(nuevasEdiciones);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView nombreEdicion;
        ImageView imgEdicion;
    }
}
