package com.qtech.im.aa.utils;

import com.qtech.common.utils.StringUtils;
import com.qtech.im.aa.domain.AaListParamsStdModel;
import com.qtech.im.aa.domain.AaListParamsStdModelInfo;
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

    public static AaListParamsStdModelInfo doConvert(AaListParamsStdModel aaListParamsStdModelDetail) {
        AtomicInteger listParamsCnt = new AtomicInteger();
        AtomicInteger itemParamsCnt = new AtomicInteger();
        if (aaListParamsStdModelDetail == null) {
            log.error("参数为空");
            return null;
        }

        PROPERTIES_TO_COMPARE.forEach(fieldName -> {
            try {
                // 首先，需要找到Field对象
                listItemParamsCnt(aaListParamsStdModelDetail, listParamsCnt, fieldName);

            } catch (Exception e) {
                log.error("计算list参数个数时出错:", e);
            }
        });

        PROPERTIES_TO_COMPUTE.forEach(fieldName -> {
            try {
                // 首先，需要找到Field对象
                listItemParamsCnt(aaListParamsStdModelDetail, itemParamsCnt, fieldName);
            } catch (Exception e) {
                log.error("计算item参数个数时出错:", e);
            }
        });

        AaListParamsStdModelInfo param = new AaListParamsStdModelInfo();
        param.setListParams(listParamsCnt.get());
        param.setItemParams(itemParamsCnt.get());
        param.setProdType(aaListParamsStdModelDetail.getProdType());
        param.setStatus(1);

        return param;
    }

    private static void listItemParamsCnt(AaListParamsStdModel aaListParamsStdModelDetail, AtomicInteger paramsCnt, String fieldName) throws NoSuchFieldException, IllegalAccessException {

        Field baseField = ReflectionUtils.getAllDeclaredFields(AaListParamsStdModel.class).stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElseThrow(() -> new NoSuchFieldException(String.format("注意AaListParamsStdModelDetail类的属性是否缺失，或者PROPERTIES_TO_COMPARE集合中的元素有冗余。Field ‘%s’ not found", fieldName)));

        if (baseField.getType().equals(String.class)) {
            baseField.setAccessible(true);
            String value = (String) baseField.get(aaListParamsStdModelDetail);
            if (StringUtils.isNotBlank(value)) {
                paramsCnt.getAndIncrement();
            }
        } else {
            paramsCnt.getAndIncrement();
        }
    }
}
