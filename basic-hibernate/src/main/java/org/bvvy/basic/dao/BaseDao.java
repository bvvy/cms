package org.bvvy.basic.dao;

import org.bvvy.basic.model.Pager;
import org.bvvy.basic.model.SystemContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBasicDao<T> {
    private SessionFactory sessionFactory;
    private Class clz;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Class<T> getClz() {
        if (clz == null) {
            //获取泛型的Class对象
            clz = ((Class<T>)
                    (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }


    private String initSort(String hql) {
        String sort = SystemContext.getSort();
        String order = SystemContext.getOrder();
        if (sort != null && !"".equals(sort.trim())) {
            hql += " order by " + sort;
            if (!"desc".equals(order)) {
                hql += " asc";
            } else {
                hql += " desc";
            }
        }
        return hql;
    }

    private void setAliasParameter(Query query, Map<String, Object> alias) {
        if (alias != null) {
            Set<String> keys = alias.keySet();
            for (String key : keys) {
                Object val = alias.get(key);
                if (val instanceof Collection) {
                    query.setParameterList(key, (Collection) val);
                } else {
                    query.setParameter(key, val);
                }
            }
        }
    }

    private void setParameter(Query query, Object[] args) {
        if (args != null && args.length > 0) {
            int index = 0;
            for (Object arg : args) {
                query.setParameter(index++, arg);
            }
        }

    }

    private String getCountHql(String hql, boolean isHql) {
        String s = hql.substring(0, hql.indexOf("from"));
        if ("".equals(s.trim())) hql = "select count(*) " + hql;
        else hql = hql.replace(s, "select count(*) ");
        if (isHql)
            hql = hql.replace("fetch", "");
        return hql;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public T add(T t) {
        getSession().save(t);
        return t;
    }

    public void update(T t) {
        getSession().update(t);

    }

    public void delete(int id) {
        getSession().delete(this.load(id));
    }

    public T load(int id) {
        return (T) getSession().load(getClz(), id);
    }

    public List<T> list(String hql) {
        return this.list(hql, null, null);
    }

    public List<T> list(String hql, Object[] args) {

        return this.list(hql, args, null);
    }

    public List<T> list(String hql, Object arg) {
        return this.list(hql, new Object[]{arg});
    }

    public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
        hql = this.initSort(hql);
        Query query = getSession().createQuery(hql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        return query.list();
    }

    public List<T> list(String hql, Map<String, Object> alias) {
        return null;
    }

    public Pager<T> find(String hql) {
        return this.find(hql, null, null);
    }

    public Pager<T> find(String hql, Object[] args) {
        return this.find(hql, args, null);
    }

    public Pager<T> find(String hql, Object arg) {
        return find(hql, new Object[]{arg});
    }

    public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
        hql = initSort(hql);
        String countHql = getCountHql(hql, true);

        Query query = getSession().createQuery(hql);
        Query countQuery = getSession().createQuery(countHql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        setAliasParameter(countQuery, alias);
        setParameter(countQuery, args);

        Integer pageSize = SystemContext.getPageSize();
        Integer pageOffset = SystemContext.getPageOffset();

        if (pageOffset == null || pageOffset < 0) pageOffset = 0;
        if (pageSize == null || pageSize < 0) pageSize = 15;

        query.setFirstResult(pageOffset).setMaxResults(pageSize);
        long total =  (Long)countQuery.uniqueResult();

        Pager<T> pages = new Pager<T>();
        pages.setDatas(query.list());
        pages.setOffset(pageOffset);
        pages.setSize(pageSize);
        pages.setTotal(total);
        return pages;
    }

    public Pager<T> find(String hql, Map<String, Object> alias) {

        return this.find(hql, null, alias);
    }

    public Object queryObject(String hql, Object[] args) {
        return this.queryObject(hql, args, null);
    }

    public Object queryObject(String hql, Object args) {
        return this.queryObject(hql, new Object[]{args});
    }

    public Object queryObject(String hql) {
        return queryObject(hql, null, null);
    }

    @Override
    public Object queryObject(String hql, Object[] args, Map<String, Object> alias) {
        Query query = getSession().createQuery(hql);
        setParameter(query, args);
        setAliasParameter(query, alias);
        return query.uniqueResult();
    }

    public void updateByHql(String hql, Object[] args) {
        Query query = getSession().createQuery(hql);
        setParameter(query, args);
        query.executeUpdate();
    }

    public void updateByHql(String hql, Object arg) {
        this.updateByHql(hql, new Object[]{arg});
    }

    public void updateByHql(String hql) {
        this.updateByHql(hql, null);
    }

    public <N>List<N>  listBySql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, null, alias, clz, hasEntity);
    }

    public <N>List<N>  listBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, args, null, clz, hasEntity);
    }

    public <N>List<N>  listBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, new Object[]{arg}, null, clz, hasEntity);
    }

    public<N>List<N>  listBySql(String sql, Class<?> clz, boolean hasEntity) {
        return listBySql(sql, null, null, clz, hasEntity);
    }

    public <N>List<N>  listBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        sql = initSort(sql);
        SQLQuery sq = getSession().createSQLQuery(sql);
        setAliasParameter(sq, alias);
        setParameter(sq, args);
        if (hasEntity) {
            sq.addEntity(clz);
        } else {
            sq.setResultTransformer(Transformers.aliasToBean(clz));
        }
        return sq.list();
    }

    public Pager<Object> findBySql(String sql, Map<String, Object> alias, Class<Object> clz, boolean hasEntity) {
        return this.findBySql(sql, null, alias, clz, hasEntity);
    }

    public Pager<Object> findBySql(String sql, Object[] args, Class<Object> clz, boolean hasEntity) {
        return this.findBySql(sql, args, null, clz, hasEntity);
    }

    public Pager<Object> findBySql(String sql, Object arg, Class<Object> clz, boolean hasEntity) {
        return this.findBySql(sql, new Object[]{arg}, null, clz, hasEntity);
    }

    public Pager<Object> findBySql(String sql, Class<Object> clz, boolean hasEntity) {
        return this.findBySql(sql, null, null, clz, hasEntity);
    }

    public Pager<Object> findBySql(String sql, Object[] args, Map<String, Object> alias, Class<Object> clz, boolean hasEntity) {
        String cq = getCountHql(sql, false);
        cq = initSort(cq);
        sql = initSort(sql);
        SQLQuery sq = getSession().createSQLQuery(cq);
        SQLQuery cquery = getSession().createSQLQuery(sql);
        setAliasParameter(sq, alias);
        setAliasParameter(cquery, alias);
        setParameter(sq, args);
        setParameter(cquery, args);
        Pager<Object> pages = new Pager<>();

        Integer pageSize = SystemContext.getPageSize();
        Integer pageOffset = SystemContext.getPageOffset();

        if (pageOffset == null || pageOffset < 0) pageOffset = 0;
        if (pageSize == null || pageSize < 0) pageSize = 15;

        if (hasEntity) {
            sq.addEntity(clz);
        } else {
            sq.setResultTransformer(Transformers.aliasToBean(clz));
        }
        long total = (Long) cquery.uniqueResult();

        pages.setDatas(sq.list());
        pages.setOffset(pageOffset);
        pages.setSize(pageSize);
        pages.setTotal(total);
        return pages;
    }
}
