package com.sg.hellowfmupgrade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button gotoUpgradeBtn = (Button) findViewById(R.id.goto_upgrade_btn);
        gotoUpgradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchUpgradeActivity();
            }
        });
    }

    private void launchUpgradeActivity() {
        Intent intent = new Intent(this, WFMUpgradeActivity.class);
        startActivity(intent);
    }
}
