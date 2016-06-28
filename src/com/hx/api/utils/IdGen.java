package com.hx.api.utils;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author bmwm.cn
 * @version 2013-01-15
 */
@Service
@Lazy(false)
public class IdGen implements IdGenerator
{
    
    private static SecureRandom random = new SecureRandom();
    
    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * 使用SecureRandom随机生成Long. 
     */
    public static long randomLong()
    {
        return Math.abs(random.nextLong());
    }
    
    /**
     * 使用SecureRandom随机生成六位的数字字符串. 
     */
    public static String randomSixInvitationCode()
    {
        long sixLong = randomLong() % 1000000;
        return String.valueOf(sixLong);
    }
    
    /**
     * Activiti ID 生成
     */
    @Override
    public String getNextId()
    {
        return IdGen.uuid();
    }
    
    public static void main(String[] args)
    {
        System.out.println(IdGen.uuid());
        System.out.println(IdGen.uuid().length());
        System.out.println(new IdGen().getNextId());
    }
    
}
