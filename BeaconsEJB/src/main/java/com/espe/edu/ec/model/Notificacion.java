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
@Table(name = "notificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n where n.deleted = 0")
    , @NamedQuery(name = "Notificacion.findByNotificacionId", query = "SELECT n FROM Notificacion n WHERE n.notificacionId = :notificacionId and n.deleted = 0")
    , @NamedQuery(name = "Notificacion.findByDescripcion", query = "SELECT n FROM Notificacion n WHERE n.descripcion = :descripcion and n.deleted = 0")
    , @NamedQuery(name = "Notificacion.findByTipo", query = "SELECT n FROM Notificacion n WHERE n.tipo = :tipo and n.deleted = 0")
    , @NamedQuery(name = "Notificacion.findByInserted", query = "SELECT n FROM Notificacion n WHERE n.inserted = :inserted and n.deleted = 0")
    , @NamedQuery(name = "Notificacion.findByUpdated", query = "SELECT n FROM Notificacion n WHERE n.updated = :updated and n.deleted = 0")})
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "notificacion_id")
    private Integer notificacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted", columnDefinition="tinyint(1) default 0")
    private Boolean deleted;
    @JoinColumn(name = "area_id", referencedColumnName = "area_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Area areaId;

    public Notificacion() {
    }

    public Notificacion(Integer notificacionId) {
        this.notificacionId = notificacionId;
    }

    public Notificacion(Integer notificacionId, String descripcion, String tipo, Date inserted, Date updated) {
        this.notificacionId = notificacionId;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.inserted = inserted;
        this.updated = updated;
    }

    public Integer getNotificacionId() {
        return notificacionId;
    }

    public void setNotificacionId(Integer notificacionId) {
        this.notificacionId = notificacionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
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
        hash += (notificacionId != null ? notificacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.notificacionId == null && other.notificacionId != null) || (this.notificacionId != null && !this.notificacionId.equals(other.notificacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espe.edu.ec.model.Notificacion[ notificacionId=" + notificacionId + " ]";
    }
    
}
