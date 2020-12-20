package com.gasolinerasoledadsacv.controller;

import com.gasolinerasoledadsacv.controller.exceptions.NonexistentEntityException;
import com.gasolinerasoledadsacv.entity.Checador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.gasolinerasoledadsacv.entity.Empleado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Carlos
 */
public class ChecadorJpaController implements Serializable {

    public ChecadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Checador checador) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado idEmpleado = checador.getIdEmpleado();
            if (idEmpleado != null) {
                idEmpleado = em.getReference(idEmpleado.getClass(), idEmpleado.getIdEmpleado());
                checador.setIdEmpleado(idEmpleado);
            }
            em.persist(checador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Checador checador) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Checador persistentChecador = em.find(Checador.class, checador.getIdChecador());
            Empleado idEmpleadoOld = persistentChecador.getIdEmpleado();
            Empleado idEmpleadoNew = checador.getIdEmpleado();
            if (idEmpleadoNew != null) {
                idEmpleadoNew = em.getReference(idEmpleadoNew.getClass(), idEmpleadoNew.getIdEmpleado());
                checador.setIdEmpleado(idEmpleadoNew);
            }
            checador = em.merge(checador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Checador checador = em.getReference(Checador.class, id);
            checador.getIdChecador();
            em.remove(checador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Checador> findChecadorEntities() {
        return findChecadorEntities(true, -1, -1);
    }

    public List<Checador> findChecadorEntities(int maxResults, int firstResult) {
        return findChecadorEntities(false, maxResults, firstResult);
    }

    private List<Checador> findChecadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Checador.class));
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

    public Checador findChecador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Checador.class, id);
        } finally {
            em.close();
        }
    }

    public int getChecadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Checador> rt = cq.from(Checador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
