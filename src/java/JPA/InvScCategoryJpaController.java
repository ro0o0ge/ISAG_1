/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Branch;
import Entity.InvScCategory;
import Entity.IsagCompany;
import Entity.IsagUser;
import JPA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdo
 */
public class InvScCategoryJpaController implements Serializable {

    public InvScCategoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InvScCategory invScCategory) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Branch brnno = invScCategory.getBrnno();
            if (brnno != null) {
                brnno = em.getReference(brnno.getClass(), brnno.getId());
                invScCategory.setBrnno(brnno);
            }
            IsagCompany companyId = invScCategory.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                invScCategory.setCompanyId(companyId);
            }
            IsagUser createdBy = invScCategory.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                invScCategory.setCreatedBy(createdBy);
            }
            IsagUser modifiedBy = invScCategory.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                invScCategory.setModifiedBy(modifiedBy);
            }
            em.persist(invScCategory);
            if (brnno != null) {
                brnno.getInvScCategoryList().add(invScCategory);
                brnno = em.merge(brnno);
            }
            if (companyId != null) {
                companyId.getInvScCategoryList().add(invScCategory);
                companyId = em.merge(companyId);
            }
            if (createdBy != null) {
                createdBy.getInvScCategoryList().add(invScCategory);
                createdBy = em.merge(createdBy);
            }
            if (modifiedBy != null) {
                modifiedBy.getInvScCategoryList().add(invScCategory);
                modifiedBy = em.merge(modifiedBy);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InvScCategory invScCategory) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InvScCategory persistentInvScCategory = em.find(InvScCategory.class, invScCategory.getCategoryID());
            Branch brnnoOld = persistentInvScCategory.getBrnno();
            Branch brnnoNew = invScCategory.getBrnno();
            IsagCompany companyIdOld = persistentInvScCategory.getCompanyId();
            IsagCompany companyIdNew = invScCategory.getCompanyId();
            IsagUser createdByOld = persistentInvScCategory.getCreatedBy();
            IsagUser createdByNew = invScCategory.getCreatedBy();
            IsagUser modifiedByOld = persistentInvScCategory.getModifiedBy();
            IsagUser modifiedByNew = invScCategory.getModifiedBy();
            if (brnnoNew != null) {
                brnnoNew = em.getReference(brnnoNew.getClass(), brnnoNew.getId());
                invScCategory.setBrnno(brnnoNew);
            }
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                invScCategory.setCompanyId(companyIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                invScCategory.setCreatedBy(createdByNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                invScCategory.setModifiedBy(modifiedByNew);
            }
            invScCategory = em.merge(invScCategory);
            if (brnnoOld != null && !brnnoOld.equals(brnnoNew)) {
                brnnoOld.getInvScCategoryList().remove(invScCategory);
                brnnoOld = em.merge(brnnoOld);
            }
            if (brnnoNew != null && !brnnoNew.equals(brnnoOld)) {
                brnnoNew.getInvScCategoryList().add(invScCategory);
                brnnoNew = em.merge(brnnoNew);
            }
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getInvScCategoryList().remove(invScCategory);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getInvScCategoryList().add(invScCategory);
                companyIdNew = em.merge(companyIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getInvScCategoryList().remove(invScCategory);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getInvScCategoryList().add(invScCategory);
                createdByNew = em.merge(createdByNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.getInvScCategoryList().remove(invScCategory);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                modifiedByNew.getInvScCategoryList().add(invScCategory);
                modifiedByNew = em.merge(modifiedByNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invScCategory.getCategoryID();
                if (findInvScCategory(id) == null) {
                    throw new NonexistentEntityException("The invScCategory with id " + id + " no longer exists.");
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
            InvScCategory invScCategory;
            try {
                invScCategory = em.getReference(InvScCategory.class, id);
                invScCategory.getCategoryID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invScCategory with id " + id + " no longer exists.", enfe);
            }
            Branch brnno = invScCategory.getBrnno();
            if (brnno != null) {
                brnno.getInvScCategoryList().remove(invScCategory);
                brnno = em.merge(brnno);
            }
            IsagCompany companyId = invScCategory.getCompanyId();
            if (companyId != null) {
                companyId.getInvScCategoryList().remove(invScCategory);
                companyId = em.merge(companyId);
            }
            IsagUser createdBy = invScCategory.getCreatedBy();
            if (createdBy != null) {
                createdBy.getInvScCategoryList().remove(invScCategory);
                createdBy = em.merge(createdBy);
            }
            IsagUser modifiedBy = invScCategory.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.getInvScCategoryList().remove(invScCategory);
                modifiedBy = em.merge(modifiedBy);
            }
            em.remove(invScCategory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InvScCategory> findInvScCategoryEntities() {
        return findInvScCategoryEntities(true, -1, -1);
    }

    public List<InvScCategory> findInvScCategoryEntities(int maxResults, int firstResult) {
        return findInvScCategoryEntities(false, maxResults, firstResult);
    }

    private List<InvScCategory> findInvScCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InvScCategory.class));
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

    public InvScCategory findInvScCategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InvScCategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvScCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InvScCategory> rt = cq.from(InvScCategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
