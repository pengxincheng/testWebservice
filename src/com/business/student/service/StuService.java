package com.business.student.service;

import com.business.student.po.StuEntity;
import com.order.cc.sys.dao.FoPage;

import java.util.List;

/**
 * Created by pxc on 2018/4/20.
 */
public interface StuService {

    /**
     * 保存
     * @param stuEntity
     */
    void saveStu(StuEntity stuEntity);

    /**
     * 分页查询
     * @param stuEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    FoPage getStuPage(StuEntity stuEntity, int pageNum, int pageSize);

    /**
     * 列表查询
     * @param stuEntity
     * @return
     */
    List<StuEntity> getStuList(StuEntity stuEntity);

    /**
     * id查询
     * @param id
     * @return
     */
    StuEntity getById(String id, Class... classes);
}
