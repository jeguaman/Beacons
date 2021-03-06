/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.Lugar;
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

    private static final String TRAER_TODOS_LUGARES_POR_AREA_NO_BYTES = "SELECT new Lugar(l.lugarId, l.titulo, l.descripcion) FROM Lugar as l  WHERE l.areaId.areaId = :areaId and l.deleted = 0 ";
    private static final String TRAER_IMAGEN_POR_ID_LUGAR = "SELECT new Lugar(l.lugarId, l.imagen) FROM Lugar as l  WHERE l.lugarId = :lugarId and l.deleted = 0 ";
    private static final String TRAER_ICONO_POR_ID_LUGAR = "SELECT new Lugar(l.lugarId, l.icono) FROM Lugar as l  WHERE l.lugarId = :lugarId and l.deleted = 0 ";
    private static final String TRAER_TODOS_LUGARES_POR_AREA_TOTAL = "SELECT count(l) FROM Lugar as l WHERE l.areaId.areaId = :areaId and l.deleted = 0 ";
    private static final String TRAER_TODOS_LUGARES_POR_UUID = "SELECT l FROM Lugar as l JOIN l.areaId as a JOIN a.areaBeaconList as ab WHERE ab.beaconId.uuid = :uuid and l.deleted = 0 ";
    private static final String TRAER_TODOS_LUGARES_POR_AREA = "SELECT l FROM Lugar as l  WHERE l.areaId.areaId = :areaId and l.deleted = 0 ";

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

    public List<Lugar> traerLugaresPorIdArea(Integer areaId) {
        Query q = em.createQuery(TRAER_TODOS_LUGARES_POR_AREA);
        q.setParameter("areaId", areaId);
        return q.getResultList();
    }

    public List<Lugar> traerLugaresPorUUIDBeacon(String uuidBeacon) {
        Query q = em.createQuery(TRAER_TODOS_LUGARES_POR_UUID);
        q.setParameter("uuid", uuidBeacon);
        return q.getResultList();
    }

    public List<Lugar> traerLugaresPorIdAreaNoBytesLazzy(Integer areaId, int first, int size) {
        Query q = em.createQuery(TRAER_TODOS_LUGARES_POR_AREA_NO_BYTES);
        q.setParameter("areaId", areaId);
        q.setFirstResult(first);
        q.setMaxResults(size);
        return q.getResultList();
    }

    public int traerLugaresPorIdAreaNoBytesTotal(Integer areaId) {
        Query q = em.createQuery(TRAER_TODOS_LUGARES_POR_AREA_TOTAL);
        q.setParameter("areaId", areaId);
        Long total = (Long) q.getSingleResult();
        return total.intValue();
    }

    public Lugar traerImagenPorIdLugar(Integer lugarId) {
        try {
            Query q = em.createQuery(TRAER_IMAGEN_POR_ID_LUGAR);
            q.setParameter("lugarId", lugarId);
            return (Lugar) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Lugar traerIconoPorIdLugar(Integer lugarId) {
        try {
            Query q = em.createQuery(TRAER_ICONO_POR_ID_LUGAR);
            q.setParameter("lugarId", lugarId);
            return (Lugar) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
