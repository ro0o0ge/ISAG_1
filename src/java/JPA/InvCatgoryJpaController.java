/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import Entity.InvCatgory;
import JPA.exceptions.NonexistentEntityException;
import JPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author akef
 */
public class InvCatgoryJpaController implements Serializable {

    public InvCatgoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InvCatgory invCatgory) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(invCatgory);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInvCatgory(invCatgory.getCatgoryId()) != null) {
                throw new PreexistingEntityException("InvCatgory " + invCatgory + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InvCatgory invCatgory) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            invCatgory = em.merge(invCatgory);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invCatgory.getCatgoryId();
                if (findInvCatgory(id) == null) {
                    throw new NonexistentEntityException("The invCatgory with id " + id + " no longer exists.");
                }
            }
            throw ex;
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
            InvCatgory invCatgory;
            try {
                invCatgory = em.getReference(InvCatgory.class, id);
                invCatgory.getCatgoryId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invCatgory with id " + id + " no longer exists.", enfe);
            }
            em.remove(invCatgory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InvCatgory> findInvCatgoryEntities() {
        return findInvCatgoryEntities(true, -1, -1);
    }

    public List<InvCatgory> findInvCatgoryEntities(int maxResults, int firstResult) {
        return findInvCatgoryEntities(false, maxResults, firstResult);
    }

    private List<InvCatgory> findInvCatgoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InvCatgory.class));
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

    public InvCatgory findInvCatgory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InvCatgory.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvCatgoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InvCatgory> rt = cq.from(InvCatgory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
