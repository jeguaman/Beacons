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

    private final String TRAER_POR_CORREO_PASS = "select u from Usuario u where u.correoElectronico = :correoElectronico and u.contrasenia = :contrasenia  and u.deleted = 0";
    private final String TRAER_POR_CORREO_LIKE = "select u from Usuario u where u.correoElectronico like :correoElectronico and u.deleted = 0";
    private final String TOTAL_POR_CORREO_LIKE = "select count(u) from Usuario u where u.correoElectronico like :correoElectronico and u.deleted = 0";
    private final String TOTAL = "select count(u) from Usuario u where u.deleted = 0";

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

    public List<Usuario> traerPorCorreoElectronicoLike(String correo, Integer first, Integer size) {
        Query q = em.createQuery(TRAER_POR_CORREO_LIKE);
        q.setParameter("correoElectronico", "%" + correo + "%");
        q.setFirstResult(first);
        q.setMaxResults(size);
        List<Usuario> usuarios = q.getResultList();
        return usuarios;
    }

    public Integer totalPorCorreoElectronicoLike(String correo) {
        Query q = em.createQuery(TOTAL_POR_CORREO_LIKE);
        q.setParameter("correoElectronico", "%" + correo + "%");
        Long total = (Long) q.getSingleResult();
        return total.intValue();
    }

    public Usuario traerUsuario(String correo, String contrasenia) {
        Usuario u = null;
        try {
            Query q = em.createQuery(TRAER_POR_CORREO_PASS);
            q.setParameter("correoElectronico", correo);
            q.setParameter("contrasenia", contrasenia);
            u = (Usuario) q.getSingleResult();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean verificarUsuario(String correo) {
        try {
            Query q = em.createNamedQuery("Usuario.findByCorreoElectronico");
            q.setParameter("correoElectronico", correo);
            Usuario u = (Usuario) q.getSingleResult();
            if (u != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario traerPorCorreoElectronico(String correoElectronico) {
        Query q = em.createNamedQuery("Usuario.findByCorreoElectronico");
        q.setParameter("correoElectronico", correoElectronico);
        return (Usuario) q.getSingleResult();
    }

    public Integer totalUsuarios() {
        Query q = em.createQuery(TOTAL);
        Long total = (Long) q.getSingleResult();
        return total.intValue();
    }
}
