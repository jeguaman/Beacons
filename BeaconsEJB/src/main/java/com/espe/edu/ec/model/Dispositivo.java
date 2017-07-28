/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Juan
 */
@Entity
@Table(name = "dispositivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dispositivo.findAll", query = "SELECT d FROM Dispositivo d where d.deleted = 0 ")
    , @NamedQuery(name = "Dispositivo.findByDispositivoId", query = "SELECT d FROM Dispositivo d WHERE d.dispositivoId = :dispositivoId and d.deleted = 0 ")
    , @NamedQuery(name = "Dispositivo.findByImei", query = "SELECT new Dispositivo(d.dispositivoId, d.imei, d.descripcion) FROM Dispositivo d WHERE d.imei = :imei and d.deleted = 0 ")
    , @NamedQuery(name = "Dispositivo.findByDescripcion", query = "SELECT d FROM Dispositivo d WHERE d.descripcion = :descripcion and d.deleted = 0 ")
    , @NamedQuery(name = "Dispositivo.findByInserted", query = "SELECT d FROM Dispositivo d WHERE d.inserted = :inserted and d.deleted = 0 ")
    , @NamedQuery(name = "Dispositivo.findByUpdated", query = "SELECT d FROM Dispositivo d WHERE d.updated = :updated and d.deleted = 0 ")})
public class Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dispositivo_id")
    private Integer dispositivoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "imei")
    private String imei;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inserted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inserted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted", columnDefinition="tinyint(1) default 0")
    private Boolean deleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dispositivoId", fetch = FetchType.LAZY)
    private List<Registro> registroList;

    public Dispositivo() {
    }

    public Dispositivo(Integer dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public Dispositivo(Integer dispositivoId, String imei, String descripcion) {
        this.dispositivoId = dispositivoId;
        this.imei = imei;
        this.descripcion = descripcion;
    }
    
    public Dispositivo(Integer dispositivoId, String imei, Date inserted, Date updated) {
        this.dispositivoId = dispositivoId;
        this.imei = imei;
        this.inserted = inserted;
        this.updated = updated;
    }

    public Integer getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(Integer dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @XmlTransient
    public List<Registro> getRegistroList() {
        return registroList;
    }

    public void setRegistroList(List<Registro> registroList) {
        this.registroList = registroList;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dispositivoId != null ? dispositivoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dispositivo)) {
            return false;
        }
        Dispositivo other = (Dispositivo) object;
        if ((this.dispositivoId == null && other.dispositivoId != null) || (this.dispositivoId != null && !this.dispositivoId.equals(other.dispositivoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espe.edu.ec.model.Dispositivo[ dispositivoId=" + dispositivoId + " ]";
    }

}
