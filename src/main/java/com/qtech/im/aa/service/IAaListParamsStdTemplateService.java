package com.qtech.im.aa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtech.im.aa.domain.template.AaListParamsStdTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @author zhilin.gao
* @description 针对表【IM_AA_LIST_PARAMS_STD_MODEL】的数据库操作Service
* @createDate 2024-10-29 14:30:19
*/
public interface IAaListParamsStdTemplateService extends IService<AaListParamsStdTemplate> {

    List<AaListParamsStdTemplate> selectList(AaListParamsStdTemplate AaListParamsStdTemplate);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
    boolean saveOrUpdateAaListParamsStdModel(AaListParamsStdTemplate AaListParamsStdTemplate);

    int batchInsert(List<AaListParamsStdTemplate> paramsModelList);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    boolean updateAaListParamsStdModel(AaListParamsStdTemplate aaListParamsStdTemplateDetail);

    void deleteAaListParamsStdModel(AaListParamsStdTemplate aaListParamsStdTemplateDetail);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    boolean deleteAaListParamsStdModelByIds(List<Long> list);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    Map<String, Object> uploadManual(List<AaListParamsStdTemplate> paramsModelList);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    Map<String, Object> uploadOnline(AaListParamsStdTemplate aaListParamsStdTemplateDetail);

    AaListParamsStdTemplate selectOne(Long id);
}
