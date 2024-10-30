package com.qtech.im.aa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtech.im.aa.domain.ImAaListParamsStdModelDetail;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @author zhilin.gao
* @description 针对表【IM_AA_LIST_PARAMS_STD_MODEL】的数据库操作Service
* @createDate 2024-10-29 14:30:19
*/
public interface ImAaListParamsStdModelDetailService extends IService<ImAaListParamsStdModelDetail> {

    List<ImAaListParamsStdModelDetail> selectAaListParamsStdModelList(ImAaListParamsStdModelDetail imAaListParamsStdModelDetail);

    ImAaListParamsStdModelDetail selectOne(ImAaListParamsStdModelDetail aaListParamsStdModelDetail);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
    boolean insertAaListParamsStdModel(ImAaListParamsStdModelDetail aaListParamsStdModelDetail);

    int batchInsert(List<ImAaListParamsStdModelDetail> paramsModelList);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    boolean updateAaListParamsStdModel(ImAaListParamsStdModelDetail aaListParamsStdModelDetail);

    boolean deleteAaListParamsStdModel(ImAaListParamsStdModelDetail aaListParamsStdModelDetail);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    boolean deleteAaListParamsStdModelByIds(List<Long> list);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    Map<String, Object> uploadManual(List<ImAaListParamsStdModelDetail> paramsModelList);

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    Map<String, Object> uploadOnline(ImAaListParamsStdModelDetail aaListParamsStdModelDetail);
}
