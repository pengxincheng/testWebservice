/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.order.cc.sys.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

// Referenced classes of package com.order.cc.sys.dao:
//            FoHQLQuery, FoSQLQuery, FoNamedQuery, FoPage, 
//            FoQBEQuery, FoQuery

public class FoHibernateDaoSupport extends HibernateDaoSupport {

    public FoHibernateDaoSupport() {
    }

    public FoPage execFoPageQuery(FoQuery foQuery) {
        if (foQuery instanceof FoHQLQuery)
            return execFoPageHQLQuery((FoHQLQuery) foQuery);
        if (foQuery instanceof FoSQLQuery)
            return execFoPageSQLQuery((FoSQLQuery) foQuery);
        if (foQuery instanceof FoNamedQuery)
            return execFoPageNamedQuery((FoNamedQuery) foQuery);
        else
            return null;
    }

    public List execFoQuery(FoQuery foQuery) {
        if (foQuery instanceof FoHQLQuery)
            return execFoHQLQuery((FoHQLQuery) foQuery);
        if (foQuery instanceof FoSQLQuery)
            return execFoSQLQuery((FoSQLQuery) foQuery);
        if (foQuery instanceof FoNamedQuery)
            return execFoNamedQuery((FoNamedQuery) foQuery);
        else
            return null;
    }

    /**
     * @deprecated Method execFoUpdate is deprecated
     */

    public int execFoUpdate(FoQuery foQuery) {
        Integer i = null;
        if (foQuery instanceof FoHQLQuery)
            i = execFoHQLUpdate((FoHQLQuery) foQuery);
        else if (foQuery instanceof FoSQLQuery)
            i = execFoSQLUpdate((FoSQLQuery) foQuery);
        else if (foQuery instanceof FoNamedQuery)
            i = execFoNamedUpdate((FoNamedQuery) foQuery);
        return i.intValue();
    }

    public <T> T execFoQuery1(FoQuery foQuery) {
        List<T> list = execFoQuery(foQuery);
        if (list == null || list.size() == 0)
            return null;
        else
            return list.get(0);
    }

    private FoPage execFoPageHQLQuery(final FoHQLQuery foQuery) {
        return (FoPage) getHibernateTemplate().execute((HibernateCallback<Object>) session -> {
            FoPage foPage = new FoPage();
            int nPageSize = foQuery.getPageSize();
            if (nPageSize <= 0)
                nPageSize = 10;
            int nPageNum = foQuery.getPageNum();
            if (nPageNum <= 0)
                nPageNum = 1;
            foPage.setPageSize(nPageSize);
            foPage.setPageNum(nPageNum);
            int totalCount = 0;
            if (foQuery.getCountHQL() != null && !foQuery.getCountHQL().equals("")) {
                totalCount = ((Number) foQuery.fillQueryStmt(
                        session.createQuery(foQuery.getCountHQL()), true)
                        .uniqueResult()).intValue();
                foPage.setTotalRecordCount(totalCount);
            } else {
               // throw new HibernateException("没有找到统计总数的HQL");
            }
//                if ((nPageNum - 1) * nPageSize >= totalCount) {
//                    foPage.setPageNum(foPage.getPageCount());
//                    nPageNum = foPage.getPageNum();
//                }
            foPage.setDataList(foQuery
                    .fillQueryStmt(session.createQuery(foQuery.getHQL()), false)
                    .setFirstResult((nPageNum - 1) * nPageSize)
                    .setMaxResults(nPageSize).list());
            return foPage;
        });
    }

    protected FoPage execFoPageQBE(FoQBEQuery qbe) {
        FoPage foPage = new FoPage();
        int nPageSize = qbe.getPageSize();
        if (nPageSize <= 0)
            nPageSize = 10;
        int nPageNum = qbe.getPageNum();
        if (nPageNum <= 0)
            nPageNum = 1;
        foPage.setPageSize(nPageSize);
        foPage.setPageNum(nPageNum);
        int totalCount = 0;
        totalCount = qbe.getQbeCriteria().setFirstResult(0).setMaxResults(50)
                .list().size();
        if ((nPageNum - 1) * nPageSize >= totalCount) {
            foPage.setPageNum(foPage.getPageCount());
            nPageNum = foPage.getPageNum();
        }
        foPage.setDataList(qbe.getQbeCriteria()
                .setFirstResult((nPageNum - 1) * nPageSize)
                .setMaxResults(nPageSize).list());
        return foPage;
    }

