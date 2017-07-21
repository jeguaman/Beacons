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
import javax.persistence.Lob;
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
@Table(name = "area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a where a.deleted = 0 ")
    , @NamedQuery(name = "Area.findByAreaId", query = "SELECT a FROM Area a WHERE a.areaId = :areaId  and a.deleted = 0 ")
    , @NamedQuery(name = "Area.findByTitulo", query = "SELECT a FROM Area a WHERE a.titulo = :titulo  and a.deleted = 0 ")
    , @NamedQuery(name = "Area.findByDescripcion", query = "SELECT a FROM Area a WHERE a.descripcion = :descripcion  and a.deleted = 0 ")
    , @NamedQuery(name = "Area.findByInserted", query = "SELECT a FROM Area a WHERE a.inserted = :inserted  and a.deleted = 0 ")
    , @NamedQuery(name = "Area.findByUpdated", query = "SELECT a FROM Area a WHERE a.updated = :updated and  a.deleted = 0 ")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "area_id")
    private Integer areaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo")
    private String titulo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId", fetch = FetchType.LAZY)
    private List<AreaBeacon> areaBeaconList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId", fetch = FetchType.LAZY)
    private List<Lugar> lugarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId", fetch = FetchType.LAZY)
    private List<Notificacion> notificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId", fetch = FetchType.LAZY)
    private List<Registro> registroList;

    public Area() {
    }

    public Area(Integer areaId) {
        this.areaId = areaId;
    }

    public Area(Integer areaId, String titulo, String descripcion, byte[] imagen, Date inserted, Date updated) {
        this.areaId = areaId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.inserted = inserted;
        this.updated = updated;
    }

    public Area(Integer areaId, String titulo, String descripcion) {
        this.areaId = areaId;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Area(Integer areaId, byte[] imagen) {
        this.areaId = areaId;
        this.imagen = imagen;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @XmlTransient
    public List<AreaBeacon> getAreaBeaconList() {
        return areaBeaconList;
    }

    public void setAreaBeaconList(List<AreaBeacon> areaBeaconList) {
        this.areaBeaconList = areaBeaconList;
    }

    @XmlTransient
    public List<Lugar> getLugarList() {
        return lugarList;
    }

    public void setLugarList(List<Lugar> lugarList) {
        this.lugarList = lugarList;
    }

    @XmlTransient
    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    @XmlTransient
    public List<Registro> getRegistroList() {
        return registroList;
    }

    public void setRegistroList(List<Registro> registroList) {
        this.registroList = registroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (areaId != null ? areaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.areaId == null && other.areaId != null) || (this.areaId != null && !this.areaId.equals(other.areaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Area{" + "areaId=" + areaId + ", titulo=" + titulo + ", descripcion=" + descripcion + ", imagen=" + imagen + ", inserted=" + inserted + ", updated=" + updated + ", areaBeaconList=" + areaBeaconList + ", lugarList=" + lugarList + ", notificacionList=" + notificacionList + ", registroList=" + registroList + '}';
    }

}
