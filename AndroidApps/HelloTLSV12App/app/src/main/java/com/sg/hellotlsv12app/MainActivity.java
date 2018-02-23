package com.sg.hellotlsv12app;

import android.app.Activity;
import android.os.Bundle;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void makeNetConnection() {
        // Tell the URLConnection to use a SocketFactory from our SSLContext
        URL url = null;
        try {
            url = new URL("https://certs.cac.washington.edu/CAtest/");

            HttpsURLConnection urlConnection =
                    (HttpsURLConnection) url.openConnection();
            urlConnection.setSSLSocketFactory(new CustomTLSSocketFactory());
            InputStream in = urlConnection.getInputStream();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
