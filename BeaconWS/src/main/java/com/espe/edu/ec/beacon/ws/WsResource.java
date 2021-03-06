/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.beacon.ws;

import com.espe.edu.ec.services.ws.RestService;
import com.espe.edu.ec.services.ws.WSResponse;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Juan
 */
@Path("ws")
public class WsResource {

    @Context
    private UriInfo context;

    @EJB
    RestService restService;

    /**
     * Creates a new instance of WsResource
     */
    public WsResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.espe.edu.ec.beaconws.WsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of WsResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerAreas")
    public WSResponse traerAreas() {
        return restService.traerAreasWS();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerAreasNoImagen")
    public WSResponse traerTodasAreasNoImagen() {
        return restService.traerTodasAreasNoImagen();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/registrarAreaDispositivo")
    public WSResponse registrarAreaDispositivo(@FormParam("id_area") Integer idArea,
            @FormParam("imei") String imei, @FormParam("tipo") String tipo) {
        return restService.registrarAreaDispositivo(idArea, imei, tipo);
    }

    /**
     *
     * @param idArea
     * @param tipo
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerNotificacionPorAreaTipo")
    public WSResponse traerNotificacionPorAreaTipo(@FormParam("id_area") Integer idArea,
            @FormParam("tipo") String tipo) {
        return restService.traerNotificacionPorAreaTipo(idArea, tipo);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerNotificacionPorBeaconTipo")
    public WSResponse traerNotificacionPorBeaconTipo(@FormParam("id_beacon") Integer idBeacon,
            @FormParam("tipo") String tipo) {
        return restService.traerNotificacionPorBeaconTipo(idBeacon, tipo);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerNotificacionListPorBeacon")
    public WSResponse traerNotificacionListPorBeacon(@FormParam("id_beacon") Integer idBeacon) {
        return restService.traerNotificacionListPorBeacon(idBeacon);
    }

    /**
     *
     * @param idArea
     * @return objeto con id y el titulo
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerLugaresPorIdAreaNoBytes")
    public WSResponse traerLugaresPorIdAreaNoBytes(@FormParam("id_area") Integer idArea) {
        return restService.traerLugaresPorIdAreaNoBytes(idArea);
    }

    /**
     *
     * @param idArea
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerAreaPorId")
    public WSResponse traerAreaPorId(@FormParam("id_area") Integer idArea) {
        return restService.traerAreaPorId(idArea);
    }

    /**
     *
     * @param idLugar
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerLugarPorId")
    public WSResponse traerLugarPorId(@FormParam("id_lugar") Integer idLugar) {
        return restService.traerLugarPorId(idLugar);
    }

    /**
     *
     * @param uuidBeacon
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerLugaresPorUUIDBeacon")
    public WSResponse traerLugaresPorUUIDBeacon(@FormParam("uuid") String uuidBeacon) {
        return restService.traerLugaresPorUUIDBeacon(uuidBeacon);
    }

    /**
     *
     * @param uuidBeacon
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerAreasPorUUIDBeacon")
    public WSResponse traerAreasPorUUIDBeacon(@FormParam("uuid") String uuidBeacon) {
        return restService.traerAreasPorUUIDBeacon(uuidBeacon);
    }

    /**
     *
     * @param idLugar
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerImagenPorIdLugar")
    public WSResponse traerImagenPorIdLugar(@FormParam("id_lugar") Integer idLugar) {
        return restService.traerImagenPorIdLugar(idLugar);
    }

    /**
     *
     * @param idArea
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerImagenPorIdArea")
    public WSResponse traerImagenPorIdArea(@FormParam("id_area") Integer idArea) {
        return restService.traerImagenPorIdArea(idArea);
    }

    /**
     *
     * @param idLugar
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerIconoPorIdLugar")
    public WSResponse traerIconoPorIdLugar(@FormParam("id_lugar") Integer idLugar) {
        return restService.traerIconoPorIdLugar(idLugar);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerBeacons")
    public WSResponse traerBeacons() {
        return restService.traerBeaconsWS();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerBeaconsAsignadasWS")
    public WSResponse traerBeaconsAsignadasWS() {
        return restService.traerBeaconsAsignadasWS();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerEstimoteAsignadosWS")
    public WSResponse traerEstimoteAsignadosWS() {
        return restService.traerEstimoteAsignadosWS();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerAreaBeaconListaPorIdsBeacon")
    public WSResponse traerAreaBeaconListaPorIdsBeacon(@FormParam("listaIdBeacon") String listaIdBeacon) {
        if (listaIdBeacon != null) {
            String[] listaId = listaIdBeacon.split(",");
            List<Integer> idsBeacons = new ArrayList();
            for (String listaId1 : listaId) {
                idsBeacons.add(Integer.valueOf(listaId1));
            }
            return restService.traerAreaBeaconListaPorIdsBeacon(idsBeacons);
        } else {
            return null;
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/traerAreaBeaconPorIdBeacon")
    public WSResponse traerAreaBeaconPorIdBeacon(@FormParam("idBeacon") String IdBeacon) {
        if (IdBeacon != null) {
            Integer id = Integer.valueOf(IdBeacon);
            return restService.traerAreaBeaconPorIdBeacon(id);
        } else {
            return null;
        }
    }
}
