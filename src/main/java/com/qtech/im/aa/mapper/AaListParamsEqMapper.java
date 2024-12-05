package com.qtech.im.aa.mapper;

import com.qtech.im.aa.domain.AaListParamsEq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/19 10:12:41
 * desc   :
 */

@Mapper
public interface AaListParamsEqMapper {
    public int editAaListParamsIgnoreEq(AaListParamsEq aaListParamsEq);

    public AaListParamsEq selectAaListParamsEqById(String id);

    public List<AaListParamsEq> selectAaListParamsEqList(AaListParamsEq aaListParamsEq);

    public int insertAaListParamsIgnoreEq(AaListParamsEq aaListParamsEq);

    public int editAaListParamsEq(AaListParamsEq aaListParamsEq);
}
