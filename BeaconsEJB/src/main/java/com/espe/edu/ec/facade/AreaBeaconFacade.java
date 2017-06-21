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

}
