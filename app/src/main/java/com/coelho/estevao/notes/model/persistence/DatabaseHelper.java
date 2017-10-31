package com.coelho.estevao.notes.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.coelho.estevao.notes.util.ApplicationUtil;

/**
 * Created by estevao on 30/10/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notesDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        //pass the context, database name, factory cursor, database version
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(getCreateTableScript());
    }

    public static DatabaseHelper getInstance() {
        return new DatabaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();
        create.append(" CREATE TABLE notes");
        create.append(" ( ");
        create.append("id INTEGER PRIMARY KEY, ");
        create.append("title TEXT, ");
        create.append("body TEXT, ");
        create.append("color TEXT ");
        create.append(" ); ");
        return create.toString();
    }

}
