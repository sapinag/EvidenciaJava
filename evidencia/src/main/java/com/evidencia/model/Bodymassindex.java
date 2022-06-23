/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evidencia.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sistemas
 */
@Entity
@Table(name = "bodymassindex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodymassindex.findAll", query = "SELECT b FROM Bodymassindex b"),
    @NamedQuery(name = "Bodymassindex.findByIdbodymassindex", query = "SELECT b FROM Bodymassindex b WHERE b.idbodymassindex = :idbodymassindex"),
    @NamedQuery(name = "Bodymassindex.findByImc", query = "SELECT b FROM Bodymassindex b WHERE b.imc = :imc"),
    @NamedQuery(name = "Bodymassindex.findByCreatedDate", query = "SELECT b FROM Bodymassindex b WHERE b.createdDate = :createdDate")})
public class Bodymassindex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idbodymassindex")
    private Integer idbodymassindex;
    @Basic(optional = false)
    @NotNull
    @Column(name = "imc")
    private float imc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "userid", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private UserImc userid;

    public Bodymassindex() {
    }

    public Bodymassindex(Integer idbodymassindex) {
        this.idbodymassindex = idbodymassindex;
    }

    public Bodymassindex(Integer idbodymassindex, float imc, Date createdDate) {
        this.idbodymassindex = idbodymassindex;
        this.imc = imc;
        this.createdDate = createdDate;
    }

    public Integer getIdbodymassindex() {
        return idbodymassindex;
    }

    public void setIdbodymassindex(Integer idbodymassindex) {
        this.idbodymassindex = idbodymassindex;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserImc getUserid() {
        return userid;
    }

    public void setUserid(UserImc userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbodymassindex != null ? idbodymassindex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodymassindex)) {
            return false;
        }
        Bodymassindex other = (Bodymassindex) object;
        if ((this.idbodymassindex == null && other.idbodymassindex != null) || (this.idbodymassindex != null && !this.idbodymassindex.equals(other.idbodymassindex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eserna.evidencia.model.Bodymassindex[ idbodymassindex=" + idbodymassindex + " ]";
    }
    
}
