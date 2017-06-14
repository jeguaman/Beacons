/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Juan
 */
@Entity
@Table(name = "historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h")
    , @NamedQuery(name = "Historial.findByHistorialId", query = "SELECT h FROM Historial h WHERE h.historialId = :historialId")
    , @NamedQuery(name = "Historial.findByCodigoHistorial", query = "SELECT h FROM Historial h WHERE h.codigoHistorial = :codigoHistorial")
    , @NamedQuery(name = "Historial.findByDescripcion", query = "SELECT h FROM Historial h WHERE h.descripcion = :descripcion")
    , @NamedQuery(name = "Historial.findByInserted", query = "SELECT h FROM Historial h WHERE h.inserted = :inserted")})
public class Historial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "historial_id")
    private Integer historialId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codigo_historial")
    private String codigoHistorial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inserted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inserted;

    public Historial() {
    }

    public Historial(Integer historialId) {
        this.historialId = historialId;
    }

    public Historial(Integer historialId, String codigoHistorial, String descripcion, Date inserted) {
        this.historialId = historialId;
        this.codigoHistorial = codigoHistorial;
        this.descripcion = descripcion;
        this.inserted = inserted;
    }

    public Integer getHistorialId() {
        return historialId;
    }

    public void setHistorialId(Integer historialId) {
        this.historialId = historialId;
    }

    public String getCodigoHistorial() {
        return codigoHistorial;
    }

    public void setCodigoHistorial(String codigoHistorial) {
        this.codigoHistorial = codigoHistorial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getInserted() {
        return inserted;
    }

    public void setInserted(Date inserted) {
        this.inserted = inserted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historialId != null ? historialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.historialId == null && other.historialId != null) || (this.historialId != null && !this.historialId.equals(other.historialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espe.edu.ec.model.Historial[ historialId=" + historialId + " ]";
    }
    
}
