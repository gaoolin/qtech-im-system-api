package com.qtech.im.aa.service;

import com.qtech.im.aa.domain.AaStdProgramManagement;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/10 09:07:37
 * desc   :
 */


public interface IAaStdProgramManagementService {
    public AaStdProgramManagement selectAaStdProgramManagementById(Long id);
    public List<AaStdProgramManagement> selectAaStdProgramManagementList(AaStdProgramManagement aaStdProgramManagement);
    public AaStdProgramManagement selectOneAaStdProgramManagement(AaStdProgramManagement aaStdProgramManagement);
    public int insertAaStdProgramManagement(AaStdProgramManagement aaStdProgramManagement);
    public int updateAaStdProgramManagement(AaStdProgramManagement aaStdProgramManagement);
    public int deleteAaStdProgramManagementById(Long id);
    public int deleteAaStdProgramManagementByIds(Long[] ids);
}
