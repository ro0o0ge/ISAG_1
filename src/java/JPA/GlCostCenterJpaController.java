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
import Entity.IsagCompany;
import Entity.IsagUser;
import Entity.GlCostCenter;
import Entity.Branch;
import Entity.InvSupplier;
import JPA.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdo
 */
public class GlCostCenterJpaController implements Serializable {

    public GlCostCenterJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GlCostCenter glCostCenter) {
        if (glCostCenter.getInvSupplierList() == null) {
            glCostCenter.setInvSupplierList(new ArrayList<InvSupplier>());
        }
        if (glCostCenter.getGlCostCenterList() == null) {
            glCostCenter.setGlCostCenterList(new ArrayList<GlCostCenter>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagCompany companyId = glCostCenter.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                glCostCenter.setCompanyId(companyId);
            }
            IsagUser createdBy = glCostCenter.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                glCostCenter.setCreatedBy(createdBy);
            }
            IsagUser modifiedBy = glCostCenter.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                glCostCenter.setModifiedBy(modifiedBy);
            }
            GlCostCenter parent = glCostCenter.getParent();
            if (parent != null) {
                parent = em.getReference(parent.getClass(), parent.getId());
                glCostCenter.setParent(parent);
            }
            Branch branchId = glCostCenter.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getId());
                glCostCenter.setBranchId(branchId);
            }
            List<InvSupplier> attachedInvSupplierList = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListInvSupplierToAttach : glCostCenter.getInvSupplierList()) {
                invSupplierListInvSupplierToAttach = em.getReference(invSupplierListInvSupplierToAttach.getClass(), invSupplierListInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList.add(invSupplierListInvSupplierToAttach);
            }
            glCostCenter.setInvSupplierList(attachedInvSupplierList);
            List<GlCostCenter> attachedGlCostCenterList = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterListGlCostCenterToAttach : glCostCenter.getGlCostCenterList()) {
                glCostCenterListGlCostCenterToAttach = em.getReference(glCostCenterListGlCostCenterToAttach.getClass(), glCostCenterListGlCostCenterToAttach.getId());
                attachedGlCostCenterList.add(glCostCenterListGlCostCenterToAttach);
            }
            glCostCenter.setGlCostCenterList(attachedGlCostCenterList);
            em.persist(glCostCenter);
            if (companyId != null) {
                companyId.getGlCostCenterList().add(glCostCenter);
                companyId = em.merge(companyId);
            }
            if (createdBy != null) {
                createdBy.getGlCostCenterList().add(glCostCenter);
                createdBy = em.merge(createdBy);
            }
            if (modifiedBy != null) {
                modifiedBy.getGlCostCenterList().add(glCostCenter);
                modifiedBy = em.merge(modifiedBy);
            }
            if (parent != null) {
                parent.getGlCostCenterList().add(glCostCenter);
                parent = em.merge(parent);
            }
            if (branchId != null) {
                branchId.getGlCostCenterList().add(glCostCenter);
                branchId = em.merge(branchId);
            }
            for (InvSupplier invSupplierListInvSupplier : glCostCenter.getInvSupplierList()) {
                GlCostCenter oldPricelvlOfInvSupplierListInvSupplier = invSupplierListInvSupplier.getPricelvl();
                invSupplierListInvSupplier.setPricelvl(glCostCenter);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
                if (oldPricelvlOfInvSupplierListInvSupplier != null) {
                    oldPricelvlOfInvSupplierListInvSupplier.getInvSupplierList().remove(invSupplierListInvSupplier);
                    oldPricelvlOfInvSupplierListInvSupplier = em.merge(oldPricelvlOfInvSupplierListInvSupplier);
                }
            }
            for (GlCostCenter glCostCenterListGlCostCenter : glCostCenter.getGlCostCenterList()) {
                GlCostCenter oldParentOfGlCostCenterListGlCostCenter = glCostCenterListGlCostCenter.getParent();
                glCostCenterListGlCostCenter.setParent(glCostCenter);
                glCostCenterListGlCostCenter = em.merge(glCostCenterListGlCostCenter);
                if (oldParentOfGlCostCenterListGlCostCenter != null) {
                    oldParentOfGlCostCenterListGlCostCenter.getGlCostCenterList().remove(glCostCenterListGlCostCenter);
                    oldParentOfGlCostCenterListGlCostCenter = em.merge(oldParentOfGlCostCenterListGlCostCenter);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GlCostCenter glCostCenter) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GlCostCenter persistentGlCostCenter = em.find(GlCostCenter.class, glCostCenter.getId());
            IsagCompany companyIdOld = persistentGlCostCenter.getCompanyId();
            IsagCompany companyIdNew = glCostCenter.getCompanyId();
            IsagUser createdByOld = persistentGlCostCenter.getCreatedBy();
            IsagUser createdByNew = glCostCenter.getCreatedBy();
            IsagUser modifiedByOld = persistentGlCostCenter.getModifiedBy();
            IsagUser modifiedByNew = glCostCenter.getModifiedBy();
            GlCostCenter parentOld = persistentGlCostCenter.getParent();
            GlCostCenter parentNew = glCostCenter.getParent();
            Branch branchIdOld = persistentGlCostCenter.getBranchId();
            Branch branchIdNew = glCostCenter.getBranchId();
            List<InvSupplier> invSupplierListOld = persistentGlCostCenter.getInvSupplierList();
            List<InvSupplier> invSupplierListNew = glCostCenter.getInvSupplierList();
            List<GlCostCenter> glCostCenterListOld = persistentGlCostCenter.getGlCostCenterList();
            List<GlCostCenter> glCostCenterListNew = glCostCenter.getGlCostCenterList();
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                glCostCenter.setCompanyId(companyIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                glCostCenter.setCreatedBy(createdByNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                glCostCenter.setModifiedBy(modifiedByNew);
            }
            if (parentNew != null) {
                parentNew = em.getReference(parentNew.getClass(), parentNew.getId());
                glCostCenter.setParent(parentNew);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getId());
                glCostCenter.setBranchId(branchIdNew);
            }
            List<InvSupplier> attachedInvSupplierListNew = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListNewInvSupplierToAttach : invSupplierListNew) {
                invSupplierListNewInvSupplierToAttach = em.getReference(invSupplierListNewInvSupplierToAttach.getClass(), invSupplierListNewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierListNew.add(invSupplierListNewInvSupplierToAttach);
            }
            invSupplierListNew = attachedInvSupplierListNew;
            glCostCenter.setInvSupplierList(invSupplierListNew);
            List<GlCostCenter> attachedGlCostCenterListNew = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterListNewGlCostCenterToAttach : glCostCenterListNew) {
                glCostCenterListNewGlCostCenterToAttach = em.getReference(glCostCenterListNewGlCostCenterToAttach.getClass(), glCostCenterListNewGlCostCenterToAttach.getId());
                attachedGlCostCenterListNew.add(glCostCenterListNewGlCostCenterToAttach);
            }
            glCostCenterListNew = attachedGlCostCenterListNew;
            glCostCenter.setGlCostCenterList(glCostCenterListNew);
            glCostCenter = em.merge(glCostCenter);
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getGlCostCenterList().remove(glCostCenter);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getGlCostCenterList().add(glCostCenter);
                companyIdNew = em.merge(companyIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getGlCostCenterList().remove(glCostCenter);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getGlCostCenterList().add(glCostCenter);
                createdByNew = em.merge(createdByNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.getGlCostCenterList().remove(glCostCenter);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                modifiedByNew.getGlCostCenterList().add(glCostCenter);
                modifiedByNew = em.merge(modifiedByNew);
            }
            if (parentOld != null && !parentOld.equals(parentNew)) {
                parentOld.getGlCostCenterList().remove(glCostCenter);
                parentOld = em.merge(parentOld);
            }
            if (parentNew != null && !parentNew.equals(parentOld)) {
                parentNew.getGlCostCenterList().add(glCostCenter);
                parentNew = em.merge(parentNew);
            }
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getGlCostCenterList().remove(glCostCenter);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getGlCostCenterList().add(glCostCenter);
                branchIdNew = em.merge(branchIdNew);
            }
            for (InvSupplier invSupplierListOldInvSupplier : invSupplierListOld) {
                if (!invSupplierListNew.contains(invSupplierListOldInvSupplier)) {
                    invSupplierListOldInvSupplier.setPricelvl(null);
                    invSupplierListOldInvSupplier = em.merge(invSupplierListOldInvSupplier);
                }
            }
            for (InvSupplier invSupplierListNewInvSupplier : invSupplierListNew) {
                if (!invSupplierListOld.contains(invSupplierListNewInvSupplier)) {
                    GlCostCenter oldPricelvlOfInvSupplierListNewInvSupplier = invSupplierListNewInvSupplier.getPricelvl();
                    invSupplierListNewInvSupplier.setPricelvl(glCostCenter);
                    invSupplierListNewInvSupplier = em.merge(invSupplierListNewInvSupplier);
                    if (oldPricelvlOfInvSupplierListNewInvSupplier != null && !oldPricelvlOfInvSupplierListNewInvSupplier.equals(glCostCenter)) {
                        oldPricelvlOfInvSupplierListNewInvSupplier.getInvSupplierList().remove(invSupplierListNewInvSupplier);
                        oldPricelvlOfInvSupplierListNewInvSupplier = em.merge(oldPricelvlOfInvSupplierListNewInvSupplier);
                    }
                }
            }
            for (GlCostCenter glCostCenterListOldGlCostCenter : glCostCenterListOld) {
                if (!glCostCenterListNew.contains(glCostCenterListOldGlCostCenter)) {
                    glCostCenterListOldGlCostCenter.setParent(null);
                    glCostCenterListOldGlCostCenter = em.merge(glCostCenterListOldGlCostCenter);
                }
            }
            for (GlCostCenter glCostCenterListNewGlCostCenter : glCostCenterListNew) {
                if (!glCostCenterListOld.contains(glCostCenterListNewGlCostCenter)) {
                    GlCostCenter oldParentOfGlCostCenterListNewGlCostCenter = glCostCenterListNewGlCostCenter.getParent();
                    glCostCenterListNewGlCostCenter.setParent(glCostCenter);
                    glCostCenterListNewGlCostCenter = em.merge(glCostCenterListNewGlCostCenter);
                    if (oldParentOfGlCostCenterListNewGlCostCenter != null && !oldParentOfGlCostCenterListNewGlCostCenter.equals(glCostCenter)) {
                        oldParentOfGlCostCenterListNewGlCostCenter.getGlCostCenterList().remove(glCostCenterListNewGlCostCenter);
                        oldParentOfGlCostCenterListNewGlCostCenter = em.merge(oldParentOfGlCostCenterListNewGlCostCenter);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = glCostCenter.getId();
                if (findGlCostCenter(id) == null) {
                    throw new NonexistentEntityException("The glCostCenter with id " + id + " no longer exists.");
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
            GlCostCenter glCostCenter;
            try {
                glCostCenter = em.getReference(GlCostCenter.class, id);
                glCostCenter.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The glCostCenter with id " + id + " no longer exists.", enfe);
            }
            IsagCompany companyId = glCostCenter.getCompanyId();
            if (companyId != null) {
                companyId.getGlCostCenterList().remove(glCostCenter);
                companyId = em.merge(companyId);
            }
            IsagUser createdBy = glCostCenter.getCreatedBy();
            if (createdBy != null) {
                createdBy.getGlCostCenterList().remove(glCostCenter);
                createdBy = em.merge(createdBy);
            }
            IsagUser modifiedBy = glCostCenter.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.getGlCostCenterList().remove(glCostCenter);
                modifiedBy = em.merge(modifiedBy);
            }
            GlCostCenter parent = glCostCenter.getParent();
            if (parent != null) {
                parent.getGlCostCenterList().remove(glCostCenter);
                parent = em.merge(parent);
            }
            Branch branchId = glCostCenter.getBranchId();
            if (branchId != null) {
                branchId.getGlCostCenterList().remove(glCostCenter);
                branchId = em.merge(branchId);
            }
            List<InvSupplier> invSupplierList = glCostCenter.getInvSupplierList();
            for (InvSupplier invSupplierListInvSupplier : invSupplierList) {
                invSupplierListInvSupplier.setPricelvl(null);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
            }
            List<GlCostCenter> glCostCenterList = glCostCenter.getGlCostCenterList();
            for (GlCostCenter glCostCenterListGlCostCenter : glCostCenterList) {
                glCostCenterListGlCostCenter.setParent(null);
                glCostCenterListGlCostCenter = em.merge(glCostCenterListGlCostCenter);
            }
            em.remove(glCostCenter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GlCostCenter> findGlCostCenterEntities() {
        return findGlCostCenterEntities(true, -1, -1);
    }

    public List<GlCostCenter> findGlCostCenterEntities(int maxResults, int firstResult) {
        return findGlCostCenterEntities(false, maxResults, firstResult);
    }

    private List<GlCostCenter> findGlCostCenterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GlCostCenter.class));
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

    public GlCostCenter findGlCostCenter(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GlCostCenter.class, id);
        } finally {
            em.close();
        }
    }

    public int getGlCostCenterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GlCostCenter> rt = cq.from(GlCostCenter.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
