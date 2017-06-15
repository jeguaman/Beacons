/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services.ws;

import java.io.Serializable;

/**
 *
 * @author Juan
 */
public class WSResponse implements Serializable {

    private boolean state;
    private Object entity;

    public WSResponse() {
    }

    public WSResponse(boolean state, Object entity) {
        this.state = state;
        this.entity = entity;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

}
