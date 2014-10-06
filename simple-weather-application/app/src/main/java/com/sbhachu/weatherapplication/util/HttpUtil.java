package com.sbhachu.weatherapplication.util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
    private static final String TAG = HttpUtil.class.getSimpleName();

    public String getWeatherData(String url) throws IOException {
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);

            connection.connect();

            int status = connection.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    return readConnectionInputStream(connection.getInputStream());
                case 404:
                    Logger.e(TAG, "Weather Service returned a 404 Error");
                default:
                    break;
            }
        } catch (MalformedURLException malformedURLException) {
            Log.e(TAG, malformedURLException.getMessage());
        }

        return null;
    }

    public String readConnectionInputStream(InputStream inputStream) throws IOException {
        BufferedReader br = null;
        try {
            InputStream cleanInputStream = checkForUtf8BOMAndDiscardIfAny(inputStream);

            br = new BufferedReader(new InputStreamReader(cleanInputStream));
            StringBuilder sb = new StringBuilder(cleanInputStream.available());
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (IOException ioException) {
            Logger.e("IOException", ioException.getMessage());
        } finally {
            br.close();
        }
        return null;
    }

    public InputStream checkForUtf8BOMAndDiscardIfAny(InputStream inputStream) throws IOException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(new BufferedInputStream(inputStream), 3);
        byte[] bom = new byte[3];
        if (pushbackInputStream.read(bom) != -1) {
            if (!(bom[0] == (byte) 0xEF && bom[1] == (byte) 0xBB && bom[2] == (byte) 0xBF)) {
                pushbackInputStream.unread(bom);
            }
        }
        return pushbackInputStream;
    }
}
