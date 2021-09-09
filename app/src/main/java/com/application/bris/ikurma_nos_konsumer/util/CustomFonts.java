package com.application.bris.ikurma_nos_konsumer.util;

import android.content.Context;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Credit Vale
 * Create by idong
 */

public class CustomFonts
{
    private Typeface typeface;

    public CustomFonts(Typeface typeface)
    {
        this.typeface = typeface;
    }

    public CustomFonts(Context context, int resources)
    {
        typeface = ResourcesCompat.getFont(context, resources);
    }

    public void replaceFonts(ViewGroup viewTree)
    {
        View child;
        for(int i = 0; i < viewTree.getChildCount(); ++i)
        {
            child = viewTree.getChildAt(i);
            if(child instanceof ViewGroup)
            {
                // recursive call
                replaceFonts((ViewGroup)child);
            }
            else if(child instanceof TextView)
            {
                // base case
                ((TextView) child).setTypeface(typeface);
            }
        }
    }
}
