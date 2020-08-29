package com.application.boilerplate.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.application.boilerplate.R;

import androidx.appcompat.widget.AppCompatTextView;


public class BaseTextView extends AppCompatTextView {

    private String customFont;

    public BaseTextView(Context context) {
        super(context);
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context, attrs);
    }

    public BaseTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        style(context, attrs);
    }

    private void style(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.BaseTextView);
        int cf = a.getInteger(R.styleable.BaseTextView_fontName, 0);
        int fontName;
        switch (cf)
        {
            case 1:
                fontName = R.string.Roboto_Regular;
                break;
            case 2:
                fontName = R.string.Roboto_Light;
                break;
            case 3:
                fontName = R.string.Roboto_Thin;
                break;
            case 4:
                fontName = R.string.Roboto_Bold;
                break;
            case 5:
                fontName = R.string.Roboto_Medium;
                break;
            default:
                fontName = R.string.Roboto_Regular;
                break;
        }

        customFont = getResources().getString(fontName);

        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/" + customFont + ".ttf");
        setTypeface(tf);
        a.recycle();
    }

}