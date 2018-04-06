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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author akef
 */
@Entity
@Table(name = "inv_catgory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvCatgory.findAll", query = "SELECT i FROM InvCatgory i")
    , @NamedQuery(name = "InvCatgory.findByCatgoryId", query = "SELECT i FROM InvCatgory i WHERE i.catgoryId = :catgoryId")
    , @NamedQuery(name = "InvCatgory.findByCompanyId", query = "SELECT i FROM InvCatgory i WHERE i.companyId = :companyId")
    , @NamedQuery(name = "InvCatgory.findByBrnanchNum", query = "SELECT i FROM InvCatgory i WHERE i.brnanchNum = :brnanchNum")
    , @NamedQuery(name = "InvCatgory.findByParentId", query = "SELECT i FROM InvCatgory i WHERE i.parentId = :parentId")
    , @NamedQuery(name = "InvCatgory.findByName", query = "SELECT i FROM InvCatgory i WHERE i.name = :name")
    , @NamedQuery(name = "InvCatgory.findByCatgoryLevel", query = "SELECT i FROM InvCatgory i WHERE i.catgoryLevel = :catgoryLevel")
    , @NamedQuery(name = "InvCatgory.findByCreatedBy", query = "SELECT i FROM InvCatgory i WHERE i.createdBy = :createdBy")
    , @NamedQuery(name = "InvCatgory.findByCreatedDate", query = "SELECT i FROM InvCatgory i WHERE i.createdDate = :createdDate")
    , @NamedQuery(name = "InvCatgory.findByModifiedBy", query = "SELECT i FROM InvCatgory i WHERE i.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "InvCatgory.findByModifiedDate", query = "SELECT i FROM InvCatgory i WHERE i.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "InvCatgory.findByCostNum", query = "SELECT i FROM InvCatgory i WHERE i.costNum = :costNum")
    , @NamedQuery(name = "InvCatgory.findByCostNum2", query = "SELECT i FROM InvCatgory i WHERE i.costNum2 = :costNum2")})
public class InvCatgory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CATGORY_ID")
    private Integer catgoryId;
    @Basic(optional = false)
    @Column(name = "COMPANY_ID")
    private int companyId;
    @Basic(optional = false)
    @Column(name = "BRNANCH_NUM")
    private int brnanchNum;
    @Column(name = "PARENT_ID")
    private String parentId;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "CATGORY_LEVEL")
    private Integer catgoryLevel;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    @Column(name = "COST_NUM")
    private String costNum;
    @Column(name = "COST_NUM_2")
    private String costNum2;

    public InvCatgory() {
    }

    public InvCatgory(Integer catgoryId) {
        this.catgoryId = catgoryId;
    }

    public InvCatgory(Integer catgoryId, int companyId, int brnanchNum, String name) {
        this.catgoryId = catgoryId;
        this.companyId = companyId;
        this.brnanchNum = brnanchNum;
        this.name = name;
    }

    public Integer getCatgoryId() {
        return catgoryId;
    }

    public void setCatgoryId(Integer catgoryId) {
        this.catgoryId = catgoryId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getBrnanchNum() {
        return brnanchNum;
    }

    public void setBrnanchNum(int brnanchNum) {
        this.brnanchNum = brnanchNum;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCatgoryLevel() {
        return catgoryLevel;
    }

    public void setCatgoryLevel(Integer catgoryLevel) {
        this.catgoryLevel = catgoryLevel;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCostNum() {
        return costNum;
    }

    public void setCostNum(String costNum) {
        this.costNum = costNum;
    }

    public String getCostNum2() {
        return costNum2;
    }

    public void setCostNum2(String costNum2) {
        this.costNum2 = costNum2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catgoryId != null ? catgoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvCatgory)) {
            return false;
        }
        InvCatgory other = (InvCatgory) object;
        if ((this.catgoryId == null && other.catgoryId != null) || (this.catgoryId != null && !this.catgoryId.equals(other.catgoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.InvCatgory[ catgoryId=" + catgoryId + " ]";
    }
    
}
