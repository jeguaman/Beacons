/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.Beacon;
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
public class BeaconFacade extends AbstractFacade<Beacon> {

    @PersistenceContext(unitName = "com.espe.edu.ec_BeaconsEJB_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    private final String TOTAL_POR_UUID = "select count(b) from Beacon b where b.uuid =:uuid";

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BeaconFacade() {
        super(Beacon.class);
    }

    public List<Beacon> traerLazzy(Integer first, Integer size) {
        Query q = em.createNamedQuery("Beacon.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        return q.getResultList();
    }

    public List<Beacon> traerPorUuid(Integer first, Integer size, String uuid) {
        Query q = em.createNamedQuery("Beacon.findByUuid");
        q.setParameter("uuid", uuid);
        q.setFirstResult(first);
        q.setMaxResults(size);
        List<Beacon> beacons = q.getResultList();
        return beacons;
    }

    public Integer totalPorUuid(String uuid) {
        Query q = em.createQuery(TOTAL_POR_UUID);
        q.setParameter("uuid", uuid);
        Long total = (Long) q.getSingleResult();
        return total.intValue();
    }
}