    private FoPage execFoPageSQLQuery(final FoSQLQuery foQuery) {
        return (FoPage) getHibernateTemplate().execute((HibernateCallback<Object>) session -> {
            FoPage foPage = new FoPage();
            int nPageSize = foQuery.getPageSize();
            if (nPageSize <= 0)
                nPageSize = 10;
            int nPageNum = foQuery.getPageNum();
            if (nPageNum <= 0)
                nPageNum = 1;
            foPage.setPageSize(nPageSize);
            foPage.setPageNum(nPageNum);
            int totalCount = 0;
            SQLQuery query;
            if (foQuery.getCountSQL() != null) {
                query = session.createSQLQuery(foQuery.getCountSQL());
                query.addScalar("COUNT", IntegerType.INSTANCE);
                foQuery.fillQueryStmt(query, true);
                totalCount = ((Number) query.uniqueResult()).intValue();
                foPage.setTotalRecordCount(totalCount);
            } else {
                throw new HibernateException("没有找到统计总数的SQL");
            }
//                if ((nPageNum - 1) * nPageSize >= totalCount) {
//                    foPage.setPageNum(foPage.getPageCount());
//                    nPageNum = foPage.getPageNum();
//                }
            query = session.createSQLQuery(foQuery.getSQL());
            query.setFirstResult((nPageNum - 1) * nPageSize);
            query.setMaxResults(nPageSize);
            foQuery.fillEntity(query);
            foQuery.fillQueryStmt(query, false);
            foPage.setDataList(query.list());
            return foPage;
        });
    }

    /**
     * @deprecated Method execFoPageNamedQuery is deprecated
     */

    private FoPage execFoPageNamedQuery(FoNamedQuery foQuery) {
        return null;
    }

    private List execFoHQLQuery(final FoHQLQuery foQuery) {
        return (List) getHibernateTemplate().execute((HibernateCallback<Object>) session -> foQuery.fillQueryStmt(
                session.createQuery(foQuery.getHQL()),false).list());
    }

    private Integer execFoHQLUpdate(final FoHQLQuery foQuery) {
        return (Integer) getHibernateTemplate().execute(
                (HibernateCallback<Object>) session -> new Integer(foQuery.fillQueryStmt(
                        session.createQuery(foQuery.getHQL()),false)
                        .executeUpdate()));
    }

    private List execFoSQLQuery(final FoSQLQuery foQuery) {
        return (List) getHibernateTemplate().execute((HibernateCallback<Object>) session -> {
            SQLQuery query = session.createSQLQuery(foQuery.getSQL());
            foQuery.fillEntity(query);
            foQuery.fillQueryStmt(query,false);
            return query.list();
        });
    }

    private Integer execFoSQLUpdate(final FoSQLQuery foQuery) {
        return (Integer) getHibernateTemplate().execute(
                (HibernateCallback<Object>) session -> {
                    SQLQuery query = session.createSQLQuery(foQuery
                            .getSQL());
                    foQuery.fillEntity(query);
                    foQuery.fillQueryStmt(query,false);
                    return new Integer(query.executeUpdate());
                });
    }

    private List execFoNamedQuery(final FoNamedQuery foQuery) {
        return (List) getHibernateTemplate().execute((HibernateCallback<Object>) session -> {
            Query query = session.getNamedQuery(foQuery.getName());
            foQuery.fillQueryStmt(query,false);
            return query.list();
        });
    }

    private Integer execFoNamedUpdate(final FoNamedQuery foQuery) {
        return (Integer) getHibernateTemplate().execute(
                (HibernateCallback<Object>) session -> {
                    Query query = session.getNamedQuery(foQuery.getName());
                    foQuery.fillQueryStmt(query,false);
                    return new Integer(query.executeUpdate());
                });
    }

    protected FoPage execFoPageSQLQuery2(final FoSQLQuery foQuery,
            final List retList) {
        return (FoPage) getHibernateTemplate().execute((HibernateCallback<Object>) session -> {
            FoPage foPage = new FoPage();
            int nPageSize = foQuery.getPageSize();
            if (nPageSize <= 0)
                nPageSize = 5;
            int nPageNum = foQuery.getPageNum();
            if (nPageNum <= 0)
                nPageNum = 1;
            foPage.setPageSize(nPageSize);
            foPage.setPageNum(nPageNum);
            int totalCount = 0;
            SQLQuery query;
            if (foQuery.getCountSQL() != null) {
                query = session.createSQLQuery(foQuery.getCountSQL());
                query.addScalar("COUNT", IntegerType.INSTANCE);
                foQuery.fillQueryStmt(query,true);
                totalCount = ((Number) query.uniqueResult()).intValue();
                foPage.setTotalRecordCount(totalCount);
            } else {
                throw new HibernateException("没有找到统计总数的SQL");
            }
//                if ((nPageNum - 1) * nPageSize >= totalCount) {
//                    foPage.setPageNum(foPage.getPageCount());
//                    nPageNum = foPage.getPageNum();
//                }
            query = session.createSQLQuery(foQuery.getSQL());
            query.setFirstResult((nPageNum - 1) * nPageSize);
            query.setMaxResults(nPageSize);
            foQuery.fillEntity(query);
            List pagelist;
            if (nPageNum == foPage.getPageCount())
                pagelist = retList.subList(nPageSize * (nPageNum - 1),
                        totalCount);
            else
                pagelist = retList.subList(nPageSize * (nPageNum - 1),
                        nPageSize * nPageNum);
            foPage.setDataList(pagelist);
            return foPage;
        });
    }

