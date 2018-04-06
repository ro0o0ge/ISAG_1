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
import Entity.Symbol;
import Entity.IsagRole;
import java.util.ArrayList;
import java.util.List;
import Entity.InvSupplier;
import Entity.GeneralSymbol;
import Entity.InvScCategory;
import Entity.Currency;
import Entity.GlCostCenter;
import Entity.Branch;
import Entity.GlAccount;
import JPA.exceptions.IllegalOrphanException;
import JPA.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abdo
 */
public class IsagUserJpaController implements Serializable {

    public IsagUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IsagUser isagUser) {
        if (isagUser.getSymbolList() == null) {
            isagUser.setSymbolList(new ArrayList<Symbol>());
        }
        if (isagUser.getSymbolList1() == null) {
            isagUser.setSymbolList1(new ArrayList<Symbol>());
        }
        if (isagUser.getIsagCompanyList() == null) {
            isagUser.setIsagCompanyList(new ArrayList<IsagCompany>());
        }
        if (isagUser.getIsagCompanyList1() == null) {
            isagUser.setIsagCompanyList1(new ArrayList<IsagCompany>());
        }
        if (isagUser.getIsagUserList() == null) {
            isagUser.setIsagUserList(new ArrayList<IsagUser>());
        }
        if (isagUser.getInvSupplierList() == null) {
            isagUser.setInvSupplierList(new ArrayList<InvSupplier>());
        }
        if (isagUser.getInvSupplierList1() == null) {
            isagUser.setInvSupplierList1(new ArrayList<InvSupplier>());
        }
        if (isagUser.getGeneralSymbolList() == null) {
            isagUser.setGeneralSymbolList(new ArrayList<GeneralSymbol>());
        }
        if (isagUser.getGeneralSymbolList1() == null) {
            isagUser.setGeneralSymbolList1(new ArrayList<GeneralSymbol>());
        }
        if (isagUser.getInvScCategoryList() == null) {
            isagUser.setInvScCategoryList(new ArrayList<InvScCategory>());
        }
        if (isagUser.getInvScCategoryList1() == null) {
            isagUser.setInvScCategoryList1(new ArrayList<InvScCategory>());
        }
        if (isagUser.getCurrencyList() == null) {
            isagUser.setCurrencyList(new ArrayList<Currency>());
        }
        if (isagUser.getCurrencyList1() == null) {
            isagUser.setCurrencyList1(new ArrayList<Currency>());
        }
        if (isagUser.getGlCostCenterList() == null) {
            isagUser.setGlCostCenterList(new ArrayList<GlCostCenter>());
        }
        if (isagUser.getGlCostCenterList1() == null) {
            isagUser.setGlCostCenterList1(new ArrayList<GlCostCenter>());
        }
        if (isagUser.getBranchList() == null) {
            isagUser.setBranchList(new ArrayList<Branch>());
        }
        if (isagUser.getBranchList1() == null) {
            isagUser.setBranchList1(new ArrayList<Branch>());
        }
        if (isagUser.getGlAccountList() == null) {
            isagUser.setGlAccountList(new ArrayList<GlAccount>());
        }
        if (isagUser.getGlAccountList1() == null) {
            isagUser.setGlAccountList1(new ArrayList<GlAccount>());
        }
        if (isagUser.getIsagRoleList() == null) {
            isagUser.setIsagRoleList(new ArrayList<IsagRole>());
        }
        if (isagUser.getIsagRoleList1() == null) {
            isagUser.setIsagRoleList1(new ArrayList<IsagRole>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagCompany companyId = isagUser.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getId());
                isagUser.setCompanyId(companyId);
            }
            IsagUser createdBy = isagUser.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getId());
                isagUser.setCreatedBy(createdBy);
            }
            Symbol lang = isagUser.getLang();
            if (lang != null) {
                lang = em.getReference(lang.getClass(), lang.getId());
                isagUser.setLang(lang);
            }
            IsagRole roleId = isagUser.getRoleId();
            if (roleId != null) {
                roleId = em.getReference(roleId.getClass(), roleId.getId());
                isagUser.setRoleId(roleId);
            }
            List<Symbol> attachedSymbolList = new ArrayList<Symbol>();
            for (Symbol symbolListSymbolToAttach : isagUser.getSymbolList()) {
                symbolListSymbolToAttach = em.getReference(symbolListSymbolToAttach.getClass(), symbolListSymbolToAttach.getId());
                attachedSymbolList.add(symbolListSymbolToAttach);
            }
            isagUser.setSymbolList(attachedSymbolList);
            List<Symbol> attachedSymbolList1 = new ArrayList<Symbol>();
            for (Symbol symbolList1SymbolToAttach : isagUser.getSymbolList1()) {
                symbolList1SymbolToAttach = em.getReference(symbolList1SymbolToAttach.getClass(), symbolList1SymbolToAttach.getId());
                attachedSymbolList1.add(symbolList1SymbolToAttach);
            }
            isagUser.setSymbolList1(attachedSymbolList1);
            List<IsagCompany> attachedIsagCompanyList = new ArrayList<IsagCompany>();
            for (IsagCompany isagCompanyListIsagCompanyToAttach : isagUser.getIsagCompanyList()) {
                isagCompanyListIsagCompanyToAttach = em.getReference(isagCompanyListIsagCompanyToAttach.getClass(), isagCompanyListIsagCompanyToAttach.getId());
                attachedIsagCompanyList.add(isagCompanyListIsagCompanyToAttach);
            }
            isagUser.setIsagCompanyList(attachedIsagCompanyList);
            List<IsagCompany> attachedIsagCompanyList1 = new ArrayList<IsagCompany>();
            for (IsagCompany isagCompanyList1IsagCompanyToAttach : isagUser.getIsagCompanyList1()) {
                isagCompanyList1IsagCompanyToAttach = em.getReference(isagCompanyList1IsagCompanyToAttach.getClass(), isagCompanyList1IsagCompanyToAttach.getId());
                attachedIsagCompanyList1.add(isagCompanyList1IsagCompanyToAttach);
            }
            isagUser.setIsagCompanyList1(attachedIsagCompanyList1);
            List<IsagUser> attachedIsagUserList = new ArrayList<IsagUser>();
            for (IsagUser isagUserListIsagUserToAttach : isagUser.getIsagUserList()) {
                isagUserListIsagUserToAttach = em.getReference(isagUserListIsagUserToAttach.getClass(), isagUserListIsagUserToAttach.getId());
                attachedIsagUserList.add(isagUserListIsagUserToAttach);
            }
            isagUser.setIsagUserList(attachedIsagUserList);
            List<InvSupplier> attachedInvSupplierList = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListInvSupplierToAttach : isagUser.getInvSupplierList()) {
                invSupplierListInvSupplierToAttach = em.getReference(invSupplierListInvSupplierToAttach.getClass(), invSupplierListInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList.add(invSupplierListInvSupplierToAttach);
            }
            isagUser.setInvSupplierList(attachedInvSupplierList);
            List<InvSupplier> attachedInvSupplierList1 = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierList1InvSupplierToAttach : isagUser.getInvSupplierList1()) {
                invSupplierList1InvSupplierToAttach = em.getReference(invSupplierList1InvSupplierToAttach.getClass(), invSupplierList1InvSupplierToAttach.getSupplierId());
                attachedInvSupplierList1.add(invSupplierList1InvSupplierToAttach);
            }
            isagUser.setInvSupplierList1(attachedInvSupplierList1);
            List<GeneralSymbol> attachedGeneralSymbolList = new ArrayList<GeneralSymbol>();
            for (GeneralSymbol generalSymbolListGeneralSymbolToAttach : isagUser.getGeneralSymbolList()) {
                generalSymbolListGeneralSymbolToAttach = em.getReference(generalSymbolListGeneralSymbolToAttach.getClass(), generalSymbolListGeneralSymbolToAttach.getId());
                attachedGeneralSymbolList.add(generalSymbolListGeneralSymbolToAttach);
            }
            isagUser.setGeneralSymbolList(attachedGeneralSymbolList);
            List<GeneralSymbol> attachedGeneralSymbolList1 = new ArrayList<GeneralSymbol>();
            for (GeneralSymbol generalSymbolList1GeneralSymbolToAttach : isagUser.getGeneralSymbolList1()) {
                generalSymbolList1GeneralSymbolToAttach = em.getReference(generalSymbolList1GeneralSymbolToAttach.getClass(), generalSymbolList1GeneralSymbolToAttach.getId());
                attachedGeneralSymbolList1.add(generalSymbolList1GeneralSymbolToAttach);
            }
            isagUser.setGeneralSymbolList1(attachedGeneralSymbolList1);
            List<InvScCategory> attachedInvScCategoryList = new ArrayList<InvScCategory>();
            for (InvScCategory invScCategoryListInvScCategoryToAttach : isagUser.getInvScCategoryList()) {
                invScCategoryListInvScCategoryToAttach = em.getReference(invScCategoryListInvScCategoryToAttach.getClass(), invScCategoryListInvScCategoryToAttach.getCategoryID());
                attachedInvScCategoryList.add(invScCategoryListInvScCategoryToAttach);
            }
            isagUser.setInvScCategoryList(attachedInvScCategoryList);
            List<InvScCategory> attachedInvScCategoryList1 = new ArrayList<InvScCategory>();
            for (InvScCategory invScCategoryList1InvScCategoryToAttach : isagUser.getInvScCategoryList1()) {
                invScCategoryList1InvScCategoryToAttach = em.getReference(invScCategoryList1InvScCategoryToAttach.getClass(), invScCategoryList1InvScCategoryToAttach.getCategoryID());
                attachedInvScCategoryList1.add(invScCategoryList1InvScCategoryToAttach);
            }
            isagUser.setInvScCategoryList1(attachedInvScCategoryList1);
            List<Currency> attachedCurrencyList = new ArrayList<Currency>();
            for (Currency currencyListCurrencyToAttach : isagUser.getCurrencyList()) {
                currencyListCurrencyToAttach = em.getReference(currencyListCurrencyToAttach.getClass(), currencyListCurrencyToAttach.getId());
                attachedCurrencyList.add(currencyListCurrencyToAttach);
            }
            isagUser.setCurrencyList(attachedCurrencyList);
            List<Currency> attachedCurrencyList1 = new ArrayList<Currency>();
            for (Currency currencyList1CurrencyToAttach : isagUser.getCurrencyList1()) {
                currencyList1CurrencyToAttach = em.getReference(currencyList1CurrencyToAttach.getClass(), currencyList1CurrencyToAttach.getId());
                attachedCurrencyList1.add(currencyList1CurrencyToAttach);
            }
            isagUser.setCurrencyList1(attachedCurrencyList1);
            List<GlCostCenter> attachedGlCostCenterList = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterListGlCostCenterToAttach : isagUser.getGlCostCenterList()) {
                glCostCenterListGlCostCenterToAttach = em.getReference(glCostCenterListGlCostCenterToAttach.getClass(), glCostCenterListGlCostCenterToAttach.getId());
                attachedGlCostCenterList.add(glCostCenterListGlCostCenterToAttach);
            }
            isagUser.setGlCostCenterList(attachedGlCostCenterList);
            List<GlCostCenter> attachedGlCostCenterList1 = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterList1GlCostCenterToAttach : isagUser.getGlCostCenterList1()) {
                glCostCenterList1GlCostCenterToAttach = em.getReference(glCostCenterList1GlCostCenterToAttach.getClass(), glCostCenterList1GlCostCenterToAttach.getId());
                attachedGlCostCenterList1.add(glCostCenterList1GlCostCenterToAttach);
            }
            isagUser.setGlCostCenterList1(attachedGlCostCenterList1);
            List<Branch> attachedBranchList = new ArrayList<Branch>();
            for (Branch branchListBranchToAttach : isagUser.getBranchList()) {
                branchListBranchToAttach = em.getReference(branchListBranchToAttach.getClass(), branchListBranchToAttach.getId());
                attachedBranchList.add(branchListBranchToAttach);
            }
            isagUser.setBranchList(attachedBranchList);
            List<Branch> attachedBranchList1 = new ArrayList<Branch>();
            for (Branch branchList1BranchToAttach : isagUser.getBranchList1()) {
                branchList1BranchToAttach = em.getReference(branchList1BranchToAttach.getClass(), branchList1BranchToAttach.getId());
                attachedBranchList1.add(branchList1BranchToAttach);
            }
            isagUser.setBranchList1(attachedBranchList1);
            List<GlAccount> attachedGlAccountList = new ArrayList<GlAccount>();
            for (GlAccount glAccountListGlAccountToAttach : isagUser.getGlAccountList()) {
                glAccountListGlAccountToAttach = em.getReference(glAccountListGlAccountToAttach.getClass(), glAccountListGlAccountToAttach.getId());
                attachedGlAccountList.add(glAccountListGlAccountToAttach);
            }
            isagUser.setGlAccountList(attachedGlAccountList);
            List<GlAccount> attachedGlAccountList1 = new ArrayList<GlAccount>();
            for (GlAccount glAccountList1GlAccountToAttach : isagUser.getGlAccountList1()) {
                glAccountList1GlAccountToAttach = em.getReference(glAccountList1GlAccountToAttach.getClass(), glAccountList1GlAccountToAttach.getId());
                attachedGlAccountList1.add(glAccountList1GlAccountToAttach);
            }
            isagUser.setGlAccountList1(attachedGlAccountList1);
            List<IsagRole> attachedIsagRoleList = new ArrayList<IsagRole>();
            for (IsagRole isagRoleListIsagRoleToAttach : isagUser.getIsagRoleList()) {
                isagRoleListIsagRoleToAttach = em.getReference(isagRoleListIsagRoleToAttach.getClass(), isagRoleListIsagRoleToAttach.getId());
                attachedIsagRoleList.add(isagRoleListIsagRoleToAttach);
            }
            isagUser.setIsagRoleList(attachedIsagRoleList);
            List<IsagRole> attachedIsagRoleList1 = new ArrayList<IsagRole>();
            for (IsagRole isagRoleList1IsagRoleToAttach : isagUser.getIsagRoleList1()) {
                isagRoleList1IsagRoleToAttach = em.getReference(isagRoleList1IsagRoleToAttach.getClass(), isagRoleList1IsagRoleToAttach.getId());
                attachedIsagRoleList1.add(isagRoleList1IsagRoleToAttach);
            }
            isagUser.setIsagRoleList1(attachedIsagRoleList1);
            em.persist(isagUser);
            if (companyId != null) {
                IsagUser oldCreatedByOfCompanyId = companyId.getCreatedBy();
                if (oldCreatedByOfCompanyId != null) {
                    oldCreatedByOfCompanyId.setCompanyId(null);
                    oldCreatedByOfCompanyId = em.merge(oldCreatedByOfCompanyId);
                }
                companyId.setCreatedBy(isagUser);
                companyId = em.merge(companyId);
            }
            if (createdBy != null) {
                createdBy.getIsagUserList().add(isagUser);
                createdBy = em.merge(createdBy);
            }
            if (lang != null) {
                IsagUser oldCreatedByOfLang = lang.getCreatedBy();
                if (oldCreatedByOfLang != null) {
                    oldCreatedByOfLang.setLang(null);
                    oldCreatedByOfLang = em.merge(oldCreatedByOfLang);
                }
                lang.setCreatedBy(isagUser);
                lang = em.merge(lang);
            }
            if (roleId != null) {
                roleId.getIsagUserList().add(isagUser);
                roleId = em.merge(roleId);
            }
            for (Symbol symbolListSymbol : isagUser.getSymbolList()) {
                IsagUser oldCreatedByOfSymbolListSymbol = symbolListSymbol.getCreatedBy();
                symbolListSymbol.setCreatedBy(isagUser);
                symbolListSymbol = em.merge(symbolListSymbol);
                if (oldCreatedByOfSymbolListSymbol != null) {
                    oldCreatedByOfSymbolListSymbol.getSymbolList().remove(symbolListSymbol);
                    oldCreatedByOfSymbolListSymbol = em.merge(oldCreatedByOfSymbolListSymbol);
                }
            }
            for (Symbol symbolList1Symbol : isagUser.getSymbolList1()) {
                IsagUser oldModifiedByOfSymbolList1Symbol = symbolList1Symbol.getModifiedBy();
                symbolList1Symbol.setModifiedBy(isagUser);
                symbolList1Symbol = em.merge(symbolList1Symbol);
                if (oldModifiedByOfSymbolList1Symbol != null) {
                    oldModifiedByOfSymbolList1Symbol.getSymbolList1().remove(symbolList1Symbol);
                    oldModifiedByOfSymbolList1Symbol = em.merge(oldModifiedByOfSymbolList1Symbol);
                }
            }
            for (IsagCompany isagCompanyListIsagCompany : isagUser.getIsagCompanyList()) {
                IsagUser oldCreatedByOfIsagCompanyListIsagCompany = isagCompanyListIsagCompany.getCreatedBy();
                isagCompanyListIsagCompany.setCreatedBy(isagUser);
                isagCompanyListIsagCompany = em.merge(isagCompanyListIsagCompany);
                if (oldCreatedByOfIsagCompanyListIsagCompany != null) {
                    oldCreatedByOfIsagCompanyListIsagCompany.getIsagCompanyList().remove(isagCompanyListIsagCompany);
                    oldCreatedByOfIsagCompanyListIsagCompany = em.merge(oldCreatedByOfIsagCompanyListIsagCompany);
                }
            }
            for (IsagCompany isagCompanyList1IsagCompany : isagUser.getIsagCompanyList1()) {
                IsagUser oldModifiedByOfIsagCompanyList1IsagCompany = isagCompanyList1IsagCompany.getModifiedBy();
                isagCompanyList1IsagCompany.setModifiedBy(isagUser);
                isagCompanyList1IsagCompany = em.merge(isagCompanyList1IsagCompany);
                if (oldModifiedByOfIsagCompanyList1IsagCompany != null) {
                    oldModifiedByOfIsagCompanyList1IsagCompany.getIsagCompanyList1().remove(isagCompanyList1IsagCompany);
                    oldModifiedByOfIsagCompanyList1IsagCompany = em.merge(oldModifiedByOfIsagCompanyList1IsagCompany);
                }
            }
            for (IsagUser isagUserListIsagUser : isagUser.getIsagUserList()) {
                IsagUser oldCreatedByOfIsagUserListIsagUser = isagUserListIsagUser.getCreatedBy();
                isagUserListIsagUser.setCreatedBy(isagUser);
                isagUserListIsagUser = em.merge(isagUserListIsagUser);
                if (oldCreatedByOfIsagUserListIsagUser != null) {
                    oldCreatedByOfIsagUserListIsagUser.getIsagUserList().remove(isagUserListIsagUser);
                    oldCreatedByOfIsagUserListIsagUser = em.merge(oldCreatedByOfIsagUserListIsagUser);
                }
            }
            for (InvSupplier invSupplierListInvSupplier : isagUser.getInvSupplierList()) {
                IsagUser oldCreatedByOfInvSupplierListInvSupplier = invSupplierListInvSupplier.getCreatedBy();
                invSupplierListInvSupplier.setCreatedBy(isagUser);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
                if (oldCreatedByOfInvSupplierListInvSupplier != null) {
                    oldCreatedByOfInvSupplierListInvSupplier.getInvSupplierList().remove(invSupplierListInvSupplier);
                    oldCreatedByOfInvSupplierListInvSupplier = em.merge(oldCreatedByOfInvSupplierListInvSupplier);
                }
            }
            for (InvSupplier invSupplierList1InvSupplier : isagUser.getInvSupplierList1()) {
                IsagUser oldModifiedByOfInvSupplierList1InvSupplier = invSupplierList1InvSupplier.getModifiedBy();
                invSupplierList1InvSupplier.setModifiedBy(isagUser);
                invSupplierList1InvSupplier = em.merge(invSupplierList1InvSupplier);
                if (oldModifiedByOfInvSupplierList1InvSupplier != null) {
                    oldModifiedByOfInvSupplierList1InvSupplier.getInvSupplierList1().remove(invSupplierList1InvSupplier);
                    oldModifiedByOfInvSupplierList1InvSupplier = em.merge(oldModifiedByOfInvSupplierList1InvSupplier);
                }
            }
            for (GeneralSymbol generalSymbolListGeneralSymbol : isagUser.getGeneralSymbolList()) {
                IsagUser oldCreatedByOfGeneralSymbolListGeneralSymbol = generalSymbolListGeneralSymbol.getCreatedBy();
                generalSymbolListGeneralSymbol.setCreatedBy(isagUser);
                generalSymbolListGeneralSymbol = em.merge(generalSymbolListGeneralSymbol);
                if (oldCreatedByOfGeneralSymbolListGeneralSymbol != null) {
                    oldCreatedByOfGeneralSymbolListGeneralSymbol.getGeneralSymbolList().remove(generalSymbolListGeneralSymbol);
                    oldCreatedByOfGeneralSymbolListGeneralSymbol = em.merge(oldCreatedByOfGeneralSymbolListGeneralSymbol);
                }
            }
            for (GeneralSymbol generalSymbolList1GeneralSymbol : isagUser.getGeneralSymbolList1()) {
                IsagUser oldModifiedByOfGeneralSymbolList1GeneralSymbol = generalSymbolList1GeneralSymbol.getModifiedBy();
                generalSymbolList1GeneralSymbol.setModifiedBy(isagUser);
                generalSymbolList1GeneralSymbol = em.merge(generalSymbolList1GeneralSymbol);
                if (oldModifiedByOfGeneralSymbolList1GeneralSymbol != null) {
                    oldModifiedByOfGeneralSymbolList1GeneralSymbol.getGeneralSymbolList1().remove(generalSymbolList1GeneralSymbol);
                    oldModifiedByOfGeneralSymbolList1GeneralSymbol = em.merge(oldModifiedByOfGeneralSymbolList1GeneralSymbol);
                }
            }
            for (InvScCategory invScCategoryListInvScCategory : isagUser.getInvScCategoryList()) {
                IsagUser oldCreatedByOfInvScCategoryListInvScCategory = invScCategoryListInvScCategory.getCreatedBy();
                invScCategoryListInvScCategory.setCreatedBy(isagUser);
                invScCategoryListInvScCategory = em.merge(invScCategoryListInvScCategory);
                if (oldCreatedByOfInvScCategoryListInvScCategory != null) {
                    oldCreatedByOfInvScCategoryListInvScCategory.getInvScCategoryList().remove(invScCategoryListInvScCategory);
                    oldCreatedByOfInvScCategoryListInvScCategory = em.merge(oldCreatedByOfInvScCategoryListInvScCategory);
                }
            }
            for (InvScCategory invScCategoryList1InvScCategory : isagUser.getInvScCategoryList1()) {
                IsagUser oldModifiedByOfInvScCategoryList1InvScCategory = invScCategoryList1InvScCategory.getModifiedBy();
                invScCategoryList1InvScCategory.setModifiedBy(isagUser);
                invScCategoryList1InvScCategory = em.merge(invScCategoryList1InvScCategory);
                if (oldModifiedByOfInvScCategoryList1InvScCategory != null) {
                    oldModifiedByOfInvScCategoryList1InvScCategory.getInvScCategoryList1().remove(invScCategoryList1InvScCategory);
                    oldModifiedByOfInvScCategoryList1InvScCategory = em.merge(oldModifiedByOfInvScCategoryList1InvScCategory);
                }
            }
            for (Currency currencyListCurrency : isagUser.getCurrencyList()) {
                IsagUser oldCreatedByOfCurrencyListCurrency = currencyListCurrency.getCreatedBy();
                currencyListCurrency.setCreatedBy(isagUser);
                currencyListCurrency = em.merge(currencyListCurrency);
                if (oldCreatedByOfCurrencyListCurrency != null) {
                    oldCreatedByOfCurrencyListCurrency.getCurrencyList().remove(currencyListCurrency);
                    oldCreatedByOfCurrencyListCurrency = em.merge(oldCreatedByOfCurrencyListCurrency);
                }
            }
            for (Currency currencyList1Currency : isagUser.getCurrencyList1()) {
                IsagUser oldModifiedByOfCurrencyList1Currency = currencyList1Currency.getModifiedBy();
                currencyList1Currency.setModifiedBy(isagUser);
                currencyList1Currency = em.merge(currencyList1Currency);
                if (oldModifiedByOfCurrencyList1Currency != null) {
                    oldModifiedByOfCurrencyList1Currency.getCurrencyList1().remove(currencyList1Currency);
                    oldModifiedByOfCurrencyList1Currency = em.merge(oldModifiedByOfCurrencyList1Currency);
                }
            }
            for (GlCostCenter glCostCenterListGlCostCenter : isagUser.getGlCostCenterList()) {
                IsagUser oldCreatedByOfGlCostCenterListGlCostCenter = glCostCenterListGlCostCenter.getCreatedBy();
                glCostCenterListGlCostCenter.setCreatedBy(isagUser);
                glCostCenterListGlCostCenter = em.merge(glCostCenterListGlCostCenter);
                if (oldCreatedByOfGlCostCenterListGlCostCenter != null) {
                    oldCreatedByOfGlCostCenterListGlCostCenter.getGlCostCenterList().remove(glCostCenterListGlCostCenter);
                    oldCreatedByOfGlCostCenterListGlCostCenter = em.merge(oldCreatedByOfGlCostCenterListGlCostCenter);
                }
            }
            for (GlCostCenter glCostCenterList1GlCostCenter : isagUser.getGlCostCenterList1()) {
                IsagUser oldModifiedByOfGlCostCenterList1GlCostCenter = glCostCenterList1GlCostCenter.getModifiedBy();
                glCostCenterList1GlCostCenter.setModifiedBy(isagUser);
                glCostCenterList1GlCostCenter = em.merge(glCostCenterList1GlCostCenter);
                if (oldModifiedByOfGlCostCenterList1GlCostCenter != null) {
                    oldModifiedByOfGlCostCenterList1GlCostCenter.getGlCostCenterList1().remove(glCostCenterList1GlCostCenter);
                    oldModifiedByOfGlCostCenterList1GlCostCenter = em.merge(oldModifiedByOfGlCostCenterList1GlCostCenter);
                }
            }
            for (Branch branchListBranch : isagUser.getBranchList()) {
                IsagUser oldCreatedByOfBranchListBranch = branchListBranch.getCreatedBy();
                branchListBranch.setCreatedBy(isagUser);
                branchListBranch = em.merge(branchListBranch);
                if (oldCreatedByOfBranchListBranch != null) {
                    oldCreatedByOfBranchListBranch.getBranchList().remove(branchListBranch);
                    oldCreatedByOfBranchListBranch = em.merge(oldCreatedByOfBranchListBranch);
                }
            }
            for (Branch branchList1Branch : isagUser.getBranchList1()) {
                IsagUser oldModifiedByOfBranchList1Branch = branchList1Branch.getModifiedBy();
                branchList1Branch.setModifiedBy(isagUser);
                branchList1Branch = em.merge(branchList1Branch);
                if (oldModifiedByOfBranchList1Branch != null) {
                    oldModifiedByOfBranchList1Branch.getBranchList1().remove(branchList1Branch);
                    oldModifiedByOfBranchList1Branch = em.merge(oldModifiedByOfBranchList1Branch);
                }
            }
            for (GlAccount glAccountListGlAccount : isagUser.getGlAccountList()) {
                IsagUser oldCreatedByOfGlAccountListGlAccount = glAccountListGlAccount.getCreatedBy();
                glAccountListGlAccount.setCreatedBy(isagUser);
                glAccountListGlAccount = em.merge(glAccountListGlAccount);
                if (oldCreatedByOfGlAccountListGlAccount != null) {
                    oldCreatedByOfGlAccountListGlAccount.getGlAccountList().remove(glAccountListGlAccount);
                    oldCreatedByOfGlAccountListGlAccount = em.merge(oldCreatedByOfGlAccountListGlAccount);
                }
            }
            for (GlAccount glAccountList1GlAccount : isagUser.getGlAccountList1()) {
                IsagUser oldModifiedByOfGlAccountList1GlAccount = glAccountList1GlAccount.getModifiedBy();
                glAccountList1GlAccount.setModifiedBy(isagUser);
                glAccountList1GlAccount = em.merge(glAccountList1GlAccount);
                if (oldModifiedByOfGlAccountList1GlAccount != null) {
                    oldModifiedByOfGlAccountList1GlAccount.getGlAccountList1().remove(glAccountList1GlAccount);
                    oldModifiedByOfGlAccountList1GlAccount = em.merge(oldModifiedByOfGlAccountList1GlAccount);
                }
            }
            for (IsagRole isagRoleListIsagRole : isagUser.getIsagRoleList()) {
                IsagUser oldCreatedByOfIsagRoleListIsagRole = isagRoleListIsagRole.getCreatedBy();
                isagRoleListIsagRole.setCreatedBy(isagUser);
                isagRoleListIsagRole = em.merge(isagRoleListIsagRole);
                if (oldCreatedByOfIsagRoleListIsagRole != null) {
                    oldCreatedByOfIsagRoleListIsagRole.getIsagRoleList().remove(isagRoleListIsagRole);
                    oldCreatedByOfIsagRoleListIsagRole = em.merge(oldCreatedByOfIsagRoleListIsagRole);
                }
            }
            for (IsagRole isagRoleList1IsagRole : isagUser.getIsagRoleList1()) {
                IsagUser oldModifiedByOfIsagRoleList1IsagRole = isagRoleList1IsagRole.getModifiedBy();
                isagRoleList1IsagRole.setModifiedBy(isagUser);
                isagRoleList1IsagRole = em.merge(isagRoleList1IsagRole);
                if (oldModifiedByOfIsagRoleList1IsagRole != null) {
                    oldModifiedByOfIsagRoleList1IsagRole.getIsagRoleList1().remove(isagRoleList1IsagRole);
                    oldModifiedByOfIsagRoleList1IsagRole = em.merge(oldModifiedByOfIsagRoleList1IsagRole);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IsagUser isagUser) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IsagUser persistentIsagUser = em.find(IsagUser.class, isagUser.getId());
            IsagCompany companyIdOld = persistentIsagUser.getCompanyId();
            IsagCompany companyIdNew = isagUser.getCompanyId();
            IsagUser createdByOld = persistentIsagUser.getCreatedBy();
            IsagUser createdByNew = isagUser.getCreatedBy();
            Symbol langOld = persistentIsagUser.getLang();
            Symbol langNew = isagUser.getLang();
            IsagRole roleIdOld = persistentIsagUser.getRoleId();
            IsagRole roleIdNew = isagUser.getRoleId();
            List<Symbol> symbolListOld = persistentIsagUser.getSymbolList();
            List<Symbol> symbolListNew = isagUser.getSymbolList();
            List<Symbol> symbolList1Old = persistentIsagUser.getSymbolList1();
            List<Symbol> symbolList1New = isagUser.getSymbolList1();
            List<IsagCompany> isagCompanyListOld = persistentIsagUser.getIsagCompanyList();
            List<IsagCompany> isagCompanyListNew = isagUser.getIsagCompanyList();
            List<IsagCompany> isagCompanyList1Old = persistentIsagUser.getIsagCompanyList1();
            List<IsagCompany> isagCompanyList1New = isagUser.getIsagCompanyList1();
            List<IsagUser> isagUserListOld = persistentIsagUser.getIsagUserList();
            List<IsagUser> isagUserListNew = isagUser.getIsagUserList();
            List<InvSupplier> invSupplierListOld = persistentIsagUser.getInvSupplierList();
            List<InvSupplier> invSupplierListNew = isagUser.getInvSupplierList();
            List<InvSupplier> invSupplierList1Old = persistentIsagUser.getInvSupplierList1();
            List<InvSupplier> invSupplierList1New = isagUser.getInvSupplierList1();
            List<GeneralSymbol> generalSymbolListOld = persistentIsagUser.getGeneralSymbolList();
            List<GeneralSymbol> generalSymbolListNew = isagUser.getGeneralSymbolList();
            List<GeneralSymbol> generalSymbolList1Old = persistentIsagUser.getGeneralSymbolList1();
            List<GeneralSymbol> generalSymbolList1New = isagUser.getGeneralSymbolList1();
            List<InvScCategory> invScCategoryListOld = persistentIsagUser.getInvScCategoryList();
            List<InvScCategory> invScCategoryListNew = isagUser.getInvScCategoryList();
            List<InvScCategory> invScCategoryList1Old = persistentIsagUser.getInvScCategoryList1();
            List<InvScCategory> invScCategoryList1New = isagUser.getInvScCategoryList1();
            List<Currency> currencyListOld = persistentIsagUser.getCurrencyList();
            List<Currency> currencyListNew = isagUser.getCurrencyList();
            List<Currency> currencyList1Old = persistentIsagUser.getCurrencyList1();
            List<Currency> currencyList1New = isagUser.getCurrencyList1();
            List<GlCostCenter> glCostCenterListOld = persistentIsagUser.getGlCostCenterList();
            List<GlCostCenter> glCostCenterListNew = isagUser.getGlCostCenterList();
            List<GlCostCenter> glCostCenterList1Old = persistentIsagUser.getGlCostCenterList1();
            List<GlCostCenter> glCostCenterList1New = isagUser.getGlCostCenterList1();
            List<Branch> branchListOld = persistentIsagUser.getBranchList();
            List<Branch> branchListNew = isagUser.getBranchList();
            List<Branch> branchList1Old = persistentIsagUser.getBranchList1();
            List<Branch> branchList1New = isagUser.getBranchList1();
            List<GlAccount> glAccountListOld = persistentIsagUser.getGlAccountList();
            List<GlAccount> glAccountListNew = isagUser.getGlAccountList();
            List<GlAccount> glAccountList1Old = persistentIsagUser.getGlAccountList1();
            List<GlAccount> glAccountList1New = isagUser.getGlAccountList1();
            List<IsagRole> isagRoleListOld = persistentIsagUser.getIsagRoleList();
            List<IsagRole> isagRoleListNew = isagUser.getIsagRoleList();
            List<IsagRole> isagRoleList1Old = persistentIsagUser.getIsagRoleList1();
            List<IsagRole> isagRoleList1New = isagUser.getIsagRoleList1();
            List<String> illegalOrphanMessages = null;
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain IsagCompany " + companyIdOld + " since its createdBy field is not nullable.");
            }
            if (langOld != null && !langOld.equals(langNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Symbol " + langOld + " since its createdBy field is not nullable.");
            }
            for (Symbol symbolListOldSymbol : symbolListOld) {
                if (!symbolListNew.contains(symbolListOldSymbol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Symbol " + symbolListOldSymbol + " since its createdBy field is not nullable.");
                }
            }
            for (IsagCompany isagCompanyListOldIsagCompany : isagCompanyListOld) {
                if (!isagCompanyListNew.contains(isagCompanyListOldIsagCompany)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain IsagCompany " + isagCompanyListOldIsagCompany + " since its createdBy field is not nullable.");
                }
            }
            for (IsagUser isagUserListOldIsagUser : isagUserListOld) {
                if (!isagUserListNew.contains(isagUserListOldIsagUser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain IsagUser " + isagUserListOldIsagUser + " since its createdBy field is not nullable.");
                }
            }
            for (GeneralSymbol generalSymbolListOldGeneralSymbol : generalSymbolListOld) {
                if (!generalSymbolListNew.contains(generalSymbolListOldGeneralSymbol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GeneralSymbol " + generalSymbolListOldGeneralSymbol + " since its createdBy field is not nullable.");
                }
            }
            for (Currency currencyListOldCurrency : currencyListOld) {
                if (!currencyListNew.contains(currencyListOldCurrency)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Currency " + currencyListOldCurrency + " since its createdBy field is not nullable.");
                }
            }
            for (GlCostCenter glCostCenterListOldGlCostCenter : glCostCenterListOld) {
                if (!glCostCenterListNew.contains(glCostCenterListOldGlCostCenter)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GlCostCenter " + glCostCenterListOldGlCostCenter + " since its createdBy field is not nullable.");
                }
            }
            for (Branch branchListOldBranch : branchListOld) {
                if (!branchListNew.contains(branchListOldBranch)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Branch " + branchListOldBranch + " since its createdBy field is not nullable.");
                }
            }
            for (GlAccount glAccountListOldGlAccount : glAccountListOld) {
                if (!glAccountListNew.contains(glAccountListOldGlAccount)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GlAccount " + glAccountListOldGlAccount + " since its createdBy field is not nullable.");
                }
            }
            for (IsagRole isagRoleListOldIsagRole : isagRoleListOld) {
                if (!isagRoleListNew.contains(isagRoleListOldIsagRole)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain IsagRole " + isagRoleListOldIsagRole + " since its createdBy field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getId());
                isagUser.setCompanyId(companyIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getId());
                isagUser.setCreatedBy(createdByNew);
            }
            if (langNew != null) {
                langNew = em.getReference(langNew.getClass(), langNew.getId());
                isagUser.setLang(langNew);
            }
            if (roleIdNew != null) {
                roleIdNew = em.getReference(roleIdNew.getClass(), roleIdNew.getId());
                isagUser.setRoleId(roleIdNew);
            }
            List<Symbol> attachedSymbolListNew = new ArrayList<Symbol>();
            for (Symbol symbolListNewSymbolToAttach : symbolListNew) {
                symbolListNewSymbolToAttach = em.getReference(symbolListNewSymbolToAttach.getClass(), symbolListNewSymbolToAttach.getId());
                attachedSymbolListNew.add(symbolListNewSymbolToAttach);
            }
            symbolListNew = attachedSymbolListNew;
            isagUser.setSymbolList(symbolListNew);
            List<Symbol> attachedSymbolList1New = new ArrayList<Symbol>();
            for (Symbol symbolList1NewSymbolToAttach : symbolList1New) {
                symbolList1NewSymbolToAttach = em.getReference(symbolList1NewSymbolToAttach.getClass(), symbolList1NewSymbolToAttach.getId());
                attachedSymbolList1New.add(symbolList1NewSymbolToAttach);
            }
            symbolList1New = attachedSymbolList1New;
            isagUser.setSymbolList1(symbolList1New);
            List<IsagCompany> attachedIsagCompanyListNew = new ArrayList<IsagCompany>();
            for (IsagCompany isagCompanyListNewIsagCompanyToAttach : isagCompanyListNew) {
                isagCompanyListNewIsagCompanyToAttach = em.getReference(isagCompanyListNewIsagCompanyToAttach.getClass(), isagCompanyListNewIsagCompanyToAttach.getId());
                attachedIsagCompanyListNew.add(isagCompanyListNewIsagCompanyToAttach);
            }
            isagCompanyListNew = attachedIsagCompanyListNew;
            isagUser.setIsagCompanyList(isagCompanyListNew);
            List<IsagCompany> attachedIsagCompanyList1New = new ArrayList<IsagCompany>();
            for (IsagCompany isagCompanyList1NewIsagCompanyToAttach : isagCompanyList1New) {
                isagCompanyList1NewIsagCompanyToAttach = em.getReference(isagCompanyList1NewIsagCompanyToAttach.getClass(), isagCompanyList1NewIsagCompanyToAttach.getId());
                attachedIsagCompanyList1New.add(isagCompanyList1NewIsagCompanyToAttach);
            }
            isagCompanyList1New = attachedIsagCompanyList1New;
            isagUser.setIsagCompanyList1(isagCompanyList1New);
            List<IsagUser> attachedIsagUserListNew = new ArrayList<IsagUser>();
            for (IsagUser isagUserListNewIsagUserToAttach : isagUserListNew) {
                isagUserListNewIsagUserToAttach = em.getReference(isagUserListNewIsagUserToAttach.getClass(), isagUserListNewIsagUserToAttach.getId());
                attachedIsagUserListNew.add(isagUserListNewIsagUserToAttach);
            }
            isagUserListNew = attachedIsagUserListNew;
            isagUser.setIsagUserList(isagUserListNew);
            List<InvSupplier> attachedInvSupplierListNew = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierListNewInvSupplierToAttach : invSupplierListNew) {
                invSupplierListNewInvSupplierToAttach = em.getReference(invSupplierListNewInvSupplierToAttach.getClass(), invSupplierListNewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierListNew.add(invSupplierListNewInvSupplierToAttach);
            }
            invSupplierListNew = attachedInvSupplierListNew;
            isagUser.setInvSupplierList(invSupplierListNew);
            List<InvSupplier> attachedInvSupplierList1New = new ArrayList<InvSupplier>();
            for (InvSupplier invSupplierList1NewInvSupplierToAttach : invSupplierList1New) {
                invSupplierList1NewInvSupplierToAttach = em.getReference(invSupplierList1NewInvSupplierToAttach.getClass(), invSupplierList1NewInvSupplierToAttach.getSupplierId());
                attachedInvSupplierList1New.add(invSupplierList1NewInvSupplierToAttach);
            }
            invSupplierList1New = attachedInvSupplierList1New;
            isagUser.setInvSupplierList1(invSupplierList1New);
            List<GeneralSymbol> attachedGeneralSymbolListNew = new ArrayList<GeneralSymbol>();
            for (GeneralSymbol generalSymbolListNewGeneralSymbolToAttach : generalSymbolListNew) {
                generalSymbolListNewGeneralSymbolToAttach = em.getReference(generalSymbolListNewGeneralSymbolToAttach.getClass(), generalSymbolListNewGeneralSymbolToAttach.getId());
                attachedGeneralSymbolListNew.add(generalSymbolListNewGeneralSymbolToAttach);
            }
            generalSymbolListNew = attachedGeneralSymbolListNew;
            isagUser.setGeneralSymbolList(generalSymbolListNew);
            List<GeneralSymbol> attachedGeneralSymbolList1New = new ArrayList<GeneralSymbol>();
            for (GeneralSymbol generalSymbolList1NewGeneralSymbolToAttach : generalSymbolList1New) {
                generalSymbolList1NewGeneralSymbolToAttach = em.getReference(generalSymbolList1NewGeneralSymbolToAttach.getClass(), generalSymbolList1NewGeneralSymbolToAttach.getId());
                attachedGeneralSymbolList1New.add(generalSymbolList1NewGeneralSymbolToAttach);
            }
            generalSymbolList1New = attachedGeneralSymbolList1New;
            isagUser.setGeneralSymbolList1(generalSymbolList1New);
            List<InvScCategory> attachedInvScCategoryListNew = new ArrayList<InvScCategory>();
            for (InvScCategory invScCategoryListNewInvScCategoryToAttach : invScCategoryListNew) {
                invScCategoryListNewInvScCategoryToAttach = em.getReference(invScCategoryListNewInvScCategoryToAttach.getClass(), invScCategoryListNewInvScCategoryToAttach.getCategoryID());
                attachedInvScCategoryListNew.add(invScCategoryListNewInvScCategoryToAttach);
            }
            invScCategoryListNew = attachedInvScCategoryListNew;
            isagUser.setInvScCategoryList(invScCategoryListNew);
            List<InvScCategory> attachedInvScCategoryList1New = new ArrayList<InvScCategory>();
            for (InvScCategory invScCategoryList1NewInvScCategoryToAttach : invScCategoryList1New) {
                invScCategoryList1NewInvScCategoryToAttach = em.getReference(invScCategoryList1NewInvScCategoryToAttach.getClass(), invScCategoryList1NewInvScCategoryToAttach.getCategoryID());
                attachedInvScCategoryList1New.add(invScCategoryList1NewInvScCategoryToAttach);
            }
            invScCategoryList1New = attachedInvScCategoryList1New;
            isagUser.setInvScCategoryList1(invScCategoryList1New);
            List<Currency> attachedCurrencyListNew = new ArrayList<Currency>();
            for (Currency currencyListNewCurrencyToAttach : currencyListNew) {
                currencyListNewCurrencyToAttach = em.getReference(currencyListNewCurrencyToAttach.getClass(), currencyListNewCurrencyToAttach.getId());
                attachedCurrencyListNew.add(currencyListNewCurrencyToAttach);
            }
            currencyListNew = attachedCurrencyListNew;
            isagUser.setCurrencyList(currencyListNew);
            List<Currency> attachedCurrencyList1New = new ArrayList<Currency>();
            for (Currency currencyList1NewCurrencyToAttach : currencyList1New) {
                currencyList1NewCurrencyToAttach = em.getReference(currencyList1NewCurrencyToAttach.getClass(), currencyList1NewCurrencyToAttach.getId());
                attachedCurrencyList1New.add(currencyList1NewCurrencyToAttach);
            }
            currencyList1New = attachedCurrencyList1New;
            isagUser.setCurrencyList1(currencyList1New);
            List<GlCostCenter> attachedGlCostCenterListNew = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterListNewGlCostCenterToAttach : glCostCenterListNew) {
                glCostCenterListNewGlCostCenterToAttach = em.getReference(glCostCenterListNewGlCostCenterToAttach.getClass(), glCostCenterListNewGlCostCenterToAttach.getId());
                attachedGlCostCenterListNew.add(glCostCenterListNewGlCostCenterToAttach);
            }
            glCostCenterListNew = attachedGlCostCenterListNew;
            isagUser.setGlCostCenterList(glCostCenterListNew);
            List<GlCostCenter> attachedGlCostCenterList1New = new ArrayList<GlCostCenter>();
            for (GlCostCenter glCostCenterList1NewGlCostCenterToAttach : glCostCenterList1New) {
                glCostCenterList1NewGlCostCenterToAttach = em.getReference(glCostCenterList1NewGlCostCenterToAttach.getClass(), glCostCenterList1NewGlCostCenterToAttach.getId());
                attachedGlCostCenterList1New.add(glCostCenterList1NewGlCostCenterToAttach);
            }
            glCostCenterList1New = attachedGlCostCenterList1New;
            isagUser.setGlCostCenterList1(glCostCenterList1New);
            List<Branch> attachedBranchListNew = new ArrayList<Branch>();
            for (Branch branchListNewBranchToAttach : branchListNew) {
                branchListNewBranchToAttach = em.getReference(branchListNewBranchToAttach.getClass(), branchListNewBranchToAttach.getId());
                attachedBranchListNew.add(branchListNewBranchToAttach);
            }
            branchListNew = attachedBranchListNew;
            isagUser.setBranchList(branchListNew);
            List<Branch> attachedBranchList1New = new ArrayList<Branch>();
            for (Branch branchList1NewBranchToAttach : branchList1New) {
                branchList1NewBranchToAttach = em.getReference(branchList1NewBranchToAttach.getClass(), branchList1NewBranchToAttach.getId());
                attachedBranchList1New.add(branchList1NewBranchToAttach);
            }
            branchList1New = attachedBranchList1New;
            isagUser.setBranchList1(branchList1New);
            List<GlAccount> attachedGlAccountListNew = new ArrayList<GlAccount>();
            for (GlAccount glAccountListNewGlAccountToAttach : glAccountListNew) {
                glAccountListNewGlAccountToAttach = em.getReference(glAccountListNewGlAccountToAttach.getClass(), glAccountListNewGlAccountToAttach.getId());
                attachedGlAccountListNew.add(glAccountListNewGlAccountToAttach);
            }
            glAccountListNew = attachedGlAccountListNew;
            isagUser.setGlAccountList(glAccountListNew);
            List<GlAccount> attachedGlAccountList1New = new ArrayList<GlAccount>();
            for (GlAccount glAccountList1NewGlAccountToAttach : glAccountList1New) {
                glAccountList1NewGlAccountToAttach = em.getReference(glAccountList1NewGlAccountToAttach.getClass(), glAccountList1NewGlAccountToAttach.getId());
                attachedGlAccountList1New.add(glAccountList1NewGlAccountToAttach);
            }
            glAccountList1New = attachedGlAccountList1New;
            isagUser.setGlAccountList1(glAccountList1New);
            List<IsagRole> attachedIsagRoleListNew = new ArrayList<IsagRole>();
            for (IsagRole isagRoleListNewIsagRoleToAttach : isagRoleListNew) {
                isagRoleListNewIsagRoleToAttach = em.getReference(isagRoleListNewIsagRoleToAttach.getClass(), isagRoleListNewIsagRoleToAttach.getId());
                attachedIsagRoleListNew.add(isagRoleListNewIsagRoleToAttach);
            }
            isagRoleListNew = attachedIsagRoleListNew;
            isagUser.setIsagRoleList(isagRoleListNew);
            List<IsagRole> attachedIsagRoleList1New = new ArrayList<IsagRole>();
            for (IsagRole isagRoleList1NewIsagRoleToAttach : isagRoleList1New) {
                isagRoleList1NewIsagRoleToAttach = em.getReference(isagRoleList1NewIsagRoleToAttach.getClass(), isagRoleList1NewIsagRoleToAttach.getId());
                attachedIsagRoleList1New.add(isagRoleList1NewIsagRoleToAttach);
            }
            isagRoleList1New = attachedIsagRoleList1New;
            isagUser.setIsagRoleList1(isagRoleList1New);
            isagUser = em.merge(isagUser);
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                IsagUser oldCreatedByOfCompanyId = companyIdNew.getCreatedBy();
                if (oldCreatedByOfCompanyId != null) {
                    oldCreatedByOfCompanyId.setCompanyId(null);
                    oldCreatedByOfCompanyId = em.merge(oldCreatedByOfCompanyId);
                }
                companyIdNew.setCreatedBy(isagUser);
                companyIdNew = em.merge(companyIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getIsagUserList().remove(isagUser);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getIsagUserList().add(isagUser);
                createdByNew = em.merge(createdByNew);
            }
            if (langNew != null && !langNew.equals(langOld)) {
                IsagUser oldCreatedByOfLang = langNew.getCreatedBy();
                if (oldCreatedByOfLang != null) {
                    oldCreatedByOfLang.setLang(null);
                    oldCreatedByOfLang = em.merge(oldCreatedByOfLang);
                }
                langNew.setCreatedBy(isagUser);
                langNew = em.merge(langNew);
            }
            if (roleIdOld != null && !roleIdOld.equals(roleIdNew)) {
                roleIdOld.getIsagUserList().remove(isagUser);
                roleIdOld = em.merge(roleIdOld);
            }
            if (roleIdNew != null && !roleIdNew.equals(roleIdOld)) {
                roleIdNew.getIsagUserList().add(isagUser);
                roleIdNew = em.merge(roleIdNew);
            }
            for (Symbol symbolListNewSymbol : symbolListNew) {
                if (!symbolListOld.contains(symbolListNewSymbol)) {
                    IsagUser oldCreatedByOfSymbolListNewSymbol = symbolListNewSymbol.getCreatedBy();
                    symbolListNewSymbol.setCreatedBy(isagUser);
                    symbolListNewSymbol = em.merge(symbolListNewSymbol);
                    if (oldCreatedByOfSymbolListNewSymbol != null && !oldCreatedByOfSymbolListNewSymbol.equals(isagUser)) {
                        oldCreatedByOfSymbolListNewSymbol.getSymbolList().remove(symbolListNewSymbol);
                        oldCreatedByOfSymbolListNewSymbol = em.merge(oldCreatedByOfSymbolListNewSymbol);
                    }
                }
            }
            for (Symbol symbolList1OldSymbol : symbolList1Old) {
                if (!symbolList1New.contains(symbolList1OldSymbol)) {
                    symbolList1OldSymbol.setModifiedBy(null);
                    symbolList1OldSymbol = em.merge(symbolList1OldSymbol);
                }
            }
            for (Symbol symbolList1NewSymbol : symbolList1New) {
                if (!symbolList1Old.contains(symbolList1NewSymbol)) {
                    IsagUser oldModifiedByOfSymbolList1NewSymbol = symbolList1NewSymbol.getModifiedBy();
                    symbolList1NewSymbol.setModifiedBy(isagUser);
                    symbolList1NewSymbol = em.merge(symbolList1NewSymbol);
                    if (oldModifiedByOfSymbolList1NewSymbol != null && !oldModifiedByOfSymbolList1NewSymbol.equals(isagUser)) {
                        oldModifiedByOfSymbolList1NewSymbol.getSymbolList1().remove(symbolList1NewSymbol);
                        oldModifiedByOfSymbolList1NewSymbol = em.merge(oldModifiedByOfSymbolList1NewSymbol);
                    }
                }
            }
            for (IsagCompany isagCompanyListNewIsagCompany : isagCompanyListNew) {
                if (!isagCompanyListOld.contains(isagCompanyListNewIsagCompany)) {
                    IsagUser oldCreatedByOfIsagCompanyListNewIsagCompany = isagCompanyListNewIsagCompany.getCreatedBy();
                    isagCompanyListNewIsagCompany.setCreatedBy(isagUser);
                    isagCompanyListNewIsagCompany = em.merge(isagCompanyListNewIsagCompany);
                    if (oldCreatedByOfIsagCompanyListNewIsagCompany != null && !oldCreatedByOfIsagCompanyListNewIsagCompany.equals(isagUser)) {
                        oldCreatedByOfIsagCompanyListNewIsagCompany.getIsagCompanyList().remove(isagCompanyListNewIsagCompany);
                        oldCreatedByOfIsagCompanyListNewIsagCompany = em.merge(oldCreatedByOfIsagCompanyListNewIsagCompany);
                    }
                }
            }
            for (IsagCompany isagCompanyList1OldIsagCompany : isagCompanyList1Old) {
                if (!isagCompanyList1New.contains(isagCompanyList1OldIsagCompany)) {
                    isagCompanyList1OldIsagCompany.setModifiedBy(null);
                    isagCompanyList1OldIsagCompany = em.merge(isagCompanyList1OldIsagCompany);
                }
            }
            for (IsagCompany isagCompanyList1NewIsagCompany : isagCompanyList1New) {
                if (!isagCompanyList1Old.contains(isagCompanyList1NewIsagCompany)) {
                    IsagUser oldModifiedByOfIsagCompanyList1NewIsagCompany = isagCompanyList1NewIsagCompany.getModifiedBy();
                    isagCompanyList1NewIsagCompany.setModifiedBy(isagUser);
                    isagCompanyList1NewIsagCompany = em.merge(isagCompanyList1NewIsagCompany);
                    if (oldModifiedByOfIsagCompanyList1NewIsagCompany != null && !oldModifiedByOfIsagCompanyList1NewIsagCompany.equals(isagUser)) {
                        oldModifiedByOfIsagCompanyList1NewIsagCompany.getIsagCompanyList1().remove(isagCompanyList1NewIsagCompany);
                        oldModifiedByOfIsagCompanyList1NewIsagCompany = em.merge(oldModifiedByOfIsagCompanyList1NewIsagCompany);
                    }
                }
            }
            for (IsagUser isagUserListNewIsagUser : isagUserListNew) {
                if (!isagUserListOld.contains(isagUserListNewIsagUser)) {
                    IsagUser oldCreatedByOfIsagUserListNewIsagUser = isagUserListNewIsagUser.getCreatedBy();
                    isagUserListNewIsagUser.setCreatedBy(isagUser);
                    isagUserListNewIsagUser = em.merge(isagUserListNewIsagUser);
                    if (oldCreatedByOfIsagUserListNewIsagUser != null && !oldCreatedByOfIsagUserListNewIsagUser.equals(isagUser)) {
                        oldCreatedByOfIsagUserListNewIsagUser.getIsagUserList().remove(isagUserListNewIsagUser);
                        oldCreatedByOfIsagUserListNewIsagUser = em.merge(oldCreatedByOfIsagUserListNewIsagUser);
                    }
                }
            }
            for (InvSupplier invSupplierListOldInvSupplier : invSupplierListOld) {
                if (!invSupplierListNew.contains(invSupplierListOldInvSupplier)) {
                    invSupplierListOldInvSupplier.setCreatedBy(null);
                    invSupplierListOldInvSupplier = em.merge(invSupplierListOldInvSupplier);
                }
            }
            for (InvSupplier invSupplierListNewInvSupplier : invSupplierListNew) {
                if (!invSupplierListOld.contains(invSupplierListNewInvSupplier)) {
                    IsagUser oldCreatedByOfInvSupplierListNewInvSupplier = invSupplierListNewInvSupplier.getCreatedBy();
                    invSupplierListNewInvSupplier.setCreatedBy(isagUser);
                    invSupplierListNewInvSupplier = em.merge(invSupplierListNewInvSupplier);
                    if (oldCreatedByOfInvSupplierListNewInvSupplier != null && !oldCreatedByOfInvSupplierListNewInvSupplier.equals(isagUser)) {
                        oldCreatedByOfInvSupplierListNewInvSupplier.getInvSupplierList().remove(invSupplierListNewInvSupplier);
                        oldCreatedByOfInvSupplierListNewInvSupplier = em.merge(oldCreatedByOfInvSupplierListNewInvSupplier);
                    }
                }
            }
            for (InvSupplier invSupplierList1OldInvSupplier : invSupplierList1Old) {
                if (!invSupplierList1New.contains(invSupplierList1OldInvSupplier)) {
                    invSupplierList1OldInvSupplier.setModifiedBy(null);
                    invSupplierList1OldInvSupplier = em.merge(invSupplierList1OldInvSupplier);
                }
            }
            for (InvSupplier invSupplierList1NewInvSupplier : invSupplierList1New) {
                if (!invSupplierList1Old.contains(invSupplierList1NewInvSupplier)) {
                    IsagUser oldModifiedByOfInvSupplierList1NewInvSupplier = invSupplierList1NewInvSupplier.getModifiedBy();
                    invSupplierList1NewInvSupplier.setModifiedBy(isagUser);
                    invSupplierList1NewInvSupplier = em.merge(invSupplierList1NewInvSupplier);
                    if (oldModifiedByOfInvSupplierList1NewInvSupplier != null && !oldModifiedByOfInvSupplierList1NewInvSupplier.equals(isagUser)) {
                        oldModifiedByOfInvSupplierList1NewInvSupplier.getInvSupplierList1().remove(invSupplierList1NewInvSupplier);
                        oldModifiedByOfInvSupplierList1NewInvSupplier = em.merge(oldModifiedByOfInvSupplierList1NewInvSupplier);
                    }
                }
            }
            for (GeneralSymbol generalSymbolListNewGeneralSymbol : generalSymbolListNew) {
                if (!generalSymbolListOld.contains(generalSymbolListNewGeneralSymbol)) {
                    IsagUser oldCreatedByOfGeneralSymbolListNewGeneralSymbol = generalSymbolListNewGeneralSymbol.getCreatedBy();
                    generalSymbolListNewGeneralSymbol.setCreatedBy(isagUser);
                    generalSymbolListNewGeneralSymbol = em.merge(generalSymbolListNewGeneralSymbol);
                    if (oldCreatedByOfGeneralSymbolListNewGeneralSymbol != null && !oldCreatedByOfGeneralSymbolListNewGeneralSymbol.equals(isagUser)) {
                        oldCreatedByOfGeneralSymbolListNewGeneralSymbol.getGeneralSymbolList().remove(generalSymbolListNewGeneralSymbol);
                        oldCreatedByOfGeneralSymbolListNewGeneralSymbol = em.merge(oldCreatedByOfGeneralSymbolListNewGeneralSymbol);
                    }
                }
            }
            for (GeneralSymbol generalSymbolList1OldGeneralSymbol : generalSymbolList1Old) {
                if (!generalSymbolList1New.contains(generalSymbolList1OldGeneralSymbol)) {
                    generalSymbolList1OldGeneralSymbol.setModifiedBy(null);
                    generalSymbolList1OldGeneralSymbol = em.merge(generalSymbolList1OldGeneralSymbol);
                }
            }
            for (GeneralSymbol generalSymbolList1NewGeneralSymbol : generalSymbolList1New) {
                if (!generalSymbolList1Old.contains(generalSymbolList1NewGeneralSymbol)) {
                    IsagUser oldModifiedByOfGeneralSymbolList1NewGeneralSymbol = generalSymbolList1NewGeneralSymbol.getModifiedBy();
                    generalSymbolList1NewGeneralSymbol.setModifiedBy(isagUser);
                    generalSymbolList1NewGeneralSymbol = em.merge(generalSymbolList1NewGeneralSymbol);
                    if (oldModifiedByOfGeneralSymbolList1NewGeneralSymbol != null && !oldModifiedByOfGeneralSymbolList1NewGeneralSymbol.equals(isagUser)) {
                        oldModifiedByOfGeneralSymbolList1NewGeneralSymbol.getGeneralSymbolList1().remove(generalSymbolList1NewGeneralSymbol);
                        oldModifiedByOfGeneralSymbolList1NewGeneralSymbol = em.merge(oldModifiedByOfGeneralSymbolList1NewGeneralSymbol);
                    }
                }
            }
            for (InvScCategory invScCategoryListOldInvScCategory : invScCategoryListOld) {
                if (!invScCategoryListNew.contains(invScCategoryListOldInvScCategory)) {
                    invScCategoryListOldInvScCategory.setCreatedBy(null);
                    invScCategoryListOldInvScCategory = em.merge(invScCategoryListOldInvScCategory);
                }
            }
            for (InvScCategory invScCategoryListNewInvScCategory : invScCategoryListNew) {
                if (!invScCategoryListOld.contains(invScCategoryListNewInvScCategory)) {
                    IsagUser oldCreatedByOfInvScCategoryListNewInvScCategory = invScCategoryListNewInvScCategory.getCreatedBy();
                    invScCategoryListNewInvScCategory.setCreatedBy(isagUser);
                    invScCategoryListNewInvScCategory = em.merge(invScCategoryListNewInvScCategory);
                    if (oldCreatedByOfInvScCategoryListNewInvScCategory != null && !oldCreatedByOfInvScCategoryListNewInvScCategory.equals(isagUser)) {
                        oldCreatedByOfInvScCategoryListNewInvScCategory.getInvScCategoryList().remove(invScCategoryListNewInvScCategory);
                        oldCreatedByOfInvScCategoryListNewInvScCategory = em.merge(oldCreatedByOfInvScCategoryListNewInvScCategory);
                    }
                }
            }
            for (InvScCategory invScCategoryList1OldInvScCategory : invScCategoryList1Old) {
                if (!invScCategoryList1New.contains(invScCategoryList1OldInvScCategory)) {
                    invScCategoryList1OldInvScCategory.setModifiedBy(null);
                    invScCategoryList1OldInvScCategory = em.merge(invScCategoryList1OldInvScCategory);
                }
            }
            for (InvScCategory invScCategoryList1NewInvScCategory : invScCategoryList1New) {
                if (!invScCategoryList1Old.contains(invScCategoryList1NewInvScCategory)) {
                    IsagUser oldModifiedByOfInvScCategoryList1NewInvScCategory = invScCategoryList1NewInvScCategory.getModifiedBy();
                    invScCategoryList1NewInvScCategory.setModifiedBy(isagUser);
                    invScCategoryList1NewInvScCategory = em.merge(invScCategoryList1NewInvScCategory);
                    if (oldModifiedByOfInvScCategoryList1NewInvScCategory != null && !oldModifiedByOfInvScCategoryList1NewInvScCategory.equals(isagUser)) {
                        oldModifiedByOfInvScCategoryList1NewInvScCategory.getInvScCategoryList1().remove(invScCategoryList1NewInvScCategory);
                        oldModifiedByOfInvScCategoryList1NewInvScCategory = em.merge(oldModifiedByOfInvScCategoryList1NewInvScCategory);
                    }
                }
            }
            for (Currency currencyListNewCurrency : currencyListNew) {
                if (!currencyListOld.contains(currencyListNewCurrency)) {
                    IsagUser oldCreatedByOfCurrencyListNewCurrency = currencyListNewCurrency.getCreatedBy();
                    currencyListNewCurrency.setCreatedBy(isagUser);
                    currencyListNewCurrency = em.merge(currencyListNewCurrency);
                    if (oldCreatedByOfCurrencyListNewCurrency != null && !oldCreatedByOfCurrencyListNewCurrency.equals(isagUser)) {
                        oldCreatedByOfCurrencyListNewCurrency.getCurrencyList().remove(currencyListNewCurrency);
                        oldCreatedByOfCurrencyListNewCurrency = em.merge(oldCreatedByOfCurrencyListNewCurrency);
                    }
                }
            }
            for (Currency currencyList1OldCurrency : currencyList1Old) {
                if (!currencyList1New.contains(currencyList1OldCurrency)) {
                    currencyList1OldCurrency.setModifiedBy(null);
                    currencyList1OldCurrency = em.merge(currencyList1OldCurrency);
                }
            }
            for (Currency currencyList1NewCurrency : currencyList1New) {
                if (!currencyList1Old.contains(currencyList1NewCurrency)) {
                    IsagUser oldModifiedByOfCurrencyList1NewCurrency = currencyList1NewCurrency.getModifiedBy();
                    currencyList1NewCurrency.setModifiedBy(isagUser);
                    currencyList1NewCurrency = em.merge(currencyList1NewCurrency);
                    if (oldModifiedByOfCurrencyList1NewCurrency != null && !oldModifiedByOfCurrencyList1NewCurrency.equals(isagUser)) {
                        oldModifiedByOfCurrencyList1NewCurrency.getCurrencyList1().remove(currencyList1NewCurrency);
                        oldModifiedByOfCurrencyList1NewCurrency = em.merge(oldModifiedByOfCurrencyList1NewCurrency);
                    }
                }
            }
            for (GlCostCenter glCostCenterListNewGlCostCenter : glCostCenterListNew) {
                if (!glCostCenterListOld.contains(glCostCenterListNewGlCostCenter)) {
                    IsagUser oldCreatedByOfGlCostCenterListNewGlCostCenter = glCostCenterListNewGlCostCenter.getCreatedBy();
                    glCostCenterListNewGlCostCenter.setCreatedBy(isagUser);
                    glCostCenterListNewGlCostCenter = em.merge(glCostCenterListNewGlCostCenter);
                    if (oldCreatedByOfGlCostCenterListNewGlCostCenter != null && !oldCreatedByOfGlCostCenterListNewGlCostCenter.equals(isagUser)) {
                        oldCreatedByOfGlCostCenterListNewGlCostCenter.getGlCostCenterList().remove(glCostCenterListNewGlCostCenter);
                        oldCreatedByOfGlCostCenterListNewGlCostCenter = em.merge(oldCreatedByOfGlCostCenterListNewGlCostCenter);
                    }
                }
            }
            for (GlCostCenter glCostCenterList1OldGlCostCenter : glCostCenterList1Old) {
                if (!glCostCenterList1New.contains(glCostCenterList1OldGlCostCenter)) {
                    glCostCenterList1OldGlCostCenter.setModifiedBy(null);
                    glCostCenterList1OldGlCostCenter = em.merge(glCostCenterList1OldGlCostCenter);
                }
            }
            for (GlCostCenter glCostCenterList1NewGlCostCenter : glCostCenterList1New) {
                if (!glCostCenterList1Old.contains(glCostCenterList1NewGlCostCenter)) {
                    IsagUser oldModifiedByOfGlCostCenterList1NewGlCostCenter = glCostCenterList1NewGlCostCenter.getModifiedBy();
                    glCostCenterList1NewGlCostCenter.setModifiedBy(isagUser);
                    glCostCenterList1NewGlCostCenter = em.merge(glCostCenterList1NewGlCostCenter);
                    if (oldModifiedByOfGlCostCenterList1NewGlCostCenter != null && !oldModifiedByOfGlCostCenterList1NewGlCostCenter.equals(isagUser)) {
                        oldModifiedByOfGlCostCenterList1NewGlCostCenter.getGlCostCenterList1().remove(glCostCenterList1NewGlCostCenter);
                        oldModifiedByOfGlCostCenterList1NewGlCostCenter = em.merge(oldModifiedByOfGlCostCenterList1NewGlCostCenter);
                    }
                }
            }
            for (Branch branchListNewBranch : branchListNew) {
                if (!branchListOld.contains(branchListNewBranch)) {
                    IsagUser oldCreatedByOfBranchListNewBranch = branchListNewBranch.getCreatedBy();
                    branchListNewBranch.setCreatedBy(isagUser);
                    branchListNewBranch = em.merge(branchListNewBranch);
                    if (oldCreatedByOfBranchListNewBranch != null && !oldCreatedByOfBranchListNewBranch.equals(isagUser)) {
                        oldCreatedByOfBranchListNewBranch.getBranchList().remove(branchListNewBranch);
                        oldCreatedByOfBranchListNewBranch = em.merge(oldCreatedByOfBranchListNewBranch);
                    }
                }
            }
            for (Branch branchList1OldBranch : branchList1Old) {
                if (!branchList1New.contains(branchList1OldBranch)) {
                    branchList1OldBranch.setModifiedBy(null);
                    branchList1OldBranch = em.merge(branchList1OldBranch);
                }
            }
            for (Branch branchList1NewBranch : branchList1New) {
                if (!branchList1Old.contains(branchList1NewBranch)) {
                    IsagUser oldModifiedByOfBranchList1NewBranch = branchList1NewBranch.getModifiedBy();
                    branchList1NewBranch.setModifiedBy(isagUser);
                    branchList1NewBranch = em.merge(branchList1NewBranch);
                    if (oldModifiedByOfBranchList1NewBranch != null && !oldModifiedByOfBranchList1NewBranch.equals(isagUser)) {
                        oldModifiedByOfBranchList1NewBranch.getBranchList1().remove(branchList1NewBranch);
                        oldModifiedByOfBranchList1NewBranch = em.merge(oldModifiedByOfBranchList1NewBranch);
                    }
                }
            }
            for (GlAccount glAccountListNewGlAccount : glAccountListNew) {
                if (!glAccountListOld.contains(glAccountListNewGlAccount)) {
                    IsagUser oldCreatedByOfGlAccountListNewGlAccount = glAccountListNewGlAccount.getCreatedBy();
                    glAccountListNewGlAccount.setCreatedBy(isagUser);
                    glAccountListNewGlAccount = em.merge(glAccountListNewGlAccount);
                    if (oldCreatedByOfGlAccountListNewGlAccount != null && !oldCreatedByOfGlAccountListNewGlAccount.equals(isagUser)) {
                        oldCreatedByOfGlAccountListNewGlAccount.getGlAccountList().remove(glAccountListNewGlAccount);
                        oldCreatedByOfGlAccountListNewGlAccount = em.merge(oldCreatedByOfGlAccountListNewGlAccount);
                    }
                }
            }
            for (GlAccount glAccountList1OldGlAccount : glAccountList1Old) {
                if (!glAccountList1New.contains(glAccountList1OldGlAccount)) {
                    glAccountList1OldGlAccount.setModifiedBy(null);
                    glAccountList1OldGlAccount = em.merge(glAccountList1OldGlAccount);
                }
            }
            for (GlAccount glAccountList1NewGlAccount : glAccountList1New) {
                if (!glAccountList1Old.contains(glAccountList1NewGlAccount)) {
                    IsagUser oldModifiedByOfGlAccountList1NewGlAccount = glAccountList1NewGlAccount.getModifiedBy();
                    glAccountList1NewGlAccount.setModifiedBy(isagUser);
                    glAccountList1NewGlAccount = em.merge(glAccountList1NewGlAccount);
                    if (oldModifiedByOfGlAccountList1NewGlAccount != null && !oldModifiedByOfGlAccountList1NewGlAccount.equals(isagUser)) {
                        oldModifiedByOfGlAccountList1NewGlAccount.getGlAccountList1().remove(glAccountList1NewGlAccount);
                        oldModifiedByOfGlAccountList1NewGlAccount = em.merge(oldModifiedByOfGlAccountList1NewGlAccount);
                    }
                }
            }
            for (IsagRole isagRoleListNewIsagRole : isagRoleListNew) {
                if (!isagRoleListOld.contains(isagRoleListNewIsagRole)) {
                    IsagUser oldCreatedByOfIsagRoleListNewIsagRole = isagRoleListNewIsagRole.getCreatedBy();
                    isagRoleListNewIsagRole.setCreatedBy(isagUser);
                    isagRoleListNewIsagRole = em.merge(isagRoleListNewIsagRole);
                    if (oldCreatedByOfIsagRoleListNewIsagRole != null && !oldCreatedByOfIsagRoleListNewIsagRole.equals(isagUser)) {
                        oldCreatedByOfIsagRoleListNewIsagRole.getIsagRoleList().remove(isagRoleListNewIsagRole);
                        oldCreatedByOfIsagRoleListNewIsagRole = em.merge(oldCreatedByOfIsagRoleListNewIsagRole);
                    }
                }
            }
            for (IsagRole isagRoleList1OldIsagRole : isagRoleList1Old) {
                if (!isagRoleList1New.contains(isagRoleList1OldIsagRole)) {
                    isagRoleList1OldIsagRole.setModifiedBy(null);
                    isagRoleList1OldIsagRole = em.merge(isagRoleList1OldIsagRole);
                }
            }
            for (IsagRole isagRoleList1NewIsagRole : isagRoleList1New) {
                if (!isagRoleList1Old.contains(isagRoleList1NewIsagRole)) {
                    IsagUser oldModifiedByOfIsagRoleList1NewIsagRole = isagRoleList1NewIsagRole.getModifiedBy();
                    isagRoleList1NewIsagRole.setModifiedBy(isagUser);
                    isagRoleList1NewIsagRole = em.merge(isagRoleList1NewIsagRole);
                    if (oldModifiedByOfIsagRoleList1NewIsagRole != null && !oldModifiedByOfIsagRoleList1NewIsagRole.equals(isagUser)) {
                        oldModifiedByOfIsagRoleList1NewIsagRole.getIsagRoleList1().remove(isagRoleList1NewIsagRole);
                        oldModifiedByOfIsagRoleList1NewIsagRole = em.merge(oldModifiedByOfIsagRoleList1NewIsagRole);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = isagUser.getId();
                if (findIsagUser(id) == null) {
                    throw new NonexistentEntityException("The isagUser with id " + id + " no longer exists.");
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
            IsagUser isagUser;
            try {
                isagUser = em.getReference(IsagUser.class, id);
                isagUser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The isagUser with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            IsagCompany companyIdOrphanCheck = isagUser.getCompanyId();
            if (companyIdOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the IsagCompany " + companyIdOrphanCheck + " in its companyId field has a non-nullable createdBy field.");
            }
            Symbol langOrphanCheck = isagUser.getLang();
            if (langOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the Symbol " + langOrphanCheck + " in its lang field has a non-nullable createdBy field.");
            }
            List<Symbol> symbolListOrphanCheck = isagUser.getSymbolList();
            for (Symbol symbolListOrphanCheckSymbol : symbolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the Symbol " + symbolListOrphanCheckSymbol + " in its symbolList field has a non-nullable createdBy field.");
            }
            List<IsagCompany> isagCompanyListOrphanCheck = isagUser.getIsagCompanyList();
            for (IsagCompany isagCompanyListOrphanCheckIsagCompany : isagCompanyListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the IsagCompany " + isagCompanyListOrphanCheckIsagCompany + " in its isagCompanyList field has a non-nullable createdBy field.");
            }
            List<IsagUser> isagUserListOrphanCheck = isagUser.getIsagUserList();
            for (IsagUser isagUserListOrphanCheckIsagUser : isagUserListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the IsagUser " + isagUserListOrphanCheckIsagUser + " in its isagUserList field has a non-nullable createdBy field.");
            }
            List<GeneralSymbol> generalSymbolListOrphanCheck = isagUser.getGeneralSymbolList();
            for (GeneralSymbol generalSymbolListOrphanCheckGeneralSymbol : generalSymbolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the GeneralSymbol " + generalSymbolListOrphanCheckGeneralSymbol + " in its generalSymbolList field has a non-nullable createdBy field.");
            }
            List<Currency> currencyListOrphanCheck = isagUser.getCurrencyList();
            for (Currency currencyListOrphanCheckCurrency : currencyListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the Currency " + currencyListOrphanCheckCurrency + " in its currencyList field has a non-nullable createdBy field.");
            }
            List<GlCostCenter> glCostCenterListOrphanCheck = isagUser.getGlCostCenterList();
            for (GlCostCenter glCostCenterListOrphanCheckGlCostCenter : glCostCenterListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the GlCostCenter " + glCostCenterListOrphanCheckGlCostCenter + " in its glCostCenterList field has a non-nullable createdBy field.");
            }
            List<Branch> branchListOrphanCheck = isagUser.getBranchList();
            for (Branch branchListOrphanCheckBranch : branchListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the Branch " + branchListOrphanCheckBranch + " in its branchList field has a non-nullable createdBy field.");
            }
            List<GlAccount> glAccountListOrphanCheck = isagUser.getGlAccountList();
            for (GlAccount glAccountListOrphanCheckGlAccount : glAccountListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the GlAccount " + glAccountListOrphanCheckGlAccount + " in its glAccountList field has a non-nullable createdBy field.");
            }
            List<IsagRole> isagRoleListOrphanCheck = isagUser.getIsagRoleList();
            for (IsagRole isagRoleListOrphanCheckIsagRole : isagRoleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IsagUser (" + isagUser + ") cannot be destroyed since the IsagRole " + isagRoleListOrphanCheckIsagRole + " in its isagRoleList field has a non-nullable createdBy field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            IsagUser createdBy = isagUser.getCreatedBy();
            if (createdBy != null) {
                createdBy.getIsagUserList().remove(isagUser);
                createdBy = em.merge(createdBy);
            }
            IsagRole roleId = isagUser.getRoleId();
            if (roleId != null) {
                roleId.getIsagUserList().remove(isagUser);
                roleId = em.merge(roleId);
            }
            List<Symbol> symbolList1 = isagUser.getSymbolList1();
            for (Symbol symbolList1Symbol : symbolList1) {
                symbolList1Symbol.setModifiedBy(null);
                symbolList1Symbol = em.merge(symbolList1Symbol);
            }
            List<IsagCompany> isagCompanyList1 = isagUser.getIsagCompanyList1();
            for (IsagCompany isagCompanyList1IsagCompany : isagCompanyList1) {
                isagCompanyList1IsagCompany.setModifiedBy(null);
                isagCompanyList1IsagCompany = em.merge(isagCompanyList1IsagCompany);
            }
            List<InvSupplier> invSupplierList = isagUser.getInvSupplierList();
            for (InvSupplier invSupplierListInvSupplier : invSupplierList) {
                invSupplierListInvSupplier.setCreatedBy(null);
                invSupplierListInvSupplier = em.merge(invSupplierListInvSupplier);
            }
            List<InvSupplier> invSupplierList1 = isagUser.getInvSupplierList1();
            for (InvSupplier invSupplierList1InvSupplier : invSupplierList1) {
                invSupplierList1InvSupplier.setModifiedBy(null);
                invSupplierList1InvSupplier = em.merge(invSupplierList1InvSupplier);
            }
            List<GeneralSymbol> generalSymbolList1 = isagUser.getGeneralSymbolList1();
            for (GeneralSymbol generalSymbolList1GeneralSymbol : generalSymbolList1) {
                generalSymbolList1GeneralSymbol.setModifiedBy(null);
                generalSymbolList1GeneralSymbol = em.merge(generalSymbolList1GeneralSymbol);
            }
            List<InvScCategory> invScCategoryList = isagUser.getInvScCategoryList();
            for (InvScCategory invScCategoryListInvScCategory : invScCategoryList) {
                invScCategoryListInvScCategory.setCreatedBy(null);
                invScCategoryListInvScCategory = em.merge(invScCategoryListInvScCategory);
            }
            List<InvScCategory> invScCategoryList1 = isagUser.getInvScCategoryList1();
            for (InvScCategory invScCategoryList1InvScCategory : invScCategoryList1) {
                invScCategoryList1InvScCategory.setModifiedBy(null);
                invScCategoryList1InvScCategory = em.merge(invScCategoryList1InvScCategory);
            }
            List<Currency> currencyList1 = isagUser.getCurrencyList1();
            for (Currency currencyList1Currency : currencyList1) {
                currencyList1Currency.setModifiedBy(null);
                currencyList1Currency = em.merge(currencyList1Currency);
            }
            List<GlCostCenter> glCostCenterList1 = isagUser.getGlCostCenterList1();
            for (GlCostCenter glCostCenterList1GlCostCenter : glCostCenterList1) {
                glCostCenterList1GlCostCenter.setModifiedBy(null);
                glCostCenterList1GlCostCenter = em.merge(glCostCenterList1GlCostCenter);
            }
            List<Branch> branchList1 = isagUser.getBranchList1();
            for (Branch branchList1Branch : branchList1) {
                branchList1Branch.setModifiedBy(null);
                branchList1Branch = em.merge(branchList1Branch);
            }
            List<GlAccount> glAccountList1 = isagUser.getGlAccountList1();
            for (GlAccount glAccountList1GlAccount : glAccountList1) {
                glAccountList1GlAccount.setModifiedBy(null);
                glAccountList1GlAccount = em.merge(glAccountList1GlAccount);
            }
            List<IsagRole> isagRoleList1 = isagUser.getIsagRoleList1();
            for (IsagRole isagRoleList1IsagRole : isagRoleList1) {
                isagRoleList1IsagRole.setModifiedBy(null);
                isagRoleList1IsagRole = em.merge(isagRoleList1IsagRole);
            }
            em.remove(isagUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IsagUser> findIsagUserEntities() {
        return findIsagUserEntities(true, -1, -1);
    }

    public List<IsagUser> findIsagUserEntities(int maxResults, int firstResult) {
        return findIsagUserEntities(false, maxResults, firstResult);
    }

    private List<IsagUser> findIsagUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IsagUser.class));
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

    public IsagUser findIsagUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IsagUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getIsagUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IsagUser> rt = cq.from(IsagUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
