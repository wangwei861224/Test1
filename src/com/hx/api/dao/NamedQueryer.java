package com.hx.api.dao;

import java.util.ArrayList;
import java.util.List;

public class NamedQueryer
{
    private String hql;
    
    private String countHql;
    
    private List<String> names = new ArrayList<String>();
    
    private List<String> countNames;
    
    private List<Object> values = new ArrayList<Object>();
    
    private List<Object> countValues;
    
    public String getHql()
    {
        return hql;
    }
    
    public void setHql(String hql)
    {
        this.hql = hql;
    }
    
    public List<String> getNames()
    {
        return names;
    }
    
    public void setNames(List<String> names)
    {
        this.names = names;
    }
    
    public List<Object> getValues()
    {
        return values;
    }
    
    public void setValues(List<Object> values)
    {
        this.values = values;
    }
    
    public String getCountHql()
    {
        if (null != countHql)
        {
            return countHql;
        }
        
        String formattedHql = hql.toLowerCase().replace("fetch", "");
        int fromIndex = formattedHql.indexOf("from");
        int orderbyIndex = formattedHql.indexOf("order by");
        String header = formattedHql.substring(0, fromIndex);
        String body = -1 != orderbyIndex ? hql.substring(fromIndex, orderbyIndex) : hql.substring(fromIndex);
        header = header.contains("select") ? header.replace("select", "select count(") + ") " : "select count(*) ";
        return header + body;
    }
    
    public void setCountHql(String countHql)
    {
        this.countHql = countHql;
    }
    
    public List<String> getCountNames()
    {
        if (null != countNames)
        {
            return countNames;
        }
        
        return names;
    }
    
    public void setCountNames(List<String> countNames)
    {
        this.countNames = countNames;
    }
    
    public List<Object> getCountValues()
    {
        if (null != countValues)
        {
            return countValues;
        }
        
        return values;
    }
    
    public void setCountValues(List<Object> countValues)
    {
        this.countValues = countValues;
    }
}
