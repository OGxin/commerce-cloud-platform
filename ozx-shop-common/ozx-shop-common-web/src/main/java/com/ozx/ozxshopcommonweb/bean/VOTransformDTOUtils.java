package com.ozx.ozxshopcommonweb.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName: VOTransformDTOUtils
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/24 17:20
 * @Version： 1.0
 **/
@Component
@Slf4j
public class VOTransformDTOUtils<Vo,Dto> {

    public static <Dto> Dto VoTransformDto(Object voEntity,Class<Dto> dtoClass){
        if (voEntity == null) {
            return null;
        }
        if (dtoClass == null) {
            return null;
        }
        try {
            Dto dto = dtoClass.newInstance();
            BeanUtils.copyProperties(voEntity,dto);
            return dto;
        } catch (Exception e) {
            log.info("vo转换为dto失败：原因：[{}]",e.getMessage());
            return null;
        }
    }
}
