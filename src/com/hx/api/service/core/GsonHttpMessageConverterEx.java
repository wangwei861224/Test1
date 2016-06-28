package com.hx.api.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.hx.api.service.gson.AnnotationExclusionStrategy;

@Service("gsonConverter")
public class GsonHttpMessageConverterEx extends GsonHttpMessageConverter
{
    @Autowired
    protected void setDefaultGson()
    {
        super.setGson(new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create());
    }
}
