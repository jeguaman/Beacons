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

    private static final String TRAER_TODAS_AREAS_POR_BEACON_NO_BYTES_IMAGE = "SELECT new Area(a.areaId, a.titulo, a.descripcion) FROM Area as a JOIN a.areaBeaconList as ab WHERE ab.beaconId.beaconId = :beaconId and a.deleted = 0";
    private static final String TRAER_TOTAL_AREAS_POR_BEACON_NO_BYTES = "SELECT count(a) FROM Area as a JOIN a.areaBeaconList as ab WHERE ab.beaconId.beaconId = :beaconId and a.deleted = 0";
    private static final String TRAER_AREAS_POR_UUID = "SELECT a FROM Area as a JOIN a.areaBeaconList as b WHERE b.beaconId.uuid = :uuid and a.deleted = 0";
    private static final String TRAER_AREAS_DISPONIBLES = "SELECT a FROM Area as a LEFT JOIN a.areaBeaconList as b WHERE b.areaId.areaId IS NULL and a.deleted = 0";
    private static final String TRAER_TODAS_AREAS_NOT_BYTES_IMAGE = "SELECT new Area(a.areaId, a.titulo, a.descripcion) FROM Area as a where a.deleted = 0 ";
    private static final String TRAER_POR_TITULO_LIKE = "select a from Area a where a.titulo like :titulo and a.deleted = 0";
    private static final String TOTAL_POR_TITULO_LIKE = "select count(a) from Area a where a.titulo like :titulo and a.deleted = 0";
    private static final String TRAER_IMAGEN_POR_ID_AREA = "SELECT new Area(a.areaId, a.imagen) FROM Area as a WHERE a.areaId = :areaId and a.deleted = 0";

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

    public List<Area> traerPorTituloLike(Integer first, Integer size, String titulo) {
        Query q = em.createQuery(TRAER_POR_TITULO_LIKE);
        q.setParameter("titulo", "%" + titulo + "%");
        q.setFirstResult(first);
        q.setMaxResults(size);
        List<Area> areas = q.getResultList();
        return areas;
    }

    public Integer totalPorTituloLike(String titulo) {
        Query q = em.createQuery(TOTAL_POR_TITULO_LIKE);
        q.setParameter("titulo", "%" + titulo + "%");
        Long total = (Long) q.getSingleResult();
        return total.intValue();
    }

    public Area traerImagenPorIdArea(Integer areaId) {
        try {
            Query q = em.createQuery(TRAER_IMAGEN_POR_ID_AREA);
            q.setParameter("areaId", areaId);
            return (Area) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
