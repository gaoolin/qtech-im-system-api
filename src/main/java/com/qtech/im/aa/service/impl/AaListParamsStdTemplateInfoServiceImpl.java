package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsStdTemplate;
import com.qtech.im.aa.domain.AaListParamsStdTemplateInfo;
import com.qtech.im.aa.event.AaListParamsStdTemplateInfoEvent;
import com.qtech.im.aa.mapper.AaListParamsStdTemplateInfoMapper;
import com.qtech.im.aa.mapper.AaListParamsStdTemplateMapper;
import com.qtech.im.aa.service.IAaListParamsStdTemplateInfoService;
import com.qtech.im.aa.utils.ModelDetailConvertToModelInfo;
import com.qtech.im.aa.vo.AaListParamsStdModelInfoVo;
import com.qtech.im.common.util.QtechImVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 14:01:06
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.FOURTH)
@Service
public class AaListParamsStdTemplateInfoServiceImpl extends ServiceImpl<AaListParamsStdTemplateInfoMapper, AaListParamsStdTemplateInfo> implements IAaListParamsStdTemplateInfoService {
    @Autowired
    private AaListParamsStdTemplateMapper aaListParamsStdTemplateMapper;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public static LambdaQueryWrapper<AaListParamsStdTemplateInfo> buildQueryWrapper(AaListParamsStdTemplateInfo aaListParamsStdTemplateInfo) {
        LambdaQueryWrapper<AaListParamsStdTemplateInfo> wrapper = Wrappers.lambdaQuery();

        addCondition(wrapper, AaListParamsStdTemplateInfo::getId, aaListParamsStdTemplateInfo.getId());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getProdType, aaListParamsStdTemplateInfo.getProdType());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getListParams, aaListParamsStdTemplateInfo.getListParams());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getItemParams, aaListParamsStdTemplateInfo.getItemParams());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getStatus, aaListParamsStdTemplateInfo.getStatus());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getProvider, aaListParamsStdTemplateInfo.getProvider());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getBelongTo, aaListParamsStdTemplateInfo.getBelongTo());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getCreateBy, aaListParamsStdTemplateInfo.getCreateBy());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getCreateTime, aaListParamsStdTemplateInfo.getCreateTime());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getUpdateBy, aaListParamsStdTemplateInfo.getUpdateBy());
        addCondition(wrapper, AaListParamsStdTemplateInfo::getUpdateTime, aaListParamsStdTemplateInfo.getUpdateTime());

        return wrapper;
    }

    private static <T, R> void addCondition(LambdaQueryWrapper<T> wrapper, SFunction<T, R> column, R value) {
        if (value != null) {
            if (value instanceof String && StringUtils.isBlank((String) value)) {
                return;
            }
            wrapper.eq(column, value);
        }
    }

    // 辅助方法，首字母大写
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QtechImVoUtil.QtechImVos<AaListParamsStdModelInfoVo> selectStdModelInfoList(AaListParamsStdTemplateInfo aaListParamsStdTemplateInfo) {
        try {
            ArrayList<AaListParamsStdModelInfoVo> vos = new ArrayList<>();
            LambdaQueryWrapper<AaListParamsStdTemplateInfo> wrapper = buildQueryWrapper(aaListParamsStdTemplateInfo);
            wrapper.orderByDesc(AaListParamsStdTemplateInfo::getCreateTime);
            List<AaListParamsStdTemplateInfo> list = list(wrapper);
            long total = new PageInfo(list).getTotal();
            if (!list.isEmpty()) {
                list.forEach(item -> vos.add(new AaListParamsStdModelInfoVo(item)));
            }
            return new QtechImVoUtil.QtechImVos<>(vos, total);
        } catch (Exception e) {
            log.error("selectStdModelInfoList:", e);
            throw new RuntimeException("查询数据库发生异常，请联系管理员。");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public boolean updateStdModelInfo(AaListParamsStdTemplateInfo aaListParamsStdTemplateInfo) {
        try {
            LambdaQueryWrapper<AaListParamsStdTemplateInfo> wrapper = new LambdaQueryWrapper<>();
            if (aaListParamsStdTemplateInfo.getId() != null) {
                wrapper.eq(AaListParamsStdTemplateInfo::getId, aaListParamsStdTemplateInfo.getId());
            }
            if (StringUtils.isNotBlank(aaListParamsStdTemplateInfo.getProdType())) {
                wrapper.eq(AaListParamsStdTemplateInfo::getProdType, aaListParamsStdTemplateInfo.getProdType());
            }
            AaListParamsStdTemplateInfo one = getOne(wrapper);
            if (one == null) {
                log.error("updateStdModelInfo: {}", "数据不存在！");
                throw new RuntimeException("数据不存在！");
            }
            String prodType = one.getProdType();

            LambdaQueryWrapper<AaListParamsStdTemplateInfo> queryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isBlank(prodType) && aaListParamsStdTemplateInfo.getId() == null) {
                throw new RuntimeException("产品类型不能为空！");
            } else {
                if (StringUtils.isNotBlank(prodType)) {
                    queryWrapper.eq(AaListParamsStdTemplateInfo::getProdType, prodType);
                }
                if (aaListParamsStdTemplateInfo.getId() != null) {
                    queryWrapper.eq(AaListParamsStdTemplateInfo::getId, aaListParamsStdTemplateInfo.getId());
                }
            }
            // 发布更新事件
            applicationEventPublisher.publishEvent(new AaListParamsStdTemplateInfoEvent(this, prodType, "UPDATE"));
            return update(aaListParamsStdTemplateInfo, queryWrapper);
        } catch (Exception e) {
            log.error("updateStdModelInfo:", e);
            throw new RuntimeException("修改数据发生异常，请联系管理员。");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public boolean saveOrUpdateStdModelInfo(Object entity) {
        AaListParamsStdTemplateInfo modelInfo = null;
        AaListParamsStdTemplate modelDetail = null;

        try {
            if (entity instanceof AaListParamsStdTemplateInfo) {
                modelInfo = (AaListParamsStdTemplateInfo) entity;
                applicationEventPublisher.publishEvent(new AaListParamsStdTemplateInfoEvent(this, modelInfo.getProdType(), "UPDATE"));
            } else if (entity instanceof AaListParamsStdTemplate) {
                modelDetail = (AaListParamsStdTemplate) entity;
                modelInfo = ModelDetailConvertToModelInfo.doConvert(modelDetail);

                if (modelInfo != null) {

                    if (StringUtils.isNotEmpty(modelDetail.getCreateBy())) {
                        modelInfo.setCreateBy(modelDetail.getCreateBy());
                    }

                    if (modelDetail.getCreateTime() != null) {
                        modelInfo.setCreateTime(modelDetail.getCreateTime());
                    }

                    if (StringUtils.isNotBlank(modelDetail.getUpdateBy())) {
                        modelInfo.setUpdateBy(modelDetail.getUpdateBy());
                    }

                    if (modelDetail.getUpdateTime() != null) {
                        modelInfo.setUpdateTime(modelDetail.getUpdateTime());
                    }
                }
                if (modelInfo != null) {
                    applicationEventPublisher.publishEvent(new AaListParamsStdTemplateInfoEvent(this, modelInfo.getProdType(), "INSERT"));
                }
            }
            return doSaveOrUpdate(modelInfo);
        } catch (Exception e) {
            // 记录日志或进行其他异常处理
            log.error("saveOrUpdateAaListParamsStdModelInfo:", e);
        }
        return false;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public boolean doSaveOrUpdate(AaListParamsStdTemplateInfo modelInfo) {
        try {
            LambdaQueryWrapper<AaListParamsStdTemplateInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AaListParamsStdTemplateInfo::getProdType, modelInfo.getProdType());
            AaListParamsStdTemplateInfo one = getOne(wrapper);
            boolean flag = false;
            if (one != null) {
                flag = update(modelInfo, wrapper);
                applicationEventPublisher.publishEvent(new AaListParamsStdTemplateInfoEvent(this, modelInfo.getProdType(), "UPDATE"));
            } else {
                flag = save(modelInfo);
                applicationEventPublisher.publishEvent(new AaListParamsStdTemplateInfoEvent(this, modelInfo.getProdType(), "INSERT"));
            }

            return flag;
        } catch (Exception e) {
            // 记录日志或进行其他异常处理
            log.error("saveOrUpdateModelInfo:", e);
        }
        return false;
    }

    /**
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean deleteStdModelInfoById(Long id) {
        try {
            AaListParamsStdTemplateInfo modelInfo = getById(id);
            if (modelInfo == null) {
                return false; // 或者抛出异常
            }
            String prodType = modelInfo.getProdType();
            boolean a = removeById(id);
            int result = aaListParamsStdTemplateMapper.deleteByProdType(prodType);
            applicationEventPublisher.publishEvent(new AaListParamsStdTemplateInfoEvent(this, prodType, "DELETE"));
            boolean b = result > 0;
            return a && b;
        } catch (Exception e) {
            log.error("deleteStdModelInfoById:", e);
            throw new RuntimeException("删除标准模型信息失败，请检查日志");
        }
    }
}
