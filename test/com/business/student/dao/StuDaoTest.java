package com.business.student.dao;

import com.business.student.po.StuEntity;
import com.order.cc.sys.dao.FoPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by pxc on 2018/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class StuDaoTest {

    @Autowired
    private StuDao stuDao;

    @Test
    public void getStuPage() throws Exception {
    }

    @Test
    public void getStuList() throws Exception {
    }

    @Test
    public void getStuList1() throws Exception {
    }

    @Test
    public void getBySql() throws Exception {
        List<Map> mapList = stuDao.getBySql(new StuEntity());
        assertNotNull(mapList);
    }

    @Test
    public void getBySqlFoPage() throws Exception {
        FoPage page = stuDao.getBySqlFoPage(new StuEntity(),1,3);
        assertNotNull(page);
    }
}