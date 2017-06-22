package encrypt2.xun.com.encrypt2;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by xunwang on 2017/6/21.
 */

public class AesUtils {
    private static String strDefaultKey = "national";
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    public static String byteArr2HexStr(byte[] arrB)
            throws Exception {
        int iLen = arrB.length;

        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            while (intTmp < 0) {
                intTmp += 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn)
            throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[(i / 2)] = ((byte) Integer.parseInt(strTmp, 16));
        }
        return arrOut;
    }

    public AesUtils()
            throws Exception {
        this(strDefaultKey);
    }

    public AesUtils(String strKey)
            throws Exception {
        Key key = getKey(strKey.getBytes());

        this.encryptCipher = Cipher.getInstance("AES/ECB/NoPadding");
        this.encryptCipher.init(1, key);

        this.decryptCipher = Cipher.getInstance("AES/ECB/NoPadding");
        this.decryptCipher.init(2, key);
    }

    public byte[] encrypt(byte[] arrB)
            throws Exception {
        if (arrB.length % 16 != 0) {
            arrB = Arrays.copyOf(arrB, (arrB.length / 16 + 1) * 16);
        }
        return this.encryptCipher.doFinal(arrB);
    }

    public String encrypt(String strIn)
            throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    public byte[] decrypt(byte[] arrB)
            throws Exception {
        return this.decryptCipher.doFinal(arrB);
    }

    public String decrypt(String strIn)
            throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }

    private Key getKey(byte[] arrBTmp)
            throws Exception {
        byte[] arrB = new byte[16];
        for (int i = 0; (i < arrBTmp.length) && (i < arrB.length); i++) {
            arrB[i] = arrBTmp[i];
        }
        Key key = new SecretKeySpec(arrB, "AES");

        return key;
    }

    public static void main(String[] args) {
        try {
            String test = "123456789";
            AesUtils des = new AesUtils("qkjll5@2md3gs5Q@FDFqf");
            System.out.println("���������������������" + test);
            System.out.println("���������������������" + des.encrypt(test));
            System.out.println("���������������������" + des.decrypt(des.encrypt(test)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
