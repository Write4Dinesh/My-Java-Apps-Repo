package com.example;

import java.util.ArrayList;

/**
 * Created by dinesh.k.masthaiah on 3/5/2017.
 */

public class SGHttpRequest {
    private ArrayList<Header> mHeaders;
    private String mUrl;
    private String mMethod;

    public SGHttpRequest() {
        mHeaders = new ArrayList<>();
    }



    public void addHeader(String key, String value) {
        mHeaders.add(new Header(key, value));
    }

    public String getMethod() {
        return mMethod;
    }

    public void setMethod(String mMethod) {
        this.mMethod = mMethod;
    }

    public String getUrl() {
        return mUrl;
    }

    public void addHeader(Header header) {
        mHeaders.add(header);
    }

    public ArrayList<Header> getHeaders() {
        return mHeaders;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public static class Header {
        String mKey;
        String mValue;

        public Header(String key, String value) {
            mKey = key;
            mValue = value;
        }
    }
}
