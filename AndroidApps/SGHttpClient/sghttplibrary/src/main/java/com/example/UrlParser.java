package com.example;

import java.util.HashMap;

/**
 * Created by dinesh.k.masthaiah on 3/5/2017.
 */

public class UrlParser {
    private ParsedUrl mParsedUrl;

    UrlParser() {
        mParsedUrl = new ParsedUrl();
    }

    public ParsedUrl parseUrl(String url) {
        String path;

        if (url != null && !url.isEmpty()) {
            if (url.contains(SGHttpClient.PROTOCOL_HTTPS)) {
                mParsedUrl.setProtocol(SGHttpClient.PROTOCOL_HTTPS);
                url = url.substring(url.indexOf(SGHttpClient.PROTOCOL_HTTPS) + 3, url.length());
            } else if (url.contains(SGHttpClient.PROTOCOL_HTTP)) {
                mParsedUrl.setProtocol(SGHttpClient.PROTOCOL_HTTP);
                url = url.substring(url.indexOf(SGHttpClient.PROTOCOL_HTTPS), url.length());
                url = url.substring(url.indexOf(SGHttpClient.PROTOCOL_HTTP) + 3, url.length());
            }
            if (url != null && url.indexOf("/") != -1) {
                String host = url.substring(0, url.indexOf("/"));
                mParsedUrl.setHostName(host);
                url = url.substring(url.indexOf("/"), url.length());
                if (url != null && url.indexOf("?") != -1) {
                    path = url.substring(0, url.indexOf("?"));
                    mParsedUrl.setPath(path);
                    url = url.substring(url.indexOf("?"), url.length());
                    if (url != null && !url.isEmpty()) {
                        HashMap<String, String> params = new HashMap<>();
                        String[] keyValue = url.split("&");
                        if (keyValue != null && keyValue.length > 0) {
                            for (String s : keyValue) {
                                String[] map = s.split("=");
                                if (map != null && map.length == 2) {
                                    params.put(map[0], map[1]);
                                }
                            }
                        }
                        mParsedUrl.setParams(params);
                    }
                }
            } else {
                mParsedUrl.setHostName(url);
                return mParsedUrl;
            }


        }
        return mParsedUrl;
    }

    public static class ParsedUrl {
        private String mHostName;
        private String mPath;
        private String mProtocol;
        private HashMap<String, String> mParams;

        public ParsedUrl() {
            mParams = new HashMap<>();
        }

        public String getHostName() {
            return mHostName;
        }

        public String getPath() {
            return mPath;
        }

        public String getProtocol() {
            return mProtocol;
        }

        public HashMap<String, String> getParams() {
            return mParams;
        }

        public void setHostName(String mHostName) {
            this.mHostName = mHostName;
        }

        public void setPath(String mPath) {
            this.mPath = mPath;
        }

        public void setProtocol(String mProtocol) {
            this.mProtocol = mProtocol;
        }

        public void setParams(HashMap<String, String> mParams) {
            this.mParams = mParams;
        }
    }
}
