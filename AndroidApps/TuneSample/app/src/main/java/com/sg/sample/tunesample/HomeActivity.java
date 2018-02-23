package com.sg.sample.tunesample;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.tune.Tune;
import com.tune.ma.application.TuneActivity;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT < 14) {
            TuneActivity.onStart(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT < 14) {
            TuneActivity.onResume(this);
        }
    }

    @Override
    public void onStop() {
        if (Build.VERSION.SDK_INT < 14) {
            TuneActivity.onStop(this);
        }
        super.onStop();
    }
}
