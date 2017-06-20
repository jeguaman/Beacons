/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.AsignacionPerfil;
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
public class AsignacionPerfilFacade extends AbstractFacade<AsignacionPerfil> {

    @PersistenceContext(unitName = "com.espe.edu.ec_BeaconsEJB_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    private String TRAER_POR_USUARIO_ID = "Select a from AsignacionPerfil a join fetch a.perfilId where a.usuarioId.usuarioId= :usuario_id";

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignacionPerfilFacade() {
        super(AsignacionPerfil.class);
    }

    public List<AsignacionPerfil> traerLazzy(Integer first, Integer size) {
        Query q = em.createNamedQuery("AsignacionPerfil.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        return q.getResultList();
    }

    public List<AsignacionPerfil> traerPorUsuarioId(int usuarioId) {
        List<AsignacionPerfil> asignaciones = null;
        try {

            Query q = em.createQuery(TRAER_POR_USUARIO_ID);
            q.setParameter("usuario_id", usuarioId);
            asignaciones = q.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return asignaciones;
    }
}
