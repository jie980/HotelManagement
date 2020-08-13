package com.jie.springboot_mybatis2.Tool;


import java.util.Random;

public class RandomStringUtil {

    public static String getRandString(int length)
    {
        String charList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String rev = "";
        Random f = new Random();
        for(int i=0;i<length;i++)
        {
            rev += charList.charAt(Math.abs(f.nextInt())%charList.length());
        }
        return rev;
    }

}
