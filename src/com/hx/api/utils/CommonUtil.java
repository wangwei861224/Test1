package com.hx.api.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.util.StringUtils;

public class CommonUtil
{
    public static String escapeSQLLike(String likeStr)
    {
        String str = StringUtils.replace(likeStr, "_", "\\_");
        str = StringUtils.replace(str, "%", "\\%");
        str = StringUtils.replace(str, "?", "\\?");
        str = StringUtils.replace(str, "*", "\\*");
        return str;
    }
    
    public static boolean compareString(String str1, String str2)
    {
        if (str1 == null && str2 == null)
            return true;
        
        if ((str1 == null || str2 == null))
            return false;
        return str1.equals(str2);
    }
    
    public static Date stringToDate(String dateString) throws ParseException
    {
        SimpleDateFormat fullDft = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat midDft = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat shortDft = new SimpleDateFormat("yyyy");
        if (StringUtils.isEmpty(dateString))
        {
            return null;
        }
        
        if (dateString.length() > 7)
        {
            return fullDft.parse(dateString);
        }
        else
        {
            if (dateString.length() > 5)
            {
                return midDft.parse(dateString);
            }
            else
            {
                return shortDft.parse(dateString);
            }
        }
    }
    
    /**
	 * 获得6位随机数
	 * @return
	 */
	public static String getValiCode()
	{
		Random random = new Random();

		int x = random.nextInt(899999);

		x = x+100000;
		return x+"";
	}
	
	/**
	 * 生成token
	 * @return
	 */
	public static String generateToken() {
        Random random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
	
	/**
	 * 生成上传文件的新文件名
	 * @param fileName
	 * @return
	 */
	public static String getUUIDName(String fileName) {
		String[] split = fileName.split("\\.");
		String extendFile = "." + split[split.length - 1].toLowerCase();
		return java.util.UUID.randomUUID().toString() + extendFile;
	}

}
