package com.business.classes.dao.impl;

import com.business.classes.dao.ClassesDao;
import com.business.classes.po.ClassesEntity;
import com.hibernateBaseDao.dao.impl.BasicDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by pxc on 2018/4/20.
 */
@Repository
public class ClassesDaoImpl extends BasicDaoImpl<ClassesEntity> implements ClassesDao {
}
