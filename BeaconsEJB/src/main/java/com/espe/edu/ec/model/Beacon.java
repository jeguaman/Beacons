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
@Table(name = "beacon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beacon.findAll", query = "SELECT b FROM Beacon b  where b.deleted = 0 ")
    , @NamedQuery(name = "Beacon.findByBeaconId", query = "SELECT b FROM Beacon b WHERE b.beaconId = :beaconId and b.deleted = 0")
    , @NamedQuery(name = "Beacon.findByUuid", query = "SELECT b FROM Beacon b WHERE b.uuid = :uuid and b.deleted = 0")
    , @NamedQuery(name = "Beacon.findByMajor", query = "SELECT b FROM Beacon b WHERE b.major = :major and b.deleted = 0")
    , @NamedQuery(name = "Beacon.findByMinor", query = "SELECT b FROM Beacon b WHERE b.minor = :minor and b.deleted = 0")
    , @NamedQuery(name = "Beacon.findByDescripcion", query = "SELECT b FROM Beacon b WHERE b.descripcion = :descripcion and b.deleted = 0")
    , @NamedQuery(name = "Beacon.findByInserted", query = "SELECT b FROM Beacon b WHERE b.inserted = :inserted and b.deleted = 0")
    , @NamedQuery(name = "Beacon.findByUpdated", query = "SELECT b FROM Beacon b WHERE b.updated = :updated and b.deleted = 0")})
public class Beacon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "beacon_id")
    private Integer beaconId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "major")
    private String major;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "minor")
    private String minor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beaconId", fetch = FetchType.LAZY)
    private List<AreaBeacon> areaBeaconList;

    public Beacon() {
    }

    public Beacon(Integer beaconId) {
        this.beaconId = beaconId;
    }

    public Beacon(Integer beaconId, String uuid, String major, String minor, String descripcion, byte[] imagen, Date inserted, Date updated) {
        this.beaconId = beaconId;
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.inserted = inserted;
        this.updated = updated;
    }

    public Integer getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(Integer beaconId) {
        this.beaconId = beaconId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (beaconId != null ? beaconId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beacon)) {
            return false;
        }
        Beacon other = (Beacon) object;
        if ((this.beaconId == null && other.beaconId != null) || (this.beaconId != null && !this.beaconId.equals(other.beaconId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espe.edu.ec.model.Beacon[ beaconId=" + beaconId + " ]";
    }

}
