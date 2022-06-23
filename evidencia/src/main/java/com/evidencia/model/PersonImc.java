/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evidencia.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sistemas
 */
@Entity
@Table(name = "person_imc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonImc.findAll", query = "SELECT p FROM PersonImc p"),
    @NamedQuery(name = "PersonImc.findByIdperson", query = "SELECT p FROM PersonImc p WHERE p.idperson = :idperson"),
    @NamedQuery(name = "PersonImc.findByFirstname", query = "SELECT p FROM PersonImc p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "PersonImc.findByLastname", query = "SELECT p FROM PersonImc p WHERE p.lastname = :lastname"),
    @NamedQuery(name = "PersonImc.findByAge", query = "SELECT p FROM PersonImc p WHERE p.age = :age"),
    @NamedQuery(name = "PersonImc.findByGender", query = "SELECT p FROM PersonImc p WHERE p.gender = :gender"),
    @NamedQuery(name = "PersonImc.findByHeight", query = "SELECT p FROM PersonImc p WHERE p.height = :height"),
    @NamedQuery(name = "PersonImc.findByWeight", query = "SELECT p FROM PersonImc p WHERE p.weight = :weight"),
    @NamedQuery(name = "PersonImc.findByDateofbirth", query = "SELECT p FROM PersonImc p WHERE p.dateofbirth = :dateofbirth")})
public class PersonImc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idperson")
    private Integer idperson;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "height")
    private float height;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private float weight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateofbirth")
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idperson")
    private Collection<UserImc> userImcCollection;

    public PersonImc() {
    }

    public PersonImc(Integer idperson) {
        this.idperson = idperson;
    }

    public PersonImc(Integer idperson, String firstname, String lastname, int age, String gender, float height, float weight, Date dateofbirth) {
        this.idperson = idperson;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.dateofbirth = dateofbirth;
    }

    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    @XmlTransient
    public Collection<UserImc> getUserImcCollection() {
        return userImcCollection;
    }

    public void setUserImcCollection(Collection<UserImc> userImcCollection) {
        this.userImcCollection = userImcCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperson != null ? idperson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonImc)) {
            return false;
        }
        PersonImc other = (PersonImc) object;
        if ((this.idperson == null && other.idperson != null) || (this.idperson != null && !this.idperson.equals(other.idperson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eserna.evidencia.model.PersonImc[ idperson=" + idperson + " ]";
    }
    
}
