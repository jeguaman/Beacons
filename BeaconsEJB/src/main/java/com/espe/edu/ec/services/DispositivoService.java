/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.DispositivoFacade;
import com.espe.edu.ec.model.Dispositivo;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Jose Guaman
 */
public class DispositivoService implements InterfaceService<Dispositivo> {

    @EJB
    DispositivoFacade dispositivoFacade;

    @Override
    public void crear(Dispositivo object) {
        object.setInserted(new Date());
        dispositivoFacade.create(object);
    }

    @Override
    public void actualizar(Dispositivo object) {
        object.setUpdated(new Date());
        dispositivoFacade.edit(object);
    }

    @Override
    public void eliminar(Dispositivo object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dispositivo> buscarTodos() {
        return dispositivoFacade.findAll();
    }

    @Override
    public Dispositivo buscar(Integer id) {
        Dispositivo d = new Dispositivo();
        d.setDispositivoId(id);
        return dispositivoFacade.find(d);
    }

    @Override
    public List<Dispositivo> traerLazzy(Integer first, Integer size) {
        return dispositivoFacade.traerLazzy(first, size);
    }

}
