package com.qtech.im.aa.mapper.files;

import com.qtech.im.aa.domain.files.AaStdProgramManagement;
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

    public List<AaStdProgramManagement> selectAaStdProgramManagementList(AaStdProgramManagement aaStdProgramManagement);

    public AaStdProgramManagement selectAaStdProgramManagementById(Long id);

    public int insertAaStdProgramManagement(AaStdProgramManagement aaStdProgramManagement);

    public int deleteAaStdProgramManagementById(@Param("id") Long id);

    int updateAaStdProgramManagement(AaStdProgramManagement aaStdProgramManagement);
}
