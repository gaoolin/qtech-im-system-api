package com.qtech.im.aa.service;

import com.qtech.im.aa.domain.AaStdProgramManagementVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/10 09:07:37
 * desc   :
 */


public interface IAaStdProgramManagementService {
    public AaStdProgramManagementVo selectAaStdProgramManagementById(Long id);
    public List<AaStdProgramManagementVo> selectAaStdProgramManagementList(AaStdProgramManagementVo aaStdProgramManagementVo);
    public AaStdProgramManagementVo selectOneAaStdProgramManagement(AaStdProgramManagementVo aaStdProgramManagementVo);
    public int insertAaStdProgramManagement(AaStdProgramManagementVo aaStdProgramManagementVo);
    public int updateAaStdProgramManagement(AaStdProgramManagementVo aaStdProgramManagementVo);
    public int deleteAaStdProgramManagementById(Long id);
    public int deleteAaStdProgramManagementByIds(Long[] ids);
}
