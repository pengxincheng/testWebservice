package com.business.student.service.impl;

import com.business.classes.po.ClassesEntity;
import com.business.student.dao.StuDao;
import com.business.student.po.StuEntity;
import com.business.student.service.StuService;
import com.order.cc.sys.dao.FoPage;
import com.utils.DTOFetchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pxc on 2018/4/20.
 */
@Service("stuService")
public class StuServiceImpl implements StuService {

    @Autowired
    private StuDao stuDao;

    @Override
    public void saveStu(StuEntity stuEntity) {
        stuDao.saveEntity(stuEntity);
    }

    @Override
    public FoPage getStuPage(StuEntity stuEntity, int pageNum, int pageSize) {
        return stuDao.getStuPage(stuEntity,pageNum,pageSize);
    }

    @Override
    public List<StuEntity> getStuList(StuEntity stuEntity) {
        return stuDao.getStuList(stuEntity);
    }

    @Override
    public StuEntity getById(String id, Class... classes) {
        StuEntity entity = stuDao.getEntityById(id);
        if (entity != null) {
            for (Class clazz : classes) {
                if (clazz == ClassesEntity.class) {
                    DTOFetchUtils.initialize(entity.getClassesEntity());
                }
            }
        }
        return entity;
    }
}
