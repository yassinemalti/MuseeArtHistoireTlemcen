package com.wordpress.yassinemalti.museearthistoiretlemcen.activity;

/**
 * Created by HP600-01 on 20/03/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartFirebaseAtBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(ShellService.class.getName()));
    }
}
