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
import javax.persistence.Lob;
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
@Table(name = "isag_company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IsagCompany.findAll", query = "SELECT i FROM IsagCompany i")
    , @NamedQuery(name = "IsagCompany.findById", query = "SELECT i FROM IsagCompany i WHERE i.id = :id")
    , @NamedQuery(name = "IsagCompany.findByName", query = "SELECT i FROM IsagCompany i WHERE i.name = :name")
    , @NamedQuery(name = "IsagCompany.findByCreationDate", query = "SELECT i FROM IsagCompany i WHERE i.creationDate = :creationDate")
    , @NamedQuery(name = "IsagCompany.findByModificationDate", query = "SELECT i FROM IsagCompany i WHERE i.modificationDate = :modificationDate")
    , @NamedQuery(name = "IsagCompany.findByCompanyId", query = "SELECT i FROM IsagCompany i WHERE i.companyId = :companyId")
    , @NamedQuery(name = "IsagCompany.findByCode", query = "SELECT i FROM IsagCompany i WHERE i.code = :code")
    , @NamedQuery(name = "IsagCompany.findByCoreBusiness", query = "SELECT i FROM IsagCompany i WHERE i.coreBusiness = :coreBusiness")
    , @NamedQuery(name = "IsagCompany.findByResponsible", query = "SELECT i FROM IsagCompany i WHERE i.responsible = :responsible")
    , @NamedQuery(name = "IsagCompany.findByPhone", query = "SELECT i FROM IsagCompany i WHERE i.phone = :phone")
    , @NamedQuery(name = "IsagCompany.findByFax", query = "SELECT i FROM IsagCompany i WHERE i.fax = :fax")
    , @NamedQuery(name = "IsagCompany.findByAddress", query = "SELECT i FROM IsagCompany i WHERE i.address = :address")
    , @NamedQuery(name = "IsagCompany.findByRowCountList", query = "SELECT i FROM IsagCompany i WHERE i.rowCountList = :rowCountList")
    , @NamedQuery(name = "IsagCompany.findByRowCountMasterDetails", query = "SELECT i FROM IsagCompany i WHERE i.rowCountMasterDetails = :rowCountMasterDetails")})
public class IsagCompany implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @Column(name = "company_id")
    private Integer companyId;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Column(name = "core_business")
    private String coreBusiness;
    @Column(name = "responsible")
    private String responsible;
    @Column(name = "phone")
    private String phone;
    @Column(name = "fax")
    private String fax;
    @Column(name = "address")
    private String address;
    @Lob
    @Column(name = "logo")
    private byte[] logo;
    @Column(name = "row_count_list")
    private Integer rowCountList;
    @Column(name = "row_count_master_details")
    private Integer rowCountMasterDetails;
    @OneToMany(mappedBy = "companyId")
    private List<Symbol> symbolList;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IsagUser createdBy;
    @JoinColumn(name = "modified_by", referencedColumnName = "id")
    @ManyToOne
    private IsagUser modifiedBy;
    @OneToMany(mappedBy = "companyId")
    private List<IsagUser> isagUserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<InvSupplier> invSupplierList;
    @OneToMany(mappedBy = "companyId")
    private List<GeneralSymbol> generalSymbolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<InvScMan> invScManList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<InvScCategory> invScCategoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<GlCostCenter> glCostCenterList;
    @OneToMany(mappedBy = "companyId")
    private List<Branch> branchList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<GlAccount> glAccountList;
    @OneToMany(mappedBy = "companyId")
    private List<IsagRole> isagRoleList;

    public IsagCompany() {
    }

    public IsagCompany(Integer id) {
        this.id = id;
    }

    public IsagCompany(Integer id, String name, Date creationDate, String code) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCoreBusiness() {
        return coreBusiness;
    }

    public void setCoreBusiness(String coreBusiness) {
        this.coreBusiness = coreBusiness;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Integer getRowCountList() {
        return rowCountList;
    }

    public void setRowCountList(Integer rowCountList) {
        this.rowCountList = rowCountList;
    }

    public Integer getRowCountMasterDetails() {
        return rowCountMasterDetails;
    }

    public void setRowCountMasterDetails(Integer rowCountMasterDetails) {
        this.rowCountMasterDetails = rowCountMasterDetails;
    }

    @XmlTransient
    public List<Symbol> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(List<Symbol> symbolList) {
        this.symbolList = symbolList;
    }

    public IsagUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(IsagUser createdBy) {
        this.createdBy = createdBy;
    }

    public IsagUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(IsagUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @XmlTransient
    public List<IsagUser> getIsagUserList() {
        return isagUserList;
    }

    public void setIsagUserList(List<IsagUser> isagUserList) {
        this.isagUserList = isagUserList;
    }

    @XmlTransient
    public List<InvSupplier> getInvSupplierList() {
        return invSupplierList;
    }

    public void setInvSupplierList(List<InvSupplier> invSupplierList) {
        this.invSupplierList = invSupplierList;
    }

    @XmlTransient
    public List<GeneralSymbol> getGeneralSymbolList() {
        return generalSymbolList;
    }

    public void setGeneralSymbolList(List<GeneralSymbol> generalSymbolList) {
        this.generalSymbolList = generalSymbolList;
    }

    @XmlTransient
    public List<InvScMan> getInvScManList() {
        return invScManList;
    }

    public void setInvScManList(List<InvScMan> invScManList) {
        this.invScManList = invScManList;
    }

    @XmlTransient
    public List<InvScCategory> getInvScCategoryList() {
        return invScCategoryList;
    }

    public void setInvScCategoryList(List<InvScCategory> invScCategoryList) {
        this.invScCategoryList = invScCategoryList;
    }

    @XmlTransient
    public List<GlCostCenter> getGlCostCenterList() {
        return glCostCenterList;
    }

    public void setGlCostCenterList(List<GlCostCenter> glCostCenterList) {
        this.glCostCenterList = glCostCenterList;
    }

    @XmlTransient
    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    @XmlTransient
    public List<GlAccount> getGlAccountList() {
        return glAccountList;
    }

    public void setGlAccountList(List<GlAccount> glAccountList) {
        this.glAccountList = glAccountList;
    }

    @XmlTransient
    public List<IsagRole> getIsagRoleList() {
        return isagRoleList;
    }

    public void setIsagRoleList(List<IsagRole> isagRoleList) {
        this.isagRoleList = isagRoleList;
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
        if (!(object instanceof IsagCompany)) {
            return false;
        }
        IsagCompany other = (IsagCompany) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.IsagCompany[ id=" + id + " ]";
    }
    
}
