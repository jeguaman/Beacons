/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.Area;
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
public class AreaFacade extends AbstractFacade<Area> {

    @PersistenceContext(unitName = "com.espe.edu.ec_BeaconsEJB_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    private static final String TRAER_AREAS_POR_UUID = "SELECT a FROM Area as a JOIN a.areaBeaconList as b WHERE b.beaconId.uuid = :uuid";
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaFacade() {
        super(Area.class);
    }
    
    public List<Area> traerAreasLazzy(Integer first, Integer size) {
        Query q = em.createNamedQuery("Area.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        return q.getResultList();
    }
    
      public List<Area> traerAreasPorUUIDBeacon(String uuidBeacon) {
        Query q = em.createQuery(TRAER_AREAS_POR_UUID);
        q.setParameter("uuid", uuidBeacon);
        return q.getResultList();
    }
}
