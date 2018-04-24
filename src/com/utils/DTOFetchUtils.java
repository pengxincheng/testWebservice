package com.utils;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

/**
 * DAO抓取DTO实体对象实用类
 * 
 * @author pxc
 */
public class DTOFetchUtils {

	/**
	 * 装载VO
	 * 
	 * @param o
	 */
	public static void initialize(Object o) {
		if (!Hibernate.isInitialized(o)) {
			Hibernate.initialize(o);
		}
	}

	/**
	 * 若DAO返回的实体为继承子类并返回代理类(延迟加载)时，获取被代理的具体子类
	 * 
	 * @param entityClass
	 * @param entity
	 * @return 获取被代理的具体子类
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProxyTarget(Class<T> entityClass, Object entity) {
		if (entity instanceof HibernateProxy) {
			return (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
					.getImplementation();
		}

		return (T) entity;
	}
}
