package com.qtech.im.fixture.service.impl;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataScope;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.exception.AddFixtureSharedInfoException;
import com.qtech.im.common.exception.DeleteFixtureSharedInfoException;
import com.qtech.im.common.exception.EditFixtureSharedInfoException;
import com.qtech.im.common.exception.FixtureUploadException;
import com.qtech.im.fixture.common.FixtureUtils;
import com.qtech.im.fixture.domain.*;
import com.qtech.im.fixture.mapper.FixtureSharedInfoMapper;
import com.qtech.im.fixture.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;


/**
 * 治具Service业务层处理
 *
 * @author gaozhilin
 * @date 2023-06-27
 */

@Slf4j
@Service
public class FixtureSharedInfoServiceImpl implements IFixtureSharedInfoService {
    @Autowired
    private FixtureSharedInfoMapper fixtureSharedInfoMapper;

    @Autowired
    private IFixtureSharedInfoHistoryService fixtureSharedInfoHistoryService;

    @Autowired
    private IFixtureMaterialInfoService fixtureMaterialInfoService;

    @Autowired
    private IFixtureProdTypeInfoService fixtureProdTypeInfoService;

    @Autowired
    private IFixtureCategoryInfoService fixtureCategoryInfoService;

    @Autowired
    private IFixtureMaterialCategoryProdTypeService fixtureMaterialCategoryProdTypeService;

    @Autowired
    private FixtureUtils fixtureUtils;

