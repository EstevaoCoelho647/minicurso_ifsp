package com.coelho.estevao.notes.controller.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.coelho.estevao.notes.R;
import com.coelho.estevao.notes.model.entity.Note;
import com.coelho.estevao.notes.model.persistence.NoteDAO;
import com.thebluealliance.spectrum.SpectrumDialog;

import static com.coelho.estevao.notes.util.ApplicationUtil.getContext;

/**
 * Created by estevao on 30/10/17.
 */

public class NoteActivity extends AppCompatActivity {
    EditText editTextNote;
    EditText editTextTitle;
    CardView circleImageView;
    int selectedColor;
    CardView cardViewColor;
    ConstraintLayout holder;
    Note note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        editTextNote = (EditText) findViewById(R.id.editTextNote);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        circleImageView = (CardView) findViewById(R.id.circleImageView);
        cardViewColor = (CardView) findViewById(R.id.cardViewColor);
        holder = (ConstraintLayout) findViewById(R.id.holder);

        selectedColor = ContextCompat.getColor(this, R.color.cardCyan);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            note = (Note) extras.get("NOTE");
            bindParameters(note);
        } else {
            note = new Note();
        }

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SpectrumDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                        .setColors(R.array.card_colors)
                        .setSelectedColor(selectedColor)
                        .setDismissOnColorSelected(false)
                        .setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(boolean positiveResult, @ColorInt int color) {
                                if (positiveResult) {
                                    cardViewColor.setCardBackgroundColor(color);
                                    holder.setBackgroundColor(color);
                                    selectedColor = color;
                                }
                            }
                        }).build().show(getSupportFragmentManager(), "dialog_demo_5");
            }
        });
    }

    private void bindParameters(Note note) {
        editTextNote.setText(note.getNoteContent());
        editTextTitle.setText(note.getTitle());
        selectedColor = Color.parseColor(note.getColor());
        cardViewColor.setCardBackgroundColor(selectedColor);
        holder.setBackgroundColor(selectedColor);
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
            note.setTitle(editTextTitle.getText().toString());
            note.setNoteContent(editTextNote.getText().toString());
            note.setColor("#" + Integer.toHexString(selectedColor).toUpperCase());

            if (note.getId() == null) {
                new NoteDAO().insert(note);
                Toast.makeText(this, note.getTitle() + " saved", Toast.LENGTH_SHORT).show();
            } else {
                new NoteDAO().update(note);
                Toast.makeText(this, note.getTitle() + " updated", Toast.LENGTH_SHORT).show();
            }
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
