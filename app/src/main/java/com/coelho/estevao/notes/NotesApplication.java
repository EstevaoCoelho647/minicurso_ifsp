package com.coelho.estevao.notes;

import android.app.Application;

/**
 * Created by estevao on 30/10/17.
 */

public class NotesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(this);
    }
}
