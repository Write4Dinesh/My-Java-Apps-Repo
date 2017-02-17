package com.sg.sample.java.wordwrapping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView line1Tv = (TextView)findViewById(R.id.line1_tv);
        String title = "This is the First Line This is the 2nd line" +
                "This is the 3rd line this is the fourth line";
        ArrayList<String> lines = WordWrapMain.splitLines(title,19);
        if(lines!=null && !lines.isEmpty() && lines.size() > 1){
            line1Tv.setText(lines.get(0));
            TextView line2Tv = (TextView)findViewById(R.id.line2_tv);
            line2Tv.setVisibility(View.VISIBLE);
            line2Tv.setText(lines.get(1));
        }else {
            line1Tv.setText(title);
        }
    }
}
