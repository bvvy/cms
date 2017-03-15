package org.bvvy.basic.dao;

import org.bvvy.basic.model.Pager;

import java.util.List;
import java.util.Map;

public interface IBasicDao<T> {

    T add(T t);

    void update(T t);

    void delete(int id);

    T load(int id);

    List<T> list(String hql);

    List<T> list(String hql, Object[] args);

    List<T> list(String hql, Object arg);

    List<T> list(String hql, Object[] args, Map<String, Object> alias);

    List<T> list(String hql, Map<String, Object> alias);

    Pager<T> find(String hql);

    Pager<T> find(String hql, Object[] args);

    Pager<T> find(String hql, Object arg);

    Pager<T> find(String hql, Object[] args, Map<String, Object> alias);

    Pager<T> find(String hql, Map<String, Object> alias);

    Object queryObject(String hql, Object[] args);

    Object queryObject(String hql, Object args);

    Object queryObject(String hql);

    Object queryObject(String hql, Object[] args, Map<String, Object> alias);

    void updateByHql(String hql, Object[] args);

    void updateByHql(String hql, Object arg);

    void updateByHql(String hql);

    <N>List<N> listBySql(String sql, Map<String,Object> alias,Class<?> clz, boolean hasEntity);

    <N>List<N> listBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity);

    <N>List<N> listBySql(String sql, Object arg, Class<?> clz, boolean hasEntity);

    <N>List<N> listBySql(String sql, Class<?> clz, boolean hasEntity);

    <N>List<N> listBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity);

    Pager<Object>findBySql(String sql, Map<String,Object> alias, Class<Object> clz, boolean hasEntity);

    Pager<Object>findBySql(String hql, Object[] args, Class<Object> clz, boolean hasEntity);

    Pager<Object>findBySql(String hql, Object arg, Class<Object> clz, boolean hasEntity);

    Pager<Object>findBySql(String hql, Class<Object> clz, boolean hasEntity);

    Pager<Object>findBySql(String hql, Object[] args, Map<String, Object> alias, Class<Object> clz, boolean hasEntity);


}
