/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import Entity.Currency;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.IsagUser;
import Entity.InvSupplier;
import java.util.ArrayList;
import java.util.List;
import Entity.GlAccount;
import JPA.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdo
 */
public class CurrencyJpaController implements Serializable {

    public CurrencyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Currency currency) {
        if (currency.getInvSupplierList() == null) {
            currency.setInvSupplierList(new ArrayList<InvSupplier>());
        }
        if (currency.getGlAccountList() == null) {
            currency.setGlAccountList(new ArrayList<GlAccount>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagUser createdBy = currency.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                currency.setCreatedBy(createdBy);
            }
            IsagUser modifiedBy = currency.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                currency.setModifiedBy(modifiedBy);
            }
            List<InvSupplier> attachedInvSupplierList = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListInvSupplierToAttach : currency.getInvSupplierList()) {
                invSupplierListInvSupplierToAttach = em.getReference(invSupplierListInvSupplierToAttach.getClass(), invSupplierListInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList.add(invSupplierListInvSupplierToAttach);
            }
            currency.setInvSupplierList(attachedInvSupplierList);
            List<GlAccount> attachedGlAccountList = new ArrayList<GlAccount>();
            for (GlAccount glAccountListGlAccountToAttach : currency.getGlAccountList()) {
                glAccountListGlAccountToAttach = em.getReference(glAccountListGlAccountToAttach.getClass(), glAccountListGlAccountToAttach.getId());
                attachedGlAccountList.add(glAccountListGlAccountToAttach);
            }
            currency.setGlAccountList(attachedGlAccountList);
            em.persist(currency);
            if (createdBy != null) {
                createdBy.getCurrencyList().add(currency);
                createdBy = em.merge(createdBy);
            }
            if (modifiedBy != null) {
                modifiedBy.getCurrencyList().add(currency);
                modifiedBy = em.merge(modifiedBy);
            }
            for (InvSupplier invSupplierListInvSupplier : currency.getInvSupplierList()) {
                Currency oldCcyOfInvSupplierListInvSupplier = invSupplierListInvSupplier.getCcy();
                invSupplierListInvSupplier.setCcy(currency);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
                if (oldCcyOfInvSupplierListInvSupplier != null) {
                    oldCcyOfInvSupplierListInvSupplier.getInvSupplierList().remove(invSupplierListInvSupplier);
                    oldCcyOfInvSupplierListInvSupplier = em.merge(oldCcyOfInvSupplierListInvSupplier);
                }
            }
            for (GlAccount glAccountListGlAccount : currency.getGlAccountList()) {
                Currency oldCurrencyIdOfGlAccountListGlAccount = glAccountListGlAccount.getCurrencyId();
                glAccountListGlAccount.setCurrencyId(currency);
                glAccountListGlAccount = em.merge(glAccountListGlAccount);
                if (oldCurrencyIdOfGlAccountListGlAccount != null) {
                    oldCurrencyIdOfGlAccountListGlAccount.getGlAccountList().remove(glAccountListGlAccount);
                    oldCurrencyIdOfGlAccountListGlAccount = em.merge(oldCurrencyIdOfGlAccountListGlAccount);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Currency currency) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Currency persistentCurrency = em.find(Currency.class, currency.getId());
            IsagUser createdByOld = persistentCurrency.getCreatedBy();
            IsagUser createdByNew = currency.getCreatedBy();
            IsagUser modifiedByOld = persistentCurrency.getModifiedBy();
            IsagUser modifiedByNew = currency.getModifiedBy();
            List<InvSupplier> invSupplierListOld = persistentCurrency.getInvSupplierList();
            List<InvSupplier> invSupplierListNew = currency.getInvSupplierList();
            List<GlAccount> glAccountListOld = persistentCurrency.getGlAccountList();
            List<GlAccount> glAccountListNew = currency.getGlAccountList();
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                currency.setCreatedBy(createdByNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                currency.setModifiedBy(modifiedByNew);
            }
            List<InvSupplier> attachedInvSupplierListNew = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListNewInvSupplierToAttach : invSupplierListNew) {
                invSupplierListNewInvSupplierToAttach = em.getReference(invSupplierListNewInvSupplierToAttach.getClass(), invSupplierListNewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierListNew.add(invSupplierListNewInvSupplierToAttach);
            }
            invSupplierListNew = attachedInvSupplierListNew;
            currency.setInvSupplierList(invSupplierListNew);
            List<GlAccount> attachedGlAccountListNew = new ArrayList<GlAccount>();
            for (GlAccount glAccountListNewGlAccountToAttach : glAccountListNew) {
                glAccountListNewGlAccountToAttach = em.getReference(glAccountListNewGlAccountToAttach.getClass(), glAccountListNewGlAccountToAttach.getId());
                attachedGlAccountListNew.add(glAccountListNewGlAccountToAttach);
            }
            glAccountListNew = attachedGlAccountListNew;
            currency.setGlAccountList(glAccountListNew);
            currency = em.merge(currency);
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getCurrencyList().remove(currency);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getCurrencyList().add(currency);
                createdByNew = em.merge(createdByNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.getCurrencyList().remove(currency);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                modifiedByNew.getCurrencyList().add(currency);
                modifiedByNew = em.merge(modifiedByNew);
            }
            for (InvSupplier invSupplierListOldInvSupplier : invSupplierListOld) {
                if (!invSupplierListNew.contains(invSupplierListOldInvSupplier)) {
                    invSupplierListOldInvSupplier.setCcy(null);
                    invSupplierListOldInvSupplier = em.merge(invSupplierListOldInvSupplier);
                }
            }
            for (InvSupplier invSupplierListNewInvSupplier : invSupplierListNew) {
                if (!invSupplierListOld.contains(invSupplierListNewInvSupplier)) {
                    Currency oldCcyOfInvSupplierListNewInvSupplier = invSupplierListNewInvSupplier.getCcy();
                    invSupplierListNewInvSupplier.setCcy(currency);
                    invSupplierListNewInvSupplier = em.merge(invSupplierListNewInvSupplier);
                    if (oldCcyOfInvSupplierListNewInvSupplier != null && !oldCcyOfInvSupplierListNewInvSupplier.equals(currency)) {
                        oldCcyOfInvSupplierListNewInvSupplier.getInvSupplierList().remove(invSupplierListNewInvSupplier);
                        oldCcyOfInvSupplierListNewInvSupplier = em.merge(oldCcyOfInvSupplierListNewInvSupplier);
                    }
                }
            }
            for (GlAccount glAccountListOldGlAccount : glAccountListOld) {
                if (!glAccountListNew.contains(glAccountListOldGlAccount)) {
                    glAccountListOldGlAccount.setCurrencyId(null);
                    glAccountListOldGlAccount = em.merge(glAccountListOldGlAccount);
                }
            }
            for (GlAccount glAccountListNewGlAccount : glAccountListNew) {
                if (!glAccountListOld.contains(glAccountListNewGlAccount)) {
                    Currency oldCurrencyIdOfGlAccountListNewGlAccount = glAccountListNewGlAccount.getCurrencyId();
                    glAccountListNewGlAccount.setCurrencyId(currency);
                    glAccountListNewGlAccount = em.merge(glAccountListNewGlAccount);
                    if (oldCurrencyIdOfGlAccountListNewGlAccount != null && !oldCurrencyIdOfGlAccountListNewGlAccount.equals(currency)) {
                        oldCurrencyIdOfGlAccountListNewGlAccount.getGlAccountList().remove(glAccountListNewGlAccount);
                        oldCurrencyIdOfGlAccountListNewGlAccount = em.merge(oldCurrencyIdOfGlAccountListNewGlAccount);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = currency.getId();
                if (findCurrency(id) == null) {
                    throw new NonexistentEntityException("The currency with id " + id + " no longer exists.");
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
            Currency currency;
            try {
                currency = em.getReference(Currency.class, id);
                currency.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The currency with id " + id + " no longer exists.", enfe);
            }
            IsagUser createdBy = currency.getCreatedBy();
            if (createdBy != null) {
                createdBy.getCurrencyList().remove(currency);
                createdBy = em.merge(createdBy);
            }
            IsagUser modifiedBy = currency.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.getCurrencyList().remove(currency);
                modifiedBy = em.merge(modifiedBy);
            }
            List<InvSupplier> invSupplierList = currency.getInvSupplierList();
            for (InvSupplier invSupplierListInvSupplier : invSupplierList) {
                invSupplierListInvSupplier.setCcy(null);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
            }
            List<GlAccount> glAccountList = currency.getGlAccountList();
            for (GlAccount glAccountListGlAccount : glAccountList) {
                glAccountListGlAccount.setCurrencyId(null);
                glAccountListGlAccount = em.merge(glAccountListGlAccount);
            }
            em.remove(currency);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Currency> findCurrencyEntities() {
        return findCurrencyEntities(true, -1, -1);
    }

    public List<Currency> findCurrencyEntities(int maxResults, int firstResult) {
        return findCurrencyEntities(false, maxResults, firstResult);
    }

    private List<Currency> findCurrencyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Currency.class));
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

    public Currency findCurrency(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Currency.class, id);
        } finally {
            em.close();
        }
    }

    public int getCurrencyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Currency> rt = cq.from(Currency.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
