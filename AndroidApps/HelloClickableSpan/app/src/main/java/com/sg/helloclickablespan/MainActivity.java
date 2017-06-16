package com.sg.helloclickablespan;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applySpanForAffinityTnc();
    }

    private SpannableString applySpanForAffinityTnc() {
        String wholeText = getString(R.string.an_agree_term_conditions_info1);

        SpannableString spannableString = new SpannableString(wholeText);
        ClickableSpan tncSpanClickable = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(MainActivity.this, "Clicked Terms & Conditions", Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.dark_green));
            }
        };
        ClickableSpan touSpanClickable = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(MainActivity.this, "Clicked Terms of use", Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.dark_green));
            }
        };
        ClickableSpan ppSpanClickable = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(MainActivity.this, "Clicked Privacy Policy", Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.dark_green));
            }
        };
        int start;
        int end;
        start = wholeText.indexOf(getString(R.string.tnc_spannable));
        end = start + getString(R.string.tnc_spannable).length();
        spannableString.setSpan(tncSpanClickable, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        start = wholeText.indexOf(getString(R.string.tou_spannable));
        end = start + getString(R.string.tou_spannable).length();
        spannableString.setSpan(touSpanClickable, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        start = wholeText.indexOf(getString(R.string.pp_spannable));
        end = start + getString(R.string.pp_spannable).length();
        spannableString.setSpan(ppSpanClickable, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView tncTv = (TextView) findViewById(R.id.tnc_tv);
        tncTv.setText(spannableString);
        tncTv.setMovementMethod(LinkMovementMethod.getInstance());
        return spannableString;
    }
}
