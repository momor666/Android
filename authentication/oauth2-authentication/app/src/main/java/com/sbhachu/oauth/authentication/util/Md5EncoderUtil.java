package com.sbhachu.oauth.authentication.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sbhachu on 21/11/2014.
 */
public class Md5EncoderUtil {
    public static final String MD5 = "MD5";

    public static String encode(String value) {
        MessageDigest messageDigest = getMessageDigest(MD5);
        byte[] digest;

        try {
            digest = messageDigest.digest(value.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not supported!");
        }

        return new String(Hex.encodeHex(digest));
    }

    private static MessageDigest getMessageDigest(String algorithm) throws IllegalArgumentException {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }
}
