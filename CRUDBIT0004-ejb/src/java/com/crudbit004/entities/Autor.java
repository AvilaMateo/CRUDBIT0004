/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudbit004.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author MATEO AVILA
 */
@Entity
@Table(name = "AUTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autor.findAll", query = "SELECT a FROM Autor a")
    , @NamedQuery(name = "Autor.findByIdautor", query = "SELECT a FROM Autor a WHERE a.idautor = :idautor")
    , @NamedQuery(name = "Autor.findByNombreautor", query = "SELECT a FROM Autor a WHERE a.nombreautor = :nombreautor")
    , @NamedQuery(name = "Autor.findByApellidoautor", query = "SELECT a FROM Autor a WHERE a.apellidoautor = :apellidoautor")
    , @NamedQuery(name = "Autor.findByNacionalidadautor", query = "SELECT a FROM Autor a WHERE a.nacionalidadautor = :nacionalidadautor")})
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDAUTOR")
    private BigDecimal idautor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBREAUTOR")
    private String nombreautor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "APELLIDOAUTOR")
    private String apellidoautor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NACIONALIDADAUTOR")
    private String nacionalidadautor;
    @OneToMany(mappedBy = "autor")
    private List<Libro> libroList;

    public Autor() {
    }

    public Autor(BigDecimal idautor) {
        this.idautor = idautor;
    }

    public Autor(BigDecimal idautor, String nombreautor, String apellidoautor, String nacionalidadautor) {
        this.idautor = idautor;
        this.nombreautor = nombreautor;
        this.apellidoautor = apellidoautor;
        this.nacionalidadautor = nacionalidadautor;
    }

    public BigDecimal getIdautor() {
        return idautor;
    }

    public void setIdautor(BigDecimal idautor) {
        this.idautor = idautor;
    }

    public String getNombreautor() {
        return nombreautor;
    }

    public void setNombreautor(String nombreautor) {
        this.nombreautor = nombreautor;
    }

    public String getApellidoautor() {
        return apellidoautor;
    }

    public void setApellidoautor(String apellidoautor) {
        this.apellidoautor = apellidoautor;
    }

    public String getNacionalidadautor() {
        return nacionalidadautor;
    }

    public void setNacionalidadautor(String nacionalidadautor) {
        this.nacionalidadautor = nacionalidadautor;
    }

    @XmlTransient
    public List<Libro> getLibroList() {
        return libroList;
    }

    public void setLibroList(List<Libro> libroList) {
        this.libroList = libroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idautor != null ? idautor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.idautor == null && other.idautor != null) || (this.idautor != null && !this.idautor.equals(other.idautor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crudbit004.entities.Autor[ idautor=" + idautor + " ]";
    }
    
}
