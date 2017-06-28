/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.AreaBeaconFacade;
import com.espe.edu.ec.model.AreaBeacon;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Jose Guaman
 */
@Stateless
@LocalBean
public class AreaBeaconService implements InterfaceService<AreaBeacon> {

    @EJB
    AreaBeaconFacade areaBeaconFacade;

    @Override
    public void crear(AreaBeacon object) {
        object.setInserted(new Date());
        object.setUpdated(new Date());
        areaBeaconFacade.create(object);
    }

    @Override
    public void actualizar(AreaBeacon object) {
        object.setUpdated(new Date());
        areaBeaconFacade.edit(object);
    }

    @Override
    public void eliminar(AreaBeacon object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AreaBeacon> buscarTodos() {
        return areaBeaconFacade.findAll();
    }

    @Override
    public AreaBeacon buscar(Integer id) {
        return areaBeaconFacade.find(id);
    }

    @Override
    public List<AreaBeacon> traerLazzy(Integer first, Integer size) {
        return areaBeaconFacade.traerLazzy(first, size);
    }

    public AreaBeacon traerAreaBeaconPorBeacon(Integer beaconId) {
        return areaBeaconFacade.traerAreaBeaconPorBeacon(beaconId);
    }

    public AreaBeacon traerAreaBeaconPorArea(Integer areaId) {
        return areaBeaconFacade.traerAreaBeaconPorArea(areaId);
    }

}
