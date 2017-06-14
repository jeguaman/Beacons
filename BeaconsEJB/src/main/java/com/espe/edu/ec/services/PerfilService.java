/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.PerfilFacade;
import com.espe.edu.ec.model.Perfil;
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
public class PerfilService implements InterfaceService<Perfil> {

    @EJB
    PerfilFacade perfilFacade;

    @Override
    public void crear(Perfil object) {
        perfilFacade.create(object);
    }

    @Override
    public void actualizar(Perfil object) {
        perfilFacade.edit(object);
    }

    @Override
    public void eliminar(Perfil object) {

    }

    @Override
    public List<Perfil> buscarTodos() {
        return perfilFacade.findAll();
    }

    @Override
    public Perfil buscar(Integer id) {
        Perfil perfil= new Perfil();
        perfil.setPerfilId(id);
        return perfilFacade.find(perfil);
    }

    @Override
    public List<Perfil> traerLazzy(Integer first, Integer size) {
        return perfilFacade.traerLazzy(first, size);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
