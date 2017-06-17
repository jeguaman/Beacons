/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec;

import com.espe.edu.ec.handler.SessionHandler;
import com.espe.edu.ec.services.UsuarioService;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.jboss.logging.Logger;

/**
 *
 * @author Juan
 */
@Named(value = "loginBean")
@Dependent
public class LoginBean {

    private static final Logger LOGGER = Logger.getLogger(LoginBean.class);
    private String correo;
    private String contrasenia;
    private SessionHandler sessionHandler;
    
    @EJB
    UsuarioService usuarioService;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        sessionHandler = new SessionHandler();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void checkAccess() {
        
    }
}
