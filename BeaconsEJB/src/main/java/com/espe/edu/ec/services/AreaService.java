/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.AreaFacade;
import com.espe.edu.ec.model.Area;
import java.io.Serializable;
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
public class AreaService implements InterfaceService<Area>, Serializable {

    @EJB
    AreaFacade areaFacade;

    @Override
    public void crear(Area object) {
        object.setInserted(new Date());
        object.setUpdated(new Date());
        areaFacade.create(object);
    }

    @Override
    public void actualizar(Area object) {
        object.setUpdated(new Date());
        areaFacade.edit(object);
    }

    @Override
    public void eliminar(Area object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Area> buscarTodos() {
        return areaFacade.findAll();
    }

    @Override
    public Area buscar(Integer id) {
        return areaFacade.find(id);
    }

    @Override
    public List<Area> traerLazzy(Integer first, Integer size) {
        return areaFacade.traerAreasLazzy(first, size);
    }

    public List<Area> traerAreasPorUUIDBeacon(String uuidBeacon) {
        return areaFacade.traerAreasPorUUIDBeacon(uuidBeacon);
    }

    public Integer totalRegistros() {
        return areaFacade.count();
    }
}
