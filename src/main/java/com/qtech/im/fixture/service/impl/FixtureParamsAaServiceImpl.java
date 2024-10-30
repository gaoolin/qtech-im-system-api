package com.qtech.im.fixture.service.impl;

import com.qtech.common.exception.file.FileUploadException;
import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.im.common.exception.EditFixtureSharedInfoException;
import com.qtech.im.fixture.common.FixtureUtils;
import com.qtech.im.fixture.domain.FixtureParamsAa;
import com.qtech.im.fixture.mapper.FixtureParamsAaMapper;
import com.qtech.im.fixture.service.IFixtureParamsAaService;
import com.qtech.im.fixture.service.IFixtureSharedInfoService;
import lombok.extern.slf4j.Slf4j;
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
 * date   :  2024/04/02 13:43:08
 * desc   :
 */

@Slf4j
@Service
public class FixtureParamsAaServiceImpl implements IFixtureParamsAaService {

    @Autowired
    private FixtureParamsAaMapper fixtureParamsAaMapper;

    @Autowired
    private FixtureUtils fixtureUtils;

    @Autowired
    private IFixtureSharedInfoService fixtureSharedInfoService;

    @Override
    public List<FixtureParamsAa> selectFixtureParamsAaList(FixtureParamsAa fixtureParamsAa) {
        return fixtureParamsAaMapper.selectFixtureParamsAaList(fixtureParamsAa);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public int addFixtureParamsAa(FixtureParamsAa fixtureParamsAa) {
        return fixtureUtils.addFixtureParamsGeneric(fixtureParamsAa, "aa");
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Map<String, String> uploadFixtureParamsAa(List<FixtureParamsAa> fixtureParamsAas) {
        try {
            HashMap<String, String> resultMap = new HashMap<>();

            int insert = 0;
            int exist = 0;

            if (StringUtils.isNull(fixtureParamsAas) || fixtureParamsAas.isEmpty()) {
                try {
                    throw new FileUploadException("文档数据为空！");
                } catch (FileUploadException e) {
                    // e.printStackTrace();
                    log.error(e.getMessage());
                }
            }

            for (FixtureParamsAa fixtureParamsAa : fixtureParamsAas) {
                int i = addFixtureParamsAa(fixtureParamsAa);

                if (i > 0) {
                    insert++;
                } else {
                    exist++;
                }
            }
            return uploadDataResult(resultMap, insert, exist);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @Transactional(rollbackFor = {Exception.class, EditFixtureSharedInfoException.class}, propagation = Propagation.REQUIRED)
    @Override
    public int editFixtureParamsAa(FixtureParamsAa fixtureParamsAa) {
        try {
            String nickName = getLoginUser().getUser().getNickName();
            fixtureParamsAa.setUpdateBy(nickName);
            fixtureParamsAa.setUpdateTime(DateUtils.getNowDate());

            fixtureUtils.fixtureCategoryExistCheckGeneric(fixtureParamsAa, "aa");

            int n = fixtureParamsAaMapper.editFixtureParamsAa(fixtureParamsAa);
            fixtureUtils.fixtureMaterialCheckGeneric(fixtureParamsAa, "aa");
            return n;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
    @Override
    public int removeFixtureParamsAa(FixtureParamsAa fixtureParamsAa) {

        try {
            int i = fixtureParamsAaMapper.removeFixtureParamsAa(fixtureParamsAa);
            int j = fixtureUtils.fixtureSharedInfoRemoveCheck(fixtureParamsAa.getFixtureSharedStatus(), fixtureParamsAa.getDeptId(), fixtureParamsAa.getMaterialNmb(), fixtureSharedInfoService);
            return i | j;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @Override
    public FixtureParamsAa selectOneFixtureParamsAa(FixtureParamsAa fixtureParamsAa) {
        try {
            return fixtureParamsAaMapper.selectOneFixtureParamsAa(fixtureParamsAa);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }
}
