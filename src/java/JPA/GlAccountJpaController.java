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
import Entity.Branch;
import Entity.Currency;
import Entity.GlAccount;
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
public class GlAccountJpaController implements Serializable {

    public GlAccountJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GlAccount glAccount) {
        if (glAccount.getInvSupplierList() == null) {
            glAccount.setInvSupplierList(new ArrayList<InvSupplier>());
        }
        if (glAccount.getGlAccountList() == null) {
            glAccount.setGlAccountList(new ArrayList<GlAccount>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagCompany companyId = glAccount.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                glAccount.setCompanyId(companyId);
            }
            IsagUser createdBy = glAccount.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                glAccount.setCreatedBy(createdBy);
            }
            IsagUser modifiedBy = glAccount.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                glAccount.setModifiedBy(modifiedBy);
            }
            Branch branchId = glAccount.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getId());
                glAccount.setBranchId(branchId);
            }
            Currency currencyId = glAccount.getCurrencyId();
            if (currencyId != null) {
                currencyId = em.getReference(currencyId.getClass(), currencyId.getId());
                glAccount.setCurrencyId(currencyId);
            }
            GlAccount parentAcc = glAccount.getParentAcc();
            if (parentAcc != null) {
                parentAcc = em.getReference(parentAcc.getClass(), parentAcc.getId());
                glAccount.setParentAcc(parentAcc);
            }
            List<InvSupplier> attachedInvSupplierList = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListInvSupplierToAttach : glAccount.getInvSupplierList()) {
                invSupplierListInvSupplierToAttach = em.getReference(invSupplierListInvSupplierToAttach.getClass(), invSupplierListInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList.add(invSupplierListInvSupplierToAttach);
            }
            glAccount.setInvSupplierList(attachedInvSupplierList);
            List<GlAccount> attachedGlAccountList = new ArrayList<GlAccount>();
            for (GlAccount glAccountListGlAccountToAttach : glAccount.getGlAccountList()) {
                glAccountListGlAccountToAttach = em.getReference(glAccountListGlAccountToAttach.getClass(), glAccountListGlAccountToAttach.getId());
                attachedGlAccountList.add(glAccountListGlAccountToAttach);
            }
            glAccount.setGlAccountList(attachedGlAccountList);
            em.persist(glAccount);
            if (companyId != null) {
                companyId.getGlAccountList().add(glAccount);
                companyId = em.merge(companyId);
            }
            if (createdBy != null) {
                createdBy.getGlAccountList().add(glAccount);
                createdBy = em.merge(createdBy);
            }
            if (modifiedBy != null) {
                modifiedBy.getGlAccountList().add(glAccount);
                modifiedBy = em.merge(modifiedBy);
            }
            if (branchId != null) {
                branchId.getGlAccountList().add(glAccount);
                branchId = em.merge(branchId);
            }
            if (currencyId != null) {
                currencyId.getGlAccountList().add(glAccount);
                currencyId = em.merge(currencyId);
            }
            if (parentAcc != null) {
                parentAcc.getGlAccountList().add(glAccount);
                parentAcc = em.merge(parentAcc);
            }
            for (InvSupplier invSupplierListInvSupplier : glAccount.getInvSupplierList()) {
                GlAccount oldAccnoOfInvSupplierListInvSupplier = invSupplierListInvSupplier.getAccno();
                invSupplierListInvSupplier.setAccno(glAccount);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
                if (oldAccnoOfInvSupplierListInvSupplier != null) {
                    oldAccnoOfInvSupplierListInvSupplier.getInvSupplierList().remove(invSupplierListInvSupplier);
                    oldAccnoOfInvSupplierListInvSupplier = em.merge(oldAccnoOfInvSupplierListInvSupplier);
                }
            }
            for (GlAccount glAccountListGlAccount : glAccount.getGlAccountList()) {
                GlAccount oldParentAccOfGlAccountListGlAccount = glAccountListGlAccount.getParentAcc();
                glAccountListGlAccount.setParentAcc(glAccount);
                glAccountListGlAccount = em.merge(glAccountListGlAccount);
                if (oldParentAccOfGlAccountListGlAccount != null) {
                    oldParentAccOfGlAccountListGlAccount.getGlAccountList().remove(glAccountListGlAccount);
                    oldParentAccOfGlAccountListGlAccount = em.merge(oldParentAccOfGlAccountListGlAccount);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GlAccount glAccount) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GlAccount persistentGlAccount = em.find(GlAccount.class, glAccount.getId());
            IsagCompany companyIdOld = persistentGlAccount.getCompanyId();
            IsagCompany companyIdNew = glAccount.getCompanyId();
            IsagUser createdByOld = persistentGlAccount.getCreatedBy();
            IsagUser createdByNew = glAccount.getCreatedBy();
            IsagUser modifiedByOld = persistentGlAccount.getModifiedBy();
            IsagUser modifiedByNew = glAccount.getModifiedBy();
            Branch branchIdOld = persistentGlAccount.getBranchId();
            Branch branchIdNew = glAccount.getBranchId();
            Currency currencyIdOld = persistentGlAccount.getCurrencyId();
            Currency currencyIdNew = glAccount.getCurrencyId();
            GlAccount parentAccOld = persistentGlAccount.getParentAcc();
            GlAccount parentAccNew = glAccount.getParentAcc();
            List<InvSupplier> invSupplierListOld = persistentGlAccount.getInvSupplierList();
            List<InvSupplier> invSupplierListNew = glAccount.getInvSupplierList();
            List<GlAccount> glAccountListOld = persistentGlAccount.getGlAccountList();
            List<GlAccount> glAccountListNew = glAccount.getGlAccountList();
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                glAccount.setCompanyId(companyIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                glAccount.setCreatedBy(createdByNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                glAccount.setModifiedBy(modifiedByNew);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getId());
                glAccount.setBranchId(branchIdNew);
            }
            if (currencyIdNew != null) {
                currencyIdNew = em.getReference(currencyIdNew.getClass(), currencyIdNew.getId());
                glAccount.setCurrencyId(currencyIdNew);
            }
            if (parentAccNew != null) {
                parentAccNew = em.getReference(parentAccNew.getClass(), parentAccNew.getId());
                glAccount.setParentAcc(parentAccNew);
            }
            List<InvSupplier> attachedInvSupplierListNew = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListNewInvSupplierToAttach : invSupplierListNew) {
                invSupplierListNewInvSupplierToAttach = em.getReference(invSupplierListNewInvSupplierToAttach.getClass(), invSupplierListNewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierListNew.add(invSupplierListNewInvSupplierToAttach);
            }
            invSupplierListNew = attachedInvSupplierListNew;
            glAccount.setInvSupplierList(invSupplierListNew);
            List<GlAccount> attachedGlAccountListNew = new ArrayList<GlAccount>();
            for (GlAccount glAccountListNewGlAccountToAttach : glAccountListNew) {
                glAccountListNewGlAccountToAttach = em.getReference(glAccountListNewGlAccountToAttach.getClass(), glAccountListNewGlAccountToAttach.getId());
                attachedGlAccountListNew.add(glAccountListNewGlAccountToAttach);
            }
            glAccountListNew = attachedGlAccountListNew;
            glAccount.setGlAccountList(glAccountListNew);
            glAccount = em.merge(glAccount);
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getGlAccountList().remove(glAccount);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getGlAccountList().add(glAccount);
                companyIdNew = em.merge(companyIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getGlAccountList().remove(glAccount);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getGlAccountList().add(glAccount);
                createdByNew = em.merge(createdByNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.getGlAccountList().remove(glAccount);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                modifiedByNew.getGlAccountList().add(glAccount);
                modifiedByNew = em.merge(modifiedByNew);
            }
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getGlAccountList().remove(glAccount);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getGlAccountList().add(glAccount);
                branchIdNew = em.merge(branchIdNew);
            }
            if (currencyIdOld != null && !currencyIdOld.equals(currencyIdNew)) {
                currencyIdOld.getGlAccountList().remove(glAccount);
                currencyIdOld = em.merge(currencyIdOld);
            }
            if (currencyIdNew != null && !currencyIdNew.equals(currencyIdOld)) {
                currencyIdNew.getGlAccountList().add(glAccount);
                currencyIdNew = em.merge(currencyIdNew);
            }
            if (parentAccOld != null && !parentAccOld.equals(parentAccNew)) {
                parentAccOld.getGlAccountList().remove(glAccount);
                parentAccOld = em.merge(parentAccOld);
            }
            if (parentAccNew != null && !parentAccNew.equals(parentAccOld)) {
                parentAccNew.getGlAccountList().add(glAccount);
                parentAccNew = em.merge(parentAccNew);
            }
            for (InvSupplier invSupplierListOldInvSupplier : invSupplierListOld) {
                if (!invSupplierListNew.contains(invSupplierListOldInvSupplier)) {
                    invSupplierListOldInvSupplier.setAccno(null);
                    invSupplierListOldInvSupplier = em.merge(invSupplierListOldInvSupplier);
                }
            }
            for (InvSupplier invSupplierListNewInvSupplier : invSupplierListNew) {
                if (!invSupplierListOld.contains(invSupplierListNewInvSupplier)) {
                    GlAccount oldAccnoOfInvSupplierListNewInvSupplier = invSupplierListNewInvSupplier.getAccno();
                    invSupplierListNewInvSupplier.setAccno(glAccount);
                    invSupplierListNewInvSupplier = em.merge(invSupplierListNewInvSupplier);
                    if (oldAccnoOfInvSupplierListNewInvSupplier != null && !oldAccnoOfInvSupplierListNewInvSupplier.equals(glAccount)) {
                        oldAccnoOfInvSupplierListNewInvSupplier.getInvSupplierList().remove(invSupplierListNewInvSupplier);
                        oldAccnoOfInvSupplierListNewInvSupplier = em.merge(oldAccnoOfInvSupplierListNewInvSupplier);
                    }
                }
            }
            for (GlAccount glAccountListOldGlAccount : glAccountListOld) {
                if (!glAccountListNew.contains(glAccountListOldGlAccount)) {
                    glAccountListOldGlAccount.setParentAcc(null);
                    glAccountListOldGlAccount = em.merge(glAccountListOldGlAccount);
                }
            }
            for (GlAccount glAccountListNewGlAccount : glAccountListNew) {
                if (!glAccountListOld.contains(glAccountListNewGlAccount)) {
                    GlAccount oldParentAccOfGlAccountListNewGlAccount = glAccountListNewGlAccount.getParentAcc();
                    glAccountListNewGlAccount.setParentAcc(glAccount);
                    glAccountListNewGlAccount = em.merge(glAccountListNewGlAccount);
                    if (oldParentAccOfGlAccountListNewGlAccount != null && !oldParentAccOfGlAccountListNewGlAccount.equals(glAccount)) {
                        oldParentAccOfGlAccountListNewGlAccount.getGlAccountList().remove(glAccountListNewGlAccount);
                        oldParentAccOfGlAccountListNewGlAccount = em.merge(oldParentAccOfGlAccountListNewGlAccount);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = glAccount.getId();
                if (findGlAccount(id) == null) {
                    throw new NonexistentEntityException("The glAccount with id " + id + " no longer exists.");
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
            GlAccount glAccount;
            try {
                glAccount = em.getReference(GlAccount.class, id);
                glAccount.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The glAccount with id " + id + " no longer exists.", enfe);
            }
            IsagCompany companyId = glAccount.getCompanyId();
            if (companyId != null) {
                companyId.getGlAccountList().remove(glAccount);
                companyId = em.merge(companyId);
            }
            IsagUser createdBy = glAccount.getCreatedBy();
            if (createdBy != null) {
                createdBy.getGlAccountList().remove(glAccount);
                createdBy = em.merge(createdBy);
            }
            IsagUser modifiedBy = glAccount.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.getGlAccountList().remove(glAccount);
                modifiedBy = em.merge(modifiedBy);
            }
            Branch branchId = glAccount.getBranchId();
            if (branchId != null) {
                branchId.getGlAccountList().remove(glAccount);
                branchId = em.merge(branchId);
            }
            Currency currencyId = glAccount.getCurrencyId();
            if (currencyId != null) {
                currencyId.getGlAccountList().remove(glAccount);
                currencyId = em.merge(currencyId);
            }
            GlAccount parentAcc = glAccount.getParentAcc();
            if (parentAcc != null) {
                parentAcc.getGlAccountList().remove(glAccount);
                parentAcc = em.merge(parentAcc);
            }
            List<InvSupplier> invSupplierList = glAccount.getInvSupplierList();
            for (InvSupplier invSupplierListInvSupplier : invSupplierList) {
                invSupplierListInvSupplier.setAccno(null);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
            }
            List<GlAccount> glAccountList = glAccount.getGlAccountList();
            for (GlAccount glAccountListGlAccount : glAccountList) {
                glAccountListGlAccount.setParentAcc(null);
                glAccountListGlAccount = em.merge(glAccountListGlAccount);
            }
            em.remove(glAccount);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GlAccount> findGlAccountEntities() {
        return findGlAccountEntities(true, -1, -1);
    }

    public List<GlAccount> findGlAccountEntities(int maxResults, int firstResult) {
        return findGlAccountEntities(false, maxResults, firstResult);
    }

    private List<GlAccount> findGlAccountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GlAccount.class));
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

    public GlAccount findGlAccount(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GlAccount.class, id);
        } finally {
            em.close();
        }
    }

    public int getGlAccountCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GlAccount> rt = cq.from(GlAccount.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
