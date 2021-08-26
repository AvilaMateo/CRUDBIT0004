/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudbit004.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MATEO AVILA
 */
@Entity
@Table(name = "LIBRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")
    , @NamedQuery(name = "Libro.findByIdlibro", query = "SELECT l FROM Libro l WHERE l.idlibro = :idlibro")
    , @NamedQuery(name = "Libro.findByTitulolibro", query = "SELECT l FROM Libro l WHERE l.titulolibro = :titulolibro")
    , @NamedQuery(name = "Libro.findByFechalanzamiento", query = "SELECT l FROM Libro l WHERE l.fechalanzamiento = :fechalanzamiento")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLIBRO")
    private BigDecimal idlibro;
    @Size(max = 50)
    @Column(name = "TITULOLIBRO")
    private String titulolibro;
    @Column(name = "FECHALANZAMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechalanzamiento;
    @JoinColumn(name = "AUTOR", referencedColumnName = "IDAUTOR")
    @ManyToOne
    private Autor autor;

    public Libro() {
    }

    public Libro(BigDecimal idlibro) {
        this.idlibro = idlibro;
    }

    public BigDecimal getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(BigDecimal idlibro) {
        this.idlibro = idlibro;
    }

    public String getTitulolibro() {
        return titulolibro;
    }

    public void setTitulolibro(String titulolibro) {
        this.titulolibro = titulolibro;
    }

    public Date getFechalanzamiento() {
        return fechalanzamiento;
    }

    public void setFechalanzamiento(Date fechalanzamiento) {
        this.fechalanzamiento = fechalanzamiento;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlibro != null ? idlibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.idlibro == null && other.idlibro != null) || (this.idlibro != null && !this.idlibro.equals(other.idlibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crudbit004.entities.Libro[ idlibro=" + idlibro + " ]";
    }
    
}
