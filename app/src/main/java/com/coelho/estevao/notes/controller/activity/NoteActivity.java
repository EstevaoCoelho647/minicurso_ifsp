package com.coelho.estevao.notes.controller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.coelho.estevao.notes.R;
import com.coelho.estevao.notes.model.entity.Note;
import com.coelho.estevao.notes.model.persistence.NoteDAO;

/**
 * Created by estevao on 30/10/17.
 */

public class NoteActivity extends AppCompatActivity {
    EditText editTextNote;
    EditText editTextTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editTextNote = (EditText) findViewById(R.id.editTextNote);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            Note note = new Note();
            note.setTitle(editTextTitle.getText().toString());
            note.setNoteContent(editTextNote.getText().toString());

            new NoteDAO().insert(note);
            Toast.makeText(this, note.getTitle() + " saved", Toast.LENGTH_SHORT).show();

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
