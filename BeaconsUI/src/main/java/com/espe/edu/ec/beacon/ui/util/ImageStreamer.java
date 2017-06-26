/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.beacon.ui.util;

import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.model.Beacon;
import com.espe.edu.ec.model.Lugar;
import com.espe.edu.ec.services.AreaService;
import com.espe.edu.ec.services.BeaconService;
import com.espe.edu.ec.services.LugarService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.omnifaces.cdi.GraphicImageBean;

/**
 *
 * @author Juan
 */
@ManagedBean
@GraphicImageBean
public class ImageStreamer {

    @EJB
    LugarService lugarService;

    @EJB
    AreaService areaService;

    @EJB
    BeaconService beaconService;

    /**
     * Creates a new instance of ImageStreamer
     */
    public ImageStreamer() {
    }

    public byte[] getLugarImagen(int lugar_id) {
        Lugar tmp = null;
        tmp = lugarService.buscar(lugar_id);
        if (tmp != null) {
            return tmp.getImagen();
        } else {
            return null;
        }
    }

    public byte[] getAreaImagen(int area_id) {
        Area tmp = null;
        tmp = areaService.buscar(area_id);
        if (tmp != null) {
            return tmp.getImagen();
        } else {
            return null;
        }
    }

    public byte[] getBeaconImagen(int beacon_id) {
        Beacon tmp = null;
        tmp = beaconService.buscar(beacon_id);
        if (tmp != null) {
            return tmp.getImagen();
        } else {
            return null;
        }
    }
}
