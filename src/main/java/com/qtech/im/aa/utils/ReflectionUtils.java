package com.qtech.im.aa.utils;


import com.qtech.im.aa.domain.template.AaListParamsStdTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/05 08:39:48
 * desc   :
 */


public class ReflectionUtils {

    /**
     * 获取给定类及其所有父类的声明字段列表。
     *
     * @param clazz 要检查的类
     * @return 字段列表
     */
    public static List<Field> getAllDeclaredFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) { // 遍历直到到达Object类
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass(); // 获取父类并继续循环
        }
        return fields;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        class BaseClass {
            private String baseField;
        }

        class ChildClass extends BaseClass {
            private String childField;
        }

        // 使用示例
        Field field = ReflectionUtils.getAllDeclaredFields(AaListParamsStdTemplate.class).stream().filter(f -> f.getName().equals("baseField")) // 示例：查找名为"baseField"的字段
                .findFirst().orElseThrow(() -> new NoSuchFieldException("Field not found"));
    }
}
