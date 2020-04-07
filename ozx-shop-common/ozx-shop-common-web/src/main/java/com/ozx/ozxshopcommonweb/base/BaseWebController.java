package com.ozx.ozxshopcommonweb.base;

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

    private static final String ERROR_FTL = "500.ftl";

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

    public void setErrorMsg(Model model,String errMsg){
        model.addAttribute("error",errMsg);
    }
}
