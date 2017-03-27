package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class SGHttpClient {
    public static final String PROTOCOL_HTTP = "http";
    public static final String PROTOCOL_HTTPS = "https";
    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";
    public static final int PORT = 80;
    private SGHttpRequest mRequest;
    private SGHttpResponse mResponse;
    private String mUrl;

    public SGHttpClient() {
        mResponse = new SGHttpResponse();
    }

    public SGHttpResponse execute(String url, SGHttpRequest request) {
        mRequest = request;
        mRequest.setUrl(url);
        mUrl = url;
        submitRequest();
        return mResponse;
    }

    private void submitRequest() {
        UrlParser.ParsedUrl parsedUrl = new UrlParser().parseUrl(mUrl);
        try {
            Socket socket = new Socket(InetAddress.getByName(parsedUrl.getHostName()), PORT);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(mRequest.getMethod() + " / HTTP/1.1");
            for (SGHttpRequest.Header header : mRequest.getHeaders()) {
                pw.println(header.mKey + ":" + header.mValue);
            }
            pw.println();
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
                System.out.println(line);
            }
            br.close();
           mResponse.setRaw(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
