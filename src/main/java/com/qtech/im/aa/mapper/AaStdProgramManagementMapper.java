package com.qtech.im.aa.mapper;

import com.qtech.im.aa.domain.AaStdProgramManagementVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/02 16:10:56
 * desc   :
 */

@Mapper
public interface AaStdProgramManagementMapper {

    public List<AaStdProgramManagementVo> selectAaStdProgramManagementList(AaStdProgramManagementVo aaStdProgramManagementVo);

    public AaStdProgramManagementVo selectAaStdProgramManagementById(Long id);

    public int insertAaStdProgramManagement(AaStdProgramManagementVo aaStdProgramManagementVo);

    public int deleteAaStdProgramManagementById(@Param("id") Long id);

    int updateAaStdProgramManagement(AaStdProgramManagementVo aaStdProgramManagementVo);
}
