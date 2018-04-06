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
import Entity.IsagRole;
import Entity.IsagUser;
import JPA.exceptions.IllegalOrphanException;
import JPA.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdo
 */
public class IsagRoleJpaController implements Serializable {

    public IsagRoleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IsagRole isagRole) throws IllegalOrphanException {
        if (isagRole.getIsagUserList() == null) {
            isagRole.setIsagUserList(new ArrayList<IsagUser>());
        }
        List<String> illegalOrphanMessages = null;
        IsagUser createdByOrphanCheck = isagRole.getCreatedBy();
        if (createdByOrphanCheck != null) {
            IsagRole oldRoleIdOfCreatedBy = createdByOrphanCheck.getRoleId();
            if (oldRoleIdOfCreatedBy != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The IsagUser " + createdByOrphanCheck + " already has an item of type IsagRole whose createdBy column cannot be null. Please make another selection for the createdBy field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagCompany companyId = isagRole.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                isagRole.setCompanyId(companyId);
            }
            IsagUser createdBy = isagRole.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                isagRole.setCreatedBy(createdBy);
            }
            IsagUser modifiedBy = isagRole.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                isagRole.setModifiedBy(modifiedBy);
            }
            List<IsagUser> attachedIsagUserList = new ArrayList<IsagUser>();
            for (IsagUser isagUserListIsagUserToAttach : isagRole.getIsagUserList()) {
                isagUserListIsagUserToAttach = em.getReference(isagUserListIsagUserToAttach.getClass(), isagUserListIsagUserToAttach.getId());
                attachedIsagUserList.add(isagUserListIsagUserToAttach);
            }
            isagRole.setIsagUserList(attachedIsagUserList);
            em.persist(isagRole);
            if (companyId != null) {
                companyId.getIsagRoleList().add(isagRole);
                companyId = em.merge(companyId);
            }
            if (createdBy != null) {
                createdBy.setRoleId(isagRole);
                createdBy = em.merge(createdBy);
            }
            if (modifiedBy != null) {
                IsagRole oldRoleIdOfModifiedBy = modifiedBy.getRoleId();
                if (oldRoleIdOfModifiedBy != null) {
                    oldRoleIdOfModifiedBy.setModifiedBy(null);
                    oldRoleIdOfModifiedBy = em.merge(oldRoleIdOfModifiedBy);
                }
                modifiedBy.setRoleId(isagRole);
                modifiedBy = em.merge(modifiedBy);
            }
            for (IsagUser isagUserListIsagUser : isagRole.getIsagUserList()) {
                IsagRole oldRoleIdOfIsagUserListIsagUser = isagUserListIsagUser.getRoleId();
                isagUserListIsagUser.setRoleId(isagRole);
                isagUserListIsagUser = em.merge(isagUserListIsagUser);
                if (oldRoleIdOfIsagUserListIsagUser != null) {
                    oldRoleIdOfIsagUserListIsagUser.getIsagUserList().remove(isagUserListIsagUser);
                    oldRoleIdOfIsagUserListIsagUser = em.merge(oldRoleIdOfIsagUserListIsagUser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IsagRole isagRole) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagRole persistentIsagRole = em.find(IsagRole.class, isagRole.getId());
            IsagCompany companyIdOld = persistentIsagRole.getCompanyId();
            IsagCompany companyIdNew = isagRole.getCompanyId();
            IsagUser createdByOld = persistentIsagRole.getCreatedBy();
            IsagUser createdByNew = isagRole.getCreatedBy();
            IsagUser modifiedByOld = persistentIsagRole.getModifiedBy();
            IsagUser modifiedByNew = isagRole.getModifiedBy();
            List<IsagUser> isagUserListOld = persistentIsagRole.getIsagUserList();
            List<IsagUser> isagUserListNew = isagRole.getIsagUserList();
            List<String> illegalOrphanMessages = null;
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                IsagRole oldRoleIdOfCreatedBy = createdByNew.getRoleId();
                if (oldRoleIdOfCreatedBy != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The IsagUser " + createdByNew + " already has an item of type IsagRole whose createdBy column cannot be null. Please make another selection for the createdBy field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                isagRole.setCompanyId(companyIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                isagRole.setCreatedBy(createdByNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                isagRole.setModifiedBy(modifiedByNew);
            }
            List<IsagUser> attachedIsagUserListNew = new ArrayList<IsagUser>();
            for (IsagUser isagUserListNewIsagUserToAttach : isagUserListNew) {
                isagUserListNewIsagUserToAttach = em.getReference(isagUserListNewIsagUserToAttach.getClass(), isagUserListNewIsagUserToAttach.getId());
                attachedIsagUserListNew.add(isagUserListNewIsagUserToAttach);
            }
            isagUserListNew = attachedIsagUserListNew;
            isagRole.setIsagUserList(isagUserListNew);
            isagRole = em.merge(isagRole);
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getIsagRoleList().remove(isagRole);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getIsagRoleList().add(isagRole);
                companyIdNew = em.merge(companyIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.setRoleId(null);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.setRoleId(isagRole);
                createdByNew = em.merge(createdByNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.setRoleId(null);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                IsagRole oldRoleIdOfModifiedBy = modifiedByNew.getRoleId();
                if (oldRoleIdOfModifiedBy != null) {
                    oldRoleIdOfModifiedBy.setModifiedBy(null);
                    oldRoleIdOfModifiedBy = em.merge(oldRoleIdOfModifiedBy);
                }
                modifiedByNew.setRoleId(isagRole);
                modifiedByNew = em.merge(modifiedByNew);
            }
            for (IsagUser isagUserListOldIsagUser : isagUserListOld) {
                if (!isagUserListNew.contains(isagUserListOldIsagUser)) {
                    isagUserListOldIsagUser.setRoleId(null);
                    isagUserListOldIsagUser = em.merge(isagUserListOldIsagUser);
                }
            }
            for (IsagUser isagUserListNewIsagUser : isagUserListNew) {
                if (!isagUserListOld.contains(isagUserListNewIsagUser)) {
                    IsagRole oldRoleIdOfIsagUserListNewIsagUser = isagUserListNewIsagUser.getRoleId();
                    isagUserListNewIsagUser.setRoleId(isagRole);
                    isagUserListNewIsagUser = em.merge(isagUserListNewIsagUser);
                    if (oldRoleIdOfIsagUserListNewIsagUser != null && !oldRoleIdOfIsagUserListNewIsagUser.equals(isagRole)) {
                        oldRoleIdOfIsagUserListNewIsagUser.getIsagUserList().remove(isagUserListNewIsagUser);
                        oldRoleIdOfIsagUserListNewIsagUser = em.merge(oldRoleIdOfIsagUserListNewIsagUser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = isagRole.getId();
                if (findIsagRole(id) == null) {
                    throw new NonexistentEntityException("The isagRole with id " + id + " no longer exists.");
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
            IsagRole isagRole;
            try {
                isagRole = em.getReference(IsagRole.class, id);
                isagRole.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The isagRole with id " + id + " no longer exists.", enfe);
            }
            IsagCompany companyId = isagRole.getCompanyId();
            if (companyId != null) {
                companyId.getIsagRoleList().remove(isagRole);
                companyId = em.merge(companyId);
            }
            IsagUser createdBy = isagRole.getCreatedBy();
            if (createdBy != null) {
                createdBy.setRoleId(null);
                createdBy = em.merge(createdBy);
            }
            IsagUser modifiedBy = isagRole.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.setRoleId(null);
                modifiedBy = em.merge(modifiedBy);
            }
            List<IsagUser> isagUserList = isagRole.getIsagUserList();
            for (IsagUser isagUserListIsagUser : isagUserList) {
                isagUserListIsagUser.setRoleId(null);
                isagUserListIsagUser = em.merge(isagUserListIsagUser);
            }
            em.remove(isagRole);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IsagRole> findIsagRoleEntities() {
        return findIsagRoleEntities(true, -1, -1);
    }

    public List<IsagRole> findIsagRoleEntities(int maxResults, int firstResult) {
        return findIsagRoleEntities(false, maxResults, firstResult);
    }

    private List<IsagRole> findIsagRoleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IsagRole.class));
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

    public IsagRole findIsagRole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IsagRole.class, id);
        } finally {
            em.close();
        }
    }

    public int getIsagRoleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IsagRole> rt = cq.from(IsagRole.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
