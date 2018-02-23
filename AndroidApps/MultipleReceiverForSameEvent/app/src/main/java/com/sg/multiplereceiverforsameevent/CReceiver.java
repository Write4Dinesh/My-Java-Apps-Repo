package com.sg.multiplereceiverforsameevent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by dinesh.k.masthaiah on 4/18/2017.
 */

public class CReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MultiReceiver", "Called onReceive of " + getClass().getSimpleName());
    }
}
