package com.sbhachu.bbcnewsreader.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by sbhachu on 15/10/2014.
 */
public class TypefaceCache {

    private static final Hashtable<String, Typeface> CACHE = new Hashtable<String, Typeface>();

    private static final String ROBOTO_REGULAR = "fonts/Roboto/Roboto-Regular.ttf";
    private static final String ROBOTO_ITALIC = "fonts/Roboto/Roboto-Italic.ttf";
    private static final String ROBOTO_LIGHT = "fonts/Roboto/Roboto-Light.ttf";
    private static final String ROBOTO_LIGHT_ITALIC = "fonts/Roboto/Roboto-LightItalic.ttf";
    private static final String ROBOTO_THIN = "fonts/Roboto/Roboto-Thin.ttf";
    private static final String ROBOTO_THIN_ITALIC = "fonts/Roboto/Roboto-ThinItalic.ttf";
    private static final String ROBOTO_MEDIUM = "fonts/Roboto/Roboto-Medium.ttf";
    private static final String ROBOTO_MEDIUM_ITALIC = "fonts/Roboto/Roboto-MediumItalic.ttf";
    private static final String ROBOTO_BOLD = "fonts/Roboto/Roboto-Bold.ttf";
    private static final String ROBOTO_BOLD_ITALIC = "fonts/Roboto/Roboto-BoldItalic.ttf";
    private static final String ROBOTO_BLACK = "fonts/Roboto/Roboto-Black.ttf";
    private static final String ROBOTO_BLACK_ITALIC = "fonts/Roboto/Roboto-BlackItalic.ttf";

    private static final String ROBOTO_CONDENSED_REGULAR = "fonts/RobotoCondensed/RobotoCondensed-Regular.ttf";
    private static final String ROBOTO_CONDENSED_ITALIC = "fonts/RobotoCondensed/RobotoCondensed-Italic.ttf";
    private static final String ROBOTO_CONDENSED_LIGHT = "fonts/RobotoCondensed/RobotoCondensed-Light.ttf";
    private static final String ROBOTO_CONDENSED_LIGHT_ITALIC = "fonts/RobotoCondensed/RobotoCondensed-LightItalic.ttf";
    private static final String ROBOTO_CONDENSED_BOLD = "fonts/RobotoCondensed/RobotoCondensed-Bold.ttf";
    private static final String ROBOTO_CONDENSED_BOLD_ITALIC = "fonts/RobotoCondensed/RobotoCondensed-BoldItalic.ttf";

    public static Typeface get(AssetManager manager, int typefaceCode) {
        synchronized (CACHE) {
            String typeface = getTypeface(typefaceCode);

            if (!CACHE.containsKey(typeface)) {
                Typeface t = Typeface.createFromAsset(manager, typeface);
                CACHE.put(typeface, t);
            }

            return CACHE.get(typeface);
        }
    }

    private static String getTypeface(int typefaceCode) {
        switch (typefaceCode) {
            case 0:
                return ROBOTO_REGULAR;
            case 1:
                return ROBOTO_ITALIC;
            case 2:
                return ROBOTO_LIGHT;
            case 3:
                return ROBOTO_LIGHT_ITALIC;
            case 4:
                return ROBOTO_THIN;
            case 5:
                return ROBOTO_THIN_ITALIC;
            case 6:
                return ROBOTO_MEDIUM;
            case 7:
                return ROBOTO_MEDIUM_ITALIC;
            case 8:
                return ROBOTO_BOLD;
            case 9:
                return ROBOTO_BOLD_ITALIC;
            case 10:
                return ROBOTO_BLACK;
            case 11:
                return ROBOTO_BLACK_ITALIC;
            case 12:
                return ROBOTO_CONDENSED_REGULAR;
            case 13:
                return ROBOTO_CONDENSED_ITALIC;
            case 14:
                return ROBOTO_CONDENSED_LIGHT;
            case 15:
                return ROBOTO_CONDENSED_LIGHT_ITALIC;
            case 16:
                return ROBOTO_CONDENSED_BOLD;
            case 17:
                return ROBOTO_CONDENSED_BOLD_ITALIC;
            default:
                return null;
        }
    }
}
