package com.sg.hellodialogfragment;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 
 * @author ssadan
 * 
 */
public class WFMLegalGothicRegularTextView extends TextView {

	public WFMLegalGothicRegularTextView(Context context) {
		super(context);
        if(!isInEditMode())
		    this.setTypeface(Typeface.createFromAsset(context.getAssets(),
				"LeagueGothic-Regular.otf"));

	}

	public WFMLegalGothicRegularTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
        if(!isInEditMode())
            this.setTypeface(Typeface.createFromAsset(context.getAssets(),
				"LeagueGothic-Regular.otf"));
	}

	public WFMLegalGothicRegularTextView(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
        if(!isInEditMode())
            this.setTypeface(Typeface.createFromAsset(context.getAssets(),
				"LeagueGothic-Regular.otf"));
	}

}