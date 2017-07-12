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

    private static final String TRAER_TODAS_AREAS_POR_BEACON_NO_BYTES_IMAGE = "SELECT new Area(a.areaId, a.titulo, a.descripcion) FROM Area as a JOIN a.areaBeaconList as ab WHERE ab.beaconId.beaconId = :beaconId";
    private static final String TRAER_TOTAL_AREAS_POR_BEACON_NO_BYTES = "SELECT count(a) FROM Area as a JOIN a.areaBeaconList as ab WHERE ab.beaconId.beaconId = :beaconId";
    private static final String TRAER_AREAS_POR_UUID = "SELECT a FROM Area as a JOIN a.areaBeaconList as b WHERE b.beaconId.uuid = :uuid";
    private static final String TRAER_AREAS_DISPONIBLES = "SELECT a FROM Area as a LEFT JOIN a.areaBeaconList as b WHERE b.areaId.areaId IS NULL";
    private static final String TRAER_TODAS_AREAS_NOT_BYTES_IMAGE = "SELECT new Area(a.areaId, a.titulo, a.descripcion) FROM Area as a";

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

    public List<Area> traerAreasPorIdBeaconNoBytesImageLazzy(Integer beaconId, int first, int size) {
        Query q = em.createQuery(TRAER_TODAS_AREAS_POR_BEACON_NO_BYTES_IMAGE);
        q.setParameter("beaconId", beaconId);
        q.setFirstResult(first);
        q.setMaxResults(size);
        return q.getResultList();
    }

    public int traerAreasPorIdBeaconNoBytesImageTotal(Integer areaId) {
        Query q = em.createQuery(TRAER_TOTAL_AREAS_POR_BEACON_NO_BYTES);
        q.setParameter("beaconId", areaId);
        Long total = (Long) q.getSingleResult();
        return total.intValue();
    }

    public List<Area> traerAreasDisponibles() {
        Query q = em.createQuery(TRAER_AREAS_DISPONIBLES);
        return q.getResultList();
    }

    public List<Area> traerTodasAreasNoImagen() {
        Query q = em.createQuery(TRAER_TODAS_AREAS_NOT_BYTES_IMAGE);
        return q.getResultList();
    }
}
