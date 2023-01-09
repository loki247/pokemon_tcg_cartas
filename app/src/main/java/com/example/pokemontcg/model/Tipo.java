package com.example.pokemontcg.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Tipo  implements Serializable {
    ArrayList<String> listaTipos;

    public ArrayList<String> getListaTipos() {
        listaTipos.add("Acero");
        listaTipos.add("Agua");
        listaTipos.add("Fuego");
        listaTipos.add("Hada");
        listaTipos.add("Lucha");
        listaTipos.add("Oscura");
        listaTipos.add("Planta");
        listaTipos.add("Psiquica");
        listaTipos.add("Rayo");
        listaTipos.add("Incolora");

        return listaTipos;
    }

    public void setListaTipos(ArrayList<String> listaTipos) {
        this.listaTipos = listaTipos;
    }
}
