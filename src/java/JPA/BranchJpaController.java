/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import Entity.Branch;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.IsagCompany;
import Entity.IsagUser;
import Entity.InvSupplier;
import java.util.ArrayList;
import java.util.List;
import Entity.InvScMan;
import Entity.InvScCategory;
import Entity.GlCostCenter;
import Entity.GlAccount;
import JPA.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdo
 */
public class BranchJpaController implements Serializable {

    public BranchJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Branch branch) {
        if (branch.getInvSupplierList() == null) {
            branch.setInvSupplierList(new ArrayList<InvSupplier>());
        }
        if (branch.getInvScManList() == null) {
            branch.setInvScManList(new ArrayList<InvScMan>());
        }
        if (branch.getInvScCategoryList() == null) {
            branch.setInvScCategoryList(new ArrayList<InvScCategory>());
        }
        if (branch.getGlCostCenterList() == null) {
            branch.setGlCostCenterList(new ArrayList<GlCostCenter>());
        }
        if (branch.getGlAccountList() == null) {
            branch.setGlAccountList(new ArrayList<GlAccount>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagCompany companyId = branch.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                branch.setCompanyId(companyId);
            }
            IsagUser createdBy = branch.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                branch.setCreatedBy(createdBy);
            }
            IsagUser modifiedBy = branch.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                branch.setModifiedBy(modifiedBy);
            }
            List<InvSupplier> attachedInvSupplierList = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListInvSupplierToAttach : branch.getInvSupplierList()) {
                invSupplierListInvSupplierToAttach = em.getReference(invSupplierListInvSupplierToAttach.getClass(), invSupplierListInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList.add(invSupplierListInvSupplierToAttach);
            }
            branch.setInvSupplierList(attachedInvSupplierList);
            List<InvScMan> attachedInvScManList = new ArrayList<InvScMan>();
            for (InvScMan invScManListInvScManToAttach : branch.getInvScManList()) {
                invScManListInvScManToAttach = em.getReference(invScManListInvScManToAttach.getClass(), invScManListInvScManToAttach.getScManId());
                attachedInvScManList.add(invScManListInvScManToAttach);
            }
            branch.setInvScManList(attachedInvScManList);
            List<InvScCategory> attachedInvScCategoryList = new ArrayList<InvScCategory>();
            for (InvScCategory invScCategoryListInvScCategoryToAttach : branch.getInvScCategoryList()) {
                invScCategoryListInvScCategoryToAttach = em.getReference(invScCategoryListInvScCategoryToAttach.getClass(), invScCategoryListInvScCategoryToAttach.getCategoryID());
                attachedInvScCategoryList.add(invScCategoryListInvScCategoryToAttach);
            }
            branch.setInvScCategoryList(attachedInvScCategoryList);
            List<GlCostCenter> attachedGlCostCenterList = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterListGlCostCenterToAttach : branch.getGlCostCenterList()) {
                glCostCenterListGlCostCenterToAttach = em.getReference(glCostCenterListGlCostCenterToAttach.getClass(), glCostCenterListGlCostCenterToAttach.getId());
                attachedGlCostCenterList.add(glCostCenterListGlCostCenterToAttach);
            }
            branch.setGlCostCenterList(attachedGlCostCenterList);
            List<GlAccount> attachedGlAccountList = new ArrayList<GlAccount>();
            for (GlAccount glAccountListGlAccountToAttach : branch.getGlAccountList()) {
                glAccountListGlAccountToAttach = em.getReference(glAccountListGlAccountToAttach.getClass(), glAccountListGlAccountToAttach.getId());
                attachedGlAccountList.add(glAccountListGlAccountToAttach);
            }
            branch.setGlAccountList(attachedGlAccountList);
            em.persist(branch);
            if (companyId != null) {
                companyId.getBranchList().add(branch);
                companyId = em.merge(companyId);
            }
            if (createdBy != null) {
                createdBy.getBranchList().add(branch);
                createdBy = em.merge(createdBy);
            }
            if (modifiedBy != null) {
                modifiedBy.getBranchList().add(branch);
                modifiedBy = em.merge(modifiedBy);
            }
            for (InvSupplier invSupplierListInvSupplier : branch.getInvSupplierList()) {
                Branch oldBrnnoOfInvSupplierListInvSupplier = invSupplierListInvSupplier.getBrnno();
                invSupplierListInvSupplier.setBrnno(branch);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
                if (oldBrnnoOfInvSupplierListInvSupplier != null) {
                    oldBrnnoOfInvSupplierListInvSupplier.getInvSupplierList().remove(invSupplierListInvSupplier);
                    oldBrnnoOfInvSupplierListInvSupplier = em.merge(oldBrnnoOfInvSupplierListInvSupplier);
                }
            }
            for (InvScMan invScManListInvScMan : branch.getInvScManList()) {
                Branch oldBrnnoOfInvScManListInvScMan = invScManListInvScMan.getBrnno();
                invScManListInvScMan.setBrnno(branch);
                invScManListInvScMan = em.merge(invScManListInvScMan);
                if (oldBrnnoOfInvScManListInvScMan != null) {
                    oldBrnnoOfInvScManListInvScMan.getInvScManList().remove(invScManListInvScMan);
                    oldBrnnoOfInvScManListInvScMan = em.merge(oldBrnnoOfInvScManListInvScMan);
                }
            }
            for (InvScCategory invScCategoryListInvScCategory : branch.getInvScCategoryList()) {
                Branch oldBrnnoOfInvScCategoryListInvScCategory = invScCategoryListInvScCategory.getBrnno();
                invScCategoryListInvScCategory.setBrnno(branch);
                invScCategoryListInvScCategory = em.merge(invScCategoryListInvScCategory);
                if (oldBrnnoOfInvScCategoryListInvScCategory != null) {
                    oldBrnnoOfInvScCategoryListInvScCategory.getInvScCategoryList().remove(invScCategoryListInvScCategory);
                    oldBrnnoOfInvScCategoryListInvScCategory = em.merge(oldBrnnoOfInvScCategoryListInvScCategory);
                }
            }
            for (GlCostCenter glCostCenterListGlCostCenter : branch.getGlCostCenterList()) {
                Branch oldBranchIdOfGlCostCenterListGlCostCenter = glCostCenterListGlCostCenter.getBranchId();
                glCostCenterListGlCostCenter.setBranchId(branch);
                glCostCenterListGlCostCenter = em.merge(glCostCenterListGlCostCenter);
                if (oldBranchIdOfGlCostCenterListGlCostCenter != null) {
                    oldBranchIdOfGlCostCenterListGlCostCenter.getGlCostCenterList().remove(glCostCenterListGlCostCenter);
                    oldBranchIdOfGlCostCenterListGlCostCenter = em.merge(oldBranchIdOfGlCostCenterListGlCostCenter);
                }
            }
            for (GlAccount glAccountListGlAccount : branch.getGlAccountList()) {
                Branch oldBranchIdOfGlAccountListGlAccount = glAccountListGlAccount.getBranchId();
                glAccountListGlAccount.setBranchId(branch);
                glAccountListGlAccount = em.merge(glAccountListGlAccount);
                if (oldBranchIdOfGlAccountListGlAccount != null) {
                    oldBranchIdOfGlAccountListGlAccount.getGlAccountList().remove(glAccountListGlAccount);
                    oldBranchIdOfGlAccountListGlAccount = em.merge(oldBranchIdOfGlAccountListGlAccount);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Branch branch) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Branch persistentBranch = em.find(Branch.class, branch.getId());
            IsagCompany companyIdOld = persistentBranch.getCompanyId();
            IsagCompany companyIdNew = branch.getCompanyId();
            IsagUser createdByOld = persistentBranch.getCreatedBy();
            IsagUser createdByNew = branch.getCreatedBy();
            IsagUser modifiedByOld = persistentBranch.getModifiedBy();
            IsagUser modifiedByNew = branch.getModifiedBy();
            List<InvSupplier> invSupplierListOld = persistentBranch.getInvSupplierList();
            List<InvSupplier> invSupplierListNew = branch.getInvSupplierList();
            List<InvScMan> invScManListOld = persistentBranch.getInvScManList();
            List<InvScMan> invScManListNew = branch.getInvScManList();
            List<InvScCategory> invScCategoryListOld = persistentBranch.getInvScCategoryList();
            List<InvScCategory> invScCategoryListNew = branch.getInvScCategoryList();
            List<GlCostCenter> glCostCenterListOld = persistentBranch.getGlCostCenterList();
            List<GlCostCenter> glCostCenterListNew = branch.getGlCostCenterList();
            List<GlAccount> glAccountListOld = persistentBranch.getGlAccountList();
            List<GlAccount> glAccountListNew = branch.getGlAccountList();
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                branch.setCompanyId(companyIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                branch.setCreatedBy(createdByNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                branch.setModifiedBy(modifiedByNew);
            }
            List<InvSupplier> attachedInvSupplierListNew = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListNewInvSupplierToAttach : invSupplierListNew) {
                invSupplierListNewInvSupplierToAttach = em.getReference(invSupplierListNewInvSupplierToAttach.getClass(), invSupplierListNewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierListNew.add(invSupplierListNewInvSupplierToAttach);
            }
            invSupplierListNew = attachedInvSupplierListNew;
            branch.setInvSupplierList(invSupplierListNew);
            List<InvScMan> attachedInvScManListNew = new ArrayList<InvScMan>();
            for (InvScMan invScManListNewInvScManToAttach : invScManListNew) {
                invScManListNewInvScManToAttach = em.getReference(invScManListNewInvScManToAttach.getClass(), invScManListNewInvScManToAttach.getScManId());
                attachedInvScManListNew.add(invScManListNewInvScManToAttach);
            }
            invScManListNew = attachedInvScManListNew;
            branch.setInvScManList(invScManListNew);
            List<InvScCategory> attachedInvScCategoryListNew = new ArrayList<InvScCategory>();
            for (InvScCategory invScCategoryListNewInvScCategoryToAttach : invScCategoryListNew) {
                invScCategoryListNewInvScCategoryToAttach = em.getReference(invScCategoryListNewInvScCategoryToAttach.getClass(), invScCategoryListNewInvScCategoryToAttach.getCategoryID());
                attachedInvScCategoryListNew.add(invScCategoryListNewInvScCategoryToAttach);
            }
            invScCategoryListNew = attachedInvScCategoryListNew;
            branch.setInvScCategoryList(invScCategoryListNew);
            List<GlCostCenter> attachedGlCostCenterListNew = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterListNewGlCostCenterToAttach : glCostCenterListNew) {
                glCostCenterListNewGlCostCenterToAttach = em.getReference(glCostCenterListNewGlCostCenterToAttach.getClass(), glCostCenterListNewGlCostCenterToAttach.getId());
                attachedGlCostCenterListNew.add(glCostCenterListNewGlCostCenterToAttach);
            }
            glCostCenterListNew = attachedGlCostCenterListNew;
            branch.setGlCostCenterList(glCostCenterListNew);
            List<GlAccount> attachedGlAccountListNew = new ArrayList<GlAccount>();
            for (GlAccount glAccountListNewGlAccountToAttach : glAccountListNew) {
                glAccountListNewGlAccountToAttach = em.getReference(glAccountListNewGlAccountToAttach.getClass(), glAccountListNewGlAccountToAttach.getId());
                attachedGlAccountListNew.add(glAccountListNewGlAccountToAttach);
            }
            glAccountListNew = attachedGlAccountListNew;
            branch.setGlAccountList(glAccountListNew);
            branch = em.merge(branch);
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getBranchList().remove(branch);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getBranchList().add(branch);
                companyIdNew = em.merge(companyIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getBranchList().remove(branch);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getBranchList().add(branch);
                createdByNew = em.merge(createdByNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.getBranchList().remove(branch);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                modifiedByNew.getBranchList().add(branch);
                modifiedByNew = em.merge(modifiedByNew);
            }
            for (InvSupplier invSupplierListOldInvSupplier : invSupplierListOld) {
                if (!invSupplierListNew.contains(invSupplierListOldInvSupplier)) {
                    invSupplierListOldInvSupplier.setBrnno(null);
                    invSupplierListOldInvSupplier = em.merge(invSupplierListOldInvSupplier);
                }
            }
            for (InvSupplier invSupplierListNewInvSupplier : invSupplierListNew) {
                if (!invSupplierListOld.contains(invSupplierListNewInvSupplier)) {
                    Branch oldBrnnoOfInvSupplierListNewInvSupplier = invSupplierListNewInvSupplier.getBrnno();
                    invSupplierListNewInvSupplier.setBrnno(branch);
                    invSupplierListNewInvSupplier = em.merge(invSupplierListNewInvSupplier);
                    if (oldBrnnoOfInvSupplierListNewInvSupplier != null && !oldBrnnoOfInvSupplierListNewInvSupplier.equals(branch)) {
                        oldBrnnoOfInvSupplierListNewInvSupplier.getInvSupplierList().remove(invSupplierListNewInvSupplier);
                        oldBrnnoOfInvSupplierListNewInvSupplier = em.merge(oldBrnnoOfInvSupplierListNewInvSupplier);
                    }
                }
            }
            for (InvScMan invScManListOldInvScMan : invScManListOld) {
                if (!invScManListNew.contains(invScManListOldInvScMan)) {
                    invScManListOldInvScMan.setBrnno(null);
                    invScManListOldInvScMan = em.merge(invScManListOldInvScMan);
                }
            }
            for (InvScMan invScManListNewInvScMan : invScManListNew) {
                if (!invScManListOld.contains(invScManListNewInvScMan)) {
                    Branch oldBrnnoOfInvScManListNewInvScMan = invScManListNewInvScMan.getBrnno();
                    invScManListNewInvScMan.setBrnno(branch);
                    invScManListNewInvScMan = em.merge(invScManListNewInvScMan);
                    if (oldBrnnoOfInvScManListNewInvScMan != null && !oldBrnnoOfInvScManListNewInvScMan.equals(branch)) {
                        oldBrnnoOfInvScManListNewInvScMan.getInvScManList().remove(invScManListNewInvScMan);
                        oldBrnnoOfInvScManListNewInvScMan = em.merge(oldBrnnoOfInvScManListNewInvScMan);
                    }
                }
            }
            for (InvScCategory invScCategoryListOldInvScCategory : invScCategoryListOld) {
                if (!invScCategoryListNew.contains(invScCategoryListOldInvScCategory)) {
                    invScCategoryListOldInvScCategory.setBrnno(null);
                    invScCategoryListOldInvScCategory = em.merge(invScCategoryListOldInvScCategory);
                }
            }
            for (InvScCategory invScCategoryListNewInvScCategory : invScCategoryListNew) {
                if (!invScCategoryListOld.contains(invScCategoryListNewInvScCategory)) {
                    Branch oldBrnnoOfInvScCategoryListNewInvScCategory = invScCategoryListNewInvScCategory.getBrnno();
                    invScCategoryListNewInvScCategory.setBrnno(branch);
                    invScCategoryListNewInvScCategory = em.merge(invScCategoryListNewInvScCategory);
                    if (oldBrnnoOfInvScCategoryListNewInvScCategory != null && !oldBrnnoOfInvScCategoryListNewInvScCategory.equals(branch)) {
                        oldBrnnoOfInvScCategoryListNewInvScCategory.getInvScCategoryList().remove(invScCategoryListNewInvScCategory);
                        oldBrnnoOfInvScCategoryListNewInvScCategory = em.merge(oldBrnnoOfInvScCategoryListNewInvScCategory);
                    }
                }
            }
            for (GlCostCenter glCostCenterListOldGlCostCenter : glCostCenterListOld) {
                if (!glCostCenterListNew.contains(glCostCenterListOldGlCostCenter)) {
                    glCostCenterListOldGlCostCenter.setBranchId(null);
                    glCostCenterListOldGlCostCenter = em.merge(glCostCenterListOldGlCostCenter);
                }
            }
            for (GlCostCenter glCostCenterListNewGlCostCenter : glCostCenterListNew) {
                if (!glCostCenterListOld.contains(glCostCenterListNewGlCostCenter)) {
                    Branch oldBranchIdOfGlCostCenterListNewGlCostCenter = glCostCenterListNewGlCostCenter.getBranchId();
                    glCostCenterListNewGlCostCenter.setBranchId(branch);
                    glCostCenterListNewGlCostCenter = em.merge(glCostCenterListNewGlCostCenter);
                    if (oldBranchIdOfGlCostCenterListNewGlCostCenter != null && !oldBranchIdOfGlCostCenterListNewGlCostCenter.equals(branch)) {
                        oldBranchIdOfGlCostCenterListNewGlCostCenter.getGlCostCenterList().remove(glCostCenterListNewGlCostCenter);
                        oldBranchIdOfGlCostCenterListNewGlCostCenter = em.merge(oldBranchIdOfGlCostCenterListNewGlCostCenter);
                    }
                }
            }
            for (GlAccount glAccountListOldGlAccount : glAccountListOld) {
                if (!glAccountListNew.contains(glAccountListOldGlAccount)) {
                    glAccountListOldGlAccount.setBranchId(null);
                    glAccountListOldGlAccount = em.merge(glAccountListOldGlAccount);
                }
            }
            for (GlAccount glAccountListNewGlAccount : glAccountListNew) {
                if (!glAccountListOld.contains(glAccountListNewGlAccount)) {
                    Branch oldBranchIdOfGlAccountListNewGlAccount = glAccountListNewGlAccount.getBranchId();
                    glAccountListNewGlAccount.setBranchId(branch);
                    glAccountListNewGlAccount = em.merge(glAccountListNewGlAccount);
                    if (oldBranchIdOfGlAccountListNewGlAccount != null && !oldBranchIdOfGlAccountListNewGlAccount.equals(branch)) {
                        oldBranchIdOfGlAccountListNewGlAccount.getGlAccountList().remove(glAccountListNewGlAccount);
                        oldBranchIdOfGlAccountListNewGlAccount = em.merge(oldBranchIdOfGlAccountListNewGlAccount);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = branch.getId();
                if (findBranch(id) == null) {
                    throw new NonexistentEntityException("The branch with id " + id + " no longer exists.");
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
            Branch branch;
            try {
                branch = em.getReference(Branch.class, id);
                branch.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The branch with id " + id + " no longer exists.", enfe);
            }
            IsagCompany companyId = branch.getCompanyId();
            if (companyId != null) {
                companyId.getBranchList().remove(branch);
                companyId = em.merge(companyId);
            }
            IsagUser createdBy = branch.getCreatedBy();
            if (createdBy != null) {
                createdBy.getBranchList().remove(branch);
                createdBy = em.merge(createdBy);
            }
            IsagUser modifiedBy = branch.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.getBranchList().remove(branch);
                modifiedBy = em.merge(modifiedBy);
            }
            List<InvSupplier> invSupplierList = branch.getInvSupplierList();
            for (InvSupplier invSupplierListInvSupplier : invSupplierList) {
                invSupplierListInvSupplier.setBrnno(null);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
            }
            List<InvScMan> invScManList = branch.getInvScManList();
            for (InvScMan invScManListInvScMan : invScManList) {
                invScManListInvScMan.setBrnno(null);
                invScManListInvScMan = em.merge(invScManListInvScMan);
            }
            List<InvScCategory> invScCategoryList = branch.getInvScCategoryList();
            for (InvScCategory invScCategoryListInvScCategory : invScCategoryList) {
                invScCategoryListInvScCategory.setBrnno(null);
                invScCategoryListInvScCategory = em.merge(invScCategoryListInvScCategory);
            }
            List<GlCostCenter> glCostCenterList = branch.getGlCostCenterList();
            for (GlCostCenter glCostCenterListGlCostCenter : glCostCenterList) {
                glCostCenterListGlCostCenter.setBranchId(null);
                glCostCenterListGlCostCenter = em.merge(glCostCenterListGlCostCenter);
            }
            List<GlAccount> glAccountList = branch.getGlAccountList();
            for (GlAccount glAccountListGlAccount : glAccountList) {
                glAccountListGlAccount.setBranchId(null);
                glAccountListGlAccount = em.merge(glAccountListGlAccount);
            }
            em.remove(branch);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Branch> findBranchEntities() {
        return findBranchEntities(true, -1, -1);
    }

    public List<Branch> findBranchEntities(int maxResults, int firstResult) {
        return findBranchEntities(false, maxResults, firstResult);
    }

    private List<Branch> findBranchEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Branch.class));
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

    public Branch findBranch(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Branch.class, id);
        } finally {
            em.close();
        }
    }

    public int getBranchCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Branch> rt = cq.from(Branch.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
