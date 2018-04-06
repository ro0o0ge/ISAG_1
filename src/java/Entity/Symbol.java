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
@Table(name = "symbol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Symbol.findAll", query = "SELECT s FROM Symbol s")
    , @NamedQuery(name = "Symbol.findById", query = "SELECT s FROM Symbol s WHERE s.id = :id")
    , @NamedQuery(name = "Symbol.findByName", query = "SELECT s FROM Symbol s WHERE s.name = :name")
    , @NamedQuery(name = "Symbol.findBySerial", query = "SELECT s FROM Symbol s WHERE s.serial = :serial")
    , @NamedQuery(name = "Symbol.findByCreationDate", query = "SELECT s FROM Symbol s WHERE s.creationDate = :creationDate")
    , @NamedQuery(name = "Symbol.findByModificationDate", query = "SELECT s FROM Symbol s WHERE s.modificationDate = :modificationDate")})
public class Symbol implements Serializable {

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
    @Column(name = "serial")
    private int serial;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
    private IsagCompany companyId;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IsagUser createdBy;
    @JoinColumn(name = "general_symbol_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GeneralSymbol generalSymbolId;
    @JoinColumn(name = "modified_by", referencedColumnName = "id")
    @ManyToOne
    private IsagUser modifiedBy;
    @OneToMany(mappedBy = "lang")
    private List<IsagUser> isagUserList;

    public Symbol() {
    }

    public Symbol(Integer id) {
        this.id = id;
    }

    public Symbol(Integer id, String name, int serial, Date creationDate) {
        this.id = id;
        this.name = name;
        this.serial = serial;
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

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
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

    public GeneralSymbol getGeneralSymbolId() {
        return generalSymbolId;
    }

    public void setGeneralSymbolId(GeneralSymbol generalSymbolId) {
        this.generalSymbolId = generalSymbolId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Symbol)) {
            return false;
        }
        Symbol other = (Symbol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Symbol[ id=" + id + " ]";
    }
    
}
