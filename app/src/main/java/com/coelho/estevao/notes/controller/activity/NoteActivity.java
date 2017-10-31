package com.coelho.estevao.notes.controller.activity;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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
    String selectedColor = "0288D1";
    CardView cardViewColor;
    ConstraintLayout holder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editTextNote = (EditText) findViewById(R.id.editTextNote);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        circleImageView = (CardView) findViewById(R.id.circleImageView);
        cardViewColor = (CardView) findViewById(R.id.cardViewColor);
        holder = (ConstraintLayout) findViewById(R.id.holder);


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SpectrumDialog.Builder(getContext())
                        .setColors(R.array.card_colors)
                        .setSelectedColorRes(R.color.cardBlue)
                        .setDismissOnColorSelected(false)
                        .setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(boolean positiveResult, @ColorInt int color) {
                                if (positiveResult) {
                                    cardViewColor.setCardBackgroundColor(color);
                                    holder.setBackgroundColor(color);
                                    selectedColor = Integer.toHexString(color).toUpperCase();
                                    Toast.makeText(getContext(), "Color selected: #" + selectedColor, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Dialog cancelled", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).build().show(getSupportFragmentManager(), "dialog_demo_5");
            }
        });
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
            note.setColor("#" + selectedColor);

            new NoteDAO().insert(note);
            Toast.makeText(this, note.getTitle() + " saved", Toast.LENGTH_SHORT).show();

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
