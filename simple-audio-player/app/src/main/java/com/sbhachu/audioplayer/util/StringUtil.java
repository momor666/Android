package com.sbhachu.audioplayer.util;

/**
 * Created by sbhachu on 04/10/2014.
 */
public class StringUtil {

    public static String getTimeString(long millis) {
        StringBuffer buffer = new StringBuffer();

        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

        buffer.append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buffer.toString();
    }
}
