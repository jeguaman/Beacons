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
        object.setDeleted(Boolean.FALSE);
        areaFacade.create(object);
    }

    @Override
    public void actualizar(Area object) {
        object.setUpdated(new Date());
        areaFacade.edit(object);
    }

    @Override
    public void eliminar(Area object) {
        object.setUpdated(new Date());
        object.setDeleted(Boolean.TRUE);
        areaFacade.edit(object);
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

    public List<Area> traerAreasPorIdBeaconNoBytesImageLazzy(Integer beaconId, int first, int size) {
        return areaFacade.traerAreasPorIdBeaconNoBytesImageLazzy(beaconId, first, size);
    }

    public int traerAreasPorIdBeaconNoBytesImageTotal(Integer areaId) {
        return areaFacade.traerAreasPorIdBeaconNoBytesImageTotal(areaId);
    }

    public List<Area> traerAreasDisponibles() {
        return areaFacade.traerAreasDisponibles();
    }

    public List<Area> traerTodasAreasNoImagen() {
        return areaFacade.traerTodasAreasNoImagen();
    }

    public List<Area> traerPorTituloLike(Integer first, Integer size, String titulo) {
        return areaFacade.traerPorTituloLike(first, size, titulo);
    }

    public int totalPorTituloLike(String titulo) {
        return areaFacade.totalPorTituloLike(titulo);
    }

    public Area traerImagenPorIdArea(Integer areaId) {
        return areaFacade.traerImagenPorIdArea(areaId);
    }

    public boolean verificarArea(Integer idArea) {
        return areaFacade.verificarArea(idArea);
    }
}
