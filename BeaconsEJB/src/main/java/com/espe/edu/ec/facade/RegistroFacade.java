/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.Registro;
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
public class RegistroFacade extends AbstractFacade<Registro> {

    public static final String TRAER_AREA_DISPO_FETCH = "Select r from Registro r join fetch r.areaId a join fetch r.dispositivoId d where r.deleted = 0 ";

    @PersistenceContext(unitName = "com.espe.edu.ec_BeaconsEJB_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroFacade() {
        super(Registro.class);
    }

    public List<Registro> traerLazzy(Integer first, Integer size) {
        Query q = em.createNamedQuery("Registro.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        List<Registro> registros = q.getResultList();
        return registros;
    }
    public List<Registro> traerFetchAreaDispositivo(Integer first, Integer size) {
        Query q = em.createQuery(TRAER_AREA_DISPO_FETCH);
        q.setFirstResult(first);
        q.setMaxResults(size);
        List<Registro> registros = q.getResultList();
        return registros;
    }
}
