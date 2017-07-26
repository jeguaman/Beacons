/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.AreaBeacon;
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
public class AreaBeaconFacade extends AbstractFacade<AreaBeacon> {

    @PersistenceContext(unitName = "com.espe.edu.ec_BeaconsEJB_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    private static final String TRAER_AREA_BEACON_POR_BEACON_ID = "SELECT ab FROM AreaBeacon ab WHERE ab.beaconId.beaconId = :id and ab.deleted = 0 ";
    private static final String TRAER_AREA_BEACON_POR_AREA_ID = "SELECT ab FROM AreaBeacon ab WHERE ab.areaId.areaId = :idArea and ab.deleted = 0 ";
    private static final String TRAER_AREA_BEACON_ID_POR_BEACON_ID = "SELECT ab.areaId.areaId, ab.beaconId.beaconId FROM AreaBeacon ab WHERE ab.beaconId.beaconId = :id and ab.deleted = 0 ";

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaBeaconFacade() {
        super(AreaBeacon.class);
    }

    public List<AreaBeacon> traerLazzy(Integer first, Integer size) {
        Query q = em.createNamedQuery("AreaBeacon.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        return q.getResultList();
    }

    public List<AreaBeacon> traerPorAreaIdFectchLugar(Integer areaId, Integer first, Integer size) {
        Query q = em.createQuery("AreaBeacon.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        return q.getResultList();
    }

    public AreaBeacon traerAreaBeaconPorBeacon(Integer beaconId) {
        try {
            Query q = em.createQuery(TRAER_AREA_BEACON_POR_BEACON_ID);
            q.setParameter("id", beaconId);
            return (AreaBeacon) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public AreaBeacon traerAreaBeaconIdPorBeacon(Integer beaconId) {
        try {
            Query q = em.createQuery(TRAER_AREA_BEACON_ID_POR_BEACON_ID);
            q.setParameter("id", beaconId);
            Object[] area = (Object[]) q.getSingleResult();
            AreaBeacon ab = null;
            if (area != null && area.length == 2) {
                ab = new AreaBeacon((Integer)area[0],(Integer) area[1]);
            }
            return ab;
        } catch (Exception e) {
            return null;
        }
    }

    public AreaBeacon traerAreaBeaconPorArea(Integer areaId) {
        try {
            Query q = em.createQuery(TRAER_AREA_BEACON_POR_AREA_ID);
            q.setParameter("idArea", areaId);
            return (AreaBeacon) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
