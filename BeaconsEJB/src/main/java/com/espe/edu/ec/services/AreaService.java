/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.AreaFacade;
import com.espe.edu.ec.model.Area;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Jose Guaman
 */
public class AreaService implements InterfaceService<Area> {

    @EJB
    AreaFacade areaFacade;
    
    @Override
    public void crear(Area object) {
        object.setInserted(new Date());
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
        Area a = new Area();
        a.setAreaId(id);
        return areaFacade.find(a);
    }

    @Override
    public List<Area> traerLazzy(Integer first, Integer size) {
        return areaFacade.traerLazzy(first, size);
    }
    
}
