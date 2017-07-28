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

    //private final String TOTAL_POR_UUID = "select count(b) from Beacon b where b.uuid =:uuid and b.deleted = 0";
    private final String TOTAL_POR_NOMBRE = "select count(b) from Beacon b where b.nombre like :uuid and b.deleted = 0";
    private final String TRAER_POR_NOMBRE = "select b from Beacon b where b.nombre like :uuid and b.deleted = 0";
    private final String TRAER_SIN_BYTES = "select new Beacon(b.beaconId, b.uuid, b.major, b.minor) from Beacon b where b.deleted = 0";
    private final String TRAER_ASIGNADOS_SIN_BYTES = "select new Beacon(b.beaconId, b.uuid, b.major, b.minor) from Beacon b left join b.areaBeaconList as a where b.deleted = 0 and a.areaId.areaId is not null";

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

    public List<Beacon> traerPorNombre(Integer first, Integer size, String nombre) {
        Query q = em.createQuery(TRAER_POR_NOMBRE);
        q.setParameter("nombre", "%" + nombre + "%");
        q.setFirstResult(first);
        q.setMaxResults(size);
        List<Beacon> beacons = q.getResultList();
        return beacons;
    }

    public Integer totalPorNombre(String nombre) {
        Query q = em.createQuery(TOTAL_POR_NOMBRE);
        q.setParameter("nombre", "%" + nombre + "%");
        Long total = (Long) q.getSingleResult();
        return total.intValue();
    }

    public List<Beacon> traerTodosSinImagen() {
        Query q = em.createQuery(TRAER_SIN_BYTES);
        List<Beacon> beacons = q.getResultList();
        return beacons;
    }

    public List<Beacon> traerBeaconsAsignadasWS() {
        Query q = em.createQuery(TRAER_ASIGNADOS_SIN_BYTES);
        List<Beacon> beacons = q.getResultList();
        return beacons;
    }
}
