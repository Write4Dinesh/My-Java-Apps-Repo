package com.sg.sample.java.wordwrapping;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView line1Tv = (TextView)findViewById(R.id.line1_tv);
        String title = "Fingerling Potato Tostones";
        ArrayList<String> lines = WordWrapMain.splitLines(title,24);
        if(lines!=null && !lines.isEmpty() && lines.size() > 1){
            line1Tv.setText(lines.get(0));
            line1Tv.requestLayout();
            TextView line2Tv = (TextView)findViewById(R.id.line2_tv);
            line2Tv.setVisibility(View.VISIBLE);
             line2Tv.setText(lines.get(1));

        }else {
            line1Tv.setText(title);
        }
    }
}
