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
import Entity.IsagUser;
import Entity.Symbol;
import java.util.ArrayList;
import java.util.List;
import Entity.InvSupplier;
import Entity.GeneralSymbol;
import Entity.InvScMan;
import Entity.InvScCategory;
import Entity.GlCostCenter;
import Entity.Branch;
import Entity.GlAccount;
import Entity.IsagCompany;
import Entity.IsagRole;
import JPA.exceptions.IllegalOrphanException;
import JPA.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdo
 */
public class IsagCompanyJpaController implements Serializable {

    public IsagCompanyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IsagCompany isagCompany) {
        if (isagCompany.getSymbolList() == null) {
            isagCompany.setSymbolList(new ArrayList<Symbol>());
        }
        if (isagCompany.getIsagUserList() == null) {
            isagCompany.setIsagUserList(new ArrayList<IsagUser>());
        }
        if (isagCompany.getInvSupplierList() == null) {
            isagCompany.setInvSupplierList(new ArrayList<InvSupplier>());
        }
        if (isagCompany.getGeneralSymbolList() == null) {
            isagCompany.setGeneralSymbolList(new ArrayList<GeneralSymbol>());
        }
        if (isagCompany.getInvScManList() == null) {
            isagCompany.setInvScManList(new ArrayList<InvScMan>());
        }
        if (isagCompany.getInvScCategoryList() == null) {
            isagCompany.setInvScCategoryList(new ArrayList<InvScCategory>());
        }
        if (isagCompany.getGlCostCenterList() == null) {
            isagCompany.setGlCostCenterList(new ArrayList<GlCostCenter>());
        }
        if (isagCompany.getBranchList() == null) {
            isagCompany.setBranchList(new ArrayList<Branch>());
        }
        if (isagCompany.getGlAccountList() == null) {
            isagCompany.setGlAccountList(new ArrayList<GlAccount>());
        }
        if (isagCompany.getIsagRoleList() == null) {
            isagCompany.setIsagRoleList(new ArrayList<IsagRole>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagUser createdBy = isagCompany.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                isagCompany.setCreatedBy(createdBy);
            }
            IsagUser modifiedBy = isagCompany.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy = em.getReference(modifiedBy.getClass(), modifiedBy.getId());
                isagCompany.setModifiedBy(modifiedBy);
            }
            List<Symbol> attachedSymbolList = new ArrayList<Symbol>();
            for (Symbol symbolListSymbolToAttach : isagCompany.getSymbolList()) {
                symbolListSymbolToAttach = em.getReference(symbolListSymbolToAttach.getClass(), symbolListSymbolToAttach.getId());
                attachedSymbolList.add(symbolListSymbolToAttach);
            }
            isagCompany.setSymbolList(attachedSymbolList);
            List<IsagUser> attachedIsagUserList = new ArrayList<IsagUser>();
            for (IsagUser isagUserListIsagUserToAttach : isagCompany.getIsagUserList()) {
                isagUserListIsagUserToAttach = em.getReference(isagUserListIsagUserToAttach.getClass(), isagUserListIsagUserToAttach.getId());
                attachedIsagUserList.add(isagUserListIsagUserToAttach);
            }
            isagCompany.setIsagUserList(attachedIsagUserList);
            List<InvSupplier> attachedInvSupplierList = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListInvSupplierToAttach : isagCompany.getInvSupplierList()) {
                invSupplierListInvSupplierToAttach = em.getReference(invSupplierListInvSupplierToAttach.getClass(), invSupplierListInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList.add(invSupplierListInvSupplierToAttach);
            }
            isagCompany.setInvSupplierList(attachedInvSupplierList);
            List<GeneralSymbol> attachedGeneralSymbolList = new ArrayList<GeneralSymbol>();
            for (GeneralSymbol generalSymbolListGeneralSymbolToAttach : isagCompany.getGeneralSymbolList()) {
                generalSymbolListGeneralSymbolToAttach = em.getReference(generalSymbolListGeneralSymbolToAttach.getClass(), generalSymbolListGeneralSymbolToAttach.getId());
                attachedGeneralSymbolList.add(generalSymbolListGeneralSymbolToAttach);
            }
            isagCompany.setGeneralSymbolList(attachedGeneralSymbolList);
            List<InvScMan> attachedInvScManList = new ArrayList<InvScMan>();
            for (InvScMan invScManListInvScManToAttach : isagCompany.getInvScManList()) {
                invScManListInvScManToAttach = em.getReference(invScManListInvScManToAttach.getClass(), invScManListInvScManToAttach.getScManId());
                attachedInvScManList.add(invScManListInvScManToAttach);
            }
            isagCompany.setInvScManList(attachedInvScManList);
            List<InvScCategory> attachedInvScCategoryList = new ArrayList<InvScCategory>();
            for (InvScCategory invScCategoryListInvScCategoryToAttach : isagCompany.getInvScCategoryList()) {
                invScCategoryListInvScCategoryToAttach = em.getReference(invScCategoryListInvScCategoryToAttach.getClass(), invScCategoryListInvScCategoryToAttach.getCategoryID());
                attachedInvScCategoryList.add(invScCategoryListInvScCategoryToAttach);
            }
            isagCompany.setInvScCategoryList(attachedInvScCategoryList);
            List<GlCostCenter> attachedGlCostCenterList = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterListGlCostCenterToAttach : isagCompany.getGlCostCenterList()) {
                glCostCenterListGlCostCenterToAttach = em.getReference(glCostCenterListGlCostCenterToAttach.getClass(), glCostCenterListGlCostCenterToAttach.getId());
                attachedGlCostCenterList.add(glCostCenterListGlCostCenterToAttach);
            }
            isagCompany.setGlCostCenterList(attachedGlCostCenterList);
            List<Branch> attachedBranchList = new ArrayList<Branch>();
            for (Branch branchListBranchToAttach : isagCompany.getBranchList()) {
                branchListBranchToAttach = em.getReference(branchListBranchToAttach.getClass(), branchListBranchToAttach.getId());
                attachedBranchList.add(branchListBranchToAttach);
            }
            isagCompany.setBranchList(attachedBranchList);
            List<GlAccount> attachedGlAccountList = new ArrayList<GlAccount>();
            for (GlAccount glAccountListGlAccountToAttach : isagCompany.getGlAccountList()) {
                glAccountListGlAccountToAttach = em.getReference(glAccountListGlAccountToAttach.getClass(), glAccountListGlAccountToAttach.getId());
                attachedGlAccountList.add(glAccountListGlAccountToAttach);
            }
            isagCompany.setGlAccountList(attachedGlAccountList);
            List<IsagRole> attachedIsagRoleList = new ArrayList<IsagRole>();
            for (IsagRole isagRoleListIsagRoleToAttach : isagCompany.getIsagRoleList()) {
                isagRoleListIsagRoleToAttach = em.getReference(isagRoleListIsagRoleToAttach.getClass(), isagRoleListIsagRoleToAttach.getId());
                attachedIsagRoleList.add(isagRoleListIsagRoleToAttach);
            }
            isagCompany.setIsagRoleList(attachedIsagRoleList);
            em.persist(isagCompany);
            if (createdBy != null) {
                createdBy.getIsagCompanyList().add(isagCompany);
                createdBy = em.merge(createdBy);
            }
            if (modifiedBy != null) {
                modifiedBy.getIsagCompanyList().add(isagCompany);
                modifiedBy = em.merge(modifiedBy);
            }
            for (Symbol symbolListSymbol : isagCompany.getSymbolList()) {
                IsagCompany oldCompanyIdOfSymbolListSymbol = symbolListSymbol.getCompanyId();
                symbolListSymbol.setCompanyId(isagCompany);
                symbolListSymbol = em.merge(symbolListSymbol);
                if (oldCompanyIdOfSymbolListSymbol != null) {
                    oldCompanyIdOfSymbolListSymbol.getSymbolList().remove(symbolListSymbol);
                    oldCompanyIdOfSymbolListSymbol = em.merge(oldCompanyIdOfSymbolListSymbol);
                }
            }
            for (IsagUser isagUserListIsagUser : isagCompany.getIsagUserList()) {
                IsagCompany oldCompanyIdOfIsagUserListIsagUser = isagUserListIsagUser.getCompanyId();
                isagUserListIsagUser.setCompanyId(isagCompany);
                isagUserListIsagUser = em.merge(isagUserListIsagUser);
                if (oldCompanyIdOfIsagUserListIsagUser != null) {
                    oldCompanyIdOfIsagUserListIsagUser.getIsagUserList().remove(isagUserListIsagUser);
                    oldCompanyIdOfIsagUserListIsagUser = em.merge(oldCompanyIdOfIsagUserListIsagUser);
                }
            }
            for (InvSupplier invSupplierListInvSupplier : isagCompany.getInvSupplierList()) {
                IsagCompany oldCompanyIdOfInvSupplierListInvSupplier = invSupplierListInvSupplier.getCompanyId();
                invSupplierListInvSupplier.setCompanyId(isagCompany);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
                if (oldCompanyIdOfInvSupplierListInvSupplier != null) {
                    oldCompanyIdOfInvSupplierListInvSupplier.getInvSupplierList().remove(invSupplierListInvSupplier);
                    oldCompanyIdOfInvSupplierListInvSupplier = em.merge(oldCompanyIdOfInvSupplierListInvSupplier);
                }
            }
            for (GeneralSymbol generalSymbolListGeneralSymbol : isagCompany.getGeneralSymbolList()) {
                IsagCompany oldCompanyIdOfGeneralSymbolListGeneralSymbol = generalSymbolListGeneralSymbol.getCompanyId();
                generalSymbolListGeneralSymbol.setCompanyId(isagCompany);
                generalSymbolListGeneralSymbol = em.merge(generalSymbolListGeneralSymbol);
                if (oldCompanyIdOfGeneralSymbolListGeneralSymbol != null) {
                    oldCompanyIdOfGeneralSymbolListGeneralSymbol.getGeneralSymbolList().remove(generalSymbolListGeneralSymbol);
                    oldCompanyIdOfGeneralSymbolListGeneralSymbol = em.merge(oldCompanyIdOfGeneralSymbolListGeneralSymbol);
                }
            }
            for (InvScMan invScManListInvScMan : isagCompany.getInvScManList()) {
                IsagCompany oldCompanyIdOfInvScManListInvScMan = invScManListInvScMan.getCompanyId();
                invScManListInvScMan.setCompanyId(isagCompany);
                invScManListInvScMan = em.merge(invScManListInvScMan);
                if (oldCompanyIdOfInvScManListInvScMan != null) {
                    oldCompanyIdOfInvScManListInvScMan.getInvScManList().remove(invScManListInvScMan);
                    oldCompanyIdOfInvScManListInvScMan = em.merge(oldCompanyIdOfInvScManListInvScMan);
                }
            }
            for (InvScCategory invScCategoryListInvScCategory : isagCompany.getInvScCategoryList()) {
                IsagCompany oldCompanyIdOfInvScCategoryListInvScCategory = invScCategoryListInvScCategory.getCompanyId();
                invScCategoryListInvScCategory.setCompanyId(isagCompany);
                invScCategoryListInvScCategory = em.merge(invScCategoryListInvScCategory);
                if (oldCompanyIdOfInvScCategoryListInvScCategory != null) {
                    oldCompanyIdOfInvScCategoryListInvScCategory.getInvScCategoryList().remove(invScCategoryListInvScCategory);
                    oldCompanyIdOfInvScCategoryListInvScCategory = em.merge(oldCompanyIdOfInvScCategoryListInvScCategory);
                }
            }
            for (GlCostCenter glCostCenterListGlCostCenter : isagCompany.getGlCostCenterList()) {
                IsagCompany oldCompanyIdOfGlCostCenterListGlCostCenter = glCostCenterListGlCostCenter.getCompanyId();
                glCostCenterListGlCostCenter.setCompanyId(isagCompany);
                glCostCenterListGlCostCenter = em.merge(glCostCenterListGlCostCenter);
                if (oldCompanyIdOfGlCostCenterListGlCostCenter != null) {
                    oldCompanyIdOfGlCostCenterListGlCostCenter.getGlCostCenterList().remove(glCostCenterListGlCostCenter);
                    oldCompanyIdOfGlCostCenterListGlCostCenter = em.merge(oldCompanyIdOfGlCostCenterListGlCostCenter);
                }
            }
            for (Branch branchListBranch : isagCompany.getBranchList()) {
                IsagCompany oldCompanyIdOfBranchListBranch = branchListBranch.getCompanyId();
                branchListBranch.setCompanyId(isagCompany);
                branchListBranch = em.merge(branchListBranch);
                if (oldCompanyIdOfBranchListBranch != null) {
                    oldCompanyIdOfBranchListBranch.getBranchList().remove(branchListBranch);
                    oldCompanyIdOfBranchListBranch = em.merge(oldCompanyIdOfBranchListBranch);
                }
            }
            for (GlAccount glAccountListGlAccount : isagCompany.getGlAccountList()) {
                IsagCompany oldCompanyIdOfGlAccountListGlAccount = glAccountListGlAccount.getCompanyId();
                glAccountListGlAccount.setCompanyId(isagCompany);
                glAccountListGlAccount = em.merge(glAccountListGlAccount);
                if (oldCompanyIdOfGlAccountListGlAccount != null) {
                    oldCompanyIdOfGlAccountListGlAccount.getGlAccountList().remove(glAccountListGlAccount);
                    oldCompanyIdOfGlAccountListGlAccount = em.merge(oldCompanyIdOfGlAccountListGlAccount);
                }
            }
            for (IsagRole isagRoleListIsagRole : isagCompany.getIsagRoleList()) {
                IsagCompany oldCompanyIdOfIsagRoleListIsagRole = isagRoleListIsagRole.getCompanyId();
                isagRoleListIsagRole.setCompanyId(isagCompany);
                isagRoleListIsagRole = em.merge(isagRoleListIsagRole);
                if (oldCompanyIdOfIsagRoleListIsagRole != null) {
                    oldCompanyIdOfIsagRoleListIsagRole.getIsagRoleList().remove(isagRoleListIsagRole);
                    oldCompanyIdOfIsagRoleListIsagRole = em.merge(oldCompanyIdOfIsagRoleListIsagRole);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IsagCompany isagCompany) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagCompany persistentIsagCompany = em.find(IsagCompany.class, isagCompany.getId());
            IsagUser createdByOld = persistentIsagCompany.getCreatedBy();
            IsagUser createdByNew = isagCompany.getCreatedBy();
            IsagUser modifiedByOld = persistentIsagCompany.getModifiedBy();
            IsagUser modifiedByNew = isagCompany.getModifiedBy();
            List<Symbol> symbolListOld = persistentIsagCompany.getSymbolList();
            List<Symbol> symbolListNew = isagCompany.getSymbolList();
            List<IsagUser> isagUserListOld = persistentIsagCompany.getIsagUserList();
            List<IsagUser> isagUserListNew = isagCompany.getIsagUserList();
            List<InvSupplier> invSupplierListOld = persistentIsagCompany.getInvSupplierList();
            List<InvSupplier> invSupplierListNew = isagCompany.getInvSupplierList();
            List<GeneralSymbol> generalSymbolListOld = persistentIsagCompany.getGeneralSymbolList();
            List<GeneralSymbol> generalSymbolListNew = isagCompany.getGeneralSymbolList();
            List<InvScMan> invScManListOld = persistentIsagCompany.getInvScManList();
            List<InvScMan> invScManListNew = isagCompany.getInvScManList();
            List<InvScCategory> invScCategoryListOld = persistentIsagCompany.getInvScCategoryList();
            List<InvScCategory> invScCategoryListNew = isagCompany.getInvScCategoryList();
            List<GlCostCenter> glCostCenterListOld = persistentIsagCompany.getGlCostCenterList();
            List<GlCostCenter> glCostCenterListNew = isagCompany.getGlCostCenterList();
            List<Branch> branchListOld = persistentIsagCompany.getBranchList();
            List<Branch> branchListNew = isagCompany.getBranchList();
            List<GlAccount> glAccountListOld = persistentIsagCompany.getGlAccountList();
            List<GlAccount> glAccountListNew = isagCompany.getGlAccountList();
            List<IsagRole> isagRoleListOld = persistentIsagCompany.getIsagRoleList();
            List<IsagRole> isagRoleListNew = isagCompany.getIsagRoleList();
            List<String> illegalOrphanMessages = null;
            for (InvSupplier invSupplierListOldInvSupplier : invSupplierListOld) {
                if (!invSupplierListNew.contains(invSupplierListOldInvSupplier)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InvSupplier " + invSupplierListOldInvSupplier + " since its companyId field is not nullable.");
                }
            }
            for (InvScMan invScManListOldInvScMan : invScManListOld) {
                if (!invScManListNew.contains(invScManListOldInvScMan)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InvScMan " + invScManListOldInvScMan + " since its companyId field is not nullable.");
                }
            }
            for (InvScCategory invScCategoryListOldInvScCategory : invScCategoryListOld) {
                if (!invScCategoryListNew.contains(invScCategoryListOldInvScCategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InvScCategory " + invScCategoryListOldInvScCategory + " since its companyId field is not nullable.");
                }
            }
            for (GlCostCenter glCostCenterListOldGlCostCenter : glCostCenterListOld) {
                if (!glCostCenterListNew.contains(glCostCenterListOldGlCostCenter)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GlCostCenter " + glCostCenterListOldGlCostCenter + " since its companyId field is not nullable.");
                }
            }
            for (GlAccount glAccountListOldGlAccount : glAccountListOld) {
                if (!glAccountListNew.contains(glAccountListOldGlAccount)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GlAccount " + glAccountListOldGlAccount + " since its companyId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                isagCompany.setCreatedBy(createdByNew);
            }
            if (modifiedByNew != null) {
                modifiedByNew = em.getReference(modifiedByNew.getClass(), modifiedByNew.getId());
                isagCompany.setModifiedBy(modifiedByNew);
            }
            List<Symbol> attachedSymbolListNew = new ArrayList<Symbol>();
            for (Symbol symbolListNewSymbolToAttach : symbolListNew) {
                symbolListNewSymbolToAttach = em.getReference(symbolListNewSymbolToAttach.getClass(), symbolListNewSymbolToAttach.getId());
                attachedSymbolListNew.add(symbolListNewSymbolToAttach);
            }
            symbolListNew = attachedSymbolListNew;
            isagCompany.setSymbolList(symbolListNew);
            List<IsagUser> attachedIsagUserListNew = new ArrayList<IsagUser>();
            for (IsagUser isagUserListNewIsagUserToAttach : isagUserListNew) {
                isagUserListNewIsagUserToAttach = em.getReference(isagUserListNewIsagUserToAttach.getClass(), isagUserListNewIsagUserToAttach.getId());
                attachedIsagUserListNew.add(isagUserListNewIsagUserToAttach);
            }
            isagUserListNew = attachedIsagUserListNew;
            isagCompany.setIsagUserList(isagUserListNew);
            List<InvSupplier> attachedInvSupplierListNew = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListNewInvSupplierToAttach : invSupplierListNew) {
                invSupplierListNewInvSupplierToAttach = em.getReference(invSupplierListNewInvSupplierToAttach.getClass(), invSupplierListNewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierListNew.add(invSupplierListNewInvSupplierToAttach);
            }
            invSupplierListNew = attachedInvSupplierListNew;
            isagCompany.setInvSupplierList(invSupplierListNew);
            List<GeneralSymbol> attachedGeneralSymbolListNew = new ArrayList<GeneralSymbol>();
            for (GeneralSymbol generalSymbolListNewGeneralSymbolToAttach : generalSymbolListNew) {
                generalSymbolListNewGeneralSymbolToAttach = em.getReference(generalSymbolListNewGeneralSymbolToAttach.getClass(), generalSymbolListNewGeneralSymbolToAttach.getId());
                attachedGeneralSymbolListNew.add(generalSymbolListNewGeneralSymbolToAttach);
            }
            generalSymbolListNew = attachedGeneralSymbolListNew;
            isagCompany.setGeneralSymbolList(generalSymbolListNew);
            List<InvScMan> attachedInvScManListNew = new ArrayList<InvScMan>();
            for (InvScMan invScManListNewInvScManToAttach : invScManListNew) {
                invScManListNewInvScManToAttach = em.getReference(invScManListNewInvScManToAttach.getClass(), invScManListNewInvScManToAttach.getScManId());
                attachedInvScManListNew.add(invScManListNewInvScManToAttach);
            }
            invScManListNew = attachedInvScManListNew;
            isagCompany.setInvScManList(invScManListNew);
            List<InvScCategory> attachedInvScCategoryListNew = new ArrayList<InvScCategory>();
            for (InvScCategory invScCategoryListNewInvScCategoryToAttach : invScCategoryListNew) {
                invScCategoryListNewInvScCategoryToAttach = em.getReference(invScCategoryListNewInvScCategoryToAttach.getClass(), invScCategoryListNewInvScCategoryToAttach.getCategoryID());
                attachedInvScCategoryListNew.add(invScCategoryListNewInvScCategoryToAttach);
            }
            invScCategoryListNew = attachedInvScCategoryListNew;
            isagCompany.setInvScCategoryList(invScCategoryListNew);
            List<GlCostCenter> attachedGlCostCenterListNew = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterListNewGlCostCenterToAttach : glCostCenterListNew) {
                glCostCenterListNewGlCostCenterToAttach = em.getReference(glCostCenterListNewGlCostCenterToAttach.getClass(), glCostCenterListNewGlCostCenterToAttach.getId());
                attachedGlCostCenterListNew.add(glCostCenterListNewGlCostCenterToAttach);
            }
            glCostCenterListNew = attachedGlCostCenterListNew;
            isagCompany.setGlCostCenterList(glCostCenterListNew);
            List<Branch> attachedBranchListNew = new ArrayList<Branch>();
            for (Branch branchListNewBranchToAttach : branchListNew) {
                branchListNewBranchToAttach = em.getReference(branchListNewBranchToAttach.getClass(), branchListNewBranchToAttach.getId());
                attachedBranchListNew.add(branchListNewBranchToAttach);
            }
            branchListNew = attachedBranchListNew;
            isagCompany.setBranchList(branchListNew);
            List<GlAccount> attachedGlAccountListNew = new ArrayList<GlAccount>();
            for (GlAccount glAccountListNewGlAccountToAttach : glAccountListNew) {
                glAccountListNewGlAccountToAttach = em.getReference(glAccountListNewGlAccountToAttach.getClass(), glAccountListNewGlAccountToAttach.getId());
                attachedGlAccountListNew.add(glAccountListNewGlAccountToAttach);
            }
            glAccountListNew = attachedGlAccountListNew;
            isagCompany.setGlAccountList(glAccountListNew);
            List<IsagRole> attachedIsagRoleListNew = new ArrayList<IsagRole>();
            for (IsagRole isagRoleListNewIsagRoleToAttach : isagRoleListNew) {
                isagRoleListNewIsagRoleToAttach = em.getReference(isagRoleListNewIsagRoleToAttach.getClass(), isagRoleListNewIsagRoleToAttach.getId());
                attachedIsagRoleListNew.add(isagRoleListNewIsagRoleToAttach);
            }
            isagRoleListNew = attachedIsagRoleListNew;
            isagCompany.setIsagRoleList(isagRoleListNew);
            isagCompany = em.merge(isagCompany);
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getIsagCompanyList().remove(isagCompany);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getIsagCompanyList().add(isagCompany);
                createdByNew = em.merge(createdByNew);
            }
            if (modifiedByOld != null && !modifiedByOld.equals(modifiedByNew)) {
                modifiedByOld.getIsagCompanyList().remove(isagCompany);
                modifiedByOld = em.merge(modifiedByOld);
            }
            if (modifiedByNew != null && !modifiedByNew.equals(modifiedByOld)) {
                modifiedByNew.getIsagCompanyList().add(isagCompany);
                modifiedByNew = em.merge(modifiedByNew);
            }
            for (Symbol symbolListOldSymbol : symbolListOld) {
                if (!symbolListNew.contains(symbolListOldSymbol)) {
                    symbolListOldSymbol.setCompanyId(null);
                    symbolListOldSymbol = em.merge(symbolListOldSymbol);
                }
            }
            for (Symbol symbolListNewSymbol : symbolListNew) {
                if (!symbolListOld.contains(symbolListNewSymbol)) {
                    IsagCompany oldCompanyIdOfSymbolListNewSymbol = symbolListNewSymbol.getCompanyId();
                    symbolListNewSymbol.setCompanyId(isagCompany);
                    symbolListNewSymbol = em.merge(symbolListNewSymbol);
                    if (oldCompanyIdOfSymbolListNewSymbol != null && !oldCompanyIdOfSymbolListNewSymbol.equals(isagCompany)) {
                        oldCompanyIdOfSymbolListNewSymbol.getSymbolList().remove(symbolListNewSymbol);
                        oldCompanyIdOfSymbolListNewSymbol = em.merge(oldCompanyIdOfSymbolListNewSymbol);
                    }
                }
            }
            for (IsagUser isagUserListOldIsagUser : isagUserListOld) {
                if (!isagUserListNew.contains(isagUserListOldIsagUser)) {
                    isagUserListOldIsagUser.setCompanyId(null);
                    isagUserListOldIsagUser = em.merge(isagUserListOldIsagUser);
                }
            }
            for (IsagUser isagUserListNewIsagUser : isagUserListNew) {
                if (!isagUserListOld.contains(isagUserListNewIsagUser)) {
                    IsagCompany oldCompanyIdOfIsagUserListNewIsagUser = isagUserListNewIsagUser.getCompanyId();
                    isagUserListNewIsagUser.setCompanyId(isagCompany);
                    isagUserListNewIsagUser = em.merge(isagUserListNewIsagUser);
                    if (oldCompanyIdOfIsagUserListNewIsagUser != null && !oldCompanyIdOfIsagUserListNewIsagUser.equals(isagCompany)) {
                        oldCompanyIdOfIsagUserListNewIsagUser.getIsagUserList().remove(isagUserListNewIsagUser);
                        oldCompanyIdOfIsagUserListNewIsagUser = em.merge(oldCompanyIdOfIsagUserListNewIsagUser);
                    }
                }
            }
            for (InvSupplier invSupplierListNewInvSupplier : invSupplierListNew) {
                if (!invSupplierListOld.contains(invSupplierListNewInvSupplier)) {
                    IsagCompany oldCompanyIdOfInvSupplierListNewInvSupplier = invSupplierListNewInvSupplier.getCompanyId();
                    invSupplierListNewInvSupplier.setCompanyId(isagCompany);
                    invSupplierListNewInvSupplier = em.merge(invSupplierListNewInvSupplier);
                    if (oldCompanyIdOfInvSupplierListNewInvSupplier != null && !oldCompanyIdOfInvSupplierListNewInvSupplier.equals(isagCompany)) {
                        oldCompanyIdOfInvSupplierListNewInvSupplier.getInvSupplierList().remove(invSupplierListNewInvSupplier);
                        oldCompanyIdOfInvSupplierListNewInvSupplier = em.merge(oldCompanyIdOfInvSupplierListNewInvSupplier);
                    }
                }
            }
            for (GeneralSymbol generalSymbolListOldGeneralSymbol : generalSymbolListOld) {
                if (!generalSymbolListNew.contains(generalSymbolListOldGeneralSymbol)) {
                    generalSymbolListOldGeneralSymbol.setCompanyId(null);
                    generalSymbolListOldGeneralSymbol = em.merge(generalSymbolListOldGeneralSymbol);
                }
            }
            for (GeneralSymbol generalSymbolListNewGeneralSymbol : generalSymbolListNew) {
                if (!generalSymbolListOld.contains(generalSymbolListNewGeneralSymbol)) {
                    IsagCompany oldCompanyIdOfGeneralSymbolListNewGeneralSymbol = generalSymbolListNewGeneralSymbol.getCompanyId();
                    generalSymbolListNewGeneralSymbol.setCompanyId(isagCompany);
                    generalSymbolListNewGeneralSymbol = em.merge(generalSymbolListNewGeneralSymbol);
                    if (oldCompanyIdOfGeneralSymbolListNewGeneralSymbol != null && !oldCompanyIdOfGeneralSymbolListNewGeneralSymbol.equals(isagCompany)) {
                        oldCompanyIdOfGeneralSymbolListNewGeneralSymbol.getGeneralSymbolList().remove(generalSymbolListNewGeneralSymbol);
                        oldCompanyIdOfGeneralSymbolListNewGeneralSymbol = em.merge(oldCompanyIdOfGeneralSymbolListNewGeneralSymbol);
                    }
                }
            }
            for (InvScMan invScManListNewInvScMan : invScManListNew) {
                if (!invScManListOld.contains(invScManListNewInvScMan)) {
                    IsagCompany oldCompanyIdOfInvScManListNewInvScMan = invScManListNewInvScMan.getCompanyId();
                    invScManListNewInvScMan.setCompanyId(isagCompany);
                    invScManListNewInvScMan = em.merge(invScManListNewInvScMan);
                    if (oldCompanyIdOfInvScManListNewInvScMan != null && !oldCompanyIdOfInvScManListNewInvScMan.equals(isagCompany)) {
                        oldCompanyIdOfInvScManListNewInvScMan.getInvScManList().remove(invScManListNewInvScMan);
                        oldCompanyIdOfInvScManListNewInvScMan = em.merge(oldCompanyIdOfInvScManListNewInvScMan);
                    }
                }
            }
            for (InvScCategory invScCategoryListNewInvScCategory : invScCategoryListNew) {
                if (!invScCategoryListOld.contains(invScCategoryListNewInvScCategory)) {
                    IsagCompany oldCompanyIdOfInvScCategoryListNewInvScCategory = invScCategoryListNewInvScCategory.getCompanyId();
                    invScCategoryListNewInvScCategory.setCompanyId(isagCompany);
                    invScCategoryListNewInvScCategory = em.merge(invScCategoryListNewInvScCategory);
                    if (oldCompanyIdOfInvScCategoryListNewInvScCategory != null && !oldCompanyIdOfInvScCategoryListNewInvScCategory.equals(isagCompany)) {
                        oldCompanyIdOfInvScCategoryListNewInvScCategory.getInvScCategoryList().remove(invScCategoryListNewInvScCategory);
                        oldCompanyIdOfInvScCategoryListNewInvScCategory = em.merge(oldCompanyIdOfInvScCategoryListNewInvScCategory);
                    }
                }
            }
            for (GlCostCenter glCostCenterListNewGlCostCenter : glCostCenterListNew) {
                if (!glCostCenterListOld.contains(glCostCenterListNewGlCostCenter)) {
                    IsagCompany oldCompanyIdOfGlCostCenterListNewGlCostCenter = glCostCenterListNewGlCostCenter.getCompanyId();
                    glCostCenterListNewGlCostCenter.setCompanyId(isagCompany);
                    glCostCenterListNewGlCostCenter = em.merge(glCostCenterListNewGlCostCenter);
                    if (oldCompanyIdOfGlCostCenterListNewGlCostCenter != null && !oldCompanyIdOfGlCostCenterListNewGlCostCenter.equals(isagCompany)) {
                        oldCompanyIdOfGlCostCenterListNewGlCostCenter.getGlCostCenterList().remove(glCostCenterListNewGlCostCenter);
                        oldCompanyIdOfGlCostCenterListNewGlCostCenter = em.merge(oldCompanyIdOfGlCostCenterListNewGlCostCenter);
                    }
                }
            }
            for (Branch branchListOldBranch : branchListOld) {
                if (!branchListNew.contains(branchListOldBranch)) {
                    branchListOldBranch.setCompanyId(null);
                    branchListOldBranch = em.merge(branchListOldBranch);
                }
            }
            for (Branch branchListNewBranch : branchListNew) {
                if (!branchListOld.contains(branchListNewBranch)) {
                    IsagCompany oldCompanyIdOfBranchListNewBranch = branchListNewBranch.getCompanyId();
                    branchListNewBranch.setCompanyId(isagCompany);
                    branchListNewBranch = em.merge(branchListNewBranch);
                    if (oldCompanyIdOfBranchListNewBranch != null && !oldCompanyIdOfBranchListNewBranch.equals(isagCompany)) {
                        oldCompanyIdOfBranchListNewBranch.getBranchList().remove(branchListNewBranch);
                        oldCompanyIdOfBranchListNewBranch = em.merge(oldCompanyIdOfBranchListNewBranch);
                    }
                }
            }
            for (GlAccount glAccountListNewGlAccount : glAccountListNew) {
                if (!glAccountListOld.contains(glAccountListNewGlAccount)) {
                    IsagCompany oldCompanyIdOfGlAccountListNewGlAccount = glAccountListNewGlAccount.getCompanyId();
                    glAccountListNewGlAccount.setCompanyId(isagCompany);
                    glAccountListNewGlAccount = em.merge(glAccountListNewGlAccount);
                    if (oldCompanyIdOfGlAccountListNewGlAccount != null && !oldCompanyIdOfGlAccountListNewGlAccount.equals(isagCompany)) {
                        oldCompanyIdOfGlAccountListNewGlAccount.getGlAccountList().remove(glAccountListNewGlAccount);
                        oldCompanyIdOfGlAccountListNewGlAccount = em.merge(oldCompanyIdOfGlAccountListNewGlAccount);
                    }
                }
            }
            for (IsagRole isagRoleListOldIsagRole : isagRoleListOld) {
                if (!isagRoleListNew.contains(isagRoleListOldIsagRole)) {
                    isagRoleListOldIsagRole.setCompanyId(null);
                    isagRoleListOldIsagRole = em.merge(isagRoleListOldIsagRole);
                }
            }
            for (IsagRole isagRoleListNewIsagRole : isagRoleListNew) {
                if (!isagRoleListOld.contains(isagRoleListNewIsagRole)) {
                    IsagCompany oldCompanyIdOfIsagRoleListNewIsagRole = isagRoleListNewIsagRole.getCompanyId();
                    isagRoleListNewIsagRole.setCompanyId(isagCompany);
                    isagRoleListNewIsagRole = em.merge(isagRoleListNewIsagRole);
                    if (oldCompanyIdOfIsagRoleListNewIsagRole != null && !oldCompanyIdOfIsagRoleListNewIsagRole.equals(isagCompany)) {
                        oldCompanyIdOfIsagRoleListNewIsagRole.getIsagRoleList().remove(isagRoleListNewIsagRole);
                        oldCompanyIdOfIsagRoleListNewIsagRole = em.merge(oldCompanyIdOfIsagRoleListNewIsagRole);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = isagCompany.getId();
                if (findIsagCompany(id) == null) {
                    throw new NonexistentEntityException("The isagCompany with id " + id + " no longer exists.");
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
            IsagCompany isagCompany;
            try {
                isagCompany = em.getReference(IsagCompany.class, id);
                isagCompany.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The isagCompany with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InvSupplier> invSupplierListOrphanCheck = isagCompany.getInvSupplierList();
            for (InvSupplier invSupplierListOrphanCheckInvSupplier : invSupplierListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagCompany (" + isagCompany + ") cannot be destroyed since the InvSupplier " + invSupplierListOrphanCheckInvSupplier + " in its invSupplierList field has a non-nullable companyId field.");
            }
            List<InvScMan> invScManListOrphanCheck = isagCompany.getInvScManList();
            for (InvScMan invScManListOrphanCheckInvScMan : invScManListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagCompany (" + isagCompany + ") cannot be destroyed since the InvScMan " + invScManListOrphanCheckInvScMan + " in its invScManList field has a non-nullable companyId field.");
            }
            List<InvScCategory> invScCategoryListOrphanCheck = isagCompany.getInvScCategoryList();
            for (InvScCategory invScCategoryListOrphanCheckInvScCategory : invScCategoryListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagCompany (" + isagCompany + ") cannot be destroyed since the InvScCategory " + invScCategoryListOrphanCheckInvScCategory + " in its invScCategoryList field has a non-nullable companyId field.");
            }
            List<GlCostCenter> glCostCenterListOrphanCheck = isagCompany.getGlCostCenterList();
            for (GlCostCenter glCostCenterListOrphanCheckGlCostCenter : glCostCenterListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagCompany (" + isagCompany + ") cannot be destroyed since the GlCostCenter " + glCostCenterListOrphanCheckGlCostCenter + " in its glCostCenterList field has a non-nullable companyId field.");
            }
            List<GlAccount> glAccountListOrphanCheck = isagCompany.getGlAccountList();
            for (GlAccount glAccountListOrphanCheckGlAccount : glAccountListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagCompany (" + isagCompany + ") cannot be destroyed since the GlAccount " + glAccountListOrphanCheckGlAccount + " in its glAccountList field has a non-nullable companyId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            IsagUser createdBy = isagCompany.getCreatedBy();
            if (createdBy != null) {
                createdBy.getIsagCompanyList().remove(isagCompany);
                createdBy = em.merge(createdBy);
            }
            IsagUser modifiedBy = isagCompany.getModifiedBy();
            if (modifiedBy != null) {
                modifiedBy.getIsagCompanyList().remove(isagCompany);
                modifiedBy = em.merge(modifiedBy);
            }
            List<Symbol> symbolList = isagCompany.getSymbolList();
            for (Symbol symbolListSymbol : symbolList) {
                symbolListSymbol.setCompanyId(null);
                symbolListSymbol = em.merge(symbolListSymbol);
            }
            List<IsagUser> isagUserList = isagCompany.getIsagUserList();
            for (IsagUser isagUserListIsagUser : isagUserList) {
                isagUserListIsagUser.setCompanyId(null);
                isagUserListIsagUser = em.merge(isagUserListIsagUser);
            }
            List<GeneralSymbol> generalSymbolList = isagCompany.getGeneralSymbolList();
            for (GeneralSymbol generalSymbolListGeneralSymbol : generalSymbolList) {
                generalSymbolListGeneralSymbol.setCompanyId(null);
                generalSymbolListGeneralSymbol = em.merge(generalSymbolListGeneralSymbol);
            }
            List<Branch> branchList = isagCompany.getBranchList();
            for (Branch branchListBranch : branchList) {
                branchListBranch.setCompanyId(null);
                branchListBranch = em.merge(branchListBranch);
            }
            List<IsagRole> isagRoleList = isagCompany.getIsagRoleList();
            for (IsagRole isagRoleListIsagRole : isagRoleList) {
                isagRoleListIsagRole.setCompanyId(null);
                isagRoleListIsagRole = em.merge(isagRoleListIsagRole);
            }
            em.remove(isagCompany);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IsagCompany> findIsagCompanyEntities() {
        return findIsagCompanyEntities(true, -1, -1);
    }

    public List<IsagCompany> findIsagCompanyEntities(int maxResults, int firstResult) {
        return findIsagCompanyEntities(false, maxResults, firstResult);
    }

    private List<IsagCompany> findIsagCompanyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IsagCompany.class));
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

    public IsagCompany findIsagCompany(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IsagCompany.class, id);
        } finally {
            em.close();
        }
    }

    public int getIsagCompanyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IsagCompany> rt = cq.from(IsagCompany.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
