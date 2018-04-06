/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import Entity.GeneralSymbol;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.IsagCompany;
import Entity.IsagUser;
import Entity.Symbol;
import java.util.ArrayList;
import java.util.List;
import Entity.InvSupplier;
import JPA.exceptions.IllegalOrphanException;
import JPA.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdo
 */
public class GeneralSymbolJpaController implements Serializable {

    public GeneralSymbolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GeneralSymbol generalSymbol) {
        if (generalSymbol.getSymbolList() == null) {
            generalSymbol.setSymbolList(new ArrayList<Symbol>());
        }
        if (generalSymbol.getInvSupplierList() == null) {
            generalSymbol.setInvSupplierList(new ArrayList<InvSupplier>());
        }
        if (generalSymbol.getInvSupplierList1() == null) {
            generalSymbol.setInvSupplierList1(new ArrayList<InvSupplier>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagCompany companyId = generalSymbol.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                generalSymbol.setCompanyId(companyId);
            }
            IsagUser createdBy = generalSymbol.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                generalSymbol.setCreatedBy(createdBy);
            }
            IsagUser modifiedBy = generalSymbol.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                generalSymbol.setModifiedBy(modifiedBy);
            }
            List<Symbol> attachedSymbolList = new ArrayList<Symbol>();
            for (Symbol symbolListSymbolToAttach : generalSymbol.getSymbolList()) {
                symbolListSymbolToAttach = em.getReference(symbolListSymbolToAttach.getClass(), symbolListSymbolToAttach.getId());
                attachedSymbolList.add(symbolListSymbolToAttach);
            }
            generalSymbol.setSymbolList(attachedSymbolList);
            List<InvSupplier> attachedInvSupplierList = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListInvSupplierToAttach : generalSymbol.getInvSupplierList()) {
                invSupplierListInvSupplierToAttach = em.getReference(invSupplierListInvSupplierToAttach.getClass(), invSupplierListInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList.add(invSupplierListInvSupplierToAttach);
            }
            generalSymbol.setInvSupplierList(attachedInvSupplierList);
            List<InvSupplier> attachedInvSupplierList1 = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierList1InvSupplierToAttach : generalSymbol.getInvSupplierList1()) {
                invSupplierList1InvSupplierToAttach = em.getReference(invSupplierList1InvSupplierToAttach.getClass(), invSupplierList1InvSupplierToAttach.getSupplierId());
                attachedInvSupplierList1.add(invSupplierList1InvSupplierToAttach);
            }
            generalSymbol.setInvSupplierList1(attachedInvSupplierList1);
            em.persist(generalSymbol);
            if (companyId != null) {
                companyId.getGeneralSymbolList().add(generalSymbol);
                companyId = em.merge(companyId);
            }
            if (createdBy != null) {
                createdBy.getGeneralSymbolList().add(generalSymbol);
                createdBy = em.merge(createdBy);
            }
            if (modifiedBy != null) {
                modifiedBy.getGeneralSymbolList().add(generalSymbol);
                modifiedBy = em.merge(modifiedBy);
            }
            for (Symbol symbolListSymbol : generalSymbol.getSymbolList()) {
                GeneralSymbol oldGeneralSymbolIdOfSymbolListSymbol = symbolListSymbol.getGeneralSymbolId();
                symbolListSymbol.setGeneralSymbolId(generalSymbol);
                symbolListSymbol = em.merge(symbolListSymbol);
                if (oldGeneralSymbolIdOfSymbolListSymbol != null) {
                    oldGeneralSymbolIdOfSymbolListSymbol.getSymbolList().remove(symbolListSymbol);
                    oldGeneralSymbolIdOfSymbolListSymbol = em.merge(oldGeneralSymbolIdOfSymbolListSymbol);
                }
            }
            for (InvSupplier invSupplierListInvSupplier : generalSymbol.getInvSupplierList()) {
                GeneralSymbol oldTypnoOfInvSupplierListInvSupplier = invSupplierListInvSupplier.getTypno();
                invSupplierListInvSupplier.setTypno(generalSymbol);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
                if (oldTypnoOfInvSupplierListInvSupplier != null) {
                    oldTypnoOfInvSupplierListInvSupplier.getInvSupplierList().remove(invSupplierListInvSupplier);
                    oldTypnoOfInvSupplierListInvSupplier = em.merge(oldTypnoOfInvSupplierListInvSupplier);
                }
            }
            for (InvSupplier invSupplierList1InvSupplier : generalSymbol.getInvSupplierList1()) {
                GeneralSymbol oldCountryOfInvSupplierList1InvSupplier = invSupplierList1InvSupplier.getCountry();
                invSupplierList1InvSupplier.setCountry(generalSymbol);
                invSupplierList1InvSupplier = em.merge(invSupplierList1InvSupplier);
                if (oldCountryOfInvSupplierList1InvSupplier != null) {
                    oldCountryOfInvSupplierList1InvSupplier.getInvSupplierList1().remove(invSupplierList1InvSupplier);
                    oldCountryOfInvSupplierList1InvSupplier = em.merge(oldCountryOfInvSupplierList1InvSupplier);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GeneralSymbol generalSymbol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneralSymbol persistentGeneralSymbol = em.find(GeneralSymbol.class, generalSymbol.getId());
            IsagCompany companyIdOld = persistentGeneralSymbol.getCompanyId();
            IsagCompany companyIdNew = generalSymbol.getCompanyId();
            IsagUser createdByOld = persistentGeneralSymbol.getCreatedBy();
            IsagUser createdByNew = generalSymbol.getCreatedBy();
            IsagUser modifiedByOld = persistentGeneralSymbol.getModifiedBy();
            IsagUser modifiedByNew = generalSymbol.getModifiedBy();
            List<Symbol> symbolListOld = persistentGeneralSymbol.getSymbolList();
            List<Symbol> symbolListNew = generalSymbol.getSymbolList();
            List<InvSupplier> invSupplierListOld = persistentGeneralSymbol.getInvSupplierList();
            List<InvSupplier> invSupplierListNew = generalSymbol.getInvSupplierList();
            List<InvSupplier> invSupplierList1Old = persistentGeneralSymbol.getInvSupplierList1();
            List<InvSupplier> invSupplierList1New = generalSymbol.getInvSupplierList1();
            List<String> illegalOrphanMessages = null;
            for (Symbol symbolListOldSymbol : symbolListOld) {
                if (!symbolListNew.contains(symbolListOldSymbol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Symbol " + symbolListOldSymbol + " since its generalSymbolId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                generalSymbol.setCompanyId(companyIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                generalSymbol.setCreatedBy(createdByNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                generalSymbol.setModifiedBy(modifiedByNew);
            }
            List<Symbol> attachedSymbolListNew = new ArrayList<Symbol>();
            for (Symbol symbolListNewSymbolToAttach : symbolListNew) {
                symbolListNewSymbolToAttach = em.getReference(symbolListNewSymbolToAttach.getClass(), symbolListNewSymbolToAttach.getId());
                attachedSymbolListNew.add(symbolListNewSymbolToAttach);
            }
            symbolListNew = attachedSymbolListNew;
            generalSymbol.setSymbolList(symbolListNew);
            List<InvSupplier> attachedInvSupplierListNew = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListNewInvSupplierToAttach : invSupplierListNew) {
                invSupplierListNewInvSupplierToAttach = em.getReference(invSupplierListNewInvSupplierToAttach.getClass(), invSupplierListNewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierListNew.add(invSupplierListNewInvSupplierToAttach);
            }
            invSupplierListNew = attachedInvSupplierListNew;
            generalSymbol.setInvSupplierList(invSupplierListNew);
            List<InvSupplier> attachedInvSupplierList1New = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierList1NewInvSupplierToAttach : invSupplierList1New) {
                invSupplierList1NewInvSupplierToAttach = em.getReference(invSupplierList1NewInvSupplierToAttach.getClass(), invSupplierList1NewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList1New.add(invSupplierList1NewInvSupplierToAttach);
            }
            invSupplierList1New = attachedInvSupplierList1New;
            generalSymbol.setInvSupplierList1(invSupplierList1New);
            generalSymbol = em.merge(generalSymbol);
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getGeneralSymbolList().remove(generalSymbol);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getGeneralSymbolList().add(generalSymbol);
                companyIdNew = em.merge(companyIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getGeneralSymbolList().remove(generalSymbol);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getGeneralSymbolList().add(generalSymbol);
                createdByNew = em.merge(createdByNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.getGeneralSymbolList().remove(generalSymbol);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                modifiedByNew.getGeneralSymbolList().add(generalSymbol);
                modifiedByNew = em.merge(modifiedByNew);
            }
            for (Symbol symbolListNewSymbol : symbolListNew) {
                if (!symbolListOld.contains(symbolListNewSymbol)) {
                    GeneralSymbol oldGeneralSymbolIdOfSymbolListNewSymbol = symbolListNewSymbol.getGeneralSymbolId();
                    symbolListNewSymbol.setGeneralSymbolId(generalSymbol);
                    symbolListNewSymbol = em.merge(symbolListNewSymbol);
                    if (oldGeneralSymbolIdOfSymbolListNewSymbol != null && !oldGeneralSymbolIdOfSymbolListNewSymbol.equals(generalSymbol)) {
                        oldGeneralSymbolIdOfSymbolListNewSymbol.getSymbolList().remove(symbolListNewSymbol);
                        oldGeneralSymbolIdOfSymbolListNewSymbol = em.merge(oldGeneralSymbolIdOfSymbolListNewSymbol);
                    }
                }
            }
            for (InvSupplier invSupplierListOldInvSupplier : invSupplierListOld) {
                if (!invSupplierListNew.contains(invSupplierListOldInvSupplier)) {
                    invSupplierListOldInvSupplier.setTypno(null);
                    invSupplierListOldInvSupplier = em.merge(invSupplierListOldInvSupplier);
                }
            }
            for (InvSupplier invSupplierListNewInvSupplier : invSupplierListNew) {
                if (!invSupplierListOld.contains(invSupplierListNewInvSupplier)) {
                    GeneralSymbol oldTypnoOfInvSupplierListNewInvSupplier = invSupplierListNewInvSupplier.getTypno();
                    invSupplierListNewInvSupplier.setTypno(generalSymbol);
                    invSupplierListNewInvSupplier = em.merge(invSupplierListNewInvSupplier);
                    if (oldTypnoOfInvSupplierListNewInvSupplier != null && !oldTypnoOfInvSupplierListNewInvSupplier.equals(generalSymbol)) {
                        oldTypnoOfInvSupplierListNewInvSupplier.getInvSupplierList().remove(invSupplierListNewInvSupplier);
                        oldTypnoOfInvSupplierListNewInvSupplier = em.merge(oldTypnoOfInvSupplierListNewInvSupplier);
                    }
                }
            }
            for (InvSupplier invSupplierList1OldInvSupplier : invSupplierList1Old) {
                if (!invSupplierList1New.contains(invSupplierList1OldInvSupplier)) {
                    invSupplierList1OldInvSupplier.setCountry(null);
                    invSupplierList1OldInvSupplier = em.merge(invSupplierList1OldInvSupplier);
                }
            }
            for (InvSupplier invSupplierList1NewInvSupplier : invSupplierList1New) {
                if (!invSupplierList1Old.contains(invSupplierList1NewInvSupplier)) {
                    GeneralSymbol oldCountryOfInvSupplierList1NewInvSupplier = invSupplierList1NewInvSupplier.getCountry();
                    invSupplierList1NewInvSupplier.setCountry(generalSymbol);
                    invSupplierList1NewInvSupplier = em.merge(invSupplierList1NewInvSupplier);
                    if (oldCountryOfInvSupplierList1NewInvSupplier != null && !oldCountryOfInvSupplierList1NewInvSupplier.equals(generalSymbol)) {
                        oldCountryOfInvSupplierList1NewInvSupplier.getInvSupplierList1().remove(invSupplierList1NewInvSupplier);
                        oldCountryOfInvSupplierList1NewInvSupplier = em.merge(oldCountryOfInvSupplierList1NewInvSupplier);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = generalSymbol.getId();
                if (findGeneralSymbol(id) == null) {
                    throw new NonexistentEntityException("The generalSymbol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneralSymbol generalSymbol;
            try {
                generalSymbol = em.getReference(GeneralSymbol.class, id);
                generalSymbol.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The generalSymbol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Symbol> symbolListOrphanCheck = generalSymbol.getSymbolList();
            for (Symbol symbolListOrphanCheckSymbol : symbolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This GeneralSymbol (" + generalSymbol + ") cannot be destroyed since the Symbol " + symbolListOrphanCheckSymbol + " in its symbolList field has a non-nullable generalSymbolId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            IsagCompany companyId = generalSymbol.getCompanyId();
            if (companyId != null) {
                companyId.getGeneralSymbolList().remove(generalSymbol);
                companyId = em.merge(companyId);
            }
            IsagUser createdBy = generalSymbol.getCreatedBy();
            if (createdBy != null) {
                createdBy.getGeneralSymbolList().remove(generalSymbol);
                createdBy = em.merge(createdBy);
            }
            IsagUser modifiedBy = generalSymbol.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.getGeneralSymbolList().remove(generalSymbol);
                modifiedBy = em.merge(modifiedBy);
            }
            List<InvSupplier> invSupplierList = generalSymbol.getInvSupplierList();
            for (InvSupplier invSupplierListInvSupplier : invSupplierList) {
                invSupplierListInvSupplier.setTypno(null);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
            }
            List<InvSupplier> invSupplierList1 = generalSymbol.getInvSupplierList1();
            for (InvSupplier invSupplierList1InvSupplier : invSupplierList1) {
                invSupplierList1InvSupplier.setCountry(null);
                invSupplierList1InvSupplier = em.merge(invSupplierList1InvSupplier);
            }
            em.remove(generalSymbol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GeneralSymbol> findGeneralSymbolEntities() {
        return findGeneralSymbolEntities(true, -1, -1);
    }

    public List<GeneralSymbol> findGeneralSymbolEntities(int maxResults, int firstResult) {
        return findGeneralSymbolEntities(false, maxResults, firstResult);
    }

    private List<GeneralSymbol> findGeneralSymbolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GeneralSymbol.class));
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

    public GeneralSymbol findGeneralSymbol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GeneralSymbol.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneralSymbolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GeneralSymbol> rt = cq.from(GeneralSymbol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
