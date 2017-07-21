/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.RegistroFacade;
import com.espe.edu.ec.model.Registro;
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
public class RegistroService implements InterfaceService<Registro> {

    @EJB
    RegistroFacade registroFacade;

    @Override
    public void crear(Registro object) {
        object.setInserted(new Date());
        object.setDeleted(Boolean.FALSE);
        registroFacade.create(object);
    }

    @Override
    public void actualizar(Registro object) {
        registroFacade.edit(object);
    }

    @Override
    public void eliminar(Registro object) {

    }

    @Override
    public List<Registro> buscarTodos() {
        return registroFacade.findAll();
    }

    @Override
    public Registro buscar(Integer id) {
        return registroFacade.find(id);
    }

    @Override
    public List<Registro> traerLazzy(Integer first, Integer size) {
        return registroFacade.traerLazzy(first, size);
    }

    public Integer total() {
        return registroFacade.count();
    }

    public List<Registro> traerFetchAreaDispositivo(Integer first, Integer size) {
        return registroFacade.traerFetchAreaDispositivo(first, size);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
