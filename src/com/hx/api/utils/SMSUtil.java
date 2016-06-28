package com.hx.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

/**
 * Created by apple on 16/4/30.
 */
public class SMSUtil {
	
	public static final String SMS_URL = "http://112.74.76.186:8030/service/httpService/httpInterface.do?method=sendMsg";
	public static final String SMS_USERNAME = "JSM4099501";
	public static final String SMS_PASSWORD = "cd2juro4";
	public static final String SMS_KEY = "tmkqt7x6fjv2";
    public static String sendShortMsg(String mobile, String content, String tempid) throws IOException {
        StringBuffer sb = new StringBuffer(SMS_URL);
        sb.append("&username=").append(SMS_USERNAME);
        sb.append("&password=").append(SMS_PASSWORD);
        sb.append("&veryCode=").append(SMS_KEY);
        sb.append("&msgtype=2");
//        sb.append("&msgtype=1")
        sb.append("&tempid=").append(tempid);
        sb.append("&mobile=" + mobile);
        sb.append("&content=" + URLEncoder.encode(content, "UTF-8"));
        sb.append("&code=utf-8");

//        PropertiesUtil.close()

        URL url = new URL(sb.toString());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        InputStream is = url.openStream();

        return convertStreamToString(is);
    }

    private static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        boolean size = false;

        try {
            int size1;
            try {
                while((size1 = is.read(bytes)) > 0) {
                    String e = new String(bytes, 0, size1, "UTF-8");
                    sb1.append(e);
                }
            } catch (IOException var13) {
                var13.printStackTrace();
            }
        } finally {
            try {
                is.close();
            } catch (IOException var12) {
                var12.printStackTrace();
            }

        }

        return sb1.toString();
    }

    static String getValiCode() {
        Random random = new Random();
        int x = random.nextInt(899999);
        x += 100000;
        return String.valueOf(x);
    }
}
