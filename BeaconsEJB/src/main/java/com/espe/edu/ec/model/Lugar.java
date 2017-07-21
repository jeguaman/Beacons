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
import javax.persistence.Lob;
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
@Table(name = "lugar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lugar.findAll", query = "SELECT l FROM Lugar l")
    , @NamedQuery(name = "Lugar.findByLugarId", query = "SELECT l FROM Lugar l WHERE l.lugarId = :lugarId")
    , @NamedQuery(name = "Lugar.findByDescripcion", query = "SELECT l FROM Lugar l WHERE l.descripcion = :descripcion")
    , @NamedQuery(name = "Lugar.findByTitulo", query = "SELECT l FROM Lugar l WHERE l.titulo = :titulo")
    , @NamedQuery(name = "Lugar.findByInserted", query = "SELECT l FROM Lugar l WHERE l.inserted = :inserted")
    , @NamedQuery(name = "Lugar.findByUpdated", query = "SELECT l FROM Lugar l WHERE l.updated = :updated")})
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lugar_id")
    private Integer lugarId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo")
    private String titulo;
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
    @Column(name = "deleted", columnDefinition = "tinyint(1) default 0")
    private Boolean deleted;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "icono")
    private byte[] icono;
    @JoinColumn(name = "area_id", referencedColumnName = "area_id")
    @ManyToOne(optional = false)
    private Area areaId;

    public Lugar() {
    }

    public Lugar(Integer lugarId, String titulo) {
        this.lugarId = lugarId;
        this.titulo = titulo;
    }

    public Lugar(Integer lugarId, String titulo, String descripcion) {
        this.lugarId = lugarId;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Lugar(Integer lugarId) {
        this.lugarId = lugarId;
    }

    public Lugar(Integer lugarId, String descripcion, byte[] imagen, String titulo, Date inserted, Date updated) {
        this.lugarId = lugarId;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.titulo = titulo;
        this.inserted = inserted;
        this.updated = updated;
    }

    public Integer getLugarId() {
        return lugarId;
    }

    public void setLugarId(Integer lugarId) {
        this.lugarId = lugarId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public byte[] getIcono() {
        return icono;
    }

    public void setIcono(byte[] icono) {
        this.icono = icono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lugarId != null ? lugarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lugar)) {
            return false;
        }
        Lugar other = (Lugar) object;
        if ((this.lugarId == null && other.lugarId != null) || (this.lugarId != null && !this.lugarId.equals(other.lugarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espe.edu.ec.model.Lugar[ lugarId=" + lugarId + " ]";
    }

}
