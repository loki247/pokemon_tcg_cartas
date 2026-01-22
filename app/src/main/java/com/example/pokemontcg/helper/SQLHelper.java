package com.example.pokemontcg.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pokemon_tcg.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        context.deleteDatabase(DATABASE_NAME);
        copiarBaseDesdeAssets();
    }

    private void copiarBaseDesdeAssets() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);

        if (dbFile.exists()) return;

        dbFile.getParentFile().mkdirs();

        try (InputStream is = context.getAssets().open(DATABASE_NAME);
             OutputStream os = new FileOutputStream(dbFile)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();

        } catch (Exception e) {
            throw new RuntimeException("Error copiando DB desde assets", e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
