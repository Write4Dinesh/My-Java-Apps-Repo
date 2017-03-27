package com.sg.helloapptentive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.apptentive.android.sdk.Apptentive;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.message_center_button);
        if (Apptentive.canShowMessageCenter()) {
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Apptentive.showMessageCenter(MainActivity.this);
                }
            });
        } else {
            button.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Apptentive.engage(this, "main_activity_resumed");

    }
}
