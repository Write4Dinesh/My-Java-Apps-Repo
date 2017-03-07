package com.example;

import java.util.ArrayList;

/**
 * Created by dinesh.k.masthaiah on 3/5/2017.
 */

public class SGHttpResponse {
    private ArrayList<String> mRaw;
    private ArrayList<Header> mHeaders;
    private String mBody;

    public SGHttpResponse() {
        mHeaders = new ArrayList<>();
    }

    public void setRaw(ArrayList<String> raw) {
        mRaw = raw;
        parseResponse();
    }

    private void parseResponse() {
        String[] values;
        if (mRaw != null && !mRaw.isEmpty()) {
            for (String line : mRaw) {
               System.out.println(line);
                values = line.split(":");
                mHeaders.add(new Header(values[0], values[1]));
            }
        }
    }

    public static class Header {
        private String mKey;
        private String mValue;

        public Header(String key, String value) {
            mKey = key;
            mValue = value;
        }

        public String getKey() {
            return mKey;
        }

        public void setKey(String mKey) {
            this.mKey = mKey;
        }

        public String getValue() {
            return mValue;
        }

        public void setValue(String mValue) {
            this.mValue = mValue;
        }
    }
}
