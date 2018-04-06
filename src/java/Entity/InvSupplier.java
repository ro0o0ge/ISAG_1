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
@Table(name = "inv_supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvSupplier.findAll", query = "SELECT i FROM InvSupplier i")
    , @NamedQuery(name = "InvSupplier.findBySupplierId", query = "SELECT i FROM InvSupplier i WHERE i.supplierId = :supplierId")
    , @NamedQuery(name = "InvSupplier.findByCode", query = "SELECT i FROM InvSupplier i WHERE i.code = :code")
    , @NamedQuery(name = "InvSupplier.findByNamea", query = "SELECT i FROM InvSupplier i WHERE i.namea = :namea")
    , @NamedQuery(name = "InvSupplier.findByTel", query = "SELECT i FROM InvSupplier i WHERE i.tel = :tel")
    , @NamedQuery(name = "InvSupplier.findByFax", query = "SELECT i FROM InvSupplier i WHERE i.fax = :fax")
    , @NamedQuery(name = "InvSupplier.findByEmail", query = "SELECT i FROM InvSupplier i WHERE i.email = :email")
    , @NamedQuery(name = "InvSupplier.findByMobil", query = "SELECT i FROM InvSupplier i WHERE i.mobil = :mobil")
    , @NamedQuery(name = "InvSupplier.findByAdrs", query = "SELECT i FROM InvSupplier i WHERE i.adrs = :adrs")
    , @NamedQuery(name = "InvSupplier.findByPobox", query = "SELECT i FROM InvSupplier i WHERE i.pobox = :pobox")
    , @NamedQuery(name = "InvSupplier.findByDisc", query = "SELECT i FROM InvSupplier i WHERE i.disc = :disc")
    , @NamedQuery(name = "InvSupplier.findByTotPursal", query = "SELECT i FROM InvSupplier i WHERE i.totPursal = :totPursal")
    , @NamedQuery(name = "InvSupplier.findByTotPayrcv", query = "SELECT i FROM InvSupplier i WHERE i.totPayrcv = :totPayrcv")
    , @NamedQuery(name = "InvSupplier.findByOpnbaldbt", query = "SELECT i FROM InvSupplier i WHERE i.opnbaldbt = :opnbaldbt")
    , @NamedQuery(name = "InvSupplier.findByOpnbalcrd", query = "SELECT i FROM InvSupplier i WHERE i.opnbalcrd = :opnbalcrd")
    , @NamedQuery(name = "InvSupplier.findByBallimt", query = "SELECT i FROM InvSupplier i WHERE i.ballimt = :ballimt")
    , @NamedQuery(name = "InvSupplier.findByRemarka", query = "SELECT i FROM InvSupplier i WHERE i.remarka = :remarka")
    , @NamedQuery(name = "InvSupplier.findByCreatedDate", query = "SELECT i FROM InvSupplier i WHERE i.createdDate = :createdDate")
    , @NamedQuery(name = "InvSupplier.findByModifiedDate", query = "SELECT i FROM InvSupplier i WHERE i.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "InvSupplier.findByActv", query = "SELECT i FROM InvSupplier i WHERE i.actv = :actv")
    , @NamedQuery(name = "InvSupplier.findByCustsuppFlg", query = "SELECT i FROM InvSupplier i WHERE i.custsuppFlg = :custsuppFlg")})
