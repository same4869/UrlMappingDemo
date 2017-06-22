package com.xun.urlmappingdemo;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by xunwang on 2017/6/20.
 */

public class MappingUtil {
    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 用于建立十六进制字符的输出的大写字符数组
     */
    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private static final String ENCRYPT_FILE_NAME = "app";
    private static final String ENCRYPT_FILE_AIXUECOMM = "aixuecomm";
    private static final String ENCRYPT_FILE_HOMEWORK = "homework";
    private static final String ENCRYPT_FILE_PEN = "pen";

    private static Context mContext;

    private static JSONObject readOnlyData = new JSONObject();
    private static JSONObject appData;

    private static String[] initModuleMappingStrings = {ENCRYPT_FILE_NAME};

    public static void init(Context context) {
        mContext = context;
        initModuleEncrypt();
    }

    public static void initModuleEncrypt() {
        for (int i = 0; i < initModuleMappingStrings.length; i++) {
            initEncrypt(initModuleMappingStrings[i]);
        }
        Log.d("kkkkkkkk", "readOnlyData --> " + readOnlyData);
    }

    public static String getMappingValue(String key) {
        if (readOnlyData != null) {
            try {
                return readOnlyData.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }


    protected static String encodeHexStr(byte[] data, char[] toDigits) {
        return new String(encodeHex(data, toDigits));
    }

    public static String encodeHexStr(byte[] data) {
        return encodeHexStr(data, true);
    }

    public static String encodeHexStr(byte[] data, boolean toLowerCase) {
        return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = str.getBytes();
        return byteArray;
    }

    private static void initEncrypt(String moduleName) {
        byte[] encryptByte = getFromAssets(mContext, moduleName + ".dat");
        if (encryptByte == null) {
            return;
        }
//        byte[] decryptByte = EncryptUtil.soDecryptValue(encryptByte);

        AesUtil des = null;// 自定义密钥
        String originStr =  "1234567890abc";
        String key = "key=c09be5a0916c73fb";
        String key2 = key.substring(key.indexOf('=') + 1, key.length());
        try {
            des = new AesUtil(key2);
            byte[] temp2 = des.encrypt(strToByteArray(originStr));
            Log.d("kkkkkkkk", "temp2 --> " + new String(temp2));
            des = new AesUtil(key2);
            String temp = encodeHexStr(encryptByte);
            Log.d("kkkkkkkk", "temp --> " + temp + " key2 --> " + key2);
            String temp3 = encodeHexStr(temp2);
            String temp4 = encodeHexStr(encryptByte);
            String sss = des.decrypt(temp4);
            Log.d("kkkkkkkk", "temp3 --> " + temp3 + " sss --> " + new String(sss));
        } catch (Exception e) {
            Log.e("kkkkkkkk", e.getMessage());
            e.printStackTrace();
        }
//        if (decryptByte == null) {
//            return;
//        }
//        String decryptStr = new String(decryptByte);

//        try {
//            appData = new JSONObject(decryptStr);
//            setupReadOnlyData(appData);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private static void setupReadOnlyData(JSONObject data) {
        if (data == null) {
            return;
        }
        Iterator<String> sIterator = data.keys();
        while (sIterator.hasNext()) {
            try {
                String key = sIterator.next();
                String value = data.getString(key);
                readOnlyData.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isFileInAssets(Context context, String fileName) {
        try {
            String[] listName = context.getAssets().list("");
            for (int i = 0; i < listName.length; i++) {
                if (fileName.endsWith(listName[i])) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static byte[] getFromAssets(Context context, String fileName) {
        if (context == null || fileName == null) {
            return null;
        }

        if (!isFileInAssets(context, fileName)) {
            return null;
        }

        byte[] dataBuffer = null;
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(fileName);
            dataBuffer = new byte[inputStream.available()];

            inputStream.read(dataBuffer);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataBuffer;
    }
}
