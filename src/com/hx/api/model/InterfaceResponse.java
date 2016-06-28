package com.hx.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InterfaceResponse
{
    public static final String SUCCESS = "00000";
    
    public static final String INNER_ERROR = "00001";
    
    @Expose
    @SerializedName("code")
    private String stateCode;
    
    @Expose
    private String message;
    
    @Expose
    private Object data;
    
    public String getStateCode()
    {
        return stateCode;
    }
    
    public void setStateCode(String stateCode)
    {
        this.stateCode = stateCode;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(Object data)
    {
        this.data = data;
    }
}
