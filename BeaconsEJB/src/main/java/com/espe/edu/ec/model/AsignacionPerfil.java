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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "asignacion_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignacionPerfil.findAll", query = "SELECT a FROM AsignacionPerfil a")
    , @NamedQuery(name = "AsignacionPerfil.findByAsigPerfilId", query = "SELECT a FROM AsignacionPerfil a WHERE a.asigPerfilId = :asigPerfilId")
    , @NamedQuery(name = "AsignacionPerfil.findByInserted", query = "SELECT a FROM AsignacionPerfil a WHERE a.inserted = :inserted")
    , @NamedQuery(name = "AsignacionPerfil.findByUpdated", query = "SELECT a FROM AsignacionPerfil a WHERE a.updated = :updated")})
public class AsignacionPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "asig_perfil_id")
    private Integer asigPerfilId;
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
    @JoinColumn(name = "perfil_id", referencedColumnName = "perfil_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Perfil perfilId;
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioId;

    public AsignacionPerfil() {
    }

    public AsignacionPerfil(Integer asigPerfilId) {
        this.asigPerfilId = asigPerfilId;
    }

    public AsignacionPerfil(Integer asigPerfilId, Date inserted, Date updated) {
        this.asigPerfilId = asigPerfilId;
        this.inserted = inserted;
        this.updated = updated;
    }

    public Integer getAsigPerfilId() {
        return asigPerfilId;
    }

    public void setAsigPerfilId(Integer asigPerfilId) {
        this.asigPerfilId = asigPerfilId;
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

    public Perfil getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Perfil perfilId) {
        this.perfilId = perfilId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
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
        hash += (asigPerfilId != null ? asigPerfilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionPerfil)) {
            return false;
        }
        AsignacionPerfil other = (AsignacionPerfil) object;
        if ((this.asigPerfilId == null && other.asigPerfilId != null) || (this.asigPerfilId != null && !this.asigPerfilId.equals(other.asigPerfilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espe.edu.ec.model.AsignacionPerfil[ asigPerfilId=" + asigPerfilId + " ]";
    }

}
