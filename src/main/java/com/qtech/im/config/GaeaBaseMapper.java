package com.qtech.im.config;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/25 15:39:43
 * desc   :  解决MybatisPlus空值不能更新的问题，全局设置，不单针对某个字段
 */


public interface GaeaBaseMapper<T> extends BaseMapper<T> {

    /**
     * 驼峰命名转下划线
     *
     * @param str 例如：createUsername
     * @return 例如：create_username
     */
    public static String cameToUnderline(String str) {
        Matcher matcher = Pattern.compile("[A-Z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        int index = 0;
        while (matcher.find()) {
            builder.replace(matcher.start() + index, matcher.end() + index, "_" + matcher.group().toLowerCase());
            index++;
        }
        if (builder.charAt(0) == '_') {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    /**
     * 返回全量修改 null 的 updateWrapper
     */
    default LambdaUpdateWrapper<T> updateWithNullField(T entity) {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        List<Field> allFields = TableInfoHelper.getAllFields(entity.getClass());
        MetaObject metaObject = SystemMetaObject.forObject(entity);
        allFields.forEach(field -> {
            Object value = metaObject.getValue(field.getName());
            updateWrapper.set(cameToUnderline(field.getName()), value);
        });
        return updateWrapper.lambda();
    }
}