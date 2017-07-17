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
    private String jsonEntity;

    public WSResponse() {
    }

    public WSResponse(boolean state, String jsonEntity) {
        this.state = state;
        this.jsonEntity = jsonEntity;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getJsonEntity() {
        return jsonEntity;
    }

    public void setJsonEntity(String jsonEntity) {
        this.jsonEntity = jsonEntity;
    }

   
}
