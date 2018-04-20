package com.business.student.dao.impl;

import com.business.student.dao.StuDao;
import com.business.student.po.StuEntity;
import com.hibernateBaseDao.dao.impl.BasicDaoImpl;
import com.order.cc.sys.dao.FoHQLQuery;
import com.order.cc.sys.dao.FoPage;
import com.order.cc.sys.dao.FoSQLQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pxc on 2018/4/20.
 */
@Repository("stuDao")
public class StuDaoImpl extends BasicDaoImpl<StuEntity> implements StuDao {

    @Override
    public FoPage getStuPage(StuEntity stuEntity, int pageNum, int pageSize) {
        FoHQLQuery query = new FoHQLQuery();
        String hql = "from StuEntity s left join fetch s.classesEntity c where 1=1";
        String condition = getConditions(stuEntity, query);
        hql += condition;
        query.setHQL(hql + " order by s.age asc");
        String countHql = "select count(*) from StuEntity s join s.classesEntity c where 1=1 ";
        countHql += condition;
        query.setCountHQL(countHql);
        query.setPageNum(pageNum);
        query.setpageSize(pageSize);
        return this.execFoPageQuery(query);
    }

    @Override
    public List<StuEntity> getStuList(StuEntity stuEntity) {
        FoHQLQuery query = new FoHQLQuery();
        String hql = "from StuEntity s left join fetch s.classesEntity c where 1=1";
        String condition = getConditions(stuEntity, query);
        hql += condition;
        query.setHQL(hql + " order by s.age asc");
        return this.execFoQuery(query);
    }

    @Override
    public List<StuEntity> getStuList(String name, Integer age) {
        String hql = "from StuEntity s left join fetch s.classesEntity c where 1=1 and s.name=? and age= ? ";
        return this.find(hql,name,age);
    }

    @Override
    public List<Map> getBySql(StuEntity stuEntity) {

        FoSQLQuery query = new FoSQLQuery();
        String sql = "SELECT\n" +
                "\ts.`name` AS \"name\",\n" +
                "\ts.age AS \"age\"\n" +
                "FROM\n" +
                "\ttab_stu s";
        query.setSQL(sql);
        query.setEntityMap(true);
        return execFoQuery(query);
    }

    @Override
    public FoPage getBySqlFoPage(StuEntity stuEntity, int pageNum, int pageSize) {
        FoSQLQuery query = new FoSQLQuery();
        String sql = "SELECT\n" +
                "\ts.`name` AS \"name\",\n" +
                "\ts.age AS \"age\"\n" +
                "FROM\n" +
                "\ttab_stu s";
        String countHql = "select count(*) as count from tab_stu s";
        query.setSQL(sql);
        query.setCountSQL(countHql);
        query.setEntityMap(true);
        query.setPageNum(pageNum);
        query.setpageSize(pageSize);
        return this.execFoPageQuery(query);
    }

    /**
     * hql条件查询
     *
     * @param stuEntity
     * @param query
     * @return
     */
    private String getConditions(StuEntity stuEntity, FoHQLQuery query) {
        String conditions = "";
        if (StringUtils.isNotBlank(stuEntity.getName())) {
            conditions += " and s.name = :name ";
            query.setString("name", stuEntity.getName());
        }
        if (null != stuEntity.getAge()) {
            conditions += " and s.age = :age ";
            query.setInt("age", stuEntity.getAge());
        }

        return conditions;
    }


}
