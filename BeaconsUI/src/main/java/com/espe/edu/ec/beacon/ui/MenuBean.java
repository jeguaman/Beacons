/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.handler.SessionHandler;
import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.jboss.logging.Logger;

/**
 *
 * @author Juan
 */
@ManagedBean
@SessionScoped
public class MenuBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(MenuBean.class);

    private String menuRuta;
    private String nombreUsuario;
    private SessionHandler sessionHandler;

    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
        sessionHandler = new SessionHandler();
    }

    @PostConstruct
    public void init() {
        menuRuta = sessionHandler.getMenuRuta();
        nombreUsuario = sessionHandler.getNombreUsuario();
    }

    public String getMenuRuta() {
        return menuRuta;
    }

    public void setMenuRuta(String menuRuta) {
        this.menuRuta = menuRuta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void cerrarSesion() {
        try {
            sessionHandler.logoff();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../login.xhtml");
        } catch (IOException ex) {
            LOGGER.error("Error al redirigir a el'noSession.xhtml' desde 'MenuBaseBean'.", ex);
        }
    }
}
