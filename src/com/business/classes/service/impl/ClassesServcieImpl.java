package com.business.classes.service.impl;

import com.business.classes.dao.ClassesDao;
import com.business.classes.po.ClassesEntity;
import com.business.classes.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pxc on 2018/4/20.
 */
@Service
public class ClassesServcieImpl implements ClassesService {

    @Autowired
    private ClassesDao classesDao;

    @Override
    public ClassesEntity getById(String id) {
        return classesDao.getEntityById(id);
    }
}
