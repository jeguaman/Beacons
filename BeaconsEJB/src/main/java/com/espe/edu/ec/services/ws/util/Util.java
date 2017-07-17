/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services.ws.util;

import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author Jose Guaman
 * @param <T>
 */
public class Util<T> {

    public String convertirObjetoEnJsonString(T object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public String convertirListaObjetoEnJsonString(List<T> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
 /*
    Gson gson = new Gson();
    System.out.println(
    gson.fromJson("{'id':1,'firstName':'Lokesh','lastName':'Gupta','roles':['ADMIN','MANAGER']}", 
    Employee.class));
     */
}
