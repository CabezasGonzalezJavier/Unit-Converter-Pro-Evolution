package com.thedeveloperworldisyours.unitconverterpro.common.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by javierg on 19/09/16.
 */
public class StarjediButton extends Button {
    public StarjediButton(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public StarjediButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public StarjediButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Starjedi.ttf", context);
        setTypeface(customFont);
    }

}
