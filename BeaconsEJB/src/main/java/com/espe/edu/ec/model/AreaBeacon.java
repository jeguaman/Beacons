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
@Table(name = "area_beacon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaBeacon.findAll", query = "SELECT a FROM AreaBeacon a where a.deleted = 0 ")
    , @NamedQuery(name = "AreaBeacon.findByAreaBeaconId", query = "SELECT a FROM AreaBeacon a WHERE a.areaBeaconId = :areaBeaconId and  a.deleted = 0 ")
    , @NamedQuery(name = "AreaBeacon.findByEstado", query = "SELECT a FROM AreaBeacon a WHERE a.estado = :estado and a.deleted = 0 ")
    , @NamedQuery(name = "AreaBeacon.findByInserted", query = "SELECT a FROM AreaBeacon a WHERE a.inserted = :inserted and a.deleted = 0 ")
    , @NamedQuery(name = "AreaBeacon.findByUpdated", query = "SELECT a FROM AreaBeacon a WHERE a.updated = :updated and a.deleted = 0 ")})
public class AreaBeacon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "area_beacon_id")
    private Integer areaBeaconId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
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
    @JoinColumn(name = "area_id", referencedColumnName = "area_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Area areaId;
    @JoinColumn(name = "beacon_id", referencedColumnName = "beacon_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Beacon beaconId;

    public AreaBeacon() {
    }

    public AreaBeacon(Integer areaBeaconId) {
        this.areaBeaconId = areaBeaconId;
    }

    public AreaBeacon(Integer areaBeaconId, boolean estado, Date inserted, Date updated) {
        this.areaBeaconId = areaBeaconId;
        this.estado = estado;
        this.inserted = inserted;
        this.updated = updated;
    }

    public AreaBeacon(Integer areaId, Integer beaconId) {
        this.areaId = new Area(areaId);
        this.beaconId = new Beacon(beaconId);
    }

    public Integer getAreaBeaconId() {
        return areaBeaconId;
    }

    public void setAreaBeaconId(Integer areaBeaconId) {
        this.areaBeaconId = areaBeaconId;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

    public Beacon getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(Beacon beaconId) {
        this.beaconId = beaconId;
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
        hash += (areaBeaconId != null ? areaBeaconId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaBeacon)) {
            return false;
        }
        AreaBeacon other = (AreaBeacon) object;
        if ((this.areaBeaconId == null && other.areaBeaconId != null) || (this.areaBeaconId != null && !this.areaBeaconId.equals(other.areaBeaconId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espe.edu.ec.model.AreaBeacon[ areaBeaconId=" + areaBeaconId + " ]";
    }

}
