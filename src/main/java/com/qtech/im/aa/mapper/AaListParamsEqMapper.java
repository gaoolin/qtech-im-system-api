package com.qtech.im.aa.mapper;

import com.qtech.im.aa.domain.AaListParamsEqVo;
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
    public int editAaListParamsIgnoreEq(AaListParamsEqVo aaListParamsEqVo);

    public AaListParamsEqVo selectAaListParamsEqById(String id);

    public List<AaListParamsEqVo> selectAaListParamsEqList(AaListParamsEqVo aaListParamsEqVo);

    public int insertAaListParamsIgnoreEq(AaListParamsEqVo aaListParamsEqVo);

    public int editAaListParamsEq(AaListParamsEqVo aaListParamsEqVo);
}
