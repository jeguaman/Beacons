/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.NotificacionFacade;
import com.espe.edu.ec.model.Notificacion;
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
public class NotificacionService implements InterfaceService<Notificacion> {

    @EJB
    NotificacionFacade notificacionFacade;

    @Override
    public void crear(Notificacion object) {
        notificacionFacade.create(object);
    }

    @Override
    public void actualizar(Notificacion object) {
        notificacionFacade.edit(object);
    }

    @Override
    public void eliminar(Notificacion object) {

    }

    @Override
    public List<Notificacion> buscarTodos() {
        return notificacionFacade.findAll();
    }

    @Override
    public Notificacion buscar(Integer id) {
        Notificacion n = new Notificacion();
        n.setNotificacionId(id);
        return notificacionFacade.find(n);
    }

    @Override
    public List<Notificacion> traerLazzy(Integer first, Integer size) {
        return notificacionFacade.traerLazzy(first, size);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
