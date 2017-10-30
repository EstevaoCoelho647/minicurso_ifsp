package com.coelho.estevao.notes;

/**
 * Created by estevao on 30/10/17.
 */

public class ApplicationUtil {

    private static NotesApplication context;

    public static void setContext(NotesApplication context) {
        ApplicationUtil.context = context;
    }

    public static NotesApplication getContext() {
        return context;
    }
}
