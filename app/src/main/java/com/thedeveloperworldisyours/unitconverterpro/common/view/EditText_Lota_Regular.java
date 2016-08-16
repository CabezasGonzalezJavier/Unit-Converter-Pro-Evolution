package com.thedeveloperworldisyours.unitconverterpro.common.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by javierg on 16/08/16.
 */
public class EditText_Lota_Regular extends EditText {

    public EditText_Lota_Regular(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public EditText_Lota_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public EditText_Lota_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Lato_Regular.ttf", context);
        setTypeface(customFont);
    }

}
