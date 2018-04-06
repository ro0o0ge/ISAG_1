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
@Table(name = "general_symbol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneralSymbol.findAll", query = "SELECT g FROM GeneralSymbol g")
    , @NamedQuery(name = "GeneralSymbol.findById", query = "SELECT g FROM GeneralSymbol g WHERE g.id = :id")
    , @NamedQuery(name = "GeneralSymbol.findByName", query = "SELECT g FROM GeneralSymbol g WHERE g.name = :name")
    , @NamedQuery(name = "GeneralSymbol.findBySerial", query = "SELECT g FROM GeneralSymbol g WHERE g.serial = :serial")
    , @NamedQuery(name = "GeneralSymbol.findByCreationDate", query = "SELECT g FROM GeneralSymbol g WHERE g.creationDate = :creationDate")
    , @NamedQuery(name = "GeneralSymbol.findByModificationDate", query = "SELECT g FROM GeneralSymbol g WHERE g.modificationDate = :modificationDate")})
public class GeneralSymbol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "serial")
    private Integer serial;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "generalSymbolId")
    private List<Symbol> symbolList;
    @OneToMany(mappedBy = "typno")
    private List<InvSupplier> invSupplierList;
    @OneToMany(mappedBy = "country")
    private List<InvSupplier> invSupplierList1;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
    private IsagCompany companyId;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IsagUser createdBy;
    @JoinColumn(name = "modified_by", referencedColumnName = "id")
    @ManyToOne
    private IsagUser modifiedBy;

    public GeneralSymbol() {
    }

    public GeneralSymbol(Integer id) {
        this.id = id;
    }

    public GeneralSymbol(Integer id, String name, Date creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
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

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
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
    public List<Symbol> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(List<Symbol> symbolList) {
        this.symbolList = symbolList;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneralSymbol)) {
            return false;
        }
        GeneralSymbol other = (GeneralSymbol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.GeneralSymbol[ id=" + id + " ]";
    }
    
}