    static Map<String, String> uploadDataResult(HashMap<String, String> resultMap, int insert, int exist) {
        try {
            if (exist == 0) {
                resultMap.put("flag", "1");
                resultMap.put("result", "共 " + (insert + exist) + " 条数据，已导入 " + insert + "条数据。");
            } else {
                resultMap.put("flag", "0");
                resultMap.put("result", "共 " + (insert + exist) + " 条数据，已导入 " + insert + " 条数据，未导入 " + exist +
                        " 条数据，请检查！");
            }
            return resultMap;
        } catch (Exception e) {
            log.error("uploadDataResult error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public FixtureSharedInfo selectFixtureSharedInfoOne(FixtureSharedInfo fixtureSharedInfo) {
        try {
            return fixtureSharedInfoMapper.selectFixtureSharedInfoOne(fixtureSharedInfo);
        } catch (Exception e) {
            log.error("selectFixtureSharedInfoOne error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public FixtureSharedInfo selectFixtureSharedInfoById(Long id) {
        try {
            return fixtureSharedInfoMapper.selectFixtureSharedInfoById(id);
        } catch (Exception e) {
            log.error("selectFixtureSharedInfoById error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    /**
     * 查询治具列表
     *
     * @param fixtureSharedInfo 治具
     * @return 治具
     */
    @DataScope(deptAlias = "ta")
    @Override
    public List<FixtureSharedInfo> selectFixtureSharedInfoList(FixtureSharedInfo fixtureSharedInfo) {
        try {
            return fixtureSharedInfoMapper.selectFixtureSharedInfoList(fixtureSharedInfo);
        } catch (Exception e) {
            log.error("selectFixtureSharedInfoList error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    /**
     * 新增治具
     *
     * @param fixtureSharedInfo 治具
     * @return 结果
     */
    @DataScope(deptAlias = "ta")
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public int addFixtureSharedInfo(FixtureSharedInfo fixtureSharedInfo) {
        if (!Optional.ofNullable(fixtureSharedInfo.getDeptId()).isPresent()) {
            throw new AddFixtureSharedInfoException("数据权限不明确，请检查治具类别设置！");
        }

        // 新增不会有mId, cId, pId，需要经过数据库查询，修改会自带这些信息
        String nickName = getLoginUser().getUser().getNickName();

        fixtureSharedInfo.setCreateBy(nickName);
        fixtureSharedInfo.setCreateTime(DateUtils.getNowDate());

        Long deptId = fixtureSharedInfo.getDeptId();
        Long userId = fixtureSharedInfo.getUserId();

        if (StringUtils.isEmpty(fixtureSharedInfo.getMaterialNmb())) {
            throw new AddFixtureSharedInfoException("缺少料号信息，请检查！");
        } else if (StringUtils.isEmpty(fixtureSharedInfo.getProdType())) {
            throw new AddFixtureSharedInfoException("缺少机型信息，请检查！");
        } else if (StringUtils.isEmpty(fixtureSharedInfo.getFixtureCategory())) {
            throw new AddFixtureSharedInfoException("缺少治具类别信息，请检查！");
        }

        FixtureMaterialInfo fixtureMaterialInfo = new FixtureMaterialInfo();
        FixtureProdTypeInfo fixtureProdTypeInfo = new FixtureProdTypeInfo();
        FixtureCategoryInfo fixtureCategoryInfo = new FixtureCategoryInfo();
        BeanUtils.copyProperties(fixtureSharedInfo, fixtureMaterialInfo, "remark");
        BeanUtils.copyProperties(fixtureSharedInfo, fixtureProdTypeInfo, "remark");
        BeanUtils.copyProperties(fixtureSharedInfo, fixtureCategoryInfo, "remark");

        Integer i = 1;
        Integer j = 1;
        Integer k = 1;
        Integer l = 1;

        FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType = new FixtureMaterialCategoryProdType();
        fixtureMaterialCategoryProdType.setDeptId(fixtureSharedInfo.getDeptId());
        fixtureMaterialCategoryProdType.setUserId(fixtureSharedInfo.getUserId());

        // 料号相关逻辑
        // 根据料号查询数据库是否存在该料号
        FixtureMaterialInfo fixtureMaterialInfoTmp = new FixtureMaterialInfo();
        fixtureMaterialInfoTmp.setMaterialNmb(fixtureMaterialInfo.getMaterialNmb());
        fixtureMaterialInfoTmp.setDeptId(deptId);
        fixtureMaterialInfoTmp.setUserId(userId);
        Boolean fixtureMaterialInfoExist = fixtureMaterialInfoService.isFixtureMaterialInfoExist(fixtureMaterialInfoTmp);

        if (!fixtureMaterialInfoExist) {
            // 不存在，则新增料号
            fixtureMaterialInfo.setCreateBy(nickName);
            fixtureMaterialInfo.setCreateTime(DateUtils.getNowDate());
            try {
                i = fixtureMaterialInfoService.addFixtureMaterialInfo(fixtureMaterialInfo);
            } catch (Exception e) {
                throw new AddFixtureSharedInfoException("与料号相关的信息有误，请检查！");
            }
        } else {
            // 存在
            FixtureMaterialInfo fixtureMaterialInfoDb = fixtureMaterialInfoService.selectOneFixtureMaterialInfo(fixtureMaterialInfoTmp);
            fixtureMaterialInfo.setmId(fixtureMaterialInfoDb.getmId());
        }

        // 共享机型相关逻辑
        // 在料号的基础上添加共享机型
        // 判断共享机型是否存在
        FixtureProdTypeInfo fixtureProdTypeInfoTmp = new FixtureProdTypeInfo();
        fixtureProdTypeInfoTmp.setProdType(fixtureProdTypeInfo.getProdType());
        fixtureProdTypeInfoTmp.setDeptId(deptId);
        fixtureProdTypeInfoTmp.setUserId(userId);
        Boolean fixtureProdTypeInfoExist = fixtureProdTypeInfoService.isFixtureProdTypeInfoExist(fixtureProdTypeInfoTmp);

        if (!fixtureProdTypeInfoExist) {
            // 不存在，则新增共享机型
            fixtureProdTypeInfo.setCreateBy(nickName);
            fixtureProdTypeInfo.setCreateTime(DateUtils.getNowDate());
            try {
                j = fixtureProdTypeInfoService.addFixtureProdTypeInfo(fixtureProdTypeInfo);
            } catch (Exception e) {
                throw new AddFixtureSharedInfoException("与共享机型相关信息有误，请检查！");
            }
        } else {
            // 存在，则添加映射关系信息
            FixtureProdTypeInfo fixtureProdTypeInfoDb = fixtureProdTypeInfoService.selectOneFixtureProdTypeInfo(fixtureProdTypeInfoTmp);
            fixtureProdTypeInfo.setpId(fixtureProdTypeInfoDb.getpId());
        }

        // 治具类型相关逻辑
        // 判断治具类型是否存在
        FixtureCategoryInfo fixtureCategoryInfoTmp = new FixtureCategoryInfo();
        fixtureCategoryInfoTmp.setFixtureCategory(fixtureCategoryInfo.getFixtureCategory());
        fixtureCategoryInfoTmp.setDeptId(deptId);
        fixtureCategoryInfoTmp.setUserId(userId);

        Boolean fixtureCategoryInfoExist = fixtureCategoryInfoService.isFixtureCategoryInfoExist(fixtureCategoryInfoTmp);
        if (!fixtureCategoryInfoExist) {
            // 不存在，新增治具类型
            try {
                k = fixtureCategoryInfoService.addFixtureCategoryInfo(fixtureCategoryInfo);
            } catch (Exception e) {
                throw new AddFixtureSharedInfoException("与治具类型相关信息有误，请检查！");
            }
            fixtureMaterialCategoryProdType.setCId(fixtureCategoryInfo.getCId());
        } else {
            FixtureCategoryInfo fixtureCategoryInfoDb = fixtureCategoryInfoService.selectOneFixtureCategoryInfo(fixtureCategoryInfoTmp);
            fixtureMaterialCategoryProdType.setCId(fixtureCategoryInfoDb.getCId());
        }

        // 添加映射关系信息
        fixtureMaterialCategoryProdType.setMId(fixtureMaterialInfo.getmId());
        fixtureMaterialCategoryProdType.setPId(fixtureProdTypeInfo.getpId());
        fixtureMaterialCategoryProdType.setCreateBy(nickName);
        fixtureMaterialCategoryProdType.setCreateTime(DateUtils.getNowDate());
        fixtureMaterialCategoryProdType.setRemark(fixtureSharedInfo.getRemark());

        try {
            l = fixtureMaterialCategoryProdTypeService.addFixtureMaterialCategoryProdType(fixtureMaterialCategoryProdType);
        } catch (Exception e) {
            l = 0;
            log.error("治具信息重复： " + fixtureMaterialInfo.getMaterialNmb() + "-" + fixtureProdTypeInfo.getProdType() +
                    "-" + fixtureCategoryInfo.getFixtureCategory() + "，请检查！");
            throw new AddFixtureSharedInfoException("治具共享信息重复！");
        }
        return i * j * k * l;
    }

    /**
     * 修改治具
     *
     * @param fixtureSharedInfo 治具
     * @return 结果
     */
    @DataScope(deptAlias = "ta")
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public int editFixtureSharedInfo(FixtureSharedInfo fixtureSharedInfo) {
        if (!Optional.ofNullable(fixtureSharedInfo.getDeptId()).isPresent()) {
            throw new EditFixtureSharedInfoException("数据权限不明确，请检查治具类别设置！");
        }
        // String nickName = SpringUtils.getAopProxy(this).sysUserService.selectUserByUserName(username).getNickName();
        String nickName = getLoginUser().getUser().getNickName();
        Long deptId = fixtureSharedInfo.getDeptId();
        Long userId = fixtureSharedInfo.getUserId();

        FixtureMaterialInfo fixtureMaterialInfo = new FixtureMaterialInfo();
        FixtureProdTypeInfo fixtureProdTypeInfo = new FixtureProdTypeInfo();
        FixtureCategoryInfo fixtureCategoryInfo = new FixtureCategoryInfo();

        BeanUtils.copyProperties(fixtureSharedInfo, fixtureMaterialInfo, "remark");
        BeanUtils.copyProperties(fixtureSharedInfo, fixtureProdTypeInfo, "remark");
        BeanUtils.copyProperties(fixtureSharedInfo, fixtureCategoryInfo, "remark");

        FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType = new FixtureMaterialCategoryProdType();
        fixtureMaterialCategoryProdType.setDeptId(deptId);
        fixtureMaterialCategoryProdType.setUserId(userId);

        Integer i = 1;
        Integer j = 1;
        Integer k;
        Integer l;
        Integer m = 1;

        // 修改前的历史信息（仅使用mId, cId, pId作为查询条件，此3条件均为信息发生修改前的）
        FixtureSharedInfo sharedInfoHistory = fixtureSharedInfoMapper.selectFixtureSharedInfoById(fixtureSharedInfo.getId());

        // 更新料号相关信息
        // 根据料号获取数据库中相关料号的信息
        // FixtureMaterialInfo fixtureMaterialInfoTmp = new FixtureMaterialInfo();
        // fixtureMaterialInfoTmp.setmId(fixtureMaterialInfo.getmId());
        // fixtureMaterialInfoTmp.setDeptId(deptId);
        // fixtureMaterialInfoTmp.setUserId(userId);
        //
        // FixtureMaterialInfo fixtureMaterialInfoDb = fixtureMaterialInfoService.selectOneFixtureMaterialInfo(fixtureMaterialInfoTmp);

        // 判断治具类型是否有修改，如果有变化
        if (!ObjectUtils.nullSafeEquals(fixtureCategoryInfo.getFixtureCategory(), sharedInfoHistory.getFixtureCategory())) {
            FixtureCategoryInfo fixtureCategoryInfoTmp = new FixtureCategoryInfo();
            fixtureCategoryInfoTmp.setFixtureCategory(fixtureCategoryInfo.getFixtureCategory());
            fixtureCategoryInfoTmp.setDeptId(deptId);
            fixtureCategoryInfoTmp.setUserId(userId);
            FixtureCategoryInfo fixtureCategoryInfoDb = fixtureCategoryInfoService.selectOneFixtureCategoryInfo(fixtureCategoryInfoTmp);
            fixtureMaterialCategoryProdType.setCId(fixtureCategoryInfoDb.getCId());
        } else {
            fixtureMaterialCategoryProdType.setCId(fixtureCategoryInfo.getCId());
        }

        // 判断备注信息是否有修改， 和共享机型信息绑定
        if (!ObjectUtils.nullSafeEquals(fixtureSharedInfo.getRemark(), sharedInfoHistory.getRemark())) {
            fixtureMaterialCategoryProdType.setRemark(fixtureSharedInfo.getRemark());
        }

        // 判断治具名称、规格是否有修改, 更新料号相关信息
        if (!(ObjectUtils.nullSafeEquals(fixtureMaterialInfo.getFixtureName(), sharedInfoHistory.getFixtureName()) &&
                ObjectUtils.nullSafeEquals(fixtureMaterialInfo.getFixtureSpec(), sharedInfoHistory.getFixtureSpec()) &&
                ObjectUtils.nullSafeEquals(fixtureMaterialInfo.getFixtureVersion(), sharedInfoHistory.getFixtureVersion()) &&
                ObjectUtils.nullSafeEquals(fixtureMaterialInfo.getBuckle(), sharedInfoHistory.getBuckle()))) {
            // 对比有差异后，根据料号更新相关信息
            fixtureMaterialInfo.setUpdateBy(nickName);
            fixtureMaterialInfo.setUpdateTime(DateUtils.getNowDate());
            try {
                i = fixtureMaterialInfoService.editFixtureMaterialInfo(fixtureMaterialInfo);
            } catch (Exception e) {
                throw new EditFixtureSharedInfoException("与料号相关信息有误，请检查！");
            }
        }

        // 更新共享机型相关信息
        // 查找数据库中是否有该共享机型的信息(只使用prodType和deptId作为查询条件，不使用pId。如果机型发生修改，pId是修改前的机型)
//        FixtureProdTypeInfo fixtureProdTypeInfoTmp1 = new FixtureProdTypeInfo();
//        fixtureProdTypeInfoTmp1.setProdType(fixtureProdTypeInfo.getProdType());
//        fixtureProdTypeInfoTmp1.setDeptId(deptId);
//        fixtureProdTypeInfoTmp1.setUserId(userId);

        if (!ObjectUtils.nullSafeEquals(fixtureProdTypeInfo.getProdType(), sharedInfoHistory.getProdType())) {
            // 编辑前后机型信息有变化
            FixtureProdTypeInfo fixtureProdTypeInfoTmp2 = new FixtureProdTypeInfo();
            fixtureProdTypeInfoTmp2.setProdType(fixtureProdTypeInfo.getProdType());
            fixtureProdTypeInfoTmp2.setDeptId(deptId);
            fixtureProdTypeInfoTmp2.setUserId(userId);
            // 编辑后的机型数据库中是否存在
            Boolean fixtureProdTypeInfoExist = fixtureProdTypeInfoService.isFixtureProdTypeInfoExist(fixtureProdTypeInfoTmp2);

            if (!fixtureProdTypeInfoExist) {
                // 共享机型不存在，则添加该机型
                fixtureProdTypeInfo.setCreateBy(nickName);
                fixtureProdTypeInfo.setCreateTime(DateUtils.getNowDate());
                try {
                    j = fixtureProdTypeInfoService.addFixtureProdTypeInfo(fixtureProdTypeInfo);
                } catch (Exception e) {
                    throw new EditFixtureSharedInfoException("与共享机型相关信息有误，请检查！");
                }
                // 修改关系映射表数据
                fixtureMaterialCategoryProdType.setPId(fixtureProdTypeInfo.getpId());

            } else {
                // 共享机型存在，则更新关系映射表
                // 根据prodType和project获取已存在的机型信息
                FixtureProdTypeInfo fixtureProdTypeInfoDbByProdType = fixtureProdTypeInfoService.selectOneFixtureProdTypeInfo(fixtureProdTypeInfoTmp2);
                // 根据料号更新共享机型（治具类型和料号一一绑定，不用更新）
                fixtureMaterialCategoryProdType.setPId(fixtureProdTypeInfoDbByProdType.getpId());
            }
            fixtureMaterialCategoryProdType.setId(fixtureSharedInfo.getId());
            fixtureMaterialCategoryProdType.setMId(fixtureMaterialInfo.getmId());

            // 判断原机型是否可以删除
            Boolean b = fixtureUtils.fixtureProdTypeMultipleReferenceCheck(sharedInfoHistory.getpId(), deptId);
            if (!b) {
                FixtureProdTypeInfo deleteProdType = new FixtureProdTypeInfo();
                deleteProdType.setpId(fixtureProdTypeInfo.getpId());
                deleteProdType.setDeptId(deptId);
                m = fixtureProdTypeInfoService.removeFixtureProdTypeInfo(deleteProdType);
            }
        } else {
            fixtureMaterialCategoryProdType.setId(fixtureSharedInfo.getId());
            fixtureMaterialCategoryProdType.setMId(fixtureMaterialInfo.getmId());
            // 共享机型没有变化，采用原pId
            fixtureMaterialCategoryProdType.setPId(fixtureProdTypeInfo.getpId());
        }
        fixtureMaterialCategoryProdType.setUpdateBy(nickName);
        fixtureMaterialCategoryProdType.setUpdateTime(DateUtils.getNowDate());

        try {
            k = fixtureMaterialCategoryProdTypeService.editFixtureMaterialCategoryProdType(fixtureMaterialCategoryProdType);
        } catch (Exception e) {
            throw new EditFixtureSharedInfoException("治具共享机型重复，请检查！");
        }

        sharedInfoHistory.setOpsTime(DateUtils.getNowDate());

        l = fixtureSharedInfoHistoryService.addFixtureSharedInfoHistory(sharedInfoHistory);

        return i * j * k * l * m;
    }

    @DataScope(deptAlias = "ta")
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public int deleteFixtureSharedInfo(FixtureSharedInfo fixtureSharedInfo) {
        // 非实体类参数不能携带 deptId， userId，操作数据最好用主键
        FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType = new FixtureMaterialCategoryProdType();
        FixtureMaterialInfo fixtureMaterialInfo = new FixtureMaterialInfo();
        FixtureProdTypeInfo fixtureProdTypeInfo = new FixtureProdTypeInfo();

        // 三个参数分别为 mId, pId, deptId
        BeanUtils.copyProperties(fixtureSharedInfo, fixtureMaterialCategoryProdType);
        BeanUtils.copyProperties(fixtureSharedInfo, fixtureMaterialInfo);
        BeanUtils.copyProperties(fixtureSharedInfo, fixtureProdTypeInfo);

        Integer i;
        Integer j = 0;
        Integer k = 0;
        Integer l = 0;

        boolean empty = StringUtils.isEmpty(fixtureSharedInfo.getProdType());
        // 传过来的参数仅有料号
        if (empty) {
            // 1.1
            List<FixtureMaterialCategoryProdType> list = fixtureMaterialCategoryProdTypeService.selectFixtureMaterialCategoryProdTypeList(fixtureMaterialCategoryProdType);
            // 删除映射表内容（必删）
            if (fixtureMaterialCategoryProdType.getMId() == null) {
                if (fixtureSharedInfo.getMaterialNmb() != null) {
                    FixtureMaterialInfo fixtureMaterialInfoQuery = new FixtureMaterialInfo();
                    fixtureMaterialInfoQuery.setDeptId(fixtureSharedInfo.getDeptId());
                    fixtureMaterialInfoQuery.setMaterialNmb(fixtureMaterialInfo.getMaterialNmb());
                    FixtureMaterialInfo fixtureMaterialInfoOneDb = fixtureMaterialInfoService.selectOneFixtureMaterialInfo(fixtureMaterialInfoQuery);
                    if (fixtureMaterialInfoOneDb != null) {
                        if (fixtureMaterialInfoOneDb.getmId() != null) {
                            fixtureMaterialCategoryProdType.setMId(fixtureMaterialInfoOneDb.getmId());
                        } else {
                            throw new DeleteFixtureSharedInfoException("查询到相关料号mId，请检查！");
                        }
                    } else {
                        throw new DeleteFixtureSharedInfoException("无料号信息（materialId或mId），请检查！");
                    }
                } else {
                    throw new DeleteFixtureSharedInfoException("无料号信息（materialId或mId），请检查！");
                }
            }
            i = fixtureMaterialCategoryProdTypeService.removeFixtureMaterialCategoryProdType(fixtureMaterialCategoryProdType);

            // 再删除料号（必删）
            j = fixtureMaterialInfoService.removeFixtureMaterialInfo(fixtureMaterialInfo);

            // 再判断料号所引用的机型能否删除（可删）
            // 1.2
            if (CollectionUtils.isNotEmpty(list)) {
                for (FixtureMaterialCategoryProdType materialCategoryProdType : list) {
                    Long pIdParam = materialCategoryProdType.getPId();
                    Boolean b = fixtureUtils.fixtureProdTypeMultipleReferenceCheck(pIdParam, fixtureMaterialCategoryProdType.getDeptId());
                    if (!b) {
                        FixtureProdTypeInfo deleteProdType = new FixtureProdTypeInfo();
                        deleteProdType.setpId(pIdParam);
                        deleteProdType.setDeptId(fixtureMaterialCategoryProdType.getDeptId());
                        k = fixtureProdTypeInfoService.removeFixtureProdTypeInfo(deleteProdType);
                    }
                }
            }
        } else {
            // 传过来的参数有机型
            // 删除映射表内容（必删）
            if (fixtureMaterialCategoryProdType.getId() != null) {
                i = fixtureMaterialCategoryProdTypeService.removeFixtureMaterialCategoryProdType(fixtureMaterialCategoryProdType);
            } else {
                throw new DeleteFixtureSharedInfoException("无id信息，请检查！");
            }

            // 机型是否可以删除（可删）
            Boolean prodTypeDeleteOk = fixtureUtils.fixtureProdTypeMultipleReferenceCheck(fixtureSharedInfo.getpId(), fixtureSharedInfo.getDeptId());
            if (!prodTypeDeleteOk) {
                j = fixtureProdTypeInfoService.removeFixtureProdTypeInfo(fixtureProdTypeInfo);
            }

            // 料号是否可以删除（可删）
            Boolean materialIdDeleteOk = fixtureUtils.fixtureMaterialNmbMultipleReferenceCheck(fixtureSharedInfo.getMaterialNmb(), fixtureSharedInfo.getDeptId());
            if (!materialIdDeleteOk) {
                l = fixtureMaterialInfoService.removeFixtureMaterialInfo(fixtureMaterialInfo);
            }
        }
        // 通过 位或 运算返回操作是否成功
        return i | j | k | l;
    }

    @DataSource(DataSourceType.MASTER)
    @DataScope(deptAlias = "ta")
    @Override
    public List<String> selectFixtureSharedInfoDeptIds(Long userDeptId) {
        List<FixtureSharedInfo> fixtureSharedInfos = fixtureSharedInfoMapper.selectFixtureSharedInfoDeptIds(userDeptId);
        ArrayList<String> deptIds = new ArrayList<>();
        for (FixtureSharedInfo fixtureSharedInfo : fixtureSharedInfos) {
            deptIds.add(fixtureSharedInfo.getDeptId().toString());
        }
        return deptIds;
    }

    @DataScope(deptAlias = "ta")
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Map<String, String> uploadFixtureInfo(List<FixtureSharedInfo> fixtureSharedInfos) {

        HashMap<String, String> resultMap = new HashMap<>();

        int insert = 0;
        int exist = 0;

        if (StringUtils.isNull(fixtureSharedInfos) || fixtureSharedInfos.isEmpty()) {
            throw new FixtureUploadException();
        }

        for (FixtureSharedInfo fixtureSharedInfo : fixtureSharedInfos) {
            int i = addFixtureSharedInfo(fixtureSharedInfo);

            if (i > 0) {
                insert = insert + 1;
            } else {
                exist = exist + 1;
            }
        }

        return uploadDataResult(resultMap, insert, exist);
    }
}
