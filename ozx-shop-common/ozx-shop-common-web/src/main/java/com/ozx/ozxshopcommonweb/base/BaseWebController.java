package com.ozx.ozxshopcommonweb.base;

import cn.hutool.core.util.StrUtil;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import com.ozx.ozxshopcommombasecore.constants.Constants;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: BaseWebController
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/24 11:42
 * @Version： 1.0
 **/
public class BaseWebController {




    public Boolean isSuccess(BasicResponse<?> basicResponse) {
        if (basicResponse == null) {
            return false;
        } else if (basicResponse.getCode().equals(Constants.HTTP_RES_CODE_500)) {
            return false;
        }
        return true;
    }

    public String getwebBrowserInfo(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();

        Version version = browser.getVersion(request.getHeader("User-Agent"));

        String info = browser.getName() + "/" + version.getVersion();
        return info;
    }

    protected <T> BasicResponse<T> message(Integer code,String message,T data){
        BasicResponse<T> response = new BasicResponse<>();
        response.setCode(code);
        response.setMsg(message);
        if (data != null) {
            response.setData(data);
        }
        return response;
    }

    protected <T> BasicResponse<T> success(){
        return message(Constants.HTTP_RES_CODE_200,Constants.HTTP_RES_CODE_200_VALUE,null);
    }

    protected <T> BasicResponse<T> success(T data){
        return message(Constants.HTTP_RES_CODE_200,Constants.HTTP_RES_CODE_200_VALUE,data);
    }

    protected <T> BasicResponse<T> failure(){
        return message(Constants.HTTP_RES_CODE_500,Constants.HTTP_RES_CODE_500_VALUE,null);
    }
    protected <T> BasicResponse<T> failure(T data){
        return message(Constants.HTTP_RES_CODE_500,Constants.HTTP_RES_CODE_500_VALUE,data);
    }
}