    public FoPage execFoPageFileQuery(final List fileList, final int pageNum,
            final int pageSize) {
        return (FoPage) getHibernateTemplate().execute((HibernateCallback<Object>) session -> {
            FoPage foPage = new FoPage();
            int nPageSize = pageSize;
            if (nPageSize <= 0)
                nPageSize = 10;
            int nPageNum = pageNum;
            if (nPageNum <= 0)
                nPageNum = 1;
            foPage.setPageSize(nPageSize);
            foPage.setPageNum(nPageNum);
            int totalCount = fileList.size();
            foPage.setTotalRecordCount(totalCount);
//                if ((nPageNum - 1) * nPageSize >= totalCount) {
//                    foPage.setPageNum(foPage.getPageCount());
//                    nPageNum = foPage.getPageNum();
//                }
            List pagelist;
            if (nPageNum == foPage.getPageCount())
                pagelist = fileList.subList(nPageSize * (nPageNum - 1),
                        totalCount);
            else
                pagelist = fileList.subList(nPageSize * (nPageNum - 1),
                        nPageSize * nPageNum);
            foPage.setDataList(pagelist);
            return foPage;
        });
    }

//    public synchronized int getSeqValue(final String seqname) {
//        Number n = (Number) getHibernateTemplate().execute(
//                new HibernateCallback<Object>() {
//
//                    public Object doInHibernate(Session session)
//                            throws SQLException, HibernateException {
//                        String dialect = session.connection().getMetaData()
//                                .getDriverName().toLowerCase();
//                        System.out.println((new StringBuilder("dialect="))
//                                .append(dialect).toString());
//                        SQLQuery query = session
//                                .createSQLQuery((new StringBuilder("select   "))
//                                        .append(seqname)
//                                        .append(".nextval     SEQID     from   dual")
//                                        .toString());
//                        if (dialect.indexOf("sql") >= 0)
//                            query = session.createSQLQuery((new StringBuilder(
//                                    "select   ")).append(seqname)
//                                    .append(" SEQID     from   DIC_SEQUENCE")
//                                    .toString());
//                        else if (dialect.indexOf("oracle") >= 0)
//                            query = session
//                                    .createSQLQuery((new StringBuilder(
//                                            "select   "))
//                                            .append(seqname)
//                                            .append(".nextval     SEQID     from   dual")
//                                            .toString());
//                        query.addScalar("SEQID", new IntegerType());
//                        List children = query.list();
//                        Number seqValue = (Number) children.iterator().next();
//                        if (dialect.indexOf("sql") >= 0) {
//                            int i = seqValue.intValue() + 1;
//                            session.createSQLQuery(
//                                    (new StringBuilder(
//                                            "update DIC_SEQUENCE set "))
//                                            .append(seqname).append("=")
//                                            .append(i).toString())
//                                    .executeUpdate();
//                        }
//                        return seqValue;
//                    }
//                });
//        return n.intValue();
//    }

    public Integer execFoQuery2(final FoSQLQuery foQuery) {
        return (Integer) getHibernateTemplate().execute(
                (HibernateCallback<Object>) session -> {
                    int totalCount = 0;
                    if (foQuery.getCountSQL() != null) {
                        SQLQuery query = session.createSQLQuery(foQuery
                                .getCountSQL());
                        query.addScalar("COUNT", IntegerType.INSTANCE);
                        foQuery.fillQueryStmt(query,false);
                        totalCount = ((Number) query.uniqueResult())
                                .intValue();
                    }
                    return new Integer(totalCount);
                });
    }
    
    public Integer execFoCountHql(final FoHQLQuery foQuery) {
        return (Integer) getHibernateTemplate().execute(new HibernateCallback<Object>() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return ((Long)foQuery.fillQueryStmt(session.createQuery(foQuery.getHQL()),false).uniqueResult()).intValue();
            }
        });
    }
}