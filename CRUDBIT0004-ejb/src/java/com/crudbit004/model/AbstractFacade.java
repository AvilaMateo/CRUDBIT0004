/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudbit004.model;

import com.crudbit004.entities.Autor;
import com.crudbit004.entities.Autor_;
import com.crudbit004.entities.Libro;
import com.crudbit004.entities.Libro_;
import java.util.List;
import static java.util.stream.DoubleStream.builder;
import static java.util.stream.IntStream.builder;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author MATEO AVILA
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

//    public List<T> selecItemEdit() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        cq.where(
//                getEntityManager().getCriteriaBuilder().equal(rt.get("IDAUTOR"), "1001")
//        );
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return q.getResultList();
//    }
//    public List<T> listLibrosAutor() {
////        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
////        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
////        Join<Object, Object> joinAutoLibro = rt.join("idautor");
////        joinAutoLibro.on(getEntityManager().getCriteriaBuilder().equal(rt.get("idautor"), joinAutoLibro.get("autor")));
////
////        cq.multiselect(joinAutoLibro);
////        List<T> resultList = getEntityManager().createQuery(cq).getResultList();
////        return resultList;
//
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<Libro> rt = cq.from(Libro.class);
//// Person.address is an embedded attribute
//        Join<Libro, Autor> personAddress = rt.join(Libro_.autor);
//        personAddress.on(getEntityManager().getCriteriaBuilder().equal(rt.get("autor"), personAddress));
//        
//        cq.multiselect(personAddress);
//        List<T> resultList = getEntityManager().createQuery(cq).getResultList();
//        return resultList;
//
//    }

}
