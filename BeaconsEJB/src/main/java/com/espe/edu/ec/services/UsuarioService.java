/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import com.espe.edu.ec.facade.AsignacionPerfilFacade;
import com.espe.edu.ec.facade.PerfilFacade;
import com.espe.edu.ec.facade.UsuarioFacade;
import com.espe.edu.ec.model.AsignacionPerfil;
import com.espe.edu.ec.model.Perfil;
import com.espe.edu.ec.model.Usuario;
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
public class UsuarioService implements InterfaceService<Usuario>, Serializable {
    
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    AsignacionPerfilFacade asignacionPerfilFacade;
    @EJB
    PerfilFacade perfilFacade;
    
    @Override
    public void crear(Usuario object) {
        object.setInserted(new Date());
        object.setUpdated(new Date());
        object.setDeleted(Boolean.FALSE);
        usuarioFacade.create(object);
    }
    
    @Override
    public void actualizar(Usuario object) {
        object.setUpdated(new Date());
        usuarioFacade.edit(object);
    }
    
    @Override
    public void eliminar(Usuario object) {
        object.setUpdated(new Date());
        object.setDeleted(Boolean.TRUE);
        usuarioFacade.edit(object);
    }
    
    @Override
    public List<Usuario> buscarTodos() {
        return usuarioFacade.findAll();
    }
    
    @Override
    public Usuario buscar(Integer id) {
        return usuarioFacade.find(id);
    }
    
    @Override
    public List<Usuario> traerLazzy(Integer first, Integer size) {
        return usuarioFacade.traerLazzy(first, size);
    }
    
    public Usuario traerUsuarioPorCorreoContrasenia(String correo, String contrasenia) {
        return usuarioFacade.traerUsuario(correo, contrasenia);
    }
    
    public boolean verificarUsuarioExistente(String correo) {
        return usuarioFacade.verificarUsuario(correo);
    }
    
    public Integer totalRegistros() {
        return usuarioFacade.totalUsuarios();
    }
    
    public void crearUsuarioConPerfil(Usuario usuario, String codPerfil) {
        Perfil perfil = perfilFacade.traerPerfilPorCodigo(codPerfil);
        if (perfil != null) {
            crear(usuario);
            AsignacionPerfil ap = new AsignacionPerfil();
            ap.setInserted(new Date());
            ap.setUpdated(new Date());
            ap.setUsuarioId(usuario);
            ap.setPerfilId(perfil);
            ap.setDeleted(Boolean.FALSE);
            asignacionPerfilFacade.create(ap);
        }
    }
    
    public List<Usuario> traerPorCorreoElectronicoLike(String correo, Integer first, Integer size) {
        return usuarioFacade.traerPorCorreoElectronicoLike(correo, first, size);
    }
    
    public Integer totalPorCorreoElectronicoLike(String correo) {
        return usuarioFacade.totalPorCorreoElectronicoLike(correo);
    }
    
    public Usuario traerPorCorreoElectronico(String correoElectronico) {
        return usuarioFacade.traerPorCorreoElectronico(correoElectronico);
    }
    
}
