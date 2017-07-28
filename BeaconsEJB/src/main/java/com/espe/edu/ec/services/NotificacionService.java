/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.NotificacionFacade;
import com.espe.edu.ec.model.Notificacion;
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
public class NotificacionService implements InterfaceService<Notificacion>, Serializable {

    @EJB
    NotificacionFacade notificacionFacade;

    @Override
    public void crear(Notificacion object) {
        object.setInserted(new Date());
        object.setUpdated(new Date());
        object.setDeleted(Boolean.FALSE);
        notificacionFacade.create(object);
    }

    @Override
    public void actualizar(Notificacion object) {
        object.setUpdated(new Date());
        notificacionFacade.edit(object);
    }

    @Override
    public void eliminar(Notificacion object) {
        object.setUpdated(new Date());
        object.setDeleted(Boolean.TRUE);
        notificacionFacade.edit(object);
    }

    @Override
    public List<Notificacion> buscarTodos() {
        return notificacionFacade.findAll();
    }

    @Override
    public Notificacion buscar(Integer id) {
        return notificacionFacade.find(id);
    }

    @Override
    public List<Notificacion> traerLazzy(Integer first, Integer size) {
        return notificacionFacade.traerLazzy(first, size);
    }

    public Notificacion traerPorAreaYTipo(Integer areaId, String tipo) {
        return notificacionFacade.traerPorAreaYTipo(areaId, tipo);
    }

    public Notificacion traerPorAreaYTipoWS(Integer areaId, String tipo) {
        return notificacionFacade.traerPorAreaYTipoWS(areaId, tipo);
    }

    public List<Notificacion> traerNotificacionPorArea(Integer areaId) {
        return notificacionFacade.traerNotificacionPorArea(areaId);
    }
    public List<Notificacion> traerNotificacionPorAreaWS(Integer areaId) {
        return notificacionFacade.traerNotificacionPorAreaWS(areaId);
    }
}
