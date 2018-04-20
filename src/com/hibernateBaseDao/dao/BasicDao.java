package com.hibernateBaseDao.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DAO公用方法封装接口
 *
 * @author liujiegang
 */
public interface BasicDao<T extends Object> {

    /**
     * 查询指定对象
     *
     * @param id
     * @return
     */
    T getEntityById(Serializable id);

    /**
     * 保存实体对象
     *
     * @param entity
     */
    void saveEntity(T entity);

    /**
     * 保存或更新实体对象
     *
     * @param entity
     */
    void saveOrUpdateEntity(T entity);

    /**
     * 修改实体对象
     *
     * @param entity
     */
     void updateEntity(T entity);

    /**
     * 删除实体对象
     *
     * @param entity
     */
     void deleteEntity(T entity);

    /**
     * 删除实体对象
     *
     * @param clazz
     * @param id
     */
     void deleteEntityById(Class clazz, Serializable id);

    /**
     * 查询对象集合
     *
     * @param hql
     * @param params
     * @return
     */
     List<?> find(String hql, Object... params);

    /**
     * 查询结果List<Map>
     *
     * @param sql
     * @param params
     * @return
     */
     List<Map> findForMap(String sql, Object... params);

    /**
     * 查询指定实体对象
     *
     * @param hql
     * @param params
     * @return
     */
     T findEntity(String hql, Object... params);

    /**
     * hql统计
     *
     * @param hql
     * @param params
     * @return
     */
     int countHql(String hql, Object... params);

    /**
     * sql统计
     *
     * @param sql
     * @param params
     * @return
     */
     int countSql(String sql, Object... params);

    /**
     * flush
     */
    void flush();

    /**
     * Completely clear the session and is used to dissociate/disconnect all the objects from the session.
     */
    void clear();

    /**
     * Removes the object from the session. This method is used to dissociate/disconnect the specified object from the session
     */
    void evict(Object obj);
}
