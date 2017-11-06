package com.coelho.estevao.notes.model.entity;

import java.io.Serializable;

/**
 * Created by estevao on 30/10/17.
 */

public class Note implements Serializable {
    private Long id;
    private String title;
    private String noteContent;
    private String color;

    public Note(String title, String note) {
        this.title = title;
        this.noteContent = note;
    }

    public Note() {

    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
