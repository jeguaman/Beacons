/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services.ws;

import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.model.Dispositivo;
import com.espe.edu.ec.model.Historial;
import com.espe.edu.ec.model.Lugar;
import com.espe.edu.ec.model.Notificacion;
import com.espe.edu.ec.model.Registro;
import com.espe.edu.ec.services.AreaService;
import com.espe.edu.ec.services.DispositivoService;
import com.espe.edu.ec.services.HistorialService;
import com.espe.edu.ec.services.LugarService;
import com.espe.edu.ec.services.NotificacionService;
import com.espe.edu.ec.services.RegistroService;
import com.espe.edu.ec.services.ws.util.Util;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

    @EJB
    HistorialService historialService;

    public WSResponse traerAreasWS() {
        WSResponse response = new WSResponse();
        Util util = new Util();
        try {
            List<Area> listaArea = areaService.buscarTodos();
            response.setJsonEntity(util.convertirListaObjetoEnJsonString(listaArea));
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
        Util util = new Util();
        try {
            Area area = areaService.buscar(idArea);
            response.setJsonEntity(util.convertirObjetoEnJsonString(area));
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
        Util util = new Util();
        try {
            List<Lugar> listaLugar = lugarService.traerLugaresPorIdAreaNoBytes(idArea);
            response.setJsonEntity(util.convertirListaObjetoEnJsonString(listaLugar));
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
        Util util = new Util();
        try {
            Lugar lugar = lugarService.buscar(idLugar);
            response.setJsonEntity(util.convertirObjetoEnJsonString(lugar));
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
        Util util = new Util();
        try {
            List<Lugar> lista = lugarService.traerLugaresPorUUIDBeacon(uuidBeacon);
            response.setJsonEntity(util.convertirListaObjetoEnJsonString(lista));
            response.setState(true);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    public WSResponse registrarAreaDispositivo(Integer idArea, String imeiDispositivo, String tipo) {
        WSResponse response = new WSResponse();
        Util util = new Util();
        Historial h = null;
        try {
            Dispositivo d = new Dispositivo();
            d.setImei(imeiDispositivo);
            d.setInserted(new Date());
            d.setUpdated(new Date());
            dispositivoService.crear(d);
            //h.setCodigoHistorial(ConstanteBeacon.CREACION);
            h = new Historial();
            h.setCodigoHistorial("C1");
            h.setDescripcion("Dispositivo creado con éxito " + d.getDispositivoId());
            historialService.crear(h);
            Area a = areaService.buscar(idArea);

            Registro r = new Registro();
            r.setAreaId(a);
            r.setDispositivoId(d);
            r.setInserted(new Date());
            r.setTipo(tipo);
            registroService.crear(r);
            h = new Historial();
            h.setCodigoHistorial("C1");
            h.setDescripcion("Registro creado con éxito " + r.getRegistroId());
            historialService.crear(h);

            response.setState(true);
            response.setJsonEntity(util.convertirObjetoEnJsonString(r));
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerNotificacionPorAreaTipo(Integer idArea, String tipo) {
        WSResponse response = new WSResponse();
        Util util = new Util();
        try {
            Notificacion notificacion = notificacionService.traerPorAreaYTipo(idArea, tipo);
            if (notificacion != null) {
                response.setState(true);
            } else {
                response.setState(false);
            }
            response.setJsonEntity(util.convertirObjetoEnJsonString(notificacion));
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
        Util util = new Util();
        try {
            List<Area> lista = areaService.traerAreasPorUUIDBeacon(uuidBeacon);
            response.setJsonEntity(util.convertirListaObjetoEnJsonString(lista));
            response.setState(true);
        } catch (Exception e) {
            LOGGER.error(e);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerTodasAreasNoImagen() {
        WSResponse response = new WSResponse();
        Util util = new Util();
        try {
            List<Area> lista = areaService.traerTodasAreasNoImagen();
            response.setJsonEntity(util.convertirListaObjetoEnJsonString(lista));
            response.setState(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerImagenPorIdLugar(Integer idLugar) {
        WSResponse response = new WSResponse();
        Util util = new Util();
        try {
            Lugar lugar = lugarService.traerImagenPorIdLugar(idLugar);
            response.setJsonEntity(util.convertirObjetoEnJsonString(lugar));
            response.setState(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerImagenPorIdArea(Integer idArea) {
        WSResponse response = new WSResponse();
        Util util = new Util();
        try {
            Area area = areaService.traerImagenPorIdArea(idArea);
            response.setJsonEntity(util.convertirObjetoEnJsonString(area));
            response.setState(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setState(false);
        }
        return response;
    }

    public WSResponse traerIconoPorIdLugar(Integer idLugar) {
        WSResponse response = new WSResponse();
        Util util = new Util();
        try {
            Lugar lugar = lugarService.traerIconoPorIdLugar(idLugar);
            response.setJsonEntity(util.convertirObjetoEnJsonString(lugar));
            response.setState(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setState(false);
        }
        return response;
    }
}
