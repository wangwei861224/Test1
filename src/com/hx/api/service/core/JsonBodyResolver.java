package com.hx.api.service.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.GsonBuilder;

@Component("jsonBodyResolver")
public class JsonBodyResolver implements IRequestResolver
{
    private static Logger log = LoggerFactory.getLogger(JsonBodyResolver.class);
    
    @Override
    public <T> T resolve(HttpServletRequest request, Class<T> clazz) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        
        String line;
        StringBuffer buffer = new StringBuffer(256);
        
        while (null != (line = reader.readLine()))
        {
            buffer.append(line);
        }
        
        String body = buffer.toString();
        
        if (log.isDebugEnabled())
        {
            log.debug("Request body: {}", body);
        }
        
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(body, clazz);
    }
    
    @Override
    public <T> T resolve(String body, Class<T> clazz) throws IOException
    {
        // TODO Auto-generated method stub
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(body, clazz);
    }
}
