package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by dinesh.k.masthaiah on 3/5/2017.
 */

public class TestMain {

    public static void main(String[] args) {
        //makeHttpRequest();
        SGHttpRequest request = new SGHttpRequest();
        request.addHeader("Host", "stackoverflow.com");
        request.setMethod(SGHttpClient.HTTP_METHOD_GET);
        SGHttpClient client = new SGHttpClient();
        client.execute("stackoverflow.com", request);
    }

    private static void makeHttpRequest() {
        try {
            Socket socket = new Socket(InetAddress.getByName("stackoverflow.com"), 80);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("GET / HTTP/1.1");
            pw.println("Host:stackoverflow.com");
            pw.println();
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String t;
            while ((t = br.readLine()) != null) {
                System.out.println(t);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

