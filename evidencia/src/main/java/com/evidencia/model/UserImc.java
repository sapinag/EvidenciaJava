/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evidencia.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sistemas
 */
@Entity
@Table(name = "user_imc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserImc.findAll", query = "SELECT u FROM UserImc u"),
    @NamedQuery(name = "UserImc.findByIduser", query = "SELECT u FROM UserImc u WHERE u.iduser = :iduser"),
    @NamedQuery(name = "UserImc.findByUsername", query = "SELECT u FROM UserImc u WHERE u.username = :username"),
    @NamedQuery(name = "UserImc.findByPassword", query = "SELECT u FROM UserImc u WHERE u.password = :password")})
public class UserImc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iduser")
    private Integer iduser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Bodymassindex> bodymassindexCollection;
    @JoinColumn(name = "idperson", referencedColumnName = "idperson")
    @ManyToOne(optional = false)
    private PersonImc idperson;

    public UserImc() {
    }

    public UserImc(Integer iduser) {
        this.iduser = iduser;
    }

    public UserImc(Integer iduser, String username, String password) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Bodymassindex> getBodymassindexCollection() {
        return bodymassindexCollection;
    }

    public void setBodymassindexCollection(Collection<Bodymassindex> bodymassindexCollection) {
        this.bodymassindexCollection = bodymassindexCollection;
    }

    public PersonImc getIdperson() {
        return idperson;
    }

    public void setIdperson(PersonImc idperson) {
        this.idperson = idperson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserImc)) {
            return false;
        }
        UserImc other = (UserImc) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eserna.evidencia.model.UserImc[ iduser=" + iduser + " ]";
    }
    
}
