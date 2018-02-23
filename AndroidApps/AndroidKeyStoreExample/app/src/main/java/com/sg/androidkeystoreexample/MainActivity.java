package com.sg.androidkeystoreexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * _____                       _        _    _
 * / ____|                     | |      | |  | |
 * | (___   __ _ _ __ ___  _ __ | | ___  | |  | |___  __ _  __ _  ___
 * \___ \ / _` | '_ ` _ \| '_ \| |/ _ \ | |  | / __|/ _` |/ _` |/ _ \
 * ____) | (_| | | | | | | |_) | |  __/ | |__| \__ \ (_| | (_| |  __/
 * |_____/ \__,_|_| |_| |_| .__/|_|\___|  \____/|___/\__,_|\__, |\___|
 * | |                               __/ |
 * |_|                              |___/
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String SAMPLE_ALIAS = "MYALIAS";
    public static final int IV_SIZE = 12;

    Toolbar toolbar;

    EditText edTextToEncrypt;

    TextView tvEncryptedText;

    TextView tvDecryptedText;

    private EnCryptor encryptor;
    private DeCryptor decryptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setSupportActionBar(toolbar);
        edTextToEncrypt = findViewById(R.id.textfield_et);
        tvEncryptedText = findViewById(R.id.encrypted_txt_tv);
        tvDecryptedText = findViewById(R.id.decrypted_txt_tv);
        encryptor = new EnCryptor();

        try {
            decryptor = new DeCryptor();
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException |
                IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(final View view) {

        final int id = view.getId();

        switch (id) {
            case R.id.btn_encrypt:
                encryptText();
                break;
            case R.id.btn_decrypt:
                decryptText();
                break;
        }
    }

    private void decryptText() {
        try {
            tvDecryptedText.setText(decryptor
                    .decryptData(SAMPLE_ALIAS, encryptor.getEncryption()));
        } catch (UnrecoverableEntryException | NoSuchAlgorithmException |
                KeyStoreException | NoSuchPaddingException | NoSuchProviderException |
                IOException | InvalidKeyException e) {
            Log.e(TAG, "decryptData() called with: " + e.getMessage(), e);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    private void encryptText() {

        try {
            final byte[] encryptedText = encryptor
                    .encryptText(SAMPLE_ALIAS, edTextToEncrypt.getText().toString());
            final byte[] encryptedText2 =   encryptor.encryptText(SAMPLE_ALIAS,"My Name is Mr.INdia");
            String str = decryptor.decryptData(SAMPLE_ALIAS, encryptedText2);

            Log.d("MainActivity","str=" + str);
            tvEncryptedText.setText(Base64.encodeToString(encryptedText, Base64.DEFAULT));
        } catch (UnrecoverableEntryException | NoSuchAlgorithmException | NoSuchProviderException |
                KeyStoreException | IOException | NoSuchPaddingException | InvalidKeyException e) {
            Log.e(TAG, "onClick() called with: " + e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException | SignatureException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
testCrypto();
    }
    private void testCrypto(){
        try {
          byte[] encData =   encryptor.encryptText(SAMPLE_ALIAS,"TestData1");
            String str = decryptor.decryptData(SAMPLE_ALIAS,encData);
            Log.d("AESSample","decrypted data1=" + str);

            byte[] encData2 =   encryptor.encryptText(SAMPLE_ALIAS,"TestData2");
            String str2 = decryptor.decryptData(SAMPLE_ALIAS,encData2);
            Log.d("AESSample","decrypted data2=" + str2);
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
