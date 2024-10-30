package com.qtech.im.fixture.service.impl;

import com.qtech.common.exception.file.FileUploadException;
import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataScope;
import com.qtech.im.common.exception.TooManyResultsException;
import com.qtech.im.fixture.common.FixtureUtils;
import com.qtech.im.fixture.domain.FixtureParamsPoGoPin;
import com.qtech.im.fixture.domain.FixtureSharedInfo;
import com.qtech.im.fixture.mapper.FixtureParamsPoGoPinMapper;
import com.qtech.im.fixture.service.IFixtureParamsPoGoPinService;
import com.qtech.im.fixture.service.IFixtureSharedInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;
import static com.qtech.im.fixture.service.impl.FixtureSharedInfoServiceImpl.uploadDataResult;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/03/07 15:41:01
 * desc   :
 */

@Slf4j
@Service
public class FixtureParamsPoGoPinServiceImpl implements IFixtureParamsPoGoPinService {

    @Autowired
    private FixtureParamsPoGoPinMapper fixtureParamsPoGoPinMapper;

    @Autowired
    private FixtureUtils fixtureUtils;

    @Autowired
    private IFixtureSharedInfoService fixtureSharedInfoService;


    @Override
    public List<FixtureParamsPoGoPin> selectFixtureParamsPoGoPinList(FixtureParamsPoGoPin FixtureParamsPoGoPin) {
        return fixtureParamsPoGoPinMapper.selectFixtureParamsPoGoPinList(FixtureParamsPoGoPin);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public Map<String, String> uploadFixtureParamsPoGoPin(List<FixtureParamsPoGoPin> FixtureParamsPoGoPins) {

        HashMap<String, String> resultMap = new HashMap<>();

        int insert = 0;
        int exist = 0;

        if (StringUtils.isNull(FixtureParamsPoGoPins) || FixtureParamsPoGoPins.isEmpty()) {
            try {
                throw new FileUploadException("文档数据为空！");
            } catch (FileUploadException e) {
                // 在开发环境中，可以同时在控制台和日志文件中看到错误信息
                // 在生产环境中，可以只将错误记录到日志文件，避免敏感信息泄露
                log.error(e.getMessage());
                // e.printStackTrace(); // 仅在开发环境中打印堆栈跟踪
            }
        }

        for (FixtureParamsPoGoPin FixtureParamsPoGoPin : FixtureParamsPoGoPins) {
            int i = addFixtureParamsPoGoPin(FixtureParamsPoGoPin);

            if (i > 0) {
                insert++;
            } else {
                exist++;
            }
        }

        return uploadDataResult(resultMap, insert, exist);
    }

    @Override
    public FixtureParamsPoGoPin selectOneFixtureParamsPoGoPin(FixtureParamsPoGoPin FixtureParamsPoGoPin) {
        List<FixtureParamsPoGoPin> list = fixtureParamsPoGoPinMapper.selectFixtureParamsPoGoPinList(FixtureParamsPoGoPin);

        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            if (size > 1) {
                // log.warn(String.format("Warn: execute Method There are  %s results.", size));
                throw new TooManyResultsException(String.format("Expected one result (or null) to be returned by selectOne(), but found: %s" , size));
            }
            return list.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public int removeFixtureParamsPoGoPin(FixtureParamsPoGoPin FixtureParamsPoGoPin) {

        try {
            int i = fixtureParamsPoGoPinMapper.removeFixtureParamsPoGoPin(FixtureParamsPoGoPin);
            if (FixtureParamsPoGoPin.getFixtureSharedStatus() == 1) {
                FixtureSharedInfo fixtureSharedInfoQuery = new FixtureSharedInfo();
                fixtureSharedInfoQuery.setDeptId(FixtureParamsPoGoPin.getDeptId());
                fixtureSharedInfoQuery.setMaterialNmb(FixtureParamsPoGoPin.getMaterialNmb());
                int j = fixtureSharedInfoService.deleteFixtureSharedInfo(fixtureSharedInfoQuery);
                return i & j;
            }
            return i;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public int addFixtureParamsPoGoPin(FixtureParamsPoGoPin fixtureParamsPoGoPin) {
        try {
            return fixtureUtils.addFixtureParamsGeneric(fixtureParamsPoGoPin, "pogopin");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public int editFixtureParamsPoGoPin(FixtureParamsPoGoPin FixtureParamsPoGoPin) {
        try {
            String nickName = getLoginUser().getUser().getNickName();
            FixtureParamsPoGoPin.setUpdateBy(nickName);
            FixtureParamsPoGoPin.setUpdateTime(DateUtils.getNowDate());

            fixtureUtils.fixtureCategoryExistCheckGeneric(FixtureParamsPoGoPin, "pogopin");

            int n = fixtureParamsPoGoPinMapper.editFixtureParamsPoGoPin(FixtureParamsPoGoPin);
            fixtureUtils.fixtureMaterialCheckGeneric(FixtureParamsPoGoPin, "pogopin");
            return n;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public FixtureParamsPoGoPin selectOneFixtureParamsPoGoPin(Long id) {
        try {
            return fixtureParamsPoGoPinMapper.selectOneFixtureParamsPoGoPin(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }
}
