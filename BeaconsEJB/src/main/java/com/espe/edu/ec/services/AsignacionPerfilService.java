/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.AsignacionPerfilFacade;
import com.espe.edu.ec.model.AsignacionPerfil;
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
public class AsignacionPerfilService implements InterfaceService<AsignacionPerfil>, Serializable {

    @EJB
    AsignacionPerfilFacade asignacionPerfilFacade;

    @Override
    public void crear(AsignacionPerfil object) {
        object.setInserted(new Date());
        object.setUpdated(new Date());
        asignacionPerfilFacade.create(object);
    }

    @Override
    public void actualizar(AsignacionPerfil object) {
        asignacionPerfilFacade.edit(object);
    }

    @Override
    public void eliminar(AsignacionPerfil object) {

    }

    @Override
    public List<AsignacionPerfil> buscarTodos() {
        return asignacionPerfilFacade.findAll();
    }

    @Override
    public AsignacionPerfil buscar(Integer id) {
        return asignacionPerfilFacade.find(id);
    }

    @Override
    public List<AsignacionPerfil> traerLazzy(Integer first, Integer size) {
        return asignacionPerfilFacade.traerLazzy(first, size);
    }

    public List<AsignacionPerfil> traerPorUsuarioId(int usuarioId) {
        List<AsignacionPerfil> asignaciones = asignacionPerfilFacade.traerPorUsuarioId(usuarioId);
        if (asignaciones != null && !asignaciones.isEmpty()) {
            return asignaciones;
        } else {
            return null;
        }
    }

}
