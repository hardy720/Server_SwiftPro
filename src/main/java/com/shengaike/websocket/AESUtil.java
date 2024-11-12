package com.shengaike.websocket;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final String KEY = "1234567890abcdef";
    private static final int IV_LENGTH = 12; // GCM模式下，IV通常为12字节
    private static final int TAG_LENGTH = 16; // GCM模式下，标签长度通常为16字节

    // 解密.
    public static String decrypt(String encryptedValue) throws Exception {
        byte[] combined = Base64.getDecoder().decode(encryptedValue);
        byte[] iv = new byte[IV_LENGTH];
        byte[] encrypted = new byte[combined.length - IV_LENGTH - TAG_LENGTH];
        byte[] tag = new byte[TAG_LENGTH];
        System.arraycopy(combined, 0, iv, 0, iv.length);
        System.arraycopy(combined, iv.length, encrypted, 0, encrypted.length);
        System.arraycopy(combined, iv.length + encrypted.length, tag, 0, tag.length);

        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv); // 128位标签长度

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, gcmParameterSpec);

        byte[] combinedEncryptedWithTag = new byte[encrypted.length + tag.length];
        System.arraycopy(encrypted, 0, combinedEncryptedWithTag, 0, encrypted.length);
        System.arraycopy(tag, 0, combinedEncryptedWithTag, encrypted.length, tag.length);

        byte[] original = cipher.doFinal(combinedEncryptedWithTag);
        return new String(original);
    }

    // 加密.
    public static String encrypt(String value) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");

        // 生成随机IV
        byte[] iv = new byte[IV_LENGTH];
        new java.security.SecureRandom().nextBytes(iv);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv); // 128位标签长度

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);

        byte[] encrypted = cipher.doFinal(value.getBytes());
        byte[] combined = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
        return Base64.getEncoder().encodeToString(combined);
    }
}