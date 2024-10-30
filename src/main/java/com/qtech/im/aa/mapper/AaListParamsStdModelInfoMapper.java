package com.qtech.im.aa.mapper;

import com.qtech.im.aa.domain.AaListParamsStdModelInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 13:43:36
 * desc   :
 */

@Mapper
public interface AaListParamsStdModelInfoMapper {

    public List<AaListParamsStdModelInfoVo> selectAaListParamsStdModelInfoList(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo);
    public int insertAaListParamsStdModelInfo(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo);
    public int updateAaListParamsStdModelInfo(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo);

    public int deleteAaListParamsStdModelInfoByIds(Long[] list);
}
