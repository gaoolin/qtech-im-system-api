package com.qtech.im.aa.utils;

import com.qtech.common.utils.StringUtils;
import com.qtech.im.aa.domain.template.AaListParamsStdTemplate;
import com.qtech.im.aa.domain.template.AaListParamsStdTemplateInfo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import static com.qtech.share.aa.constant.ComparisonConstants.PROPERTIES_TO_COMPARE;
import static com.qtech.share.aa.constant.ComparisonConstants.PROPERTIES_TO_COMPUTE;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/08 17:03:56
 * desc   :
 */

@Slf4j
public class ModelDetailConvertToModelInfo {

    public static AaListParamsStdTemplateInfo doConvert(AaListParamsStdTemplate aaListParamsStdTemplateDetail) {
        AtomicInteger listParamsCnt = new AtomicInteger();
        AtomicInteger itemParamsCnt = new AtomicInteger();
        if (aaListParamsStdTemplateDetail == null) {
            log.error("参数为空");
            return null;
        }

        PROPERTIES_TO_COMPARE.forEach(fieldName -> {
            try {
                // 首先，需要找到Field对象
                listItemParamsCnt(aaListParamsStdTemplateDetail, listParamsCnt, fieldName);

            } catch (Exception e) {
                log.error("计算list参数个数时出错:", e);
            }
        });

        PROPERTIES_TO_COMPUTE.forEach(fieldName -> {
            try {
                // 首先，需要找到Field对象
                listItemParamsCnt(aaListParamsStdTemplateDetail, itemParamsCnt, fieldName);
            } catch (Exception e) {
                log.error("计算item参数个数时出错:", e);
            }
        });

        AaListParamsStdTemplateInfo param = new AaListParamsStdTemplateInfo();
        param.setListParams(listParamsCnt.get());
        param.setItemParams(itemParamsCnt.get());
        param.setProdType(aaListParamsStdTemplateDetail.getProdType());
        param.setStatus(1);

        return param;
    }

    private static void listItemParamsCnt(AaListParamsStdTemplate aaListParamsStdTemplateDetail, AtomicInteger paramsCnt, String fieldName) throws NoSuchFieldException, IllegalAccessException {

        Field baseField = ReflectionUtils.getAllDeclaredFields(AaListParamsStdTemplate.class).stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElseThrow(() -> new NoSuchFieldException(String.format("注意AaListParamsStdModelDetail类的属性是否缺失，或者PROPERTIES_TO_COMPARE集合中的元素有冗余。Field ‘%s’ not found", fieldName)));

        if (baseField.getType().equals(String.class)) {
            baseField.setAccessible(true);
            String value = (String) baseField.get(aaListParamsStdTemplateDetail);
            if (StringUtils.isNotBlank(value)) {
                paramsCnt.getAndIncrement();
            }
        } else {
            paramsCnt.getAndIncrement();
        }
    }
}
