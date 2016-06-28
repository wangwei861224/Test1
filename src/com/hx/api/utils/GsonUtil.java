package com.hx.api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil
{
    public static Gson getGson()
    {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
    
    public static Gson getGson(String pattern)
    {
        return new GsonBuilder().setDateFormat(pattern).create();
    }
    
    public static Gson getGsonExpose()
    {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
}
