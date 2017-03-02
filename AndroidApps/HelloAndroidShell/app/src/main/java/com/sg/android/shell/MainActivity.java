package com.sg.android.shell;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText commandEt = (EditText) findViewById(R.id.command_et);
        final Button commandBtn = (Button) findViewById(R.id.execute_btn);

        commandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String command = commandEt.getText().toString();
                //http://stackoverflow.com/questions/20932102/execute-shell-command-from-android
                try {
                    //Its a Java process (not the android process)
                    Process subProcess = Runtime.getRuntime().exec("shell");
                    DataOutputStream outputStream = new DataOutputStream(subProcess.getOutputStream());
                    outputStream.writeBytes(command);
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    subProcess.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
