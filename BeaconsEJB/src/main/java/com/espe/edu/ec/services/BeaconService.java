/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.BeaconFacade;
import com.espe.edu.ec.model.Beacon;
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
public class BeaconService implements InterfaceService<Beacon>, Serializable {

    @EJB
    BeaconFacade beaconFacade;

    @Override
    public void crear(Beacon object) {
        object.setInserted(new Date());
        object.setUpdated(new Date());
        object.setDeleted(Boolean.FALSE);
        beaconFacade.create(object);
    }

    @Override
    public void actualizar(Beacon object) {
        object.setUpdated(new Date());
        beaconFacade.edit(object);
    }

    @Override
    public void eliminar(Beacon object) {
        object.setUpdated(new Date());
        object.setDeleted(Boolean.TRUE);
        beaconFacade.edit(object);
    }

    @Override
    public List<Beacon> buscarTodos() {
        return beaconFacade.findAll();
    }

    @Override
    public Beacon buscar(Integer id) {
        return beaconFacade.find(id);
    }

    @Override
    public List<Beacon> traerLazzy(Integer first, Integer size) {
        return beaconFacade.traerLazzy(first, size);
    }

    public Integer totalRegistros() {
        return beaconFacade.count();
    }

    public List<Beacon> traerPorNombre(Integer first, Integer size, String nombre) {
        return beaconFacade.traerPorNombre(first, size, nombre);
    }

    public Integer totalPorNombre(String nombre) {
        return beaconFacade.totalPorNombre(nombre);
    }
           
    public List<Beacon> traerTodosSinImagen() {
        return beaconFacade.traerTodosSinImagen();
    }

    public List<Beacon> traerBeaconsAsignadasWS() {
        return beaconFacade.traerBeaconsAsignadasWS();
    }
}
