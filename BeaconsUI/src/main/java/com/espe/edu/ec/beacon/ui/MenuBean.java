/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.handler.SessionHandler;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Juan
 */
@ManagedBean
@SessionScoped
public class MenuBean implements Serializable {

    private String menuRuta;
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
    }

    public String getMenuRuta() {
        return menuRuta;
    }

    public void setMenuRuta(String menuRuta) {
        this.menuRuta = menuRuta;
    }

}
