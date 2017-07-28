/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.facade;

import com.espe.edu.ec.model.AsignacionPerfil;
import com.espe.edu.ec.model.Dispositivo;
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
public class DispositivoFacade extends AbstractFacade<Dispositivo> {

    @PersistenceContext(unitName = "com.espe.edu.ec_BeaconsEJB_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(DispositivoFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DispositivoFacade() {
        super(Dispositivo.class);
    }

    public List<Dispositivo> traerLazzy(Integer first, Integer size) {
        Query q = em.createNamedQuery("Dispositivo.findAll");
        q.setFirstResult(first);
        q.setMaxResults(size);
        return q.getResultList();
    }

    public Dispositivo traerPorImei(String imei) {
        try {
            Query q = em.createNamedQuery("Dispositivo.findByImei");
            q.setParameter("imei", imei);
            List<Dispositivo> dispositivos = q.getResultList();
            if (dispositivos != null && !dispositivos.isEmpty()) {
                LOGGER.error("Existe más de un dispositivo con ese Imei" + imei);
                return dispositivos
                        .get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

}
