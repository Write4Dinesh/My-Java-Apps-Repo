package com.sg.androidkeystoreexample;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

class DeCryptor {

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    private KeyStore keyStore;

    DeCryptor() throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
            IOException {
        initKeyStore();
    }

    private void initKeyStore() throws KeyStoreException, CertificateException,
            NoSuchAlgorithmException, IOException {
        keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);
    }

    String decryptData(final String alias, final byte[] encryptedData)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        byte[] encrypt = extractActualData(encryptedData);
        final GCMParameterSpec spec = new GCMParameterSpec(128, extractIV(encryptedData));
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(alias), spec);

        return new String(cipher.doFinal(encrypt), "UTF-8");
    }

    private byte[] extractActualData(byte[] encryptedData) {
        byte[] encrypt = new byte[encryptedData.length - MainActivity.IV_SIZE];
        System.arraycopy(encryptedData, 0, encrypt, 0, encrypt.length);
        byte[] encryptionIv = new byte[MainActivity.IV_SIZE];
        System.arraycopy(encryptedData, encrypt.length, encryptionIv, 0, MainActivity.IV_SIZE);
        return encrypt;
    }

    private byte[] extractIV(byte[] encryptedData) {
        byte[] encrypt = new byte[encryptedData.length - MainActivity.IV_SIZE];
        System.arraycopy(encryptedData, 0, encrypt, 0, encrypt.length);
        byte[] encryptionIv = new byte[MainActivity.IV_SIZE];
        System.arraycopy(encryptedData, encrypt.length, encryptionIv, 0, MainActivity.IV_SIZE);
        return encryptionIv;
    }

    private SecretKey getSecretKey(final String alias) throws NoSuchAlgorithmException,
            UnrecoverableEntryException, KeyStoreException {
        return ((KeyStore.SecretKeyEntry) keyStore.getEntry(alias, null)).getSecretKey();
    }
}