package com.example.pokemontcg.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pokemontcg.model.tcg.Ability;
import com.example.pokemontcg.model.tcg.Card;
import com.example.pokemontcg.model.tcg.CardCount;
import com.example.pokemontcg.model.tcg.Set;
import com.example.pokemontcg.model.tcg.Tipo;

import java.util.ArrayList;
import java.util.List;

public class CardHelper {
    private final SQLHelper dbHelper;
    private final Context context;

    public CardHelper(Context context) {
        dbHelper = new SQLHelper(context);
        this.context = context;
    }

    public List<Card> getCards(String idSet) {
        List<Card> cards = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT * FROM card WHERE id_set = ?";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, new String[]{String.valueOf(idSet)});

            if (cursor.moveToFirst()) {
                do {
                    Card card = new Card();
                    card.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    card.setIllustrator(cursor.getString(cursor.getColumnIndexOrThrow("illustrator")));
                    card.setHp(cursor.getInt(cursor.getColumnIndexOrThrow("hp")));
                    card.setStage(cursor.getString(cursor.getColumnIndexOrThrow("stage")));
                    card.setCategory(cursor.getString(cursor.getColumnIndexOrThrow("category")));
                    card.setEvolveFrom(cursor.getString(cursor.getColumnIndexOrThrow("evolve_from")));
                    card.setCardId(cursor.getString(cursor.getColumnIndexOrThrow("card_id")));
                    card.setLocalId(cursor.getString(cursor.getColumnIndexOrThrow("local_id")));
                    card.setRarity(cursor.getString(cursor.getColumnIndexOrThrow("rarity")));
                    card.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    card.setImage(cursor.getString(cursor.getColumnIndexOrThrow("image")));

                    cards.add(card);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return cards;
    }

    public List<Card> getCardsSearch(String search) {
        List<Card> cards = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT * FROM card WHERE name LIKE lower('%" + search +"%')";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    Card card = new Card();
                    card.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    card.setIllustrator(cursor.getString(cursor.getColumnIndexOrThrow("illustrator")));
                    card.setHp(cursor.getInt(cursor.getColumnIndexOrThrow("hp")));
                    card.setStage(cursor.getString(cursor.getColumnIndexOrThrow("stage")));
                    card.setCategory(cursor.getString(cursor.getColumnIndexOrThrow("category")));
                    card.setEvolveFrom(cursor.getString(cursor.getColumnIndexOrThrow("evolve_from")));
                    card.setCardId(cursor.getString(cursor.getColumnIndexOrThrow("card_id")));
                    card.setLocalId(cursor.getString(cursor.getColumnIndexOrThrow("local_id")));
                    card.setRarity(cursor.getString(cursor.getColumnIndexOrThrow("rarity")));
                    card.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    card.setImage(cursor.getString(cursor.getColumnIndexOrThrow("image")));

                    cards.add(card);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return cards;
    }

    public Card getById(String id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        Card card = new Card();

        String sql = "SELECT * FROM card WHERE id = ?";

        try {
            cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                card.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                card.setCategory(cursor.getString(cursor.getColumnIndexOrThrow("category")));
                card.setIllustrator(cursor.getString(cursor.getColumnIndexOrThrow("illustrator")));
                card.setHp(cursor.getInt(cursor.getColumnIndexOrThrow("hp")));
                card.setStage(cursor.getString(cursor.getColumnIndexOrThrow("stage")));
                card.setCategory(cursor.getString(cursor.getColumnIndexOrThrow("category")));
                card.setEvolveFrom(cursor.getString(cursor.getColumnIndexOrThrow("evolve_from")));
                card.setCardId(cursor.getString(cursor.getColumnIndexOrThrow("card_id")));
                card.setLocalId(cursor.getString(cursor.getColumnIndexOrThrow("local_id")));
                card.setRarity(cursor.getString(cursor.getColumnIndexOrThrow("rarity")));
                card.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                card.setImage(cursor.getString(cursor.getColumnIndexOrThrow("image")));
                card.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                card.setIdTcgPlayer(cursor.getInt(cursor.getColumnIndexOrThrow("id_tcgplayer")));

                SetHelper setHelper = new SetHelper(context);
                Set set = setHelper.getById(cursor.getInt(cursor.getColumnIndexOrThrow("id_set")));

                System.out.println(card.getImage());

                CardCount cardCount = setHelper.getCardCount(set.getId());

                set.setCardCount(cardCount);
                card.setSet(set);

                card.setAbilities(getAbilities(card.getId()));
                card.setTypes(getTypes(card.getId()));
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return card;
    }

    public List<Ability> getAbilities(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        List<Ability> abilities = new ArrayList<>();

        String sql = "SELECT * FROM ability WHERE id_card = ?";

        try {
            cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                Ability ability = new Ability();
                ability.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                ability.setEffect(cursor.getString(cursor.getColumnIndexOrThrow("effect")));
                ability.setType(cursor.getString(cursor.getColumnIndexOrThrow("type")));

                abilities.add(ability);
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return abilities;
    }

    public List<String> getTypes(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        List<String> typesArray = new ArrayList<>();

        String sql = "SELECT * FROM type WHERE id_card = ?";

        try {
            cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                do{
                    typesArray.add(cursor.getString(cursor.getColumnIndexOrThrow("type")));

                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return typesArray;
    }
}
