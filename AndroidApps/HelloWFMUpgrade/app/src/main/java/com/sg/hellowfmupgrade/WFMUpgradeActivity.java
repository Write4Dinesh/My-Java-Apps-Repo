package com.sg.hellowfmupgrade;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WFMUpgradeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        final Button gotoPlayStoreBtn = (Button) findViewById(R.id.goto_playstore_btn);
        gotoPlayStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPlayStore();
            }
        });
    }

    private void goToPlayStore() {
        Toast.makeText(this, "Go to playstore button clicked", Toast.LENGTH_LONG).show();
    }
}
