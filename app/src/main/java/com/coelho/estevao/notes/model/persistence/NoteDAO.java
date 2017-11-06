package com.coelho.estevao.notes.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.coelho.estevao.notes.model.entity.Note;

import java.util.ArrayList;

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

    //  Delete a note from database
    public void delete(Note note) {
        DatabaseHelper instance = DatabaseHelper.getInstance();
        SQLiteDatabase db = instance.getWritableDatabase();

        String where = "id = ?";
        String[] params = {String.valueOf(note.getId())};

        db.delete("notes", where, params);

        db.close();
        instance.close();
    }

    // Update a note from database
    public void update(Note note) {
        DatabaseHelper instance = DatabaseHelper.getInstance();
        SQLiteDatabase db = instance.getWritableDatabase();

        ContentValues values = getContentValues(note);

        String where = "id = ?";
        String[] params = {String.valueOf(note.getId())};

        db.update("notes", values, where, params);
    }

    public ArrayList<Note> getAll() {
        //get All notes from database
        DatabaseHelper instance = DatabaseHelper.getInstance();
        SQLiteDatabase db = instance.getReadableDatabase();
        String[] columns = {"id", "title", "body", "color"};
        //params: table name, columns, selection, selectionArgs, groupBy, having, orderBy
        Cursor cursor = db.query("notes", columns, null, null, null, null, "id");

        ArrayList<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            Note note = new Note();
            if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
                note.setId(cursor.getLong(cursor.getColumnIndex("id")));
                note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                note.setNoteContent(cursor.getString(cursor.getColumnIndex("body")));
                note.setColor(cursor.getString(cursor.getColumnIndex("color")));
            }
            notes.add(note);
        }

        db.close();
        instance.close();
        return notes;
    }

    private ContentValues getContentValues(Note note) {
        ContentValues values = new ContentValues();
        values.put("id", note.getId());
        values.put("title", note.getTitle());
        values.put("body", note.getNoteContent());
        values.put("color", note.getColor());
        return values;
    }
}
