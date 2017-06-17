/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.handler;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan
 */

public class SessionHandler implements Serializable {

    /**
     * Creates a new instance of SessionHandler
     */
    private HttpSession session;

    public SessionHandler() {
        try {
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        } catch (Exception ex) {

        }
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getNombreUsuario() {
        String attrName = "usuario";
        String salida = "";
        if (session != null) {
            if (session.getAttribute(attrName) != null) {
                return session.getAttribute(attrName).toString();
            }
        }
        return salida;
    }

    public void setNombreUsuario(String nombreUsuario) {
        String attrName = "usuario";
        session.setAttribute(attrName, nombreUsuario);
    }

    public Integer getUsuarioId() {
        String attrName = "usuarioId";
        int salida = 0;
        if (session != null) {
            if (session.getAttribute(attrName) != null) {
                return Integer.parseInt(session.getAttribute(attrName).toString());
            }
        }
        return salida;
    }

    public void setUsuarioId(int usuarioId) {
        String attrName = "usuarioId";
        session.setAttribute(attrName, usuarioId);
    }

    public String getPerfil() {
        String attrName = "perfil";
        String salida = "";
        if (session != null) {
            if (session.getAttribute(attrName) != null) {
                return session.getAttribute(attrName).toString();
            }
        }
        return salida;
    }

    public void setPerfil(String codigoPerfil) {
        String attrName = "perfil";
        session.setAttribute(attrName, codigoPerfil);
    }

    public String getNombreCompleto() {
        String attrName = "nombre";
        if (session.getAttribute(attrName) != null) {
            return session.getAttribute(attrName).toString();
        } else {
            return "";
        }
    }

    public void setNombreCompleto(String nombreCompleto) {
        String attrName = "nombre";
        session.setAttribute(attrName, nombreCompleto);
    }

    public void setCorreo(String correo) {
        String attrCorreo = "correo";
        session.setAttribute(attrCorreo, correo);
    }

    public String getCorreo() {
        String attrName = "correo";
        if (session.getAttribute(attrName) != null) {
            return session.getAttribute(attrName).toString();
        } else {
            return "";
        }
    }

    public void logoff() {
        session.invalidate();
    }
}