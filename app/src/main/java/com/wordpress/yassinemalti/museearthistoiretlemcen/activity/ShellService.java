package com.wordpress.yassinemalti.museearthistoiretlemcen.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ShellService extends Service {
    public ShellService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, START_STICKY);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}