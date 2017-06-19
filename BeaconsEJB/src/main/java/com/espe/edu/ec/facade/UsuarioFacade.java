/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.espe.edu.ec_BeaconsEJB_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public List<Usuario> traerLazzy(Integer first, Integer size) {
        Query q = em.createNamedQuery("Usuario.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        List<Usuario> usuarios = q.getResultList();
        return usuarios;
    }

    public Usuario traerUsuario(String correo, String contrasenia) {
        Usuario u = null;
        try {
            Query q = em.createQuery(correo);
            q.setParameter("correo", correo);
            q.setParameter("contrasenia", contrasenia);
            List<Usuario> usuarios = q.getResultList();
            if (usuarios != null) {
                return u;
            } else {
                return u;
            }
        } catch (Exception e) {
            return u;
        }
    }
}
