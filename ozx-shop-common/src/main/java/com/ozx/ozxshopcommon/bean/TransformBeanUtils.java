package com.ozx.ozxshopcommon.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TransformBeanUtils
 * @Description: Dto、Do转换工具类
 * @Author ou.zhenxing
 * @Date 2020/3/4 14:19
 * @Version： 1.0
 **/
@Component
@Slf4j
public class TransformBeanUtils<Dto,Do> {

    /**
     * @Description: Dto转换为Do
     * @Author ou.zhenxing
     * @Date   2020/3/4 15:18
     * @Param
     * @return
     **/
    public static <Do> Do dtoTransformDO(Object dtoEntity,Class<Do> doClass){
        if (dtoEntity == null) {
            return null;
        }
        if (doClass == null) {
            return null;
        }
        try {
            Do newInstance = doClass.newInstance();
            BeanUtils.copyProperties(dtoEntity,newInstance);
            return newInstance;
        } catch (Exception e) {
            log.info("Dto转换为Do失败：原因是{[]},",e.getMessage());
            return null;
        }
    }

    public static <Dto> Dto doTransformDto(Object doEntity,Class<Dto> dtoClass){
        if (doEntity == null) {
            return null;
        }
        if (dtoClass == null) {
            return null;
        }
        try {
            Dto newInstance = dtoClass.newInstance();
            BeanUtils.copyProperties(doEntity,newInstance);
            return newInstance;
        } catch (Exception e) {
            log.info("Do转换为Dto失败，原因是{[]}",e.getMessage());
            return null;
        }
    }

}
