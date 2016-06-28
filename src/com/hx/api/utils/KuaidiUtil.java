package com.hx.api.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hx.api.common.utils.MD5;
import com.hx.api.model.KuaidiParmModel;

public class KuaidiUtil {
	private static String KD_KEY = "MZezaZjZ2319";
	private static String KD_CUSTOMER = "FB8B12D1D1B21689CDD1EB06D42AC098";
	private static String KD_URL = "http://poll.kuaidi100.com/poll/query.do";

	public static String kdQuery(String kdCom, String kdNu) {
		String resultJson = "";
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		try
        {
			KuaidiParmModel parmModel = new KuaidiParmModel();
			parmModel.setCom(kdCom);
			parmModel.setNum(kdNu);
			String param = gson.toJson(parmModel);
			
            HashMap<String, String> parmMap = new HashMap<>();
            String sign = MD5.MD5Decode(param+KD_KEY+KD_CUSTOMER);
            parmMap.put("customer", KD_CUSTOMER);
            parmMap.put("sign", sign);
            parmMap.put("param", param);
            resultJson = post(KD_URL, parmMap);
            
        }
		catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
	}
	
	
	public static String post(String url, Map<String, String> paramMap) {
        HttpURLConnection conn = null;
        try {
            URL murl = new URL(url);
            conn = (HttpURLConnection)murl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setDoOutput(true);
//            conn.setRequestProperty("content-type", "charset=utf-8")
            String data = "";

            for (String k : paramMap.keySet()) {
                String v = paramMap.get(k);
                String key = URLEncoder.encode(k, "UTF-8");
                String value = URLEncoder.encode(v, "UTF-8");
                data += "&"+key+"="+value+"";
            }

            data = data.substring(1);
//            data = URLEncoder.encode(data)

            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();

            if (responseCode < 500) {
                InputStream is = conn.getInputStream();
                String state = getStringFromInputStream(is);
                return state;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null) 
            	conn.disconnect();
        }

        return "";
    }
	
	public static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;

        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString("utf-8");
        os.close();

        return state;
    }
	
	
}
