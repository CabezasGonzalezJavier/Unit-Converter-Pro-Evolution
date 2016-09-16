package com.thedeveloperworldisyours.unitconverterpro.common.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by javierg on 16/09/16.
 */
public class Button_Lota_Regular extends Button {
    public Button_Lota_Regular(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public Button_Lota_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public Button_Lota_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Lato_Regular.ttf", context);
        setTypeface(customFont);
    }

}
