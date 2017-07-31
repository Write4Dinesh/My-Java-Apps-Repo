package com.sg.helloclickablespan;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by pravin.sham.kulkarni on 7/11/17.
 */

public class WFMRescueTextVIew extends android.support.v7.widget.AppCompatTextView {

    public WFMRescueTextVIew(Context context) {
        super(context);
        if (!isInEditMode())
            this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                    "Rescue-Regular.ttf"));
    }

    public WFMRescueTextVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                    "Rescue-Regular.ttf"));
    }

    public WFMRescueTextVIew(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        if (!isInEditMode())
            this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                    "Rescue-Regular.ttf"));
    }

}
