/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services.ws;

import com.espe.edu.ec.facade.AreaFacade;
import com.espe.edu.ec.facade.LugarFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

/**
 *
 * @author Juan
 */
@Stateless
@LocalBean
public class RestService {

    private static Logger LOGGER = Logger.getLogger(RestService.class);
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    AreaFacade areaFacade;
    @EJB
    LugarFacade lugarFacade;

    public WSResponse traerAreasWS() {
        
        WSResponse response = new WSResponse();
        try {
            response.setEntity(areaFacade.findAll());
            response.setState(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerAreaPorId(int idArea) {
        WSResponse response = new WSResponse();
        return response;
    }

}
