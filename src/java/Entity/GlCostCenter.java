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
@Table(name = "gl_cost_center")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlCostCenter.findAll", query = "SELECT g FROM GlCostCenter g")
    , @NamedQuery(name = "GlCostCenter.findById", query = "SELECT g FROM GlCostCenter g WHERE g.id = :id")
    , @NamedQuery(name = "GlCostCenter.findByCode", query = "SELECT g FROM GlCostCenter g WHERE g.code = :code")
    , @NamedQuery(name = "GlCostCenter.findByName", query = "SELECT g FROM GlCostCenter g WHERE g.name = :name")
    , @NamedQuery(name = "GlCostCenter.findByShortCode", query = "SELECT g FROM GlCostCenter g WHERE g.shortCode = :shortCode")
    , @NamedQuery(name = "GlCostCenter.findByLevel", query = "SELECT g FROM GlCostCenter g WHERE g.level = :level")
    , @NamedQuery(name = "GlCostCenter.findByActive", query = "SELECT g FROM GlCostCenter g WHERE g.active = :active")
    , @NamedQuery(name = "GlCostCenter.findByCreationDate", query = "SELECT g FROM GlCostCenter g WHERE g.creationDate = :creationDate")
    , @NamedQuery(name = "GlCostCenter.findByModificationDate", query = "SELECT g FROM GlCostCenter g WHERE g.modificationDate = :modificationDate")})
public class GlCostCenter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private Integer code;
    @Column(name = "name")
    private String name;
    @Column(name = "short_code")
    private Integer shortCode;
    @Column(name = "level")
    private Integer level;
    @Column(name = "active")
    private Short active;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @OneToMany(mappedBy = "pricelvl")
    private List<InvSupplier> invSupplierList;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IsagCompany companyId;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IsagUser createdBy;
    @JoinColumn(name = "modified_by", referencedColumnName = "id")
    @ManyToOne
    private IsagUser modifiedBy;
    @OneToMany(mappedBy = "parent")
    private List<GlCostCenter> glCostCenterList;
    @JoinColumn(name = "parent", referencedColumnName = "id")
    @ManyToOne
    private GlCostCenter parent;
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    @ManyToOne
    private Branch branchId;

    public GlCostCenter() {
    }

    public GlCostCenter(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShortCode() {
        return shortCode;
    }

    public void setShortCode(Integer shortCode) {
        this.shortCode = shortCode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
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

    @XmlTransient
    public List<InvSupplier> getInvSupplierList() {
        return invSupplierList;
    }

    public void setInvSupplierList(List<InvSupplier> invSupplierList) {
        this.invSupplierList = invSupplierList;
    }

    public IsagCompany getCompanyId() {
        return companyId;
    }

    public void setCompanyId(IsagCompany companyId) {
        this.companyId = companyId;
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
    public List<GlCostCenter> getGlCostCenterList() {
        return glCostCenterList;
    }

    public void setGlCostCenterList(List<GlCostCenter> glCostCenterList) {
        this.glCostCenterList = glCostCenterList;
    }

    public GlCostCenter getParent() {
        return parent;
    }

    public void setParent(GlCostCenter parent) {
        this.parent = parent;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
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
        if (!(object instanceof GlCostCenter)) {
            return false;
        }
        GlCostCenter other = (GlCostCenter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.GlCostCenter[ id=" + id + " ]";
    }
    
}
