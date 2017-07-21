/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.handler.SessionHandler;
import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    private StreamedContent file;

    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
        sessionHandler = new SessionHandler();
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
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

    public void descargarAyuda() {
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/prueba.docx");
        file = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.wordprocessingml.document","Manual_Ayuda.docx");
    }
}
