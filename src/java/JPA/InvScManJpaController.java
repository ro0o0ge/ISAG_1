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
import Entity.InvScMan;
import Entity.IsagCompany;
import Entity.InvSupplier;
import JPA.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Abdo
 */
public class InvScManJpaController implements Serializable {

    public InvScManJpaController(EntityManagerFactory emf) {
        this.emf = Persistence.createEntityManagerFactory("Isag_2PU");
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Isag_2PU");
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InvScMan invScMan) {
        if (invScMan.getInvSupplierList() == null) {
            invScMan.setInvSupplierList(new ArrayList<InvSupplier>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Branch brnno = invScMan.getBrnno();
            if (brnno != null) {
                brnno = em.getReference(brnno.getClass(), brnno.getId());
                invScMan.setBrnno(brnno);
            }
            IsagCompany companyId = invScMan.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                invScMan.setCompanyId(companyId);
            }
            List<InvSupplier> attachedInvSupplierList = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListInvSupplierToAttach : invScMan.getInvSupplierList()) {
                invSupplierListInvSupplierToAttach = em.getReference(invSupplierListInvSupplierToAttach.getClass(), invSupplierListInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList.add(invSupplierListInvSupplierToAttach);
            }
            invScMan.setInvSupplierList(attachedInvSupplierList);
            em.persist(invScMan);
            if (brnno != null) {
                brnno.getInvScManList().add(invScMan);
                brnno = em.merge(brnno);
            }
            if (companyId != null) {
                companyId.getInvScManList().add(invScMan);
                companyId = em.merge(companyId);
            }
            for (InvSupplier invSupplierListInvSupplier : invScMan.getInvSupplierList()) {
                InvScMan oldMannoOfInvSupplierListInvSupplier = invSupplierListInvSupplier.getManno();
                invSupplierListInvSupplier.setManno(invScMan);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
                if (oldMannoOfInvSupplierListInvSupplier != null) {
                    oldMannoOfInvSupplierListInvSupplier.getInvSupplierList().remove(invSupplierListInvSupplier);
                    oldMannoOfInvSupplierListInvSupplier = em.merge(oldMannoOfInvSupplierListInvSupplier);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InvScMan invScMan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InvScMan persistentInvScMan = em.find(InvScMan.class, invScMan.getScManId());
            Branch brnnoOld = persistentInvScMan.getBrnno();
            Branch brnnoNew = invScMan.getBrnno();
            IsagCompany companyIdOld = persistentInvScMan.getCompanyId();
            IsagCompany companyIdNew = invScMan.getCompanyId();
            List<InvSupplier> invSupplierListOld = persistentInvScMan.getInvSupplierList();
            List<InvSupplier> invSupplierListNew = invScMan.getInvSupplierList();
            if (brnnoNew != null) {
                brnnoNew = em.getReference(brnnoNew.getClass(), brnnoNew.getId());
                invScMan.setBrnno(brnnoNew);
            }
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                invScMan.setCompanyId(companyIdNew);
            }
            List<InvSupplier> attachedInvSupplierListNew = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListNewInvSupplierToAttach : invSupplierListNew) {
                invSupplierListNewInvSupplierToAttach = em.getReference(invSupplierListNewInvSupplierToAttach.getClass(), invSupplierListNewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierListNew.add(invSupplierListNewInvSupplierToAttach);
            }
            invSupplierListNew = attachedInvSupplierListNew;
            invScMan.setInvSupplierList(invSupplierListNew);
            invScMan = em.merge(invScMan);
            if (brnnoOld != null && !brnnoOld.equals(brnnoNew)) {
                brnnoOld.getInvScManList().remove(invScMan);
                brnnoOld = em.merge(brnnoOld);
            }
            if (brnnoNew != null && !brnnoNew.equals(brnnoOld)) {
                brnnoNew.getInvScManList().add(invScMan);
                brnnoNew = em.merge(brnnoNew);
            }
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getInvScManList().remove(invScMan);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getInvScManList().add(invScMan);
                companyIdNew = em.merge(companyIdNew);
            }
            for (InvSupplier invSupplierListOldInvSupplier : invSupplierListOld) {
                if (!invSupplierListNew.contains(invSupplierListOldInvSupplier)) {
                    invSupplierListOldInvSupplier.setManno(null);
                    invSupplierListOldInvSupplier = em.merge(invSupplierListOldInvSupplier);
                }
            }
            for (InvSupplier invSupplierListNewInvSupplier : invSupplierListNew) {
                if (!invSupplierListOld.contains(invSupplierListNewInvSupplier)) {
                    InvScMan oldMannoOfInvSupplierListNewInvSupplier = invSupplierListNewInvSupplier.getManno();
                    invSupplierListNewInvSupplier.setManno(invScMan);
                    invSupplierListNewInvSupplier = em.merge(invSupplierListNewInvSupplier);
                    if (oldMannoOfInvSupplierListNewInvSupplier != null && !oldMannoOfInvSupplierListNewInvSupplier.equals(invScMan)) {
                        oldMannoOfInvSupplierListNewInvSupplier.getInvSupplierList().remove(invSupplierListNewInvSupplier);
                        oldMannoOfInvSupplierListNewInvSupplier = em.merge(oldMannoOfInvSupplierListNewInvSupplier);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invScMan.getScManId();
                if (findInvScMan(id) == null) {
                    throw new NonexistentEntityException("The invScMan with id " + id + " no longer exists.");
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
            InvScMan invScMan;
            try {
                invScMan = em.getReference(InvScMan.class, id);
                invScMan.getScManId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invScMan with id " + id + " no longer exists.", enfe);
            }
            Branch brnno = invScMan.getBrnno();
            if (brnno != null) {
                brnno.getInvScManList().remove(invScMan);
                brnno = em.merge(brnno);
            }
            IsagCompany companyId = invScMan.getCompanyId();
            if (companyId != null) {
                companyId.getInvScManList().remove(invScMan);
                companyId = em.merge(companyId);
            }
            List<InvSupplier> invSupplierList = invScMan.getInvSupplierList();
            for (InvSupplier invSupplierListInvSupplier : invSupplierList) {
                invSupplierListInvSupplier.setManno(null);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
            }
            em.remove(invScMan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InvScMan> findInvScManEntities() {
        return findInvScManEntities(true, -1, -1);
    }

    public List<InvScMan> findInvScManEntities(int maxResults, int firstResult) {
        return findInvScManEntities(false, maxResults, firstResult);
    }

    private List<InvScMan> findInvScManEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InvScMan.class));
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

    public InvScMan findInvScMan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InvScMan.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvScManCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InvScMan> rt = cq.from(InvScMan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
