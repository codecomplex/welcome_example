package com.suwish.welcome.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.suwish.welcome.log.LL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author min.su on 2016/9/27.
 */
public final class AccountUtils {

    private static final String TAG = LL.makeLogTag(AccountUtils.class);

    private AccountUtils(){}

    public static boolean isPhoneNumber(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static void close(InputStream inputStream){
        if (inputStream == null) return;
        try {
            inputStream.close();
        }catch (Exception ignored){}
    }

    public static void close(OutputStream outputStream){
        if (outputStream == null) return;
        try {
            outputStream.close();
        }catch (Exception ignored){}
    }

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (Exception ignored) {
            LL.w(TAG, "", ignored);
            return "";
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            LL.w(TAG, "", e);
        }
        return sb.toString();
    }

    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }

    public static String getIMEICode(Context context){
        try {
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            return telephonyManager.getDeviceId();
        }catch (SecurityException e){
            LL.w(TAG, "", e);// 权限不足
            return "";
        }catch (Exception e){
            LL.w(TAG, "", e);
            return "";
        }
    }
}
