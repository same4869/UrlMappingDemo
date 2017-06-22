package com.xun.urlmappingdemo;

import android.os.Environment;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncryptMain {
    private JSONObject mainObject = new JSONObject();
    public String mSecretKey = "";
    public String mSrcFile = "";
    public String mOutputFile = "";

    public void start()
            throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(this.mSrcFile)));
        String line = null;

        Pattern pattern = Pattern.compile("^[^=]*=[ ]*\"([^\"].*?)\".*?//[ ]*@encrypt.*?\"([^\"].*?)\"");
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                log("find entry: " + matcher.group(1) + " = " + matcher.group(2));
                if (this.mainObject.optString(matcher.group(1), null) != null) {
                    log("Find duplicate entry: " + matcher.group(1) + ", fatal to die! Stop processing");
                    System.exit(-1);
                }
                this.mainObject.put(matcher.group(1), matcher.group(2));
            }
        }
        reader.close();

        log("collect message: " + this.mainObject.toString().substring(0, 48) + "....");

        AesUtil desUtils = new AesUtil(this.mSecretKey);
        log(mSecretKey);

        byte[] encryptData = desUtils.encrypt(this.mainObject.toString().getBytes("UTF-8"));

        String encryptBody = new String(encryptData);

        log("encryptBody: " + encryptBody.substring(0, 48) + "...");

        FileOutputStream outputStream = new FileOutputStream(this.mOutputFile);

        outputStream.write(encryptData);
        outputStream.close();
    }

    private void log(String msg) {
        Log.d("kkkkkkkk", msg);
    }

    private static String convertArg(String arg) {
        return arg.substring(arg.indexOf('=') + 1, arg.length());
    }

    public void startEncrypt() {
        mSrcFile = Environment.getExternalStorageDirectory().getPath() + "/SoMapping.java";
        mOutputFile = Environment.getExternalStorageDirectory().getPath() + "/abc.dat";
        mSecretKey = "c09be5a0916c73fa";
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
            throws Exception {
        EncryptMain encryptMain = new EncryptMain();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("in=")) {
                encryptMain.mSrcFile = convertArg(arg);
            } else if (arg.startsWith("out=")) {
                encryptMain.mOutputFile = convertArg(arg);
            } else if (arg.startsWith("key=")) {
                encryptMain.mSecretKey = convertArg(arg);
            }
        }
        encryptMain.start();
    }
}
