package com.library.basemodule.util;





import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author qjr
 * @create 2019/7/30
 * @Describe
 */
public class RSAUtils {
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";//加密填充方式
    /**
     * RSA公钥加密
     * @param content 加密内容
     * @param rsaKey 公钥
     * @return 加密byte数组
     * HHHDDDFGDDDDDDDDDD
     */
    public static String encrypt(String content, String rsaKey)  {
        try {
            byte[] keyBytes = Base64.decode(rsaKey,Base64.DEFAULT);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeToString(cipher.doFinal(content.getBytes(StandardCharsets.UTF_8)),Base64.NO_WRAP);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return bytes2HexString(content.getBytes());
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     */
//    public static String encrypt(String str, String publicKey)  {
//        try {
//            byte[] decoded = Base64.decode(publicKey,Base64.DEFAULT);
//            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
//            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
//            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
//            return Base64.encodeToString(cipher.doFinal(str.getBytes()));
//        }catch (Exception e) {
//
//            e.printStackTrace();
//        }
//        return Base64.encodeBase64String(str.getBytes());
//    }

    private static final char HEX_DIGITS[] =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) return "";
        int len = bytes.length;
        if (len <= 0) return "";
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = HEX_DIGITS[bytes[i] >> 4 & 0x0f];
            ret[j++] = HEX_DIGITS[bytes[i] & 0x0f];
        }
        return new String(ret);
    }
}
