/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.HistorialFacade;
import com.espe.edu.ec.model.Historial;
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
public class HistorialService implements InterfaceService<Historial> {

    @EJB
    HistorialFacade historialFacade;

    @Override
    public void crear(Historial object) {
        historialFacade.create(object);
    }

    @Override
    public void actualizar(Historial object) {
        historialFacade.edit(object);
    }

    @Override
    public void eliminar(Historial object) {

    }

    @Override
    public List<Historial> buscarTodos() {
        return historialFacade.findAll();
    }

    @Override
    public Historial buscar(Integer id) {
        return historialFacade.find(id);
    }

    @Override
    public List<Historial> traerLazzy(Integer first, Integer size) {
        return historialFacade.traerLazzy(first, size);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
