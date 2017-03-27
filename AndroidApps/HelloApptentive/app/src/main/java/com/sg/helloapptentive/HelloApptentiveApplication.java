package com.sg.helloapptentive;

import android.app.Application;

import com.apptentive.android.sdk.Apptentive;

/**
 * Created by dinesh.k.masthaiah on 3/22/2017.
 */

public class HelloApptentiveApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Apptentive.register(this, "YOUR_APPTENTIVE_API_KEY");
    }

}
