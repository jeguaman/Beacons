/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.beacon.ui.util;

/**
 *
 * @author Juan
 */
public class ValidationUtil {

    public static Boolean soloCorreoElectronicoInstitucional(String str) {
        return str.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(espe.edu.ec)$");
    }
}
