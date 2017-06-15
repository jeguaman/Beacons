/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services.ws;

import com.espe.edu.ec.facade.AreaFacade;
import com.espe.edu.ec.facade.DispositivoFacade;
import com.espe.edu.ec.facade.LugarFacade;
import com.espe.edu.ec.facade.RegistroFacade;
import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.model.Dispositivo;
import com.espe.edu.ec.model.Registro;
import com.espe.edu.ec.services.AreaService;
import com.espe.edu.ec.services.DispositivoService;
import com.espe.edu.ec.services.LugarService;
import com.espe.edu.ec.services.RegistroService;
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
public class RestService {

    private static Logger LOGGER = Logger.getLogger(RestService.class);
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    AreaService areaService;
    @EJB
    LugarService lugarService;
    @EJB
    DispositivoService dispositivoService;
    @EJB
    RegistroService registroService;

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
        return response;
    }

    public WSResponse registrarAreaDispositivo(int idArea, String imeiDispositivo) {
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
            r.setTipo("E");
            registroService.crear(r);
            response.setState(true);
            response.setEntity(r);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return response;
    }

}
