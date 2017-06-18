/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services.ws;

import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.model.Dispositivo;
import com.espe.edu.ec.model.Notificacion;
import com.espe.edu.ec.model.Registro;
import com.espe.edu.ec.services.AreaService;
import com.espe.edu.ec.services.DispositivoService;
import com.espe.edu.ec.services.LugarService;
import com.espe.edu.ec.services.NotificacionService;
import com.espe.edu.ec.services.RegistroService;
import java.io.Serializable;
import java.util.Date;
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
public class RestService implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(RestService.class);
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    LugarService lugarService;

    @EJB
    AreaService areaService;

    @EJB
    RegistroService registroService;

    @EJB
    DispositivoService dispositivoService;

    @EJB
    NotificacionService notificacionService;

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

    /**
     * Jose Guaman
     *
     * @param idArea
     * @return
     */
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

    /**
     * Jose Guaman
     *
     * @param idArea
     * @return
     */
    public WSResponse traerLugaresPorIdAreaNoBytes(Integer idArea) {
        WSResponse response = new WSResponse();
        try {
            response.setEntity(lugarService.traerLugaresPorIdAreaNoBytes(idArea));
            response.setState(true);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    /**
     * Jose Guaman
     *
     * @param idLugar
     * @return
     */
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

    /**
     *
     * @param uuidBeacon
     * @return
     */
    public WSResponse traerLugaresPorUUIDBeacon(String uuidBeacon) {
        WSResponse response = new WSResponse();
        try {
            response.setEntity(lugarService.traerLugaresPorUUIDBeacon(uuidBeacon));
            response.setState(true);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    public WSResponse registrarAreaDispositivo(Integer idArea, String imeiDispositivo, String tipo) {
        WSResponse response = new WSResponse();
        try {
            Dispositivo d = new Dispositivo();
            d.setImei(imeiDispositivo);
            d.setInserted(new Date());
            d.setUpdated(new Date());
            dispositivoService.crear(d);

            Area a = areaService.buscar(idArea);

            Registro r = new Registro();
            r.setAreaId(a);
            r.setDispositivoId(d);
            r.setInserted(new Date());
            r.setTipo(tipo);
            registroService.crear(r);
            response.setState(true);
            response.setEntity(r);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerNotificacionPorAreaTipo(Integer idArea, String tipo) {
        WSResponse response = new WSResponse();
        try {
            Notificacion notificacion = notificacionService.traerPorAreaYTipo(idArea, tipo);
            if (notificacion != null) {
                response.setState(true);
            } else {
                response.setState(false);
            }
            response.setEntity(notificacion);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    /**
     * 
     * @param uuidBeacon
     * @return 
     */
    public WSResponse traerAreasPorUUIDBeacon(String uuidBeacon) {
        WSResponse response = new WSResponse();
        try {
            response.setEntity(areaService.traerAreasPorUUIDBeacon(uuidBeacon));
            response.setState(true);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }
    
}
