package com.sbhachu.bbcnewsreader.presentation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sbhachu.bbcnewsreader.R;
import com.sbhachu.bbcnewsreader.util.TypefaceCache;

/**
 * Created by sbhachu on 15/10/2014.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray styleAttributes = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
            int typefaceCode = styleAttributes.getInt(R.styleable.CustomTextView_fontStyle, -1);
            styleAttributes.recycle();

            if (isInEditMode()) {
                return;
            }

            Typeface typeface = TypefaceCache.get(context.getAssets(), typefaceCode);
            setTypeface(typeface);
        }
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
