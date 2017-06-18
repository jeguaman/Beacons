/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.Lugar;
import java.util.ArrayList;
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
public class LugarFacade extends AbstractFacade<Lugar> {

    @PersistenceContext(unitName = "com.espe.edu.ec_BeaconsEJB_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    private static final String TRAER_TODOS_LUGARES_POR_AREA_NO_BYTES = "SELECT new Lugar(l.lugarId, l.titulo) FROM Lugar as l JOIN l.areaId as a WHERE a.areaId = :areaId";
    private static final String TRAER_TODOS_LUGARES_POR_UUID = "SELECT l FROM Lugar as l JOIN l.areaId as a JOIN a.areaBeaconList as ab WHERE ab.beaconId.uuid = :uuid";

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LugarFacade() {
        super(Lugar.class);
    }

    public List<Lugar> traerLugaresLazzy(Integer first, Integer size) {
        Query q = em.createNamedQuery("Lugar.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        List<Lugar> lugares = q.getResultList();
        return lugares;
    }

    public List<Lugar> traerLugaresPorIdAreaNoBytes(Integer areaId) {
        Query q = em.createQuery(TRAER_TODOS_LUGARES_POR_AREA_NO_BYTES);
        q.setParameter("areaId", areaId);
        return q.getResultList();
    }

    public List<Lugar> traerLugaresPorUUIDBeacon(String uuidBeacon) {
        Query q = em.createQuery(TRAER_TODOS_LUGARES_POR_UUID);
        q.setParameter("uuid", uuidBeacon);
        return q.getResultList();
    }

}
