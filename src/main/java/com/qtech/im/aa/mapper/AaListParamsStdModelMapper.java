package com.qtech.im.aa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mchange.v2.encounter.StrongEqualityEncounterCounter;
import com.qtech.im.aa.domain.AaListParamsStdModel;
import com.qtech.im.config.GaeaBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhilin.gao
 * @description 针对表【IM_AA_LIST_PARAMS_STD_MODEL】的数据库操作Mapper
 * @createDate 2024-10-29 14:30:19
 * @Entity com.qtech.aa.domain.AaListParamsStdModel
 */

@Mapper
public interface AaListParamsStdModelMapper extends GaeaBaseMapper<AaListParamsStdModel> {
    @Delete("DELETE FROM IMBIZ.IM_AA_LIST_PARAMS_STD_MODEL WHERE PROD_TYPE = #{prodType}")
    int deleteByProdType(String prodType);

}