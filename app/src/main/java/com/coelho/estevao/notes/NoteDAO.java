package com.coelho.estevao.notes;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by estevao on 30/10/17.
 */

public class NoteDAO {

    // insert a new note in database
    public void insert(Note note) {
        DatabaseHelper instance = DatabaseHelper.getInstance();
        SQLiteDatabase db = instance.getWritableDatabase();

        ContentValues values = getContentValues(note);
        long id = db.insert("notes", null, values);

        db.close();
        instance.close();
        Log.i("inserted", "last id " + id);
    }


    public void delete(Note note) {
        //TODO: delete a note from database
    }

    public void update(Note note) {
        //TODO: update a note from database
    }

    public void getAll() {
        //TODO: get All notes from database
    }

    private ContentValues getContentValues(Note note) {
        ContentValues values = new ContentValues();
        values.put("id", note.getId());
        values.put("title", note.getTitle());
        values.put("body", note.getNoteContent());
        return values;
    }
}