public class InvSupplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUPPLIER_ID")
    private Integer supplierId;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAMEA")
    private String namea;
    @Column(name = "TEL")
    private String tel;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "MOBIL")
    private String mobil;
    @Column(name = "ADRS")
    private String adrs;
    @Column(name = "POBOX")
    private String pobox;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DISC")
    private Double disc;
    @Column(name = "TOT_PURSAL")
    private Double totPursal;
    @Column(name = "TOT_PAYRCV")
    private Double totPayrcv;
    @Column(name = "OPNBALDBT")
    private Double opnbaldbt;
    @Column(name = "OPNBALCRD")
    private Double opnbalcrd;
    @Column(name = "BALLIMT")
    private Double ballimt;
    @Column(name = "REMARKA")
    private String remarka;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "ACTV")
    private Integer actv;
    @Column(name = "CUSTSUPP_FLG")
    private Integer custsuppFlg;
    @JoinColumn(name = "BRNNO", referencedColumnName = "id")
    @ManyToOne
    private Branch brnno;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IsagCompany companyId;
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "id")
    @ManyToOne
    private IsagUser createdBy;
    @JoinColumn(name = "TYPNO", referencedColumnName = "id")
    @ManyToOne
    private GeneralSymbol typno;
    @JoinColumn(name = "COUNTRY", referencedColumnName = "id")
    @ManyToOne
    private GeneralSymbol country;
    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "id")
    @ManyToOne
    private IsagUser modifiedBy;
    @JoinColumn(name = "MANNO", referencedColumnName = "SC_MAN_ID")
    @ManyToOne
    private InvScMan manno;
    @JoinColumn(name = "CCY", referencedColumnName = "id")
    @ManyToOne
    private Currency ccy;
    @JoinColumn(name = "ACCNO", referencedColumnName = "id")
    @ManyToOne
    private GlAccount accno;
    @JoinColumn(name = "PRICELVL", referencedColumnName = "id")
    @ManyToOne
    private GlCostCenter pricelvl;

    public InvSupplier() {
    }

    public InvSupplier(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public InvSupplier(Integer supplierId, Date createdDate) {
        this.supplierId = supplierId;
        this.createdDate = createdDate;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNamea() {
        return namea;
    }

    public void setNamea(String namea) {
        this.namea = namea;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getAdrs() {
        return adrs;
    }

    public void setAdrs(String adrs) {
        this.adrs = adrs;
    }

    public String getPobox() {
        return pobox;
    }

    public void setPobox(String pobox) {
        this.pobox = pobox;
    }

    public Double getDisc() {
        return disc;
    }

    public void setDisc(Double disc) {
        this.disc = disc;
    }

    public Double getTotPursal() {
        return totPursal;
    }

    public void setTotPursal(Double totPursal) {
        this.totPursal = totPursal;
    }

    public Double getTotPayrcv() {
        return totPayrcv;
    }

    public void setTotPayrcv(Double totPayrcv) {
        this.totPayrcv = totPayrcv;
    }

    public Double getOpnbaldbt() {
        return opnbaldbt;
    }

    public void setOpnbaldbt(Double opnbaldbt) {
        this.opnbaldbt = opnbaldbt;
    }

    public Double getOpnbalcrd() {
        return opnbalcrd;
    }

    public void setOpnbalcrd(Double opnbalcrd) {
        this.opnbalcrd = opnbalcrd;
    }

    public Double getBallimt() {
        return ballimt;
    }

    public void setBallimt(Double ballimt) {
        this.ballimt = ballimt;
    }

    public String getRemarka() {
        return remarka;
    }

    public void setRemarka(String remarka) {
        this.remarka = remarka;
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

    public Integer getActv() {
        return actv;
    }

    public void setActv(Integer actv) {
        this.actv = actv;
    }

    public Integer getCustsuppFlg() {
        return custsuppFlg;
    }

    public void setCustsuppFlg(Integer custsuppFlg) {
        this.custsuppFlg = custsuppFlg;
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

    public GeneralSymbol getTypno() {
        return typno;
    }

    public void setTypno(GeneralSymbol typno) {
        this.typno = typno;
    }

    public GeneralSymbol getCountry() {
        return country;
    }

    public void setCountry(GeneralSymbol country) {
        this.country = country;
    }

    public IsagUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(IsagUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public InvScMan getManno() {
        return manno;
    }

    public void setManno(InvScMan manno) {
        this.manno = manno;
    }

    public Currency getCcy() {
        return ccy;
    }

    public void setCcy(Currency ccy) {
        this.ccy = ccy;
    }

    public GlAccount getAccno() {
        return accno;
    }

    public void setAccno(GlAccount accno) {
        this.accno = accno;
    }

    public GlCostCenter getPricelvl() {
        return pricelvl;
    }

    public void setPricelvl(GlCostCenter pricelvl) {
        this.pricelvl = pricelvl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvSupplier)) {
            return false;
        }
        InvSupplier other = (InvSupplier) object;
        if ((this.supplierId == null && other.supplierId != null) || (this.supplierId != null && !this.supplierId.equals(other.supplierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.InvSupplier[ supplierId=" + supplierId + " ]";
    }
    
}
