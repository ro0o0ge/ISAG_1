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
import Entity.IsagCompany;
import Entity.IsagUser;
import Entity.GeneralSymbol;
import Entity.InvScMan;
import Entity.Currency;
import Entity.GlAccount;
import Entity.GlCostCenter;
import Entity.InvSupplier;
import JPA.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Abdo
 */
public class InvSupplierJpaController implements Serializable {

    public InvSupplierJpaController(EntityManagerFactory emf) {
        this.emf = Persistence.createEntityManagerFactory("Isag_2PU");
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Isag_2PU");
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InvSupplier invSupplier) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Branch brnno = invSupplier.getBrnno();
            if (brnno != null) {
                brnno = em.getReference(brnno.getClass(), brnno.getId());
                invSupplier.setBrnno(brnno);
            }
            IsagCompany companyId = invSupplier.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                invSupplier.setCompanyId(companyId);
            }
            IsagUser createdBy = invSupplier.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                invSupplier.setCreatedBy(createdBy);
            }
            GeneralSymbol typno = invSupplier.getTypno();
            if (typno != null) {
                typno = em.getReference(typno.getClass(), typno.getId());
                invSupplier.setTypno(typno);
            }
            GeneralSymbol country = invSupplier.getCountry();
            if (country != null) {
                country = em.getReference(country.getClass(), country.getId());
                invSupplier.setCountry(country);
            }
            IsagUser modifiedBy = invSupplier.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                invSupplier.setModifiedBy(modifiedBy);
            }
            InvScMan manno = invSupplier.getManno();
            if (manno != null) {
                manno = em.getReference(manno.getClass(), manno.getScManId());
                invSupplier.setManno(manno);
            }
            Currency ccy = invSupplier.getCcy();
            if (ccy != null) {
                ccy = em.getReference(ccy.getClass(), ccy.getId());
                invSupplier.setCcy(ccy);
            }
            GlAccount accno = invSupplier.getAccno();
            if (accno != null) {
                accno = em.getReference(accno.getClass(), accno.getId());
                invSupplier.setAccno(accno);
            }
            GlCostCenter pricelvl = invSupplier.getPricelvl();
            if (pricelvl != null) {
                pricelvl = em.getReference(pricelvl.getClass(), pricelvl.getId());
                invSupplier.setPricelvl(pricelvl);
            }
            em.persist(invSupplier);
            if (brnno != null) {
                brnno.getInvSupplierList().add(invSupplier);
                brnno = em.merge(brnno);
            }
            if (companyId != null) {
                companyId.getInvSupplierList().add(invSupplier);
                companyId = em.merge(companyId);
            }
            if (createdBy != null) {
                createdBy.getInvSupplierList().add(invSupplier);
                createdBy = em.merge(createdBy);
            }
            if (typno != null) {
                typno.getInvSupplierList().add(invSupplier);
                typno = em.merge(typno);
            }
            if (country != null) {
                country.getInvSupplierList().add(invSupplier);
                country = em.merge(country);
            }
            if (modifiedBy != null) {
                modifiedBy.getInvSupplierList().add(invSupplier);
                modifiedBy = em.merge(modifiedBy);
            }
            if (manno != null) {
                manno.getInvSupplierList().add(invSupplier);
                manno = em.merge(manno);
            }
            if (ccy != null) {
                ccy.getInvSupplierList().add(invSupplier);
                ccy = em.merge(ccy);
            }
            if (accno != null) {
                accno.getInvSupplierList().add(invSupplier);
                accno = em.merge(accno);
            }
            if (pricelvl != null) {
                pricelvl.getInvSupplierList().add(invSupplier);
                pricelvl = em.merge(pricelvl);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InvSupplier invSupplier) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InvSupplier persistentInvSupplier = em.find(InvSupplier.class, invSupplier.getSupplierId());
            Branch brnnoOld = persistentInvSupplier.getBrnno();
            Branch brnnoNew = invSupplier.getBrnno();
            IsagCompany companyIdOld = persistentInvSupplier.getCompanyId();
            IsagCompany companyIdNew = invSupplier.getCompanyId();
            IsagUser createdByOld = persistentInvSupplier.getCreatedBy();
            IsagUser createdByNew = invSupplier.getCreatedBy();
            GeneralSymbol typnoOld = persistentInvSupplier.getTypno();
            GeneralSymbol typnoNew = invSupplier.getTypno();
            GeneralSymbol countryOld = persistentInvSupplier.getCountry();
            GeneralSymbol countryNew = invSupplier.getCountry();
            IsagUser modifiedByOld = persistentInvSupplier.getModifiedBy();
            IsagUser modifiedByNew = invSupplier.getModifiedBy();
            InvScMan mannoOld = persistentInvSupplier.getManno();
            InvScMan mannoNew = invSupplier.getManno();
            Currency ccyOld = persistentInvSupplier.getCcy();
            Currency ccyNew = invSupplier.getCcy();
            GlAccount accnoOld = persistentInvSupplier.getAccno();
            GlAccount accnoNew = invSupplier.getAccno();
            GlCostCenter pricelvlOld = persistentInvSupplier.getPricelvl();
            GlCostCenter pricelvlNew = invSupplier.getPricelvl();
            if (brnnoNew != null) {
                brnnoNew = em.getReference(brnnoNew.getClass(), brnnoNew.getId());
                invSupplier.setBrnno(brnnoNew);
            }
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                invSupplier.setCompanyId(companyIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                invSupplier.setCreatedBy(createdByNew);
            }
            if (typnoNew != null) {
                typnoNew = em.getReference(typnoNew.getClass(), typnoNew.getId());
                invSupplier.setTypno(typnoNew);
            }
            if (countryNew != null) {
                countryNew = em.getReference(countryNew.getClass(), countryNew.getId());
                invSupplier.setCountry(countryNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                invSupplier.setModifiedBy(modifiedByNew);
            }
            if (mannoNew != null) {
                mannoNew = em.getReference(mannoNew.getClass(), mannoNew.getScManId());
                invSupplier.setManno(mannoNew);
            }
            if (ccyNew != null) {
                ccyNew = em.getReference(ccyNew.getClass(), ccyNew.getId());
                invSupplier.setCcy(ccyNew);
            }
            if (accnoNew != null) {
                accnoNew = em.getReference(accnoNew.getClass(), accnoNew.getId());
                invSupplier.setAccno(accnoNew);
            }
            if (pricelvlNew != null) {
                pricelvlNew = em.getReference(pricelvlNew.getClass(), pricelvlNew.getId());
                invSupplier.setPricelvl(pricelvlNew);
            }
            invSupplier = em.merge(invSupplier);
            if (brnnoOld != null && !brnnoOld.equals(brnnoNew)) {
                brnnoOld.getInvSupplierList().remove(invSupplier);
                brnnoOld = em.merge(brnnoOld);
            }
            if (brnnoNew != null && !brnnoNew.equals(brnnoOld)) {
                brnnoNew.getInvSupplierList().add(invSupplier);
                brnnoNew = em.merge(brnnoNew);
            }
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getInvSupplierList().remove(invSupplier);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getInvSupplierList().add(invSupplier);
                companyIdNew = em.merge(companyIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getInvSupplierList().remove(invSupplier);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getInvSupplierList().add(invSupplier);
                createdByNew = em.merge(createdByNew);
            }
            if (typnoOld != null && !typnoOld.equals(typnoNew)) {
                typnoOld.getInvSupplierList().remove(invSupplier);
                typnoOld = em.merge(typnoOld);
            }
            if (typnoNew != null && !typnoNew.equals(typnoOld)) {
                typnoNew.getInvSupplierList().add(invSupplier);
                typnoNew = em.merge(typnoNew);
            }
            if (countryOld != null && !countryOld.equals(countryNew)) {
                countryOld.getInvSupplierList().remove(invSupplier);
                countryOld = em.merge(countryOld);
            }
            if (countryNew != null && !countryNew.equals(countryOld)) {
                countryNew.getInvSupplierList().add(invSupplier);
                countryNew = em.merge(countryNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.getInvSupplierList().remove(invSupplier);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                modifiedByNew.getInvSupplierList().add(invSupplier);
                modifiedByNew = em.merge(modifiedByNew);
            }
            if (mannoOld != null && !mannoOld.equals(mannoNew)) {
                mannoOld.getInvSupplierList().remove(invSupplier);
                mannoOld = em.merge(mannoOld);
            }
            if (mannoNew != null && !mannoNew.equals(mannoOld)) {
                mannoNew.getInvSupplierList().add(invSupplier);
                mannoNew = em.merge(mannoNew);
            }
            if (ccyOld != null && !ccyOld.equals(ccyNew)) {
                ccyOld.getInvSupplierList().remove(invSupplier);
                ccyOld = em.merge(ccyOld);
            }
            if (ccyNew != null && !ccyNew.equals(ccyOld)) {
                ccyNew.getInvSupplierList().add(invSupplier);
                ccyNew = em.merge(ccyNew);
            }
            if (accnoOld != null && !accnoOld.equals(accnoNew)) {
                accnoOld.getInvSupplierList().remove(invSupplier);
                accnoOld = em.merge(accnoOld);
            }
            if (accnoNew != null && !accnoNew.equals(accnoOld)) {
                accnoNew.getInvSupplierList().add(invSupplier);
                accnoNew = em.merge(accnoNew);
            }
            if (pricelvlOld != null && !pricelvlOld.equals(pricelvlNew)) {
                pricelvlOld.getInvSupplierList().remove(invSupplier);
                pricelvlOld = em.merge(pricelvlOld);
            }
            if (pricelvlNew != null && !pricelvlNew.equals(pricelvlOld)) {
                pricelvlNew.getInvSupplierList().add(invSupplier);
                pricelvlNew = em.merge(pricelvlNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invSupplier.getSupplierId();
                if (findInvSupplier(id) == null) {
                    throw new NonexistentEntityException("The invSupplier with id " + id + " no longer exists.");
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
            InvSupplier invSupplier;
            try {
                invSupplier = em.getReference(InvSupplier.class, id);
                invSupplier.getSupplierId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invSupplier with id " + id + " no longer exists.", enfe);
            }
            Branch brnno = invSupplier.getBrnno();
            if (brnno != null) {
                brnno.getInvSupplierList().remove(invSupplier);
                brnno = em.merge(brnno);
            }
            IsagCompany companyId = invSupplier.getCompanyId();
            if (companyId != null) {
                companyId.getInvSupplierList().remove(invSupplier);
                companyId = em.merge(companyId);
            }
            IsagUser createdBy = invSupplier.getCreatedBy();
            if (createdBy != null) {
                createdBy.getInvSupplierList().remove(invSupplier);
                createdBy = em.merge(createdBy);
            }
            GeneralSymbol typno = invSupplier.getTypno();
            if (typno != null) {
                typno.getInvSupplierList().remove(invSupplier);
                typno = em.merge(typno);
            }
            GeneralSymbol country = invSupplier.getCountry();
            if (country != null) {
                country.getInvSupplierList().remove(invSupplier);
                country = em.merge(country);
            }
            IsagUser modifiedBy = invSupplier.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.getInvSupplierList().remove(invSupplier);
                modifiedBy = em.merge(modifiedBy);
            }
            InvScMan manno = invSupplier.getManno();
            if (manno != null) {
                manno.getInvSupplierList().remove(invSupplier);
                manno = em.merge(manno);
            }
            Currency ccy = invSupplier.getCcy();
            if (ccy != null) {
                ccy.getInvSupplierList().remove(invSupplier);
                ccy = em.merge(ccy);
            }
            GlAccount accno = invSupplier.getAccno();
            if (accno != null) {
                accno.getInvSupplierList().remove(invSupplier);
                accno = em.merge(accno);
            }
            GlCostCenter pricelvl = invSupplier.getPricelvl();
            if (pricelvl != null) {
                pricelvl.getInvSupplierList().remove(invSupplier);
                pricelvl = em.merge(pricelvl);
            }
            em.remove(invSupplier);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InvSupplier> findInvSupplierEntities() {
        return findInvSupplierEntities(true, -1, -1);
    }

    public List<InvSupplier> findInvSupplierEntities(int maxResults, int firstResult) {
        return findInvSupplierEntities(false, maxResults, firstResult);
    }

    private List<InvSupplier> findInvSupplierEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InvSupplier.class));
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

    public InvSupplier findInvSupplier(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InvSupplier.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvSupplierCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InvSupplier> rt = cq.from(InvSupplier.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
