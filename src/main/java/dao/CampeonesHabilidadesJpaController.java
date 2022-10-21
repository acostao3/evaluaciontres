/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import huh.evaluaciontres.CampeonesHabilidades;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author yorsh
 */
public class CampeonesHabilidadesJpaController implements Serializable {

    public CampeonesHabilidadesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CampeonesHabilidades campeonesHabilidades) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(campeonesHabilidades);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCampeonesHabilidades(campeonesHabilidades.getCampeonNombre()) != null) {
                throw new PreexistingEntityException("CampeonesHabilidades " + campeonesHabilidades + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CampeonesHabilidades campeonesHabilidades) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            campeonesHabilidades = em.merge(campeonesHabilidades);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = campeonesHabilidades.getCampeonNombre();
                if (findCampeonesHabilidades(id) == null) {
                    throw new NonexistentEntityException("The campeonesHabilidades with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CampeonesHabilidades campeonesHabilidades;
            try {
                campeonesHabilidades = em.getReference(CampeonesHabilidades.class, id);
                campeonesHabilidades.getCampeonNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The campeonesHabilidades with id " + id + " no longer exists.", enfe);
            }
            em.remove(campeonesHabilidades);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CampeonesHabilidades> findCampeonesHabilidadesEntities() {
        return findCampeonesHabilidadesEntities(true, -1, -1);
    }

    public List<CampeonesHabilidades> findCampeonesHabilidadesEntities(int maxResults, int firstResult) {
        return findCampeonesHabilidadesEntities(false, maxResults, firstResult);
    }

    private List<CampeonesHabilidades> findCampeonesHabilidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CampeonesHabilidades.class));
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

    public CampeonesHabilidades findCampeonesHabilidades(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CampeonesHabilidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getCampeonesHabilidadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CampeonesHabilidades> rt = cq.from(CampeonesHabilidades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
