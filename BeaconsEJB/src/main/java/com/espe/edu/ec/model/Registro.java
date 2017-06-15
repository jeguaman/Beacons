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
import javax.persistence.FetchType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r")
    , @NamedQuery(name = "Registro.findByRegistroId", query = "SELECT r FROM Registro r WHERE r.registroId = :registroId")
    , @NamedQuery(name = "Registro.findByTipo", query = "SELECT r FROM Registro r WHERE r.tipo = :tipo")
    , @NamedQuery(name = "Registro.findByInserted", query = "SELECT r FROM Registro r WHERE r.inserted = :inserted")})
public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "registro_id")
    private Integer registroId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inserted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inserted;
    @JoinColumn(name = "area_id", referencedColumnName = "area_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Area areaId;
    @JoinColumn(name = "dispositivo_id", referencedColumnName = "dispositivo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Dispositivo dispositivoId;

    public Registro() {
    }

    public Registro(Integer registroId) {
        this.registroId = registroId;
    }

    public Registro(Integer registroId, String tipo, Date inserted) {
        this.registroId = registroId;
        this.tipo = tipo;
        this.inserted = inserted;
    }

    public Integer getRegistroId() {
        return registroId;
    }

    public void setRegistroId(Integer registroId) {
        this.registroId = registroId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getInserted() {
        return inserted;
    }

    public void setInserted(Date inserted) {
        this.inserted = inserted;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }

    public Dispositivo getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(Dispositivo dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroId != null ? registroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.registroId == null && other.registroId != null) || (this.registroId != null && !this.registroId.equals(other.registroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espe.edu.ec.model.Registro[ registroId=" + registroId + " ]";
    }
    
}
