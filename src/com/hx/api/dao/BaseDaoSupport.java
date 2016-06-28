package com.hx.api.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoSupport extends HibernateDaoSupport
{
    public Serializable insert(Serializable entity)
    {
        return getHibernateTemplate().save(entity);
    }
    
    @SuppressWarnings({"unchecked"})
    public <T> T insert(Class<T> entityClass, Serializable entity)
    {
        return (T)insert(entity);
    }
    
    public void update(Serializable entity)
    {
        getHibernateTemplate().update(entity);
    }
    
    public void delete(Serializable entity)
    {
        getHibernateTemplate().delete(entity);
    }
    
    public <T extends Serializable> void deleteByID(Class<T> entityClass, Serializable id)
    {
        delete(getHibernateTemplate().load(entityClass, id));
    }
    
    public <T> T get(Class<T> entityClass, Serializable id)
    {
        return getHibernateTemplate().get(entityClass, id);
    }
    
    public <T> List<T> find(Class<T> entityClass)
    {
        String hql = "FROM " + entityClass.getSimpleName();
        return find(entityClass, hql);
    }
    
    @SuppressWarnings({"unchecked"})
    public <T> List<T> find(Class<T> entityClass, String hql)
    {
        return (List<T>)getHibernateTemplate().find(hql);
    }
    
    @SuppressWarnings({"unchecked"})
    public <T> List<T> find(Class<T> entityClass, String hql, Object... objects)
    {
        return (List<T>)getHibernateTemplate().find(hql, objects);
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> findByNamedParam(Class<T> entityClass, String hql, String[] names, Object[] values)
    {
        return (List<T>)getHibernateTemplate().findByNamedParam(hql, names, values);
    }
    
    public List<?> findByNamedParam(String hql, String[] names, Object[] values)
    {
        return getHibernateTemplate().findByNamedParam(hql, names, values);
    }
    
    public List<?> find(String hql, Object... objects)
    {
        return getHibernateTemplate().find(hql, objects);
    }
    
    public <T> List<T> find(NamedQueryer queryer, Class<T> entityClass)
    {
        return findByNamedParam(entityClass,
            queryer.getHql(),
            queryer.getNames().toArray(new String[queryer.getNames().size()]),
            queryer.getValues().toArray());
    }
    
    public int execute(String hql)
    {
        return getHibernateTemplate().bulkUpdate(hql);
    }
    
    public int execute(String hql, Object... objects)
    {
        return getHibernateTemplate().bulkUpdate(hql, objects);
    }
    
    @SuppressWarnings("unchecked")
    public <T> Pagination<T> find(final NamedQueryer queryer, int pageNo, int pageSize, Class<T> clazz)
    {
        final String[] names = queryer.getNames().toArray(new String[queryer.getNames().size()]);
        final Object[] values = queryer.getValues().toArray();
        
        final String[] cnames = queryer.getCountNames().toArray(new String[queryer.getCountNames().size()]);
        final Object[] cvalues = queryer.getCountValues().toArray();
        int totalCount = ((Number)findByNamedParam(queryer.getCountHql(), cnames, cvalues).get(0)).intValue();
        final Pagination<T> pagination = new Pagination<T>(pageNo, pageSize, totalCount);
        int minPageNo = 1;
        int maxPageNo = pagination.getTotalPage();
        pageNo = pageNo < minPageNo ? minPageNo : pageNo;
        pageNo = pageNo > maxPageNo ? maxPageNo : pageNo;
        pagination.setPageNo(pageNo);
        
        if (totalCount == 0)
        {
            List<T> records = Collections.emptyList();
            pagination.setRecords(records);
            return pagination;
        }
        
        final int first = (pageNo - 1) * pageSize;
        List<T> list = (List<T>)this.getHibernateTemplate().execute(new HibernateCallback<T>()
        {
            @Override
            public T doInHibernate(Session session) throws HibernateException
            {
                Query query = session.createQuery(queryer.getHql());
                
                if (values != null)
                {
                    Object value;
                    
                    for (int i = 0; i < values.length; i++)
                    {
                        value = values[i];
                        
                        if (value instanceof Collection)
                        {
                            query.setParameterList(names[i], (Collection<?>)value);
                        }
                        else if (value instanceof Object[])
                        {
                            query.setParameterList(names[i], (Object[])value);
                        }
                        else
                        {
                            query.setParameter(names[i], value);
                        }
                    }
                }
                
                query.setFirstResult(first);
                query.setMaxResults(pagination.getPageSize());
                return (T)query.list();
            }
        });
        pagination.setRecords(list);
        return pagination;
    }
    
    @SuppressWarnings("unchecked")
    public <E, T> Pagination<T> find(final NamedQueryer queryer, int pageNo, int pageSize, IEntityWrapper<E, T> wrapper)
    {
        Pagination<E> pagination = (Pagination<E>)find(queryer, pageNo, pageSize, Object.class);
        Pagination<T> result = new Pagination<T>(pagination);
        List<T> records = new ArrayList<T>();
        
        for (E record : pagination.getRecords())
        {
            records.add(wrapper.wrap(record));
        }
        
        result.setRecords(records);
        return result;
    }
    
    @Autowired
    protected final void autowiredSessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
}
