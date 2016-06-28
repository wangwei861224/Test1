package com.hx.api.service.core;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public interface IRequestResolver
{
    <T> T resolve(HttpServletRequest request, Class<T> clazz) throws IOException;
    
    <T> T resolve(String body, Class<T> clazz) throws IOException;
}
