package com.coelho.estevao.notes;

/**
 * Created by estevao on 30/10/17.
 */

public class Note {
    private String title;
    private String noteContent;

    public Note(String title, String note) {
        this.title = title;
        this.noteContent = note;
    }

    public Note() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
