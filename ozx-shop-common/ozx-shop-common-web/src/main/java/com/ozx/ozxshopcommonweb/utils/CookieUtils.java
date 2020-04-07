package com.ozx.ozxshopcommonweb.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @ClassName: CookieUtils
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/25 15:42
 * @Version： 1.0
 **/
@Component
@Slf4j
public class CookieUtils {

    /**
     * 获取Cookie的值，修改编码方式
     * @param request
     * @param cookieName
     * @param isDecoder
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName,boolean isDecoder){
        Cookie[] cookies = request.getCookies();
        if (cookieName == null || cookies == null) {
            return null;
        }
        String value = null;
        try {
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(cookieName)) {
                if (isDecoder) {
                        value = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }else{
                    value = cookies[i].getValue();
                }
                break;
            }
        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;

    }

    /**
     * 获取Cookie的值，不修改编码
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName){
        return getCookieValue(request,cookieName,false);
    }

    /**
     * 根据输入的编码方式修改，获取Cookie的值
     * @param request
     * @param cookieName
     * @param enc
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,String cookieName,String enc){
        Cookie[] cookies = request.getCookies();
        String value = null;
        try {
        for (int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName().equals(cookieName)){
                    value=URLDecoder.decode(cookies[i].getValue(),enc);
            }
            break;
        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取Cookie的域名
     * @param request
     * @return
     */
    public static final String getDomain(HttpServletRequest request){
        String domainName = null;
        String serverName = request.getRequestURL().toString();
        if (serverName == null && serverName.equals("")) {
            domainName = "";
        }else {
            final int index = serverName.lastIndexOf("/");
            serverName = serverName.substring(0, index);
            final String[] domains = serverName.split("\\.");
            int length = domains.length;
            if(length > 3){
                /*
                www.xxx.com.cn
                 */
                domainName="."+domains[length-3]+"."+domains[length-2]+"."+domains[length-1];
            }else if(length<=3 && length >1){
                domainName="."+domains[length-2]+"."+domains[length-1];
            }else{
                domainName=serverName;
            }
        }
        if (domainName!=null && domainName.indexOf(":")>0) {
            String[] split = domainName.split("\\:");
            domainName = split[0];
        }
        return domainName;
    }

    /**
     * 设置Cookie的值，并指定时间内cookie生效
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxAge cookie存活的最大秒数
     * @param enc
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,
                                          String cookieValue,int cookieMaxAge,String enc,boolean isDecode){
        try {
        if (cookieValue == null) {
            cookieValue = "";
        }
        if (enc != null) {
                cookieValue = URLDecoder.decode(cookieValue, enc);
        }
        if(isDecode){
            cookieValue = URLDecoder.decode(cookieValue,"UTF-8");
        }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if(cookieMaxAge > 0){
                cookie.setMaxAge(cookieMaxAge);
            }
            if(request != null){
                String domain = getDomain(request);
                if (!"localhost".equals(domain)) {
                    /**
                     * 实际发布需要设置cookie的域名
                     */
//                    cookie.setDomain(domain);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置cookie的值，指定cookie生效时间和编码方式
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxAge
     * @param enc
     */
    public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,
                                String cookieValue,int cookieMaxAge,String enc){
        doSetCookie(request,response,cookieName,cookieValue,cookieMaxAge,enc,false);
    }

    /**
     * 设置cookie的值，指定cookie生效时间和UTF-8编码方式
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxAge
     * @param isDecode
     */
    public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,
                                 String cookieValue,int cookieMaxAge,boolean isDecode){
        doSetCookie(request,response,cookieName,cookieValue,cookieMaxAge,null,true);
    }

    /**
     * 设置cookie的值，指定cookie UTF-8编码方式，不设置生效时间
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param isDecode
     */
    public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,
                                 String cookieValue,boolean isDecode){
        doSetCookie(request,response,cookieName,cookieValue,-1,null,true);
    }

    /**
     * 设置cookie的值，不指定生效时间和编码方式，即浏览器关闭，cookie失效
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,
                                 String cookieValue){
        doSetCookie(request,response,cookieName,cookieValue,-1,null,false);
    }
}
