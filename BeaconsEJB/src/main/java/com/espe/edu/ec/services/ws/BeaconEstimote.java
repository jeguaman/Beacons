/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services.ws;

/**
 *
 * @author Juan
 */
public class BeaconEstimote{

    private int beaconId;
    private String proximityUUID;
    private int major;
    private int minor;
    private String enterMessage; 
    private String exitMessage;
    private int areaId;

    public BeaconEstimote() {
    }

    public BeaconEstimote(int beaconId, String proximityUUID, int major, int minor, String enterMessage, String exitMessage, int areaId) {
        this.beaconId = beaconId;
        this.proximityUUID = proximityUUID;
        this.major = major;
        this.minor = minor;
        this.enterMessage = enterMessage;
        this.exitMessage = exitMessage;
        this.areaId = areaId;
    }

    public int getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(int beaconId) {
        this.beaconId = beaconId;
    }

    public String getProximityUUID() {
        return proximityUUID;
    }

    public void setProximityUUID(String proximityUUID) {
        this.proximityUUID = proximityUUID;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public String getEnterMessage() {
        return enterMessage;
    }

    public void setEnterMessage(String enterMessage) {
        this.enterMessage = enterMessage;
    }

    public String getExitMessage() {
        return exitMessage;
    }

    public void setExitMessage(String exitMessage) {
        this.exitMessage = exitMessage;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.beaconId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BeaconEstimote other = (BeaconEstimote) obj;
        if (this.beaconId != other.beaconId) {
            return false;
        }
        return true;
    }        

}
