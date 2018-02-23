package com.sg.multiplereceiverforsameevent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by dinesh.k.masthaiah on 4/18/2017.
 */

public class MainReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MultiReceiver", "Called onReceive of " + getClass().getSimpleName());
        new AReceiver().onReceive(context, intent);
        new BReceiver().onReceive(context, intent);
        new CReceiver().onReceive(context, intent);
    }
}
