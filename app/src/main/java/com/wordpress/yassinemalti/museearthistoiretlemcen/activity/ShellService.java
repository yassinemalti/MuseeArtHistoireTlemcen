package com.wordpress.yassinemalti.museearthistoiretlemcen.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class ShellService extends Service {

    private static String TAG = "ShellService";
    private Handler handler;
    private Runnable runnable;
    private final int runTime = 5000;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                handler.postDelayed(runnable, runTime);
            }
        };
        handler.post(runnable);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart");
    }

}