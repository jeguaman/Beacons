/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services.ws;

import com.espe.edu.ec.facade.AreaFacade;
import com.espe.edu.ec.facade.LugarFacade;
import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.services.AreaService;
import com.espe.edu.ec.services.LugarService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.jboss.logging.Logger;

/**
 *
 * @author Juan
 */
@Stateless
@LocalBean
public class RestService {

    private static final Logger LOGGER = Logger.getLogger(RestService.class);
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    LugarService lugarService;

    @EJB
    AreaService areaService;

    public WSResponse traerAreasWS() {
        WSResponse response = new WSResponse();
        try {
            response.setEntity(areaService.buscarTodos());
            response.setState(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerAreaPorId(int idArea) {
        WSResponse response = new WSResponse();
        try {
            response.setEntity(areaService.buscar(idArea));
            response.setState(true);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerLugaresPorIdArea(Integer idArea) {
        WSResponse response = new WSResponse();
        try {
            response.setEntity(lugarService.traerLugaresPorIdArea(idArea));
            response.setState(true);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerLugarPorId(Integer idLugar) {
        WSResponse response = new WSResponse();
        try {
            response.setEntity(lugarService.buscar(idLugar));
            response.setState(true);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerAreaPorUUIDBeacon(String uuidBeacon) {
        WSResponse response = new WSResponse();
        try {
               response.setEntity(lugarService.traerLugaresPorIdArea(Integer.SIZE));
               response.setState(true);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

}
