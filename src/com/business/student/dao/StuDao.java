package com.business.student.dao;

import com.business.student.po.StuEntity;
import com.hibernateBaseDao.dao.BasicDao;
import com.order.cc.sys.dao.FoPage;

import java.util.List;
import java.util.Map;

/**
 * Created by pxc on 2018/4/20.
 */
public interface StuDao extends BasicDao<StuEntity> {

    /**
     * 分页查询
     *
     * @param stuEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    FoPage getStuPage(StuEntity stuEntity, int pageNum, int pageSize);

    /**
     * 列表查询
     *
     * @param stuEntity
     * @return
     */
    List<StuEntity> getStuList(StuEntity stuEntity);

    /**
     * @param name
     * @param age
     * @return
     */
    List<StuEntity> getStuList(String name, Integer age);

    /**
     * sql查询
     *
     * @return
     */
    List<Map> getBySql(StuEntity stuEntity);

    /**
     * sql分页查询
     *
     * @return
     */
    FoPage getBySqlFoPage(StuEntity stuEntity,int pageNum, int pageSize);
}
