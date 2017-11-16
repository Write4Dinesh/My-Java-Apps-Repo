package com.sg.helloclickablespan;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    String sampleString = " 10%get  OFF 94 on selected1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        makeTextColor();
        //makeTextBold();
       /* WFMRescueTextVIew textView = (WFMRescueTextVIew) findViewById(R.id.tnc_tv);
        Spanned spannded = Html.fromHtml("&cent;");
        String cent = getString(R.string.cent_symbol);
        String str = "Save 50¢ on any ONE (1) Package of Cut Basil from Produce Department. Valid 6/21-6/27/17. Excludes basil plants.\n" +
                "\n" +
                "Limit one coupon per purchase of specified product(s) per individual. Valid customer bar code must be presented at checkout. Quantities are limited and may not be available in all stores; no rain checks. Prices may vary. We reserve the right to correct errors. Void to the extent prohibited or restricted by law. No monetary value. Valid only in the U.S.A. Not valid at Whole Foods Market™ 365 locations.";

        textView.setText(spannded.toString() + str);*/
        //textView.setText(resizeDigitsInTheText(sampleString,30));

        /*SpannableString spannableString = new SpannableString("RM123.456");

        textView.setText(spannableString);*/
       /* TextView tv1 = (TextView)findViewById(R.id.before_price_tv);
        TextView tv2 = (TextView)findViewById(R.id.price_tv);
        TextView tv3 = (TextView)findViewById(R.id.after_price_tv);
        formatTv(sampleString,tv1, tv2, tv3);

        textView.setText(applySpannable("13.500,27"));

        SpannableStringBuilder builder=new SpannableStringBuilder("13.500,");
        builder.append(Html.fromHtml("X<sup>27</sup>"));
        textView.setText(builder);*/
    }

    private SpannableString resizeDigitsInTheText(String sourceString, int digitSizeInDP) {
        if (sourceString == null || sourceString.isEmpty()) {
            return null;
        }
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        int digitSizeInPixel = Math.round(digitSizeInDP * (displaymetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        // int dp = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, myPixels, displaymetrics );

        class DigitIndex {
            int start;
            int end;

            DigitIndex() {
                start = -1;
                end = -1;
            }
        }
        ArrayList<DigitIndex> digitSubStrings = new ArrayList<>();
        boolean isDigitFirstCharFound = false;
        DigitIndex digitIndex = new DigitIndex();
        for (int i = 0; i < sourceString.length(); i++) {
            // Tracks digit substring's last character
            if (isDigitFirstCharFound && !Character.isDigit(sourceString.charAt(i))) {
                digitIndex.end = i;
                digitSubStrings.add(digitIndex);
                digitIndex = new DigitIndex();
                isDigitFirstCharFound = false;
            }
            // Tracks digit substring's first character
            if (!isDigitFirstCharFound && Character.isDigit(sourceString.charAt(i))) {
                digitIndex.start = i;
                isDigitFirstCharFound = true;
            }
        }

        if (isDigitFirstCharFound) {//if the last character of the string is also a digit and hence a last index was -1
            digitIndex.end = sourceString.length();
            digitSubStrings.add(digitIndex);
        }
        TextAppearanceSpan textAppearanceSpan = null;
        int style = 0;
        SpannableString sourceSpanString = new SpannableString(sourceString);
        if (!digitSubStrings.isEmpty()) {
            for (DigitIndex digitSubstring : digitSubStrings) {
                textAppearanceSpan = new TextAppearanceSpan(null, style, digitSizeInPixel, null, null);
                sourceSpanString.setSpan(textAppearanceSpan, digitSubstring.start, digitSubstring.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return sourceSpanString;
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
                //ds.setTextSize(10);
            }
        };
        int start;
        int end;
        start = wholeText.indexOf(getString(R.string.tnc_spannable));
        end = start + getString(R.string.tnc_spannable).length();


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


    class TopAlignSuperscriptSpan extends SuperscriptSpan {
        //divide superscript by this number
        protected int fontScale = 2;

        //shift value, 0 to 1.0
        protected float shiftPercentage = 0;

        //doesn't shift
        TopAlignSuperscriptSpan() {
        }

        //sets the shift percentage
        TopAlignSuperscriptSpan(float shiftPercentage) {
            if (shiftPercentage > 0.0 && shiftPercentage < 1.0)
                this.shiftPercentage = shiftPercentage;
        }

        @Override
        public void updateDrawState(TextPaint tp) {
            //original ascent
            float ascent = tp.ascent();

            //scale down the font
            tp.setTextSize(tp.getTextSize() / fontScale);

            //get the new font ascent
            float newAscent = tp.getFontMetrics().ascent;

            //move baseline to top of old font, then move down size of new font
            //adjust for errors with shift percentage
            tp.baselineShift += (ascent - ascent * shiftPercentage)
                    - (newAscent - newAscent * shiftPercentage);
        }

        @Override
        public void updateMeasureState(TextPaint tp) {
            updateDrawState(tp);
        }
    }

    private void formatTv(String wholeText, TextView tv1, TextView tv2, TextView tv3) {
        String scr1, scr2, scr3;
        int start = 0;
        int end = 0;
        boolean firstDigitFound = false;
        if (wholeText != null && !wholeText.isEmpty()) {
            for (int i = 0; i < wholeText.length(); i++) {
                if (firstDigitFound && !Character.isDigit(wholeText.charAt(i))) {
                    end = i;
                    break;
                }
                if (!firstDigitFound && Character.isDigit(wholeText.charAt(i))) {
                    start = i;
                    firstDigitFound = true;
                }
            }
        }
        if (start == end) {
            Log.d("TextSizing", "there is no Digit found. returning");
            tv1.setText(wholeText);
            tv2.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
            return;
        }
        if (start == 0) {//First char is the Digit
            Log.d("TextSizing", "First Character is Digit");
            tv1.setVisibility(View.GONE);
            scr2 = wholeText.substring(start, end);
            tv2.setText(scr2);
            if (end != wholeText.length()) {
                scr3 = wholeText.substring(end, wholeText.length());
                tv3.setText(scr3);
            } else {
                Log.d("TextSizing", "There is no right part of the string for the digit");
                tv3.setVisibility(View.GONE);
            }
        } else {
            scr1 = wholeText.substring(0, start);
            scr2 = wholeText.substring(start, end);
            scr3 = wholeText.substring(end, wholeText.length());
            tv1.setText(scr1);
            tv2.setText(scr2);
            tv3.setText(scr3);
        }


    }

    private Spannable applySpannable(String text) {
        Spannable WordtoSpan = new SpannableString(text);
        WordtoSpan.setSpan(new TextAppearanceSpan(null, 0, 17, null, null), WordtoSpan.length() - 2, WordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        WordtoSpan.setSpan(new SuperscriptSpan2(), WordtoSpan.length() - 2, WordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return WordtoSpan;
    }

    public class SuperscriptSpan2 extends MetricAffectingSpan implements
            ParcelableSpan {
        public SuperscriptSpan2() {
        }

        public int getSpanTypeId() {
            return TextUtils.CAP_MODE_CHARACTERS;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
        }

        @Override
        public void updateDrawState(TextPaint tp) {
            tp.baselineShift += (int) (tp.ascent() / 1.25);
        }

        @Override
        public void updateMeasureState(TextPaint tp) {
            tp.baselineShift += (int) (tp.ascent() / 1.25);
        }
    }

    private void testList(){
        class Data implements Comparable {
            int rank;
            String desc;
            String type;
            public Data(int rank,String desc, String type){
                this.rank = rank;
                this.desc = desc;
                this.type = type;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "rank=" + rank +
                        ", desc='" + desc + '\'' +
                        ", type='" + type + '\'' +
                        '}';
            }

            @Override
            public int compareTo(Object obj) {
                int value = 0;
                Data o = (Data) obj;
                if (o.type.equals(type)) {
                    if (rank > o.rank) value = 1;
                    else if (rank < o.rank) value = -1;
                } else if (type.equals("strategy")) {
                    value = -1;
                } else {
                    value = 1;
                }
                return value;
            }
        }
        ArrayList<Data> list = new ArrayList<>();
        list.add(new Data(5,"five","coupon"));
        list.add(new Data(1,"one","strategy"));
        list.add(new Data(4,"four","coupon"));
        list.add(new Data(7,"seven","strategy"));
        list.add(new Data(3,"three","coupon"));
        Collections.sort(list);

        Log.d("TestList",list.toString());
    }
    private void makeTextBold(){
          String src =  "As of September 27, 2017,updates to the Whole Foods Market app will no longer be available outside of the United States.";
        String src1 = "September 27, 2017";

        SpannableString spannableString = new SpannableString(src);
        StyleSpan styleSpan = new StyleSpan(android.graphics.Typeface.BOLD);
        int start = src.indexOf(src1);
        int end = start + src1.length();
        TextView tncTv = (TextView) findViewById(R.id.tnc_tv);
        spannableString.setSpan(styleSpan,start,end,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tncTv.setText(spannableString);
    }

    private void makeTextColor(){
        String src =  "Name*";
        String src1 = "*";

        SpannableString spannableString = new SpannableString(src);
        StringColor styleSpan = new StringColor();
        int start = src.indexOf(src1);
        int end = start + src1.length();
        TextView tncTv = (TextView) findViewById(R.id.tnc_tv);
        spannableString.setSpan(styleSpan,start,end,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tncTv.setText(spannableString);
    }
    private class StringColor extends CharacterStyle {
        @Override
        public void updateDrawState(TextPaint tp) {
            tp.setColor(getResources().getColor(android.R.color.holo_green_dark));
        }
    }
}
