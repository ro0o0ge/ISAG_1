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
import Entity.GeneralSymbol;
import Entity.Symbol;
import JPA.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdo
 */
public class SymbolJpaController implements Serializable {

    public SymbolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Symbol symbol) {
        if (symbol.getIsagUserList() == null) {
            symbol.setIsagUserList(new ArrayList<IsagUser>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagCompany companyId = symbol.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                symbol.setCompanyId(companyId);
            }
            IsagUser createdBy = symbol.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                symbol.setCreatedBy(createdBy);
            }
            GeneralSymbol generalSymbolId = symbol.getGeneralSymbolId();
            if (generalSymbolId != null) {
                generalSymbolId = em.getReference(generalSymbolId.getClass(), generalSymbolId.getId());
                symbol.setGeneralSymbolId(generalSymbolId);
            }
            IsagUser modifiedBy = symbol.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                symbol.setModifiedBy(modifiedBy);
            }
            List<IsagUser> attachedIsagUserList = new ArrayList<IsagUser>();
            for (IsagUser isagUserListIsagUserToAttach : symbol.getIsagUserList()) {
                isagUserListIsagUserToAttach = em.getReference(isagUserListIsagUserToAttach.getClass(), isagUserListIsagUserToAttach.getId());
                attachedIsagUserList.add(isagUserListIsagUserToAttach);
            }
            symbol.setIsagUserList(attachedIsagUserList);
            em.persist(symbol);
            if (companyId != null) {
                companyId.getSymbolList().add(symbol);
                companyId = em.merge(companyId);
            }
            if (createdBy != null) {
                createdBy.getSymbolList().add(symbol);
                createdBy = em.merge(createdBy);
            }
            if (generalSymbolId != null) {
                generalSymbolId.getSymbolList().add(symbol);
                generalSymbolId = em.merge(generalSymbolId);
            }
            if (modifiedBy != null) {
                modifiedBy.getSymbolList().add(symbol);
                modifiedBy = em.merge(modifiedBy);
            }
            for (IsagUser isagUserListIsagUser : symbol.getIsagUserList()) {
                Symbol oldLangOfIsagUserListIsagUser = isagUserListIsagUser.getLang();
                isagUserListIsagUser.setLang(symbol);
                isagUserListIsagUser = em.merge(isagUserListIsagUser);
                if (oldLangOfIsagUserListIsagUser != null) {
                    oldLangOfIsagUserListIsagUser.getIsagUserList().remove(isagUserListIsagUser);
                    oldLangOfIsagUserListIsagUser = em.merge(oldLangOfIsagUserListIsagUser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Symbol symbol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Symbol persistentSymbol = em.find(Symbol.class, symbol.getId());
            IsagCompany companyIdOld = persistentSymbol.getCompanyId();
            IsagCompany companyIdNew = symbol.getCompanyId();
            IsagUser createdByOld = persistentSymbol.getCreatedBy();
            IsagUser createdByNew = symbol.getCreatedBy();
            GeneralSymbol generalSymbolIdOld = persistentSymbol.getGeneralSymbolId();
            GeneralSymbol generalSymbolIdNew = symbol.getGeneralSymbolId();
            IsagUser modifiedByOld = persistentSymbol.getModifiedBy();
            IsagUser modifiedByNew = symbol.getModifiedBy();
            List<IsagUser> isagUserListOld = persistentSymbol.getIsagUserList();
            List<IsagUser> isagUserListNew = symbol.getIsagUserList();
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                symbol.setCompanyId(companyIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                symbol.setCreatedBy(createdByNew);
            }
            if (generalSymbolIdNew != null) {
                generalSymbolIdNew = em.getReference(generalSymbolIdNew.getClass(), generalSymbolIdNew.getId());
                symbol.setGeneralSymbolId(generalSymbolIdNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                symbol.setModifiedBy(modifiedByNew);
            }
            List<IsagUser> attachedIsagUserListNew = new ArrayList<IsagUser>();
            for (IsagUser isagUserListNewIsagUserToAttach : isagUserListNew) {
                isagUserListNewIsagUserToAttach = em.getReference(isagUserListNewIsagUserToAttach.getClass(), isagUserListNewIsagUserToAttach.getId());
                attachedIsagUserListNew.add(isagUserListNewIsagUserToAttach);
            }
            isagUserListNew = attachedIsagUserListNew;
            symbol.setIsagUserList(isagUserListNew);
            symbol = em.merge(symbol);
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getSymbolList().remove(symbol);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getSymbolList().add(symbol);
                companyIdNew = em.merge(companyIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getSymbolList().remove(symbol);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getSymbolList().add(symbol);
                createdByNew = em.merge(createdByNew);
            }
            if (generalSymbolIdOld != null && !generalSymbolIdOld.equals(generalSymbolIdNew)) {
                generalSymbolIdOld.getSymbolList().remove(symbol);
                generalSymbolIdOld = em.merge(generalSymbolIdOld);
            }
            if (generalSymbolIdNew != null && !generalSymbolIdNew.equals(generalSymbolIdOld)) {
                generalSymbolIdNew.getSymbolList().add(symbol);
                generalSymbolIdNew = em.merge(generalSymbolIdNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.getSymbolList().remove(symbol);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                modifiedByNew.getSymbolList().add(symbol);
                modifiedByNew = em.merge(modifiedByNew);
            }
            for (IsagUser isagUserListOldIsagUser : isagUserListOld) {
                if (!isagUserListNew.contains(isagUserListOldIsagUser)) {
                    isagUserListOldIsagUser.setLang(null);
                    isagUserListOldIsagUser = em.merge(isagUserListOldIsagUser);
                }
            }
            for (IsagUser isagUserListNewIsagUser : isagUserListNew) {
                if (!isagUserListOld.contains(isagUserListNewIsagUser)) {
                    Symbol oldLangOfIsagUserListNewIsagUser = isagUserListNewIsagUser.getLang();
                    isagUserListNewIsagUser.setLang(symbol);
                    isagUserListNewIsagUser = em.merge(isagUserListNewIsagUser);
                    if (oldLangOfIsagUserListNewIsagUser != null && !oldLangOfIsagUserListNewIsagUser.equals(symbol)) {
                        oldLangOfIsagUserListNewIsagUser.getIsagUserList().remove(isagUserListNewIsagUser);
                        oldLangOfIsagUserListNewIsagUser = em.merge(oldLangOfIsagUserListNewIsagUser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = symbol.getId();
                if (findSymbol(id) == null) {
                    throw new NonexistentEntityException("The symbol with id " + id + " no longer exists.");
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
            Symbol symbol;
            try {
                symbol = em.getReference(Symbol.class, id);
                symbol.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The symbol with id " + id + " no longer exists.", enfe);
            }
            IsagCompany companyId = symbol.getCompanyId();
            if (companyId != null) {
                companyId.getSymbolList().remove(symbol);
                companyId = em.merge(companyId);
            }
            IsagUser createdBy = symbol.getCreatedBy();
            if (createdBy != null) {
                createdBy.getSymbolList().remove(symbol);
                createdBy = em.merge(createdBy);
            }
            GeneralSymbol generalSymbolId = symbol.getGeneralSymbolId();
            if (generalSymbolId != null) {
                generalSymbolId.getSymbolList().remove(symbol);
                generalSymbolId = em.merge(generalSymbolId);
            }
            IsagUser modifiedBy = symbol.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.getSymbolList().remove(symbol);
                modifiedBy = em.merge(modifiedBy);
            }
            List<IsagUser> isagUserList = symbol.getIsagUserList();
            for (IsagUser isagUserListIsagUser : isagUserList) {
                isagUserListIsagUser.setLang(null);
                isagUserListIsagUser = em.merge(isagUserListIsagUser);
            }
            em.remove(symbol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Symbol> findSymbolEntities() {
        return findSymbolEntities(true, -1, -1);
    }

    public List<Symbol> findSymbolEntities(int maxResults, int firstResult) {
        return findSymbolEntities(false, maxResults, firstResult);
    }

    private List<Symbol> findSymbolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Symbol.class));
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

    public Symbol findSymbol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Symbol.class, id);
        } finally {
            em.close();
        }
    }

    public int getSymbolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Symbol> rt = cq.from(Symbol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
