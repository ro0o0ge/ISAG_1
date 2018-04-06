/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Abdo
 */
@Entity
@Table(name = "isag_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IsagUser.findAll", query = "SELECT i FROM IsagUser i")
    , @NamedQuery(name = "IsagUser.findById", query = "SELECT i FROM IsagUser i WHERE i.id = :id")
    , @NamedQuery(name = "IsagUser.findByUserCode", query = "SELECT i FROM IsagUser i WHERE i.userCode = :userCode")
    , @NamedQuery(name = "IsagUser.findByPassword", query = "SELECT i FROM IsagUser i WHERE i.password = :password")
    , @NamedQuery(name = "IsagUser.findByName", query = "SELECT i FROM IsagUser i WHERE i.name = :name")
    , @NamedQuery(name = "IsagUser.findByCreationDate", query = "SELECT i FROM IsagUser i WHERE i.creationDate = :creationDate")
    , @NamedQuery(name = "IsagUser.findByModifiedBy", query = "SELECT i FROM IsagUser i WHERE i.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "IsagUser.findByModificationDate", query = "SELECT i FROM IsagUser i WHERE i.modificationDate = :modificationDate")})
public class IsagUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_code")
    private String userCode;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "modified_by")
    private Integer modifiedBy;
    @Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<Symbol> symbolList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<Symbol> symbolList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<IsagCompany> isagCompanyList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<IsagCompany> isagCompanyList1;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
    private IsagCompany companyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<IsagUser> isagUserList;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IsagUser createdBy;
    @JoinColumn(name = "lang", referencedColumnName = "id")
    @ManyToOne
    private Symbol lang;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private IsagRole roleId;
    @OneToMany(mappedBy = "createdBy")
    private List<InvSupplier> invSupplierList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<InvSupplier> invSupplierList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<GeneralSymbol> generalSymbolList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<GeneralSymbol> generalSymbolList1;
    @OneToMany(mappedBy = "createdBy")
    private List<InvScCategory> invScCategoryList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<InvScCategory> invScCategoryList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<Currency> currencyList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<Currency> currencyList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<GlCostCenter> glCostCenterList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<GlCostCenter> glCostCenterList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<Branch> branchList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<Branch> branchList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<GlAccount> glAccountList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<GlAccount> glAccountList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<IsagRole> isagRoleList;
    @OneToMany(mappedBy = "modifiedBy")
    private List<IsagRole> isagRoleList1;

    public IsagUser() {
    }

    public IsagUser(Integer id) {
        this.id = id;
    }

    public IsagUser(Integer id, String userCode, String password, Date creationDate) {
        this.id = id;
        this.userCode = userCode;
        this.password = password;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @XmlTransient
    public List<Symbol> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(List<Symbol> symbolList) {
        this.symbolList = symbolList;
    }

    @XmlTransient
    public List<Symbol> getSymbolList1() {
        return symbolList1;
    }

    public void setSymbolList1(List<Symbol> symbolList1) {
        this.symbolList1 = symbolList1;
    }

    @XmlTransient
    public List<IsagCompany> getIsagCompanyList() {
        return isagCompanyList;
    }

    public void setIsagCompanyList(List<IsagCompany> isagCompanyList) {
        this.isagCompanyList = isagCompanyList;
    }

    @XmlTransient
    public List<IsagCompany> getIsagCompanyList1() {
        return isagCompanyList1;
    }

    public void setIsagCompanyList1(List<IsagCompany> isagCompanyList1) {
        this.isagCompanyList1 = isagCompanyList1;
    }

    public IsagCompany getCompanyId() {
        return companyId;
    }

    public void setCompanyId(IsagCompany companyId) {
        this.companyId = companyId;
    }

    @XmlTransient
    public List<IsagUser> getIsagUserList() {
        return isagUserList;
    }

    public void setIsagUserList(List<IsagUser> isagUserList) {
        this.isagUserList = isagUserList;
    }

    public IsagUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(IsagUser createdBy) {
        this.createdBy = createdBy;
    }

    public Symbol getLang() {
        return lang;
    }

    public void setLang(Symbol lang) {
        this.lang = lang;
    }

    public IsagRole getRoleId() {
        return roleId;
    }

    public void setRoleId(IsagRole roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public List<InvSupplier> getInvSupplierList() {
        return invSupplierList;
    }

    public void setInvSupplierList(List<InvSupplier> invSupplierList) {
        this.invSupplierList = invSupplierList;
    }

    @XmlTransient
    public List<InvSupplier> getInvSupplierList1() {
        return invSupplierList1;
    }

    public void setInvSupplierList1(List<InvSupplier> invSupplierList1) {
        this.invSupplierList1 = invSupplierList1;
    }

    @XmlTransient
    public List<GeneralSymbol> getGeneralSymbolList() {
        return generalSymbolList;
    }

    public void setGeneralSymbolList(List<GeneralSymbol> generalSymbolList) {
        this.generalSymbolList = generalSymbolList;
    }

    @XmlTransient
    public List<GeneralSymbol> getGeneralSymbolList1() {
        return generalSymbolList1;
    }

    public void setGeneralSymbolList1(List<GeneralSymbol> generalSymbolList1) {
        this.generalSymbolList1 = generalSymbolList1;
    }

    @XmlTransient
    public List<InvScCategory> getInvScCategoryList() {
        return invScCategoryList;
    }

    public void setInvScCategoryList(List<InvScCategory> invScCategoryList) {
        this.invScCategoryList = invScCategoryList;
    }

    @XmlTransient
    public List<InvScCategory> getInvScCategoryList1() {
        return invScCategoryList1;
    }

    public void setInvScCategoryList1(List<InvScCategory> invScCategoryList1) {
        this.invScCategoryList1 = invScCategoryList1;
    }

    @XmlTransient
    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    @XmlTransient
    public List<Currency> getCurrencyList1() {
        return currencyList1;
    }

    public void setCurrencyList1(List<Currency> currencyList1) {
        this.currencyList1 = currencyList1;
    }

    @XmlTransient
    public List<GlCostCenter> getGlCostCenterList() {
        return glCostCenterList;
    }

    public void setGlCostCenterList(List<GlCostCenter> glCostCenterList) {
        this.glCostCenterList = glCostCenterList;
    }

    @XmlTransient
    public List<GlCostCenter> getGlCostCenterList1() {
        return glCostCenterList1;
    }

    public void setGlCostCenterList1(List<GlCostCenter> glCostCenterList1) {
        this.glCostCenterList1 = glCostCenterList1;
    }

    @XmlTransient
    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    @XmlTransient
    public List<Branch> getBranchList1() {
        return branchList1;
    }

    public void setBranchList1(List<Branch> branchList1) {
        this.branchList1 = branchList1;
    }

    @XmlTransient
    public List<GlAccount> getGlAccountList() {
        return glAccountList;
    }

    public void setGlAccountList(List<GlAccount> glAccountList) {
        this.glAccountList = glAccountList;
    }

    @XmlTransient
    public List<GlAccount> getGlAccountList1() {
        return glAccountList1;
    }

    public void setGlAccountList1(List<GlAccount> glAccountList1) {
        this.glAccountList1 = glAccountList1;
    }

    @XmlTransient
    public List<IsagRole> getIsagRoleList() {
        return isagRoleList;
    }

    public void setIsagRoleList(List<IsagRole> isagRoleList) {
        this.isagRoleList = isagRoleList;
    }

    @XmlTransient
    public List<IsagRole> getIsagRoleList1() {
        return isagRoleList1;
    }

    public void setIsagRoleList1(List<IsagRole> isagRoleList1) {
        this.isagRoleList1 = isagRoleList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IsagUser)) {
            return false;
        }
        IsagUser other = (IsagUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.IsagUser[ id=" + id + " ]";
    }
    
}
