/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.UsuarioFacade;
import com.espe.edu.ec.model.Usuario;
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
public class UsuarioService implements InterfaceService<Usuario> {

    @EJB
    UsuarioFacade usuarioFacade;

    @Override
    public void crear(Usuario object) {
        object.setInserted(new Date());
        object.setUpdated(new Date());
        usuarioFacade.create(object);
    }

    @Override
    public void actualizar(Usuario object) {
        object.setInserted(new Date());
        object.setUpdated(new Date());
        usuarioFacade.edit(object);
    }

    @Override
    public void eliminar(Usuario object) {

    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioFacade.findAll();
    }

    @Override
    public Usuario buscar(Integer id) {
        Usuario u = new Usuario();
        u.setUsuarioId(id);
        return usuarioFacade.find(u);
    }

    @Override
    public List<Usuario> traerLazzy(Integer first, Integer size) {
        return usuarioFacade.traerLazzy(first, size);
    }

}
