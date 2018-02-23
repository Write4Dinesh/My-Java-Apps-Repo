package com.sg.androidkeystoreexample;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.security.auth.x500.X500Principal;

import java.security.cert.CertificateException;

/**
 * Created by dinesh.k.masthaiah on 11/16/2017.
 */

public class AsymmetricCrypto {
    private Context mContext;
    final String AES_CIPHER = KeyProperties.KEY_ALGORITHM_AES + "/" +
            KeyProperties.BLOCK_MODE_GCM + "/" +
            KeyProperties.ENCRYPTION_PADDING_NONE;
    final String KEYSTORE_PROVIDER = "AndroidKeyStore";
    private KeyStore mStore;
    private static final int RSA_BIT_LENGTH = 124;
    private static final int AES_BIT_LENGTH = 124;
    // source: https://devliving.online/securely-store-preference-data-in-android/
    private static final String RSA_KEY_ALIAS = "";
    private static final String DEFAULT_CHARSET = "UTF-8";
    final String AES_CIPHER_COMPAT = KeyProperties.KEY_ALGORITHM_AES + "/" +
            KeyProperties.BLOCK_MODE_CBC + "/" +
            KeyProperties.ENCRYPTION_PADDING_PKCS7;
    private static final String BOUNCY_CASTLE_PROVIDER = "";
    private static final int GCM_TAG_LENGTH = 124;
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
    final String RSA_CIPHER = KeyProperties.KEY_ALGORITHM_RSA + "/" +
            KeyProperties.BLOCK_MODE_ECB + "/" +
            KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1;
    final String SSL_PROVIDER = "AndroidOpenSSL";
    private SecretKey aesKey;

    public AsymmetricCrypto(Context context) {
        mContext = context;

    }

    String getHashed(String text, String randomSalt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(randomSalt.getBytes(DEFAULT_CHARSET));
        byte[] result = digest.digest(text.getBytes(DEFAULT_CHARSET));

        return new String(result);
    }

    void loadKeyStore() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        mStore = KeyStore.getInstance(KEYSTORE_PROVIDER);
        mStore.load(null);
    }

    void generateRSAKeys(Context context) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException {
        if (!mStore.containsAlias(RSA_KEY_ALIAS)) {
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            end.add(Calendar.YEAR, 25);

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEYSTORE_PROVIDER);

            KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context)
                    .setAlias(RSA_KEY_ALIAS)
                    .setKeySize(RSA_BIT_LENGTH)
                    .setKeyType(KeyProperties.KEY_ALGORITHM_RSA)
                    .setEndDate(end.getTime())
                    .setStartDate(start.getTime())
                    .setSerialNumber(BigInteger.ONE)
                    .setSubject(new X500Principal("CN = Secured Preference Store, O = Devliving Online"))
                    .build();

            keyGen.initialize(spec);
            keyGen.generateKeyPair();
        }
    }


    void loadRSAKeys() throws KeyStoreException, UnrecoverableEntryException, NoSuchAlgorithmException {
        if (mStore.containsAlias(RSA_KEY_ALIAS) && mStore.entryInstanceOf(RSA_KEY_ALIAS, KeyStore.PrivateKeyEntry.class)) {
            KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry) mStore.getEntry(RSA_KEY_ALIAS, null);
            publicKey = (RSAPublicKey) entry.getCertificate().getPublicKey();
            privateKey = (RSAPrivateKey) entry.getPrivateKey();
        }
    }

    byte[] generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        keyGen.init(AES_BIT_LENGTH);
        SecretKey sKey = keyGen.generateKey();
        return sKey.getEncoded();
    }

    byte[] RSAEncrypt(byte[] bytes) throws KeyStoreException, UnrecoverableEntryException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance(RSA_CIPHER, SSL_PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
        cipherOutputStream.write(bytes);
        cipherOutputStream.close();

        return outputStream.toByteArray();
    }

  /*  private void onApiLevel23Symmetric() {
        KeyGenerator keyGen = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE_PROVIDER);

        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(AES_KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setCertificateSubject(new X500Principal("CN = Secured Preference Store, O = Devliving Online"))
                .setCertificateSerialNumber(BigInteger.ONE)
                .setKeySize(AES_BIT_LENGTH)
                .setKeyValidityEnd(end.getTime())
                .setKeyValidityStart(start.getTime())
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setRandomizedEncryptionRequired(false)
                .build();
        keyGen.init(spec);

        keyGen.generateKey();
    }*/

    byte[] decryptAESCompat(byte[] bytes, byte[] IV) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        Cipher c = Cipher.getInstance(AES_CIPHER_COMPAT, BOUNCY_CASTLE_PROVIDER);
        c.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(IV));
        return c.doFinal(bytes);
    }

    byte[] encryptAES(byte[] bytes, byte[] IV) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(AES_CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, new GCMParameterSpec(GCM_TAG_LENGTH, IV));
        return cipher.doFinal(bytes);
    }

    byte[] decryptAES(byte[] bytes, byte[] IV) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(AES_CIPHER);
        cipher.init(Cipher.DECRYPT_MODE, aesKey, new GCMParameterSpec(GCM_TAG_LENGTH, IV));
        return cipher.doFinal(bytes);
    }

    byte[] RSADecrypt(byte[] bytes) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance(RSA_CIPHER, SSL_PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(bytes), cipher);

        ArrayList<Byte> values = new ArrayList<>();
        int nextByte;
        while ((nextByte = cipherInputStream.read()) != -1) {
            values.add((byte) nextByte);
        }

        byte[] dbytes = new byte[values.size()];
        for (int i = 0; i < dbytes.length; i++) {
            dbytes[i] = values.get(i).byteValue();
        }

        cipherInputStream.close();
        return dbytes;
    }
}
