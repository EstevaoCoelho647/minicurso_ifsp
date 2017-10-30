package com.coelho.estevao.notes.controller.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.coelho.estevao.notes.controller.adapter.NoteAdapter;
import com.coelho.estevao.notes.R;
import com.coelho.estevao.notes.model.entity.Note;
import com.coelho.estevao.notes.model.persistence.NoteDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        final Note note = new Note("noteeeeeee", "booooooody");
        final NoteDAO noteDAO = new NoteDAO();

        final ArrayList<Note> allNotes = noteDAO.getAll();

        final NoteAdapter noteAdapter = new NoteAdapter(allNotes);
        recyclerView.setAdapter(noteAdapter);
        noteAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteDAO.insert(note);
                allNotes.add(note);
                noteAdapter.notifyDataSetChanged();
//                Intent goToNoteActivity = new Intent(MainActivity.this, NoteActivity.class);
//                startActivity(goToNoteActivity);
            }
        });
    }
}
