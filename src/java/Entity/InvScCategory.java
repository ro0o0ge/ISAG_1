/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abdo
 */
@Entity
@Table(name = "inv_sc_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvScCategory.findAll", query = "SELECT i FROM InvScCategory i")
    , @NamedQuery(name = "InvScCategory.findByCategoryID", query = "SELECT i FROM InvScCategory i WHERE i.categoryID = :categoryID")
    , @NamedQuery(name = "InvScCategory.findByCatno", query = "SELECT i FROM InvScCategory i WHERE i.catno = :catno")
    , @NamedQuery(name = "InvScCategory.findByParnt", query = "SELECT i FROM InvScCategory i WHERE i.parnt = :parnt")
    , @NamedQuery(name = "InvScCategory.findByNamea", query = "SELECT i FROM InvScCategory i WHERE i.namea = :namea")
    , @NamedQuery(name = "InvScCategory.findByCatlvl", query = "SELECT i FROM InvScCategory i WHERE i.catlvl = :catlvl")
    , @NamedQuery(name = "InvScCategory.findByCreatedDate", query = "SELECT i FROM InvScCategory i WHERE i.createdDate = :createdDate")
    , @NamedQuery(name = "InvScCategory.findByModifiedDate", query = "SELECT i FROM InvScCategory i WHERE i.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "InvScCategory.findByCostno", query = "SELECT i FROM InvScCategory i WHERE i.costno = :costno")})
public class InvScCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Category_ID")
    private Integer categoryID;
    @Basic(optional = false)
    @Column(name = "CATNO")
    private String catno;
    @Column(name = "PARNT")
    private String parnt;
    @Column(name = "NAMEA")
    private String namea;
    @Column(name = "CATLVL")
    private Integer catlvl;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "COSTNO")
    private Integer costno;
    @JoinColumn(name = "BRNNO", referencedColumnName = "id")
    @ManyToOne
    private Branch brnno;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IsagCompany companyId;
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "id")
    @ManyToOne
    private IsagUser createdBy;
    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "id")
    @ManyToOne
    private IsagUser modifiedBy;

    public InvScCategory() {
    }

    public InvScCategory(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public InvScCategory(Integer categoryID, String catno, Date createdDate) {
        this.categoryID = categoryID;
        this.catno = catno;
        this.createdDate = createdDate;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCatno() {
        return catno;
    }

    public void setCatno(String catno) {
        this.catno = catno;
    }

    public String getParnt() {
        return parnt;
    }

    public void setParnt(String parnt) {
        this.parnt = parnt;
    }

    public String getNamea() {
        return namea;
    }

    public void setNamea(String namea) {
        this.namea = namea;
    }

    public Integer getCatlvl() {
        return catlvl;
    }

    public void setCatlvl(Integer catlvl) {
        this.catlvl = catlvl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getCostno() {
        return costno;
    }

    public void setCostno(Integer costno) {
        this.costno = costno;
    }

    public Branch getBrnno() {
        return brnno;
    }

    public void setBrnno(Branch brnno) {
        this.brnno = brnno;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvScCategory)) {
            return false;
        }
        InvScCategory other = (InvScCategory) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.InvScCategory[ categoryID=" + categoryID + " ]";
    }
    
}
