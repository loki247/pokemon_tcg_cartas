package com.example.pokemontcg.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CollectionSQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "colecciones.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public CollectionSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        System.out.println("COLLECTION");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE coleccion(" +
                        "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT NOT NULL)";

        String sql2 = "CREATE TABLE carta_coleccion (" +
                        "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "id_carta TEXT NOT NULL," +
                        "obtenida INTEGER NOT NULL DEFAULT 0)";

        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS coleccion";
        String sql2 = "DROP TABLE IF EXISTS carta_coleccion";

        db.execSQL(sql);
        db.execSQL(sql2);
        onCreate(db);
    }
}
