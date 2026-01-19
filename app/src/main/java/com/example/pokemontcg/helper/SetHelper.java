package com.example.pokemontcg.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pokemontcg.model.tcg.Set;

import java.util.ArrayList;
import java.util.List;

public class SetHelper {
    private final SQLHelper dbHelper;

    public SetHelper(Context context) {
        dbHelper = new SQLHelper(context);
    }

    // 🔍 Obtener todos los sets
    public List<Set> getSets() {
        List<Set> sets = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT id, id_set, name, logo, symbol, id_serie FROM set_";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {
                    Set set = new Set();
                    set.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    set.setIdSet(cursor.getString(cursor.getColumnIndexOrThrow("id_set")));
                    set.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    set.setLogo(cursor.getString(cursor.getColumnIndexOrThrow("logo")));
                    set.setSymbol(cursor.getString(cursor.getColumnIndexOrThrow("symbol")));

                    sets.add(set);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return sets;
    }

    // 🔎 Obtener un set por ID
    public Set getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        Set set = null;

        String sql = "SELECT id, id_set, name, logo, symbol, id_serie FROM set_ WHERE id = ?";

        try {
            cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                set = new Set();
                set.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                set.setIdSet(cursor.getString(cursor.getColumnIndexOrThrow("id_set")));
                set.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                set.setLogo(cursor.getString(cursor.getColumnIndexOrThrow("logo")));
                set.setSymbol(cursor.getString(cursor.getColumnIndexOrThrow("symbol")));
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return set;
    }
}
