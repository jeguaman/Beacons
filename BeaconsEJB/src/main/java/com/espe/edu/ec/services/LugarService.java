/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.LugarFacade;
import com.espe.edu.ec.model.Lugar;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Juan
 */
@Stateless
@LocalBean
public class LugarService implements InterfaceService<Lugar>, Serializable {

    @EJB
    LugarFacade lugarFacade;

    @Override
    public void crear(Lugar object) {
        object.setInserted(new Date());
        object.setUpdated(new Date());
        object.setDeleted(Boolean.FALSE);
        lugarFacade.create(object);
    }

    @Override
    public void actualizar(Lugar object) {
        object.setUpdated(new Date());
        lugarFacade.edit(object);
    }

    @Override
    public void eliminar(Lugar object) {
        object.setUpdated(new Date());
        object.setDeleted(Boolean.TRUE);
        lugarFacade.edit(object);
    }

    @Override
    public List<Lugar> buscarTodos() {
        return lugarFacade.findAll();
    }

    @Override
    public Lugar buscar(Integer id) {
        return lugarFacade.find(id);
    }

    @Override
    public List<Lugar> traerLazzy(Integer first, Integer size) {
        return lugarFacade.traerLugaresLazzy(first, size);
    }

    public List<Lugar> traerLugaresPorIdAreaNoBytes(Integer idArea) {
        return lugarFacade.traerLugaresPorIdAreaNoBytes(idArea);
    }

    public List<Lugar> traerLugaresPorIdArea(Integer idArea) {
        return lugarFacade.traerLugaresPorIdArea(idArea);
    }

    public List<Lugar> traerLugaresPorUUIDBeacon(String uuidBeacon) {
        return lugarFacade.traerLugaresPorUUIDBeacon(uuidBeacon);
    }

    public List<Lugar> traerLugaresPorIdAreaNoBytesLazzy(Integer areaId, int first, int size) {
        return lugarFacade.traerLugaresPorIdAreaNoBytesLazzy(areaId, first, size);
    }

    public int traerLugaresPorIdAreaNoBytesTotal(Integer areaId) {
        return lugarFacade.traerLugaresPorIdAreaNoBytesTotal(areaId);
    }

    public Lugar traerImagenPorIdLugar(Integer lugarId) {
        return lugarFacade.traerImagenPorIdLugar(lugarId);
    }

    public void eliminarLugaresPorIdArea(Integer areaId) {
        List<Lugar> lugares = traerLugaresPorIdAreaNoBytes(areaId);
        if (lugares != null && !lugares.isEmpty()) {
            for (Lugar lugare : lugares) {
                Lugar tmp = buscar(lugare.getLugarId());
                eliminar(tmp);
            }
        }
    }
    
    public Lugar traerIconoPorIdLugar(Integer lugarId) {
        return lugarFacade.traerIconoPorIdLugar(lugarId);
    }

}
