package com.example.pokemontcg.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pokemontcg.model.tcg.CardCount;
import com.example.pokemontcg.model.tcg.Set;

import java.util.ArrayList;
import java.util.List;

public class SetHelper {
    private final SQLHelper dbHelper;

    public SetHelper(Context context) {
        dbHelper = new SQLHelper(context);
    }

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

    public Set getById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        Set set = new Set();

        String sql = "SELECT id, id_set, name, logo, symbol, id_serie FROM set_ WHERE id = ?";

        try {
            cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
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

    public CardCount getCardCount(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        CardCount cardCount = new CardCount();

        String sql = "SELECT * FROM card_count WHERE id_set = ?";

        try {
            cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                cardCount.setTotal(cursor.getInt(cursor.getColumnIndexOrThrow("total")));
                cardCount.setOfficial(cursor.getInt(cursor.getColumnIndexOrThrow("official")));
                cardCount.setFirstEd(cursor.getInt(cursor.getColumnIndexOrThrow("first_ed")));
                cardCount.setHolo(cursor.getInt(cursor.getColumnIndexOrThrow("holo")));
                cardCount.setNormal(cursor.getInt(cursor.getColumnIndexOrThrow("normal")));
                cardCount.setReverse(cursor.getInt(cursor.getColumnIndexOrThrow("reverse")));
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return cardCount;
    }
}
