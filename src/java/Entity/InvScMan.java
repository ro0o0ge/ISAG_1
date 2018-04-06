/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Abdo
 */
@Entity
@Table(name = "inv_sc_man")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvScMan.findAll", query = "SELECT i FROM InvScMan i")
    , @NamedQuery(name = "InvScMan.findByScManId", query = "SELECT i FROM InvScMan i WHERE i.scManId = :scManId")
    , @NamedQuery(name = "InvScMan.findBySerialNO", query = "SELECT i FROM InvScMan i WHERE i.serialNO = :serialNO")
    , @NamedQuery(name = "InvScMan.findByTyp", query = "SELECT i FROM InvScMan i WHERE i.typ = :typ")
    , @NamedQuery(name = "InvScMan.findByNamea", query = "SELECT i FROM InvScMan i WHERE i.namea = :namea")
    , @NamedQuery(name = "InvScMan.findByTarget", query = "SELECT i FROM InvScMan i WHERE i.target = :target")
    , @NamedQuery(name = "InvScMan.findByComm", query = "SELECT i FROM InvScMan i WHERE i.comm = :comm")
    , @NamedQuery(name = "InvScMan.findByDisclmt", query = "SELECT i FROM InvScMan i WHERE i.disclmt = :disclmt")
    , @NamedQuery(name = "InvScMan.findByPhone", query = "SELECT i FROM InvScMan i WHERE i.phone = :phone")
    , @NamedQuery(name = "InvScMan.findByEmail", query = "SELECT i FROM InvScMan i WHERE i.email = :email")})
public class InvScMan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SC_MAN_ID")
    private Integer scManId;
    @Column(name = "SerialNO")
    private Integer serialNO;
    @Column(name = "TYP")
    private Integer typ;
    @Column(name = "NAMEA")
    private String namea;
    @Column(name = "TARGET")
    private Integer target;
    @Column(name = "COMM")
    private Integer comm;
    @Column(name = "DISCLMT")
    private Integer disclmt;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "manno")
    private List<InvSupplier> invSupplierList;
    @JoinColumn(name = "BRNNO", referencedColumnName = "id")
    @ManyToOne
    private Branch brnno;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IsagCompany companyId;

    public InvScMan() {
    }

    public InvScMan(Integer scManId) {
        this.scManId = scManId;
    }

    public Integer getScManId() {
        return scManId;
    }

    public void setScManId(Integer scManId) {
        this.scManId = scManId;
    }

    public Integer getSerialNO() {
        return serialNO;
    }

    public void setSerialNO(Integer serialNO) {
        this.serialNO = serialNO;
    }

    public Integer getTyp() {
        return typ;
    }

    public void setTyp(Integer typ) {
        this.typ = typ;
    }

    public String getNamea() {
        return namea;
    }

    public void setNamea(String namea) {
        this.namea = namea;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public Integer getDisclmt() {
        return disclmt;
    }

    public void setDisclmt(Integer disclmt) {
        this.disclmt = disclmt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<InvSupplier> getInvSupplierList() {
        return invSupplierList;
    }

    public void setInvSupplierList(List<InvSupplier> invSupplierList) {
        this.invSupplierList = invSupplierList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scManId != null ? scManId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvScMan)) {
            return false;
        }
        InvScMan other = (InvScMan) object;
        if ((this.scManId == null && other.scManId != null) || (this.scManId != null && !this.scManId.equals(other.scManId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.InvScMan[ scManId=" + scManId + " ]";
    }
    
}
