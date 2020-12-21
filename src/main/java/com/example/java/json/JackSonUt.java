package com.example.java.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.time.Instant;

/**
 * @author Liq
 * @date 2020/7/13
 */
public class JackSonUt {

    private static ObjectMapper  objectMapper = null;


    private JackSonUt() {

    }

    public static ObjectMapper getInstance(){
        if(objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}
