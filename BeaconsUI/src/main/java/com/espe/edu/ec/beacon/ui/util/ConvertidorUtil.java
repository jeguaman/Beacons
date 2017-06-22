/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.beacon.ui.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Juan
 */
public class ConvertidorUtil {

    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }

    public static String getString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String convertirMD5(String password) {
        MessageDigest md = null;
        String myHash = "";
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ConvertidorUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (md != null) {
            md.update(password.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        }
        return myHash;
    }
}
