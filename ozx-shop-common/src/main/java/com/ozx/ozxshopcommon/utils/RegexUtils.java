package com.ozx.ozxshopcommon.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: RegexUtil
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/4 11:15
 * @Version： 1.0
 **/
@Component
public class RegexUtils {

    private Pattern pattern;

    /**
     * @Description: 验证邮箱
     * @Author ou.zhenxing
     * @Date   2020/3/4 11:19
     * @Param [email]
     * @return 验证成功返回true，否则返回false
     **/
    public static boolean checkEmail(String email){
        String regex="\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex,email);
    }

    public static boolean checkIdCard(String idCard){
        String regex="[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex,idCard);
    }

    public static boolean checkMobile(String mobile){
        String regex="^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        return Pattern.matches(regex,mobile);
    }

    public static boolean checkPhone(String phone){
        String regex="(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex,phone);
    }

    public static boolean checkDigit(String digit){
        String regex="\\-?[1-9]\\d+";
        return Pattern.matches(regex,digit);
    }

    public static  boolean checkDecimals(String decimals){
        String regex="\\-?[1-9]\\d+(\\.\\d+)?";
        return Pattern.matches(regex,decimals);
    }

    public static boolean checkBlankSpace(String blankSpace){
        String regex="\\s+";
        return Pattern.matches(regex,blankSpace);
    }

    public static boolean checkChinese(String chinese){
        String regex="^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex,chinese);
    }

    public static boolean checkBirthday(String birthday){
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex,birthday);
    }

    /**
     * @Description: 验证Url
     * @Author ou.zhenxing
     * @Date   2020/3/4 11:48
     * @Param
     * @return
     **/
    public static boolean checkUrl(String url){
        String regex="(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex,url);
    }
    /**
     * @Description: 获取网址 URL 的一级域
     * @Author ou.zhenxing
     * @Date   2020/3/4 11:47
     * @Param
     * @return
     **/
    public String checkDomain(String url){
        Pattern pattern = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        matcher.find();
        return matcher.group();
    }

    public static boolean checkPostCode(String postCode){
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postCode);
    }

    public static boolean checkIpAddress(String ipAddress){
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ipAddress);
    }

}
