package com.coelho.estevao.notes.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coelho.estevao.notes.R;
import com.coelho.estevao.notes.controller.activity.NoteActivity;
import com.coelho.estevao.notes.model.entity.Note;
import com.coelho.estevao.notes.model.persistence.NoteDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by estevao on 30/10/17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private List<Note> noteList;
    private Context context;

    public NoteAdapter(Context context) {
        this.context = context;
        this.noteList = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Note note = noteList.get(position);

        holder.textViewNoteBody.setText(note.getNoteContent());
        holder.textViewNoteTitle.setText(note.getTitle());

        holder.linearLayout.setCardBackgroundColor(Color.parseColor(note.getColor()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                NoteDAO noteDAO = new NoteDAO();
                noteDAO.delete(note);
                noteList.remove(note);
                notifyDataSetChanged();
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNoteActivity = new Intent(context, NoteActivity.class);
                goToNoteActivity.putExtra("NOTE", note);
                context.startActivity(goToNoteActivity);
            }
        });
    }


    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList.clear();
        this.noteList.addAll(noteList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView linearLayout;
        TextView textViewNoteTitle;
        TextView textViewNoteBody;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewNoteTitle = itemView.findViewById(R.id.textViewNoteTitle);
            textViewNoteBody = itemView.findViewById(R.id.textViewNoteBody);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
