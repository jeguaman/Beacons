/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.model.Notificacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jboss.logging.Logger;

/**
 *
 * @author Juan
 */
@Stateless
public class NotificacionFacade extends AbstractFacade<Notificacion> {
    
    private static final Logger LOGGER = Logger.getLogger(NotificacionFacade.class);
    private static final String TRAER_POR_AREA_Y_TIPO = "Select n from Notificacion as n join fetch n.areaId as a where a.areaId = :areaId and n.tipo = :tipo and n.deleted = 0";
    private static final String TRAER_POR_AREA = "Select n from Notificacion as n join fetch n.areaId as a where a.areaId = :areaId and n.deleted = 0";
    private static final String TRAER_POR_AREA_Y_TIPO_WS = "Select n from Notificacion as n where n.areaId.areaId = :areaId and n.tipo = :tipo and n.deleted = 0";
    @PersistenceContext(unitName = "com.espe.edu.ec_BeaconsEJB_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public NotificacionFacade() {
        super(Notificacion.class);
    }
    
    public List<Notificacion> traerLazzy(Integer first, Integer size) {
        Query q = em.createNamedQuery("Notificacion.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        List<Notificacion> notificaciones = q.getResultList();
        return notificaciones;
    }
    
    public Notificacion traerPorAreaYTipo(Integer areaId, String tipo) {
        Query q = em.createQuery(TRAER_POR_AREA_Y_TIPO);
        q.setParameter("areaId", areaId);
        q.setParameter("tipo", tipo);
        List<Notificacion> notificaciones = q.getResultList();
        if (notificaciones != null && !notificaciones.isEmpty()) {
            if (notificaciones.size() > 1) {
                LOGGER.warn("Existe más de una notificación con el area " + areaId);
            }
            return notificaciones.get(0);
        }
        return null;
    }
    
    public Notificacion traerPorAreaYTipoWS(Integer areaId, String tipo) {
        Query q = em.createQuery(TRAER_POR_AREA_Y_TIPO_WS);
        q.setParameter("areaId", areaId);
        q.setParameter("tipo", tipo);
        List<Notificacion> notificaciones = q.getResultList();
        Notificacion notificacion;
        if (notificaciones != null && !notificaciones.isEmpty()) {
            if (notificaciones.size() > 1) {
                LOGGER.warn("Existe más de una notificación con el area " + areaId);
            }
            notificacion = notificaciones.get(0);
            Area a = new Area();
            a.setAreaId(areaId);
            notificacion.setAreaId(a);
            return notificacion;
        }
        return null;
    }
    
    public List<Notificacion> traerNotificacionPorArea(Integer areaId) {
        Query q = em.createQuery(TRAER_POR_AREA);
        q.setParameter("areaId", areaId);
        return q.getResultList();
    }
}
