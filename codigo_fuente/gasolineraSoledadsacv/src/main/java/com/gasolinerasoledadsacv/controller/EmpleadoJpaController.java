package com.gasolinerasoledadsacv.controller;

import java.io.Serializable;
import com.gasolinerasoledadsacv.entity.Contacto;
import com.gasolinerasoledadsacv.entity.Direccion;
import com.gasolinerasoledadsacv.entity.Empleado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Carlos
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Empleado create(Empleado empleado, Direccion direccion, Contacto contacto) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            empleado.setContacto(contacto);
            empleado.setDireccion(direccion);
            em.persist(direccion);
            em.persist(contacto);
            em.persist(empleado);
            em.getTransaction().commit();
            return empleado;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(empleado.getDireccion());
            em.merge(empleado.getContacto());
            em.merge(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Empleado findByNumeroEmpleado(String numeroEmpleado) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Empleado e WHERE e.numeroEmpleado = :numeroEmpleado", Empleado.class)
                    .setParameter("numeroEmpleado", numeroEmpleado)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Empleado> findByEstatusOrderByNumEmplead(String estatus) {
        EntityManager em = getEntityManager();
        try {
            // return em.createQuery("SELECT e FROM Empleado e WHERE e.estatus LIKE :estatus ORDER BY e.numeroEmpleado", Empleado.class)
            return em.createQuery("SELECT e FROM Empleado e WHERE e.estatus LIKE :estatus", Empleado.class)
                    .setParameter("estatus", estatus)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Empleado> findByEstatusAndNumEmpleadoOrNombreCompleto(String estatus, String coincidencia, int maxResults) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Empleado e WHERE e.estatus LIKE :estatus AND (e.numeroEmpleado LIKE :numeroEmpleado OR CONCAT(e.nombre, ' ', e.apellidoPaterno, ' ', e.apellidoMaterno) LIKE :coincidencia)", Empleado.class)
                    .setParameter("estatus", estatus).setParameter("numeroEmpleado", coincidencia).setParameter("coincidencia", "%" + coincidencia + "%")
                    .setMaxResults(maxResults)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
