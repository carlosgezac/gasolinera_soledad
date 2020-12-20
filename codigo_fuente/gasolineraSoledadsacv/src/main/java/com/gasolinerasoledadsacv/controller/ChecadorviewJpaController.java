package com.gasolinerasoledadsacv.controller;

import com.gasolinerasoledadsacv.entity.Checadorview;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ChecadorviewJpaController implements Serializable {

    public ChecadorviewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Checadorview> findChecadorviewEntities() {
        return findChecadorviewEntities(true, -1, -1);
    }

    public List<Checadorview> findChecadorviewEntities(int maxResults, int firstResult) {
        return findChecadorviewEntities(false, maxResults, firstResult);
    }

    private List<Checadorview> findChecadorviewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Checadorview.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Checadorview findChecadorview(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Checadorview.class, id);
        } finally {
            em.close();
        }
    }

    public int getChecadorviewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Checadorview> rt = cq.from(Checadorview.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
