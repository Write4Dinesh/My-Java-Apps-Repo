package com.sg.sample.tunesample;

import com.tune.Tune;
import com.tune.ma.application.TuneApplication;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;


/**
 * Created by dinesh.k.masthaiah on 4/7/2017.
 */

public class MyApplication extends TuneApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize TMC
        Tune.init(this, "your_advertiser_ID", "your_conversion_key");
        //pass android id
        Tune.getInstance().setAndroidId(Secure.getString(getContentResolver(), Secure.ANDROID_ID));
        // pass device id
        String deviceId = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        Tune.getInstance().setDeviceId(deviceId);

        // WifiManager objects may be null : pass mac
        try {
            WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            Tune.getInstance().setMacAddress(wm.getConnectionInfo().getMacAddress());
        } catch (NullPointerException e) {
        }
    }

   /* Note: If your Application class already extends another class, you may instead call registerActivityLifecycleCallbacks with TuneActivityLifecycleCallbacks in your Application’s onCreate:

            import com.tune.ma.application.TuneActivityLifecycleCallbacks;

    public class MyApplication extends SomeOtherClass {
        @Override
        public void onCreate() {
            super.onCreate();

            if (Build.VERSION.SDK_INT >= 14) {
                registerActivityLifecycleCallbacks(new TuneActivityLifecycleCallbacks());
            }
        }
    }*/
}
